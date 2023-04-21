package com.poc.app.models;

import lombok.Data;

import java.io.Serializable;
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public String toString() {
        return "User{username = '" + username + "', password='" + password +"'}";
    }
}
