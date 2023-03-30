import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class cashierlogin extends JFrame implements ActionListener {

	JLabel l1, l2, l3;
	JTextField t1;
	JPasswordField p1;
	JButton b1;
	String time, date;
	int cec, cec1;
	private JButton btnBack;

	public void s_no() {

		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select s_no from cashierlogindata order by s_no");
			rs.afterLast();

			if (rs.previous()) {
				cec = rs.getInt(1);
			} else {
				cec = 0;
			}
			cn.close();
			cec1 = ++cec;
		}

		catch (Exception e4) {
			JOptionPane.showMessageDialog(this, "Error cashier login");
		}
	}

	public void clock() {

		Calendar calen = new GregorianCalendar();
		int day = calen.get(Calendar.DAY_OF_MONTH);
		int month = calen.get(Calendar.MONTH);
		int month1 = month + 1;
		int year = calen.get(Calendar.YEAR);

		int sec = calen.get(Calendar.SECOND);
		int min = calen.get(Calendar.MINUTE);
		int hr = calen.get(Calendar.HOUR);

		time = hr + ":" + min + ":" + sec;
		date = day + "/" + month1 + "/" + year;

	}

	cashierlogin() {
		super("practice");
		setTitle("Cashier Login");
		setSize(500, 500);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		l3 = new JLabel("Cashier Login");
		l3.setFont(new Font("Times New Roman", Font.BOLD, 30));
		l3.setBounds(150, 30, 500, 100);
		getContentPane().add(l3);

		l1 = new JLabel("Name");
		l1.setBounds(100, 150, 100, 30);
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l1);

		t1 = new JTextField();
		t1.setBounds(250, 150, 150, 30);
		getContentPane().add(t1);

		l2 = new JLabel("Password");
		l2.setBounds(100, 200, 100, 30);
		l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l2);

		p1 = new JPasswordField();
		p1.setBounds(250, 200, 150, 30);
		getContentPane().add(p1);

		b1 = new JButton("Login");
		b1.setBounds(100, 270, 100, 30);
		getContentPane().add(b1);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Home();
			}
		});
		btnBack.setBounds(300, 270, 100, 30);
		getContentPane().add(btnBack);
		b1.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String a = t1.getText();
		String b = p1.getText();

		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st
					.executeQuery("select * from cashierdata where name='" + a + "'  and password='" + b + "'");
			boolean ss = rs.next();
			if (ss) {
				clock();
				s_no();
				ResultSet rs1 = st.executeQuery(
						"select cashier_id from cashierdata where name='" + a + "'  and password='" + b + "'");

				if (rs1.next()) {
					int c = rs1.getInt(1);
					st.executeUpdate("insert into cashierlogindata values('" + cec1 + "','" + c + "','" + a + "','"
							+ time + "','" + time + "','" + date + "')");
				}
				new cashier();
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Login Invalid");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
