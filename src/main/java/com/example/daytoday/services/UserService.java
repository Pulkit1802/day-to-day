package com.example.daytoday.services;

import com.example.daytoday.dao.UserDao;
import com.example.daytoday.dao.WorkDao;
import com.example.daytoday.models.User;
import com.example.daytoday.models.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    private final WorkDao workDao;

    @Autowired
    public UserService(@Qualifier("userRepo") UserDao userDao, @Qualifier("workRepo") WorkDao workDao) {
        this.userDao = userDao;
        this.workDao = workDao;
    }

    public final User insertUser(User newUser) {
        return userDao.addUser(newUser);
    }

    public final List<User> getAllUsers() {
        return userDao.fetchAllUsers();
    }

    public final Optional<User> getOneUser(String phoneNumber) {
        return userDao.getSingleUser(phoneNumber);
    }

    public final int removeUser(String phoneNumber) {
        return userDao.deleteUser(phoneNumber);
    }

    public final Optional<User> updateUser (String phoneNumber, Map<Object, Object> fields) {
        return userDao.updateUser(phoneNumber, fields);
    }

    public final List<Work> getUserWorks(String number) {
        return workDao.getWorkByUserNumber(number);
    }

}
