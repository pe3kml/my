package GUI;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import API.InsertIntoDb;

import java.awt.Scrollbar;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;

import redis.clients.jedis.Jedis;

public class Grafika {

	public JFrame dbWin;
	public static JLabel StatusBar;
	public static JPanel mainPanel;
	public static JPanel A_Panel;
	public static JPanel D_Panel;
	public static JPanel C_Panel;
	public static JPanel S_Panel;
	public static JPanel Do_Panel;
	public static JTextField txtName;
	public static JTextField txtSurname;
	public static JTextField txtDateofbirth;
	public static Choice date_choice;
	public static Choice date_choice2;
	public static JTextField txtId_c;
	public static JTextField txtFrom;
	public static JTextField txtTo;
	public static JTextField txtName_1;
	public static JTextField txtPassword;
	public static JTextField txtName_2;
	public static JTextField txtSurname_1;
	public static JTextField txtUniversity;
	public static JTextField txtPassword_1;
	public static JButton btnAdddoctor;
	public static JButton btnAddDoctor;
	public static JPanel addDocPanel;
	public static Button btnx3;
	public static JButton btnLogDoc;
	public static JPanel addcPanel;
	public static JLabel lblName;
	public static JLabel lblSurname;
	public static JPanel addProcPanel;
	public static JTextField txtName_3;
	public static JPanel addstayPanel;
	public static JPanel Doctor;
	public static JTable table;
	public static JTable table_1;
	public static JTextField txtIdClient;
	public static JTable table_2;
	public static JTextField txtSurename;
	public static JTable table_3;
	public static JTable table_4;
	public static JTable table_5;
	public static JTable table_6;
	public static JLabel lblNewLabel_1;
	public static JTable table_7;
	public static JTable table_8;
	public static JTable table_9;
	public static TextArea textArea;
	public static JTable table_10;
	public static JTable table_11;
	public static JLabel lblNewLabel_3;
	public static JTextField txtIdDoctor;
	public static JRadioButton rdbtnOccupated;
	public static JRadioButton rdbtnFree;
	public static ButtonGroup bg1;
	public static JCheckBox chckbxViewAllStays;
	private Scrollbar scrollbar;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JScrollPane scrollPane_6;
	private JScrollPane scrollPane_7;
	public static JTextArea TAStatistic;
	private JScrollPane scrollPane_8;
	public static JCheckBox checkBox;
	private JScrollPane scrollPane_9;
	private JScrollPane scrollPane_10;
	private JScrollPane scrollPane_11;
	public static JTextField txtPid;
	private JButton btnHelp;
	public static JPanel help;
	private JTextArea helpTA;

	public Grafika() throws IOException {
		initialize();
	}

	private void initialize()/* throws IOException */{

		Toolkit tk = Toolkit.getDefaultToolkit();

		ImageIcon icon = new ImageIcon(
				"C:\\Users\\Peter\\Desktop\\fiit\\4.semester\\DBS\\projekt - db\\logo.jpg");

		Dimension screenSize = tk.getScreenSize();

		dbWin = new JFrame("ISSPA 1.0");
		dbWin.setResizable(false);
		dbWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dbWin.getContentPane().setLayout(null);
		// set properties
		dbWin.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		// dbWin.setUndecorated(true);
		dbWin.setVisible(true);

		help = new JPanel();
		help.setBounds(1, 28, 1358, 708);
		dbWin.getContentPane().add(help);
		help.setLayout(null);

		helpTA = new JTextArea();
		helpTA.setFont(new Font("Dialog", Font.BOLD, 25));
		helpTA.setEditable(false);
		helpTA.setBounds(1, 28, 1358, 708);
		help.add(helpTA);
		help.setVisible(false);

		C_Panel = new JPanel();
		C_Panel.setToolTipText("surename");
		C_Panel.setBounds(1, 28, 1358, 708);
		dbWin.getContentPane().add(C_Panel);
		C_Panel.setLayout(null);

		addstayPanel = new JPanel();
		addstayPanel.setBounds(0, 100, 385, 647);
		C_Panel.add(addstayPanel);
		addstayPanel.setLayout(null);
		addstayPanel.setVisible(false);

		Button x2 = new Button("x");
		x2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addstayPanel.setVisible(false);
			}
		});
		x2.setBounds(362, 0, 23, 23);
		addstayPanel.add(x2);

		txtId_c = new JTextField();
		txtId_c.setVisible(false);
		txtId_c.setEnabled(false);
		txtId_c.setText("ID client ");
		txtId_c.setBounds(93, 27, 114, 20);
		addstayPanel.add(txtId_c);
		txtId_c.setColumns(10);

		txtFrom = new JTextField();
		txtFrom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtFrom.setText("");
			}
		});
		txtFrom.setText("From");
		txtFrom.setBounds(93, 59, 114, 20);
		addstayPanel.add(txtFrom);
		txtFrom.setColumns(10);

		txtTo = new JTextField();
		txtTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTo.setText("");
			}
		});
		txtTo.setText("To");
		txtTo.setBounds(93, 91, 114, 20);
		addstayPanel.add(txtTo);
		txtTo.setColumns(10);

		JButton btnAddStaytoDb = new JButton("Add stay");
		btnAddStaytoDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				API.fcie.addStay();
			}
		});
		btnAddStaytoDb.setBounds(204, 146, 98, 26);
		addstayPanel.add(btnAddStaytoDb);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(32, 61, 55, 16);
		addstayPanel.add(lblFrom);

		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(32, 93, 55, 16);
		addstayPanel.add(lblTo);

		JButton btnAddClient = new JButton("Add client");
		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcPanel.setVisible(true);
				addstayPanel.setVisible(false);
			}
		});
		btnAddClient.setBounds(12, 12, 98, 26);
		C_Panel.add(btnAddClient);

		addcPanel = new JPanel();
		addcPanel.setBounds(0, 100, 385, 647);
		C_Panel.add(addcPanel);
		addcPanel.setLayout(null);

		txtName = new JTextField();
		txtName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
			}
		});
		txtName.setText("Name");
		txtName.setBounds(92, 25, 114, 20);
		addcPanel.add(txtName);
		txtName.setColumns(10);

		txtSurname = new JTextField();
		txtSurname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSurname.setText("");
			}
		});
		txtSurname.setText("Surname");
		txtSurname.setBounds(92, 57, 114, 20);
		addcPanel.add(txtSurname);
		txtSurname.setColumns(10);

		txtDateofbirth = new JTextField();
		txtDateofbirth.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDateofbirth.setText("");
			}
		});
		txtDateofbirth.setText("Date of Birth");
		txtDateofbirth.setBounds(92, 89, 114, 20);
		addcPanel.add(txtDateofbirth);
		txtDateofbirth.setColumns(10);

		lblName = new JLabel("Name");
		lblName.setBounds(32, 27, 55, 16);
		addcPanel.add(lblName);

		lblSurname = new JLabel("Surname");
		lblSurname.setBounds(32, 59, 55, 16);
		addcPanel.add(lblSurname);

		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(32, 91, 55, 16);
		addcPanel.add(lblDateOfBirth);

		JLabel lblNewLabel = new JLabel("Id client");
		lblNewLabel.setBounds(32, 126, 55, 16);
		addcPanel.add(lblNewLabel);

		JButton btnAdd_to_DB = new JButton("Add");
		btnAdd_to_DB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				API.fcie.addClient();
			}
		});
		btnAdd_to_DB.setBounds(235, 170, 98, 26);
		addcPanel.add(btnAdd_to_DB);

		Button x1 = new Button("x");
		x1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addcPanel.setVisible(false);
			}
		});
		x1.setBounds(362, 0, 23, 23);
		addcPanel.add(x1);

		txtIdClient = new JTextField();
		txtIdClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIdClient.setText("");
			}
		});
		txtIdClient.setText("Id client");
		txtIdClient.setBounds(92, 121, 114, 20);
		addcPanel.add(txtIdClient);
		txtIdClient.setColumns(10);

		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(974, 86, 384, 622);
		C_Panel.add(scrollPane_6);

		table_2 = new JTable();
		scrollPane_6.setViewportView(table_2);
		table_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				API.fcie.infoClientPanel();
			}
		});

		JLabel lblSearch = new JLabel("Search:");
		lblSearch.setBounds(974, 67, 55, 16);
		C_Panel.add(lblSearch);

		txtSurename = new JTextField();
		txtSurename.setText("surname");
		txtSurename.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				API.fcie.searchClient();
			}
		});

		txtSurename.setBounds(1027, 65, 114, 20);
		C_Panel.add(txtSurename);
		txtSurename.setColumns(10);

		scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(523, 461, 452, 247);
		C_Panel.add(scrollPane_7);

		table_8 = new JTable();
		scrollPane_7.setViewportView(table_8);
		table_8.setBorder(new LineBorder(new Color(0, 0, 0)));

		txtPid = new JTextField();
		txtPid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				API.fcie.searchClient();
			}
		});
		txtPid.setText("PID");
		txtPid.setBounds(1149, 65, 114, 20);
		C_Panel.add(txtPid);
		txtPid.setColumns(10);
		addcPanel.setVisible(false);
		C_Panel.setVisible(false);

		D_Panel = new JPanel();
		D_Panel.setBounds(0, 28, 1358, 708);
		dbWin.getContentPane().add(D_Panel);
		D_Panel.setLayout(null);

		date_choice2 = new Choice();
		date_choice2.setBounds(1056, 32, 159, 22);
		D_Panel.add(date_choice2);

		Label label_2 = new Label("Choose date");
		label_2.setBounds(970, 32, 80, 23);
		D_Panel.add(label_2);

		JButton btnView_2 = new JButton("View");
		btnView_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				API.fcie.viewDepar();
			}
		});
		btnView_2.setBounds(1248, 59, 98, 26);
		D_Panel.add(btnView_2);

		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(974, 86, 384, 622);
		D_Panel.add(scrollPane_4);

		table_3 = new JTable();
		scrollPane_4.setViewportView(table_3);
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				API.fcie.chooseClient2();
			}
		});
		table_3.setBorder(new LineBorder(new Color(0, 0, 0)));

		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 86, 973, 43);
		D_Panel.add(scrollPane_5);

		table_4 = new JTable();
		scrollPane_5.setViewportView(table_4);
		table_4.setBackground(UIManager.getColor("Button.background"));

		JButton btbBookOut = new JButton("Check out");
		btbBookOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					API.fcie.chcekout();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btbBookOut.setBounds(864, 134, 98, 26);
		D_Panel.add(btbBookOut);

		checkBox = new JCheckBox("View actual stays before selected date");
		checkBox.setBounds(994, 60, 221, 24);
		D_Panel.add(checkBox);
		D_Panel.setVisible(false);
		Do_Panel = new JPanel();
		Do_Panel.setBounds(0, 31, 1358, 708);
		dbWin.getContentPane().add(Do_Panel);
		Do_Panel.setLayout(null);

		txtName_1 = new JTextField();
		txtName_1.setText("Pokeman");
		txtName_1.setBounds(23, 12, 114, 20);
		Do_Panel.add(txtName_1);
		txtName_1.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setText("prof");
		txtPassword.setBounds(149, 12, 114, 20);
		Do_Panel.add(txtPassword);
		txtPassword.setColumns(10);

		btnLogDoc = new JButton("Log in");
		btnLogDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				API.fcie.logDoctor();

			}
		});
		btnLogDoc.setBounds(280, 9, 98, 26);
		Do_Panel.add(btnLogDoc);

		Doctor = new JPanel();
		Doctor.setBackground(SystemColor.menu);
		Doctor.setBounds(0, 53, 1358, 655);
		Do_Panel.add(Doctor);
		Doctor.setLayout(null);

		scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(0, 33, 384, 622);
		Doctor.add(scrollPane_10);

		table_9 = new JTable();
		scrollPane_10.setViewportView(table_9);
		table_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				API.fcie.chooseClientDoctor();
			}
		});
		table_9.setBorder(new LineBorder(new Color(0, 0, 0)));

		textArea = new TextArea();
		textArea.setBounds(770, 310, 588, 345);
		Doctor.add(textArea);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.SaveCard();
			}
		});
		btnSave.setBounds(1248, 274, 98, 26);
		Doctor.add(btnSave);

		scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(384, 242, 385, 413);
		Doctor.add(scrollPane_9);

		table_10 = new JTable();
		scrollPane_9.setViewportView(table_10);
		table_10.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.addProcClient();
			}
		});
		btnAdd.setBounds(576, 213, 98, 26);
		Doctor.add(btnAdd);

		scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(390, 44, 180, 149);
		Doctor.add(scrollPane_11);

		table_11 = new JTable();
		scrollPane_11.setViewportView(table_11);
		table_11.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(icon);
		lblNewLabel_2.setBounds(965, 0, 381, 214);
		Doctor.add(lblNewLabel_2);

		Label label = new Label("Choose procedure for client:");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(390, 213, 180, 23);
		Doctor.add(label);

		JLabel lblClientsProcedures = new JLabel("CLient`s procedures:");
		lblClientsProcedures.setBounds(390, 23, 128, 16);
		Doctor.add(lblClientsProcedures);

		Doctor.setVisible(false);
		Do_Panel.setVisible(false);

		S_Panel = new JPanel();
		S_Panel.setBounds(0, 31, 1358, 708);
		dbWin.getContentPane().add(S_Panel);
		S_Panel.setLayout(null);
		S_Panel.setVisible(false);

		btnAddDoctor = new JButton("Add doctor");
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDocPanel.setVisible(true);
				addProcPanel.setVisible(false);
			}
		});
		btnAddDoctor.setBounds(12, 12, 98, 26);
		S_Panel.add(btnAddDoctor);

		JButton btnAddProcedure = new JButton("Add procedure");
		btnAddProcedure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProcPanel.setVisible(true);
				addDocPanel.setVisible(false);

			}
		});
		btnAddProcedure.setBounds(122, 12, 98, 26);
		S_Panel.add(btnAddProcedure);

		addProcPanel = new JPanel();
		addProcPanel.setBounds(0, 100, 385, 390);
		S_Panel.add(addProcPanel);
		addProcPanel.setLayout(null);

		Button btnx6 = new Button("x");
		btnx6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProcPanel.setVisible(false);
			}
		});
		btnx6.setBounds(362, 0, 23, 23);
		addProcPanel.add(btnx6);

		txtName_3 = new JTextField();
		txtName_3.setText("Name");
		txtName_3.setBounds(42, 26, 114, 20);
		addProcPanel.add(txtName_3);
		txtName_3.setColumns(10);

		JButton btnAddProc = new JButton("Add procedure");
		btnAddProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.addProc();
			}
		});
		btnAddProc.setBounds(175, 23, 114, 26);
		addProcPanel.add(btnAddProc);
		addProcPanel.setVisible(false);

		addDocPanel = new JPanel();
		addDocPanel.setBounds(0, 100, 385, 195);
		S_Panel.add(addDocPanel);
		addDocPanel.setLayout(null);
		addDocPanel.setVisible(false);

		txtName_2 = new JTextField();
		txtName_2.setBounds(94, 31, 114, 20);
		txtName_2.setText("Name");
		addDocPanel.add(txtName_2);
		txtName_2.setColumns(10);

		txtSurname_1 = new JTextField();
		txtSurname_1.setText("Surname");
		txtSurname_1.setBounds(94, 63, 114, 20);
		addDocPanel.add(txtSurname_1);
		txtSurname_1.setColumns(10);

		txtUniversity = new JTextField();
		txtUniversity.setText("University");
		txtUniversity.setBounds(94, 95, 114, 20);
		addDocPanel.add(txtUniversity);
		txtUniversity.setColumns(10);

		btnAdddoctor = new JButton("Add Doctor");
		btnAdddoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				API.fcie.addDoctor();
			}
		});
		btnAdddoctor.setBounds(225, 132, 98, 26);
		addDocPanel.add(btnAdddoctor);

		btnx3 = new Button("x");
		btnx3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDocPanel.setVisible(false);
			}
		});
		btnx3.setBounds(362, 0, 23, 23);
		addDocPanel.add(btnx3);

		txtPassword_1 = new JTextField();
		txtPassword_1.setText("Password");
		txtPassword_1.setBounds(94, 135, 114, 20);
		addDocPanel.add(txtPassword_1);
		txtPassword_1.setColumns(10);

		txtIdDoctor = new JTextField();
		txtIdDoctor.setText("Id doctor");
		txtIdDoctor.setBounds(232, 12, 78, 26);
		S_Panel.add(txtIdDoctor);
		txtIdDoctor.setColumns(10);

		JButton btnDeleteDoctor = new JButton("Delete doctor");
		btnDeleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					API.fcie.DeleteDoctor();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnDeleteDoctor.setBounds(320, 12, 98, 26);
		S_Panel.add(btnDeleteDoctor);

		scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(410, 117, 948, 591);
		S_Panel.add(scrollPane_8);

		TAStatistic = new JTextArea();
		scrollPane_8.setViewportView(TAStatistic);
		TAStatistic.setFont(new Font("Dialog", Font.PLAIN, 15));
		S_Panel.setVisible(false);
		// dbWin.setResizable(true);

		A_Panel = new JPanel();
		A_Panel.setBounds(0, 28, 1358, 708);
		dbWin.getContentPane().add(A_Panel);
		A_Panel.setLayout(null);

		date_choice = new Choice();
		date_choice.setBounds(1051, 32, 159, 22);
		A_Panel.add(date_choice);

		Label label_1 = new Label("Choose date");
		label_1.setBounds(974, 32, 80, 23);
		A_Panel.add(label_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(974, 86, 384, 622);
		A_Panel.add(scrollPane_1);

		bg1 = new ButtonGroup();
		table = new JTable();
		scrollPane_1.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				API.fcie.infoClientArr();
			}
		});
		table.setToolTipText("");
		table.setFont(new Font("Dialog", Font.PLAIN, 15));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));

		JButton btnView_1 = new JButton("View");
		btnView_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.viewArr();
			}
		});
		btnView_1.setBounds(1234, 48, 98, 26);
		A_Panel.add(btnView_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 86, 970, 49);
		A_Panel.add(scrollPane_2);

		table_1 = new JTable();
		scrollPane_2.setViewportView(table_1);
		table_1.setBackground(UIManager.getColor("Button.background"));
		table_1.setForeground(new Color(51, 51, 51));
		table_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		table_1.setColumnSelectionAllowed(true);
		table_1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {}));
		table_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				API.fcie.updateZaznam(e);
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(786, 347, 187, 367);
		A_Panel.add(scrollPane);

		table_5 = new JTable();
		scrollPane.setViewportView(table_5);
		table_5.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblRooms = new JLabel("Rooms:");
		lblRooms.setBounds(754, 284, 55, 16);
		A_Panel.add(lblRooms);

		JButton btnChoose = new JButton("Choose room");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.chooseRoom();

			}
		});
		btnChoose.setBounds(845, 309, 117, 26);
		A_Panel.add(btnChoose);

		table_6 = new JTable();
		table_6.setBounds(918, 263, 55, 16);
		A_Panel.add(table_6);

		lblNewLabel_1 = new JLabel("Status frre/occup. from all");
		lblNewLabel_1.setBounds(754, 262, 159, 16);
		A_Panel.add(lblNewLabel_1);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(552, 583, 194, 125);
		A_Panel.add(scrollPane_3);

		table_7 = new JTable();
		scrollPane_3.setViewportView(table_7);
		table_7.setBorder(new LineBorder(new Color(0, 0, 0)));

		JLabel lblDoctors = new JLabel("Doctors:");
		lblDoctors.setBounds(552, 555, 55, 16);
		A_Panel.add(lblDoctors);

		JButton btnChooseDoctor = new JButton("Choose doctor");
		btnChooseDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.chooseDoctor();
			}
		});
		btnChooseDoctor.setBounds(642, 545, 109, 26);
		A_Panel.add(btnChooseDoctor);

		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(377, 130, 400, 230);
		A_Panel.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(icon);
		rdbtnOccupated = new JRadioButton("occupated");
		rdbtnFree = new JRadioButton("free");
		bg1.add(rdbtnOccupated);
		bg1.add(rdbtnFree);
		ActionListener sliceActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton aButton = (AbstractButton) actionEvent
						.getSource();
				API.fcie.showRooms(aButton.getText());
			}
		};
		rdbtnOccupated.addActionListener(sliceActionListener);
		rdbtnFree.addActionListener(sliceActionListener);

		rdbtnFree.setBounds(811, 280, 65, 24);
		A_Panel.add(rdbtnFree);

		rdbtnOccupated.setBounds(881, 280, 85, 24);
		A_Panel.add(rdbtnOccupated);

		chckbxViewAllStays = new JCheckBox("View all actual stays");
		chckbxViewAllStays.setBounds(974, 60, 153, 24);
		A_Panel.add(chckbxViewAllStays);
		A_Panel.setVisible(false);

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1360, 739);
		dbWin.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		JLabel logo = new JLabel("");
		logo.setBounds(0, 37, 374, 190);
		logo.setIcon(icon);
		mainPanel.add(logo);
		mainPanel.setVisible(false);
		mainPanel.setBackground(Color.WHITE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 529, 28);
		mainPanel.add(menuBar);

		JButton btnArrivals = new JButton("Arrivals");
		btnArrivals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUI.Grafika.A_Panel.setVisible(true);
				GUI.Grafika.D_Panel.setVisible(false);
				GUI.Grafika.C_Panel.setVisible(false);
				GUI.Grafika.S_Panel.setVisible(false);
				GUI.Grafika.Do_Panel.setVisible(false);
				GUI.Grafika.help.setVisible(false);
				API.fcie.btnArrival();
			}
		});
		menuBar.add(btnArrivals);

		JButton btnDepartures = new JButton("Departures");
		btnDepartures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				A_Panel.setVisible(false);
				D_Panel.setVisible(true);
				C_Panel.setVisible(false);
				S_Panel.setVisible(false);
				Do_Panel.setVisible(false);
				help.setVisible(false);
			}
		});
		menuBar.add(btnDepartures);

		JButton btnClients = new JButton("Clients");
		btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				API.fcie.clientPanel();
			}
		});
		menuBar.add(btnClients);

		JButton btnServices = new JButton("Services");
		btnServices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				A_Panel.setVisible(false);
				D_Panel.setVisible(false);
				C_Panel.setVisible(false);
				S_Panel.setVisible(true);
				Do_Panel.setVisible(false);
				// API.fcie.Statistic();
				help.setVisible(false);

				GUI.Grafika.TAStatistic.setText("");
				GUI.Grafika.TAStatistic.append("Štatistiky: \n");
				GUI.Grafika.TAStatistic
						.append("  Poèet pacientov doktorov: \n ");
				Jedis jedis = API.Redis.getJedis();
				if (jedis.lrange("Doctors", 0, -1).isEmpty()) {
					API.Redis.statisticRedisDoctors();
					TAStatistic.append(jedis.lrange("Doctors", 0, -1)
							.toString().replace("[", "").replace("]", "")
							.replace(",", ""));
					System.out.println("tu som");
				} else {
					TAStatistic.append(jedis.lrange("Doctors", 0, -1)
							.toString().replace("[", "").replace("]", "")
							.replace(",", ""));
					System.out.println("von som");
				}

				GUI.Grafika.TAStatistic.append("\n  Priemerný vek klientov: ");
				if (jedis.lrange("avg_vek", 0, -1).isEmpty()) {
					API.Redis.statisticRedisAvgAge();
					TAStatistic.append(jedis.lrange("avg_vek", 0, -1)
							.toString().replace("[", "").replace("]", ""));
				} else {
					TAStatistic.append(jedis.lrange("avg_vek", 0, -1)
							.toString().replace("[", "").replace("]", ""));
				}

				GUI.Grafika.TAStatistic
						.append("\n  Poèet a priemerný vek klientov v jednotlivých rozsahoch: \n ");
				if (jedis.lrange("age_group", 0, -1).isEmpty()) {
					API.Redis.statisticRedisAgeGroup();
					TAStatistic.append(jedis.lrange("age_group", 0, -1)
							.toString().replace("[", "").replace("]", "")
							.replace(",", ""));
				} else {
					TAStatistic.append(jedis.lrange("age_group", 0, -1)
							.toString().replace("[", "").replace("]", "")
							.replace(",", ""));
				}

				GUI.Grafika.TAStatistic
						.append("\n  Najèastejšie zvolená procedúra:  ");
				if (jedis.lrange("procedure", 0, -1).isEmpty()) {
					API.Redis.statisticRedisProcedur();
					TAStatistic.append(jedis.lrange("procedure", 0, -1)
							.toString().replace("[", "").replace("]", "")
							.replace(",", ""));
				} else {
					TAStatistic.append(jedis.lrange("procedure", 0, -1)
							.toString().replace("[", "").replace("]", "")
							.replace(",", ""));
				}

				GUI.Grafika.TAStatistic.append("  Najèastejšie mená: \n ");
				if (jedis.lrange("names", 0, -1).isEmpty()) {
					API.Redis.statisticRedisMeno();
					TAStatistic
							.append(jedis.lrange("names", 0, -1).toString()
									.replace("[", "").replace("]", "")
									.replace(",", ""));
					System.out.println("dnu som");
				} else {
					TAStatistic
							.append(jedis.lrange("names", 0, -1).toString()
									.replace("[", "").replace("]", "")
									.replace(",", ""));
				}

				GUI.Grafika.TAStatistic
						.append("\n  Klient s najväèším poètom pobytov :");
				if (jedis.lrange("client", 0, -1).isEmpty()) {
					API.Redis.statisticRedisKlient();
					TAStatistic
							.append(jedis.lrange("client", 0, -1).toString()
									.replace("[", "").replace("]", "")
									.replace(",", ""));
					System.out.println("dnu som");
				} else {
					TAStatistic
							.append(jedis.lrange("client", 0, -1).toString()
									.replace("[", "").replace("]", "")
									.replace(",", ""));
				}
			}
		});
		menuBar.add(btnServices);

		JButton btnDoctors = new JButton("Doctors");
		btnDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Do_Panel.setVisible(true);
				A_Panel.setVisible(false);
				D_Panel.setVisible(false);
				C_Panel.setVisible(false);
				S_Panel.setVisible(false);
				help.setVisible(false);
				// Do_Panel.setVisible(true);
				// mainPanel.setVisible(false);
			}
		});
		menuBar.add(btnDoctors);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addDocPanel.setVisible(false);
				addstayPanel.setVisible(false);
				addcPanel.setVisible(false);
				A_Panel.setVisible(false);
				D_Panel.setVisible(false);
				C_Panel.setVisible(false);
				S_Panel.setVisible(false);
				Do_Panel.setVisible(false);
				mainPanel.setVisible(false);
				help.setVisible(false);
				API.ConnectToDb.DisConnect();
				System.exit(0);

			}
		});

		btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_Panel.setVisible(false);
				D_Panel.setVisible(false);
				C_Panel.setVisible(false);
				S_Panel.setVisible(false);
				Do_Panel.setVisible(false);
				help.setVisible(true);
				helpTA.append("Pre jednoduché používanie aplikácie si preèítajte priloženú dokumentáciu k programu. \n\n\b S pozdravom tím pekaSoft");
			}
		});
		menuBar.add(btnHelp);
		menuBar.add(btnQuit);

		StatusBar = new JLabel("");
		StatusBar.setBounds(1132, 0, 198, 16);
		mainPanel.add(StatusBar);

	}

	public JPanel getC_Panel() {
		return C_Panel;
	}
}
