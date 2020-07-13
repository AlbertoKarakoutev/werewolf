package Roles;

import javax.swing.JOptionPane;

public class Minion extends Role {

	public Minion() {
		team = 1;
	}

	public void execute() {
		JOptionPane.showMessageDialog(null, "Inform the Minion who the Werewolves are!");
	}
}
