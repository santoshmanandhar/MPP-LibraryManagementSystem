package librarysystem.panels;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Book;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;

public class CheckoutBook extends JPanel {

	private JTextField memberId;
	private JTextField ISBN;

	/**
	 * Create the panel.
	 */
	public CheckoutBook() {
		JLabel label = new JLabel("this is check out  book.");
		add(label);

		init();

	}

	public void init() {
		setBounds(100, 100, 589, 450);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Check Out Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 10, 232, 26);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Member Id\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(40, 46, 123, 26);
		this.add(lblNewLabel_1);

		memberId = new JTextField();
		memberId.setBounds(163, 46, 237, 25);
		this.add(memberId);
		memberId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(40, 87, 95, 13);
		this.add(lblNewLabel_2);

		ISBN = new JTextField();
		ISBN.setBounds(163, 82, 237, 26);
		this.add(ISBN);
		ISBN.setColumns(10);

		JButton addMember = new JButton("Check Out");
		addMember.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addMember.setBounds(163, 138, 149, 26);
		addMember.addActionListener(addLibraryMemberListener());
		this.add(addMember);

	}

	public ActionListener addLibraryMemberListener() {
		ActionListener addMemberListener = e -> {
			String membId = memberId.getText();
			String isbn = ISBN.getText();

			boolean isBookCheckout = SystemController.INSTANCE.validateCheckoutBook(membId,isbn);
			if (isBookCheckout){
				memberId.setText("");
				ISBN.setText("");
			}

		};
		return addMemberListener;
	}

}
