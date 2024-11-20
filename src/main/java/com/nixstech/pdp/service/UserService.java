package com.nixstech.pdp.service;

import com.nixstech.pdp.entity.User;
import com.nixstech.pdp.exception.UserNotFoundException;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  void saveUser(User user);

  String isLoginUnique(Integer id, String login);

  boolean checkLoginRegistration(String login);

  User getUser(int id) throws UserNotFoundException;

  User getUserByLogin(String login);

  void deleteUser(Integer id) throws UserNotFoundException;
}
