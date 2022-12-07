package com.ti.ty.controller;

import com.ti.ty.model.User;
import com.ti.ty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/account/login")
    public String login() {

        return "/account/login";
    }

    @GetMapping("/account/register")
    public String register() {

        return "/account/register";
    }



    @PostMapping("/account/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/";
    }
}
