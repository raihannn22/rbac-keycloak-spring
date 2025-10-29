package com.example.demo.service.Impl;

import com.example.demo.config.KeycloakProps;
import com.example.demo.service.UserService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Keycloak keycloak;
    private final KeycloakProps props;
    private UsersResource usersResourceInstance() {
        return keycloak.realm(props.getRealm()).users();
    }


    public UserServiceImpl(Keycloak keycloak, KeycloakProps props) {
        this.keycloak = keycloak;
        this.props = props;
    }


    @Override
    public List<UserRepresentation> listUsers(int first, int max) {
        List<UserRepresentation> users = keycloak.realm(props.getRealm()).users().list();
        List<UserRepresentation> filteredUsers = usersResourceInstance().list();
        return users;
    }
}
