
package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.IssueBook;
import bcu.cmp5332.librarysystem.commands.RenewBook;
import bcu.cmp5332.librarysystem.commands.ReturnBook;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class RenewBookWindow extends JFrame implements ActionListener {

    // INSTANCE VARIABLES
    private MainWindow mw;
    private JTextField patronIdField = new JTextField();
    private JTextField bookIdField = new JTextField();

    private JButton renewBtn = new JButton("Renew");
    private JButton cancelBtn = new JButton("Cancel");

    // CONSTRUCTOR
    public RenewBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    // METHOD TO INITIALIZE THE RENEW BOOK WINDOW
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Renew Book");

        setSize(300, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Patron ID : "));
        topPanel.add(patronIdField);
        topPanel.add(new JLabel("Book ID : "));
        topPanel.add(bookIdField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(renewBtn);
        bottomPanel.add(cancelBtn);

        renewBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    // OVERRIDE actionPerformed METHOD
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == renewBtn) {
            renewBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    // METHOD TO HANDLE RENEW BOOK ACTION
    private void renewBook() {
        try {
            // PARSE USER INPUT
            int bookId = Integer.parseInt(bookIdField.getText());
            int patronId = Integer.parseInt(patronIdField.getText());
            
            // CREATE AND EXECUTE THE RENEWBOOK COMMAND
            Command renewBook = new RenewBook(patronId, bookId);
            renewBook.execute(mw.getLibrary(), LocalDate.now());
            
            // REFRESH THE VIEW WITH THE LIST OF BOOKS
            mw.displayBooks();
            
            // HIDE (CLOSE) THE RENEWBOOKCOMMAND
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
