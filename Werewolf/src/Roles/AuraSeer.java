package Roles;

import javax.swing.JOptionPane;

public class AuraSeer extends Role {

	public AuraSeer() {
		team = 0;
	}

	public void execute() {
		JOptionPane.showMessageDialog(null, "Inform the Aura Seer whether theis target is: Vampire and Cult Leader or other roles");

	}

}
