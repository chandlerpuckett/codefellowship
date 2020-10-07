package com.chandlerpuckett.CodeFellowship.controllers;

import com.chandlerpuckett.CodeFellowship.models.user.ApplicationUser;
import com.chandlerpuckett.CodeFellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginPage(Principal principal, Model m){
        m.addAttribute("principal", principal);
        System.out.println("---- navigating to login page -----");

        return ("login");
    }

    @PostMapping("/login")
    public String showUserDetails(@PathVariable String username, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        System.out.println("++++ found user ++++ " + username);
        m.addAttribute("user",user);

        if(user == null){
            m.addAttribute("user not found", true);
        }
        return "user";
    }

    @GetMapping("/signup")
    public String signUpNewUser(){
        System.out.println("entered sign up route");

        return ("signup");
    }

    @DateTimeFormat(pattern="MM-dd-yyyy")
    @PostMapping("/signup")
    public RedirectView makeNewUser(String username,
                                    String password,
                                    String firstName,
                                    String lastName,
                                    Date dateOfBirth,
                                    String bio)
    {
        System.out.println("----- adding a user to the DB -----");
        password = passwordEncoder.encode(password);

        ApplicationUser newUser = new ApplicationUser(username,
                password,
                firstName,
                lastName,
                dateOfBirth,
                bio);
        applicationUserRepository.save(newUser);

        return new RedirectView("/login");
    }
}
