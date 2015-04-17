import java.sql.*; 
public class excelDB {
	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;
		ResultSet hotelCount = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:..\\FlightDB.db");
			stmt = c.createStatement();
			
			hotelCount = stmt.executeQuery("SELECT * FROM Flights;");			
			while(hotelCount.next())
			{
				System.out.println(hotelCount.getString("fromAirport") + "\t" +hotelCount.getString("toAirport") + "\t" +hotelCount.getString("dateDeparture") + "\t" +hotelCount.getString("dateArrival") + "\t" +hotelCount.getString("timeDeparture") + "\t" +hotelCount.getString("timeArrival") +"\t" +hotelCount.getInt("availableSeats") +  "\t" +hotelCount.getInt("price") + "\t" +hotelCount.getString("flightNumber"));
			}			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
                if (c != null) {
		        	stmt.close();
					hotelCount.close();
					c.close();
                } 
            } catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
}