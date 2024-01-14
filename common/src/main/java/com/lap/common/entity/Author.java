package com.lap.common.entity;

import org.apache.logging.log4j.util.Strings;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;
	
	private String name;
	
	private String pseudonym;
	
	@Column(name = "year_birth")
	private String yearBirth;
	
	private String address;
	
	@Override
	public String toString() {
		String s = name;
		if (Strings.isNotBlank(pseudonym)) {
			s += "(" + pseudonym + ")";
		}
		s += "-" + yearBirth;
		if (Strings.isNotBlank(address)) {
			s += "-" + address;
		}
		return s;

	}
}
