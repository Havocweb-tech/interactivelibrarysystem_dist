package bcu.cmp5332.librarysystem.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;

public class RenewBook implements Command {
	private int p_id;
	private int b_id;
	
	public RenewBook(int patronID, int bookID)
	{
		this.p_id = patronID;
		this.b_id = bookID;
	}
	
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {

		Patron patron = library.getPatronByID(p_id);
		Book book = library.getBookByID(b_id);
		LocalDate dueDate = LocalDate.now();
		dueDate.plusDays(library.getLoanPeriod());
		Book copyBook = book.clone();
		Patron copyPatron = patron.clone();
		try {
			patron.renewBook(book, dueDate);
			LibraryData.store(library);
			System.out.println("Book # " + b_id + " Successfully Renewed by patron # " + p_id);
		} catch (IOException e) {
			book.copy(copyBook);
			patron.copy(copyPatron);
			throw new LibraryException(FILE_ERROR_MESSAGE);
		}
    }
	
}
