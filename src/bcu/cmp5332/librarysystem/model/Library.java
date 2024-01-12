package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.util.*;

public class Library {
    
	// INSTANCE VARIABLES
	private final int loanPeriod = 7;
	private final Map<Integer, Patron> patrons = new TreeMap<>();
	private final Map<Integer, Book> books = new TreeMap<>();

	// GETTER METHODS
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

	// METHOD TO GET BOOK BY ID
	public Book getBookByID(int id) throws LibraryException {
	    if (!books.containsKey(id)) {
	        throw new LibraryException("There is no such book with that ID.");
	    }
	    return books.get(id);
	}


    public Patron getPatronByID(int id) throws LibraryException {
        // TODO: implementation here
    	// CHECK IF THE ID IS AVAILABLE OR IF THE PATRONID DELETED...
    	if (!patrons.containsKey(id) || patrons.get(id).isDeleted()) {
    		// THROW LIBRARY EXCEPTION...
            throw new LibraryException("There is no such Patron with that ID.");
        }
    	//RETURN PATRON ID
        return patrons.get(id);
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            throw new IllegalArgumentException("Duplicate book ID.");
        }
        books.put(book.getId(), book);
    }
    
    public boolean deleteBook(int id) {
    	// CREATE A BOOLEAN TO SPECIFY IF DELETE IS SUCCESSFUL...
    	boolean deleted = false;
    	if (books.containsKey(id)) {
            books.remove(id);
            // ASSIGNING THE BOOLEAN TO TRUE
            deleted = true;
        }
    	// RETURN DELETED
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
 