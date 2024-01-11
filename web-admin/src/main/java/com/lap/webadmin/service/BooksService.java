package com.lap.webadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lap.common.entity.Books;
import com.lap.webadmin.repository.BooksRepository;

@Service
public class BooksService {
	@Autowired
    private BooksRepository booksRepository;
	
	public void saveBook(Books book) {
		booksRepository.save(book);
	}
}
