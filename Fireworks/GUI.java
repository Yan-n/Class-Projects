//This class is creates a container and add all the GUIs and the canvas instance to it
//set layout for all these components and get the parameters from the user


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JSlider;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class GUI extends JFrame implements ActionListener, ChangeListener {
	  JLabel label;
	  JLabel label2;
	  JLabel label3;
      JButton bttn;
      JButton bttn2;
      JButton bttn3;
      JButton bttn4;
      JButton bttn5;
      JSlider slider;
      JSlider slider2;
      JSlider slider3;
      JList<String> myList;
      
      public static double v0;
      public static double angle;
      public static double time;
      public static int index;
      public static int choice;
     
      GridBagConstraints layoutConst;
      
	
	public GUI() {
		//use gridbaglayout
		setLayout(new GridBagLayout());
		layoutConst = new GridBagConstraints();
		
		
		
		//five buttons to lunch each type of fireworks
		bttn = new JButton("Line explosion");
		bttn.addActionListener(this);
		
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 0;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    add(bttn, layoutConst);
		
		

		bttn2 = new JButton("Hexagon");
		bttn2.addActionListener(this);
		layoutConst.gridx = 1;
	    layoutConst.gridy = 0;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    add(bttn2, layoutConst);
		
		

		bttn3 = new JButton("Snowflake");
		bttn3.addActionListener(this);
		layoutConst.gridx = 0;
	    layoutConst.gridy = 1;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    add(bttn3, layoutConst);
		
		

		bttn4 = new JButton("Dandelion");
		bttn4.addActionListener(this);
		layoutConst.gridx = 1;
	    layoutConst.gridy = 1;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    add(bttn4, layoutConst);
		
		

		bttn5 = new JButton("Pinwheel");
		bttn5.addActionListener(this);
		layoutConst.gridx = 0;
	    layoutConst.gridy = 2;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    add(bttn5, layoutConst);
		
		

		//guis for parameter speed
		label = new JLabel("speed: ");
		layoutConst.gridx = 0;
	    layoutConst.gridy = 3;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    add(label, layoutConst);
		
		
		slider = new JSlider(0, 120, 0);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(20);
	    slider.setMinorTickSpacing(5);
	    slider.setPaintTicks(true);
	    slider.setPaintLabels(true);
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 3;
	    layoutConst.gridwidth = 3;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    add(slider, layoutConst);
		
		
		//guis for parameter angle
		label2 =new JLabel("angle: ");
		layoutConst.gridx = 0;
	    layoutConst.gridy = 4;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
	    add(label2, layoutConst);
		
		
		slider2 = new JSlider(0, 90, 0);
		slider2.addChangeListener(this);
		slider2.setMajorTickSpacing(10);
	    slider2.setMinorTickSpacing(5);
	    slider2.setPaintTicks(true);
	    slider2.setPaintLabels(true);
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 4;
	    layoutConst.gridwidth = 3;
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    add(slider2, layoutConst);
		
		//guis for parameter time delayed
		label3 =new JLabel("time delay: ");
		layoutConst.gridx = 0;
	    layoutConst.gridy = 5;
	    layoutConst.insets = new Insets(5, 5, 5, 5);
		add(label3, layoutConst);
		
		slider3 = new JSlider(0, 100, 0);
		slider3.addChangeListener(this);
		slider3.setMajorTickSpacing(20);
	    slider3.setMinorTickSpacing(5);
	    slider3.setPaintTicks(true);
	    slider3.setPaintLabels(true);
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 5;
	    layoutConst.gridwidth = 3;
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
		add(slider3, layoutConst);
		
		//JList for users to choose colors
		String [] data = { "Yellow", "Blue", "Red", "Green", "Pink" };
		myList = new JList<String>(data);
		 layoutConst.gridx = 0;
		 layoutConst.gridy = 6;
		add(myList, layoutConst);
		myList.addMouseListener(mouseListener);
		
		     
		//create canvas instance and add it the the frame
		Canvas b = new Canvas();
		b.setOpaque(true);
		layoutConst.gridx = 4;
	    layoutConst.gridy = 0;
	    layoutConst.ipadx = 500;
	    layoutConst.ipady = 600;
	    layoutConst.gridwidth = 1;
	    layoutConst.gridheight = 7;
	    layoutConst.fill = GridBagConstraints.HORIZONTAL;
	    layoutConst.fill = GridBagConstraints.VERTICAL;
		add(b, layoutConst);  
		
		
		
	}
	
	//listner to get the value from the JList
	MouseListener mouseListener = new MouseAdapter() {
	     public void mouseClicked(MouseEvent e) {
	         
	             index = myList.locationToIndex(e.getPoint());
	             
	          
	     }
	
	};
	
      
    
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		   if (e.getSource() == bttn) {
			choice = 1;
			
			
		   }
		   if (e.getSource() == bttn2) {
			choice = 2;
			  
		   }
		   if (e.getSource() == bttn3) {
			choice = 3;
		   }
		   if (e.getSource() == bttn4) {
			   choice = 4;
		   }
		   if (e.getSource() == bttn5) {
			   choice = 5;
		   }
		   repaint();
		   
	}
	
	

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == slider) {
			v0 =  slider.getValue();
		}
		
		if(e.getSource() == slider2) {
			angle =  slider2.getValue() * Math.PI / 180;
		}
		
		if(e.getSource() == slider3) {
		    time = slider3.getValue() / 10;
		}
		
	}

	

	public static double getV0() {
		return v0;
	}

	public static void setV0(double v0) {
		GUI.v0 = v0;
	}

	public static double getAngle() {
		return angle;
	}

	public static void setAngle(double angle) {
		GUI.angle = angle;
	}

	public static double getTime() {
		return time;
	}

	public static void setTime(double time) {
		GUI.time = time;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		GUI.index = index;
	}



	public static int getChoice() {
		return choice;
	}



	public static void setChoice(int choice) {
		GUI.choice = choice;
	}

	
	

	

	
	

}
	
