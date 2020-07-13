package Roles;

import java.awt.Color;

import javax.swing.JOptionPane;

import H8.Game;
import H8.Player;

public class LoneWolf extends Role{

	public LoneWolf(){
		team = 1;
	}
	
	public void execute() {
		int input = JOptionPane.showConfirmDialog(null, "Was the Wolf Cub hanged?", null, JOptionPane.YES_NO_OPTION);
		if (input == 0) {
			prompt("KILL", 1);
		}
		prompt("KILL", 1);

		Game.panels.get(p.index).setBackground(Color.RED);
		for (Player p : Game.players) {
			if (Game.wolves.contains(p)) {
				p.roleA.awoken = true;
				Game.panels.get(p.index).p2.setBackground(Color.RED);
			}
		}
	}
}
