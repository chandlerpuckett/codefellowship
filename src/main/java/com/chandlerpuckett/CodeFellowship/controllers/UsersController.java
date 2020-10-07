package com.chandlerpuckett.CodeFellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UsersController {

    @GetMapping("/users")
    public String showUserPage(Principal principal, Model m){
        m.addAttribute("user", principal);
        return "users";
    }
}
