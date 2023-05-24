package librarysystem.panels;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;

public class Message extends JPanel {
	private String message = "";

	/**
	 * Create the panel.
	 */
	public Message() {
		setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(170, 121, 65),
				new Color(170, 121, 65), new Color(170, 121, 65), new Color(170, 121, 65)), null));
		setBackground(new Color(254, 255, 255));

		JLabel lblNewLabel = new JLabel(getMessage());
		add(lblNewLabel);

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}