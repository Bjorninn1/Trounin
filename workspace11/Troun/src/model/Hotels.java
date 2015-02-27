package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class Hotels {
	final public String hotelName;
	final public String dateCheckIn;
	final public int price;
	final public int numberOfPassengers;
	
	public Hotels(ResultSet rs) throws SQLException{
		hotelName = rs.getString("HotelName");
		dateCheckIn = rs.getString("DateCheckIn");
		price = rs.getInt("Price");
		numberOfPassengers =  rs.getInt("NumberOfRooms");
		
	}
	
	
	//ath erum bara að athuga núna hvort hótel sé laust á dateCheckIn degi og til dagsins á eftir, erum ekkert að vinna með dateCheckOut
	public static Hotels[] findHotels(int maxPrice, String dateCheckIn, String dateCheckOut, String location, int numberOfPassengers) {
		
		
		// load the sqlite-JDBC driver using the current class loader
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection("jdbc:sqlite:Data6.db");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			PreparedStatement p = connection.prepareStatement("select * from Table1 where DateCheckIn = ? " +
					"and Location = ? and  Price <= ? and NumberOfRooms >= ?");
			p.setString(1, dateCheckIn);
			p.setString(2, location);
			p.setInt(3, maxPrice);
			p.setInt(4, numberOfPassengers);
			//vantar að nota dateCheckOut og athuga hvort laust sé frá dateCheckIn til dateCheckOut
			ResultSet rs = p.executeQuery();
			
			ArrayList<Hotels> a = new ArrayList<>();
			
			while(rs.next()) {
				a.add(new Hotels(rs));
			}
			
			return a.toArray(new Hotels[0]);

		}
		catch(SQLException e) {
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
			 e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(connection != null)
				connection.close();
			}
			catch(SQLException e) {
			 // connection close failed.
			 System.err.println(e);
			 e.printStackTrace();
			}
		}
		//skilum engu ef ??a?? er villa
		return null;
	}
}
