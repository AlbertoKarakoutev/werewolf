package H8;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import Roles.Role;

public class GameFrame{

	private static JFrame j = new JFrame("Werewolves");
	public static JFrame summary = new JFrame("ON THIS NIGHT");
	static JPanel panel = new JPanel();
	private final static JPanel panels = new JPanel();
	public static JLabel n = new JLabel();

	Font f = new Font("Rockwell Condensed", Font.BOLD, 22);
	
	public static final JLabel lkill = new JLabel();
	public static final JLabel lbg = new JLabel();
	public static final JLabel lvamp = new JLabel();
	public static final JLabel lcult = new JLabel();
	public static final JLabel lhood = new JLabel();
	public static final JLabel ldg = new JLabel();
	public static final JLabel lcupid = new JLabel();
	public static final JLabel lwitchK = new JLabel();
	public static final JLabel lwitchH = new JLabel();
	public static final JLabel lhag = new JLabel();
	public static final JLabel lsc = new JLabel();
	public static final JLabel ltm = new JLabel();

	private final JLabel[] constLabels = new JLabel[20];
	
	private static BufferedImage img;
	private static Image imgScaled;
	
	//Main game frame and summaryFrame()
	public GameFrame(){

		j.setSize(1000, 1000);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setLocationRelativeTo(null);
		j.getContentPane().setLayout(null);
		j.setResizable(false);
		
		try {
			img = ImageIO.read(GameFrame.class.getResource("/wwbg.jpg"));
			imgScaled = img.getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel ilab = new JLabel();
		ilab.setLocation(0, 0);
		ilab.setSize(1000, 1000);
		j.getContentPane().add(ilab);
		ilab.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ilab.setIcon(new ImageIcon(imgScaled));
		
		panel.setBounds(0, 0, 1000, 1000);
		j.getContentPane().setBounds(0, 0, 1000, 1000);
		j.getContentPane().add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 12, 40);
		panel.setLayout(fl_panel);

		summaryFrame();

	}
	
	//Night actions summary frame
	public void summaryFrame(){
		
		summary.setResizable(false);
		
		constLabels[0] = new JLabel("KILLED:");
		constLabels[1] = new JLabel("HEALED:");
		constLabels[2] = new JLabel("VAMPIRED:");
		constLabels[3] = new JLabel("CULT:");
		constLabels[4] = new JLabel("HOOD. TARGETS:");
		constLabels[5] = new JLabel("DOPPELGANGED:");
		constLabels[6] = new JLabel("SWEETHEARTS:");
		constLabels[7] = new JLabel("HAGGED:");
		constLabels[8] = new JLabel("SILENCED:");
		constLabels[9] = new JLabel("TROUBLEMAKER:");

		constLabels[10] = lkill;
		constLabels[11] = lbg;
		constLabels[12] = lvamp;
		constLabels[13] = lcult;
		constLabels[14] = lhood;
		constLabels[15] = ldg;
		constLabels[16] = lcupid;
		constLabels[17] = lhag;
		constLabels[18] = lsc;
		constLabels[19] = ltm;
		
		summary.setSize(400, 425);
		summary.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		summary.setLocationRelativeTo(null);
		summary.getContentPane().setLayout(null);
		panels.setBackground(Color.lightGray);
		panels.setBounds(0, 0, 398, 465);
		summary.getContentPane().add(panels);
		GridBagLayout gbl_panels = new GridBagLayout();
		gbl_panels.columnWidths = new int[] {180, 200};
		gbl_panels.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40};
		gbl_panels.columnWeights = new double[] { 0.0, 0.0 };
		gbl_panels.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panels.setLayout(gbl_panels);
	
		for(int i = 0; i < 10; i++){
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(0,0,5,5);
			gbc.gridx = 0;
			gbc.gridy = i;
			constLabels[i].setFont(f);
			panels.add(constLabels[i], gbc);
		}
		for(int i = 10; i < 20; i++){
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.insets = new Insets(0,0,5,5);
			gbc.gridx = 1;
			gbc.gridy = i-10;
			constLabels[i].setFont(f);
			panels.add(constLabels[i], gbc);
		}	
	}

	//Moderator control panel for game execution
	public static void control() {

		for (Role r : Game.unawokenRoles) {
			r.awoken = false;
		}
		JFrame fc = new JFrame("Moderator's Pad");
		JButton b = new JButton("NIGHT");
		JButton bw = new JButton("WAKE");
		JButton bd = new JButton("DAY");
		b.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		b.setSize(100, 50);
		b.setLocation(10, 10);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Game.day) {
					try {
						Game.night();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "It is still the previout night!");
				}
			}
		});

		bw.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		bw.setSize(100, 50);
		bw.setLocation(10, 10);
		bw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.wake();
			}
		});
		bd.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		bd.setSize(100, 50);
		bd.setLocation(10, 10);
		bd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Game.day();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		n.setFont(new Font("Rockwell Condensed", Font.BOLD, 15));
		n.setSize(200, 100);
		n.setLocation(150, 300);
		
		fc.getContentPane().add(n);
		fc.getContentPane().add(b);
		fc.getContentPane().add(bw);
		fc.getContentPane().add(bd);
		fc.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		fc.setSize(400, 80);
		fc.setUndecorated(true);
		((JComponent) fc.getContentPane()).setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, Color.BLACK, Color.BLACK));
		fc.setVisible(true);
		fc.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fc.setResizable(false);
		fc.setAlwaysOnTop(true);
		fc.setLocation(j.getX() + j.getWidth()/2 - fc.getWidth()/2, j.getY() + 4*j.getHeight()/5 + fc.getHeight()/2);
		fc.getContentPane().setBackground(Color.DARK_GRAY);
		

	}
	
	public void start() {
		j.setVisible(true);
	}
}
