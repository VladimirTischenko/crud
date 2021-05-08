package ru.sberbank.crud.dao;

import org.springframework.stereotype.Component;
import ru.sberbank.crud.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/crud";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static int COUNT = 2;
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String query = "select * from users";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));

                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    public User getById(int id) {
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(User user) {
        try {
            Statement statement = connection.createStatement();
            String query = "insert into users values(" + ++COUNT + ", '" + user.getName() + "')";
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        user.setId(++COUNT);
//        users.add(user);
    }

    public void update(User updatedUser) {
        User existedUser = getById(updatedUser.getId());
        existedUser.setName(updatedUser.getName());
    }

    public void delete(Integer id) {
//        users.removeIf(u -> u.getId().equals(id));
    }
}
