package com.lap.webadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lap.common.entity.Author;
import com.lap.webadmin.dto.AuthorDto;
import com.lap.webadmin.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
    private AuthorRepository authorRepository;
	
	public void saveBook(Author author) {
		authorRepository.save(author);
	}
	
	public List<Author> listAll() {
		return authorRepository.findAll();
	}

	public Author findById(Long id) {
		return authorRepository.findById(id).get();
	}

	public boolean isExist(AuthorDto authorDto) {
		return authorRepository.existAuthor(authorDto.getName(),
				authorDto.getPseudonym(),
				authorDto.getYearBirth(),
				authorDto.getAddress()) > 0;
	}

	public void saveAuthor(Author author) {
		authorRepository.save(author);
		
	}

	public void deleteById(Long id) {
		authorRepository.deleteById(id);
		
	}
}
