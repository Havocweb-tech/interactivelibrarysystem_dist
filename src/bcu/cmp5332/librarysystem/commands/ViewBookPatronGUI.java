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

    private final int bookId;
    public ViewBookPatronGUI(int bookId) {
        this.bookId = bookId;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    	Book book = library.getBookByID(bookId);
    	if (book.isOnLoan()) {
    		JFrame tmpFrame = new JFrame();
    		Patron patron = library.getBookByID(bookId).getLoan().getPatron();
	    	JPanel topPanel = new JPanel();
	        topPanel.setLayout(new GridLayout(5, 2));
	        topPanel.add(new JLabel("Id : "));
	        topPanel.add(new JLabel(String.valueOf(patron.getId())));
	        topPanel.add(new JLabel("Name : "));
	        topPanel.add(new JLabel(patron.getName()));
	        topPanel.add(new JLabel("Phone : "));
	        topPanel.add(new JLabel(patron.getPhone()));
	        topPanel.setVisible(true);
	        tmpFrame.getContentPane().add(topPanel, BorderLayout.CENTER);
	        tmpFrame.pack();
	        tmpFrame.setVisible(true);
	        
    	}
    }
}
