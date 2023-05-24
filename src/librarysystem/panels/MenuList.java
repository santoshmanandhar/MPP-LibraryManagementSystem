package librarysystem.panels;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import librarysystem.SecondForm;

public class MenuList extends JPanel {
	public JList menuList;
	public SecondForm lib;
	public HashMap<String, String> options;

	/**
	 * Create the panel.
	 */
	public MenuList(SecondForm lib, String access) {
		this.lib = lib;
		setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(170, 121, 65), new Color(170, 121, 65),
				new Color(170, 121, 65), new Color(170, 121, 65)));
		setBackground(new Color(254, 255, 255));

		options = new HashMap<>();
		options.put("Checkout Book", "LIBRARIAN");
		options.put("Search Member", "LIBRARIAN");
		options.put("Search Book", "LIBRARIAN");
		options.put("All Book Id", "LIBRARIAN");
		options.put("All Member Id", "LIBRARIAN");

		options.put("Add Member", "ADMIN");
		options.put("Add Book", "ADMIN");
		options.put("Add Copy", "ADMIN");
		options.put("All Book Id", "ADMIN");
		options.put("All Member Id", "ADMIN");

		String[] items = getMenuList(access);
		System.out.println("items = " + Arrays.toString(items));
		menuList = new JList(items);
		menuList.setSelectedIndex(0);
		menuList.addListSelectionListener(event -> {
			lib.test(menuList.getSelectedValue().toString());
		});
		add(menuList);

	}

	public String[] getMenuList(String access) {
		if (access.equals("LIBRARIAN")) {
			String[] items = { "Checkout Book", "Search Member", "Search Book", "All Book Id", "All Member Id",
					"Logout" };
			return items;
		} else if (access.equals("ADMIN")) {
			String[] items = { "Add Member", "Add Book", "Add Book Copy", "All Book Id", "All Member Id", "Logout" };
			return items;
		} else {
			String[] items = { "Checkout Book", "Search Member", "Search Book", "Add Member", "Add Book",
					"Add Book Copy", "All Book Id", "All Member Id", "Logout" };
			return items;
		}
	}
}
