package BreakTheBrick;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;

import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
	private boolean play = false;
	private int score = 0;
	private String highscore = "";
	
	private int totalBricks = 48;
	// adaugare timer
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	//pozitiile de start a bilei
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	
	
	private MapGenerator map;
	
	//Crearea mapei, adaugare timer, permisiunea de a folosi butoanele tastaturii
	public Gameplay()
	{		
		map = new MapGenerator(4, 12);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	//Crearea obiectelor de pe ecran
	public void paint(Graphics g)
	{    		
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// mapa
		map.draw((Graphics2D) g);
		
		// margini
		g.setColor(Color.green);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// scor	
		g.setColor(Color.YELLOW);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("Score: " + score, 550,30);
		
		// highscore
		g.setColor(Color.YELLOW);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("HS " + highscore,5,30);
		
		// platforma
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		// minge
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
	
		// In cazul in care castigam apare mesajul de "You Won" si scorul, avand optiunea de a reseta meciul
		if(totalBricks <= 0)
		{
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);  
		}
		
		// In cazul in care pierdem apare mesajul de "Game Over" si scorul, avand optiunea de a reseta meciul
		if(ballposY > 570)
        {
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart or (ESC) to Exit", 170,350);        
        }
		
		if(ballposY > 570)
		{
			CheckScore();
		}
		
		if(highscore.equals(""))
		{
			highscore = this.GetHighscore();
			
		}
		
		g.dispose();
	}	

		//Mobilitatea caracterului in functie de butoanele apasate + resturl butoanelor ce pot fi actionate pentru functionalitatea jocului
	
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX >= 600)
			{
				playerX = 600;
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);
				
				repaint();
			}
        }
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		{
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;
		playerX+=20;	
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=20;	 	
	}
	
	
	public String GetHighscore() 
	{
		
		FileReader readFile = null;
		BufferedReader reader = null;
		
		try
		{
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		
		catch(Exception e)
		{
			return "Nobody:0";
		}
		
		finally
		{
			try 
			{
				if(reader != null)
					reader.close();
			} 
			catch (IOException e) 
			{
				
				e.printStackTrace();
			}
		}
	}
	
	public void CheckScore()
	{		
		if(score > Integer.parseInt((highscore.split(":")[1])))
		{
			String name = JOptionPane.showInputDialog("New highscore!!! Set your name:");
			highscore = name + ":" + score;
			
			File scorefile = new File("highscore.dat");
			if (!scorefile.exists())
			{
				try 
				{
					scorefile.createNewFile();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			FileWriter writefile = null;
			BufferedWriter writer = null;
			
			try
			{
				writefile = new FileWriter(scorefile);
				writer = new BufferedWriter(writefile);
				//writer.
				writer.write(this.highscore);
			}
			catch (Exception e)
			{
				//errors
			}
			finally
			{
				try
				{
					if (writer != null)
						writer.close();
				}
				catch (Exception e)
				{
					
				}
			}
			
			
		}
	}
	
	// Actiunile in urma coliziunilor bilei cu caramzile
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
			}
			
			// verifica coliziunie cu marginea mapei	
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;	
							totalBricks--;
							
							// momentul cand bila loveste caramida in stanga sau dreapta
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// momentul cand bila loveste caramida sus sau jos
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				
				ballXdir = -ballXdir;
			}
			if(ballposY > 570)
			{
				CheckScore();
			}
			
			repaint();		
		}
	}
}
