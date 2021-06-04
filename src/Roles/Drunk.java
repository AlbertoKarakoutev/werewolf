package Roles;

import javax.swing.JOptionPane;

import H8.Game;

public class Drunk extends Role {

	public Drunk() {
		team = 0;
	}

	public void execute() {

		if (Game.nights == 1) {
			JOptionPane.showMessageDialog(null, "The Drunk does not do anything yet.");
		} else if (Game.nights == 2) {
			String newR = JOptionPane.showInputDialog(null, "Enter new Role:");
			p.roleA.name = newR;
			p.thisp.addImageA();

		}
	}
}
