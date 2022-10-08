package com.example.daytoday.repos;

import com.example.daytoday.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserMongoRepo extends MongoRepository<User, UUID> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
