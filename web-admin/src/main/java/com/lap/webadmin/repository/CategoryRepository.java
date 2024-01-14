package com.lap.webadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lap.common.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value = "Select count(1) from Category where upper(Category_name) = upper(?)",nativeQuery = true)
	Integer existCategoryName(String categoryName);
}
