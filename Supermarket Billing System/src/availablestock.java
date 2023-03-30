import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
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

public class availablestock extends JFrame implements ActionListener {

	JLabel l6;
	JButton b1, b2, b3;
	JTable ta;
	JScrollPane sp;
	private JButton btnBack;

	public void updatetable() {
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("select * from addnew order by product_id");
			ta.setModel(DbUtils.resultSetToTableModel(rs));
			cn.close();
		} catch (Exception e4) {
			JOptionPane.showMessageDialog(this, "Error in availablestock");
		}
	}

	availablestock() {
		super("practice");
		setTitle("Available Stock");

		setSize(800, 600);
		setLocationRelativeTo(null);

		getContentPane().setLayout(null);
//this.getContentPane().setBackground(Color.LIGHT_GRAY); 
		setResizable(false);
		l6 = new JLabel("Available Stock");
		l6.setFont(new Font("Times New Roman", Font.BOLD, 40));
		l6.setBounds(250, 30, 500, 100);
		getContentPane().add(l6);

		b1 = new JButton("Load Stock");
		b1.setBounds(100, 450, 200, 40);
		getContentPane().add(b1);
		b1.addActionListener(this);
		b1.setFont(new Font("Arial", Font.PLAIN, 20));

		b2 = new JButton("Add Quantity");
		b2.setBounds(300, 450, 200, 40);
		getContentPane().add(b2);
		b2.addActionListener(this);
		b2.setFont(new Font("Arial", Font.PLAIN, 20));

		b3 = new JButton("Update Price");
		b3.setBounds(500, 450, 200, 40);
		getContentPane().add(b3);
		b3.addActionListener(this);
		b3.setFont(new Font("Arial", Font.PLAIN, 20));

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
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 20));
		btnBack.setBounds(300, 495, 200, 40);
		getContentPane().add(btnBack);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			updatetable();
		} else if (e.getSource() == b2) {
			new addquantity();
		}

		else {
			new updateprice();
		}
	}

}
