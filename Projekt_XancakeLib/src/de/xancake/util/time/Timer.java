package de.xancake.util.time;

import java.util.Objects;

/**
 * Eine Klasse die zum Zeitmessen verwendet werden kann wie eine Stoppuhr.
 */
public class Timer {
	/**
	 * Standardimplementation für eine Genauigkeit im Millisekunden-Bereich.
	 * <p>
	 * Intern realisiert durch {@link System#currentTimeMillis()}.
	 */
	public static final Zeitmesser MILLIS = System::currentTimeMillis;
	
	/**
	 * Standardimplementation für eine Genauigkeit im Nanosekunden-Bereich.
	 * <p>
	 * Intern realisiert durch {@link System#nanoTime()}.
	 */
	public static final Zeitmesser NANOS = System::nanoTime;
	
	private Zeitmesser myZeitmesser;
	private long myStartNanos;
	
	/**
	 * Initialisiert einen neuen Timer in Nanosekunden Genauigkeit.
	 */
	public Timer() {
		this(NANOS);
	}
	
	/**
	 * Initialisiert einen neuen Timer dessen Genauigkeit von dem übergebenen {@link Zeitmesser} festgelegt wird.
	 * 
	 * @param messer Der Zeitmesser
	 * @see #NANOS
	 * @see #MILLIS
	 */
	public Timer(Zeitmesser messer) {
		myZeitmesser = Objects.requireNonNull(messer);
	}
	
	/**
	 * Setzt den Startzeitpunkt des Timers auf die aktuelle Zeit.
	 */
	public void start() {
		myStartNanos = myZeitmesser.missZeit();
	}
	
	/**
	 * Nimmt die Zeit seit dem letzten {@link #start()} und gibt sie zurück.
	 * <p>
	 * Aufeinander folgende Aufrufe von {@code stop()} erzeugen Werte die alle relativ zu der genommenen Startzeit sind.
	 * Somit ist für jeden späteren Aufruf mehr Zeit vergangen, als für seinen Vorgänger.
	 * <p>
	 * Falls gewünscht ist die Zeit zu messen die seit dem letzten {@code stop()} vergangen ist, bietet sich
	 * {@link #stopAndRestart()} an.
	 * 
	 * @return Die Zeit seit dem letzten {@link #start()} in der über den Konstruktor vorgegebenen Genauigkeit
	 */
	public long stop() {
		return myZeitmesser.missZeit() - myStartNanos;
	}
	
	/**
	 * Nimmt die Zeit seit dem letzten {@link #start()} und startet den Timer erneut.
	 * <p>
	 * Aufeinander folgende Aufrufe von {@code stopAndRestart()} erzeugen Werte die immer relativ zu einem vorherigen
	 * {@link #start()} oder {@code stopAndRestart()} sind.
	 * 
	 * @return Die Zeit seit dem letzten {@link #start()} in der über den Konstruktor vorgegebenen Genauigkeit
	 */
	public long stopAndRestart() {
		long time = stop();
		start();
		return time;
	}
	
	/**
	 * Schnittstelle für das Messen der aktuellen Zeit.
	 */
	@FunctionalInterface
	public interface Zeitmesser {
		/**
		 * Liefert die aktuelle Zeit als {@code long}.
		 * 
		 * @return Die aktuelle Zeit
		 */
		long missZeit();
	}
}
