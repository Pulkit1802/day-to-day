package com.example.daytoday.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class User {
    @Id
    private UUID id;
    private String name;
    @Indexed(unique = true)
    private String phoneNumber;
    private String email;
    private Boolean isWorker;


    public User(UUID id, String name, String phoneNumber, String email, Boolean isWorker) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isWorker = isWorker;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
