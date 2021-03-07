package ru.sberbank.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.crud.dao.UserDAO;
import ru.sberbank.crud.models.User;

@Controller
@RequestMapping("/users")
public class userController {
    @Autowired
    private UserDAO dao;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", dao.getUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", dao.getById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        dao.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
        User user = dao.getById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping
    public String update(@ModelAttribute("user") User user) {
        dao.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        dao.delete(id);
        return "redirect:/users";
    }
}
