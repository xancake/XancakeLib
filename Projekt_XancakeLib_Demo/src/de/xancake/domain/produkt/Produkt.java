package de.xancake.domain.produkt;

import java.math.BigDecimal;
import java.util.Objects;

public class Produkt {
	private String _nummer;
	private String _bezeichnung;
	private BigDecimal _preis;
	private BigDecimal _bestand;
	
	public String getNummer() {
		return _nummer;
	}
	
	public String getBezeichnung() {
		return _bezeichnung;
	}
	
	public BigDecimal getPreis() {
		return _preis;
	}
	
	public BigDecimal getBestand() {
		return _bestand;
	}
	
	public void setNummer(String nummer) {
		_nummer = Objects.requireNonNull(nummer);
	}
	
	public void setBezeichnung(String bezeichnung) {
		_bezeichnung = Objects.requireNonNull(bezeichnung);
	}
	
	public void setPreis(BigDecimal preis) {
		_preis = Objects.requireNonNull(preis);
	}
	
	public void setBestand(BigDecimal bestand) {
		_bestand = Objects.requireNonNull(bestand);
	}
	
	@Override
	public String toString() {
		return "Produkt '" + _nummer + "' (" + _bezeichnung + ", " + _preis + ")";
	}
}
