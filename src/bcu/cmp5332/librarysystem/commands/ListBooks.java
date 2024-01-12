package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {

    // EXECUTE METHOD OVERRIDING COMMAND INTERFACE
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // GET LIST OF BOOKS FROM LIBRARY
        List<Book> books = library.getBooks();
        
        // PRINT DETAILS OF EACH BOOK
        for (Book book : books) {
            System.out.println(book.getDetailsShort());
        }
        
        // PRINT TOTAL NUMBER OF BOOKS
        System.out.println(books.size() + " book(s)");
    }
}