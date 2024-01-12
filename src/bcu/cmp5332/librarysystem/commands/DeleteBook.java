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
    	// GET THE BOOK BY THE SENT ID
    	Book book = library.getBookByID(id);
    	// CREATING A COPY OF THE BOOK
    	Book copyOfBook = book.clone();
    	try {
    		// TRYING TO DELETE BOOK...
	        boolean result = library.deleteBook(id);
	        // STORE THE DATA IN THE LIBRARY
	        LibraryData.store(library);
	        if (result)
	        	System.out.println("Book #" + id + " removed.");
	        else
	        	System.out.println("Book #" + id + " doesn't exist.");
    	}
    	catch (IOException e) {
    		// CATCH FOR FAILURE BENEATH
    		book.copy(copyOfBook);
    		throw new LibraryException(FILE_ERROR_MESSAGE);
    	}
    }
}
