package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron {
    
    private int id;
    private String name;
    private String phone;
    // private final List<Book> books = new ArrayList<>();
    
    public Patron(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    // TODO: implement constructor here
    
    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void borrowBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
        // TODO: implementation here
    }

    public void returnBook(Book book) throws LibraryException {
        // TODO: implementation here
    }
    
    public void addBook(Book book) {
        // TODO: implementation here
    }
    public String getDetailShort() {
        return "Patron #" + id + " - " + name;
    }
    public String getMyPatron(int Id) {
    	if (id == Id) {
    		return "Patron #" + id + " - " + name;
    	}
    	return "Patron not found";
    }
}
 