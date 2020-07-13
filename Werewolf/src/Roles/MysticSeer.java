package Roles;

import javax.swing.JOptionPane;

public class MysticSeer extends Role {

	public MysticSeer() {
		team = 0;
	}

	public void execute() {

		JOptionPane.showMessageDialog(null, "Inform the Mystic Seer of the roles of their target!");
	}
}
