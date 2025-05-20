
package main.java.model;

import main.java.model.iterator.BookIterator;
import main.java.model.iterator.BorrowingHistoryIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patron {
    private String id;
    private String name;
    private String email;
    private String currentBranch;
    private final List<String> borrowedBooks;
    private final List<String> borrowingHistory;

    public Patron(String name, String email, String currentBranch) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.currentBranch = currentBranch;
        this.borrowedBooks = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
    }

    public String getCurrentBranch() { return currentBranch; }
    public void setCurrentBranch(String currentBranch) { this.currentBranch = currentBranch; }

    public void borrowBook(String bookId) {
        borrowedBooks.add(bookId);
        borrowingHistory.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBooks.remove(bookId);
    }

    public BookIterator getBorrowingHistoryIterator() {
        return new BorrowingHistoryIterator(borrowingHistory);
    }

    public BookIterator getCurrentBorrowingsIterator() {
        return new BorrowingHistoryIterator(borrowedBooks);
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<String> getBorrowedBooks() { return borrowedBooks; }
    public List<String> getBorrowingHistory() { return borrowingHistory; }
}
