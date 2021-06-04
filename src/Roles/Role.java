package Roles;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import H8.Game;
import H8.Player;

public class Role{
	public boolean awoken;

	public Player targetP;
	public Player p;

	public int team = -1;
	
	public String name;

	public String roleSprite;

	public Role(){

	}

	public void execute(){
	
	}
	
	//Target selector prompt.
	//The effect is inflicted depending on the player role and player name-check
	public void prompt(String s, final int ef) {
		final JFrame pf = new JFrame(this.name);
		pf.setVisible(true);
		pf.setSize(400, 300);
		pf.setLayout(null);
		pf.setLocationRelativeTo(null);
		pf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] pa = new String[Game.players.size()];
		for (int i = 0; i < Game.players.size(); i++) {
			pa[i] = Game.players.get(i).name;
		}

		final JComboBox<String> combox = new JComboBox<>();
		combox.setModel(new DefaultComboBoxModel<String>(pa));
		combox.setBounds(50, 80, 300, 50);
		pf.add(combox);
		pf.setAlwaysOnTop(true);

		JLabel l = new JLabel();
		l.setText("ENTER PLAYER TO " + s + ":");
		l.setFont(new Font("Rockwell Condensed", Font.BOLD, 18));
		l.setBounds(50, 50, 300, 30);
		l.setAlignmentX(JLabel.CENTER);
		pf.add(l);

		JButton b = new JButton("OKAY");
		b.setFont(new Font("Rockwell Condensed", Font.BOLD, 18));
		b.setBounds(150, 150, 100, 50);
		pf.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String target = combox.getSelectedItem().toString();
				for (int i = 0; i < Game.players.size(); i++) {
					if (Game.players.get(i).name.equals(target)) {
						targetP = Game.players.get(i);
						targetP.effects[ef] = true;
						//if(p.roleA.name == "CultLeader"){
						//     targetP.cultcounter = Game.nights;
         				//}
					}
				}
				pf.dispose();
			}
		});

	}

}
