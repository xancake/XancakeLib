package de.xancake.ui.swing.components.colorchooser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

@SuppressWarnings("serial")
public class ColorChooserButton extends JButton {
	private static final int SIZE_PREF_WIDTH  = 100;
	private static final int SIZE_PREF_HEIGHT = 24;
	
	public static final int NOTATION_NONE          = 0;
	public static final int NOTATION_FORCE_DECIMAL = 1;
	public static final int NOTATION_FORCE_HEX     = 2;
	public static final int NOTATION_JUSTIFY       = 3;
	
	private boolean bInit;
	
	private Color myColor;
	private int myNotation;
	private boolean bPaintDisplayFull;
	
	public ColorChooserButton() {
		this(Color.BLACK);
	}
	
	public ColorChooserButton(boolean paintDisplayFull) {
		this(Color.BLACK, paintDisplayFull);
	}
	
	public ColorChooserButton(Color color) {
		this(color, false);
	}
	
	public ColorChooserButton(Color color, boolean paintDisplayFull) {
		super(" ");
		bInit = true;
		setColor(color);
		setNotation(NOTATION_JUSTIFY);
		setPaintDisplayFull(paintDisplayFull);
		
		setPreferredSize(new Dimension(SIZE_PREF_WIDTH, SIZE_PREF_HEIGHT));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		setFocusPainted(false);
		setOpaque(false);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showColorChooser();
			}
		});
		bInit = false;
		repaint();
	}
	
	private void showColorChooser() {
		Color color = JColorChooser.showDialog(this, "Choose the Color", myColor);
		if(color != null) {
			setColor(color);
			fireColorSelectedEvent(color);
		}
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
	
	public Color getColor() {
		return myColor;
	}
	
	public void setColor(Color color) {
		myColor = color;
		repaint();
	}
	
	public int getNotation() {
		return myNotation;
	}
	
	public void setNotation(int notation) {
		switch(notation) {
			case NOTATION_NONE:
			case NOTATION_FORCE_DECIMAL:
			case NOTATION_FORCE_HEX:
			case NOTATION_JUSTIFY:
				myNotation = notation;
				repaint();
		}
	}
	
	public boolean isPaintDisplayFull() {
		return bPaintDisplayFull;
	}
	
	public void setPaintDisplayFull(boolean b) {
		bPaintDisplayFull = b;
		repaint();
	}
	
	public String getDecimalColorString() {
		return myColor.getRed() + ", " + myColor.getGreen() + ", " + myColor.getBlue();
	}
	
	public String getHexadecimalColorString() {
		String colorString = null;
		String redString   = Integer.toHexString(myColor.getRed());
		String greenString = Integer.toHexString(myColor.getGreen());
		String blueString  = Integer.toHexString(myColor.getBlue());
		colorString = (redString.length()  <2 ? "0" : "") + redString + 
		              (greenString.length()<2 ? "0" : "") + greenString +
		              (blueString.length() <2 ? "0" : "") + blueString;
		return colorString.toUpperCase();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = 0;
		int y = 0;
		int w = getWidth();
		int h = getHeight();
		
		final int padding = 4;
		int size = w<h ? w : h;
		
		String colorString = null;
		switch(myNotation) {
			case NOTATION_NONE:
				size = w;
				break;
			case NOTATION_FORCE_DECIMAL:
				colorString = getDecimalColorString();
				break;
			case NOTATION_FORCE_HEX:
				colorString = getHexadecimalColorString();
				break;
			case NOTATION_JUSTIFY:
				colorString = getDecimalColorString();
				FontMetrics fm = g.getFontMetrics();
				if(fm.stringWidth(colorString)+padding*2+size > w) {
					colorString = getHexadecimalColorString();
				}
				if(fm.stringWidth(colorString)+padding*2+size > w) {
					size = w;
					if(fm.stringWidth(colorString)+padding*2+3 > w) {
						colorString = null;
					}
				}
				break;
		}
		
		if(bPaintDisplayFull) {
			size = w;
		}
		
		g.setColor(myColor);
		g.fillRect(x+padding, y+padding, size-padding*2-1, h-padding*2-1);
		g.setColor(Color.BLACK);
		g.drawRect(x+padding, y+padding, size-padding*2-1, h-padding*2-1);
		
		if(colorString != null) {
			FontMetrics fm = g.getFontMetrics();
			g.drawString(colorString, w-padding-fm.stringWidth(colorString)-1, h/2-fm.getDescent()+fm.getHeight()/2);
		}
	}
	
	@Override
	public void repaint() {
		if(!bInit) {
			super.repaint();
		}
	}
	
	@Override
	public String getText() {
		return " ";
	}
}
