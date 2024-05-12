package com.htdweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class RegisterDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String telephone;
    private String email;
    private String address;
    private String password;
    private String role;


}
