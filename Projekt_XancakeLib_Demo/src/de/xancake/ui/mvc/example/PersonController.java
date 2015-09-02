package de.xancake.ui.mvc.example;

import java.util.Arrays;
import de.xancake.ui.mvc.ControllerListener_I;
import de.xancake.ui.mvc.window.WindowController_A;

public class PersonController extends WindowController_A<PersonModel, PersonViewListener_I, SwingPersonView, ControllerListener_I> implements PersonViewListener_I {
	private static final Integer[] IDENTEN;
	private static final String[]  VORNAMEN  = {"Lars", "Hans", "Sven", "Peter", "Marie", "Jennifer", "Melanie", "Larissa"};
	private static final String[]  NACHNAMEN = {"Nielsen", "Wurst", "Schneider", "Planlos", "Mertens", "Momsen", "Mutig", "Lustig"};
	
	static {
		IDENTEN = new Integer[VORNAMEN.length*NACHNAMEN.length];
		for(int i=0; i<IDENTEN.length; i++) {
			IDENTEN[i] = i;
		}
	}
	
	public PersonController() {
		super(new PersonModel(), new SwingPersonView());
	}
	
	@Override
	public void onIdentChanged(int ident) {
		if(ident != getModel().getIdent()) {
			if(Arrays.asList(IDENTEN).contains(ident)) {
				belegeModel(getModel(), ident);
			} else {
				getModel().setIdent(ident);
				getModel().setVorname("");
				getModel().setNachname("");
			}
			update();
		}
	}
	
	@Override
	public void onVornameChanged(String vorname) {
		if(vorname != null && !vorname.equals(getModel().getVorname())) {
			if(Arrays.asList(VORNAMEN).contains(vorname)) {
				getView().showFehlermeldung("Der Vorname " + vorname + " ist bereits vergeben!");
				getView().setVorname(getModel().getVorname());
			} else {
				getModel().setVorname(vorname);
			}
		}
	}
	
	@Override
	public void onNachnameChanged(String nachname) {
		if(nachname != null && !nachname.equals(getModel().getNachname())) {
			if(Arrays.asList(NACHNAMEN).contains(nachname)) {
				getView().showFehlermeldung("Der Nachname " + nachname + " ist bereits vergeben!");
				getView().setNachname(getModel().getNachname());
			} else {
				getModel().setNachname(nachname);
			}
		}
	}
	
	@Override
	public void onRandom() {
		belegeModel(getModel(), (int)(Math.random()*IDENTEN.length));
		update();
	}
	
	private void belegeModel(PersonModel model, int ident) {
		model.setIdent(IDENTEN[ident]);
		model.setVorname(VORNAMEN[ident%VORNAMEN.length]);
		model.setNachname(NACHNAMEN[ident/NACHNAMEN.length]);
	}
	
	@Override
	public void onViewOpened() {}
	
	@Override
	public void onViewClosed() {}
	
	@Override
	protected PersonViewListener_I getViewListener() {
		return this;
	}
}
