package com.lap.webadmin.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BookDto {
	
	private Long bookId;

	@NotEmpty(message = "Name should not be empty")
	private String name;

	private Long categoryId;

	@NotNull(message = "Author should not be empty")
	private Long authorId;

	@Lob
	private byte[] imageCover;

	@Pattern(regexp="\\d{4}",message="Year format YYYY is wrong")  
	private String publishingYear;

	private MultipartFile fileImport;
}
