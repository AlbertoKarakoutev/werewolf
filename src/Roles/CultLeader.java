package Roles;

import javax.swing.JOptionPane;

import H8.Game;

public class CultLeader extends Role {

	public CultLeader() {
		team = 2;
	}

	public void execute() {
		JOptionPane.showMessageDialog(null, "Ask the Cult Leader to show you their target and tap the target's shoulder.");
		prompt("ADD TO THE CULT:", 4);
		Game.cult.add(p);
	}

}
