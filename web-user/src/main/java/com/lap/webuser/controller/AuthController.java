package com.lap.webuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lap.common.entity.Users;
import com.lap.webuser.server.UsersService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UsersService usersService;
	
	
	@GetMapping("/login")
    public String login(){
        return "login";
    }
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        Users user = new Users();
        model.addAttribute("user", user);
        return "register";
    }
	
	@PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") Users user,
                               BindingResult result,
                               Model model){
        Users existingUser = usersService.findByUserName(user.getUserName());

        if(existingUser != null && existingUser.getUserName() != null && !existingUser.getUserName().isEmpty()){
            result.rejectValue("user name", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "/register";
        }
        user.setRole("USER");
        usersService.save(user);
        return "redirect:/register?success";
    }
	
    @GetMapping("/users")
    public String users(Model model){
        List<Users> users = usersService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
