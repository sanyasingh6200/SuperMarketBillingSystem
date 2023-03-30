import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Home extends JFrame implements ActionListener {

	JLabel l1, l2, l3;
	JButton b1, b2;
	ImageIcon image;

	public void marque() {
		Thread head = new Thread() {
			public void run() {
				try {
					int a = 0, b = 40, c = 750, d = 50;
					for (;;) {
						if (a == 800) {
							a = -650;
						} else {
							a = a + 10;
						}
						l1.setBounds(a, b, c, d);
						sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		head.start();
	}

	public void marque1() {
		Thread head = new Thread() {
			public void run() {
				try {
					int a = 20, b = 120, c = 150, d = 30;
					for (;;) {
						if ((a == 700) && (b != 440)) {
							b = b + 20;
						} else if ((b == 440) && (a != 20)) {
							a = a - 20;
						} else if (b == 120) {
							a = a + 20;
						} else {
							b = b - 20;
						}

						l2.setBounds(a, b, c, d);
						sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		head.start();
	}

	Home() {

		super("Practice");
		setTitle("Supermarket Billing System");

		setSize(800, 600);
		setLocationRelativeTo(null);
		setForeground(Color.BLUE);
		getContentPane().setLayout(null);
		this.getContentPane().setBackground(Color.getHSBColor(154, 254, 25));
		setResizable(false);

		l3 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/download.png")).getImage();
		l3.setIcon(new ImageIcon("D:\\Eclipse_Java_Programs\\Supermarket Billing System\\src\\download.png"));
		l3.setBounds(180, 180, 246, 205);
		getContentPane().add(l3);

		l1 = new JLabel("Welcome To Supermarket Billing System");
		l1.setFont(new Font("Times New Roman", Font.BOLD, 40));
		getContentPane().add(l1);
		l1.setBounds(40, 40, 750, 50);
		l1.setForeground(Color.BLACK);


		l2 = new JLabel("Welcome");
		getContentPane().add(l2);
		l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		l2.setForeground(Color.BLACK);
		marque1();

		b1 = new JButton("Admin Login");
		b1.setBounds(470, 200, 200, 50);
		getContentPane().add(b1);
		b1.addActionListener(this);
		b1.setFont(new Font("Arial", Font.PLAIN, 20));
		Image img2 = new ImageIcon(this.getClass().getResource("/Ok-icon.png")).getImage();
		b1.setIcon(new ImageIcon(img2));

		b2 = new JButton("Cashier Login");
		b2.setBounds(470, 300, 200, 50);
		getContentPane().add(b2);
		b2.addActionListener(this);
		b2.setFont(new Font("Arial", Font.PLAIN, 20));
		Image img1 = new ImageIcon(this.getClass().getResource("/Ok-icon.png")).getImage();
		b2.setIcon(new ImageIcon(img1));
		
		JLabel lblNewLabel = new JLabel("Developed By : Sanya Singh");
		lblNewLabel.setBounds(10, 549, 330, 13);
		getContentPane().add(lblNewLabel);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Home();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			dispose();
			new adminlogin();
		} else {
			dispose();
			new cashierlogin();
		}

	}
}
