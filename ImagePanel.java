package org.openimaj.MyFaceReco;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Image panel to hold the image and display
class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L; // UID for serialization
	private Image img; // To store image
	
	// Constructor
	public ImagePanel(String img) 
	{ 
		this(new ImageIcon(img).getImage()); // Store image icon
	}
	
	//constructor
	public ImagePanel(Image img) 
	{
	  
	  // Store the image
	  this.img = img;
	  Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	  setPreferredSize(size);
	  setMinimumSize(size);
	  setMaximumSize(size);
	  setSize(size);
	  setLayout(null);
	  
	 }
	
	//Display or paint the image into image panel
	 public void paintComponent(Graphics g) 
	 {
	  g.drawImage(this.img, 0, 0, null);
	 }
}