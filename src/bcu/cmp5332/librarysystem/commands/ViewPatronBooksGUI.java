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

public class ViewPatronBooksGUI implements  Command {

    private final int patronId;
    public ViewPatronBooksGUI(int bookId) {
        this.patronId = bookId;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
    	
		JFrame tmpFrame = new JFrame();
		String[] columns = new String[]{"Id", "Title", "Issue Date", "Due Date"};
        List<Book> booksList = library.getPatronByID(patronId).getBooks();
        Object[][] data = new Object[booksList.size()][7];
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            data[i][0] = book.getId();
            data[i][1] = book.getTitle();
            data[i][2] = book.getLoan().getStartDate().toString();
            data[i][3] = book.getLoan().getDueDate().toString();
        }

        JTable table = new JTable(data, columns);
        tmpFrame.getContentPane().removeAll();
        tmpFrame.getContentPane().add(new JScrollPane(table));
        tmpFrame.revalidate();
        tmpFrame.pack();
		tmpFrame.setVisible(true);
    }
    
}
