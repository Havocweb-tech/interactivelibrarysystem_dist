package bcu.cmp5332.librarysystem.commands;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class showpatron implements Command {
	private int Id;
	public showpatron(int id) {
		this.Id = id;
	}
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Patron> patrons = library.getPatrons();
        for (Patron patron : patrons) {
        	if(patron.getId() == Id) {
        		System.out.println(patron.getMyPatron(Id));
        	}
            
            
        }
        
    }
}
 