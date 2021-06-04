package H8;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Roles.Role;
import Roles.Werewolf;

public class Game {
	static int playerN;
	boolean loaded = false;
	public static int nights = 0;
	static boolean day = true;
	static boolean diskilled = false;

	public static ArrayList<Player> players = new ArrayList<>();
	public static ArrayList<PlayerPanel> panels = new ArrayList<>();
	public static ArrayList<Role> unawokenRoles = new ArrayList<>();
	public static ArrayList<Player> wolves = new ArrayList<>();
	public static ArrayList<Player> cult = new ArrayList<>();
	public static ArrayList<Player> vampires = new ArrayList<>();

	static GameFrame gf = new GameFrame();

	static LocalDateTime ldt = LocalDateTime.now();
	static String time;
	static String date;
	static String id;
	static String logDir_Path;
	static FileOutputStream fos;
	static File f;

	public Game() {
		time = String.format("%d%d%d", ldt.getSecond(), ldt.getHour(), ldt.getMinute());
		date = String.format("%d%d%d", ldt.getDayOfMonth(), ldt.getMonthValue(), ldt.getYear());
		
		id = time + "-" + date + ".txt";
		logDir_Path = System.getProperty("user.home") + "/Desktop/Werewolf Logs/";
		File logDir = new File(logDir_Path);
		if(!logDir.exists()){
			logDir.mkdirs();
		}
		
		init();
	}

	// Gets the number of players and starts the main Frame (GameFrame)
	// Creates a Log file, which will contain the game actions
	public void init() {

		final JFrame pf = new JFrame("Number of Players");
		JButton pb = new JButton("ENTER");
		final JTextField ptf = new JTextField();
		JLabel pl = new JLabel("Please enter number of players");
		
		pf.setSize(400, 300);
		pf.setLocationRelativeTo(null);
		pf.setVisible(true);
		pf.setLayout(null);
		pf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pf.add(pb);
		pb.setSize(100, 20);
		pb.setLocation(150, 120);
		pf.add(ptf);
		ptf.setSize(100, 20);
		ptf.setLocation(150, 80);
		pf.add(pl);
		pl.setLocation(100, 40);
		pl.setSize(400, 30);
		pl.setVisible(true);
		ptf.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(KeyEvent.VK_ENTER);
					try {

						playerN = Integer.parseInt(ptf.getText());
						

					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Please enter a number!");
					}
					try {
						makeLog();
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					addPlayers(playerN);
					pf.dispose();
					gf.start();
				}
			}
		});
		pb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					playerN = Integer.parseInt(ptf.getText());
					makeLog();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Please enter a number!");
				}
				addPlayers(playerN);
				pf.dispose();
				gf.start();
			}
		});
	}

	
	/* Checks the conditions in PlayerAbilities.conditions(), 
	 *in order to change the player attributes. Depending on the effect, 
	 *changes the view in accordance to the actions. 
	 *Displays a summary of the last night and checks end condition.
	 */
	public static void day() throws IOException {

		for (Player p : players) {

			PlayerAbilities.conditions(p);
		}

		if (unawokenRoles.size() == 0) {
			day = true;
			for (int i = 0; i < players.size(); i++) {

				panels.get(i).p2.setBackground(Color.LIGHT_GRAY);

				if (players.get(i).effects[0]) {
					panels.get(i).p2.setBackground(Color.YELLOW);
				}
				if (players.get(i).effects[3]) {
					panels.get(i).p2.setBackground(Color.PINK);
				}
				if (players.get(i).effects[6]) {
					panels.get(i).p2.setBackground(Color.BLACK);
				}

				if (players.get(i).roleP.name == "Hunter" && players.get(i).dead && !players.get(i).hunterUsed) {

					JOptionPane.showMessageDialog(null, "Hunter has died.");
					String s = JOptionPane.showInputDialog("Who will the Hunter kill?");

					for (Player hunted : players) {
						if (hunted.name.equals(s)) {
							panels.get(hunted.index).destroy(GameFrame.panel);
							break;
						}
					}
				}
			}
			summary();
			checkGameOver();
		} else {
			JOptionPane.showMessageDialog(null, "Not all roles have been woken up yet!");
		}
	}

	//Reset player effects. Check finalConditions()
	public static void night() throws IOException {


		String logNight = "Night: " + (nights + 1);
		fos.write("-------------------------------".getBytes());
		fos.write(System.getProperty("line.separator").getBytes());
		fos.write(logNight.getBytes());
		fos.write(System.getProperty("line.separator").getBytes());
		
		day = false;
		GameFrame.n.setText("Night: " + nights);
		for (Player p : players) {
			p.effects[0] = false;
			p.effects[1] = false;
			p.effects[3] = false;
			p.effects[5] = false;
			p.effects[6] = false;
			p.effects[8] = false;
			p.effects[10] = false;
			finalConditions(p);
		}
		nights++;
	}

	private static void makeLog() throws IOException {
		fos = new FileOutputStream(logDir_Path + id);
	}

	private static void log(String t, String a) throws IOException {
		fos.write((t + " was ").getBytes());
		fos.write((a + " ").getBytes());
		fos.write(System.getProperty("line.separator").getBytes());
	
	}

	public static void finalConditions(Player r) {

		if (nights == 0) {
			for (Player s : players) {
				boolean spec = s.roleP.name == "Cupid" || s.roleP.name == "Doppelganger" || s.roleP.name == "Hoodlum" || s.roleP.name == "Hunter"
						|| s.roleP.name == "Mason";
				if (s.roleA.name == "Werewolf" || s.roleA.name == "Wolf Cub" || s.roleA.name == "Lone Wolf") {
					if (!wolves.contains(s)) {
						wolves.add(s);
					}
				}
				if (spec && !unawokenRoles.contains(s.roleP)) {
					unawokenRoles.add(s.roleP);
				}
			}
			r.roleP.awoken = false;
		}
		unawokenRoles.add(r.roleA);
		r.roleP.awoken = false;

		if (r.effects[2]) {
			panels.get(r.index).p2.setBackground(Color.ORANGE);
		} else {
			panels.get(r.index).p2.setBackground(Color.DARK_GRAY);

		}
		if (r.roleA.name == "Cursed" && nights == r.cursecounter) {
			r.roleA = new Werewolf();
		}
		if (nights == r.cultcounter) {
			cult.add(r);
			r.effects[4] = true;
			r.roleA.team = 2;
		}
		if (r.roleP.name == "Tough Guy" && nights == r.tgcounter) {
			JOptionPane.showMessageDialog(null, "Tough guy dies on this night!");
			panels.get(r.index).destroy(GameFrame.panel);

			Game.unawokenRoles.remove(r.roleA);
		}
		if (diskilled) {
			JOptionPane.showMessageDialog(null, "The Werewolves shall not kill because of the Diseased");
			for (Player w : wolves) {
				unawokenRoles.remove(w.roleA);
			}
		}
	}

	/*In accordance with game mechanics, wakes up one or more players, 
	 * so they can execute their individual actions.
	 */
	public static void wake() {
		Random rand = new Random();
		Role r = null;
		try {
			r = unawokenRoles.get(rand.nextInt(unawokenRoles.size()));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Everyone has been awoken");
		}
		if (r != null) {
			PlayerAbilities.wake(r);
			unawokenRoles.remove(r);
		}
		if (wolves.contains(r)) {
			for (Player wolf : wolves) {
				unawokenRoles.remove(wolf);
			}
		}
		if (vampires.contains(r)) {
			for (Player vamp : vampires) {
				unawokenRoles.remove(vamp);
			}
		}

	}

	public void addPlayers(int p) {
		for (int i = 0; i < p; i++) {
			PlayerPanel pp = new PlayerPanel();
			Player pl = new Player();
			GameFrame.panel.add(pp.p2);
			panels.add(pp);
			players.add(pl);
			pp.index = i;
			pl.index = i;
		}
	}

	public static void checkGameOver() {
		for (Player p : players) {
			// Condition 1 (Tanner Wins)
			if (p.roleP.name == "Tanner" && p.dead) {
				gameOver("The Tanner has");
			}
			// Condition 2 (Hoodlum Wins)
			if(p.roleP.name == "Hoodlum"){
			for (int i = 0;i < Player.hoodTargets.size(); i++) {
				if (Player.hoodTargets.get(i).dead) {
					if(i==1)gameOver("The Hoodlum has");
					continue;
				}else{
					break;
				}
				
			}
			}
			
			// Condition 3 (Town Wins)
			if (p.roleA.team == 0) {
				boolean all = false;
				for (Player towns : players) {
					if (towns.roleA.team == 0) {
						all = true;
						continue;
					}
					all = false;
				}
				if (all) {
					gameOver("The Town has");
				}
			}
			// Condition 4 (Werewolves Win)
			if (p.roleA.team == 1) {
				boolean all = false;
				for (Player wolves : players) {
					if (wolves.roleA.team == 1) {
						all = true;
						continue;
					}
					all = false;
				}
				if (all) {
					gameOver("The Werewolves have");
				}
			}
			// Condition 5 (Cult Wins)
			if (p.roleA.team == 2) {
				boolean all = false;
				for (Player cults : players) {
					if (cults.roleA.team == 2) {
						all = true;
						continue;
					}
					all = false;
				}
				if (all) {
					gameOver("The Cult has");
				}
			}
			// Condition 6 (Vampires Win)
			if (p.roleA.team == 3) {
				boolean all = false;
				for (Player vamps : players) {
					if (vamps.roleA.team == 3) {
						all = true;
						continue;
					}
					all = false;
				}
				if (all) {
					gameOver("The Vampires have");
				}
			}
			// Condition 7 (Lone Wolf Wins)
			if (players.size() == 1) {
				for (Player lw : players) {
					if (lw.roleA.name.equals("LoneWolf")) {
						gameOver("The Lone Wolf has");
					}
				}
			}
		}
	}

	public static void gameOver(String s) {

		JOptionPane.showMessageDialog(null, s + " won");

	}

	public static void summary() throws IOException {

		for (Player p : players) {
			if (p.effects[14]) {
				GameFrame.lkill.setText(p.name);
				log(p.name, "killed.");
			}
			if (p.effects[0] || p.effects[10]) {
				GameFrame.lbg.setText(p.name);
				log(p.name, "healed.");
			}
			if (p.effects[3]) {
				GameFrame.lvamp.setText(p.name);
				log(p.name, "vampired.");
			}
			if (p.effects[4]) {
				GameFrame.lcult.setText(p.name);
				log(p.name, "added to the cult.");
			}
			if (!Player.hoodTargets.isEmpty()) {
				GameFrame.lhood.setText(Player.hoodTargets.get(0).name + ", " + Player.hoodTargets.get(1).name);
				log(Player.hoodTargets.get(0).name + " and " + Player.hoodTargets.get(1).name, "hoodlummed.");
			}
			if (p.effects[9]) {
				GameFrame.ldg.setText(p.name);
				log(p.name, "doppelganged.");
			}
			if (!Player.sweethearts.isEmpty()) {
				GameFrame.lcupid.setText(Player.sweethearts.get(0).name + ", " + Player.sweethearts.get(1).name);
				log(Player.sweethearts.get(0).name + " and " + Player.sweethearts.get(1).name, "picked for a sweetheart.");
			}
			if (p.effects[6]) {
				GameFrame.lhag.setText(p.name);
				log(p.name, "hagged.");
			}
			if (p.effects[8]) {
				GameFrame.lsc.setText(p.name);
				log(p.name, "silenced.");
			}
			if (Player.tm) {
				GameFrame.ltm.setText("YES");
			} else {
				GameFrame.ltm.setText("NO");
			}

		}

		GameFrame.summary.setVisible(true);
		
		for (int i = 0; i < panels.size(); i++) {
			if (players.get(i).effects[14]) {
				panels.get(i).destroy(GameFrame.panel);
			}
		}
	}

}
