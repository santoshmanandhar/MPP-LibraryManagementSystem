package librarysystem.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import business.CheckoutRecord;
import business.CheckoutRecordEntry;
import business.LibraryMember;
import business.SystemController;
import dataaccess.DataAccessFacade;

public class SearchMember extends JPanel {

	private JTextField memberId;

	/**
	 * Create the panel.
	 */
	public SearchMember() {
		JLabel label = new JLabel("this is search member.");
		add(label);

		init();

	}

	public void init() {
		setBounds(100, 100, 589, 450);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setLayout(null);

		JLabel lblNewLabel = new JLabel("Search Memeber");
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

		JButton addMember = new JButton("Search");
		addMember.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addMember.setBounds(450, 46, 223, 26);
		addMember.addActionListener(addLibraryMemberListener());
		this.add(addMember);

	}

	public ActionListener addLibraryMemberListener() {
		ActionListener addMemberListener = e -> {
			String membId = memberId.getText();

			boolean isSearchMemberId = SystemController.INSTANCE.addCheckOutRecord(membId);
			if (isSearchMemberId){
				memberId.setText("");
			}

		};
		return addMemberListener;
	}

}
