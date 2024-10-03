package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {
		antall = 0;
		gpspoints = new GPSPoint[n];
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		boolean inserted = false;
		if(antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted = true;
		}
		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;
		 gpspoint = convert(time, latitude, longitude, elevation);
		 return insertGPS(gpspoint);
		
	}

	public void print() {
		
		System.out.println("====== GPS Data - START ======");
		for(int i = 0; i < gpspoints.length; i++) {
			System.out.print(gpspoints[i].toString());
		}
		System.out.println("====== GPS Data - SLUTT ======");
	}
	public int toSeconds(String timestr) {
		//"2017-08-13T08:52:26.000Z"
		int secs;
		int hr, min, sec;
		hr = 3600*Integer.parseInt(timestr.substring(11,13));
		min = 60*Integer.parseInt(timestr.substring(14,16));
		sec = Integer.parseInt(timestr.substring(17,19));
		secs = hr+min+sec;
		
		return secs;
	}

	public GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		double lati = Double.parseDouble(latitudeStr);
		double longi = Double.parseDouble(longitudeStr);
		double ele = Double.parseDouble(elevationStr);
		int time = toSeconds(timeStr);
		gpspoint = new GPSPoint(time, lati, longi, ele);
		return gpspoint;
		
	}
}
