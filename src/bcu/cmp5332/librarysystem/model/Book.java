package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String Publisher;
    private boolean deleted = false;

    private Loan loan;
    
    // CONSTRUCTOR
    public Book(int id, String title, String author, String publicationYear, String Publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.Publisher = Publisher;
    }

    
    // GETTERS AND SETTERS METHODS
    public int getId() {
        return id;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }
    
    public String getPublisher() {
    	return Publisher;
    }
    
    public void setPublisher(String Publisher) {
    	this.Publisher = Publisher;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }
    public String getMyBook(int Id) {
    	if (id == Id) {
    		return "Book #" + id + " - " + title;
    	}else {
    		return "Book not found";
    	}
    	
    }
    public String getDetailsLong() {
    	String toReturn = "Book ID: "+ id + "\n";
        toReturn += "Title: " + title + "\n";
        toReturn += "Author: " + author + "\n";
        toReturn += "Publication Year: " + publicationYear + "\n";
        toReturn += "Publisher: "+ Publisher + "\n";
        return toReturn;       
    }
    
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    public String getStatus() {
        if (loan == null)
        	return "Available";
        else
        	return "On Loan";
    }

    public LocalDate getDueDate() {
    	if (this.isOnLoan() == true)
    		return null;
    	else {
    		return this.loan.getDueDate();
    	}
    }
    
    public void setDueDate(LocalDate dueDate) throws LibraryException {
    	if (this.isOnLoan())
    	{
    		this.loan.setDueDate(dueDate);
    	}
    	else
    	{
    		throw new LibraryException("Unable to Set Due Date");
    	}
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void returnToLibrary() {
        loan = null;
    }
    public void setDeleted(boolean deleted) {
    	// SETTING BOOK TO DELETED
    	this.deleted = deleted;
    }
    
    public boolean isDeleted() {
    	return this.deleted;
    }
    
    public Book clone() {
    	// CREATE A NEW BOOK
    	Book copy = new Book(this.id, this.title, this.author, this.publicationYear, this.Publisher);
    	// SETTING THE BOOK COPY TO NOT DELETED
    	copy.setDeleted(this.deleted);
    	// RETURN COPY....
    	return copy;
    }
    
    public void copy(Book rhs) {
    	this.id = rhs.getId();
    	this.title = rhs.getTitle();
    	this.author = rhs.getAuthor();
    	this.publicationYear = rhs.getPublicationYear();
    	this.Publisher = rhs.getPublisher();
    	this.deleted = rhs.isDeleted();
    }
}
