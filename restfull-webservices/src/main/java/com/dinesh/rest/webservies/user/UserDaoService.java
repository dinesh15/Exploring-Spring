package com.dinesh.rest.webservies.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private  static int count = 3;

    static {
        users.add(new User(1,"Dinesh Raj", LocalDate.of(1999,6,15)));
        users.add(new User(2,"Sahithi", LocalDate.of(1999,1,22)));
        users.add(new User(3,"Siri", LocalDate.of(1999,1,22)));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void saveUser(User user) {
        users.add(user);
    }

    public User getUser(int id) {
        Predicate<User> predicate = u -> u.getId() == id;
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User add(User user) {
        user.setId(++count);
        users.add(user);
        return user;
    }

    public void deleteUserById(int id) {
        Predicate<User> predicate = u -> u.getId() == id;
        users.removeIf(predicate);
    }
}
