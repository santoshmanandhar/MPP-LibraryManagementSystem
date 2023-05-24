package librarysystem.panels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;

public class AllMemberId extends JPanel {
	JTable jt;

	/**
	 * Create the panel.
	 */
	public AllMemberId() {
		display();
	}

	public void display() {
		String[] columns = { "Member Id", "First Name", "Last Name" };
		SystemController sc = new SystemController();
		List<String[]> table = sc.allMemberIds();
		String[][] tableData = table.toArray(String[][]::new);
		jt = new JTable(tableData, columns);

		jt.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt.getColumnModel().getColumn(1).setPreferredWidth(100);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);
		add(new JScrollPane(jt));
	}
}
