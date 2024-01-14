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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lap.common.entity.Author;
import com.lap.common.entity.Books;
import com.lap.common.entity.Category;
import com.lap.webadmin.dto.BookDto;
import com.lap.webadmin.service.AuthorService;
import com.lap.webadmin.service.BooksService;
import com.lap.webadmin.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class BooksController {

	@Autowired
    private BooksService booksService;
	@Autowired
    private CategoryService categoryService;
	@Autowired
    private AuthorService authorService;
	@Autowired
	private ModelMapper mapperMapper;
	
	@GetMapping("/addBooks")
    public String showAddBooksForm(Model model){
        // create model object to store form data
		BookDto books = new BookDto();
        model.addAttribute("books", books);
        
        List<Category> categorys = categoryService.listAll();
        model.addAttribute("categorys", categorys);
        
        List<Author> authors = authorService.listAll();
        model.addAttribute("authors", authors);
        return "books";
    }

	@PostMapping("/books/add")
    public String registration(@Valid @ModelAttribute("books") BookDto bookDto,
                               BindingResult result,
                               Model model){

		if(booksService.isExist(bookDto)) {
			result.rejectValue("name", null, "The book already exists");
		} 

        if(result.hasErrors()){
            model.addAttribute("books", bookDto);
            List<Category> categorys = categoryService.listAll();
            model.addAttribute("categorys", categorys);
            
            List<Author> authors = authorService.listAll();
            model.addAttribute("authors", authors);
            return "books";
        }
        booksService.saveBook(mapperMapper.map(bookDto, Books.class));
        return "redirect:/addBooks?success";
    }
	
	@PostMapping("/books/import")
    public String importByFile(
    		@RequestParam("fileImport") MultipartFile fileImport,
    		@ModelAttribute("books") BookDto bookDto,
            BindingResult result,
            Model model){

		
		if(fileImport.isEmpty()) {
			result.rejectValue("fileImport", null, "The book already exists");
		} 
        if(result.hasErrors()){
            model.addAttribute("books", bookDto);
            List<Category> categorys = categoryService.listAll();
            model.addAttribute("categorys", categorys);
            
            List<Author> authors = authorService.listAll();
            model.addAttribute("authors", authors);
            return "books";
        }
        booksService.importFile(fileImport);
        return "redirect:/addBooks?success";
    }
}
