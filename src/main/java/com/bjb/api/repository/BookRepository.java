package com.bjb.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bjb.api.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	public Book findByName(String name);
	
	
}
