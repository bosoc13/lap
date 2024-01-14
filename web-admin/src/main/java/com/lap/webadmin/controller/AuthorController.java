package com.lap.webadmin.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lap.common.entity.Author;
import com.lap.webadmin.dto.AuthorDto;
import com.lap.webadmin.service.AuthorService;

import jakarta.validation.Valid;

@Controller
public class AuthorController {

	@Autowired
    private AuthorService authorService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/author")
    public String showAllAuthor(Model model){
        // create model object to store form data
		List<Author> authors = authorService.listAll();
        model.addAttribute("authors", authors);
        model.addAttribute("mode", "list");
        return "author";
    }
	@GetMapping("/author/edit/{id}")
    public ModelAndView showEditAuthor(@PathVariable(name = "id") Long id){
        // create model object to store form data
        ModelAndView mav = new ModelAndView("author");
        Author author = authorService.findById(id);
        mav.addObject("author", author);
        mav.addObject("mode", "edit");
        return mav;
    }
	
    @RequestMapping(value = "/author/save", method = RequestMethod.POST)
    public String saveAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto,
            BindingResult result, Model model) {
		if(authorService.isExist(authorDto)) {
			result.rejectValue("name", null, "The author already exists");
		} 
        if(result.hasErrors()){
            model.addAttribute("author", authorDto);
            model.addAttribute("mode", "edit");
            return "author";
        }
    	authorService.saveAuthor(modelMapper.map(authorDto, Author.class));
        return "redirect:/author";
    }
    
    @RequestMapping("/author/add")
    public String showNewAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        model.addAttribute("mode", "add");
        return "author";
    }
    
	@PostMapping("/author/add")
    public String addNewAuthor(@Valid @ModelAttribute("author") AuthorDto authorDto,
                               BindingResult result,
                               Model model){

		if(authorService.isExist(authorDto)) {
			result.rejectValue("name", null, "The author already exists");
		} 

        if(result.hasErrors()){
        	model.addAttribute("author", authorDto);
        	model.addAttribute("mode", "add");
            return "author";
        }
        authorService.saveAuthor(modelMapper.map(authorDto, Author.class));
        return "redirect:/author";
    }
	
	@GetMapping("/author/delete/{id}")
    public String deleteAuthor(@PathVariable(name = "id") Long id){
        authorService.deleteById(id);
        return "redirect:/author?success";
    }
}
