package BreakTheBrick;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

public class Menu extends JFrame
{
	private JLabel title;
	private JButton playbutton;
	private JButton helpbutton;
	private JButton exitbutton;

	Menu()
	{	
		playbutton = new JButton();
		helpbutton = new JButton();
		exitbutton = new JButton();
		title = new JLabel();
		
		
		
		playbutton.setBounds(200,150,100,50);
		helpbutton.setBounds(200,250,100,50);
		exitbutton.setBounds(200,350,100,50);
		title.setBounds(110,50,300,100);
		
		
		title.setText("BREAK THE BRICK");
		title.setFont(new Font("Comic Sans",Font.BOLD,30));
		title.setForeground(Color.RED);
		
		playbutton.addActionListener(e -> new GameFrame());
		playbutton.addActionListener(e -> {this.dispose();});
		playbutton.setText("Play");
		playbutton.setFocusable(false);
		playbutton.setForeground(Color.RED);
		playbutton.setBackground(Color.BLACK);
		
		
		helpbutton.setText("Help");
		helpbutton.setFocusable(false);
		helpbutton.setForeground(Color.RED);
		helpbutton.setBackground(Color.BLACK);
		helpbutton.addActionListener(e -> new HelpFrame());
		
		exitbutton.setText("Exit");
		exitbutton.setFocusable(false);
		exitbutton.setForeground(Color.RED);
		exitbutton.setBackground(Color.BLACK);
		exitbutton.addActionListener(e -> System.exit(0));
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setForeground(Color.BLACK);
		this.setBackground(Color.BLACK);
		this.setTitle("Break the brick");
		this.setBounds(650, 200, 1000, 1000);
		this.setSize(500,500);
		this.setVisible(true);
		this.add(playbutton);
		this.add(helpbutton);
		this.add(exitbutton);
		this.add(title);
		
		
	}
	
	
}
