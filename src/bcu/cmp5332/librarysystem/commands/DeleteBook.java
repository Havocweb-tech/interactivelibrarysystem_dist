package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class DeleteBook implements  Command {

    private final int id;
    public DeleteBook(int id) {
    	this.id = id;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    	Book book = library.getBookByID(id);
    	Book copyOfBook = book.clone();
    	try {
	        boolean result = library.deleteBook(id);
	        LibraryData.store(library);
	        if (result)
	        	System.out.println("Book #" + id + " removed.");
	        else
	        	System.out.println("Book #" + id + " doesn't exist.");
    	}
    	catch (IOException e) {
    		book.copy(copyOfBook);
    		throw new LibraryException(FILE_ERROR_MESSAGE);
    	}
    }
}
