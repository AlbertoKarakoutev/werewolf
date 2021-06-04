package H8;

import javax.swing.JOptionPane;

import Roles.Role;

public class PlayerAbilities{
	
	private static Player p;
	
	public PlayerAbilities() {
		
	}
	
	//On-player effects. Calculate actions after mutual exclusion.
	//Main game mechanics.
	public static void conditions(Player targetP) {
		
		if (targetP.effects[5] || targetP.effects[11]) {
			targetP.effects[14] = true;
		}

		if (targetP.effects[12]){
			Player.hoodTargets.add(targetP);
		}
		
		if (targetP.effects[1]) {
			if (targetP.roleP.name == "Cursed") {
				targetP.effects[14] = false;
				targetP.cursecounter = Game.nights;
			}

			if (targetP.roleP.name == "Tough Guy") {
				targetP.effects[14] = false;
				targetP.tgcounter = Game.nights;
			}

			if (targetP.roleP.name == "Diseased") {
				targetP.effects[14] = true;
				Game.diskilled = true;
				for (int i = 0; i < Game.wolves.size(); i++) {
					Game.wolves.get(i).discounter = Game.nights;
				}
			}
			targetP.effects[14] = true;
		}

		if (targetP.effects[10] || targetP.effects[0] || targetP.effects[2]) {
			targetP.effects[14] = false;
		}
		if (targetP.effects[7]) {
			targetP.effects[14] = true;
		}
		if (Player.sweethearts.size() > 1) {
			if (Player.sweethearts.get(0).effects[14]) {
				Player.sweethearts.get(1).effects[14] = true;

			} else if (Player.sweethearts.get(1).effects[14]) {
				Player.sweethearts.get(0).effects[14] = true;
			}
		}

		if (targetP.roleP.name == "Mad Bomber" && targetP.effects[14] == true) {
			JOptionPane.showMessageDialog(null, targetP.name + " was the Mad Bomber");
		}

		if (targetP.effects[14]) {
			if (targetP.effects[9]) {
				if (Game.wolves.contains(p)) {
					Game.wolves.remove(p);
				}
				p.roleA = targetP.roleA;
			}
		}
	}

	//Cupid sweethearts does not work
	public static void wake(Role r){
		PlayerPanel.update(r);
		r.execute();
	}
}
