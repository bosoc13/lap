package com.lap.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lap.common.entity.Category;
import com.lap.webadmin.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;
	
	public void saveCategory(Category category) {
		categoryRepository.save(category);
	}
	public Category findById(Long categoryId) {
		return categoryRepository.findById(categoryId).get();
	}
	public boolean isExist(String categoryName) {
		return categoryRepository.existCategoryName(categoryName) > 0;
	}
	
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}
	
	public void deleteById(Long categoryId) {
		categoryRepository.deleteById(categoryId);;
	}
	
}
