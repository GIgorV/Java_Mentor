package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.models.User;
import ru.gigorv.web.services.UsersService;
import ru.gigorv.web.services.RolesService;
import java.security.Principal;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @GetMapping("/admin")
    public String getUsersPage(Model model, Principal principal) {
        model.addAttribute("auth", usersService.findUserByEmail(principal.getName()));
        model.addAttribute("users", usersService.getAllUsers());
        model.addAttribute("roles", rolesService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @GetMapping("/admin/findOne")
    @ResponseBody
    public User findOne(Long id) {
        System.out.println(usersService.findUserById(id));
        return usersService.findUserById(id);
    }

    @PostMapping("/admin/save")
    public String save(@ModelAttribute("newUser") User newUser) {
        System.out.println(usersService.save(newUser));
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println(usersService.findUserById(id));
        System.out.println(usersService.deleteUserById(id));
        return "redirect:/admin";
    }

    @PostMapping("/admin/update")
    public String update(@ModelAttribute("user") User user) {
        usersService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserInfo(Model model, Principal principal) {
        model.addAttribute("user", usersService.findUserByEmail(principal.getName()));
        System.out.println(principal.getName());
        return "user";
    }
}
