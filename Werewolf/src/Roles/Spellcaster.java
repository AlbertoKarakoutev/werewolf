package Roles;


public class Spellcaster extends Role {

	public Spellcaster() {
		team = 0;
	}

	public void execute() {
		prompt("SILENCED", 8);
	}
}
