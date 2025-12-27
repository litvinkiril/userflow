package com.hse.userflow.storingservice.controller;

import com.hse.userflow.dto.user.UserCreateDto;
import com.hse.userflow.dto.user.UserDto;
import com.hse.userflow.storingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateDto newUser) {
        return userService.addUser(newUser);
    }

    @GetMapping("/{userId}")
    public UserDto findById(@PathVariable Integer userId) {
        return userService.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }
}
