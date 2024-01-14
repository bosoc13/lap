package com.lap.webadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lap.common.entity.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {
	/*
	 * List<Author> findByNameAndPseudonymAndYearBirthAndAddress(String name, String
	 * pseudonym, String yearBirth, String address);
	 */

	@Query(value = "select count(1) from Author "
			+ " where upper(name) = upper(:name) "
			+ " And (:pseudonym is null or upper(pseudonym) = upper(:pseudonym)) "
			+ " And (:yearOfBirth is null or yearBirth = :yearOfBirth) "
			+ " And (:address is null or upper(address) = upper(:address)) ")
	Integer existAuthor(@Param("name") String name, 
			@Param("pseudonym") String pseudonym, 
			@Param("yearOfBirth") String yearOfBirth, 
			@Param("address") String address);
}
