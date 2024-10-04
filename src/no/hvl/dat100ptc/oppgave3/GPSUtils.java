package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {
		double min;
		min = da[0];
		
		for (double d : da) {
			if(d < min) {
				min = d;
			}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] lati = new double[gpspoints.length];
		
		for (int i = 0; i < lati.length; i++) {
			lati[i] = gpspoints[i].getLatitude();
		}
		return lati;

	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longi = new double[gpspoints.length];
		
		for (int i = 0; i < longi.length; i++) {
			longi[i] = gpspoints[i].getLongitude();
		}
		return longi;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
        double lati1 = gpspoint1.getLatitude();
        double long1 = gpspoint1.getLongitude();
        double lati2 = gpspoint2.getLatitude();
        double long2 = gpspoint2.getLongitude();
        
        double phi1 = Math.toRadians(lati1);
        double phi2 = Math.toRadians(lati2);
        double deltaPhi = Math.toRadians(lati2 - lati1);
        double deltaLambda = Math.toRadians(long2-long1);
        
        double a = compute_a(phi1, phi2, deltaPhi, deltaLambda);
        double c = compute_c(a);
		
        return R * c;

	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltalambda) {
		
		double sinDeltaPhi = Math.sin(deltaphi/2);
		double sinDeltaLambda = Math.sin(deltalambda/2);
		
		double a = Math.pow(sinDeltaPhi, 2)+Math.cos(phi1)*Math.cos(phi2)*Math.pow(sinDeltaLambda, 2);
		return a;

	}

	private static double compute_c(double a) {
		double c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		return c;
	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		
		double d = distance(gpspoint1, gpspoint2);
		int t1 = gpspoint1.getTime();
		int t2 = gpspoint2.getTime();

		int secs = t2-t1;
		if (secs == 0) {
		return 0;
		}
		double speed = d/secs;
		
		
		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		int time = secs/3600;
		int min = (secs%3600)/60;
		int sec = secs % 60;
		String format = String.format("%02d:%02d:%02d",time, min,sec);
		timestr = "  "+format;
		
		return timestr;
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {
		
		String str = Double.toString(roundDeci(d, 2));
		
		return String.format("%10s", str);
		
	}
	   public static double roundDeci(double value, int decimals) {
	        double scale = Math.pow(10, decimals);  
	        return Math.round(value * scale) / scale;  
	    }
}
