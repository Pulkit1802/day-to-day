package com.example.daytoday.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Work {
    @Id
    private String id;
    private String clientNumber;
    private String workerNumber;
    private String workDescription;
    private String feedback;
    private List<String> workRequestPool;
    private List<String > requiredSkills;
    private Double price;
    private Double rating;
    private Double distance;

    private List<Double> coords;
    private Boolean isAvailable;
    private Boolean completed;

    public Work(String clientNumber, String workerNumber, String workDescription, String feedback, Double price, Double rating) {
        this.clientNumber = clientNumber;
        this.workerNumber = workerNumber;
        this.workDescription = workDescription;
        this.feedback = feedback;
        this.workRequestPool = new ArrayList<>();
        this.requiredSkills = new ArrayList<>();
        this.price = price;
        this.rating = rating;
        this.distance = 0.0;
        this.coords = new ArrayList<>();
        this.isAvailable = true;
        this.completed = false;
    }

    public void appendWorkRequestPool(String val){
        this.workRequestPool.add(val);
    }

}
