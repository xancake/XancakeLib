package de.xancake.util.time;

public class TimeUtils {
	public static final long MILLIS_TO_NANOS_FACTOR    = 1_000_000;
	
	public static final long SECONDS_TO_MILLIS_FACTOR  = 1000;
	public static final long SECONDS_TO_NANOS_FACTOR   = SECONDS_TO_MILLIS_FACTOR  * MILLIS_TO_NANOS_FACTOR;
	
	public static final long MINUTES_TO_SECONDS_FACTOR = 60;
	public static final long MINUTES_TO_MILLIS_FACTOR  = MINUTES_TO_SECONDS_FACTOR * SECONDS_TO_MILLIS_FACTOR;
	public static final long MINUTES_TO_NANOS_FACTOR   = MINUTES_TO_MILLIS_FACTOR  * MILLIS_TO_NANOS_FACTOR;
	
	public static final long HOURS_TO_MINUTES_FACTOR   = 60;
	public static final long HOURS_TO_SECONDS_FACTOR   = HOURS_TO_MINUTES_FACTOR   * MINUTES_TO_SECONDS_FACTOR;
	public static final long HOURS_TO_MILLIS_FACTOR    = HOURS_TO_SECONDS_FACTOR   * SECONDS_TO_MILLIS_FACTOR;
	public static final long HOURS_TO_NANOS_FACTOR     = HOURS_TO_MILLIS_FACTOR    * MILLIS_TO_NANOS_FACTOR;
	
	public static final long DAYS_TO_HOURS_FACTOR      = 24;
	public static final long DAYS_TO_MINUTES_FACTOR    = DAYS_TO_HOURS_FACTOR      * HOURS_TO_MINUTES_FACTOR;
	public static final long DAYS_TO_SECONDS_FACTOR    = DAYS_TO_MINUTES_FACTOR    * MINUTES_TO_SECONDS_FACTOR;
	public static final long DAYS_TO_MILLIS_FACTOR     = DAYS_TO_SECONDS_FACTOR    * SECONDS_TO_MILLIS_FACTOR;
	public static final long DAYS_TO_NANOS_FACTOR      = DAYS_TO_MILLIS_FACTOR     * MILLIS_TO_NANOS_FACTOR;
	
	private TimeUtils() {}
	
	public static long millisToNanos(double millis) {
		return (long)(millis * MILLIS_TO_NANOS_FACTOR);
	}
	
	public static long secondsToMillis(double seconds) {
		return (long)(seconds * SECONDS_TO_MILLIS_FACTOR);
	}
	
	public static long secondsToNanos(double seconds) {
		return (long)(seconds * SECONDS_TO_NANOS_FACTOR);
	}
	
	public static long minutesToSeconds(double minutes) {
		return (long)(minutes * MINUTES_TO_SECONDS_FACTOR);
	}
	
	public static long minutesToMillis(double minutes) {
		return (long)(minutes * MINUTES_TO_MILLIS_FACTOR);
	}
	
	public static long minutesToNanos(double minutes) {
		return (long)(minutes * MINUTES_TO_NANOS_FACTOR);
	}
	
	public static long hoursToMinutes(double hours) {
		return (long)(hours * HOURS_TO_MINUTES_FACTOR);
	}
	
	public static long hoursToSeconds(double hours) {
		return (long)(hours * HOURS_TO_SECONDS_FACTOR);
	}
	
	public static long hoursToMillis(double hours) {
		return (long)(hours * HOURS_TO_MILLIS_FACTOR);
	}
	
	public static long hoursToNanos(double hours) {
		return (long)(hours * HOURS_TO_NANOS_FACTOR);
	}
	
	public static long daysToHours(double days) {
		return (long)(days * DAYS_TO_HOURS_FACTOR);
	}
	
	public static long daysToMinutes(double days) {
		return (long)(days * DAYS_TO_MINUTES_FACTOR);
	}
	
	public static long daysToSeconds(double days) {
		return (long)(days * DAYS_TO_SECONDS_FACTOR);
	}
	
	public static long daysToMillis(double days) {
		return (long)(days * DAYS_TO_MILLIS_FACTOR);
	}
	
	public static long daysToNanos(double days) {
		return (long)(days * DAYS_TO_NANOS_FACTOR);
	}
	
	public static long nanosToDays(long nanos) {
		return (long)(nanos / (double)DAYS_TO_NANOS_FACTOR);
	}
	
	public static long nanosToHours(long nanos) {
		return (long)(nanos / (double)HOURS_TO_NANOS_FACTOR);
	}
	
	public static long nanosToMinutes(long nanos) {
		return (long)(nanos / (double)MINUTES_TO_NANOS_FACTOR);
	}
	
	public static long nanosToSeconds(long nanos) {
		return (long)(nanos / (double)SECONDS_TO_NANOS_FACTOR);
	}
	
	public static long nanosToMillis(long nanos) {
		return (long)(nanos / (double)MILLIS_TO_NANOS_FACTOR);
	}
	
	public static long millisToDays(long millis) {
		return (long)(millis / (double)DAYS_TO_MILLIS_FACTOR);
	}
	
	public static long millisToHours(long millis) {
		return (long)(millis / (double)HOURS_TO_MILLIS_FACTOR);
	}
	
	public static long millisToMinutes(long millis) {
		return (long)(millis / (double)MINUTES_TO_MILLIS_FACTOR);
	}
	
	public static long millisToSeconds(long millis) {
		return (long)(millis / (double)SECONDS_TO_MILLIS_FACTOR);
	}
	
	public static long secondsToDays(long seconds) {
		return (long)(seconds / (double)DAYS_TO_SECONDS_FACTOR);
	}
	
	public static long secondsToHours(long seconds) {
		return (long)(seconds / (double)HOURS_TO_SECONDS_FACTOR);
	}
	
	public static long secondsToMinutes(long seconds) {
		return (long)(seconds / (double)MINUTES_TO_SECONDS_FACTOR);
	}
	
	public static long minutesToDays(long minutes) {
		return (long)(minutes / (double)DAYS_TO_MINUTES_FACTOR);
	}
	
	public static long minutesToHours(long minutes) {
		return (long)(minutes / (double)HOURS_TO_MINUTES_FACTOR);
	}
	
	public static long hoursToDays(long hours) {
		return (long)(hours / (double)DAYS_TO_HOURS_FACTOR);
	}
}