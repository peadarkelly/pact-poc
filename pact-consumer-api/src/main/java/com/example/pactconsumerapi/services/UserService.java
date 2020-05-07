package com.example.pactconsumerapi.services;

import com.example.pactconsumerapi.dtos.UserCreateRequest;
import com.example.pactconsumerapi.dtos.UserResponse;
import com.example.pactconsumerapi.entities.User;
import com.example.pactconsumerapi.mappers.UserMapper;
import com.example.pactconsumerapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper mapper;
    private final UserEligibilityService eligibilityService;
    private final UserRepository repository;

    public UserService(
            UserMapper mapper,
            UserEligibilityService eligibilityService,
            UserRepository repository) {
        this.mapper = mapper;
        this.eligibilityService = eligibilityService;
        this.repository = repository;
    }

    public List<UserResponse> getUsers() {
        final List<User> users = this.repository.findAll();

        return users.stream().map(user -> this.mapper.mapToUserResponse(user)).collect(Collectors.toList());
    }

    public boolean isUserEligible(UserCreateRequest user) {
        return this.eligibilityService.isUserEligible(user.getEmail());
    }

    public void createUser(UserCreateRequest body) {
        final User user = this.mapper.mapToUser(body);

        this.repository.saveAndFlush(user);
    }
}

