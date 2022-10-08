package com.example.daytoday.repos;

import com.example.daytoday.models.Work;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface WorkMongoRepo extends MongoRepository<Work, String> {
    List<Work> findWorksByClientNumber(String number);
    List<Work> findWorksByWorkerNumber(String number);
}
