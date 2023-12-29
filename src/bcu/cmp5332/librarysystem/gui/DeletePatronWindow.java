package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.DeletePatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class DeletePatronWindow  {

    private MainWindow mw;
    int id;
    
    public DeletePatronWindow(MainWindow mw) {
    	this.mw = mw;
    	initialize();
    }
    
    private void initialize() {
    	String res= JOptionPane.showInputDialog("Patron Id:");
    	id = Integer.parseInt(res);
    	performAction();
    }
    
    private void performAction() {
        try {
        	Command deleteBook = new DeletePatron(id);
			deleteBook.execute(mw.getLibrary(), LocalDate.now());
			mw.displayPatrons();
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}
