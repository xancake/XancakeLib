package de.xancake.ui.swing.components.colorchooser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorChooserPanel extends JPanel {
	private double myFactor;
	private double myStart;
	
	public ColorChooserPanel() {
		this(1);
	}
	
	public ColorChooserPanel(double factor) {
		this(factor, 0);
	}
	
	public ColorChooserPanel(double factor, double start) {
		setFactor(factor);
		setStart(start);
		
		MouseAdapter mouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fireColorSelectedEvent(getColorAt(e.getX(), e.getY()));
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				fireColorSelectedEvent(getColorAt(e.getX(), e.getY()));
			}
		};
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}
	
	private void fireColorSelectedEvent(Color color) {
		for(ColorChooserListener_I l : listenerList.getListeners(ColorChooserListener_I.class)) {
			l.onColorSelected(color);
		}
	}
	
	public void addColorChooserListener(ColorChooserListener_I l) {
		listenerList.add(ColorChooserListener_I.class, l);
	}
	
	public void removeColorChooserListener(ColorChooserListener_I l) {
		listenerList.remove(ColorChooserListener_I.class, l);
	}
	
	public Color getColorAt(Point p) {
		return getColorAt(p.x, p.y);
	}
	
	public double getFactor() {
		return myFactor;
	}
	
	public void setFactor(double factor) {
		if(factor<=0)
			throw new IllegalArgumentException("The factor has to be > 0");
		
		myFactor = factor;
	}
	
	public double getStart() {
		return myStart;
	}
	
	public void setStart(double start) {
		myStart = start;
	}
	
	public Color getColorAt(int x, int y) {
		float fw = (float)getWidth();
		float fh = (float)getHeight();
		float fx = (float)x;
		float fy = (float)y;
		float h = (float)(myStart+fx/fw*myFactor);
		float s = (float)(fy < fh/2 ? fy/(fh/2) : 1);
		float b = (float)(fy > fh/2 ? (fh-fy)/(fh/2) : 1);
		return Color.getHSBColor(h, s, b);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for(int x=0; x<getWidth(); x++) {
			for(int y=0; y<getHeight(); y++) {
				g.setColor(getColorAt(x, y));
				g.drawLine(x, y, x, y);
			}
		}
	}
}
