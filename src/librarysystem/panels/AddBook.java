package librarysystem.panels;


import business.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AddBook extends JPanel {

	private final ControllerInterface sc = SystemController.INSTANCE;
	private JTextField isbn;
	private JTextField authorFirstName;
	private JTextField title;
	private JTextField checkoutLength;
	private JTextField textLastName;
	private JTextField authorPhone;

	private JTextField stateJText;
	private JTextField streetJText;
	private JTextField cityJText;
	private JTextField zipJText;

	public AddBook() {
		init();
	}

	/**
	 * Create the frame.
	 */
	public void init() {
		setBounds(100, 100, 538, 472);
		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("ADD BOOK");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 10, 191, 24);
		this.add(lblNewLabel);

		JLabel txtIsbn = new JLabel("ISBN\r\n");
		txtIsbn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIsbn.setBounds(54, 63, 95, 13);
		this.add(txtIsbn);

		isbn = new JTextField();
		isbn.setBounds(202, 62, 247, 26);
		this.add(isbn);
		isbn.setColumns(10);

		JLabel txtAuthorFN = new JLabel("Author First Name");
		txtAuthorFN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAuthorFN.setBounds(54, 140, 137, 13);
		this.add(txtAuthorFN);

		authorFirstName = new JTextField();
		authorFirstName.setBounds(202, 135, 247, 28);
		this.add(authorFirstName);
		authorFirstName.setColumns(10);

		JLabel txtTitle = new JLabel("Title");
		txtTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTitle.setBounds(54, 102, 95, 13);
		this.add(txtTitle);

		title = new JTextField();
		title.setBounds(202, 98, 247, 26);
		this.add(title);
		title.setColumns(10);

		JLabel txtCheckLength = new JLabel("Checkout Length");
		txtCheckLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCheckLength.setBounds(54, 267, 137, 24);
		this.add(txtCheckLength);

		checkoutLength = new JTextField();
		checkoutLength.setBounds(202, 259, 247, 32);
		this.add(checkoutLength);
		checkoutLength.setColumns(10);

		JButton addBookBtn = new JButton("ADD BOOK");
		addBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addBookBtn.setBounds(502, 490, 209, 29);
		addBookBtn.addActionListener(addBookListener());
		this.add(addBookBtn);

		JLabel txtAuthorLN = new JLabel("Author Last Name");
		txtAuthorLN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAuthorLN.setBounds(54, 178, 137, 24);
		this.add(txtAuthorLN);

		textLastName = new JTextField();
		textLastName.setBounds(202, 173, 247, 29);
		this.add(textLastName);
		textLastName.setColumns(10);

		JLabel txtAuthorPhone = new JLabel("Author Phone\r\n");
		txtAuthorPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAuthorPhone.setBounds(54, 216, 128, 26);
		this.add(txtAuthorPhone);

		authorPhone = new JTextField();
		authorPhone.setBounds(202, 212, 247, 34);
		this.add(authorPhone);
		authorPhone.setColumns(10);


		JLabel streetJLabel = new JLabel("Street\r\n");
		streetJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		streetJLabel.setBounds(54, 316, 128, 26);
		this.add(streetJLabel);

		streetJText = new JTextField();
		streetJText.setBounds(202, 312, 247, 34);
		this.add(streetJText);
		streetJText.setColumns(10);


		JLabel cityJLabel = new JLabel("City\r\n");
		cityJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cityJLabel.setBounds(54, 370, 128, 26);
		this.add(cityJLabel);

		cityJText = new JTextField();
		cityJText.setBounds(202, 370, 247, 34);
		this.add(cityJText);
		cityJText.setColumns(10);


		JLabel state = new JLabel("State\r\n");
		state.setFont(new Font("Tahoma", Font.PLAIN, 16));
		state.setBounds(54, 428, 128, 26);
		this.add(state);

		stateJText = new JTextField();
		stateJText.setBounds(202, 428, 247, 34);
		this.add(stateJText);
		stateJText.setColumns(10);


		JLabel zipJLabel = new JLabel("Zip\r\n");
		zipJLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		zipJLabel.setBounds(54, 486, 128, 26);
		this.add(zipJLabel);

		zipJText = new JTextField();
		zipJText.setBounds(202, 486, 247, 34);
		this.add(zipJText);
		zipJText.setColumns(10);

	}

	public ActionListener addBookListener() {
		ActionListener addBookListener = e -> {
			String bookIsbn = isbn.getText();
			String bookAuthorFirstName = authorFirstName.getText();
			String bookAuthorLastName = textLastName.getText();
			String authorPhoneNumber = authorPhone.getText();
			String bookTitle = title.getText();
			String checkoutLengthData = checkoutLength.getText();

			String zipString = zipJText.getText();
			String streetString = streetJText.getText();
			String cityString = cityJText.getText();
			String stateString = stateJText.getText();

			boolean isBookAdded = sc.validateAddBook(bookIsbn,bookAuthorFirstName,bookAuthorLastName, authorPhoneNumber,bookTitle,checkoutLengthData,zipString,streetString,cityString,stateString);

			if (isBookAdded){
				int bookCheckoutLength;

				try {
					bookCheckoutLength = Integer.parseInt(checkoutLengthData);

					Address address = new Address(streetString, cityString, stateString, zipString);
					Author author = new Author(bookAuthorFirstName, bookAuthorLastName, authorPhoneNumber, address,
							"He is Good.");
					Book book = new Book(bookIsbn, bookTitle, bookCheckoutLength, List.of(author));
					sc.addBook(bookIsbn, bookTitle, bookCheckoutLength, List.of(author));

					isbn.setText("");
					authorFirstName.setText("");
					textLastName.setText("");
					authorPhone.setText("");
					title.setText("");
					checkoutLength.setText("");
					stateJText.setText("");
					cityJText.setText("");
					stateJText.setText("");
					zipJText.setText("");
					streetJText.setText("");
					JOptionPane.showMessageDialog(AddBook.this, "Book Added", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(AddBook.this, "Checkout Length must be number!!!", "Error",
							JOptionPane.ERROR_MESSAGE);

				} catch (LibrarySystemException e1) {
					JOptionPane.showMessageDialog(AddBook.this, "Isbn Already Exist!!!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		return addBookListener;
	}

}