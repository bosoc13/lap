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

import com.lap.common.entity.Category;
import com.lap.webadmin.dto.CategoryDto;
import com.lap.webadmin.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class CategoryController {

	@Autowired
    private CategoryService categoryService;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/category")
    public String showAllCategory(Model model){
        // create model object to store form data
		List<Category> categorys = categoryService.listAll();
        model.addAttribute("categorys", categorys);
        model.addAttribute("mode", "list");
        return "category";
    }
	@GetMapping("/category/edit/{id}")
    public ModelAndView showEditCategory(@PathVariable(name = "id") Long id){
        // create model object to store form data
        ModelAndView mav = new ModelAndView("category");
        Category category = categoryService.findById(id);
        mav.addObject("category", category);
        mav.addObject("mode", "edit");
        return mav;
    }
	
    @RequestMapping(value = "/category/save", method = RequestMethod.POST)
    public String saveCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult result, Model model) {
		if(categoryService.isExist(categoryDto.getCategoryName())) {
			result.rejectValue("categoryName", null, "The category already exists");
		} 
        if(result.hasErrors()){
            model.addAttribute("category", categoryDto);
            model.addAttribute("mode", "edit");
            return "category";
        }
    	categoryService.saveCategory(modelMapper.map(categoryDto, Category.class));
        return "redirect:/category";
    }
    
    @RequestMapping("/category/add")
    public String showNewCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("mode", "add");
        return "category";
    }
    
	@PostMapping("/category/add")
    public String addNewCategory(@Valid @ModelAttribute("category") CategoryDto categoryDto,
                               BindingResult result,
                               Model model){

		if(categoryService.isExist(categoryDto.getCategoryName())) {
			result.rejectValue("categoryName", null, "The category already exists");
		} 

        if(result.hasErrors()){
        	model.addAttribute("category", categoryDto);
        	model.addAttribute("mode", "add");
            return "category";
        }
        categoryService.saveCategory(modelMapper.map(categoryDto, Category.class));
        return "redirect:/category?success";
    }
	
	@GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id){
        // create model object to store form data
        categoryService.deleteById(id);
        return "redirect:/category?success";
    }
}
