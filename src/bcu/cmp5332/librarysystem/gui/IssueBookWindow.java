
package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddBook;
import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.IssueBook;
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

public class IssueBookWindow extends JFrame implements ActionListener {

    // INSTANCE VARIABLES
    private MainWindow mw;
    private JTextField bookIdField = new JTextField();
    private JTextField patronIdField = new JTextField();
    private JTextField dueDateField = new JTextField();

    private JButton issueBtn = new JButton("Issue");
    private JButton cancelBtn = new JButton("Cancel");

    // CONSTRUCTOR
    public IssueBookWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    // METHOD TO INITIALIZE THE ISSUE BOOK WINDOW
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Issue Book");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("Book ID : "));
        topPanel.add(bookIdField);
        topPanel.add(new JLabel("Patron ID : "));
        topPanel.add(patronIdField);
        topPanel.add(new JLabel("Due Date (YYYY-MM-DD): "));
        topPanel.add(dueDateField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(issueBtn);
        bottomPanel.add(cancelBtn);

        issueBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    // OVERRIDE actionPerformed METHOD
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == issueBtn) {
            issueBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    // METHOD TO HANDLE ISSUE BOOK ACTION
    private void issueBook() {
        try {
            // PARSE USER INPUT
            int bookId = Integer.parseInt(bookIdField.getText());
            int patronId = Integer.parseInt(patronIdField.getText());
            LocalDate dueDate = LocalDate.parse(dueDateField.getText());
            
            // CREATE AND EXECUTE THE ISSUEBOOK COMMAND
            Command issueBook = new IssueBook(bookId, patronId, dueDate);
            issueBook.execute(mw.getLibrary(), LocalDate.now());
            
            // REFRESH THE VIEW WITH THE LIST OF BOOKS
            mw.displayBooks();
            
            // HIDE (CLOSE) THE ISSUEBOOKWINDOW
            this.setVisible(false);
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
