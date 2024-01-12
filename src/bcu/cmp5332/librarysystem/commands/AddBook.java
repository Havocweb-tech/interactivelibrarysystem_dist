package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.data.LibraryData;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

public class AddBook implements  Command {

    private final String title;
    private final String author;
    private final String publicationYear;
    private final String Publisher;

    public AddBook(String title, String author, String publicationYear, String Publisher) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.Publisher = Publisher;
    }
    
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int maxId = 0;
    	if (library.getBooks().size() > 0) {
    		int lastIndex = library.getBooks().size() - 1;
            maxId = library.getBooks().get(lastIndex).getId();
    	}
    	// ADD BOOK INTO THE BOOK MODEL....
        Book book = new Book(++maxId, title, author, publicationYear, Publisher);
        try {
		    library.addBook(book);
		    LibraryData.store(library);
		    System.out.println("Book #" + book.getId() + " added.");
        } catch (IOException e) {
        	library.deleteBook(book.getId());
        	throw new LibraryException(FILE_ERROR_MESSAGE);
        }
    }
}
 