package com.example.simpleNoteApp.model;

import com.example.simpleNoteApp.constants.Constants;

import javax.validation.constraints.NotBlank;

public class UserModel {
    private String id;
    @NotBlank(message = Constants.blankUserNameModelMessage)
    private String username;

    public UserModel(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}