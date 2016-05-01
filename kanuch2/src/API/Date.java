package API;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



public class Date {
	private static ArrayList datumy = new ArrayList();
	
	public static ArrayList getDatumy() {
		return datumy;
	}

	public static void setDatumy(ArrayList datumy) {
		Date.datumy = datumy;
	}

	public static void genDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
		   //get current date time with Date()
		   Date date = new Date();
		  
		  
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		 
		   for(int i = 0; i < 7; i++){
 			   String den = dateFormat.format(cal.getTime());
			   cal.add(Calendar.DATE, 1);
			   datumy.add(den);
			  // System.out.println(den);
		   };
		   
	}

}
