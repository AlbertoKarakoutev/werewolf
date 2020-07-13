package Roles;

import javax.swing.JOptionPane;

public class PI extends Role {

	public PI() {
		team = 0;
	}

	public void execute() {

		JOptionPane.showMessageDialog(null, "Inform the PI whether his target or any of the adjacent players are Werewolves!");
	}
}
