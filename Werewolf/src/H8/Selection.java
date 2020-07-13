package H8;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Roles.Role;

public class Selection {
	static JFrame f = new JFrame("Please select a player");
	public static int index;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Selection() {

		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(600, 500);
		f.setLocationRelativeTo(null);

		f.getContentPane().setLayout(null);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBounds(296, 0, 8, 375);
		f.getContentPane().add(verticalStrut);

		final JComboBox roleA = new JComboBox();
		roleA.setModel(new DefaultComboBoxModel(new String[] { "", "AlfaWolf", "ApprenticeSeer", "AuraSeer", "Bodyguard", "CultLeader", "Drunk",
				"Huntress", "LoneWolf", "Minion", "MysticSeer", "OldHag", "Priest", "PI", "Revealer", "Seer", "Sorceress", "Spellcaster",
				"Troublemaker", "Vampire", "Villager", "Werewolf", "Witch", "WolfCub" }));
		roleA.setSelectedIndex(0);
		roleA.setBounds(33, 98, 231, 22);
		f.getContentPane().add(roleA);

		final JComboBox roleP = new JComboBox();
		roleP.setModel(new DefaultComboBoxModel(new String[] { "", "Cupid", "Cursed", "Diseased", "Doppelganger", "Hoodlum", "Hunter", "Lycan",
				"MadBomber", "Mason", "Mayor", "Prince", "Pacifist", "Tanner", "ToughGuy", "Villager", "VillageIdiot" }));
		roleP.setSelectedIndex(0);
		roleP.setBounds(33, 271, 231, 22);
		f.getContentPane().add(roleP);

		final JTextField name = new JTextField();
		name.setFont(new Font("Segoe UI Symbol", Font.BOLD, 18));
		name.setBounds(345, 164, 184, 47);
		f.getContentPane().add(name);
		name.setColumns(10);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 369, 582, 12);
		f.getContentPane().add(horizontalStrut);

		JButton b = new JButton("SAVE AND EXIT");
		b.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		b.setBounds(185, 394, 231, 46);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					setSprites(name, roleA, roleP);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				name.setText("");
				roleP.setSelectedIndex(0);
				roleA.setSelectedIndex(0);
			}
		});
		f.getContentPane().add(b);

		JLabel lblNewLabel = new JLabel("Active Role:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 18));
		lblNewLabel.setBounds(33, 48, 231, 36);
		f.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Passive Role:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Rockwell Condensed", Font.BOLD, 18));
		lblNewLabel_1.setBounds(33, 222, 231, 36);
		f.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Player Name");
		lblNewLabel_2.setFont(new Font("Rockwell Condensed", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(345, 115, 184, 36);
		f.getContentPane().add(lblNewLabel_2);
	}

	/*
	 * Finds player through index.
	 * Finds player roles via Class finder and adds appropriate sprite.
	 */
	public static void setSprites(JTextField name, JComboBox<String> roleA, JComboBox<String> roleP) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		
		Player thisPlayer = Game.players.get(index);
		PlayerPanel thisPanel = Game.panels.get(index);
		
		Game.players.get(index).name = name.getText();
		thisPanel.namel.setText(Game.players.get(index).name);
		try {
			Object ra = Class.forName("Roles." + roleA.getSelectedItem().toString()).newInstance();
			thisPlayer.roleA = (Role) ra;
			thisPlayer.roleA.p = thisPlayer;

			Object rp = Class.forName("Roles." + roleP.getSelectedItem().toString()).newInstance();
			thisPlayer.roleP = (Role) rp;
			thisPlayer.roleA.p = thisPlayer;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Please input a role!");
		}
		
		thisPlayer.roleA.name = roleA.getSelectedItem().toString();
		thisPlayer.roleA.roleSprite = "/" + thisPlayer.roleA.name + ".jpg";
		thisPlayer.roleP.name = roleP.getSelectedItem().toString();
		thisPlayer.roleP.roleSprite = "/" + thisPlayer.roleP.name + ".jpg";
		
		thisPanel.addImageA();
		thisPanel.addImageP();
		
		thisPanel.nla.setIcon(new ImageIcon(thisPanel.imageScaled));
		thisPanel.nlp.setIcon(new ImageIcon(thisPanel.imageScaledp));
		if (thisPlayer.name != "" && Game.players.get(index).roleA.name != "" && Game.players.get(index).roleP.name != "") {
			thisPanel.infoComplete = true;
		}
		if (thisPanel.infoComplete) {
			if (thisPlayer.roleA.name == "Werewolf" || Game.players.get(index).roleA.name == "Wolf Cub"
					|| Game.players.get(index).roleA.name == "Lone Wolf") {
				Game.wolves.add(thisPlayer);
			}

			if (thisPlayer.roleA.name == "Vampire") {
				Game.vampires.add(thisPlayer);
			}

			if (index == Game.panels.size() - 1) {
				GameFrame.control();
			}
		}
		f.dispose();
	}

	public void start() {
		f.setVisible(true);
	}
}
