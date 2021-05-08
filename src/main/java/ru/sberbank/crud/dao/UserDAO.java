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
        User user = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            user = new User();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values(?, ?)");

            preparedStatement.setInt(1, ++COUNT);
            preparedStatement.setString(2, user.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, User updatedUser) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name=? where id=?");

            preparedStatement.setString(1, updatedUser.getName());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
