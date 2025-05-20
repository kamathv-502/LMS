
package main.java.service;

import main.java.model.Patron;
import java.util.*;

public class PatronService {
    private final Map<String, Patron> patrons;

    public PatronService() {
        this.patrons = new HashMap<>();
    }

    public void addPatron(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public void updatePatron(Patron patron) {
        patrons.put(patron.getId(), patron);
    }

    public Patron getPatron(String patronId) {
        return patrons.get(patronId);
    }

    public Set<String> getAllPatronIds() {
        return patrons.keySet();
    }
}
