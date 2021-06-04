package Roles;

import javax.swing.JOptionPane;

public class Seer extends Role {

	public Seer() {
		team = 0;
	}

	public void execute() {

		JOptionPane.showMessageDialog(null, "Inform the Seer of whether the their target is on the Village team or on the Werewolf team!");
	}
}
