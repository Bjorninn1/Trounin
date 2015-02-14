package Model;

import java.sql.ResultSet;
import java.sql.SQLException;




public class Hotels {
	public String hotelName;
	public String dateCheckIn;
	public String price;
	
	
	
	public Hotels(ResultSet rs) throws SQLException{
		hotelName = rs.getString("HotelName");
		dateCheckIn = rs.getString("DateCheckIn");
		price = rs.getString("Price");
		
		
	}
}
