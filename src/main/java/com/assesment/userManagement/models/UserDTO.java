package com.assesment.userManagement.models;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String email;
    private Role role;
    private Status status;
}
