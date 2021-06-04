package Roles;

import javax.swing.JOptionPane;

import H8.Game;
import H8.Player;

public class Hunter extends Role{
	public void execute() {
		for(Player s: Game.players){
			if(s.roleP.name == "Hunter"){
				p = s;
			}
		}
		if (Game.nights == 1) {
			int input = JOptionPane.showConfirmDialog(null, "Will the hunter kill someone?", null, JOptionPane.YES_NO_OPTION);
			if (input == 0) {
				p.hunterUsed = true;
				prompt("KILL", 5);
			}
		}
	}

}
