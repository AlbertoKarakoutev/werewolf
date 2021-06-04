package Roles;

import javax.swing.JOptionPane;

public class Priest extends Role {

	public Priest() {
		team = 1;
	}

	public void execute() {
		if (!p.priestUsed) {
			int input = JOptionPane.showConfirmDialog(null, "Will the Priest use her ability?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				prompt("SAVE", 2);
				p.priestUsed = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "The Priest has already used her shot.");
		}
	}

}
