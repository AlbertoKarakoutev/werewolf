package Roles;

import javax.swing.JOptionPane;

public class ApprenticeSeer extends Role {

	public ApprenticeSeer() {
		team = 0;
	}

	public void execute() {

		int input = JOptionPane.showConfirmDialog(null, "Is the seer dead?", null, JOptionPane.YES_NO_OPTION);
		if (input == 0) {
			JOptionPane.showMessageDialog(null, "There is no seer. Consider the Apprentice Seer is now the Seer and inform them.");
		} else {
			JOptionPane.showMessageDialog(null, "The Apprentice Seer does nothing this night.");
		}
	}

}
