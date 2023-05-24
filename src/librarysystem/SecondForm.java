package librarysystem;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.ListItem;
import librarysystem.panels.Content;
import librarysystem.panels.MenuList;
import librarysystem.panels.Message;

public class SecondForm extends JFrame {

	private String auth;
	private JPanel mainPanel;
	private JPanel contentPanel;

	// listItem
	ListItem addBookItem = new ListItem("Add Book", false);
	ListItem addBookCopyItem = new ListItem("Add Book Copy", false);

	ListItem[] librarianItems = { addBookItem };
	ListItem[] adminItems = { addBookCopyItem, addBookItem };
	ListItem[] allItems = { addBookCopyItem, addBookItem };

	public ListItem[] getAdminItems() {
		return adminItems;
	}

	public ListItem[] getAllItems() {
		return allItems;
	}

	public ListItem[] getLibrarianItems() {
		return librarianItems;
	}

	public void getItemsForUser(String auth) {
		if (auth == "LIBRARIAN")
			getLibrarianItems();
		if (auth == "ADMIN")
			getAdminItems();
		else
			getAllItems();

	}

	SecondForm(String auth) {

		this.auth = auth;
		getItemsForUser(this.auth);
		initialize();

		createMainPanels();

	}

	public void initialize() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(200, 200);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		setVisible(true);
	}

	public void createMainPanels() {
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel menuPanel = new MenuList(this, this.auth);
		mainPanel.add(menuPanel, BorderLayout.WEST);

		contentPanel = new Content(this.auth);
		mainPanel.add(contentPanel, BorderLayout.CENTER);

		JPanel messagePanel = new Message();
		mainPanel.add(messagePanel, BorderLayout.SOUTH);
	}

	public void test(String page) {

		((Content) contentPanel).addLayout(page);

		if (page.equalsIgnoreCase("Logout")) {
			new Login();
			dispose();
		}
	}

}
