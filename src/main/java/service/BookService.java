
package main.java.service;

import main.java.model.Book;
import java.util.*;
import java.util.stream.Collectors;

public class BookService {
    private Map<String, Book> books;

    public BookService() {
        this.books = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void removeBook(String bookId) {
        books.remove(bookId);
    }

    public Book findBookByIsbn(String isbn) {
        return books.values().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Book> findBooksByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Book getBook(String bookId) {
        return books.get(bookId);
    }

    public void updateBookAvailability(String bookId, boolean isAvailable) {
        Book book = books.get(bookId);
        if (book != null) {
            book.setAvailable(isAvailable);
        }
    }
}
