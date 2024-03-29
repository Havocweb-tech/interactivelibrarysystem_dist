
package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PatronDataManager implements DataManager {

    // RESOURCE FILE PATH
    private final String RESOURCE = "./resources/data/patrons.txt";
    
    // OVERRIDE loadData METHOD
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    // PARSE DATA FROM FILE AND CREATE PATRON OBJECT
                    int id = Integer.parseInt(properties[0]);
                    String name = properties[1];
                    String phone = properties[2];
                    String email = properties[3];
                    Patron patron = new Patron(id, name, phone, email);
                    
                    // ADD PATRON TO LIBRARY
                    library.addPatron(patron);
                } catch (NumberFormatException ex) {
                    throw new LibraryException("Unable to parse Patron id " + properties[0] + " on line " + line_idx
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
            // ITERATE THROUGH PATRONS IN LIBRARY
            for (Patron patron : library.getPatrons()) {
                // WRITE PATRON DATA TO FILE
                out.print(patron.getId() + SEPARATOR);
                out.print(patron.getName() + SEPARATOR);
                out.print(patron.getPhone() + SEPARATOR);
                out.print(patron.getEmail() + SEPARATOR);
                out.println();
            }
        }
    }
}
