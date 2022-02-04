package BreakTheBrick;

import javax.swing.JFrame;

public class GameFrame extends JFrame 
{	
	
	Gameplay gamePlay = new Gameplay();
	
	GameFrame()
	{
		
		this.setBounds(600, 100, 710, 600);
		this.setTitle("Break the brick");		
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(gamePlay);
		this.setVisible(true);
		
	}
}