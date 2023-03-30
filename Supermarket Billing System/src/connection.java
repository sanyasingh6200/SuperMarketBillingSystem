import java.sql.Connection;
import java.sql.DriverManager;

public class connection {

	public static Connection con = null;

	public static Connection mycon() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			/**
			 * Change your password only
			 */
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "Sanya@24");
		} catch (Exception e) {
			System.out.println(e);
		}

		return con;
	}

}
