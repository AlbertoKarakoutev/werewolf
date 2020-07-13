package Roles;


public class Bodyguard extends Role {

	public Bodyguard() {
		team = 0;
	}

	public void execute() {
		prompt("HEAL", 0);
	}

}
