package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.models.Role;
import ru.gigorv.web.models.User;
import ru.gigorv.web.services.RolesService;
import ru.gigorv.web.services.UsersService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

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
        Set<Role> roles = new HashSet<>(); roles.add(rolesService.getRoleById(2L));
        user.setRoles(roles);
        usersService.save(user);
        return "redirect:/users";
    }

    //далее не работает, не могу разобраться почему,
    //подскажи, как правильно сделать update and delete
    //в предыдущей задаче все норм, а как перешел на Jsp - все
    //есть много запутанных вариантов с аннотациями и Query запросами, но, думаю, все намного проще
    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        System.out.println(usersService.findUserById(id));
        model.addAttribute("user", usersService.findUserById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) return "users/edit";
        usersService.save(user);
        return "redirect:/users";
    }
    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println(usersService.findUserById(id));
        usersService.deleteUserById(id);
        return "redirect:/users";
    }
}
