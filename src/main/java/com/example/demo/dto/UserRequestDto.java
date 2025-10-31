package com.example.demo.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
