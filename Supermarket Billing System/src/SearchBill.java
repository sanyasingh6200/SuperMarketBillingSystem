import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SearchBill extends JFrame implements ActionListener {

	JLabel l1, l6;
	JTextField t1;
	JButton b1;
	JTable ta;
	JScrollPane sp;
	private DefaultTableModel model;

	public void updatetable() {
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from addnew");
			ta.setModel(DbUtils.resultSetToTableModel(rs));
			cn.close();
		} catch (Exception e4) {
			JOptionPane.showMessageDialog(this, "Error in search bill");
		}
	}

	SearchBill() {
		super("practice");
		setTitle("Search Bill");

		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
//this.getContentPane().setBackground(Color.LIGHT_GRAY); 

		l6 = new JLabel("Search Bill");
		l6.setFont(new Font("Times New Roman", Font.BOLD, 40));
		l6.setBounds(250, 20, 500, 100);
		getContentPane().add(l6);

		l1 = new JLabel("Bill no.");
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l1.setBounds(150, 140, 100, 30);
		getContentPane().add(l1);

		t1 = new JTextField();
		t1.setBounds(260, 140, 100, 30);
		getContentPane().add(t1);

		b1 = new JButton("Search");
		b1.setBounds(420, 140, 150, 30);
		getContentPane().add(b1);
		b1.addActionListener(this);
		b1.setFont(new Font("Arial", Font.PLAIN, 20));
		Image img3 = new ImageIcon(this.getClass().getResource("/search-icon (1).png")).getImage();
		b1.setIcon(new ImageIcon(img3));

		ta = new JTable();
		ta.setBounds(100, 200, 500, 200);
		getContentPane().add(ta);

		ta.setEnabled(false);
		ta.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) ta.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		ta.getTableHeader().setBackground(Color.LIGHT_GRAY);
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		if (defaults.get("Table.alternateRowColor") == null)
			defaults.put("Table.alternateRowColor", new Color(240, 240, 240));

		JScrollPane sp = new JScrollPane(ta);
		sp.setBounds(61, 200, 580, 227);
		getContentPane().add(sp);
		setVisible(true);

		Object[] columns = { "Bill No", "Customer Name", "Time", "Date", "Amount Pay" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		ta.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent e1) {
		String a = t1.getText();
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from customerdata where bill_no='" + a + "'");
			if (rs.next()) {

				int c = rs.getInt(1);
				String d = rs.getString(2);
				String e = rs.getString(3);
				String f = rs.getString(4);
				int g = rs.getInt(5);
				Object[] row = new Object[5];
				row[0] = c;
				row[1] = d;
				row[2] = e;
				row[3] = f;
				row[4] = g;

				model.addRow(row);
			}
		} catch (Exception e2) {
			System.out.println(e2);
			JOptionPane.showMessageDialog(this, "Error in search bill 2");
		}
	}

}
