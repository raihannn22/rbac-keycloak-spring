package com.example.demo.service;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface UserService {
    List<UserRepresentation> listUsers(int first, int max);
}
