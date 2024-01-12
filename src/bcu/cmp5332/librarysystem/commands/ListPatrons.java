package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListPatrons implements Command {

    // EXECUTE METHOD OVERRIDING COMMAND INTERFACE
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // GET LIST OF PATRONS FROM LIBRARY
        List<Patron> patrons = library.getPatrons();
        
        // PRINT DETAILS OF EACH PATRON
        for (Patron patron : patrons) {
            System.out.println(patron.getDetailShort());
        }
        
        // PRINT TOTAL NUMBER OF PATRONS
        System.out.println(patrons.size() + " Patron(s)");
    }
}

 