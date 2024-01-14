package com.lap.webadmin.dto;

import org.apache.logging.log4j.util.Strings;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthorDto {
	private Long authorId;
	
	@NotEmpty(message = "Name should not be empty")
	private String name;
	
	private String pseudonym;
	
	@Pattern(regexp="^(?:[0-9]{4}|)$",message="YearBirth format YYYY is wrong")
	private String yearBirth;
	
	private String address;
	
	@Override
	public String toString() {
		String s = name;
		if(Strings.isNotBlank(pseudonym)) {
			s += "("+pseudonym+")";
		}
		s += "-"+yearBirth;
		if(Strings.isNotBlank(address)) {
			s += "-"+address;
		}
		return s;
		
	}
}
