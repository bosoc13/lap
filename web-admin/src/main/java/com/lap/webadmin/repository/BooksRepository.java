package com.lap.webadmin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lap.common.entity.Books;

import jakarta.transaction.Transactional;

@Transactional
public interface BooksRepository extends JpaRepository<Books, Long> {

	List<Books> findByNameAndCategoryIdAndAuthorIdAndPublishingYear(String name, Long categoryId, Long authorId, String publishingYear);
}
