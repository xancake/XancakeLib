package de.xancake.ui.mvc.example;

import java.util.Arrays;

import de.xancake.ui.mvc.Controller_A;

public class PersonController extends Controller_A<PersonModel, PersonViewListener_I, PersonView> implements PersonViewListener_I {
	private static final Integer[] IDENTEN;
	private static final String[]  VORNAMEN  = {"Lars", "Hans", "Sven", "Peter", "Marie", "Jennifer", "Melanie", "Larissa"};
	private static final String[]  NACHNAMEN = {"Nielsen", "Wurst", "Schneider", "Planlos", "Mertens", "Momsen", "Mutig", "Lustig"};
	
	static {
		IDENTEN = new Integer[VORNAMEN.length*NACHNAMEN.length];
		for(int i=0; i<IDENTEN.length; i++) {
			IDENTEN[i] = i;
		}
	}
	
	@Override
	public void onIdentChanged(int ident) {
		if(ident != myModel.getIdent()) {
			if(Arrays.asList(IDENTEN).contains(ident)) {
				belegeModel(myModel, ident);
			} else {
				myModel.setIdent(ident);
				myModel.setVorname("");
				myModel.setNachname("");
			}
			myView.fillViewWithModel(myModel);
		}
	}
	
	@Override
	public void onVornameChanged(String vorname) {
		if(vorname != null && !vorname.equals(myModel.getVorname())) {
			if(Arrays.asList(VORNAMEN).contains(vorname)) {
				myView.showFehlermeldung("Der Vorname " + vorname + " ist bereits vergeben!");
				myView.setVorname(myModel.getVorname());
			} else {
				myModel.setVorname(vorname);
			}
		}
	}
	
	@Override
	public void onNachnameChanged(String nachname) {
		if(nachname != null && !nachname.equals(myModel.getNachname())) {
			if(Arrays.asList(NACHNAMEN).contains(nachname)) {
				myView.showFehlermeldung("Der Nachname " + nachname + " ist bereits vergeben!");
				myView.setNachname(myModel.getNachname());
			} else {
				myModel.setNachname(nachname);
			}
		}
	}
	
	@Override
	public void onRandom() {
		belegeModel(myModel, (int)(Math.random()*IDENTEN.length));
		myView.fillViewWithModel(myModel);
	}
	
	private void belegeModel(PersonModel model, int ident) {
		myModel.setIdent(IDENTEN[ident]);
		myModel.setVorname(VORNAMEN[ident%VORNAMEN.length]);
		myModel.setNachname(NACHNAMEN[ident/NACHNAMEN.length]);
	}
	
	@Override
	public PersonViewListener_I getViewListener() {
		return this;
	}
}
