package Roles;

import H8.Player;

public class Cupid extends Role{
	
	public void execute() {
		prompt("BECOME A SWEETHEART", 13);
		Player.sweethearts.add(targetP);
		if (Player.sweethearts.size() == 1) {
			prompt("BECOME A SWEETHEART", 13);
			Player.sweethearts.add(targetP);
		}

	}

}
