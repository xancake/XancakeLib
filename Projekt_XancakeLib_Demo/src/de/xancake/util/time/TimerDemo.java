package de.xancake.util.time;

public class TimerDemo {
	public static void main(String... args) {
		Timer timer = new Timer(Timer.NANOS);
		Profiler profiler = new Profiler(timer);
		
		long dauer = profiler.profile(() -> {
			for(int i=0; i<Integer.MAX_VALUE; i++) {}
		});
		
		System.out.println("Dauer: " + dauer + "ns");
	}
}
