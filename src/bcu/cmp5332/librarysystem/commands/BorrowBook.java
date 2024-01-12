package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;


public class BorrowBook implements Command{
	// DECLARE 2 VARIABLES FOR BOTH THE PATRON ID AND THE BOOK ID....
	
	// PATRON ID...
	private int p_id;
	
	// BOOK ID ...
	private int b_id;
	
	// BUILD A MODEL FOR THE BORROW BOOK METHOD....
	public BorrowBook(int patronID, int bookID) {
		this.p_id = patronID;
		this.b_id = bookID;
	}
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException{
		// GET THE PATRON WITH THE ID
		Patron patron = library.getPatronByID(p_id);
		// GET THE BOOK WITH THE ID
		Book book = library.getBookByID(b_id);
		// THIS MEANS DATE IN JAVA GETTING THE DATE STRING WE'RE ASSIGNING IT TO A VARIABLE dueDate
		LocalDate dueDate = LocalDate.now();
		// WE CALL THE LOAN PERIOD FROM OUR LIBRARY THE ADD IT TO THE DUE DATE
		dueDate = dueDate.plusDays(library.getLoanPeriod());
		patron.borrowBook(book, dueDate);
		System.out.println("Book # " + b_id + " Successfully Borrowed by Patron # "+ p_id);
	}
}