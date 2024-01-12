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
    	// GETTING THE PATRON ON THE ID
    	Patron patron = library.getPatronByID(id);
    	// CLONING THE PATRON RECENTLY GOTTEN ...
    	Patron copyOfPatron = patron.clone();
    	try {
    		// TRYING TO DELETE PATRON BY THE ID...
	        boolean result = library.deletePatron(id);
	        // STORE THE NEW DATA INTO THE LIBRARY
	        LibraryData.store(library);
	        
	        // CHECK IF RESULT RETURNS TRUE OR FALSE, THE DISPLAY MESSAGE ACCORDINGLY
	        if (result)
	        	System.out.println("Patron #" + id + " removed.");
	        else
	        	System.out.println("Patron #" + id + " doesn't exist.");
	    }
		catch (IOException e) {
			// CATCH FOR ERROR THEN THROUGH EXCEPTION
			patron.copy(copyOfPatron);
			throw new LibraryException(FILE_ERROR_MESSAGE);
		}
    }
}
