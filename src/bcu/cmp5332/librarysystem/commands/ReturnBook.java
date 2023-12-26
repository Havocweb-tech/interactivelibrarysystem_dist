package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class ReturnBook implements  Command {

    private final int bookId;
    private final int patronId;

    public ReturnBook(int bookId, int patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookId);
        Patron patron = library.getPatronByID(patronId);
        Book copyOfBook = book.clone();
        Patron copyOfPatron = patron.clone();
        try {
	        patron.returnBook(book);
	        LibraryData.store(library);
	        System.out.println("Book # " + bookId + " was Successfully returned by patron # " + patronId);
        }
        catch (IOException e) {
        	book.copy(copyOfBook);
        	patron.copy(copyOfPatron);
        	throw new LibraryException(FILE_ERROR_MESSAGE);
        }
    }
}
