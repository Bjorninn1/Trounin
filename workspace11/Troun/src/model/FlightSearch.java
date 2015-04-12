package model;
import java.sql.*;
import java.util.ArrayList;
public class FlightSearch {
	public FlightSearch() {
		
	}
    //Use: hotelManager.findHotels(budget,date,numOfPeople,location)
    //Pre: budget is the maximum price of hotels you want to find, date is the date of bookin,
    //     numOfPeople is the number of travelers, and location the city you want to find an hotel in
    //Post: returns an array of hotels that match the criteria, empty if none matched
    public Flight[] findRightFlight(String date, String departureAirport, String arrivalAirport, int maxPrice, int numberOfPassengers) {
        Flight[] flights = new Flight[5];
        for(int i = 0; i < flights.length; i++) {
            flights[i] = new Flight( (int)(Math.random()*500), date, departureAirport, arrivalAirport, (int)(Math.random()*maxPrice), numberOfPassengers);
        }
        return flights;
    }
	//eða
	/*public Flight[] findRightFlight(String date, String departureAirport, String arrivalAirport, int maxPrice, int numberOfPassengers) {
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
	}*/

    //Use: flightManager.bookFlight(flight, numOfPeople)
    //Pre: flight is the flight you want to book, and numOfPassengers the number of travelers
    //Post: returns true if there was availability in the flight, false otherwise
    public boolean bookFlight(Flight flight, int numberOfPassengers) {
        if(numberOfPassengers <= flight.getAvailableSeats()) {
            return true;
        }
        else 
            return false;
    }
}