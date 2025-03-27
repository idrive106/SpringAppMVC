package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PeopleController {

    private final UserDao userDao;

    @Autowired
    public PeopleController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("people", userDao.viewUsers());
        return "/users/index";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/users/add";
    }

    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("user") User user, Model model) {
        try {
            userDao.saveUser(user);
            return "redirect:/";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Ошибка при добавлении пользователя");
            return "/users/add";
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userDao.deleteUser(id);
        return "redirect:/";
    }


    @GetMapping("/users/edit")
    public String showEditUserForm(@RequestParam Long id, Model model) {
        User user = userDao.findUser(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "/users/edit";
        }
        return "redirect:/";
    }


    @PostMapping("/users/edit")
    public String editUser(@ModelAttribute User user) {
        try {
            userDao.editUser(user);
        } catch (RuntimeException e) {

            return "/users/edit";
        }
        return "redirect:/";
    }


}
