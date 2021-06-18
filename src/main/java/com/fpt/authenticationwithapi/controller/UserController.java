package com.fpt.authenticationwithapi.controller;

import com.fpt.authenticationwithapi.service.RoleService;
import com.fpt.authenticationwithapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String view() {
        return "/index";
    }

    @GetMapping("/admin")
    public String showUsers(Model model) {
        return "admin";
    }

    @GetMapping("/admin/register")
    public String getNewUserForm(Model model) {
        return "new";
    }

    @GetMapping("/admin/edit")
    public String getEditUser(@RequestParam(value = "id") Integer id, Model model) {
        model.addAttribute("user", userService.get(id));

        return "edit";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/main")
    public String getMainPage(Model model) {
        return "main";
    }
}