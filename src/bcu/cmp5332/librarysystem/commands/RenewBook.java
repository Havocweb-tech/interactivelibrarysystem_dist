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

    // CONSTRUCTOR
    public RenewBook(int patronID, int bookID) {
        this.p_id = patronID;
        this.b_id = bookID;
    }

    // EXECUTE METHOD OVERRIDING COMMAND INTERFACE
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {

        // GET PATRON AND BOOK BY ID
        Patron patron = library.getPatronByID(p_id);
        Book book = library.getBookByID(b_id);
        LocalDate dueDate = LocalDate.now();
        dueDate = dueDate.plusDays(library.getLoanPeriod());  // FIX: Assign the updated due date back to the variable

        // CREATE COPIES OF BOOK AND PATRON
        Book copyBook = book.clone();
        Patron copyPatron = patron.clone();

        try {
            // RENEW BOOK AND STORE LIBRARY DATA
            patron.renewBook(book, dueDate);
            LibraryData.store(library);
            System.out.println("Book # " + b_id + " Successfully Renewed by patron # " + p_id);
        } catch (IOException e) {
            // ROLLBACK TO COPIES IN CASE OF EXCEPTION
            book.copy(copyBook);
            patron.copy(copyPatron);
            throw new LibraryException(FILE_ERROR_MESSAGE);
        }
    }
}
