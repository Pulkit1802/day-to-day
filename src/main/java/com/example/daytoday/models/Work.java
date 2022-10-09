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
    private Double price;
    private Double rating;
    private Boolean isAvailable;
    private Boolean completed;

    public Work(String clientNumber, String workerNumber, String workDescription, String feedback, Double price, Double rating) {
        this.clientNumber = clientNumber;
        this.workerNumber = workerNumber;
        this.workDescription = workDescription;
        this.feedback = feedback;
        this.workRequestPool = new ArrayList<>();
        this.price = price;
        this.rating = rating;
        this.isAvailable = true;
        this.completed = false;
    }

    public void appendWorkRequestPool(String val){
        this.workRequestPool.add(val);
    }

}
