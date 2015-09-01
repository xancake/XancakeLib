package de.xancake.ui.swing.components.panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BackgroundPanel extends JPanel {
	private Image myImage;
	
	public BackgroundPanel(Image image) {
		this.myImage = image;
	}
	
	public void setImage(Image image) {
		this.myImage = image;
		this.repaint();
	}
	
	public Image getImage(Image image) {
		return image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), this);
	}
}
