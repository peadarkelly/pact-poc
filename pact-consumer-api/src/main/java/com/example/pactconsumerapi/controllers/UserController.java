package com.example.pactconsumerapi.controllers;

import com.example.pactconsumerapi.dtos.UserCreateRequest;
import com.example.pactconsumerapi.dtos.UserResponse;
import com.example.pactconsumerapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers() {
        final List<UserResponse> users = this.userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody final UserCreateRequest user) {
        if (!this.userService.isUserEligible(user)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        this.userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}