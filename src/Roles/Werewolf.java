package Roles;

import java.awt.Color;

import javax.swing.JOptionPane;

import H8.Game;
import H8.Player;

public class Werewolf extends Role{

	public Werewolf(){
		team = 1;
	}
	
	public void execute() {
		int input = JOptionPane.showConfirmDialog(null, "Was the Wolf Cub hanged?", null, JOptionPane.YES_NO_OPTION);
		if (input == 0) {
			prompt("KILL", 1);
		}
		prompt("KILL", 1);
		for (Player p : Game.players) {
			if (Game.wolves.contains(p)) {
				p.roleA.awoken = true;
				Game.unawokenRoles.remove(p.roleA);
				Game.panels.get(p.index).p2.setBackground(Color.RED);
			}
		}

	}

	
}
