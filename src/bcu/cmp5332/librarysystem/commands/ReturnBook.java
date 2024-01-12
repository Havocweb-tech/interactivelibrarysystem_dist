package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class ReturnBook implements Command {

    // PRIVATE FIELDS
    private final int bookId;
    private final int patronId;

    // CONSTRUCTOR
    public ReturnBook(int bookId, int patronId) {
        this.bookId = bookId;
        this.patronId = patronId;
    }

    // EXECUTE METHOD OVERRIDING COMMAND INTERFACE
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // GET BOOK AND PATRON BY ID
        Book book = library.getBookByID(bookId);
        Patron patron = library.getPatronByID(patronId);
        
        // CREATE COPIES OF BOOK AND PATRON
        Book copyOfBook = book.clone();
        Patron copyOfPatron = patron.clone();
        
        try {
            // RETURN BOOK AND STORE LIBRARY DATA
            patron.returnBook(book);
            LibraryData.store(library);
            System.out.println("Book # " + bookId + " was Successfully returned by patron # " + patronId);
        } catch (IOException e) {
            // ROLLBACK TO COPIES IN CASE OF EXCEPTION
            book.copy(copyOfBook);
            patron.copy(copyOfPatron);
            // THROW LIBRARY EXCEPTION WITH CUSTOM ERROR MESSAGE
            throw new LibraryException("Error returning the book. Please try again.");
        }
    }
}
