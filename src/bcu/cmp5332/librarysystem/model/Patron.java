package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import bcu.cmp5332.librarysystem.model.*;
public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private final List<Book> books = new ArrayList<>();
    private boolean deleted = false;
    
    
    public static final int MAX_BORROW_LIMIT = 4;
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
        if (book.isOnLoan() == false) {
        	Loan loan = new Loan(this, book, LocalDate.now(), dueDate);
        	book.setLoan(loan);
        	books.add(book);
        }else {
        	throw new LibraryException("The specified book-id is already on loan");
        }
    }

    public void renewBook(Book book, LocalDate dueDate) throws LibraryException {
    	boolean found = false;
    	for (int i = 0; i < books.size(); i++)
    	{
    		if (books.get(i).getId() == book.getId())
    		{
    			found = true;
    			book.setDueDate(dueDate);
    			books.set(i, book);
    		}
    	}
    	if (found == false)
    	{
    		throw new LibraryException("No book found in books to renew.");
    	}
    }

    public void returnBook(Book book) throws LibraryException {
    	boolean found = false;
    	for (int i = 0; i < books.size(); i++)
    	{
    		if (books.get(i).getId() == book.getId())
    		{
    			found = true;
    			books.get(i).setLoan(null);
    			books.remove(i);
    			break;
    		}
    	}
    	if (found == false)
    	{
    		throw new LibraryException("Cannot return book as not found in books.");
    	}
    }
    
    public void addBook(Book book) {
    	boolean found = false;
    	for (int i = 0; i < books.size(); i++)
    	{
    		if (books.get(i).getId() == book.getId())
    		{
    			found = true;
    			break;
    		}
    	}
    	if (!found)
    	{
    		this.books.add(book);
    	}
    }
    public List<Book> getBooks(){
    	return books;
    }
    public String getDetailShort() {
        return "Patron #" + id + " - " + name;
    }
    public String getDetailsLong() {
    	String str = "ID: " + this.getId() + "\n";
		str += "Name: " + this.getName() + "\n";
		str += "Phone number: " + this.getPhone() + "\n";
		return str;
    }
    public String getMyPatron(int Id) {
    	if (id == Id) {
    		return "Patron #" + id + " - " + name;
    	}
    	return "Patron not found";
    }
    public void setDeleted(boolean deleted) {
    	this.deleted = deleted;
    }
	
	public boolean isDeleted() {
		return this.deleted;
	}
	
	 public Patron clone() {
    	Patron copy = new Patron(this.id, this.name, this.phone);
    	copy.setDeleted(this.deleted);
    	
    	for (int i = 0; i < books.size(); i++)
    		copy.addBook(books.get(i));
    	return copy;
    }
    
    public void copy(Patron rhs) {
    	this.id = rhs.getId();
    	this.name = rhs.getName();
    	this.phone = rhs.getPhone();
    	this.deleted = rhs.isDeleted();
    	this.books.clear();
    	
    	for(int i = 0; i< rhs.getBooks().size(); i++) {
    		books.add(rhs.getBooks().get(i));
    	}
    }
}
 