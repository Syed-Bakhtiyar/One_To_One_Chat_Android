package com.example.bakhtiyar.simplechatapplication;

/**
 * Created by Bakhtiyar on 12/19/2016.
 */
public class UserInfo {

    public String name;

    public String email;

    public String uuid;

    public UserInfo(String name, String email, String uuid) {
        this.name = name;
        this.email = email;
        this.uuid = uuid;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUuid() {
        return uuid;
    }
}
