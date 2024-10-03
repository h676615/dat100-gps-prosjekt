package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		//"2017-08-13T08:52:26.000Z"
		int secs;
		int hr, min, sec;
		hr = 3600*Integer.parseInt(timestr.substring(11,13));
		min = 60*Integer.parseInt(timestr.substring(14,16));
		sec = Integer.parseInt(timestr.substring(17,19));
		secs = hr+min+sec;
		
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		double lati = Double.parseDouble(latitudeStr);
		double longi = Double.parseDouble(longitudeStr);
		double ele = Double.parseDouble(elevationStr);
		int time = toSeconds(timeStr);
		gpspoint = new GPSPoint(time, lati, longi, ele);
		return gpspoint;
		
	}
	
}
