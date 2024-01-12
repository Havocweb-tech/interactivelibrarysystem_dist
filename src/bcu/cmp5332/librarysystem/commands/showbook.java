package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class showbook implements Command {

    // PRIVATE FIELD
    private int Id;

    // CONSTRUCTOR
    public showbook(int id) {
        this.Id = id;
    }

    // EXECUTE METHOD OVERRIDING COMMAND INTERFACE
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // GET LIST OF BOOKS FROM LIBRARY
        List<Book> books = library.getBooks();

        // FLAG TO INDICATE IF BOOK IS FOUND
        boolean bookFound = false;

        // LOOP THROUGH BOOKS TO FIND THE SPECIFIED BOOK
        for (Book book : books) {
            if (book.getId() == Id) {
                // PRINT BOOK DETAILS AND SET FLAG TO TRUE
                System.out.println(book.getMyBook(Id));
                bookFound = true;
                break;
            }
        }

        // IF BOOK NOT FOUND, PRINT MESSAGE
        if (!bookFound) {
            System.out.println("Book not Found");
        }
    }
}

 