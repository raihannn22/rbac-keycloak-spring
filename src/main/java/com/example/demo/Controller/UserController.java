package com.example.demo.Controller;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.service.UserService;
import jakarta.ws.rs.core.Response;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/findByEmail")
    public ResponseEntity<List<UserRepresentation>> getUserByEmail(@RequestBody UserRequestDto email) {
        List<UserRepresentation> response = userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserRequestDto user) {
        String response = userService.createUser(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/setPassword")
    public ResponseEntity<String> setPassword(@RequestBody UserRequestDto user) {
        String response = userService.setPassword(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(response);
    }

}
