package Roles;

import javax.swing.JOptionPane;

public class Sorceress extends Role {

	public Sorceress() {
		team = 1;
	}

	public void execute() {

		JOptionPane.showMessageDialog(null, "Inform the Seer of whether their target is a Seer!");
	}

}
