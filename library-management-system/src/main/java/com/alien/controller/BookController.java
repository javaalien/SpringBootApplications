package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.entity.Book;
import com.alien.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id) {
		return bookService.findById(id);
	}

	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return bookService.save(book);
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		// Additional logic to ensure you're updating the correct book
		return bookService.save(book);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteById(id);
	}

	@PostMapping("/{bookId}/borrow/{userId}")
	public ResponseEntity<Book> borrowbook(@PathVariable Long bookId, @PathVariable Long userId) {
		Book borrowBook = bookService.borrowBook(bookId, userId);
		if (borrowBook != null) {
			return ResponseEntity.ok(borrowBook);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/{bookId}/return")
	public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
		Book returnBook = bookService.returnBook(bookId);
		if (returnBook != null) { 
			return ResponseEntity.ok(returnBook);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
}
