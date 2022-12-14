package com.example.daytoday.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
    private Double distance;


    public User(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.isWorker = false;
        this.cords = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.workIds = new ArrayList<>();
        this.distance = 0.0;
        this.rating = 0.0;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void addWorkId(String id) {
        this.workIds.add(id);
    }

}
