
package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class showpatron implements Command {
	
	// INSTANCE VARIABLES
	private int Id;
	
	// CONSTRUCTOR
	public showpatron(int id) {
		this.Id = id;
	}
	
    // OVERRIDE execute METHOD
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // GET THE LIST OF PATRONS FROM THE LIBRARY
        List<Patron> patrons = library.getPatrons();
        
        // ITERATE THROUGH THE LIST OF PATRONS
        for (Patron patron : patrons) {
        	// CHECK IF THE CURRENT PATRON HAS THE SPECIFIED ID
        	if(patron.getId() == Id) {
        		// PRINT PATRON DETAILS USING getMyPatron METHOD
        		System.out.println(patron.getMyPatron(Id));
        	}
        }
    }
}
