package librarysystem.panels;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business.SystemController;

public class AllBookId extends JPanel {

	/**
	 * Create the panel.
	 */
	public AllBookId() {
		display();
	}

	public void display() {
		SystemController sc = new SystemController();
		List<String[]> table = sc.allBookIds();
		String[] columns = { "Book Id", "Book Title", "Number of Copies" };

		String[][] tableData = table.toArray(String[][]::new);
		JTable jt = new JTable(tableData, columns);
		JScrollPane jp = new JScrollPane();
		jt.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt.getColumnModel().getColumn(1).setPreferredWidth(200);
		jt.getColumnModel().getColumn(2).setPreferredWidth(100);
		add(new JScrollPane(jt));
	}

}
