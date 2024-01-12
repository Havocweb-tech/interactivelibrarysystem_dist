
package bcu.cmp5332.librarysystem.gui;


import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.ViewBookPatronGUI;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ViewBookPatronWindow extends JFrame {

    // INSTANCE VARIABLES
    private MainWindow mw;
    private int bookId;

    // CONSTRUCTOR
    public ViewBookPatronWindow(int bookId, MainWindow mw) {
        this.mw = mw;
        this.bookId = bookId;
        getBookPatronInfo();
    }

    // METHOD TO GET BOOK PATRON INFORMATION
    private void getBookPatronInfo() {
        try {
            // CREATE AND EXECUTE THE VIEWBOOKPATRONGUI COMMAND
            Command viewBookPatron = new ViewBookPatronGUI(bookId);
            viewBookPatron.execute(mw.getLibrary(), LocalDate.now());
        } catch (LibraryException ex) {
            // HANDLE LIBRARY EXCEPTION
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
