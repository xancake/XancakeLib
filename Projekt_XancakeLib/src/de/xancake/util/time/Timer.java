package de.xancake.util.time;

import java.util.Objects;

public class Timer {
	public static final Zeitmesser MILLIS = new SystemMillisZeitmesser();
	public static final Zeitmesser NANOS  = new SystemNanosZeitmesser();
	
	private Zeitmesser myZeitmesser;
	private long myStartNanos;
	
	public Timer() {
		this(NANOS);
	}
	
	public Timer(Zeitmesser messer) {
		myZeitmesser = Objects.requireNonNull(messer);
	}
	
	public void start() {
		myStartNanos = myZeitmesser.missZeit();
	}
	
	public long stop() {
		return myZeitmesser.missZeit() - myStartNanos;
	}
	
	public long stopAndRestart() {
		long time = stop();
		start();
		return time;
	}
	
	public static interface Zeitmesser {
		long missZeit();
	}
	
	private static class SystemNanosZeitmesser implements Zeitmesser {
		@Override
		public long missZeit() {
			return System.nanoTime();
		}
	}
	
	private static class SystemMillisZeitmesser implements Zeitmesser {
		@Override
		public long missZeit() {
			return System.currentTimeMillis();
		}
	}
}
