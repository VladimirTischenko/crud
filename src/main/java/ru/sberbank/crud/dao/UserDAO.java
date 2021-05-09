package ru.sberbank.crud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sberbank.crud.models.User;

import java.util.List;

@Component
public class UserDAO {
    private static int COUNT = 2;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users", new BeanPropertyRowMapper<>(User.class));
    }

    public User getById(int id) {
        return jdbcTemplate
                .query("select * from users where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("insert into users values(?, ?)", ++COUNT, user.getName());
    }

    public void update(int id, User user) {
        jdbcTemplate.update("update users set name=? where id=?", user.getName(), id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update("delete from users where id=?", id);
    }
}
