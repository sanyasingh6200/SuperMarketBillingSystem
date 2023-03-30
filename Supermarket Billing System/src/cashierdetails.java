import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class cashierdetails extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4, l5, l6;
	JTextField t1, t2, t4, t5;
	JButton b1, b2, b3, b4, b5;
	JPasswordField p1;
	JTable ta;
	JScrollPane sp;
	int cec;
	private JButton btnBack;

	public void cashierid() {

		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select cashier_id from cashierdata order by cashier_id");
			rs.afterLast();

			if (rs.previous()) {
				cec = rs.getInt(1);
			} else {
				cec = 0;
			}
			cn.close();
			int cec1 = ++cec;
			t1.setText(Integer.toString(cec1));
		}

		catch (Exception e4) {
			System.out.println(e4);
			//JOptionPane.showMessageDialog(this, "Error in cashierdetails");
		}
	}

	public void updatetable() {
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from cashierdata order by cashier_id");
			ta.setModel(DbUtils.resultSetToTableModel(rs));
			cn.close();
		} catch (Exception e4) {
			JOptionPane.showMessageDialog(this, "Error in cashierdetails 2");
		}
	}

	cashierdetails() {
		super("practice");
		setTitle("Cashier Details");

		setSize(900, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		l6 = new JLabel("Cashier Details");
		l6.setFont(new Font("Times New Roman", Font.BOLD, 40));
		l6.setBounds(300, 30, 500, 100);
		getContentPane().add(l6);

		l1 = new JLabel("Cashier Id");
		l1.setBounds(50, 160, 100, 30);
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l1);

		t1 = new JTextField();
		t1.setBounds(220, 160, 150, 30);
		getContentPane().add(t1);
		cashierid();

		l2 = new JLabel("Name");
		l2.setBounds(50, 210, 100, 30);
		l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l2);

		t2 = new JTextField();
		t2.setBounds(220, 210, 150, 30);
		getContentPane().add(t2);

		l3 = new JLabel("Password");
		l3.setBounds(50, 260, 100, 30);
		l3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l3);

		p1 = new JPasswordField();
		p1.setBounds(220, 260, 150, 30);
		getContentPane().add(p1);

		l4 = new JLabel("E-mail Id");
		l4.setBounds(50, 310, 100, 30);
		l4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l4);

		t4 = new JTextField();
		t4.setBounds(220, 310, 150, 30);
		getContentPane().add(t4);

		l5 = new JLabel("Phone No");
		l5.setBounds(50, 360, 100, 30);
		l5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		getContentPane().add(l5);

		t5 = new JTextField();
		t5.setBounds(220, 360, 150, 30);
		getContentPane().add(t5);

		b1 = new JButton("Add");
		b1.setBounds(100, 450, 150, 40);
		getContentPane().add(b1);
		b1.addActionListener(this);
		b1.setFont(new Font("Arial", Font.PLAIN, 20));
		Image img = new ImageIcon(this.getClass().getResource("/Button-Add-icon (1).png")).getImage();
		b1.setIcon(new ImageIcon(img));

		b2 = new JButton("Update");
		b2.setBounds(250, 450, 150, 40);
		getContentPane().add(b2);
		b2.addActionListener(this);
		b2.setFont(new Font("Arial", Font.PLAIN, 20));

		b3 = new JButton("Delete");
		b3.setBounds(400, 450, 150, 40);
		getContentPane().add(b3);
		b3.addActionListener(this);
		b3.setFont(new Font("Arial", Font.PLAIN, 20));
		Image img2 = new ImageIcon(this.getClass().getResource("/Button-Delete-icon.png")).getImage();
		b3.setIcon(new ImageIcon(img2));

		b4 = new JButton("New");
		b4.setBounds(700, 450, 150, 40);
		getContentPane().add(b4);
		b4.addActionListener(this);
		b4.setFont(new Font("Arial", Font.PLAIN, 20));
//Image img1=new ImageIcon(this.getClass().getResource("/Refresh-icon (1).png")).getImage();
//b4.setIcon(new ImageIcon(img1));

		b5 = new JButton("Load");
		b5.setBounds(550, 450, 150, 40);
		getContentPane().add(b5);
		b5.addActionListener(this);
		b5.setFont(new Font("Arial", Font.PLAIN, 20));

		ta = new JTable();
		ta.setBounds(400, 160, 470, 290);
		getContentPane().add(ta);
		ta.setEnabled(false);
		ta.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) ta.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		ta.getTableHeader().setBackground(Color.LIGHT_GRAY);
		ta.getTableHeader().setForeground(Color.WHITE);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

		JScrollPane sp = new JScrollPane(ta);
		sp.setBounds(400, 160, 470, 230);
		getContentPane().add(sp);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(400, 500, 150, 40);
		getContentPane().add(btnBack);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent h) {
		if (h.getSource() == b1) {
			String a = t1.getText();
			String b = t2.getText();
			String c = p1.getText();
			String d = t4.getText();
			String e = t5.getText();
			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				st.executeUpdate(
						"insert into cashierdata values('" + a + "','" + b + "','" + c + "','" + d + "','" + e + "')");
				cn.close();
				JOptionPane.showMessageDialog(this, "Data Inserted Successfully");
			} catch (Exception e1) {
				System.out.println(e1);
				JOptionPane.showMessageDialog(this, "Insertion Unsuccessful");
			}
			updatetable();
		}

		else if (h.getSource() == b2) {
			String a = t1.getText();
			String b = t2.getText();
			String c = p1.getText();
			String d = t4.getText();
			String e = t5.getText();
			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				st.executeUpdate("update cashierdata set name='" + b + "',password='" + c + "',email_id='" + d
						+ "',phone='" + e + "' where cashier_id='" + a + "'");
				cn.close();
				JOptionPane.showMessageDialog(this, "Update Successfully");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Update Unsuccessful");
			}
			updatetable();
		}

		else if (h.getSource() == b5) {
			updatetable();
		}

		else if (h.getSource() == b3) {

			String a = t1.getText();
			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				st.executeUpdate("delete from cashierdata where cashier_id='" + a + "' ");
				cn.close();
				JOptionPane.showMessageDialog(this, "Delete Successfully");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Deletion Unsuccessful");
			}
			updatetable();
		}

		else {
			cashierid();
			t2.setText("");
			p1.setText("");
			t4.setText("");
			t5.setText("");
		}
	}

}
