package de.xancake.ui.generic.dialog;

import javax.swing.JOptionPane;

public class SwingDialogs {
	public Antwort showFrage(String message, AntwortMoeglichkeit antwortMoeglichkeit) {
		int antwort = JOptionPane.showConfirmDialog(null, message, "Warnung", getOptionType(antwortMoeglichkeit), JOptionPane.WARNING_MESSAGE);
		return getAntwortType(antwort);
	}
	
	/**
	 * Hier geschieht das Mapping von einer {@link AntwortMoeglichkeit} auf die entsprechende int-Konstante für das {@link JOptionPane} aus Swing.
	 * @param antwortMoeglichkeit Die {@link AntwortMoeglichkeit}
	 * @return Die int-Konstante, die der {@link AntwortMoeglichkeit} entspricht
	 */
	private int getOptionType(AntwortMoeglichkeit antwortMoeglichkeit) {
		switch(antwortMoeglichkeit) {
			case JA_NEIN:           return JOptionPane.YES_NO_OPTION;
			case JA_NEIN_ABBRECHEN: return JOptionPane.YES_NO_CANCEL_OPTION;
			case OK_ABBRECHEN:      return JOptionPane.OK_CANCEL_OPTION;
			default:                throw new IllegalArgumentException("Es muss eine Antwortmöglichkeit angegeben werden");
		}
	}
	
	/**
	 * Hier geschieht das Mapping von der int-Konstante der möglichen Antworten aus {@link JOptionPane} auf die entsprechende {@link Antwort}.
	 * @param antwort Die int-Konstante aus dem {@link JOptionPane}
	 * @return Die {@link Antwort}, die der int-Konstante entspricht
	 */
	private Antwort getAntwortType(int antwort) {
		switch(antwort) {
			case JOptionPane.YES_OPTION:    return Antwort.JA;
			case JOptionPane.NO_OPTION:     return Antwort.NEIN;
			case JOptionPane.CANCEL_OPTION: return Antwort.ABBRECHEN;
			case JOptionPane.CLOSED_OPTION: return Antwort.GESCHLOSSEN;
			default:                        throw new IllegalArgumentException("Der Antworttyp muss gültig sein");
		}
	}
}
