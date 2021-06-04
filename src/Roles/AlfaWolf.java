package Roles;

import javax.swing.JOptionPane;

public class AlfaWolf extends Role{

	public AlfaWolf(){
		team = 1;
	}
	
	public void execute() {
		
		int input = JOptionPane.showConfirmDialog(null, "Did a Werewolf die during the day?", null, JOptionPane.YES_NO_OPTION);
		if (input == 0) {
			JOptionPane
					.showMessageDialog(null,
							"Inform the Alfa Wolf that a Werewolf has died. They may choose to select the target of the Werewolves to become one, instead of dying.");
		} else {
			JOptionPane.showMessageDialog(null, "The Alfa Wolf does nothing this night.");
		}
	}

	
}
