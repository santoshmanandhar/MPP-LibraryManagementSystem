package librarysystem.panels;

import business.Book;
import business.SystemController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddBookCopy extends JPanel {
	private JTextField isbn;

	/**
	 * Create the panel.
	 */
	public AddBookCopy() {
		init();
	}

	public void init() {
		setBounds(100, 100, 589, 450);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Book Copy");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 10, 232, 26);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter ISBN");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(40, 46, 123, 26);
		this.add(lblNewLabel_1);

		isbn = new JTextField();
		isbn.setBounds(163, 46, 237, 25);
		this.add(isbn);
		isbn.setColumns(10);

		JButton addBookCopy = new JButton("ADD BOOK COPY");
		addBookCopy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addBookCopy.setBounds(500, 46, 223, 26);
		addBookCopy.addActionListener(addBookCopyListener());
		this.add(addBookCopy);
	}

	public ActionListener addBookCopyListener() {
		ActionListener addBookCopyListener = e -> {
			String isbnString = isbn.getText();

			boolean isBookCopyAdded = SystemController.INSTANCE.addBookCopy(isbnString);
			if (isBookCopyAdded){
					isbn.setText("");
			}
		};
		return addBookCopyListener;
	}
}
