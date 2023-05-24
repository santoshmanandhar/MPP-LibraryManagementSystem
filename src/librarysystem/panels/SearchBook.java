package librarysystem.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.Book;
import business.SystemController;

public class SearchBook extends JPanel {

	private JTextField searchBook;

	public SearchBook() {
		JLabel label = new JLabel("this is search book.");
		add(label);

		init();
	}

	public void init() {
		setBounds(100, 100, 589, 450);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Search Book");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 10, 232, 26);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ISBN ID\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(40, 46, 123, 26);
		this.add(lblNewLabel_1);

		searchBook = new JTextField();
		searchBook.setBounds(163, 46, 237, 25);
		this.add(searchBook);
		searchBook.setColumns(10);

		JButton addMember = new JButton("Search");
		addMember.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addMember.setBounds(450, 46, 223, 26);
		addMember.addActionListener(addLibraryMemberListener());
		this.add(addMember);

	}

	public ActionListener addLibraryMemberListener() {
		ActionListener addMemberListener = e -> {
			String search = searchBook.getText();

			boolean isSearchBook = SystemController.INSTANCE.addSearchBook(search);
			if(isSearchBook) {
				searchBook.setText("");
			}

		};
		return addMemberListener;
	}
}
