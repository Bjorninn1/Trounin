package flightsearch;
import java.sql.*;

import javax.swing.*;

public class Db_connector {
	Connection conn = null;

	public static Connection dbConnect()
	{
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:..\\FlightDB.db");
			//JOptionPane.showMessageDialog(null, "Connection to database successful");
			return conn;			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
