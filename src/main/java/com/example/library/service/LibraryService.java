package com.example.library.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.example.library.LibraryDAO;
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
	
	public List<Book> getBooksForMember(int memberId) {
		return libraryDao.getBooksForMember(memberId);
	}
}
