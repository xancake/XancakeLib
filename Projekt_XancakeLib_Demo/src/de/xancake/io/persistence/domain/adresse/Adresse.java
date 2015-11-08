package de.xancake.io.persistence.domain.adresse;

import java.util.Objects;

public class Adresse {
	private String _ort;
	private String _plz;
	private String _strasse;
	
	public String getOrt() {
		return _ort;
	}
	
	public String getPostleitzahl() {
		return _plz;
	}
	
	public String getStrasse() {
		return _strasse;
	}
	
	public void setOrt(String ort) {
		_ort = Objects.requireNonNull(ort);
	}
	
	public void setPostleitzahl(String plz) {
		_plz = Objects.requireNonNull(plz);
	}
	
	public void setStrasse(String strasse) {
		_strasse = Objects.requireNonNull(strasse);
	}
	
	@Override
	public String toString() {
		return _plz + " " + _ort + ", " + _strasse;
	}
}
