
package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.ViewBookPatronGUI;
import bcu.cmp5332.librarysystem.commands.ViewPatronBooksGUI;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ViewPatronBooksWindow extends JFrame {

    // INSTANCE VARIABLES
    private MainWindow mw;
    private int patronId;

    // CONSTRUCTOR
    public ViewPatronBooksWindow(int patronId, MainWindow mw) {
        this.mw = mw;
        this.patronId = patronId;
        getPatronBooksInfo();
    }

    // METHOD TO GET PATRON BOOKS INFORMATION
    private void getPatronBooksInfo() {
        try {
            // CREATE AND EXECUTE THE VIEWPATRONBOOKSGUI COMMAND
            Command viewPatronBooks = new ViewPatronBooksGUI(patronId);
            viewPatronBooks.execute(mw.getLibrary(), LocalDate.now());
        } catch (LibraryException ex) {
            // HANDLE LIBRARY EXCEPTION
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
