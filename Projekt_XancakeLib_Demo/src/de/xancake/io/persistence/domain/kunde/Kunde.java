package de.xancake.io.persistence.domain.kunde;

import java.util.Objects;

public class Kunde {
	private String _vorname;
	private String _nachname;
	private Integer _adresse;
	
	public String getVorname() {
		return _vorname;
	}
	
	public String getNachname() {
		return _nachname;
	}
	
	public Integer getAdresse() {
		return _adresse;
	}
	
	public void setVorname(String vorname) {
		_vorname = Objects.requireNonNull(vorname);
	}
	
	public void setNachname(String nachname) {
		_nachname = Objects.requireNonNull(nachname);
	}
	
	public void setAdresse(Integer adresse) {
		_adresse = adresse;
	}
	
	@Override
	public String toString() {
		return _vorname + " " + _nachname;
	}
}
