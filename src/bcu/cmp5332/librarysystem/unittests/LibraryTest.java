package bcu.cmp5332.librarysystem.unittests;

import static org.junit.Assert.*;
import bcu.cmp5332.librarysystem.*;
import bcu.cmp5332.librarysystem.model.*;


import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;

public class LibraryTest {
	private Library library;
	
	@Before
	public void setUp() throws Exception {
		library = new Library();
	}

	
	@Test
    public void addingBookTest() {
        Book book = new Book(12345678, "Test Book", "Author", "2030", "PublisherTest");
        library.addBook(book);
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    public void gettiingBookTest() {
        List<Book> books = library.getBooks();
        assertNotNull(books);
        assertEquals(0, books.size());
    }
    @Test
    public void addingPatronTest() {
    	Patron patron = new Patron(12345678, "name me", "000000000", "email@sample.com");
    	library.addPatron(patron);
    	assertTrue(library.getPatrons().contains(patron));
    }
    
    @Test
    public void gettingPatronTest() {
        List<Patron> patrons = library.getPatrons();
        assertNotNull(patrons);
        assertEquals(0, patrons.size());
    }
    
    @After
	public void tearDown() throws Exception {
	}


}
