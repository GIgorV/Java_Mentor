package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.models.User;
import ru.gigorv.web.services.UsersService;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @GetMapping("/user")
    public String getUserInfo(Model model, Principal principal) {
        model.addAttribute("user", usersService.findUserByName(principal.getName()));
        System.out.println(principal.getName());
        return "user";
    }

    @GetMapping("/users/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model) {
        System.out.println(usersService.findUserById(id));
        model.addAttribute("user", usersService.findUserById(id));
        return "userForAdmin";
    }

    @GetMapping("/users/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping("/users")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/users/new";
        System.out.println(usersService.save(user));
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        System.out.println(usersService.findUserById(id));
        model.addAttribute("user", usersService.findUserById(id));
        return "edit";
    }

    @PostMapping("/users/edit/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) return "users/edit";
        usersService.save(user);
        return "redirect:/users";
    }
    @PostMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println(usersService.findUserById(id));
        System.out.println(usersService.deleteUserById(id));
        return "redirect:/users";
    }
}
