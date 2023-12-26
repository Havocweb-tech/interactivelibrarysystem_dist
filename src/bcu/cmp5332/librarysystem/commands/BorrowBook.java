package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;
import java.util.List;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;


public class BorrowBook implements Command{
	private int p_id;
	private int b_id;
	
	
	public BorrowBook(int patronID, int bookID) {
		this.p_id = patronID;
		this.b_id = bookID;
	}
	@Override
	public void execute(Library library, LocalDate currentDate) throws LibraryException{
		Patron patron = library.getPatronByID(p_id);
		Book book = library.getBookByID(b_id);
		LocalDate dueDate = LocalDate.now();
		dueDate = dueDate.plusDays(library.getLoanPeriod());
		patron.borrowBook(book, dueDate);
		System.out.println("Book # " + b_id + " Successfully Borrowed by Patron # "+ p_id);
	}
}