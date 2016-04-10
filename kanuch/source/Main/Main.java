package Main;

import java.awt.Component;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import GUI.MainGui;
import Objects.Client;

public class Main {
	public static void main(String[] args) {
		MainGui vlaknoGui = new MainGui();
		vlaknoGui.start();

		
		//API.ConnectToDb.Connect();
		//API.SelectFromDb.select();
		//API.ConnectToDb.DisConnect();
		 API.Date.genDate();
		 
	}

}
