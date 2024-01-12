package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;

import java.io.IOException;
import java.time.LocalDate;

public class AddPatron implements Command {

    private final String name;
    private final String phone;
    private final String email;
    // THE MODEL FOR THE PATRON MODEL...
    public AddPatron(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email =  email;
    }

    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // TODO: implementation here
    	// ASSIGN AN INTERGER THE VALUE OF 0, TO SPECIFY THE NEXT ID OF THE PATRON
    	int maxId = 0;
    	if (library.getPatrons().size() > 0) {
    		int lastIndex = library.getPatrons().size() - 1;
            maxId = library.getPatrons().get(lastIndex).getId();
    	}
    	// ADD PATRON TO THE PATRON MODEL.......
        Patron patron = new Patron(++maxId, name, phone, email);
        try {
	        library.addPatron(patron);
	        LibraryData.store(library);
	        System.out.println("Patron #" + patron.getId() + " added.");
        } catch (IOException e) {
        	library.deletePatron(patron.getId());
        	throw new LibraryException(FILE_ERROR_MESSAGE);
        }
    }
}
 