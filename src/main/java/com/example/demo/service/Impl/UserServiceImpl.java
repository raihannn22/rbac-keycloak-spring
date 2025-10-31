package com.example.demo.service.Impl;

import com.example.demo.config.KeycloakProps;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.service.UserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
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

    @Override
    public List<UserRepresentation> getUserByEmail(UserRequestDto userRequestDto) {
        List<UserRepresentation> user = keycloak.realm (props.getRealm()).users().searchByEmail(userRequestDto.getEmail(), false );
        return user;
    }

    @Override
    public String createUser(UserRequestDto userRequestDto) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEnabled(true);
        try {
            usersResourceInstance().create(user);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "user dengan username " + userRequestDto.getUsername() + " berhasil dibuat!";
    }

    @Override
    public String setPassword(String username, String password) {
        UserRepresentation user = keycloak.realm(props.getRealm()).users().searchByUsername(username, true).getFirst();
        if (user == null) {
            return "user dengan username " + username + " tidak ditemukan!";
        }
        UserResource userResource =  usersResourceInstance().get(user.getId()   );
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        userResource.resetPassword(credential);
        return "user dengan username " + username + " berhasil set passwordnya!";
    }


}
