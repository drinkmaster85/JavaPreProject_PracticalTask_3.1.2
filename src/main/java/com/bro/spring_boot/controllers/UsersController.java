package com.bro.spring_boot.controllers;

import com.bro.spring_boot.entities.User;
import com.bro.spring_boot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;


@Controller
//@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String getUserInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("user", userService.getUserByName(auth.getName()));

        return "user/info";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("currentUser", userService.getUserByName(auth.getName()));

        return "admin/all";
    }

    @GetMapping("/admin/add")
    public String newUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("roles", userService.getAllRoles());
        model.addAttribute("currentUser", userService.getUserByName(auth.getName()));

        return "admin/add";
    }

    @PostMapping("/admin/add")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", userService.getAllRoles());

        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String edit(@ModelAttribute("user") User user) {
        userService.editUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);

        return "redirect:/admin";
    }
}