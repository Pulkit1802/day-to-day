package com.example.daytoday.dao;

import com.example.daytoday.models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    User insertUser(UUID id, User user);

    default User addUser(User user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    List<User> fetchAllUsers();

    int deleteUser(String phoneNumber);

    Optional<User> getSingleUser(String phoneNumber);

    Optional<User> updateUser(String phoneNumber, Map<Object, Object> fields);

}
