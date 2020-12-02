package ru.gigorv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.gigorv.web.models.User;
import ru.gigorv.web.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
//@RequestMapping("/")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUserInfo(Model model, Principal principal) {
        String userName = principal.getName();
//        User loginUser = (User)((Authentication)principal).getPrincipal();
//        String userInfo = WebUtils.toString(loginUser); // возвращает роли склеенные через запятую, в данном случае - ROLE_ADMIN
//        System.out.println(userInfo);
        model.addAttribute("user", userService.getUserByName(userName));
        return "pages/user";
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
//        Authentication a = SecurityContextHolder.getContext().getAuthentication();
//        userService.getAll().forEach(System.out::println);
        return "pages/users";
    }

    @GetMapping("/admin/users/{id}")
    public String findUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.get(id));

        return "pages/userForAdmin";
    }

    @GetMapping("/admin/users/new")
    public String addUser(Model model) { //через модель передаем в форму объект
        model.addAttribute("user", new User()); //передадим объект c пустым конструктором для Thymeleaf -шаблона
        return "pages/new"; //отдаем шаблон, где будет создаваться новый пользователь
    }

    @PostMapping("/admin/users") //по url /users мы должны попасть в этот метод
    //здесь будет новый пользователь из формы "new"
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/users/new";
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit") //метод возвращает страницу для редактирования пользователя
    public String edit(Model model, @PathVariable("id") Long id) {
        //в модель помещаем текущего пользователя с данным id, его будем отображать на форме редактирования
        model.addAttribute("user", userService.get(id));
        //к этой модели мы будем иметь доступ в нашем представлении
        return "pages/edit";
    }

    @PatchMapping("/admin/users/{id}")
    //принимаем объект из формы редактирования
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) return "admin/users/edit";
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
