package bcu.cmp5332.librarysystem.commands;


import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class DeletePatron implements  Command {

    private final int id;
    public DeletePatron(int id) {
    	this.id = id;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    	Patron patron = library.getPatronByID(id);
    	Patron copyOfPatron = patron.clone();
    	try {
	        boolean result = library.deletePatron(id);
	        LibraryData.store(library);
	        if (result)
	        	System.out.println("Patron #" + id + " removed.");
	        else
	        	System.out.println("Patron #" + id + " doesn't exist.");
	    }
		catch (IOException e) {
			patron.copy(copyOfPatron);
			throw new LibraryException(FILE_ERROR_MESSAGE);
		}
    }
}
