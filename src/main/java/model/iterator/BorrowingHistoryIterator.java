package main.java.model.iterator;

import java.util.List;

public class BorrowingHistoryIterator implements BookIterator {
    private List<String> books;
    private int position;

    public BorrowingHistoryIterator(List<String> books) {
        this.books = books;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < books.size();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return books.get(position++);
        }
        return null;
    }
}
