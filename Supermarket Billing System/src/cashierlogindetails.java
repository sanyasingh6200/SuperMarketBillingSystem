import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class cashierlogindetails extends JFrame {

	JLabel l6;
	JTable ta;
	JScrollPane sp;
	private DefaultTableModel model;

	public void updatetable() {
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from cashierlogindata");
			ta.setModel(DbUtils.resultSetToTableModel(rs));
			cn.close();
		} catch (Exception e4) {
			JOptionPane.showMessageDialog(this, "Error in cashierlogin");
		}
	}

	cashierlogindetails() {
		super("practice");
		setTitle("Cashier Login Details");

		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		// this.getContentPane().setBackground(Color.LIGHT_GRAY);

		l6 = new JLabel("Cashier Login Details");
		l6.setFont(new Font("Times New Roman", Font.BOLD, 40));
		l6.setBounds(200, 30, 500, 100);
		getContentPane().add(l6);

		ta = new JTable();
		ta.setBounds(100, 150, 600, 250);
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
		sp.setBounds(100, 150, 600, 250);
		getContentPane().add(sp);
		updatetable();
		setVisible(true);

	}

}
