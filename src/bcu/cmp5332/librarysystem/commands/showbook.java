package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class showbook implements Command {
	private int Id;
	public showbook(int id) {
		this.Id = id;
	}
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        for (Book book : books) {
        	if(book.getId() == Id) {
        		System.out.println(book.getMyBook(Id));
        	}
            
            
        }
        
    }
}
 