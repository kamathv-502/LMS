package main.java.service;

import main.java.model.Book;
import main.java.model.Patron;
import main.java.service.observer.ReservationObserver;

public class LendingService {
    private BookService bookService;
    private PatronService patronService;

    public LendingService(BookService bookService, PatronService patronService) {
        this.bookService = bookService;
        this.patronService = patronService;
    }

    public boolean checkoutBook(String bookId, String patronId) {
        Book book = bookService.getBook(bookId);
        Patron patron = patronService.getPatron(patronId);

        if (book != null && patron != null && book.isAvailable() 
            && book.getCurrentBranch().equals(patron.getCurrentBranch())) {
            bookService.updateBookAvailability(bookId, false);
            patron.borrowBook(bookId);
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookId, String patronId) {
        Book book = bookService.getBook(bookId);
        Patron patron = patronService.getPatron(patronId);

        if (book != null && patron != null && patron.getBorrowedBooks().contains(bookId)) {
            bookService.updateBookAvailability(bookId, true);
            patron.returnBook(bookId);
            return true;
        }
        return false;
    }


}