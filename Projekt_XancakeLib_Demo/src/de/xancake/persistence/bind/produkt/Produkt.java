package de.xancake.persistence.bind.produkt;

import java.math.BigDecimal;
import java.util.Objects;

public class Produkt {
	private int myIdent;
	private String myNummer;
	private String myBezeichnung;
	private BigDecimal myPreis;
	
	public Produkt() {
		myIdent = -1;
	}
	
	public Integer getId() {
		return myIdent;
	}
	
	public String getNummer() {
		return myNummer;
	}
	
	public String getBezeichnung() {
		return myBezeichnung;
	}
	
	public BigDecimal getPreis() {
		return myPreis;
	}
	
	public void setId(Integer ident) {
		myIdent = Objects.requireNonNull(ident);
	}
	
	public void setNummer(String nummer) {
		myNummer = Objects.requireNonNull(nummer);
	}
	
	public void setBezeichnung(String bezeichnung) {
		myBezeichnung = Objects.requireNonNull(bezeichnung);
	}
	
	public void setPreis(BigDecimal preis) {
		myPreis = Objects.requireNonNull(preis);
	}
	
	@Override
	public String toString() {
		return "Produkt " + myIdent + " (" + myNummer + ", " + myBezeichnung + ", " + myPreis + ")";
	}
}
