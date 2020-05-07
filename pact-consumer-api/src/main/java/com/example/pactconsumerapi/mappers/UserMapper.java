package com.example.pactconsumerapi.mappers;

import com.example.pactconsumerapi.dtos.UserCreateRequest;
import com.example.pactconsumerapi.dtos.UserResponse;
import com.example.pactconsumerapi.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponse mapToUserResponse(User user) {
        final UserResponse response = new UserResponse();

        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;
    }

    public User mapToUser(UserCreateRequest request) {
        final User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return user;
    }
}
