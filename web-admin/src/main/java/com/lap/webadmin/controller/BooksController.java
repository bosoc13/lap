package com.lap.webadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lap.common.entity.Books;
import com.lap.webadmin.service.BooksService;

@Controller
public class BooksController {

	@Autowired
    private BooksService booksService;
	
	@GetMapping("/addBooks")
    public String showAddBooksForm(Model model){
        // create model object to store form data
		Books books = new Books();
        model.addAttribute("books", books);
        return "add_books";
    }
}
