
package bcu.cmp5332.librarysystem.commands;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

public class ViewPatronBooksGUI implements Command {

    // INSTANCE VARIABLES
    private final int patronId;

    // CONSTRUCTOR
    public ViewPatronBooksGUI(int patronId) {
        this.patronId = patronId;
    }

    // OVERRIDE execute METHOD
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {

        // CREATE A NEW JFRAME
        JFrame tmpFrame = new JFrame();

        // DEFINE COLUMNS FOR THE JTABLE
        String[] columns = new String[]{"Id", "Title", "Issue Date", "Due Date"};

        // GET THE LIST OF BOOKS BORROWED BY THE PATRON
        List<Book> booksList = library.getPatronByID(patronId).getBooks();

        // CREATE A 2D ARRAY FOR JTABLE DATA
        Object[][] data = new Object[booksList.size()][4];

        // POPULATE THE DATA ARRAY
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            data[i][0] = book.getId();
            data[i][1] = book.getTitle();
            data[i][2] = book.getLoan().getStartDate().toString();
            data[i][3] = book.getLoan().getDueDate().toString();
        }

        // CREATE A JTABLE WITH THE PROVIDED DATA AND COLUMNS
        JTable table = new JTable(data, columns);

        // REMOVE ALL COMPONENTS FROM THE FRAME, ADD THE JTABLE INSIDE A SCROLLPANE, AND REVALIDATE
        tmpFrame.getContentPane().removeAll();
        tmpFrame.getContentPane().add(new JScrollPane(table));
        tmpFrame.revalidate();

        // PACK AND MAKE THE FRAME VISIBLE
        tmpFrame.pack();
        tmpFrame.setVisible(true);
    }
}
