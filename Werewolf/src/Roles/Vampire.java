package Roles;

public class Vampire extends Role {

	public Vampire() {
		team = 3;
	}

	public void execute() {
		prompt("VAMP", 3);
	}

}
