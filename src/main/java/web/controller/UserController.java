package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String welcome() {
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/new_user")
    public String newUser(Model model) {
        model.addAttribute("userForm", new User());
        return "new_user";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("userForm") User newUser) {
        userService.add(newUser);
        return "redirect:/users";
    }

    @GetMapping("users/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("editUser", userService.findUserById(id));
        return "edit_user";
    }

    @PatchMapping("users/{id}")
    public String saveUser(@PathVariable("id") long id, @ModelAttribute("editUser") User editUser) {
        userService.update(id, editUser);
        return "redirect:/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
