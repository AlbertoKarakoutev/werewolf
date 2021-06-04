package H8;

import java.util.ArrayList;

import Roles.Role;

public class Player {
    //Possible player effects
	public boolean healed, wolfed, priested, vampired, cult, huntressed, hagged, revealed, silenced, doppelganged, witchH, witchK, dead, hoodlumed, cupided;

	public boolean[] effects = new boolean[15];

	public int cultcounter = -2;
	public int cursecounter = -2;
	public int discounter = -2;
	public int tgcounter = -2;

	//Check used-up actions
	public boolean hunterUsed, witchHUsed, witchKUsed, huntressUsed, priestUsed, revealUsed, tmUsed;

	public static boolean tm = false;
	public int index;

	public PlayerPanel thisp;

	public String name;
	public Role roleA;
	public Role roleP;

	public static ArrayList<Player> sweethearts = new ArrayList<>();
	public static ArrayList<Player> hoodTargets = new ArrayList<>();
	
	public Player() {
		hunterUsed = false;
		witchHUsed = false;
		witchKUsed = false;
		huntressUsed = false;
		priestUsed = false;
		revealUsed = false;
		tmUsed = false;
		
		effects[0] = healed;
		effects[1] = wolfed;
		effects[2] = priested;
		effects[3] = vampired;
		effects[4] = cult;
		effects[5] = huntressed;
		effects[6] = hagged;
		effects[7] = revealed;
		effects[8] = silenced;
		effects[9] = doppelganged;
		effects[10] = witchH;
		effects[11] = witchK;
		effects[12] = hoodlumed;
		effects[13] = cupided;
		effects[14] = dead;
	}

}
