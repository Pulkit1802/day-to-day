package com.example.daytoday.repos;

import com.example.daytoday.models.Work;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkMongoRepo extends MongoRepository<Work, String> {

}
