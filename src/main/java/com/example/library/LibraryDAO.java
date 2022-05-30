package com.example.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.MethodNotAllowedException;

import com.example.library.model.Book;

@Repository
public class LibraryDAO {

	int bookId;
	List<Book> books;
	List<Book> loanedBooks;
	Map<Integer, List<Book>> membersInventory;

	@PostConstruct
	public void init() {
		bookId = 0;
		books = new ArrayList<Book>();
		loanedBooks = new ArrayList<Book>();
		membersInventory = new HashMap<Integer, List<Book>>();
	}

	public Book getBook(int bookId) {
		return books.stream().filter(book -> bookId == book.getBookId()).findFirst().get();
	}

	public List<Book> getBooks() {
		return books;
	}

	public Book addBook(Book book) {
		bookId++;
		book.setBookId(bookId);
		books.add(book);
		return book;
	}

	public synchronized Book deleteBook(int bookId) {
		if (null != loanedBooks.stream().filter(book -> bookId == book.getBookId()).findFirst().orElse(null)) {
			throw new MethodNotAllowedException(HttpMethod.DELETE.name(), null);
		}
		Book foundBook = books.stream().filter(book -> bookId == book.getBookId()).findFirst().get();
		books.remove(foundBook);
		return foundBook;
	}

	public synchronized Book loanBook(int memberId, int bookId) {
		Book foundBook = books.stream().filter(book -> bookId == book.getBookId()).findFirst().get();
		List<Book> loanedBooksToMember = Optional.ofNullable(membersInventory.get(memberId))
				.orElse(new ArrayList<Book>());
		if (loanedBooksToMember.size() == 3) {
			throw new MethodNotAllowedException(HttpMethod.DELETE.name(), null);
		}
		if (loanedBooks.contains(foundBook)) {
			throw new MethodNotAllowedException(HttpMethod.GET.name(), null);
		}
		loanedBooks.add(foundBook);
		loanedBooksToMember.add(foundBook);
		membersInventory.put(memberId, loanedBooksToMember);
		return foundBook;
	}

	public synchronized Book returnBook(int memberId, int bookId) {
		List<Book> loanedBooksToMember = membersInventory.get(memberId);
		if (null == loanedBooksToMember) {
			throw new NoSuchElementException();
		}
		Book foundBook = books.stream().filter(book -> bookId == book.getBookId()).findFirst().get();
		if (!loanedBooksToMember.contains(foundBook)) {
			throw new NoSuchElementException();
		}
		loanedBooksToMember.remove(foundBook);
		loanedBooks.remove(foundBook);
		membersInventory.put(memberId, loanedBooksToMember);
		return foundBook;
	}
	
	public List<Book> getBooksForMember(int memberId) {
		List<Book> loanedBooksToMember = membersInventory.get(memberId);
		if (null == loanedBooksToMember) {
			throw new NoSuchElementException();
		}
		return loanedBooksToMember;
	}
}
