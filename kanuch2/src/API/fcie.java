package API;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

public class fcie {
	// Panel Arrivals
	// fcie-----------------------------------------------------------------------------------------------
	public static void btnArrival() {

		String sql = new String(
				"SELECT id_doctor as Doctor, surname as Surename FROM doctors ORDER BY id_doctor ASC;");
		ResultSet result = API.SelectFromDb.select(sql);
		GUI.Grafika.table_7.setModel(DbUtils.resultSetToTableModel(result));
		/*
		 * DefaultTableModel model = (DefaultTableModel)
		 * DbUtils.resultSetToTableModel(result); Vector nazvy = new Vector();
		 * 
		 * nazvy.add("ID"); nazvy.add("DOCTOR"); model.insertRow(0, nazvy);
		 * GUI.Grafika.table_7.setModel(model);
		 */
	}

	public static void showRooms(String string) {

		if (string.equals("free")) {
			String sql = new String(
					"SELECT r.id as room FROM Room r WHERE r.free = '1' ORDER BY r.id ASC;");
			ResultSet result = API.SelectFromDb.select(sql);
			GUI.Grafika.table_5.setModel(DbUtils.resultSetToTableModel(result));
			/*
			 * DefaultTableModel model = (DefaultTableModel)
			 * DbUtils.resultSetToTableModel(result); Vector nazvy = new
			 * Vector();
			 * 
			 * nazvy.add("ID"); model.insertRow(0, nazvy);
			 * GUI.Grafika.table_5.setModel(model);
			 */
			String sql2 = new String(
					"SELECT (SELECT count(free) FROM Room WHERE free='1'), count(*) FROM Room;");
			ResultSet result2 = API.SelectFromDb.select(sql2);
			GUI.Grafika.table_6
					.setModel(DbUtils.resultSetToTableModel(result2));
		} else {
			String sql = new String(
					"SELECT r.id as room FROM Room r WHERE r.free = '0' ORDER BY r.id ASC;");
			ResultSet result = API.SelectFromDb.select(sql);
			GUI.Grafika.table_5.setModel(DbUtils.resultSetToTableModel(result));
			/*
			 * DefaultTableModel model = (DefaultTableModel)
			 * DbUtils.resultSetToTableModel(result); Vector nazvy = new
			 * Vector();
			 * 
			 * nazvy.add("ID"); model.insertRow(0, nazvy);
			 * 
			 * GUI.Grafika.table_5.setModel(model);
			 */
			String sql2 = new String(
					"SELECT (SELECT count(free) FROM Room WHERE free='0'), count(*) FROM Room;");
			ResultSet result2 = API.SelectFromDb.select(sql2);
			GUI.Grafika.table_6
					.setModel(DbUtils.resultSetToTableModel(result2));
		}
	}

	public static void viewArr() {
		if (!GUI.Grafika.chckbxViewAllStays.isSelected()) {
			int x = 0;
			String a = new String(GUI.Grafika.date_choice.getSelectedItem()
					.toString());
			String q = new String(
					"SELECT  c.id_client as PID , c.name_c as name, c.surname as surename FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE s.From_date = '"
							+ a + " 00:00:00' AND book  = 'FALSE';");
			ResultSet result = API.SelectFromDb.select(q);
			GUI.Grafika.table.setModel(DbUtils.resultSetToTableModel(result));
		} else {
			int x = 0;
			String q = new String(
					"SELECT  c.id_client as PID , c.name_c as name, c.surname as surename FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE book  = 'TRUE';");
			ResultSet result = API.SelectFromDb.select(q);
			GUI.Grafika.table.setModel(DbUtils.resultSetToTableModel(result));
		}
		/*
		 * DefaultTableModel model = (DefaultTableModel)
		 * DbUtils.resultSetToTableModel(result); Vector nazvy = new Vector();
		 * 
		 * nazvy.add("ID"); nazvy.add("NAME"); nazvy.add("SURENAME");
		 * 
		 * model.insertRow(0, nazvy);
		 * 
		 * GUI.Grafika.table.setModel(model);
		 */
	}

	public static void chooseRoom() {
		int row = GUI.Grafika.table_1.getSelectedRow();
		String koho = new String(GUI.Grafika.table_1.getValueAt(0, 7)
				.toString());
		int row2 = GUI.Grafika.table_5.getSelectedRow();
		String aku = new String(GUI.Grafika.table_5.getValueAt(row2, 0)
				.toString());
		GUI.Grafika.table_1.setValueAt(aku, 0, 5);
		String sql2 = new String("UPDATE Stay SET id = '" + aku
				+ "',book = 'TRUE' WHERE id_stay = '" + koho + "';");
		API.UpdateDb.Update(sql2);
		String sql3 = new String("UPDATE Room SET free = '0' WHERE id = '"
				+ aku + "';");
		Connection conn = API.ConnectToDb.getConn();
		try {
			conn.setAutoCommit(false);
			API.UpdateDb.Update(sql2);
			API.UpdateDb.Update(sql3);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		infoClientArr();
		viewArr();
		showRooms("free");

	}

	public static void chooseDoctor() {
		String koho = new String(GUI.Grafika.table_1.getValueAt(0, 7)
				.toString());
		int row = GUI.Grafika.table_7.getSelectedRow();
		String doktor = new String(GUI.Grafika.table_7.getValueAt(row, 0)
				.toString());
		String sql = new String("UPDATE Stay SET id_doctor = " + doktor
				+ " WHERE id_stay = '" + koho + "';");
		API.UpdateDb.Update(sql);
		GUI.Grafika.table_1.setValueAt(doktor, 0, 6);
	}

	public static void infoClientArr() {
		String q;
		int row = GUI.Grafika.table.getSelectedRow();
		if (row < 0)
			return;
		String table_click = (GUI.Grafika.table.getModel().getValueAt(row, 0)
				.toString());
		String a = new String(GUI.Grafika.date_choice.getSelectedItem()
				.toString());
		if (!GUI.Grafika.chckbxViewAllStays.isSelected()) {
			q = new String(
					"SELECT c.id_client, c.name_c, c.surname, c.address, to_char(c.date_birth,'dd.MM.YYYY') as birth , s.id, s.id_doctor, s.id_stay FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE c.id_client = '"
							+ table_click
							+ "' AND s.From_date = '"
							+ a
							+ " 00:00:00';");
		} else {
			q = new String(
					"SELECT c.id_client, c.name_c, c.surname, c.address, to_char(c.date_birth,'dd.MM.YYYY') as birth, s.id, s.id_doctor, s.id_stay FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE c.id_client = '"
							+ table_click + "' AND s.book = 'TRUE'");
		}

		ResultSet result = API.SelectFromDb.select(q);
		GUI.Grafika.table_1.setModel(DbUtils.resultSetToTableModel(result));
		/*
		 * DefaultTableModel model = (DefaultTableModel) DbUtils
		 * .resultSetToTableModel(result); Vector nazvy = new Vector();
		 * 
		 * nazvy.add("ID"); nazvy.add("NAME"); nazvy.add("SURENAME");
		 * nazvy.add("ADDRESS"); nazvy.add("AGE"); nazvy.add("ROOM");
		 * nazvy.add("DOCTOR"); nazvy.add("STAY NUMBER");
		 * 
		 * model.insertRow(0, nazvy);
		 * 
		 * GUI.Grafika.table_1.setModel(model);
		 */

		// GUI.Grafika.table_1.
	}

	public static void updateZaznam(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			int row = GUI.Grafika.table_1.getSelectedRow();
			int column = GUI.Grafika.table_1.getSelectedColumn();
			String koho = new String(GUI.Grafika.table_1.getValueAt(row, 0)
					.toString());
			String nove = new String(GUI.Grafika.table_1
					.getValueAt(row, column).toString());
			if (column <= 4) {
				String co = new String(GUI.Grafika.table_1
						.getColumnName(column).toString());
				if (co.equals("birth"))
					co = "date_birth";
				String sql = new String("UPDATE Client SET " + co + " = '"
						+ nove + "' WHERE id_client = '" + koho + "';");
				API.UpdateDb.Update(sql);
			} else if (column == 5) {

			}

		}
		Redis.clearStat();
	}

	// Panel Departures
	// fcie-------------------------------------------------------------------------------

	public static void viewDepar() {
		int x = 0;
		String a = new String(GUI.Grafika.date_choice2.getSelectedItem()
				.toString());
		String q;
		if (!GUI.Grafika.checkBox.isSelected()) {
			q = new String(
					"SELECT  s.id_stay, c.id_client, c.name_c, c.surname, s.to_date FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE s.To_date = '"
							+ a + " 00:00:00' AND book = 'TRUE';");
		} else {
			// int x = 0;
			q = new String(
					"SELECT  s.id_stay, c.id_client, c.name_c, c.surname, s.to_date FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE s.To_date < '"
							+ a + " 00:00:00' AND book = 'TRUE';");
			ResultSet result = API.SelectFromDb.select(q);
			// GUI.Grafika.table.setModel(DbUtils.resultSetToTableModel(result));
		}

		ResultSet result = API.SelectFromDb.select(q);
		GUI.Grafika.table_3.setModel(DbUtils.resultSetToTableModel(result));
		/*
		 * DefaultTableModel model = (DefaultTableModel)
		 * DbUtils.resultSetToTableModel(result); Vector nazvy = new Vector();
		 * 
		 * nazvy.add("STAY"); nazvy.add("ID"); nazvy.add("NAME");
		 * nazvy.add("SURENAME"); nazvy.add("TO DATE");
		 * 
		 * model.insertRow(0, nazvy);
		 * 
		 * GUI.Grafika.table_3.setModel(model);
		 */
	}

	public static void chcekout() throws SQLException {
		String aku = (GUI.Grafika.table_4.getModel().getValueAt(0, 4)
				.toString());
		int row = GUI.Grafika.table_3.getSelectedRow();
		String koho = (GUI.Grafika.table_3.getModel().getValueAt(row, 0)
				.toString());
		String sql3 = new String("UPDATE Room SET free = '1' WHERE id = '"
				+ aku + "';");

		String sql4 = new String(
				"UPDATE stay SET book = 'FALSE' WHERE id_stay = '" + koho
						+ "';");
		Connection conn = ConnectToDb.getConn();
		try {
			conn.setAutoCommit(false);
			API.UpdateDb.Update(sql3);
			API.UpdateDb.Update(sql4);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		viewDepar();
		conn.setAutoCommit(true);
	}

	public static void chooseClient2() {
		int row = GUI.Grafika.table_3.getSelectedRow();
		if (row < 0)
			return;
		String table_click = (GUI.Grafika.table_3.getModel().getValueAt(row, 0)
				.toString());
		String a = new String(GUI.Grafika.date_choice2.getSelectedItem()
				.toString());
		String q = new String(
				"SELECT c.id_client, c.name_c, c.surname, c.address, s.id FROM Client c INNER JOIN Stay s ON c.id_client = s.ID_Client WHERE s.id_stay = '"
						+ table_click + "';");
		ResultSet result = API.SelectFromDb.select(q);
		GUI.Grafika.table_4.setModel(DbUtils.resultSetToTableModel(result));
		/*
		 * DefaultTableModel model = (DefaultTableModel)
		 * DbUtils.resultSetToTableModel(result); Vector nazvy = new Vector();
		 * 
		 * nazvy.add("ID"); nazvy.add("NAME"); nazvy.add("SURNAME");
		 * nazvy.add("ADDRESS"); nazvy.add("ROOM");
		 * 
		 * model.insertRow(0, nazvy);
		 * 
		 * GUI.Grafika.table_4.setModel(model);
		 */
	}

	// CLientPanel
	// fcie-----------------------------------------------------------------------------------
	public static void clientPanel() {
		GUI.Grafika.A_Panel.setVisible(false);
		GUI.Grafika.D_Panel.setVisible(false);
		GUI.Grafika.C_Panel.setVisible(true);
		GUI.Grafika.S_Panel.setVisible(false);
		GUI.Grafika.Do_Panel.setVisible(false);
		String sql = new String(
				"SELECT c.id_client,c.name_c,c.surname FROM Client c ORDER BY c.surname ASC;");
		ResultSet result = API.SelectFromDb.select(sql);
		GUI.Grafika.table_2.setModel(DbUtils.resultSetToTableModel(result));

	}

	public static void addStay() {
		String id = new String(GUI.Grafika.txtId_c.getText().toString());
		String from = new String(GUI.Grafika.txtFrom.getText().toString());
		String to = new String(GUI.Grafika.txtTo.getText().toString());
		String sql = new String(
				"INSERT INTO Stay (from_date, to_date, id_client, book) VALUES('"
						+ from + " 00:00:00','" + to + " 00:00:00', " + id
						+ ", 'FALSE' );");
		InsertIntoDb.Insert(sql);
		GUI.Grafika.addstayPanel.setVisible(false);
		infoClientPanel();
		Redis.clearStat();
	}

	public static void addClient() {
		String sql = new String("INSERT INTO client VALUES('"
				+ new String(GUI.Grafika.txtIdClient.getText().toString())
				+ "','" + new String(GUI.Grafika.txtName.getText().toString())
				+ "','"
				+ new String(GUI.Grafika.txtSurname.getText().toString())
				+ "');");
		API.InsertIntoDb.Insert(sql);
		GUI.Grafika.addcPanel.setVisible(false);
		clientPanel();
		API.Redis.clearStat();
	}

	public static void infoClientPanel() {
		GUI.Grafika.addstayPanel.setVisible(true);
		GUI.Grafika.addcPanel.setVisible(false);
		int row = GUI.Grafika.table_2.getSelectedRow();
		String koho = new String(GUI.Grafika.table_2.getValueAt(row, 0)
				.toString());
		GUI.Grafika.txtId_c.setText(koho);
		String sql = new String(
				"SELECT to_char(from_date,'dd.MM.YYYY') as from, to_char(to_date,'dd.MM.YYYY') as to, id, book  FROM Stay WHERE id_client = '"
						+ koho + "';");
		ResultSet result = API.SelectFromDb.select(sql);
		GUI.Grafika.table_8.setModel(DbUtils.resultSetToTableModel(result));
	}

	public static void searchClient() {
		String a = new String(GUI.Grafika.txtSurename.getText().toString());
		String b = new String(GUI.Grafika.txtPid.getText().toString());
		if (b.equals("PID") || b.equals("")) {
			b = new String("");// "^\\d+ ?");
		}
		// System.out.print(a);
		String sql = new String(
				"SELECT c.id_client,c.name_c,c.surname FROM Client c WHERE CAST(c.id_client AS TEXT) LIKE '"
						+ b
						+ "%' and  c.surname LIKE '"
						+ a
						+ "%' ORDER BY c.surname ASC;");
		ResultSet result = API.SelectFromDb.select(sql);
		GUI.Grafika.table_2.setModel(DbUtils.resultSetToTableModel(result));

	}

	// Service panel
	// fcie----------------------------------------------------------------------------------------------------------------------------------------

	public static void DeleteDoctor() throws SQLException {
		String koho = GUI.Grafika.txtIdDoctor.getText().toString();
		if (!koho.equals("1")) {
			Connection conn = ConnectToDb.getConn();
			String sql = new String(
					"UPDATE stay SET id_doctor = '1' WHERE id_doctor = '"
							+ koho + "';");

			String sql2 = new String("DELETE FROM doctors WHERE id_doctor = '"
					+ koho + "';");
			try {
				conn.setAutoCommit(false);
				API.UpdateDb.Update(sql);
				API.DeleteFromDb.Delete(sql2);
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				conn.rollback();

			}
		} else {
			GUI.Grafika.StatusBar.setText("Cannot do this operation");
		}

	}

	public static void addProc() {
		String procedura = new String(GUI.Grafika.txtName_3.getText()
				.toString());
		String sql = new String("INSERT INTO procedures(name) VALUES('"
				+ procedura + "');");
		API.UpdateDb.Update(sql);

	}

	public static void addDoctor() {
		GUI.Grafika.addDocPanel.setVisible(false);
		String name = new String(GUI.Grafika.txtName_2.getText().toString());
		String surname = new String(GUI.Grafika.txtSurname_1.getText()
				.toString());
		String university = new String(GUI.Grafika.txtUniversity.getText()
				.toString());
		String password = new String(GUI.Grafika.txtPassword_1.getText()
				.toString());
		String sql = new String(
				"INSERT INTO doctors(name,surname,university,password) VALUES('"
						+ name + "','" + surname + "','" + university
						+ "', crypt('" + password + "', '$1$/aJ27ovG'));");
		API.InsertIntoDb.Insert(sql);
		// String hashuj = new
		// String("UPDATE doctors SET pswhash = crypt( '"password,
		// gen_salt('md5'));");
	}
	
	
//statistic***********************************************************************************************
	// Pocet pacientov doktorov
	public static ResultSet STATISTIC_Doctors() {
		String sql;
		ResultSet result;
		sql = new String(
				"SELECT count(*), d.surname FROM stay s JOIN doctors d ON s.id_doctor = d.id_doctor GROUP BY d.id_doctor ;");
		result = API.SelectFromDb.select(sql);
		System.out.println(true);
		return result;
	}

	// priemerny vek
	public static ResultSet STATISTIC_avg_age() {
		String sql;
		ResultSet result;
		sql = new String(
				"SELECT round(avg(EXTRACT(YEAR FROM age(date_birth)))) FROM client;");
		result = API.SelectFromDb.select(sql);

		return result;
	}

	// priemerny vek po skupinach
	public static ResultSet STATISTIC_age_group() {
		String sql;
		ResultSet result;
		sql = new String(
				"SELECT CASE WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 0 AND 9 THEN '0-1' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 10 AND 19 THEN '10-19' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 20 AND 29 THEN '20-29' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 30 AND 39 THEN '30-39' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 40 AND 49 THEN '40-49' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 50 AND 59 THEN '50-59' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 60 AND 69 THEN '60-69' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 70 AND 79 THEN '70-79' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 80 AND 89 THEN '80-89' WHEN EXTRACT(YEAR FROM age(date_birth)) BETWEEN 90 AND 99 THEN '90-99' END as Dobre, round(avg(EXTRACT(YEAR FROM age(date_birth)))), count(*) FROM client GROUP BY 1 ORDER BY 1;");
		result = API.SelectFromDb.select(sql);

		return result;
	}

	// najcastejsie zvolena procedura
	public static ResultSet STATISTIC_procedur() {
		String sql;
		ResultSet result;
		sql = new String(
				"SELECT d.name, d.count FROM (SELECT proc.name, count(*) FROM procclient p JOIN procedures proc ON p.id_procedure = proc.id_procedure GROUP BY proc.name) AS d ORDER BY d.count DESC LIMIT 1;");
		result = API.SelectFromDb.select(sql);

		return result;
	}

	// najcastejsie mena
	public static ResultSet STATISTIC_name() {
		String sql;
		ResultSet result;
		sql = new String(
				"SELECT name_c, count(*) FROM client GROUP BY name_c ORDER BY count Desc LIMIT 5;");
		result = API.SelectFromDb.select(sql);

		return result;
	}

	// Najcastejsi klient
	public static ResultSet STATISTIC_klient() {
		String sql;
		ResultSet result;
		sql = new String(
				"SELECT surname, count FROM ( SELECT c.surname, count(*) FROM stay s JOIN client c ON s.id_client = c.id_client GROUP BY c.surname) AS a ORDER BY count DESC LIMIT 1;");
		result = API.SelectFromDb.select(sql);

		return result;
	}

	// DOctor Panel
	// fcie------------------------------------------------------------------------------------------------------
	public static void logDoctor() {
		String meno = new String(GUI.Grafika.txtName_1.getText().toString());
		String heslo = new String(GUI.Grafika.txtPassword.getText().toString());
		String sql = new String(
				"SELECT count(*) FROM doctors WHERE password = crypt('" + heslo
						+ "','$1$/aJ27ovG') and surname = '" + meno + "';");
		ResultSet result = API.SelectFromDb.select(sql);
		try {
			result.next();
			String tr = result.getString("count");
			if (tr.equals("1")) {
				GUI.Grafika.Doctor.setVisible(true);
				String sql2 = new String(
						"SELECT c.*, s.id_stay FROM Client c INNER JOIN Stay s ON s.id_client = c.id_client INNER JOIN Doctors d ON d.id_doctor = s.id_doctor WHERE d.surname = '"
								+ meno + "' AND s.book = true;");
				ResultSet rs = API.SelectFromDb.select(sql2);
				GUI.Grafika.table_9.setModel(DbUtils.resultSetToTableModel(rs));
				String sql3 = new String(
						"SELECT id_procedure, name FROM procedures;");
				ResultSet rs2 = API.SelectFromDb.select(sql3);
				GUI.Grafika.table_10.setModel(DbUtils
						.resultSetToTableModel(rs2));
			} else {
				GUI.Grafika.StatusBar.setText("Bad password");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static void SaveCard() {
		int row = GUI.Grafika.table_9.getSelectedRow();
		String koho = new String(GUI.Grafika.table_9.getValueAt(row, 0)
				.toString());
		String str = new String(GUI.Grafika.textArea.getText().toString());
		String sql = new String("UPDATE Stay SET about = '" + str
				+ "' WHERE id_client = '" + koho + "';");
		API.UpdateDb.Update(sql);

	}

	public static void addProcClient() {
		int row = GUI.Grafika.table_9.getSelectedRow();
		String koho = new String(GUI.Grafika.table_9.getValueAt(row, 5)
				.toString());
		int row2 = GUI.Grafika.table_10.getSelectedRow();
		String proc = new String(GUI.Grafika.table_10.getValueAt(row2, 0)
				.toString());
		String sql = new String(
				"INSERT INTO procclient(id_stay, id_procedure) VALUES('" + koho
						+ "','" + proc + "');");
		API.InsertIntoDb.Insert(sql);
		chooseClientDoctor();
	}

	public static void chooseClientDoctor() {
		int row = GUI.Grafika.table_9.getSelectedRow();
		String koho = new String(GUI.Grafika.table_9.getValueAt(row, 5)
				.toString());
		String sql = new String("SELECT about FROM Stay WHERE id_stay = '"
				+ koho + "';");
		ResultSet result = API.SelectFromDb.select(sql);
		try {
			result.next();
			String str = result.getString(1);
			GUI.Grafika.textArea.setText(str);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		String id = new String(GUI.Grafika.table_9.getValueAt(row, 5)
				.toString());
		String sql2 = new String(
				"SELECT p.name FROM procedures p JOIN procclient pr ON p.id_procedure = pr.id_procedure WHERE pr.id_stay = '"
						+ id + "';");
		ResultSet rs = API.SelectFromDb.select(sql2);
		GUI.Grafika.table_11.setModel(DbUtils.resultSetToTableModel(rs));

	}

}
