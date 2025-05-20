package main.java.controller;


import main.java.model.Book;
import main.java.model.Patron;
import main.java.service.LibraryService;
import java.util.Scanner;
import java.util.List;

public class IOController {
    private LibraryService libraryService;
    private Scanner scanner;

    public IOController() {
        this.libraryService = new LibraryService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Library Management System!");
        String command;

        do {
            printMenu();
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "1":
                    addBook();
                    break;
                case "2":
                    addPatron();
                    break;
                case "3":
                    checkoutBook();
                    break;
                case "4":
                    returnBook();
                    break;
                case "5":
                    searchBook();
                    break;
                case "6":
                    changeBookBranch();
                    break;
                case "7":
                    changePatronBranch();
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (!command.equals("exit"));

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Add a new book");
        System.out.println("2. Add a new patron");
        System.out.println("3. Checkout book");
        System.out.println("4. Return book");
        System.out.println("5. Search book");
        System.out.println("6. Change book branch");
        System.out.println("7. Change patron branch");
        System.out.println("Type 'exit' to quit");
    }

    private void addBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter author:");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter publication year:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter branch ID:");
        String branchId = scanner.nextLine();

        Book book = new Book(title, author, isbn, year, branchId);
        libraryService.addBook(book);
        System.out.println("Book added successfully!");
    }

    private void addPatron() {
        System.out.println("Enter patron name:");
        String name = scanner.nextLine();
        System.out.println("Enter patron email:");
        String email = scanner.nextLine();
        System.out.println("Enter branch ID:");
        String branchId = scanner.nextLine();

        Patron patron = new Patron(name, email, branchId);
        libraryService.addPatron(patron);
        System.out.println("Patron added successfully!");
    }

    private void checkoutBook() {
        System.out.println("Enter book ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter patron email:");
        String email = scanner.nextLine();

        Book book = libraryService.findBookByIsbn(isbn);
        if (book != null) {
            boolean success = libraryService.checkoutBook(book.getId(), email);
            if (success) {
                System.out.println("Book checked out successfully!");
            } else {
                System.out.println("Failed to checkout book.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private void returnBook() {
        System.out.println("Enter book ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter patron email:");
        String email = scanner.nextLine();

        Book book = libraryService.findBookByIsbn(isbn);
        if (book != null) {
            boolean success = libraryService.returnBook(book.getId(), email);
            if (success) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Failed to return book.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private void searchBook() {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();

        List<Book> books = libraryService.findBooksByTitle(title);
        if (!books.isEmpty()) {
            System.out.println("Found books:");
            for (Book book : books) {
                System.out.println("- " + book.getTitle() + " by " + book.getAuthor() + " (ISBN: " + book.getIsbn() + ")");
            }
        } else {
            System.out.println("No books found.");
        }
    }

    private void changeBookBranch() {
        System.out.println("Enter book ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter new branch ID:");
        String branchId = scanner.nextLine();

        Book book = libraryService.findBookByIsbn(isbn);
        if (book != null) {
            book.setCurrentBranch(branchId);
            System.out.println("Book branch updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void changePatronBranch() {
        System.out.println("Enter patron email:");
        String email = scanner.nextLine();
        System.out.println("Enter new branch ID:");
        String branchId = scanner.nextLine();

        // Find patron by email and update branch
        for (String patronId : libraryService.getAllPatronIds()) {
            Patron patron = libraryService.getPatron(patronId);
            if (patron != null && patron.getEmail().equals(email)) {
                patron.setCurrentBranch(branchId);
                libraryService.updatePatron(patron);
                System.out.println("Patron branch updated successfully!");
                return;
            }
        }
        System.out.println("Patron not found.");
    }

}


