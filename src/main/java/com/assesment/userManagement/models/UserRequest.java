package com.assesment.userManagement.models;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String emailId;
    private String password;
    private Role role;
}
