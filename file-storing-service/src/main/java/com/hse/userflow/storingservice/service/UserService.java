package com.hse.userflow.storingservice.service;

import com.hse.userflow.dto.user.UserCreateDto;
import com.hse.userflow.dto.user.UserDto;

public interface UserService {

    UserDto addUser(UserCreateDto newUser);
    void deleteUser(Integer userId);
    UserDto findById(Integer userId);

}
