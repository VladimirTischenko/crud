package ru.sberbank.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sberbank.crud.dao.UserDAO;

@Controller
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserDAO dao;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", dao.getUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", dao.getUser(id));
        return "user";
    }
}
