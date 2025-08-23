package com.example.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.example.blog.entities.Category;
import com.example.blog.payloads.CategoryDto;

public interface CategoryRepo extends JpaRepository<Category , Integer>{
	
	
	
}
