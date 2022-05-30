package com.example.library.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;

@RestController
public class LibraryRestController {

	@Autowired
	LibraryService libraryService;

	@GetMapping(value = "books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(
			@PathVariable("bookId") int bookId) {
		return new ResponseEntity<>(libraryService.getBook(bookId), HttpStatus.OK);
	}

	@GetMapping(value = "books", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getBooks() {
		Book book1 = new Book();
		book1.setBookId(1);
		book1.setAuthor("A1");
		book1.setTitle("First Book");
		book1.setCategories(Arrays.asList("Fiction"));

		Book book2 = new Book();
		book2.setBookId(2);
		book2.setAuthor("A2");
		book2.setTitle("Second Book");
		book2.setCategories(Arrays.asList("Comedy", "Drama"));

		return new ResponseEntity<>(libraryService.getBooks(), HttpStatus.OK);
	}

	@PostMapping(value = "books", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> addBook(
			@RequestBody Book book) {
		return new ResponseEntity<>(libraryService.addBook(book), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> deleteBook(
			@PathVariable("bookId") int bookId) {
		return new ResponseEntity<>(libraryService.deleteBook(bookId), HttpStatus.CREATED);
	}
	
	@PostMapping(value = "members/{memberId}/books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> loanBook(
			@PathVariable("memberId") int memberId,
			@PathVariable("bookId") int bookId) {
		return new ResponseEntity<>(libraryService.loanBook(memberId, bookId), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "members/{memberId}/books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> returnBook(
			@PathVariable("memberId") int memberId,
			@PathVariable("bookId") int bookId) {
		return new ResponseEntity<>(libraryService.returnBook(memberId, bookId), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "members/{memberId}/books", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> returnBooks(
			@PathVariable("memberId") int memberId) {
		return new ResponseEntity<>(libraryService.returnBooks(memberId), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "members/{memberId}/books", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getBooksForMember(
			@PathVariable("memberId") int memberId) {
		return new ResponseEntity<>(libraryService.getBooksForMember(memberId), HttpStatus.OK);
	}
	
	@GetMapping(value = "members", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, List<Book>>> getAllLoanedBooks() {
		return new ResponseEntity<>(libraryService.getAllLoanedBooks(), HttpStatus.OK);
	}
}
