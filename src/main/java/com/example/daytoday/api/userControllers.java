package com.example.daytoday.api;

import com.example.daytoday.models.User;
import com.example.daytoday.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class userControllers {
    public final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{number}")
    public Optional<User> getSingleUser(@PathVariable("number") String number) {
        System.out.println(number);
        return userService.getOneUser(number);
    }

    @PostMapping
    public final User createNewUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PatchMapping("/{number}")
    public Optional<User> updateUser(@PathVariable("number") String number, @RequestBody Map<Object, Object> fields) {
        return userService.updateUser(number, fields);
    }

    @DeleteMapping("/{number}")
    public final int DeleteUser(@PathVariable("number") String number) {
        return userService.removeUser(number);
    }

}
