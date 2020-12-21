package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.models.User;
import ru.gigorv.web.services.UsersService;
import java.security.Principal;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/admin")
    public String getUsersPage(Model model, Principal principal) {
        model.addAttribute("auth", usersService.findUserByEmail(principal.getName()));
        model.addAttribute("users", usersService.getAllUsers());
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

    @PostMapping("/admin/delete")
//    public String delete(@PathVariable("id") Long id) {
    public String delete(@RequestParam(name = "id") Long id) {
        System.out.println(usersService.findUserById(id));
        System.out.println(usersService.deleteUserById(id));
        return "redirect:/admin";
    }

//    @PostMapping("/admin/delete")
//    public String delete(User delUser) {
//        usersService.deleteUser(delUser);
//        return "redirect:/admin";
//    }



    @RequestMapping(value = "/admin/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(User user) {
        usersService.save(user);
        return "redirect:/admin";
    }



//    @GetMapping("/findOne/{id}")
//    public String getUser(@PathVariable("id") Long id, Model model) {
//        System.out.println(usersService.findUserById(id));
//        model.addAttribute("user", usersService.findUserById(id));
//        return "admin";
//    }

//    @PostMapping("/admin/save")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
//        if (bindingResult.hasErrors()) return "users/edit";
//        usersService.save(user);
//        return "redirect:/users";
//    }

//    @GetMapping("/users/{id}")
//    public String findUserById(@PathVariable("id") Long id, Model model) {
//        System.out.println(usersService.findUserById(id));
//        model.addAttribute("user", usersService.findUserById(id));
//        return "userForAdmin";
//    }

//    @GetMapping("/user")
//    public String getUserInfo(Model model, Principal principal) {
//        model.addAttribute("user", usersService.findUserByEmail(principal.getName()));
//        System.out.println(principal.getName());
//        return "user";
//    }

//

//    @GetMapping("/users/new")
//    public String addUser(Model model) {
//        model.addAttribute("user", new User());
//        return "new";
//    }

//    @GetMapping("/users/{id}/edit")
//    public String edit(@PathVariable("id") Long id, Model model) {
//        System.out.println(usersService.findUserById(id));
//        model.addAttribute("user", usersService.findUserById(id));
//        return "edit";
//    }
}
