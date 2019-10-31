//This canvas class is to draw the trajectory and the five types of fireworks

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import java.util.Random;

public class Canvas extends JComponent {
	
    int x;
    int y;
    int ypixel;
   
    double gra = 9.8;
	double v0;
	double angle;
	double time;
	
	int index;
	int count = 0;
	int choice;
	
	
	
	

   
	@Override
	    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 
		   count ++;
		   index = GUI.getIndex();
		  
		   //set user chosen colors
		  switch (index) {
		  case 0:
		  g.setColor(Color.YELLOW);
		  break;
		  case 1:
	      g.setColor(Color.BLUE);
	      break;
		  case 2:
		  g.setColor(Color.RED);
		  break;
		  case 3:
	      g.setColor(Color.GREEN);
		  break;
		  case 4:
		  g.setColor(Color.PINK);
		  }
		  
		  
		  if (count != 1) {
		   v0 = GUI.getV0();
		   angle = GUI.getAngle();
		   time = GUI.getTime();
		   
		  // calculate the location of the explosion
		  x = (int) (v0 * Math.cos(angle) * time) ;
		  y = (int) ( v0 * Math.sin(angle) * time - 0.5 * gra * Math.pow(time, 2) );
		  ypixel = getHeight() - y;
		  
        	
    		
    		
    		
    		int y1; // y real time value for projectile
    		int y2; //ending y for the interval
    		
    		//draw the trajectory
    		for (int dx = 0; dx < x; dx ++) {
    			
    			
    			y1 = (int) (Math.tan(angle) * dx - 0.5 * gra * Math.pow(dx, 2) / Math.pow(v0 * Math.cos(angle), 2));
    			y2 = (int) (Math.tan(angle) * (dx + 1)  - 0.5 * gra * Math.pow((dx + 1), 2) / Math.pow(v0 * Math.cos(angle), 2));
    			g.drawLine(dx, getHeight() - y1,  dx + 1,  getHeight() - y2);
    		}
    		
    		
    		
    		
    		choice = GUI.getChoice();
    		//type 1
    		if (choice == 1) {
    			 
    		int r = 80;
    		for (double degree = 0; degree <= 2 * Math.PI; degree += 0.2) {
    		g.drawLine(x, ypixel, x + (int) (r * Math.cos(degree)), ypixel - (int) (r * Math.sin(degree)));
    		
    		}
    		
    		
    		//extra credit3, only works for "Line explosion" it will breaks into two smaller pieces that shoot at different directions
    		//take same v0 and one fifth the previous time as parameters
    		time = time / 5;
    		r = 50;
			for (int i = 1; i <=2; i++) {
				
			int xsmall = (int) (v0 * Math.cos(angle - 0.3) * time) ; //new x for the smaller explosions
  		    int ysmall = (int) ( v0 * Math.sin(angle - 0.3) * time - 0.5 * gra * Math.pow(time, 2) ); //new y
		    ypixel = getHeight() - ysmall - y;
    		
		    //draw the new trajectory for the smaller parts
            for (int dx = 0; dx < xsmall; dx ++) {
    			
    			
    			y1 =  (int) (Math.tan(angle - 0.3) * dx - 0.5 * gra * Math.pow(dx, 2) / Math.pow(v0 * Math.cos(angle - 0.3), 2));
    			y2 =  (int) (Math.tan(angle - 0.3) * (dx + 1)  - 0.5 * gra * Math.pow((dx + 1), 2) / Math.pow(v0 * Math.cos(angle - 0.3), 2));
    			
    			g.drawLine(dx + x, getHeight() - y - y1,  dx + x + 1,  getHeight() - y - y2);
    		}
            
            //draw the smaller parts
    		for (double degree = 0; degree <= 2 * Math.PI; degree += 0.2) {
    		g.drawLine(x + xsmall, ypixel, x + xsmall + (int) (r * Math.cos(degree)), ypixel - (int) (r * Math.sin(degree)));
    		
    		}
            
    		r -= 20;
            angle += 0.6;
            
            //in case that angle is larger than pi/2
            if (angle > Math.PI / 2) {
            angle = Math.PI / 2;
            }
    		}
			
    		
    		}
    		
    		//type 2
    		if(choice == 2) {
    	    g.drawLine(x, ypixel, x + 80, ypixel - 120);
    	    g.drawLine(x + 80, ypixel - 120, x + 160, ypixel);
    	    g.drawLine(x, ypixel, x + 160, ypixel);
    	    
    	    g.drawLine(x, ypixel - 80, x + 160, ypixel - 80);
    	    g.drawLine(x + 160, ypixel - 80, x + 80, ypixel + 40);
    	    g.drawLine(x, ypixel - 80,  x + 80, ypixel + 40);
    		}
		    
    		//type3
    		if(choice == 3) {
    			
    	    Random randGen = new Random();
    	    int flakeLenth = randGen.nextInt(20) + 10;  //adding randomness to the length of the lines on the snowflake
    	    
    	    		
    		g.drawLine(x - 60, ypixel, x + 60, ypixel);
    		g.drawLine(x, ypixel + 60, x, ypixel - 60);
    		g.drawLine(x - 45, ypixel - 45 , x + 45 , ypixel + 45);
    		g.drawLine(x - 45, ypixel + 45, x + 45, ypixel - 45);
    		
    		g.drawLine(x - 22, ypixel - 22, x - 22 - flakeLenth, ypixel - 22);
    		g.drawLine(x - 22, ypixel - 22, x - 22, ypixel - 22 - flakeLenth);
    		
    		g.drawLine(x - 22, ypixel + 22, x - 22 - flakeLenth, ypixel + 22);
    		g.drawLine(x - 22, ypixel + 22, x - 22, ypixel + 22 + flakeLenth);
    		
    		g.drawLine(x + 22, ypixel - 22, x + 22, ypixel - 22 - flakeLenth);
    		g.drawLine(x + 22, ypixel - 22, x + 22 + flakeLenth, ypixel - 22);
    		
    		g.drawLine(x + 22, ypixel + 22, x + 22 , ypixel + 22 + flakeLenth);
    		g.drawLine(x + 22, ypixel + 22, x + 22 + flakeLenth, ypixel + 22);
    		
    		
    		}
    		
    		//type 4
    		if(choice == 4) {
    			int r = 80;
    			int r1 = 85;
        		for (double degree = 0; degree <= 2 * Math.PI; degree += 0.2) {
        		g.drawLine(x, ypixel, x + (int) (r * Math.cos(degree)), ypixel - (int) (r * Math.sin(degree)));
        		g.fillOval(x + (int) (r1 * Math.cos(degree)), ypixel - (int) (r1 * Math.sin(degree)), 10, 10);
        		}	
    		}
    		
    		//type 5
    		if(choice == 5) {
    			g.drawLine(x - 40, ypixel - 40, x + 40, ypixel + 40);
        	    g.drawLine(x - 40, ypixel, x + 40, ypixel);
        	    g.drawLine(x - 40, ypixel + 40, x + 40, ypixel - 40);
        	    
        	    g.drawLine(x - 80, ypixel - 40, x - 40, ypixel - 40);
        	    g.drawLine(x - 80, ypixel - 40, x - 40, ypixel);
        	    
        	    g.drawLine(x + 40, ypixel,  x + 80, ypixel - 40);
        	    g.drawLine(x + 40, ypixel - 40, x + 80, ypixel - 40);
        	    
        	    g.drawLine(x, ypixel + 80,  x - 40, ypixel + 40);
        	    g.drawLine(x, ypixel + 80,  x + 40, ypixel + 40);
    		}
		  } 
       
	}
}
