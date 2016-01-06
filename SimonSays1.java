/**
 *Simon Says Java Application
 * 
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SimonSays1 extends JFrame implements ActionListener, MouseListener {

	static JLabel targ = new JLabel("TARGET Score:8");
	static JLabel beg = new JLabel("STATUS:BEGINNER ");
	static JLabel inter = new JLabel("STATUS:INTERMEDIATE ");
	static JLabel adv = new JLabel("STATUs:ADVANCED ");
	static JLabel lab1 = new JLabel("0");
	static JLabel[] levels = new JLabel[4];

	ImageIcon simage = new ImageIcon("images/sim1.gif");

	JLabel simagelabel = new JLabel(simage);
	Container con = getContentPane();

	static JPanel gamepane = new JPanel();
	static JPanel menupane = new JPanel();


	static JLabel button1 = new JLabel(new ImageIcon("images/green.png"));
	static JLabel button2 = new JLabel(new ImageIcon("images/red.png"));
	static JLabel button3 = new JLabel(new ImageIcon("images/blue.png"));
	static JLabel button4 = new JLabel(new ImageIcon("images/yellow.png"));

	static JButton start = new JButton("Start Game");
	static JButton help = new JButton("Help");
	static JButton quit = new JButton("Quit Game");
	static JButton back = new JButton("Back");

	static JPanel panel5 = new JPanel();
	static JPanel panel6 = new JPanel();
	static JPanel panel7 = new JPanel();
	static File SOUND1 = new File("sound4.wav");
	static File SOUND2 = new File("sound2.wav");
	static File SOUND3 = new File("sound3.wav");
	static File SOUND4 = new File("sound1.wav");

	static File OneUp = new File("sounds/synth_beep_3.wav");

	static int G = 1, B = 3, Y = 4, R = 2;
	private static final int BUFFER_SIZE = 256000;

	static int Levelno = 1;
	static int[] array = new int[256]; // maximum value
	static int difficulty = 1; // initial value
	static int count;
	static int x = 0;
	static int limit = 8;
	static boolean stopblink = false;
	static boolean enableplay = false;
	static boolean levelcomplete = false;
	static boolean mouseclicked = false;
	static boolean repeat = true;
	static boolean enabled = false;
	static boolean resetgame = false;

	public SimonSays1() {
		super("Simon Says");
		con.setLayout(null);
		panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
		targ.setFont(new Font("Serif", Font.BOLD, 20));
		targ.setForeground(Color.WHITE);
		beg.setFont(new Font("Serif", Font.BOLD, 20));
		beg.setForeground(Color.WHITE);
		inter.setFont(new Font("Serif", Font.BOLD, 20));
		inter.setForeground(Color.WHITE);
		adv.setFont(new Font("Serif", Font.BOLD, 20));
		adv.setForeground(Color.WHITE);
		gamepane.setLayout(null);
		menupane.setLayout(null);
		panel7.setLayout(null);
		gamepane.setBackground(Color.BLACK);
		panel7.setBackground(Color.BLACK);
		panel7.setBounds(0, 0, 500, 600);
		con.add(panel7);
		gamepane.setBounds(0, 0, 600, 600);
		con.add(gamepane);
		simagelabel.setBounds(50, 90, 500, 500);
		panel7.add(simagelabel);
		targ.setBounds(10, 20, 500, 100);
		gamepane.add(targ);
		beg.setBounds(10, 20, 500, 100);
		gamepane.add(beg);
		inter.setBounds(10, 20, 500, 100);
		gamepane.add(inter);
		adv.setBounds(10, 20, 500, 100);
		gamepane.add(adv);
		lab1.setBounds(250, 250, 100, 100);

		button1.setBounds(10, 50, 300, 300);
		button2.setBounds(220, 50, 300, 300);
		button3.setBounds(220, 250, 300, 300);
		button4.setBounds(10, 250, 300, 300);

		gamepane.add(button1);
		gamepane.add(button2);
		gamepane.add(button3);
		gamepane.add(button4);

		menupane.setBounds(620, 0, 380, 650);
		con.add(menupane);
		panel6.setBounds(620, 0, 380, 650);
		con.add(panel6);

		start.setPreferredSize(new Dimension(90, 50));
		quit.setPreferredSize(new Dimension(90, 50));

		gamepane.add(lab1);
		start.setBounds(150, 50, 150, 50);
		menupane.add(start);
		help.setBounds(150, 180, 150, 50);
		menupane.add(help);
		quit.setBounds(150, 310, 150, 50);
		menupane.add(quit);

		panel6.add(Box.createRigidArea(new Dimension(20, 60)));
		panel6.add(back);
		panel6.add(Box.createRigidArea(new Dimension(50, 60)));
		for (int i = 0; i < 4; i++) {
			if (i <= 2)
				levels[i] = new JLabel("Level" + (i + 1));
			else
				levels[3] = new JLabel("Level" + (i + 1) + "(BEAT YOUR OWN SCORE)");
			panel6.add(levels[i]);
			levels[i].setFont(new Font("Serif", Font.BOLD, 22));
		}

		lab1.setForeground(Color.GREEN);
		con.setBackground(Color.BLACK);

		panel5.setBackground(Color.BLACK);
		panel6.setBackground(Color.BLUE);
		button1.addMouseListener(this);
		button2.addMouseListener(this);
		button3.addMouseListener(this);
		button4.addMouseListener(this);

		start.addActionListener(this);
		help.addActionListener(this);
		quit.addActionListener(this);
		back.addActionListener(this);
		panel6.setVisible(false);
		gamepane.setVisible(false);

	}

	public static void main(String[] args) {
		JFrame f = new SimonSays1();
		f.setSize(1020, 700);
		Dimension frameSize = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation((frameSize.width - f.getWidth()) / 2, (frameSize.height - f.getHeight()) / 2);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(true);

		Levelload();
		beg.setVisible(false);
		inter.setVisible(false);
		adv.setVisible(false);
		levels[0].setFont(new Font("Serif", Font.ITALIC, 40));
		while (f.isVisible()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			if (enableplay == true) {
				if (resetgame) {
					resetgame = false;
					difficulty = 1;
					count = 0;
					x = 0;
					Levelno = 1;
					Levelload();
					targ.setVisible(true);
					beg.setVisible(false);
					inter.setVisible(false);
					adv.setVisible(false);
					for (int i = 0; i < 4; i++) {
						if (i == 0 && Levelno == 1)
							levels[0].setFont(new Font("Serif", Font.ITALIC, 40));
						else
							levels[i].setFont(new Font("Serif", Font.BOLD, 26));
					}
					startblink();
				}
				if (repeat) {
					repeat = false;
					lab1.setText(Integer.toString(difficulty));
					lab1.setFont(new Font("Arial", Font.BOLD, 25));
					startblink();
				}
				if (difficulty == limit + 1) {
					difficulty = 1;
					levelcomplete = true;
				}
				if (levelcomplete == true) {
					if (Levelno == 1 && levelcomplete == true) {
						difficulty = 1;
						count = 0;
						x = 0;
						Levelno++;
						levels[0].setFont(new Font("Serif", Font.BOLD, 26));
						levels[1].setFont(new Font("Serif", Font.ITALIC, 40));
						Levelload();
						levelcomplete = false;
						startblink();
					}

					if (Levelno == 2 && levelcomplete == true) {
						difficulty = 1;
						count = 0;
						x = 0;
						Levelno++;
						levels[1].setFont(new Font("Serif", Font.BOLD, 26));
						levels[2].setFont(new Font("Serif", Font.ITALIC, 40));
						Levelload();
						levelcomplete = false;
						startblink();
					}
					if (Levelno == 3 && levelcomplete == true) {
						targ.setVisible(false);
						difficulty = 1;
						count = 0;
						x = 0;
						Levelno++;
						levels[2].setFont(new Font("Serif", Font.BOLD, 26));
						levels[3].setFont(new Font("Serif", Font.ITALIC, 40));
						Levelload();
						levelcomplete = false;
						startblink();
					}
				}
				if (Levelno == 4) {
					if (difficulty > 0 && difficulty <= 8)
						beg.setVisible(true);
					if (difficulty > 8 && difficulty <= 16) {
						beg.setVisible(false);
						inter.setVisible(true);
					}
					if (difficulty > 16) {
						inter.setVisible(false);
						adv.setVisible(true);
					}
				}
			}

		}
	}

	public static int[] Levelload() {
		if (Levelno == 1) {
			array[0] = 1;
			array[1] = 2;
			array[2] = 3;
			array[3] = 4;
			array[4] = 4;
			array[5] = 3;
			array[6] = 2;
			array[7] = 1;
		} else if (Levelno == 2) {
			array[0] = 1;
			array[1] = 1;
			array[2] = 2;
			array[3] = 2;
			array[4] = 3;
			array[5] = 3;
			array[6] = 4;
			array[7] = 4;
		} else if (Levelno == 3) {
			array[0] = 1;
			array[1] = 3;
			array[2] = 4;
			array[3] = 2;
			array[4] = 1;
			array[5] = 3;
			array[6] = 1;
			array[7] = 4;
		} else if (Levelno == 4)
			randomload();
		return array;
	}

	public static int[] randomload() {
		for (int x = 0; x < array.length; ++x) {
			array[x] = (int) (Math.random() * 10);
			if (array[x] <= 2.5)
				array[x] = 1;
			else if (2.5 < array[x] && array[x] <= 5.0)
				array[x] = 2;
			else if (array[x] <= 7.5 && 5.0 < array[x])
				array[x] = 3;
			else // if (array[x]<=2.5)
				array[x] = 4;
		}
		return array;
	}

	public static void startblink() {
		enabled = false;
		for (x = 0; x < difficulty; x++) {
			if (array[x] == 1) {
				if (stopblink == false)
					playsound(SOUND1);
				button1.setIcon(new ImageIcon("images/lightgreen.png"));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				button1.setIcon(new ImageIcon("images/green.png"));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}

			} else if (array[x] == 2 && stopblink == false) {
				if (stopblink == false)
					playsound(SOUND2);
				button2.setIcon(new ImageIcon("images/lightred.png"));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				button2.setIcon(new ImageIcon("images/red.png"));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
			} else if (array[x] == 3 && stopblink == false) {
				if (stopblink == false)
					playsound(SOUND3);
				button3.setIcon(new ImageIcon("images/lightblue.png"));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				button3.setIcon(new ImageIcon("images/blue.png"));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			} else // (array[x]==4&&stopblink==false)
			{
				if (stopblink == false)
					playsound(SOUND4);
				button4.setIcon(new ImageIcon("images/lightyellow.png"));
				;
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
				button4.setIcon(new ImageIcon("images/yellow.png"));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
		enabled = true;
		if (x == difficulty)
			count = 0;
	}

	public static void blinkgreen() {
		playsound(SOUND1);
		button1.setIcon(new ImageIcon("images/lightgreen.png"));
	}

	public static void blinkblue() {
		playsound(SOUND3);
		button3.setIcon(new ImageIcon("images/lightblue.png"));
	}

	public static void blinkyellow() {
		playsound(SOUND4);
		button4.setIcon(new ImageIcon("images/lightyellow.png"));
	}

	public static void blinkred() {
		playsound(SOUND2);
		button2.setIcon(new ImageIcon("images/lightred.png"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		Object clickedbutton = e.getSource();
		if (clickedbutton == button1) {
			button1.setIcon(new ImageIcon("images/green.png"));
		} else if (clickedbutton == button2) {
			button2.setIcon(new ImageIcon("images/red.png"));
		} else if (clickedbutton == button3) {
			button3.setIcon(new ImageIcon("images/blue.png"));
		} else if (clickedbutton == button4) {
			button4.setIcon(new ImageIcon("images/yellow.png"));
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		if (enabled == true) {
			Object clickedbutton = e.getSource();
			if (clickedbutton == button1) {
				mouseclicked = true;
				System.out.println("mousePressed-button1-blinkgreen");
				blinkgreen();
				CheckSequence(G);
			} else if (clickedbutton == button2) {
				mouseclicked = true;
				System.out.println("mousePressed-button2-blinkred");
				blinkred();
				CheckSequence(R);
			} else if (clickedbutton == button3) {
				mouseclicked = true;
				System.out.println("mousePressed-button3-blinkblue");
				blinkblue();
				CheckSequence(B);
			} else {
				mouseclicked = true;
				System.out.println("mousePressed-button4-blinkyellow");
				blinkyellow();
				CheckSequence(Y);
			}
		}
	}

	public static void CheckSequence(int A) {

		if (x < difficulty) {

			System.out.println("Randomly generated array position " + count + "'s value is, " + A);
			++count;
		} else /* if (x==difficulty) */
		{
			System.out.println("button clicked by user " + count + "'s value is, " + A);
			if (A != array[count]) {
				playsound(OneUp);
				int retry = JOptionPane.showConfirmDialog(null, "YOU LOSE!!!\nTry Again?", "LOSER!!",
						JOptionPane.YES_NO_OPTION);

				if (retry == 0) {
					button1.setIcon(new ImageIcon("images/green.png"));
					button2.setIcon(new ImageIcon("images/red.png"));
					button3.setIcon(new ImageIcon("images/blue.png"));
					button4.setIcon(new ImageIcon("images/yellow.png"));
					resetgame = true;
					repeat = true;
				} else
					System.exit(0);
			}
			if (mouseclicked == true)
				count++;
			if (array[count - 1] == A && count == difficulty) {
				System.out.println("PATTERN INCREASE");
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
				}
				difficulty++;
				count = 0;
				x = 0;
				mouseclicked = false;
				if (difficulty <= limit || Levelno == 4)
					repeat = true;// breaks loop

			}
		}
	}

	public static void playsound(File audioFile) {
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(audioFile);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		AudioFormat audioFormat = audioInputStream.getFormat();
		SourceDataLine line = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		line.start();
		int nBytesRead = 0;
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				 int nBytesWritten = line.write(abData, 0, nBytesRead);
			}

		}
	}

	private void printUsageAndExit() {
		out("SimpleAudioPlayer: usage:");
		out("\tjava SimpleAudioPlayer <soundfile>");
		System.exit(1);
	}

	private void out(String strMessage) {
		System.out.println(strMessage);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		Object clickedbutton = e.getSource();
		if (clickedbutton == start) {
			enableplay = true;
			stopblink = false;
			panel7.setVisible(false);
			menupane.setVisible(false);
			gamepane.setVisible(true);
			panel6.setVisible(true);
		} else if (clickedbutton == help) {
			JOptionPane.showMessageDialog(null,
					"Memorize the sequence of blinking buttons.\n" + "And press them in same order.", "Help",
					JOptionPane.PLAIN_MESSAGE);
		} else if (clickedbutton == quit) {
			System.exit(0);
		} else if (clickedbutton == back) {
			stopblink = true;
			enableplay = false;
			resetgame = true;
			gamepane.setVisible(false);
			panel6.setVisible(false);
			menupane.setVisible(true);
			panel7.setVisible(true);
		}

	}

}

