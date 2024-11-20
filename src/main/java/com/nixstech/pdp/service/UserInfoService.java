package com.nixstech.pdp.service;

import com.nixstech.pdp.entity.UserInfo;
import java.util.List;

public interface UserInfoService {

  List<UserInfo> getAllUserDetails();

  void saveUserDetail(UserInfo userInfo);

  UserInfo getUserDetail(int id);

  void deleteUserDetail(int id);
}
