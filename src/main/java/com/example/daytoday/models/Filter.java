package com.example.daytoday.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Filter {
    private Double minPrice;
    private Double maxPrice;
    private List<String> requiredSkills;

    public Filter(){
        this.minPrice = 0.0;
        this.maxPrice = 9999999.0;
        this.requiredSkills = new ArrayList<>();
    }

}
