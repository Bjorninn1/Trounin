package Model;

import java.sql.ResultSet;
import java.sql.SQLException;




public class Trips {
	public String fromAirport;
	public String toAirport;
	public String dateDeparture;
	public String timeDeparture;
	public String airlineName;
	public String flightDuration;
	public int price;
	
	
	public Trips(ResultSet rs) throws SQLException{
		fromAirport = rs.getString("FromAirport");
		toAirport = rs.getString("ToAirport");
		dateDeparture = rs.getString("DateDeparture");
		airlineName = rs.getString("Airline");
		timeDeparture = rs.getString("TimeDeparture");
		flightDuration = rs.getString("FlightDuration");
		price = rs.getInt("PriceTotal");
		
	}
}
