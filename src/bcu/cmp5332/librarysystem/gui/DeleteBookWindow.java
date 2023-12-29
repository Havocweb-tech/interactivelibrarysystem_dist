package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.DeleteBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class DeleteBookWindow  {

    private MainWindow mw;
    int id;
    
    public DeleteBookWindow(MainWindow mw) {
    	this.mw = mw;
    	initialize();
    }
    
    private void initialize() {
    	String res= JOptionPane.showInputDialog("Book Id:");
    	id = Integer.parseInt(res);
    	performAction();
    }
    
    private void performAction() {
        try {
        	Command deleteBook = new DeleteBook(id);
			deleteBook.execute(mw.getLibrary(), LocalDate.now());
			mw.displayBooks();
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
