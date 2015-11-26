package de.xancake.util.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Eine Klasse die es ermöglicht, die Zeit zu messen, die eine bestimmte Aktion benötigt.
 */
public class Profiler {
	private Timer myTimer;
	
	/**
	 * Initialisiert den Profiler in Nanosekunden Genauigkeit.
	 */
	public Profiler() {
		this(new Timer());
	}
	
	/**
	 * Initialisiert den Profiler mit dem übergebenen Timer.
	 * Über den Timer kann die Genauigkeit festgelegt werden.
	 * @param timer Der Timer
	 * @see Timer#Timer(de.xancake.util.time.Timer.Zeitmesser)
	 */
	public Profiler(Timer timer) {
		myTimer = timer;
	}
	
	/**
	 * Misst die Zeit, die für die Durchführung der übergebenen Aktion benötigt wurde.
	 * @param action Die durchzuführende Aktion
	 * @return Die Zeit, die für die Aktion benötigt wurde, in der Genauigkeit des Profilers
	 */
	public long profile(Action action) {
		myTimer.start();
		action.execute();
		return myTimer.stop();
	}
	
	/**
	 * Misst die Zeiten, die für die Durchführung der übergebenen Aktionen benötigt wurden.
	 * <p>Die Zeiten werden unabhängig voneinander betrachtet. Somit können die Zeiten
	 * direkt in Vergleich gestellt werden. 
	 * @param actions Die durchzuführenden Aktionen in einer Liste
	 * @return Die Zeiten, die für die Aktionen benötigt wurden, in der Genauigkeit des Profilers
	 */
	public List<Long> profile(List<Action> actions) {
		List<Long> times = new ArrayList<>(actions.size());
		for(Action action : actions) {
			times.add(profile(action));
		}
		return times;
	}
	
	/**
	 * Misst die Zeiten, die für die Durchführung der übergebenen Aktionen benötigt wurden.
	 * <p>Die Zeiten werden unabhängig voneinander betrachtet. Somit können die Zeiten
	 * direkt in Vergleich gestellt werden.
	 * <p>Diese Methode dient als Konvenienz-Methode für {@link #profile(List)}.
	 * @param actions Die durchzuführenden Aktionen
	 * @return Die Zeiten, die für die Aktionen benötigt wurden, in der Genauigkeit des Profilers
	 */
	public List<Long> profile(Action... actions) {
		return profile(Arrays.asList(actions));
	}
	
	/**
	 * Misst die Zeiten, die für die Durchführung der übergebenen Aktionen benötigt wurden.
	 * <p>Die gemessenen Zeiten stehen alle relativ zu einem Startzeitpunkt.
	 * @param actions Die durchzuführenden Aktionen
	 * @return Die Zeiten, die für die Aktionen benötigt wurden, in der Genauigkeit des Profilers
	 */
	public List<Long> profileFixedStart(List<Action> actions) {
		List<Long> times = new ArrayList<>(actions.size());
		myTimer.start();
		for(Action action : actions) {
			action.execute();
			times.add(myTimer.stop());
		}
		return times;
	}
	
	/**
	 * Misst die Zeiten, die für die Durchführung der übergebenen Aktionen benötigt wurden.
	 * <p>Die gemessenen Zeiten stehen alle relativ zu einem Startzeitpunkt.
	 * <p>Diese Methode dient als Konvenienz-Methode für {@link #profileFixedStart(List)}.
	 * @param actions Die durchzuführenden Aktionen
	 * @return Die Zeiten, die für die Aktionen benötigt wurden, in der Genauigkeit des Profilers
	 */
	public List<Long> profileFixedStart(Action... actions) {
		return profileFixedStart(Arrays.asList(actions));
	}
	
	@FunctionalInterface
	public interface Action {
		void execute();
	}
}
