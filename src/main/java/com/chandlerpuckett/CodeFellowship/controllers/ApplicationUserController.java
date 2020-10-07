package com.chandlerpuckett.CodeFellowship.controllers;

import com.chandlerpuckett.CodeFellowship.models.user.ApplicationUser;
import com.chandlerpuckett.CodeFellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @GetMapping("/user/{username}")
//    public String showUserDetails(@PathVariable String username){
//        ApplicationUser user = applicationUserRepository.findByUserName(username);
//
//        if(user == null){
////            throw new Exception("user not found");
//        }
//        return "user";
//    }

    @GetMapping("/signup")
    public String signUpNewUser(){
        System.out.println("entered sign up route");

        return ("signup");
    }

    @DateTimeFormat(pattern="MM-dd-yyyy")
    @PostMapping("/newuser")
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

        return new RedirectView("login");
    }
}
