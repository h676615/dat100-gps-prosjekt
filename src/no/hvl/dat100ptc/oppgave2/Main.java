package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	
	public static void main(String[] args) {

		GPSPoint p1 = new GPSPoint(1,4,4,10);
		GPSPoint p2 = new GPSPoint(1,8,8,20);
		GPSPoint p3 = new GPSPoint(2,8,8,10);
		GPSData t1 = new GPSData(3);
		t1.insertGPS(p1);
		t1.insertGPS(p2);
		t1.insertGPS(p3);
		t1.print();
		
	}
}
