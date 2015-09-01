package de.xancake.ui.mvc.example;

import de.xancake.ui.mvc.Model_I;

public class PersonModel implements Model_I {
	private int myIdent;
	private String myVorname;
	private String myNachname;
	
	public int getIdent() {
		return myIdent;
	}
	
	public String getVorname() {
		return myVorname;
	}
	
	public String getNachname() {
		return myNachname;
	}
	
	public void setIdent(int ident) {
		myIdent = ident;
	}
	
	public void setVorname(String vorname) {
		myVorname = vorname;
	}
	
	public void setNachname(String nachname) {
		myNachname = nachname;
	}
}
