
package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;


import java.io.IOException;
import java.time.LocalDate;

// ISSUEBOOK CLASS DECLARATION
public class IssueBook implements Command {

    // PRIVATE FIELDS
    private final int bookId;
    private final int patronId;
    private final LocalDate dueDate;
    
    // CONSTRUCTOR
    // ISSUE BOOK MODEL
    public IssueBook(int bookId, int patronId, LocalDate dueDate) {
        this.bookId = bookId;
        this.patronId = patronId;
        this.dueDate = dueDate;
    }
    
    // EXECUTE METHOD OVERRIDING COMMAND INTERFACE
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    	
    	// GET BOOK BY THE ID
        Book book = library.getBookByID(bookId);
        Patron patron = library.getPatronByID(patronId);
        
        // CHECK IF PATRON HAS REACHED MAX BORROW LIMIT
        if (patron.getBooks().size() >= Patron.MAX_BORROW_LIMIT)
        	throw new LibraryException("Max borrow limit reached!");
        
        // CREATE COPIES OF BOOK AND PATRON
        Book copyOfBook = book.clone();
        Patron copyOfPatron = patron.clone();
        
        try {
        	// BORROW BOOK AND STORE LIBRARY DATA
        	patron.borrowBook(book,  dueDate);
        	LibraryData.store(library);
        }
        // HANDLE IOEXCEPTION
        catch (IOException e) {
        	// ROLLBACK TO COPIES IN CASE OF EXCEPTION
        	patron.copy(copyOfPatron);
        	book.copy(copyOfBook);
        	throw new LibraryException(FILE_ERROR_MESSAGE);
        }
    }
}
