package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.models.User;
import ru.gigorv.web.services.UsersService;
import ru.gigorv.web.services.RolesService;

import java.security.Principal;
import java.util.List;

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

    @PostMapping("/admin/save")
    @ResponseBody
    public List<User> save(@RequestBody User newUser) {
        System.out.println(newUser);
        System.out.println(usersService.save(newUser));
        return usersService.getAllUsers();
    }

    @GetMapping("/admin/findOne/{id}")
    @ResponseBody
    public User findOne(@PathVariable Long id) {
        System.out.println(usersService.findUserById(id));
        return usersService.findUserById(id);
    }

    @GetMapping("/admin/delete/{id}")
    @ResponseBody
    public List<User> delete(@PathVariable("id") Long id) {
        System.out.println(usersService.findUserById(id));
        System.out.println(usersService.deleteUserById(id));
        return usersService.getAllUsers();
    }

    @PostMapping("/admin/update")
    @ResponseBody
    public List<User> update(@RequestBody User editUser) {
        usersService.updateUser(editUser);
        return usersService.getAllUsers();
    }

    @GetMapping("/user")
    public String getUserInfo(Model model, Principal principal) {
        model.addAttribute("user", usersService.findUserByEmail(principal.getName()));
        System.out.println(principal.getName());
        return "user";
    }

//    @GetMapping("/admin")
//    @ResponseBody
//    public ResponseEntity<List<User>> getUsersPage() {
//        List<User> users = usersService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }

//    @GetMapping("/admin")
//    @ResponseBody
//    public List<User> getUsersPage() {
//        return usersService.getAllUsers();
//    }

//    @PostMapping("/admin/save")
//    @ResponseBody
//    public ResponseEntity<User> save(@RequestBody User newUser) {
//        System.out.println(usersService.save(newUser));
//        return new ResponseEntity<>(newUser, HttpStatus.OK);
//    }

//    @DeleteMapping("/admin/delete")
//    @ResponseBody
//    public ResponseEntity<?> delete(@RequestParam("id") Long id) {
//        System.out.println(usersService.findUserById(id));
//        System.out.println(usersService.deleteUserById(id));
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }

//    @PostMapping("/admin/update")
//    @ResponseBody
//    public ResponseEntity<User> update(@RequestBody User user) {
//        usersService.updateUser(user);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
//


}
