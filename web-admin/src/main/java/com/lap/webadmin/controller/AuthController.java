package com.lap.webadmin.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lap.common.entity.Books;
import com.lap.common.entity.Category;
import com.lap.common.entity.Users;
import com.lap.webadmin.dto.UserDto;
import com.lap.webadmin.service.CategoryService;
import com.lap.webadmin.service.UsersService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UsersService usersService;
	@Autowired
    private CategoryService categoryService;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/login")
    public String login(){
        return "login";
    }
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
		UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
	
	@PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        Users existingUser = usersService.findByUserName(user.getUserName());

        if(existingUser != null && existingUser.getUserName() != null && !existingUser.getUserName().isEmpty()){
            result.rejectValue("userName", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "/register";
        }
        Users entity = modelMapper.map(user, Users.class);
        entity.setRole("ADMIN");
        usersService.save(entity);
        return "redirect:/register?success";
    }
	
    @GetMapping("/home")
    public String users(Model model){
		Books books = new Books();
        model.addAttribute("books", books);
        
        List<Category> categorys = categoryService.listAll();
        model.addAttribute("categorys", categorys);
        return "home";
    }
}
