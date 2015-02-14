package Controller;

import java.sql.*;
import java.util.ArrayList;

import Model.Hotels;
import Model.Trips;


public class DestinationTripController {
	
	public Trips[] findTrips(String dateDeparture, String fromAirport, String toAirport) {
		
		
		// load the sqlite-JDBC driver using the current class loader
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			
			connection = DriverManager.getConnection("jdbc:sqlite:Data3.db");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			PreparedStatement p = connection.prepareStatement("select * from Table1 where FromAirport = ? and ToAirport = ? and " +
					"DateDeparture = ?");
			p.setString(1, fromAirport);
			p.setString(2, toAirport);
			p.setString(3, dateDeparture);
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
		//skilum engu ef það er villa
		return null;
	}
	
	
	public Hotels[] findHotels(String dateDeparture, String location) {
		
		
		// load the sqlite-JDBC driver using the current class loader
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			
			
			connection = DriverManager.getConnection("jdbc:sqlite:Data6.db");
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			PreparedStatement p = connection.prepareStatement("select * from Table1 where DateCheckIn = ? and Location = ? ");
			p.setString(1, dateDeparture);
			p.setString(2, location);
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
		//skilum engu ef það er villa
		return null;
	}
	
	
	
}
