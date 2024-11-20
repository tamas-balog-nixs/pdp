package com.nixstech.pdp.service.impl;

import com.nixstech.pdp.entity.User;
import com.nixstech.pdp.exception.UserNotFoundException;
import com.nixstech.pdp.repository.UserRepository;
import com.nixstech.pdp.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void saveUser(User user) {
    boolean isUpdatedUser = (user.getId() != null);
    if (isUpdatedUser) {
      User existingUser = userRepository.getReferenceById(user.getId());

      if (user.getPassword().isEmpty()) {
        user.setPassword(existingUser.getPassword());
      }
    }
    userRepository.save(user);
  }

  @Override
  public User getUser(int id) throws UserNotFoundException {
    try {
      return userRepository.getReferenceById(id);
    } catch (NoSuchElementException ex) {
      throw new UserNotFoundException("Couldn't find any user with id " + id);
    }
  }

  @Override
  public User getUserByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  @Override
  public void deleteUser(Integer id) throws UserNotFoundException {
    Long countById = userRepository.countById(id);
    if (countById == null || countById == 0) {
      throw new UserNotFoundException("Couldn't find any user with id " + id);
    }
    userRepository.deleteById(id);
  }

  @Override
  public String isLoginUnique(Integer id, String login) {
    User userByLogin = userRepository.findByLogin(login);
    boolean isCreatingNew = (id == null);

    if (isCreatingNew) {
      if (userByLogin != null) {
        return "Duplicate";
      }
    } else {
      if (!Objects.equals(userByLogin.getId(), id)) {
        return "Duplicate";
      }
    }
    return "OK";
  }

  /*
  Added this method to check unique login for user while he's registration
  You can modify it to have more unique fields.
  (if you need unique fields in admin panel for user, change another methods like isLoginUnique)
   */
  @Override
  public boolean checkLoginRegistration(String login) {
    User user = userRepository.findByLogin(login);

    return user == null;
  }

}
