package com.bjb.api.exeption.error;

public class BookNotFoundException extends RuntimeException {
	
	public BookNotFoundException(Long id) {
        super("Book id not found : " + id);
    }
	public BookNotFoundException(String  name) {
        super("Book id not found : " + name);
    }
}
