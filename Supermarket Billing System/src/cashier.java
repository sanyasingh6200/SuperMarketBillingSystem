import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class cashier extends JFrame implements ActionListener {
	JButton b1, b2, b3, b4;
	String time;
	int cec;
	String cec1, cec2;

	public void clock() {

		Calendar calen = new GregorianCalendar();

		int sec = calen.get(Calendar.SECOND);
		int min = calen.get(Calendar.MINUTE);
		int hr = calen.get(Calendar.HOUR);

		time = hr + ":" + min + ":" + sec;
	}

	cashier() {
		super("Practice");

		setSize(1389, 864);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		this.getContentPane().setBackground(Color.getHSBColor(154, 254, 25));
		getContentPane().setLayout(null);
		setResizable(false);

		b1 = new JButton("Create Bill");
		b1.setBounds(20, 10, 200, 40);
		getContentPane().add(b1);
		b1.addActionListener(this);
		b1.setFont(new Font("Arial", Font.PLAIN, 20));

		b2 = new JButton("Search Bill");
		b2.setBounds(230, 10, 200, 40);
		getContentPane().add(b2);
		b2.setFont(new Font("Arial", Font.PLAIN, 20));
		b2.addActionListener(this);

		b3 = new JButton("Extra");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		b3.setBounds(440, 10, 200, 40);
		getContentPane().add(b3);
		b3.setFont(new Font("Arial", Font.PLAIN, 20));
//b3.addActionListener(this);

		b4 = new JButton("Log Out");
		b4.setBounds(700, 10, 200, 40);
		getContentPane().add(b4);
		b4.addActionListener(this);
		Image img = new ImageIcon(this.getClass().getResource("/Log-Out-icon.png")).getImage();
		b4.setIcon(new ImageIcon(img));
		b4.setFont(new Font("Arial", Font.PLAIN, 20));

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b1) {
			new CreateBill();
		}

		else if (e.getSource() == b2) {
			new SearchBill();
		}

		else {
			clock();
			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = st.executeQuery("select s_no from cashierlogindata order by s_no");
				rs.afterLast();

				if (rs.previous()) {
					cec = rs.getInt(1);
					st.executeUpdate("update cashierlogindata set logout_time='" + time + "' where s_no='" + cec + "'");
				}
				cn.close();
			} catch (Exception e4) {
				System.out.println(e4);
				JOptionPane.showMessageDialog(this, "Error cashier");
			}
			dispose();
			new Home();
		}
	}

}
