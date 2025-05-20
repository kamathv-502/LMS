
package main.java.service;

import main.java.model.Book;
import main.java.model.Patron;
import java.util.*;

public class LibraryService   {
    private BookService bookService;
    private PatronService patronService;
    private LendingService lendingService;


    public LibraryService() {
        this.bookService = new BookService();
        this.patronService = new PatronService();
        this.lendingService = new LendingService(bookService, patronService);
    }

    // Book Management - delegating to BookService
    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public void removeBook(String bookId) {
        bookService.removeBook(bookId);
    }

    public Book findBookByIsbn(String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    public List<Book> findBooksByTitle(String title) {
        return bookService.findBooksByTitle(title);
    }

    // Patron Management - delegating to PatronService
    public void addPatron(Patron patron) {
        patronService.addPatron(patron);
    }

    public void updatePatron(Patron patron) {
        patronService.updatePatron(patron);
    }

    public Patron getPatron(String patronId) {
        return patronService.getPatron(patronId);
    }

    public Set<String> getAllPatronIds() {
        return patronService.getAllPatronIds();
    }

    // Lending Process - delegating to LendingService
    public boolean checkoutBook(String bookId, String patronId) {
        return lendingService.checkoutBook(bookId, patronId);
    }

    public boolean returnBook(String bookId, String patronId) {
        return lendingService.returnBook(bookId, patronId);
    }


}
