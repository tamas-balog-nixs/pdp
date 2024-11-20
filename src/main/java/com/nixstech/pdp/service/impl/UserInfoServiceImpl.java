package com.nixstech.pdp.service.impl;

import com.nixstech.pdp.entity.UserInfo;
import com.nixstech.pdp.repository.UserInfoRepository;
import com.nixstech.pdp.service.UserInfoService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

  private final UserInfoRepository userInfoRepository;

  @Override
  public List<UserInfo> getAllUserDetails() {
    return userInfoRepository.findAll();
  }

  @Override
  public void saveUserDetail(UserInfo userInfo) {
    userInfoRepository.save(userInfo);
  }

  @Override
  public UserInfo getUserDetail(int id) {
    UserInfo userInfo = null;
    Optional<UserInfo> optional = userInfoRepository.findById(id);
    if (optional.isPresent()) {
      userInfo = optional.get();
    }
    return userInfo;
  }

  @Override
  public void deleteUserDetail(int id) {
    userInfoRepository.deleteById(id);
  }
}
