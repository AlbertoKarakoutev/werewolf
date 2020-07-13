package Roles;

import javax.swing.JOptionPane;

import H8.Game;

public class Revealer extends Role {

	public Revealer() {
		team = 0;
	}

	public void execute() {
		if (!p.revealUsed) {
			int input = JOptionPane.showConfirmDialog(null, "Will the Revealer shoot?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				prompt("KILL", 7);
				p.revealUsed = true;
				if (Game.wolves.contains(targetP)) {
					prompt("KILL", 7);

				} else {
					JOptionPane.showMessageDialog(null, "Target was not a Werewolf.");
					p.dead = true;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "The Revealer has already used her shot.");
		}
	}
}
