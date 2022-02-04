package BreakTheBrick;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelpFrame extends JFrame
{
	private JButton backbutton;
	private JLabel text,text2,text3;
	
	HelpFrame()
	{
		
		backbutton = new JButton();
		text = new JLabel();
		text2 = new JLabel();
		text3 = new JLabel();
		
		text.setText("For movement you will use:");
		text.setFont(new Font("Comic Sans",Font.BOLD,20));
		text.setForeground(Color.RED);
		text.setBounds(110,50,300,100);
		
		text2.setText("'<-' and '->' keys");
		text2.setFont(new Font("Comic Sans",Font.BOLD,20));
		text2.setForeground(Color.RED);
		text2.setBounds(110,80,300,100);
		
		text3.setText("ESC key will close the game");
		text3.setFont(new Font("Comic Sans",Font.BOLD,20));
		text3.setForeground(Color.RED);
		text3.setBounds(110,200,300,100);
		
		backbutton.setBounds(20,400,100,50);
		
		backbutton.setText("Back");
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.RED);
		backbutton.setBackground(Color.BLACK);
		
		
		this.getContentPane().setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setForeground(Color.BLACK);
		this.setBackground(Color.BLACK);
		this.setTitle("Break the brick");
		this.setBounds(650, 200, 1000, 1000);
		this.setSize(500,500);
		this.setVisible(true);
		this.add(backbutton);
		this.add(text);
		this.add(text2);
		this.add(text3);
		
		backbutton.addActionListener(e -> {this.dispose();});
		
	}
	
}
