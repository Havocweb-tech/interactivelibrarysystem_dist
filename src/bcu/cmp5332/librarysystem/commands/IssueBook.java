package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class IssueBook implements  Command {

    private final int bookId;
    private final int patronId;
    private final LocalDate dueDate;

    public IssueBook(int bookId, int patronId, LocalDate dueDate) {
        this.bookId = bookId;
        this.patronId = patronId;
        this.dueDate = dueDate;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book book = library.getBookByID(bookId);
        Patron patron = library.getPatronByID(patronId);
        if (patron.getBooks().size() >= Patron.MAX_BORROW_LIMIT)
        	throw new LibraryException("Max borrow limit reached!");
        Book copyOfBook = book.clone();
        Patron copyOfPatron = patron.clone();
        try {
        	patron.borrowBook(book,  dueDate);
        	LibraryData.store(library);
        }
        catch (IOException e) {
        	patron.copy(copyOfPatron);
        	book.copy(copyOfBook);
        	throw new LibraryException(FILE_ERROR_MESSAGE);
        }
    }
}
