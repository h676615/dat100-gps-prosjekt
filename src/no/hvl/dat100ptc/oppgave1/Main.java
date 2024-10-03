package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		GPSPoint p1 = new GPSPoint(1, 2.0, 3.0, 5.0);
		System.out.println("p1 tid= "+p1.getTime());
		p1.setTime(2);
		System.out.println("p1 tid:"+p1.getTime());
		System.out.println(p1.toString());
		
		String t = "2017-08-13T08:52:26.000Z";
		System.out.println(t.substring(11,13));
		System.out.println(t.substring(14,16));
		System.out.println(t.substring(17,19));
		
	}

}
