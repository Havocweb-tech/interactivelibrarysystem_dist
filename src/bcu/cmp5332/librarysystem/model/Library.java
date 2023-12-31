package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.util.*;

public class Library {
    
    private final int loanPeriod = 7;
    private final Map<Integer, Patron> patrons = new TreeMap<>();
    private final Map<Integer, Book> books = new TreeMap<>();

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public List<Book> getBooks() {
        List<Book> out = new ArrayList<>(books.values());
        return Collections.unmodifiableList(out);
    }
    
    public List<Patron> getPatrons() {
        List<Patron> out = new ArrayList<>(patrons.values());
        return Collections.unmodifiableList(out);
    }

    public Book getBookByID(int id) throws LibraryException {
        if (!books.containsKey(id)) {
            throw new LibraryException("There is no such book with that ID.");
        }
        return books.get(id);
    }

    public Patron getPatronByID(int id) throws LibraryException {
        // TODO: implementation here
    	if (!patrons.containsKey(id) || patrons.get(id).isDeleted()) {
            throw new LibraryException("There is no such Patron with that ID.");
        }
        return patrons.get(id);
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }
    
    public boolean deleteBook(int id) {
    	boolean deleted = false;
    	if (books.containsKey(id)) {
            books.remove(id);
            deleted = true;
        }
    	return deleted;
    }
    
    public void addPatron(Patron patron) {
        // TODO: implementation here
    	if (patrons.containsKey(patron.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        patrons.put(patron.getId(), patron);
    }
    public boolean deletePatron(int id) {
    	boolean deleted = false;
    	if (patrons.containsKey(id)) {
            patrons.remove(id);
            deleted = true;
        }
    	return deleted;
    }
}
 