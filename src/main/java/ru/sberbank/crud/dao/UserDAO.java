package ru.sberbank.crud.dao;

import org.springframework.stereotype.Component;
import ru.sberbank.crud.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static int COUNT;
    private final List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(++COUNT, "Andrew"));
        users.add(new User(++COUNT, "Bob"));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(++COUNT);
        users.add(user);
    }
}
