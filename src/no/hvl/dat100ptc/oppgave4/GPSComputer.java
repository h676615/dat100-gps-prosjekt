package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;
		for(int i = 0; i < gpspoints.length-1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		}
		return distance;
	}

	public double totalElevation() {

		double elevation = 0;
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			double p1 = gpspoints[i].getElevation();
			double p2 = gpspoints[i+1].getElevation();
			if (p1 < p2) {
				elevation += p2-p1;
			}
		}
		return elevation;
	}

	public int totalTime() {

		int tid = gpspoints[gpspoints.length-1].getTime() -  gpspoints[0].getTime();
		return tid;
		
	}
		

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length-1];
		
		for(int i = 0; i < speeds.length;i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
		}
		return speeds; 
	}
	
	public double maxSpeed() {
		double[] speeds = speeds();
		double maxspeed = 0;
		for (int i = 1; i <speeds.length; i++) {
			if(maxspeed < speeds[i]) {
				maxspeed = speeds[i];
			}
		}
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;
		average = totalDistance()/totalTime();
		return average;
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;	
		double timer = (double)secs/3600;
		double speedmph = speed * MS;
		
		if (speedmph < 10) {
	        met = 4.0;
	    } else if (speedmph < 12) {
	        met = 6.0;
	    } else if (speedmph < 14) {
	        met = 8.0;
	    } else if (speedmph < 16) {
	        met = 10.0;
	    } else if (speedmph < 20) {
	        met = 12.0;
	    } else {
	        met = 16.0;
	    }
		
		kcal = met*weight*timer;
		return kcal;

	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double[] speeds = speeds();
		GPSPoint[] points = getGPSPoints();
		
		for (int i = 0; i < speeds.length; i++) {
			int secs = points[i+1].getTime() - points[i].getTime();
			double speed = speeds[i];
			
			totalkcal += kcal(weight, secs, speed);
		}
		
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {
		String tid = GPSUtils.formatTime(totalTime());
		double totalDis = GPSUtils.roundDeci(totalDistance()/1000, 2);
		double totalEle = GPSUtils.roundDeci(totalElevation(), 2);
		double maxSpeed = GPSUtils.roundDeci(maxSpeed()*3.6, 2);
		double aveSpeed = GPSUtils.roundDeci(averageSpeed()*3.6, 2);
		double energy = GPSUtils.roundDeci(totalKcal(WEIGHT), 2);
		
		String sep = "==============================================\n";
		String[][] tekster = {
				{"Total distance","km"},
				{"Total elevation","m"},
				{"Max speed", "km/t"},
				{"Average speed","km/t"},
				{"Energy","kcal"}
		};
		double[] verdier = {totalDis,totalEle,maxSpeed,aveSpeed,energy};
		StringBuilder display = new StringBuilder();
		display.append(sep);
		display.append(String.format("%-15s", "Total Time"));
		display.append(":");
		display.append(String.format("%11s", tid)+"\n");
		
		for(int i = 0; i < verdier.length;i++) {
			
			String t1 = tekster[i][0];
			String t2 = tekster[i][1];
			String tall = String.format("%.2f", verdier[i]);
			
			StringBuilder svar = new StringBuilder();
			svar.append(String.format("%-15s", t1));
			svar.append(":");
			svar.append(String.format("%11s", tall));
			svar.append(" "+t2+"\n");
			display.append(svar.toString());
		}
		display.append(sep);
		System.out.print(display.toString());

		
	}

}
