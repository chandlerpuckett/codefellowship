package com.chandlerpuckett.CodeFellowship.controllers;

import com.chandlerpuckett.CodeFellowship.models.user.ApplicationUser;
import com.chandlerpuckett.CodeFellowship.models.user.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;

@Controller
public class ApplicationUserController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    ----- Login Routes -----
    @GetMapping("/login")
    public String showLoginPage(Principal principal, Model m){
        m.addAttribute("user",principal);
        System.out.println("---- navigating to login page -----");
        return ("login");
    }

    @PostMapping("/user/{username}")
    public String showUserDetails(@PathVariable String username, Model m){
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        System.out.println("++++ found user ++++ " + username);
        m.addAttribute("user",user);

        if(user == null){
            m.addAttribute("user not found", true);
        }
        return ("user");
    }

//    ----- Sign Up Routes -----
    @GetMapping("/signup")
    public String signUpNewUser(Principal principal, Model m){
        return ("signup");
    }

    @DateTimeFormat(pattern="dd-MM-yyyy")
    @PostMapping("/signup")
    public RedirectView makeNewUser(HttpServletRequest request,
                                    String username,
                                    String password,
                                    String firstName,
                                    String lastName,
                                    Date dateOfBirth,
                                    String bio) throws Exception {
        System.out.println("----- adding a user to the DB -----");

        String passwordEncode = passwordEncoder.encode(password);
        ApplicationUser newUser = new ApplicationUser(username,
                passwordEncode,
                firstName,
                lastName,
                dateOfBirth,
                bio);
        applicationUserRepository.save(newUser);

        request.login(username,password);
        return new RedirectView("/users");
    }
}
