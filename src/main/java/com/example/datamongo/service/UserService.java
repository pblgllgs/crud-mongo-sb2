package com.example.datamongo.service;

import com.example.datamongo.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto findUserById(String id);
    List<UserDto> findAllUsers();
    UserDto udpateUser(String id, UserDto userDto);

    void deleteUser(String id);
}
