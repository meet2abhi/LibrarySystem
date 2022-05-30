package com.example.library.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.example.library.data.LibraryDAO;
import com.example.library.model.Book;

@Component
public class LibraryService {
	
	@Resource
	LibraryDAO libraryDao;
	
	public Book getBook(int bookId) {
		return libraryDao.getBook(bookId);
	}

	public List<Book> getBooks() {
		return libraryDao.getBooks();
	}

	public Book addBook(Book book) {
		return libraryDao.addBook(book);
	}
	
	public Book deleteBook(int bookId) {
		return libraryDao.deleteBook(bookId);
	}
	
	public Book loanBook(int memberId, int bookId) {
		return libraryDao.loanBook(memberId, bookId);
	}
	
	public Book returnBook(int memberId, int bookId) {
		return libraryDao.returnBook(memberId, bookId);
	}
	
	public List<Book> returnBooks(int memberId) {
		return libraryDao.returnBooks(memberId);
	}
	
	public List<Book> getBooksForMember(int memberId) {
		return libraryDao.getBooksForMember(memberId);
	}
	
	public Map<Integer, List<Book>> getAllLoanedBooks() {
		return libraryDao.getAllLoanedBooks();
	}
}
