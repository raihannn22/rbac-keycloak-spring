package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {
    List<UserRepresentation> listUsers(int first, int max);
    List<UserRepresentation> getUserByEmail(UserRequestDto user);
    String createUser(UserRequestDto userRequestDto);
    String setPassword(String username, String password);
}
