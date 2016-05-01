package GUI;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class MainGui extends Thread {
	
	public void run(){
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				GUI.Grafika window = new GUI.Grafika();
				window.dbWin.setVisible(true);
				API.ConnectToDb.Connect();
				API.Redis.ConnectRedis();
				API.Redis.clearStat();
			} catch (Exception e) {
				e.printStackTrace();

				int input = JOptionPane.showOptionDialog(null,
						"Error: Program sa nepodarilo naËÌtaù!", "Error!",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE, null, null, null);

				if (input == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
				
		}
	});
	
		Thread.yield();
	}
}
