import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.util.Random;




public class Canvas extends JComponent implements ActionListener, KeyListener {
	Timer timer1;
	
	
	int rocketY;
	int x = 0;
    int y = 0;
    int count = 0;
    int countnew = 0;
    int countfuel = 0;   //make the fuel vanish after the rocket hits it
    int t = 0;
    int timeangle = 360;
    int xdist = 0;  //the x value for the moving fuel(extra credit2)
    int life = 3;   //each game has three runs
    int lifenew = 3;
    int score = 0;  
    int xcloud;
    int ycloud;
    
    
    
    double v0cos = 0;
    double v0sin = 0;
    double acce = 15;
    double angle = 0;        
    double gra = 9.8;
    
	
    
	public Canvas() {
	addKeyListener(this);
	setFocusable(true);
	timer1 = new Timer(50, this);
	
    
    
    
	}
     
	@Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
    
	
	//try to open the images
	BufferedImage img1 = null;
	BufferedImage img2 = null;
	BufferedImage img3 = null;
	try {
		img1 = ImageIO.read(new File("rocket.png"));
		img2 = ImageIO.read(new File("cloud.jpeg"));
		img3 = ImageIO.read(new File("fuel.png"));
	} catch (IOException e) {
		
		System.out.println("no file!");
	}
	
	//draw the "walls"
	g.drawLine(0, 470, 100, 470);
	g.drawLine(100, 470, 100, 520);
	g.drawLine(100, 520, 200, 520);
	g.drawLine(200, 520, 200, 420);
	g.drawLine(200, 420, 400, 420);
	g.drawLine(400, 420, 400, 350);
	g.drawLine(400, 350, 600, 350);
	g.drawLine(600, 350, 600, 450);
	g.drawLine(600, 450, 800, 450);
	
	g.drawLine(0, 130, 200, 130);
	g.drawLine(200, 130, 200, 80);
	g.drawLine(200, 80, 400, 80);
	g.drawLine(400, 80, 400, 200);
	g.drawLine(400, 200, 600, 200);
	g.drawLine(600, 200, 600, 100);
	g.drawLine(600, 100, 800, 100);
	
	
	
	
	
    
    
   
  	
    
    //increase the x and y for the rocket at appropriate times
    if (count > countnew) {
    x += (int) v0cos ;
    if (angle >= 1.7 || angle <= -1.7) {
    y -= (int) v0sin / 10;
    } else {
    y += (int) v0sin / 5;	
    }
    countnew = count;
    }
    else {
    y -= (int) gra / 5;   //when there is no actions, it drops because of the gravity
    }
    
        if(life == 3) {
        g.drawImage(img1, 10, 15, 35, 35, this);
        g.drawImage(img1, 50, 15, 35, 35, this);
    	g.drawImage(img1, 90, 15, 35, 35, this);
    	}
    	if(life == 2) {
    	g.drawImage(img1, 10, 15, 35, 35, this);
    	g.drawImage(img1, 50, 15, 35, 35, this);
    	
    	}
    	if(life == 1) {
        g.drawImage(img1, 10, 15, 35, 35, this);
        
        
    	}
        
    	//extra credit1: draw a random cloud as an obstacle
    	if (life == lifenew) {
    	Random randGen = new Random();
    	ycloud = randGen.nextInt(200) + 200;
    	xcloud = randGen.nextInt(800);
    	lifenew -= 1;
    	}
    	g.drawImage(img2, xcloud, ycloud, 50, 50, this);
        //extra credit1: check if the rocket crashes on the static cloud
    	if (x >= xcloud && x <= xcloud + 50) {
    		if (rocketY >= ycloud && rocketY <= ycloud + 50) {
    			timer1.stop();
    			life -= 1;
    		}
    	}
    	
    	if (countfuel == 0) {
            g.drawImage(img3, xdist, 200, 50, 50, this);
            
            //extra credit2: check if the rocket hits the moving fuel
            if (x >= xdist && x <= (xdist + 25)) {
            	if (rocketY >= 200 && rocketY <= 250) {
            		countfuel ++;
        			score ++;
        		}
            }
    	}
    	g.setColor(Color.lightGray);
    	g.fillArc(720, 15, 50, 50, 0, timeangle);
    	
    	g.setColor(Color.red);
    	g.fillRect(650, 450, 150, 10);
    	
    	
    	
    	g.setColor(Color.black);
    	
    	//draw the rotated rocket
    	AffineTransform tx = new AffineTransform();
        tx.rotate(angle, img1.getWidth() / 2, img1.getHeight() / 2);

        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        img1 = op.filter(img1, null);
    
    rocketY = getHeight() - 35 - 150 - y;
	g.drawImage(img1, x, rocketY, 35, 35, this);
	
	
	//check if the rocket goes out of the window or crashes to the walls
	if (x < 0 || x > 800) {
		timer1.stop();
		life -= 1;
		
	}
	
	if (x >= 0 && x <= 100) {
		if (rocketY <= 130 || rocketY >= 470) {
			timer1.stop();
			life -= 1;
		}
	}
	
	if (x > 100 && x <= 200) {
		if (rocketY >= 520 || rocketY <= 130 ) {
			timer1.stop();
			life -= 1;
		}
	}
	
	if (x > 200 && x <= 400) {
		if (rocketY >= 420 || rocketY <= 80 ) {
			timer1.stop();
			life -= 1;
			
		}
	}
	
	if (x > 400 && x <= 600) {
		if (rocketY >= 350 || rocketY <= 200 ) {
			timer1.stop();
			life -= 1;
		}
	}
	
	if (x > 600 && x <= 650) {
		if (rocketY >= 450 || rocketY <= 100 ) {
			timer1.stop();
			life -= 1;
		}
	}
	
	if (x > 650 && x <= 800) {
		if (rocketY <= 100 ) {
			timer1.stop();
			life -= 1;
		}
	}
	
	g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
	
	if (x > 650 && x <= 800) {
		if (rocketY >= 450) {
			
		  if (v0cos > 130 || v0sin > 130) {
		   g.drawString("Crashes!!", getWidth() / 2, getHeight() / 2 - 50);
		  } else {
		  score += 1;
		  
		  }
		  timer1.stop();
		  life -= 1;
		}
	}
	
	if(life <= 0) {
	g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
	
	g.drawString("Game ends! Your score is " + score, getWidth() / 2 - 1, getHeight() / 2 - 1);
	
	}
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//extra credit 2: a moving fuel
		xdist += 5;
		if (xdist >= 850) {
		xdist = 0;
		}
		
		//to reset the fuel for the rocket every run
		//making the fuel less than the previous run
		t += 1;
		if (life == 3 && t % 3 == 0) {
		timeangle -= 1;
		}
		if (life == 2 && t % 2 == 0) {
			timeangle -= 1;
		}
		if (life == 1 ) {
			timeangle -= 1;
		}
		if ((timeangle <= 0) && (timeangle % 360 == 0)) {
		timer1.stop();
		life -= 1;
		repaint();
		
		}
		
		repaint();
		
		}
		
	
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	  
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
	  
	  //after the third run, exit the game
	  if(life <= 0) {
		  System.exit(0);
	  }
	  
	  int location = e.getKeyCode();
	  
	  //type space key means start
	  if (location == KeyEvent.VK_SPACE) {
		  timer1.start();
	  }
	  
	  //type enter key means reset a new run
	  if (location == KeyEvent.VK_ENTER) {
			if(life == 2 || life == 1) {
				
				
				count = 0;
				v0cos = 0;
				v0sin = 0;
				angle = 0; 
				timeangle = 360;
				x = 0;
			    y = 0;
			    
			    countfuel = 0;
			    countnew = 0;
			    angle = 0; 
			    
				
			    
		
			    repaint();
				
				
			}  
			if (life <= 0) {
			 timer1.stop();
			 repaint();
			 
			}
			}
	    
	  //type up key means adding acceleration
	  if (location == KeyEvent.VK_UP) {
		  
		  count += 1;
		  
		  
		  v0cos += acce * Math.sin(angle / 2); 
		  v0sin += acce * Math.cos(angle / 2) - gra;
		  
		  
	  }
	  
	  //type left key means changing the angle to the left
	  if (location == KeyEvent.VK_LEFT) {
		  
		  angle -= 0.1;
		 
	  }
	  
	  //type left key means changing the angle to the right
	  if (location == KeyEvent.VK_RIGHT) {
		  angle += 0.1;
	  }
	}
	
	
		


	public static void main(String[] args) {
	      JFrame frame = new JFrame("Project4");
	      Canvas canvas = new Canvas();
	      frame.getContentPane().setBackground(Color.white);
	      frame.add(canvas);
	      frame.setSize(800,600);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.setVisible(true);
		  frame.setResizable(true);
			
		}
	
	
}
