package flightsearch;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

public class FindFlights {
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public ArrayList<Flight> flights;
	
	public ArrayList<Flight> dbflights(String fromAirport, String toAirport, String dateDeparture, int nrPassengers)
	{
		conn = Db_connector.dbConnect();
		
		flights = new ArrayList<Flight>();
		System.out.println(fromAirport + " " + toAirport + " " + dateDeparture + " " + nrPassengers);
		String sql = "SELECT * FROM Flights WHERE fromAirport=? AND toAirport=? AND dateDeparture=?"
				+ "AND availableSeats>=?";
		try{
			pst = conn.prepareStatement(sql);
			pst.setString(1, fromAirport);
			pst.setString(2, toAirport);
			pst.setString(3, dateDeparture);
			pst.setLong(4, nrPassengers);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				/**
				 * Retrieve data from each row from the result set
				 */
				String rsFromAirport = rs.getString("fromAirport");
				String rsToAirport = rs.getString("toAirport");
				Date rsDateDeparture = dateFormat.parse(rs.getString("dateDeparture"));
				Date rsDateArrival = dateFormat.parse(rs.getString("dateArrival"));
				int rsAvailableSeats = rs.getInt("availableSeats");
				int rsPrice = rs.getInt("price");
				String rsFlightNumber = rs.getString("flightNumber");
				
				/**
				 * Create a new Flight object with the data
				 */
				Flight rightFlight = new Flight(rsFromAirport, 
												rsToAirport, 
												rsDateDeparture, 
												rsDateArrival, 
												rsAvailableSeats, 
												rsPrice, 
												rsFlightNumber);
				
	
				/**
				 * Add the Flight object to a list of flights that
				 * match the criteria
				 */
				flights.add(rightFlight);
				conn.close();
				rs.close();
				pst.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}finally {
			try {
				if(conn != null) {
					conn.close();
					rs.close();
					pst.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return flights;
	}
	
	/**
	 * @return Returns an ArrayList with names of available fromAirports
	 */
	public ArrayList<String> getFromAirports(){
		ArrayList<String> fromAirports = new ArrayList<String>();
		
		conn = Db_connector.dbConnect();
		
		String sql = "SELECT DISTINCT fromAirport FROM flights";
		
		try{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				String fromAirport = rs.getString("fromAirport");
				fromAirports.add(fromAirport);
			}
			
		} catch (Exception e){
			System.out.println(e);
		}
		
		return fromAirports;
	}
	
	/**
	 * @return Returns an ArrayList with names of available toAirports
	 */
	public ArrayList<String> getToAirports(){
		ArrayList<String> toAirports = new ArrayList<String>();
		
		conn = Db_connector.dbConnect();
		
		String sql = "SELECT DISTINCT toAirport FROM flights";
		
		try{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()){
				String toAirport = rs.getString("toAirport");
				toAirports.add(toAirport);
			}
			
		} catch (Exception e){
			System.out.println(e);
		}
		
		return toAirports;
	}
	/**
	 * 
	 * @param date is date in String format eg."2015-04-15"
	 * @param flightNumber is a String that represents a certain flight path eg."RA04"
	 * @param nrOfPassangers is an int of how many seats are needed 
	 * @param fromAirport is the name of the departing airport
	 * @return Returns TRUE if successful, FALSE if failed
	 */
	public boolean bookFlight (String date, String flightNumber, int nrOfPassangers, String fromAirport){
		int availableSeats = 0;
		conn = Db_connector.dbConnect();
		
		String sql = "SELECT availableSeats FROM Flights WHERE flightNumber=? AND dateDeparture=?"
				+ " AND fromAirport=?";
		
		
		try{
			pst = conn.prepareStatement(sql);
			pst.setString(1, flightNumber);
			pst.setString(2, date);
			pst.setString(3, fromAirport);
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				availableSeats = rs.getInt("availableSeats");
				
			}
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
		String sql2 = "UPDATE Flights SET availableSeats=? WHERE flightNumber=? AND dateDeparture=?"
				+ " AND fromAirport=?";
		
		int updatedAvailableSeats = availableSeats-nrOfPassangers;
		if(updatedAvailableSeats < 0){
			System.out.println("no seats available");
			return false;
		}
		try{
			pst = conn.prepareStatement(sql2);
			pst.setInt(1, updatedAvailableSeats);
			pst.setString(2, flightNumber);
			pst.setString(3, date);
			pst.setString(4, fromAirport);
			pst.executeUpdate();
			System.out.println(updatedAvailableSeats);
			return true;
		}
		catch(Exception e){
			System.out.println(e);			
			return false;
		}
		
	}
}
