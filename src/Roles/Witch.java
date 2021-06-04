package Roles;

import javax.swing.JOptionPane;

public class Witch extends Role {

	public Witch() {
		team = 0;
	}

	public void execute() {

		if (!p.witchKUsed) {
			int input = JOptionPane.showConfirmDialog(null, "Will the Witch kill someone?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				prompt("KILL", 11);
				p.witchKUsed = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "The Witch has already used her shot.");
		}
		if (!p.witchHUsed) {
			int input = JOptionPane.showConfirmDialog(null, "Will the Witch heal someone?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				prompt("HEAL", 10);
				p.witchHUsed = true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "The Witch has already used her shot.");
		}
	}
}
