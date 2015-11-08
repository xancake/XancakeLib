package de.xancake.util.time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Profiler {
	private Timer myTimer;
	
	public Profiler() {
		this(new Timer());
	}
	
	public Profiler(Timer timer) {
		myTimer = timer;
	}
	
	/**
	 * Misst die Ausführungszeit der Aktion in Millisekunden.
	 * @param action Die zu profilierende Aktion
	 * @return Die Ausführungszeit der Aktion in Millisekunden
	 */
	public long profile(Action action) {
		myTimer.start();
		action.execute();
		return myTimer.stop();
	}
	
	public List<Long> profile(List<Action> actions) {
		List<Long> times = new ArrayList<>(actions.size());
		for(Action action : actions) {
			times.add(profile(action));
		}
		return times;
	}
	
	public List<Long> profile(Action... actions) {
		return profile(Arrays.asList(actions));
	}
	
	public List<Long> profileFixedStart(List<Action> actions) {
		List<Long> times = new ArrayList<>(actions.size());
		myTimer.start();
		for(Action action : actions) {
			action.execute();
			times.add(myTimer.stop());
		}
		return times;
	}
	
	public List<Long> profileFixedStart(Action... actions) {
		return profileFixedStart(Arrays.asList(actions));
	}
	
	public static abstract class Action {
		public abstract void execute();
	}
}
