package de.xancake.ui.swing.components.panel;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WordsWrappingPanel extends JPanel {
	public static final int LEADING  = 0;
	public static final int CENTER   = 1;
	public static final int TRAILING = 2;
	
	private int myHorizontalAlignment;
	private int myVerticalAlignment;
	private String[] myText = new String[0];
	
	public WordsWrappingPanel() {
		this((String[])null);
	}
	
	public WordsWrappingPanel(String... text) {
		this(CENTER, CENTER, text);
	}
	
	public WordsWrappingPanel(int horizontalAlignment, int verticalAlignment, String... text) {
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setHorizontalAlignment(horizontalAlignment);
		setVerticalAlignment(verticalAlignment);
		
		if(text != null && text.length==1) {
			setText(text[0]);
		} else {
			setText(text);
		}
	}
	
	public int getHorizontalAlignment() {
		return myHorizontalAlignment;
	}
	
	public void setHorizontalAlignment(int alignment) {
		switch(alignment) {
			case LEADING:
			case CENTER:
			case TRAILING:
				myHorizontalAlignment = alignment;
		}
	}
	
	public int getVerticalAlignment() {
		return myVerticalAlignment;
	}
	
	public void setVerticalAlignment(int alignment) {
		switch(alignment) {
			case LEADING:
			case CENTER:
			case TRAILING:
				myVerticalAlignment = alignment;
		}
	}
	
	public String[] getText() {
		return myText;
	}
	
	public void setText(String... text) {
		myText = text;
	}
	
	public String getTextString() {
		String text = "";
		if(myText != null) {
			for(int i=0; i<myText.length; i++) {
				text += myText[i];
				if(i<myText.length-1) {
					text += "\n";
				}
			}
		}
		return text;
	}
	
	public void setText(String text) {
		if(text != null) {
			setText(text.split("\\n"));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		int w = getWidth();
		int h = getHeight();
		int cx = w/2;
		int cy = h/2;
		
		if(isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(x, y, w, h);
		}
		
		Insets insets = getInsets();
		x = x + insets.left;
		y = y + insets.top;
		w = w - insets.left - insets.right;
		h = h - insets.top  - insets.bottom;
		
		g.setColor(getForeground());
		
		FontMetrics fm = g.getFontMetrics();
		List<String> text = wrapWords(fm, w, myText);
		int fontHeight = fm.getMaxAscent() + fm.getLeading() + fm.getMaxDescent();
		int offset = cy-(fontHeight*text.size()/2);
		
		int xBase = 0;
		switch(myHorizontalAlignment) {
			case LEADING:
				xBase = x;
				break;
			case CENTER:
				xBase = cx;
				break;
			case TRAILING:
				xBase = x+w;
				break;
		}
		
		int yBase = 0;
		switch(myVerticalAlignment) {
			case LEADING:
				yBase = y;
				break;
			case CENTER:
				yBase = offset;
				break;
			case TRAILING:
				yBase = h - fontHeight*text.size();
				break;
		}
		
		for(int i=0; i<text.size(); i++) {
			String s = text.get(i);
			int xStart = xBase;
			if(myHorizontalAlignment == CENTER) {
				xStart -= fm.stringWidth(s)/2;
			} else if(myHorizontalAlignment == TRAILING) {
				xStart -= fm.stringWidth(s);
			}
			g.drawString(s, xStart, yBase+fontHeight*(i+1));
		}
	}
	
	private List<String> wrapWords(FontMetrics fm, int maxWidth, String... lines) {
		List<String> text = new ArrayList<String>();
		final String space = " ";
		final int spaceWidth = fm.stringWidth(space);
		for(String s : lines) {
			if(fm.stringWidth(s) > maxWidth) {
				String line = "";
				for(String word : s.split(space)) {
					int wordWidth = fm.stringWidth(word);
					int lineWidth = fm.stringWidth(line); 
					if(lineWidth + wordWidth + spaceWidth <= maxWidth) {
						line += word + space;
					} else {
						text.add(line);
						line = word + space;
					}
				}
				text.add(line);
			} else {
				text.add(s);
			}
		}
		return text;
	}
}
