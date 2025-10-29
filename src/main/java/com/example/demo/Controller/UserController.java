package com.example.demo.Controller;

import com.example.demo.service.UserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path = "/list/{first}/{max}")
    public ResponseEntity<List<UserRepresentation>> listUsers(@PathVariable int first, @PathVariable int max) {
        List<UserRepresentation> response = userService.listUsers(first, max);
        return ResponseEntity.ok(response);
    }

}
