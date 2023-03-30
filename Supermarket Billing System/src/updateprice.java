import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class updateprice extends JFrame implements ActionListener {
	JLabel l1, l2;
	JTextField t1, t2;
	JButton b1;

	updateprice() {
		super("Practice");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		l1 = new JLabel("Product Id");
		l1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l1.setBounds(100, 50, 200, 40);
		add(l1);

		t1 = new JTextField();
		t1.setBounds(350, 50, 150, 30);
		add(t1);

		l2 = new JLabel("Updated Price");
		l2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		l2.setBounds(100, 150, 200, 40);
		add(l2);

		t2 = new JTextField();
		t2.setBounds(350, 150, 150, 30);
		add(t2);

		b1 = new JButton("Submit");
		b1.setBounds(150, 250, 100, 30);
		add(b1);
		b1.addActionListener(this);
		b1.setFont(new Font("Arial", Font.PLAIN, 20));
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		dispose();
		String a = t1.getText();
		String b = t2.getText();
		try {
			Connection cn = connection.mycon();
			Statement st = cn.createStatement();
			st.executeUpdate("update addnew set price='" + b + "' where product_id='" + a + "'");
			cn.close();
		} catch (Exception e4) {

			JOptionPane.showMessageDialog(this, "Error in update price");
		}
		JOptionPane.showMessageDialog(this, "Price Updated");
	}
}
