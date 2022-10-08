package com.example.daytoday.dao;

import com.example.daytoday.models.User;
import com.example.daytoday.repos.UserMongoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Repository("userRepo")
public class userDataService implements UserDao {

    private final UserMongoRepo userRepo;
    @Override
    public User insertUser(UUID id, User user) {
        user.setId(id);
        userRepo.insert(user);
        return user;
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public int deleteUser(String phoneNumber) {

        final int[] userDeleted = {0};

        userRepo.findUserByPhoneNumber(phoneNumber)
                .ifPresent( u -> {
                    userRepo.deleteById(u.getId());
                    userDeleted[0] = 1;
                });

        return userDeleted[0];
    }

    @Override
    public Optional<User> getSingleUser(String phoneNumber) {
        return userRepo.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<User> updateUser(String phoneNumber, Map<Object, Object> fields) {
        Optional<User> updateUser = userRepo.findUserByPhoneNumber(phoneNumber);
        updateUser.ifPresent(user -> {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, (String) key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, user, value);
            });
            deleteUser(phoneNumber);
            userRepo.insert(user);
        });
        return updateUser;
    }
}
