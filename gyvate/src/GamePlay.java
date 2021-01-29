import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private int score = 0;
	private int lengthofsnake = 3;
	private int moves = 0;

	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;

	private Timer timer;

	// SPEEDas
	private int delay = 100;

	private ImageIcon SnakeImage;

	private int[] enemyxpos = { 25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450,
			475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850 };
	private int[] enemyypos = { 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500,
			525, 550, 575, 600, 625 };

	private ImageIcon enemyimage;

	// Random prieso koord
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);

	private ImageIcon titleImage;
	
	private String names;

	public GamePlay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
		//Ivedas VARDA
		names = JOptionPane.showInputDialog(null, "Jusu vardas", "Ivekit savo varda", JOptionPane.PLAIN_MESSAGE);
		
	}

	public void paint(Graphics q) {
		
		
		if (moves == 0) {
			snakeXlength[2] = 50;
			snakeXlength[1] = 75;
			snakeXlength[0] = 100;

			snakeYlength[2] = 100;
			snakeYlength[1] = 100;
			snakeYlength[0] = 100;
		}

		// remelis RED ir tekstas

		q.setColor(Color.RED);
		q.drawRect(24, 10, 851, 55);

		q.setColor(Color.BLUE);
		q.setFont(new Font("arial", Font.BOLD, 33));
		q.drawString("Gyvatele ", 30, 47);
		
		//btn STATISTIKA
		q.setColor(Color.WHITE);
		q.drawRect(590, 20, 140, 30);
		
		q.setColor(Color.WHITE);
		q.setFont(new Font("arial", Font.PLAIN, 14));
		q.drawString("Statistika Spausti S ", 595, 40);
		
		
		
		// image remelyje
//		titleImage = new ImageIcon("src/resources/apple_16.png");
//		titleImage = new ImageIcon(getClass().getClassLoader().getResource("resources/apple_16.png"));
//		titleImage.paintIcon(this, q, 32, 32);

		// gamepad remelis
		q.setColor(Color.WHITE);
		q.drawRect(24, 74, 851, 566);

		// gamepad laukas
		q.setColor(Color.BLACK);
		q.fillRect(25, 75, 850, 565);

		// fonas TASKU DARK_GRAY
		q.setColor(Color.DARK_GRAY);
		q.fillRect(740, 11, 122, 54);

		// rasom SCORE
		q.setColor(Color.RED);
		q.setFont(new Font("arial", Font.PLAIN, 14));
		q.drawString("Taskai: " + score, 740, 30);

		// ilgis GYVATEs
		q.setColor(Color.GREEN);
		q.setFont(new Font("arial", Font.PLAIN, 14));
		q.drawString("Gyvateles ilgis: " + lengthofsnake, 740, 50);

		// ideda galva gyvates
		rightmouth = new ImageIcon(getClass().getClassLoader().getResource("resources/snake_16.png"));
		rightmouth.paintIcon(this, q, snakeXlength[0], snakeYlength[0]);

		for (int a = 0; a < lengthofsnake; a++) {
			if (a == 0 && right) {
				rightmouth = new ImageIcon(getClass().getClassLoader().getResource("resources/snake_16.png"));
				rightmouth.paintIcon(this, q, snakeXlength[a], snakeYlength[a]);
			}
			if (a == 0 && left) {
				leftmouth = new ImageIcon(getClass().getClassLoader().getResource("resources/snake_16.png"));
				leftmouth.paintIcon(this, q, snakeXlength[a], snakeYlength[a]);
			}
			if (a == 0 && up) {
				upmouth = new ImageIcon(getClass().getClassLoader().getResource("resources/snake_16.png"));
				upmouth.paintIcon(this, q, snakeXlength[a], snakeYlength[a]);
			}
			if (a == 0 && down) {
				downmouth = new ImageIcon(getClass().getClassLoader().getResource("resources/snake_16.png"));
				downmouth.paintIcon(this, q, snakeXlength[a], snakeYlength[a]);
			}

			if (!(a == 0)) {
				SnakeImage = new ImageIcon(getClass().getClassLoader().getResource("resources/circlBody_16.png"));
				SnakeImage.paintIcon(this, q, snakeXlength[a], snakeYlength[a]);
			}
		}

		// ideda OBUOLI
		// enemyimage = new ImageIcon("src/resources/apple_16.png");
		enemyimage = new ImageIcon(getClass().getClassLoader().getResource("resources/apple_16.png"));

		// Skaiciuoja taskus ir sueda enemy
		if (enemyxpos[xpos] == snakeXlength[0] && enemyypos[ypos] == snakeYlength[0]) {
			score++;
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);

		}
		
		enemyimage.paintIcon(this, q, enemyxpos[xpos], enemyypos[ypos]);

		// atsitrenki i save GAME OVER
		for (int b = 1; b < lengthofsnake; b++) {
			if (snakeXlength[b] == snakeXlength[0] && snakeYlength[b] == snakeYlength[0]) {
				left = false;
				right = false;
				up = false;
				down = false;

				repaint();

				// fonas Raudonas
				q.setColor(Color.RED);
				q.fillRect(25, 75, 850, 565);

				q.setColor(Color.WHITE);
				q.setFont(new Font("arial", Font.BOLD, 50));
				q.drawString("GAME OVER", 300, 300);

				q.setFont(new Font("arial", Font.BOLD, 20));
				q.drawString("Space to RESTART", 350, 340);

				q.setFont(new Font("arial", Font.BOLD, 20));
				q.drawString("Jusu taskai: " + "\n " + score, 370, 380);
				
			}

			// atsitrenki i siena ir GAME OVER NEVEIKIA
//			else if (snakeXlength[0] < 0 || snakeXlength[0] > 850 || snakeYlength[0] < 0 || snakeYlength[0] > 625) {
//
//				left = false;
//				right = false;
//				up = false;
//				down = false;
//
//				repaint();
//				q.setColor(Color.RED);
//				q.fillRect(25, 75, 850, 565);
//
//				q.setColor(Color.WHITE);
//				q.setFont(new Font("arial", Font.BOLD, 50));
//				q.drawString("GAME OVER", 300, 300);
//
//				q.setFont(new Font("arial", Font.BOLD, 20));
//				q.drawString("Space to RESTART", 350, 340);
//
//				q.setFont(new Font("arial", Font.BOLD, 20));
//				q.drawString("Jusu taskai: " + "\n " + score, 350, 380);
//			}


		}

		q.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if (right) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakeYlength[r + 1] = snakeYlength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeXlength[r] = snakeXlength[r] + 25;

				} else {
					snakeXlength[r] = snakeXlength[r - 1];
				}
				if (snakeXlength[r] > 850) {
					snakeXlength[r] = 25;
				}
			}
			repaint();
		}
		if (left) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakeYlength[r + 1] = snakeYlength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeXlength[r] = snakeXlength[r] - 25;

				} else {
					snakeXlength[r] = snakeXlength[r - 1];
				}
				if (snakeXlength[r] < 25) {
					snakeXlength[r] = 850;
				}
			}
			repaint();

		}
		if (up) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakeXlength[r + 1] = snakeXlength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeYlength[r] = snakeYlength[r] - 25;

				} else {
					snakeYlength[r] = snakeYlength[r - 1];
				}
				if (snakeYlength[r] < 75) {
					snakeYlength[r] = 625;
				}
			}
			repaint();

		}
		if (down) {
			for (int r = lengthofsnake - 1; r >= 0; r--) {
				snakeXlength[r + 1] = snakeXlength[r];
			}
			for (int r = lengthofsnake; r >= 0; r--) {
				if (r == 0) {
					snakeYlength[r] = snakeYlength[r] + 25;

				} else {
					snakeYlength[r] = snakeYlength[r - 1];
				}
				if (snakeYlength[r] > 625) {
					snakeYlength[r] = 75;
				}
			}
			repaint();

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_S) {
			
			//STATISTIKA
			
			Scanner skaiciuoja;
			int ctr = 0;
			
			//Suskaiciuoja visus zodzius
			try {
				skaiciuoja = new Scanner(new File("taskai.txt"));
				while (skaiciuoja.hasNextLine()) {
						ctr++;
						skaiciuoja.nextLine();
				}	
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
			
			//Ideda zodzius i masyva
			String [] nameStrings = new String[ctr];  
			Scanner ideda;
			try {
				ideda = new Scanner(new File("taskai.txt"));
				for (int i = 0; i < nameStrings.length; i++) {
					
					nameStrings[i] = ideda.nextLine();	
				}
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, nameStrings, "Statistika", JOptionPane.PLAIN_MESSAGE);
			
			
			
		}
		

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			//Issaugo i TEXT file  SCORE
	        try{
	            File file = new File("taskai.txt");

	            if (!file.exists()){
	                file.createNewFile();
	            }

	            //Irasom kelis vienam file
	            FileWriter rasom = new FileWriter(file,true);
	            BufferedWriter writer = new BufferedWriter(rasom);
	            PrintWriter printWriter = new PrintWriter(writer);
//	            PrintWriter printWriter = new PrintWriter(rasom);
	            
	            printWriter.println(names.toUpperCase() + " taskai: " + score +"." );
	            printWriter.close();

	        } catch (IOException e1){
	            e1.printStackTrace();
	        }
	        
			moves = 0;
			score = 0;
			lengthofsnake = 3;
  
			repaint();

		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}

			up = false;
			down = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}

			up = false;
			down = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				down = true;
				up = false;
			}

			left = false;
			right = false;

		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				up = true;
				down = false;
			}

			left = false;
			right = false;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
