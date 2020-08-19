package test2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class main2 {
	public static void main(String args[]) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("current: " + df.format(cal1.getTime()));
		cal1.add(Calendar.DATE,-1);
		System.out.println("current: " + df.format(cal1.getTime()));
	}
	
	
}
