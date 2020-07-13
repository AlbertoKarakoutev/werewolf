package Roles;

import javax.swing.JOptionPane;

public class Huntress extends Role {

	public Huntress() {
		team = 0;
	}

	public void execute() {
		if (!p.huntressUsed) {
			int input = JOptionPane.showConfirmDialog(null, "Will the Huntress use her shot?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				prompt("KILL", 5);
				p.huntressUsed = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "The Huntress has already used her shot.");
		}
	}

}
