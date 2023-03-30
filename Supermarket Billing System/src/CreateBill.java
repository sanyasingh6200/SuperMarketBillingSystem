import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CreateBill extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4, l5, l6, l7;

	JTextField t1, t2, t3, t4, t5, t6, t7;
	JButton b1, b2, b3, b4, b5, b6;
	JTable ta1;
	JScrollPane sp, sp1;
	JComboBox cb1, cb2;
	private DefaultTableModel model;
	int totalcal = 0;;
	String time, date;
	int cec;

	public void productid() {
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select product_name from addnew order by product_name");
			while (rs.next()) {
				String pat1 = rs.getString(1);
				cb2.addItem(pat1);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in create bill");
		}
	}

	public void itemid() {

		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select product_id from addnew order by product_id");
			while (rs.next()) {
				int pat = rs.getInt(1);
				cb1.addItem(pat);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error in create bill 2");
		}
	}

	public void billno() {

		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select bill_no from customerdata");
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
			JOptionPane.showMessageDialog(this, "Error in create 3");
		}
	}

	public void clock() {

		Thread clock = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar calen = new GregorianCalendar();
						int day = calen.get(Calendar.DAY_OF_MONTH);
						int month = calen.get(Calendar.MONTH);
						int month1 = month + 1;
						int year = calen.get(Calendar.YEAR);

						int sec = calen.get(Calendar.SECOND);
						int min = calen.get(Calendar.MINUTE);
						int hr = calen.get(Calendar.HOUR);

						l7.setText("TIME    " + hr + ":" + min + ":" + sec + "         DATE        " + day + "/"
								+ month1 + "/" + year);
						time = hr + ":" + min + ":" + sec;
						date = day + "/" + month1 + "/" + year;

						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}

	CreateBill() {
		super("SUPERMARKET BILLING SYSTEM");

		setSize(1000, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
//this.getContentPane().setBackground(Color.lightGray); 

		cb1 = new JComboBox();
		cb1.setBounds(130, 80, 100, 30);
		add(cb1);
		itemid();
		cb1.addActionListener(this);

		cb2 = new JComboBox();
		cb2.setBounds(390, 80, 100, 30);
		add(cb2);
		productid();
		cb2.addActionListener(this);

		l1 = new JLabel("Bill No");
		l1.setBounds(20, 20, 100, 30);
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l1);

		t1 = new JTextField();
		t1.setBounds(130, 20, 100, 30);
		add(t1);
		billno();

		l2 = new JLabel("Product Id");
		l2.setBounds(20, 80, 100, 30);
		l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l2);

		l3 = new JLabel("Product Name");
		l3.setBounds(250, 80, 130, 30);
		l3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l3);

		l4 = new JLabel("Price");
		l4.setBounds(510, 80, 50, 30);
		l4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l4);

		t4 = new JTextField();
		t4.setBounds(570, 80, 100, 30);
		add(t4);

		l5 = new JLabel("Quantity");
		l5.setBounds(690, 80, 80, 30);
		l5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l5);

		t5 = new JTextField();
		t5.setBounds(790, 80, 100, 30);
		add(t5);

		l6 = new JLabel("Customer Name");
		l6.setBounds(250, 20, 130, 30);
		l6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l6);

		t7 = new JTextField();
		t7.setBounds(390, 20, 100, 30);
		add(t7);

		b1 = new JButton("Total Amount");
		b1.setBounds(380, 400, 200, 30);
		add(b1);
		b1.addActionListener(this);
//Image img=new ImageIcon(this.getClass().getResource("/Button-Add-icon (1).png")).getImage();
//b1.setIcon(new ImageIcon(img));
		b1.setFont(new Font("Arial", Font.PLAIN, 20));

		t6 = new JTextField();
		t6.setBounds(590, 400, 200, 30);
		add(t6);

		b3 = new JButton("Add");
		b3.setBounds(60, 210, 150, 30);
		add(b3);
		b3.addActionListener(this);
		Image img3 = new ImageIcon(this.getClass().getResource("/Button-Add-icon (1).png")).getImage();
		b3.setIcon(new ImageIcon(img3));
		b3.setFont(new Font("Arial", Font.PLAIN, 20));

		b5 = new JButton("Delete");
		b5.setBounds(60, 270, 150, 30);
		add(b5);
		b5.addActionListener(this);
		Image img2 = new ImageIcon(this.getClass().getResource("/Button-Delete-icon.png")).getImage();
		b5.setIcon(new ImageIcon(img2));
		b5.setFont(new Font("Arial", Font.PLAIN, 20));

		b6 = new JButton("Print");
		b6.setBounds(60, 330, 150, 30);
		add(b6);
		b6.addActionListener(this);
		Image img1 = new ImageIcon(this.getClass().getResource("/print-icon.png")).getImage();
		b6.setIcon(new ImageIcon(img1));
		b6.setFont(new Font("Arial", Font.PLAIN, 20));

		ta1 = new JTable();
		ta1.setBounds(340, 150, 500, 210);
		add(ta1);
//ta1.setEnabled(false);
		ta1.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 16));
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) ta1.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		ta1.getTableHeader().setBackground(Color.lightGray);
//ta1.getTableHeader().setForeground(Color.WHITE);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

		JScrollPane sp1 = new JScrollPane(ta1);
		sp1.setBounds(340, 150, 500, 210);
		add(sp1);

		Object[] columns = { "Product Id", "Product Name", "Price", "Quantity" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		ta1.setModel(model);

		l7 = new JLabel();
		l7.setBounds(510, 20, 380, 30);
		l7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		add(l7);
		clock();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == b3) {

			String a = String.valueOf(cb1.getSelectedItem());
			String b = t5.getText();
			int f = Integer.parseInt(b);

			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("select quantity from addnew where product_id='" + a + "'");

				if (rs.next()) {

					int c = rs.getInt(1);

					if (c >= f) {

						int d = c - f;

						st.executeUpdate("update addnew set quantity='" + d + "' where product_id='" + a + "'");

						Object[] row = new Object[4];
						row[0] = String.valueOf(cb1.getSelectedItem());
						row[1] = String.valueOf(cb2.getSelectedItem());
						row[2] = t4.getText();
						row[3] = t5.getText();

						model.addRow(row);
						int cal = Integer.parseInt(t4.getText()) * Integer.parseInt(t5.getText());
						totalcal = cal + totalcal;
					}

					else {
						JOptionPane.showMessageDialog(this, "Quantity Insufficient");
					}

				}

				cn.close();
			}

			catch (Exception e4) {
				JOptionPane.showMessageDialog(this, "Error in create 4");
			}
		}

		else if (e.getSource() == b5) {

			ta1.getModel();
			try {
				int SelectedRowIndex = ta1.getSelectedRow();
				String valid = ta1.getModel().getValueAt(SelectedRowIndex, 0).toString();
				String valqty = ta1.getModel().getValueAt(SelectedRowIndex, 3).toString();
				String valpri = ta1.getModel().getValueAt(SelectedRowIndex, 2).toString();
				model.removeRow(SelectedRowIndex);

				int cal = Integer.parseInt(valpri) * Integer.parseInt(valqty);
				totalcal = totalcal - cal;

				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("select quantity from addnew where product_id='" + valid + "'");
				if (rs.next()) {
					int c = rs.getInt(1);
					int d = c + Integer.parseInt(valqty);
					st.executeUpdate("update addnew set quantity='" + d + "' where product_id='" + valid + "'");
				}
				cn.close();
			} catch (Exception q) {
				JOptionPane.showMessageDialog(this, "No Row Selected");
			}
		}

		else if (e.getSource() == b6) {

			String a = t1.getText();
			String b = t7.getText();
			String d = t6.getText();
			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				st.executeUpdate("insert into customerdata values('" + a + "','" + b + "','" + time + "','" + date
						+ "','" + d + "')");
				cn.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Error in create bill 5");
			}

			MessageFormat header = new MessageFormat("Report Print");
			MessageFormat footer = new MessageFormat("page{0,number,integer}");
			try {
				ta1.print(JTable.PrintMode.NORMAL, header, footer);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error in create bill 6");
			}
		}

		else if (e.getSource() == cb1) {

			String acv = String.valueOf(cb1.getSelectedItem());

			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("select product_name,price from addnew where product_id='" + acv + "'");
				if (rs.next()) {
					String c = rs.getString(1);
					int d = rs.getInt(2);
					cb2.setSelectedItem(c);
					t4.setText(Integer.toString(d));
				}
				cn.close();
			} catch (Exception e4) {
				System.out.println(e4);
				JOptionPane.showMessageDialog(this, "Error in create bill 7");
			}
		}

		else if (e.getSource() == cb2) {

			String acv1 = String.valueOf(cb2.getSelectedItem());
			try {
				Connection cn = connection.mycon();
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery("select product_id,price from addnew where product_name='" + acv1 + "'");
				if (rs.next()) {
					int c = rs.getInt(1);
					int d = rs.getInt(2);
					cb1.setSelectedItem(c);
					t4.setText(Integer.toString(d));
				}
				cn.close();
			} catch (Exception e4) {
				System.out.println(e4);
				JOptionPane.showMessageDialog(this, "Error in create bill 8");
			}
		}

		else {
			t6.setText(Integer.toString(totalcal));
		}
	}
}
