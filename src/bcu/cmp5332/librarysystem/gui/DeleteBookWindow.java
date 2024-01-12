
package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.DeleteBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

public class DeleteBookWindow {

    // INSTANCE VARIABLES
    private MainWindow mw;
    private int id;

    // CONSTRUCTOR
    public DeleteBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    // METHOD TO INITIALIZE THE DELETE BOOK WINDOW
    private void initialize() {
        // PROMPT USER FOR BOOK ID
        String res = JOptionPane.showInputDialog("Book Id:");
        id = Integer.parseInt(res);
        // PERFORM ACTION TO DELETE BOOK
        performAction();
    }

    // METHOD TO PERFORM THE DELETE BOOK ACTION
    private void performAction() {
        try {
            // CREATE DELETE BOOK COMMAND AND EXECUTE
            Command deleteBook = new DeleteBook(id);
            deleteBook.execute(mw.getLibrary(), LocalDate.now());
            // DISPLAY UPDATED BOOK LIST
            mw.displayBooks();
        } catch (LibraryException e) {
            // HANDLE LIBRARY EXCEPTION
            e.printStackTrace();
        }
    }
}
