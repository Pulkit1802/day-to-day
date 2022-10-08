package com.example.daytoday.dao;

import com.example.daytoday.models.Users;

import java.util.UUID;

public interface UserDao {
    Users insertUser(UUID id, Users user);

    default Users addUser(Users user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

}
