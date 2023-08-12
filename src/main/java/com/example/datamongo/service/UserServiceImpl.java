package com.example.datamongo.service;

import com.example.datamongo.dto.UserDto;
import com.example.datamongo.entity.User;
import com.example.datamongo.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        User user = objectMapper.convertValue(userDto, User.class);
        user.setCreatedAt(LocalDateTime.now());
        User userSaved = userRepository.save(user);
        log.info("the user " + userSaved.getFirstName() + " " + userSaved.getLastName() + " was saved");
        return objectMapper.convertValue(userSaved, UserDto.class);
    }

    @Override
    public UserDto findUserById(String id) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        return objectMapper.convertValue(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAllUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return userRepository
                .findAll()
                .stream()
                .map(user -> objectMapper.convertValue(user, UserDto.class)).toList();
    }

    @Override
    public UserDto udpateUser(String id, UserDto userDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        UserDto userDtoDB = this.findUserById(id);
        User user = User.builder()
                .id(userDtoDB.getId())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .birthDate(userDto.getBirthDate())
                .createdAt(userDtoDB.getCreatedAt())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
        User userUpdated = userRepository.save(user);
        return objectMapper.convertValue(userUpdated, UserDto.class);
    }

    @Override
    public void deleteUser(String id) {
        UserDto userDtoDB = this.findUserById(id);
        userRepository.deleteById(userDtoDB.getId());
    }
}
