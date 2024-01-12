
package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewBookPatronGUI implements  Command {

    // INSTANCE VARIABLES
    private final int bookId;
    
    // CONSTRUCTOR
    public ViewBookPatronGUI(int bookId) {
        this.bookId = bookId;
    }
    
    // OVERRIDE execute METHOD
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        // GET THE BOOK BY ID FROM THE LIBRARY
    	Book book = library.getBookByID(bookId);
    	
    	// CHECK IF THE BOOK IS ON LOAN
    	if (book.isOnLoan()) {
    		// CREATE A TEMPORARY JFRAME
    		JFrame tmpFrame = new JFrame();
    		
    		// GET THE PATRON WHO LOANED THE BOOK
    		Patron patron = library.getBookByID(bookId).getLoan().getPatron();
    		
    		// CREATE A PANEL FOR DISPLAYING PATRON INFORMATION
	    	JPanel topPanel = new JPanel();
	        topPanel.setLayout(new GridLayout(5, 2));
	        
	        // ADD LABELS AND DATA TO THE PANEL
	        topPanel.add(new JLabel("Id : "));
	        topPanel.add(new JLabel(String.valueOf(patron.getId())));
	        topPanel.add(new JLabel("Name : "));
	        topPanel.add(new JLabel(patron.getName()));
	        topPanel.add(new JLabel("Phone : "));
	        topPanel.add(new JLabel(patron.getPhone()));
	        topPanel.setVisible(true);
	        
	        // ADD PANEL TO THE FRAME, PACK, AND MAKE VISIBLE
	        tmpFrame.getContentPane().add(topPanel, BorderLayout.CENTER);
	        tmpFrame.pack();
	        tmpFrame.setVisible(true);
    	}
    }
}
