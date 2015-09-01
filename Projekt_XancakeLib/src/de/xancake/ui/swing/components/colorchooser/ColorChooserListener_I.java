package de.xancake.ui.swing.components.colorchooser;

import java.awt.Color;
import java.util.EventListener;

public interface ColorChooserListener_I extends EventListener {
	void onColorSelected(Color color);
}
