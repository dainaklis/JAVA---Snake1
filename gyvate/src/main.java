import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.util.*;


public class main {

	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
		GamePlay gameplay = new GamePlay();
		
		obj.setBounds(10, 10, 905, 700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.getContentPane().add(gameplay);
		

//		JMenuBar menuBar = new JMenuBar();
//		
//		menuBar.setBorderPainted(false);
//		menuBar.setBackground(Color.BLACK);
//		obj.setJMenuBar(menuBar);
//		
//		JMenu mnNewMenu = new JMenu("Meniu");
//		mnNewMenu.setForeground(Color.RED);
//		mnNewMenu.setBackground(Color.BLACK);
//		menuBar.add(mnNewMenu);
//		
//		JMenuItem btnNewGame = new JMenuItem("New game");
//		btnNewGame.setBackground(Color.GRAY);
//		//btnNewGame.setIcon(new ImageIcon(play.class.getResource("/resources/new_16.png")));
//		mnNewMenu.add(btnNewGame);


	}

}
