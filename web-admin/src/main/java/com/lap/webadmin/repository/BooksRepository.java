package com.lap.webadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lap.common.entity.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {

}
