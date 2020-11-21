package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.dao.Dao;
import ru.gigorv.web.models.User;
import ru.gigorv.web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        userService.getAll().forEach(System.out::println);
        return "pages/users";
    }

    @GetMapping("/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.get(id));
        return "pages/user";
    }

    @GetMapping("/new")
    public String addUser(Model model) { //через модель передаем в форму объект
        model.addAttribute("user", new User()); //передадим объект c пустым конструктором для Thymeleaf -шаблона
        return "pages/new"; //отдаем шаблон, где будет создаваться новый пользователь
    }

    @PostMapping() //по url /users мы должны попасть в этот метод
    //здесь будет новый пользователь из формы "new"
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "users/new";
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit") //метод возвращает страницу для редактирования пользователя
    public String edit(Model model, @PathVariable("id") Long id) {
        //в модель помещаем текущего пользователя с данным id, его будем отображать на форме редактирования
        model.addAttribute("user", userService.get(id));
        //к этой модели мы будем иметь доступ в нашем представлении
        return "pages/edit";
    }

    @PatchMapping("/{id}")
    //принимаем объект из формы редактирования
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()) return "users/edit";
    //ищем пользователя с указанным id и обновить его поля значениями, которые пришли из формы
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/users";
    }
}
