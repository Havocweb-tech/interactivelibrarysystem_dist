
package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.DeletePatron;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class DeletePatronWindow {

    // INSTANCE VARIABLES
    private MainWindow mw;
    private int id;

    // CONSTRUCTOR
    public DeletePatronWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    // METHOD TO INITIALIZE THE DELETE PATRON WINDOW
    private void initialize() {
        // PROMPT USER FOR PATRON ID
        String res = JOptionPane.showInputDialog("Patron Id:");
        id = Integer.parseInt(res);
        // PERFORM ACTION TO DELETE PATRON
        performAction();
    }

    // METHOD TO PERFORM THE DELETE PATRON ACTION
    private void performAction() {
        try {
            // CREATE DELETE PATRON COMMAND AND EXECUTE
            Command deletePatron = new DeletePatron(id);
            deletePatron.execute(mw.getLibrary(), LocalDate.now());
            // DISPLAY UPDATED PATRON LIST
            mw.displayPatrons();
        } catch (LibraryException e) {
            // HANDLE LIBRARY EXCEPTION
            e.printStackTrace();
        }
    }
}
