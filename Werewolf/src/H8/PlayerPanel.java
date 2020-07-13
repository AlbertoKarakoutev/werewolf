package H8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Roles.Role;

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public int index;

	boolean infoComplete = false;

	int w = 150;
	int h = 250;
	int imgh = (int) ((w - 20) * Math.sqrt(2) + 10);
	int imgw = 130;
	int simgh = imgh - 24;
	int simgw = imgw - 20;
	int ssimgh = imgh - 30;
	int ssimgw = imgw - 26;
	public JPanel p2 = new JPanel();
	public JPanel p3 = new JPanel();
	public JPanel p4 = new JPanel();
	public JLabel nla = new JLabel();
	public JLabel nlp = new JLabel();
	public JLabel namel = new JLabel();
	public JButton b = new JButton("Info");
	public JButton kill = new JButton();
	Dimension d = new Dimension(150, 250);
	Selection select = new Selection();
	BufferedImage image;
	BufferedImage imagep;
	Image imageScaled;
	Image imageScaledp;
	Font f = new Font("Rockwell Condensed", Font.BOLD, 18);
	Font f2 = new Font("Rockwell Condensed", Font.BOLD, 5);

	public PlayerPanel() {

		nla.setSize(imgw, imgh);
		nlp.setSize(ssimgw, ssimgh);

		p2.setPreferredSize(d);
		p2.setVisible(true);
		p2.setLayout(null);

		p2.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, Color.BLACK, Color.BLACK));

		p3.setLayout(null);
		p3.setVisible(true);
		p3.setSize(imgw, imgh);
		p3.setLocation(10, 10);
		p3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, Color.BLACK, Color.BLACK));
		p2.add(p3);

		p4.setLayout(null);
		p4.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
		p4.setVisible(true);
		p4.setSize(simgw, simgh);
		p4.setLocation(0, 0);
		p3.add(p4);
		
		nla.setFont(f);
		nla.setLocation(0, 0);
		p3.add(nla);

		nlp.setFont(f);
		nlp.setLocation(3, 3);
		p4.add(nlp);

		namel.setHorizontalAlignment(JLabel.CENTER);
		namel.setFont(f);
		namel.setForeground(Color.lightGray);
		namel.setSize(imgw, 30);
		namel.setLocation(10, 30 + imgh);
		namel.setVisible(true);
		p2.add(namel);

		kill.setSize(16, 16);
		kill.setLocation(w - 17, imgh + 40);
		kill.setVisible(true);
		try {
			BufferedImage img = ImageIO.read(PlayerPanel.class.getResource("/x.png"));
			Image imgs = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
			kill.setIcon(new ImageIcon(imgs));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p2.add(kill);
		kill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				destroy(GameFrame.panel);
			}
		});
		b.setForeground(SystemColor.controlShadow);
		b.setBackground(new Color(64, 64, 64));

		b.setSize(100, 20);
		b.setLocation(w / 2 - 50, imgh + 15);
		b.setVisible(true);
		b.setFont(f);
		p2.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Selection.index = index;
				select.start();
			}
		});
		p2.setBackground(Color.WHITE);

	}

	public void destroy(JPanel panel) {
		p2.setVisible(false);
		panel.remove(p2);
		Game.panels.remove(this);
		Game.players.remove(this.index);
		if (Game.wolves.contains(Game.players.get(this.index))) {
			Game.wolves.remove(Game.players.get(this.index));
		}
		if (Game.vampires.contains(Game.players.get(this.index))) {
			Game.vampires.remove(Game.players.get(this.index));
		}
		Game.unawokenRoles.remove(Game.players.get(index).roleA);
		Game.unawokenRoles.remove(Game.players.get(index).roleP);
	}

	//Change panel in accordance to Active Role
	public static void update(Role r) {
		Color c = null;
		if (r.team != -1) {
			switch (r.team) {
			case 0:
				c = Color.BLUE;
				break;
			case 1:
				c = Color.RED;
				break;
			case 2:
				c = Color.GREEN;
				break;
			case 3:
				c = Color.PINK;
				break;
			default:
				c = Color.gray;
				break;
			}
		}
		for (Player p : Game.players) {
			if (p.roleA == r) {
				Game.panels.get(p.index).p2.setBackground(c);
				break;
			}
		}
	}

	public void addImageA() {
		try {
			image = ImageIO.read(PlayerPanel.class.getResource(Game.players.get(index).roleA.roleSprite));
			imageScaled = image.getScaledInstance(imgw, imgh, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addImageP() {
		try {
			imagep = ImageIO.read(PlayerPanel.class.getResource(Game.players.get(index).roleP.roleSprite));
			imageScaledp = imagep.getScaledInstance(ssimgw, ssimgh, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
