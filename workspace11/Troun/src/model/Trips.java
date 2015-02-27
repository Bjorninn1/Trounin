package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class Trips {
	final public String fromAirport;
	final public String toAirport;
	final public String dateDeparture;
	final public String timeDeparture;
	final public String airlineName;
	final public String flightDuration;
	final public int price;
	final public int numberOfPassengers;
	
	public Trips(ResultSet rs) throws SQLException{
		fromAirport = rs.getString("FromAirport");
		toAirport = rs.getString("ToAirport");
		dateDeparture = rs.getString("DateDeparture");
		airlineName = rs.getString("Airline");
		timeDeparture = rs.getString("TimeDeparture");
		flightDuration = rs.getString("FlightDuration");
		price = rs.getInt("PriceTotal");
		numberOfPassengers = rs.getInt("NumberOfSeatsAvailable");
	}
	
	public static Trips[] findTrips(int maxPrice, String dateDeparture, String fromAirport, String toAirport, int numberOfPassengers) {
		
		
		// load the sqlite-JDBC driver using the current class loader
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			
			connection = DriverManager.getConnection("jdbc:sqlite:Data3.db");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			PreparedStatement p = connection.prepareStatement("select * from Table1 where FromAirport = ? and ToAirport = ? and " +
					"DateDeparture = ? and  PriceTotal <= ? and NumberOfSeatsAvailable >= ?");
			p.setString(1, fromAirport);
			p.setString(2, toAirport);
			p.setString(3, dateDeparture);
			p.setInt(4, maxPrice);
			p.setInt(5, numberOfPassengers);
			
			ResultSet rs = p.executeQuery();
			
			ArrayList<Trips> a = new ArrayList<>();
			
			while(rs.next()) {
				a.add(new Trips(rs));
			}
			
			return a.toArray(new Trips[0]);

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
