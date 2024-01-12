
package bcu.cmp5332.librarysystem.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;


public class LoanDataManager implements DataManager {
    
    // RESOURCE FILE PATH
    public final String RESOURCE = "./resources/data/loans.txt";

    // OVERRIDE loadData METHOD
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    // PARSE DATA FROM FILE AND CREATE LOAN OBJECT
                    int patronId = Integer.parseInt(properties[0]);
                    Patron patron = library.getPatronByID(patronId);
                    int bookId = Integer.parseInt(properties[1]);
                    Book book = library.getBookByID(bookId);
                    LocalDate issueDate = LocalDate.parse(properties[2]);
                    LocalDate dueDate = LocalDate.parse(properties[3]);
                    Loan loan = new Loan(patron, book, issueDate, dueDate);
                    
                    // SET LOAN TO BOOK AND ADD BOOK TO PATRON
                    book.setLoan(loan);
                    patron.addBook(book);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse patron id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                }
                line_idx++;
            }
        }
    }

    // OVERRIDE storeData METHOD
    @Override
    public void storeData(Library library) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
            // ITERATE THROUGH BOOKS IN LIBRARY
            for (Book book : library.getBooks()) {
                // CHECK IF BOOK IS ON LOAN
            	if (book.isOnLoan()) {
            		Loan loan = book.getLoan();
	                // WRITE LOAN DATA TO FILE
	                out.print(loan.getPatron().getId() + SEPARATOR);
	                out.print(loan.getBook().getId() + SEPARATOR);
	                out.print(loan.getStartDate() + SEPARATOR);
	                out.print(loan.getDueDate() + SEPARATOR);
	                out.println();
            	}
            }
        }
    }
}
