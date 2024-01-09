//package com.lap.webuser.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.lap.webuser.common.service.UsersService;
//
//@Controller
//public class HomeController {
//    
//	@Autowired
//	private UsersService usersService;
//    @GetMapping("/")
//    public String homepage() {
//    	usersService.findByUserName("");
//        return "index";  // Trả về trang index.html
//    }
//    
//    // Có thể mapping thêm các endpoint khác nữa...
//}