package Roles;

import javax.swing.JOptionPane;

import H8.Player;

public class Troublemaker extends Role {

	public Troublemaker() {
		team = 0;
	}

	public void execute() {

		if (!p.tmUsed) {
			int input = JOptionPane.showConfirmDialog(null, "Will the Troublemaker use her ability?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				Player.tm = true;
				p.tmUsed = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "The Troublemaker has already used her shot.");
		}
	}
}
