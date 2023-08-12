package com.example.datamongo.controller;

import com.example.datamongo.dto.UserDto;
import com.example.datamongo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto  userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> finUserById(@PathVariable("id") String id){
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> finUserById(){
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("id") String id,
            @RequestBody UserDto userDto
    ){
        return new ResponseEntity<>(userService.udpateUser(id,userDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id){
        return new ResponseEntity<>("User deleted successfully!",HttpStatus.OK);
    }
}