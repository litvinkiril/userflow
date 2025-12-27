package com.hse.userflow.storingservice.service.impl;

import com.hse.userflow.dto.user.UserCreateDto;
import com.hse.userflow.dto.user.UserDto;
import com.hse.userflow.storingservice.exception.NotFoundException;
import com.hse.userflow.storingservice.mapper.UserMapper;
import com.hse.userflow.storingservice.model.User;
import com.hse.userflow.storingservice.repository.UserRepository;
import com.hse.userflow.storingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hse.userflow.storingservice.mapper.UserMapper.toDto;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
        log.debug("пользователь с id{} успешно удален", userId);
    }

    @Override
    @Transactional
    public UserDto addUser(UserCreateDto newUser) {
        User user = userRepository.save(UserMapper.toEntity(newUser));
        log.debug("пользователь{} успешно сохранен", user);
        return toDto(user);
    }

    @Override
    public UserDto findById(Integer userId) {
        return toDto(getUserById(userId));
    }

    private User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("пользователь с id " + id));
    }


}
