package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {

	private int time;
	private double latitude;
	private double longitude;
	private double elevation;
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {
		
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;	
	}

	// TODO - get/set metoder
	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getElevation() {
		return this.elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
	public String toString() {
		// "1 (2.0,3.0) 5.0\n"
		// der 1 er tiden, (2.0,3.0) er (breddegrad,lengdegrad) og 5.0 er høyden.
		StringBuilder str = new StringBuilder();
		str.append(this.time +" ");
		str.append("("+this.latitude+",");
		str.append(this.longitude+") ");
		str.append(this.elevation+"\n");
		
		return str.toString();
		// TODO
		
	}
}
