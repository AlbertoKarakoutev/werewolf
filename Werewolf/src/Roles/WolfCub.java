package Roles;

import java.awt.Color;

import H8.Game;
import H8.Player;

public class WolfCub extends Role{

	public WolfCub(){
		team = 1;
	}
	
	public void execute() {
		prompt("KILL", 1);
		for (Player p : Game.players) {
			if (Game.wolves.contains(p)) {
				p.roleA.awoken = true;
				Game.panels.get(p.index).p2.setBackground(Color.RED);
			}
		}
	}
	
}
