package de.xancake.persistence.bind.produkt;

public class Produkt {
	private int myIdent;
	private String myNummer;
	private String myBezeichnung;
	private int myPreis;
	
	public Produkt() {
		myIdent = -1;
	}
	
	public int getId() {
		return myIdent;
	}
	
	public void setId(int ident) {
		myIdent = ident;
	}
	
	public String getNummer() {
		return myNummer;
	}
	
	public String getBezeichnung() {
		return myBezeichnung;
	}
	
	public int getPreis() {
		return myPreis;
	}
	
	public void setNummer(String nummer) {
		myNummer = nummer;
	}
	
	public void setBezeichnung(String bezeichnung) {
		myBezeichnung = bezeichnung;
	}
	
	public void setPreis(int preis) {
		myPreis = preis;
	}
}
