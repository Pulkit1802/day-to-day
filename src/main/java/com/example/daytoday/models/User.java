package com.example.daytoday.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class User {
    @Id
    private UUID id;
    private String name;
    private String phoneNumber;
    private String email;
    private Boolean isWorker;
    private List<Double> cords;

    private List<String> skills;

    private List<String> workIds;

    private Double rating;


    public User(UUID id, String name, String phoneNumber, String email, Boolean isWorker, List<Double> cords, List<String> skills, List<String> workIds, Double rating) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isWorker = isWorker;
        this.cords = cords;
        this.skills = skills;
        this.workIds = workIds;
        this.rating = rating;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
