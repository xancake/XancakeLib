package de.xancake.ui.mvc.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.xancake.ui.mvc.View_A;
import de.xancake.ui.mvc.window.WindowView_I;

public class PersonView extends View_A<PersonModel, PersonViewListener_I> implements WindowView_I<PersonModel, PersonViewListener_I> {
	private JFrame myFrame;
	private JPanel myContent;
	private JSpinner myIdentField;
	private JTextField myVornameField;
	private JTextField myNachnameField;
	private JButton myRandomButton;
	
	public PersonView() {
		initComponents();
		initListeners();
		initLayout();
	}
	
	private void initComponents() {
		myFrame = new JFrame("PersonView");
		myContent = new JPanel();
		myIdentField = new JSpinner(new SpinnerNumberModel(1000, 0, Integer.MAX_VALUE, 1));
		myVornameField = new JTextField();
		myNachnameField = new JTextField();
		myRandomButton = new JButton("Zufällig belegen");
	}
	
	private void initListeners() {
		myIdentField.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				myListener.onIdentChanged((Integer)myIdentField.getValue());
			}
		});
		
		myVornameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myListener.onVornameChanged(myVornameField.getText());
			}
		});
		myVornameField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				myListener.onVornameChanged(myVornameField.getText());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		
		myNachnameField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myListener.onNachnameChanged(myNachnameField.getText());
			}
		});
		myNachnameField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				myListener.onNachnameChanged(myNachnameField.getText());
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
			}
		});
		
		myRandomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myListener.onRandom();
			}
		});
	}
	
	private void initLayout() {
		myContent.setLayout(new BoxLayout(myContent, BoxLayout.PAGE_AXIS));
		myContent.add(myIdentField);
		myContent.add(myVornameField);
		myContent.add(myNachnameField);
		myContent.add(myRandomButton);
		myFrame.setContentPane(myContent);
		myFrame.pack();
		myFrame.setLocationRelativeTo(null);
	}
	
	public void showFehlermeldung(String message) {
		JOptionPane.showMessageDialog(myFrame, message, "Fehler", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void fillViewWithModel(PersonModel person) {
		setIdent(person.getIdent());
		setVorname(person.getVorname());
		setNachname(person.getNachname());
	}
	
	public void setIdent(int ident) {
		myIdentField.setValue(ident);
	}
	
	public void setVorname(String vorname) {
		myVornameField.setText(vorname);
	}
	
	public void setNachname(String nachname) {
		myNachnameField.setText(nachname);
	}
	
	@Override
	public boolean isVisible() {
		return myFrame.isVisible();
	}
	
	@Override
	public void setVisible(boolean visible) {
		myFrame.setVisible(visible);
	}
}
