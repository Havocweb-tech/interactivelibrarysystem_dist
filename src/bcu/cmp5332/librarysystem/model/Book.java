package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private boolean deleted = false;

    private Loan loan;

    public Book(int id, String title, String author, String publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

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
    	this.deleted = deleted;
    }
    
    public boolean isDeleted() {
    	return this.deleted;
    }
    
    public Book clone() {
    	Book copy = new Book(this.id, this.title, this.author, this.publicationYear);
    	copy.setDeleted(this.deleted);
    	return copy;
    }
    
    public void copy(Book rhs) {
    	this.id = rhs.getId();
    	this.title = rhs.getTitle();
    	this.author = rhs.getAuthor();
    	this.publicationYear = rhs.getPublicationYear();
    	this.deleted = rhs.isDeleted();
    }
}
