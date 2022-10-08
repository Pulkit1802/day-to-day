package com.example.daytoday.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Users {
    private final UUID id;
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final Boolean isWorker;

    public Users(UUID id, String name, String phoneNumber, String email, Boolean isWorker) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isWorker = isWorker;
    }
}
