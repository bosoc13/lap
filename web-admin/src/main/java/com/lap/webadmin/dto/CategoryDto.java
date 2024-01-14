package com.lap.webadmin.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {

	private Long categoryId;

	@NotEmpty(message = "Category Name should not be empty")
	private String categoryName;
}
