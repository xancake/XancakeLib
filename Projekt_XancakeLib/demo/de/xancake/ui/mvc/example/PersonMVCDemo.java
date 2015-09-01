package de.xancake.ui.mvc.example;

public class PersonMVCDemo {
	public static void main(String... args) {
		PersonView view = new PersonView();
		PersonController controller = new PersonController();
		controller.setModel(new PersonModel());
		controller.setView(view);
		view.setVisible(true);
	}
}
