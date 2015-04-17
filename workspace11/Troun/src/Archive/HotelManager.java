package Archive;
import java.sql.*;
public class HotelManager {

		public HotelManager()
		{
			
		}
		
		public Hotel[] findHotels()
		{
			Connection c = null;
			Statement stmt = null;
			ResultSet hotelCount = null;
			try{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
				stmt = c.createStatement();
				
				hotelCount = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS;");
				int count = hotelCount.getInt(1);
				
				Hotel[] hotels = new Hotel[count];
				for(int i = 1; i <= count; i++)
				{
					ResultSet h = stmt.executeQuery("SELECT * FROM Hotels WHERE id="+i+";");
					hotels[i-1] = new Hotel(h.getInt("id"), h.getString("name"), 
								h.getString("location"),h.getInt("roomcount"), h.getString("address"));
				}
				
				
				return hotels;
			}catch(Exception e){
				System.out.println(e.getMessage());
				return new Hotel[0];
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
		
		public Hotel[] findHotels(DateRange dates)
		{
			Connection c = null;
	    	Statement stmt = null;
	    	ResultSet hotelCount = null;
	  
	    	try {
	    		Class.forName("org.sqlite.JDBC");
	    		c = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
	    		stmt = c.createStatement();

	    		hotelCount = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS;");
	    		int count = hotelCount.getInt(1);
	    		int[] possIds = new int[count];
	    		int counter = 0;
	    		
	    		for(int i = 1; i <= count; i++)
	    		{
	    			if(dates.size() == 0){  possIds[counter++] = i; continue;  }
	    			
	    			ResultSet roomCountInfo = stmt.executeQuery("SELECT roomcount FROM HOTELS WHERE id="+i+";");
	    			int roomCount = roomCountInfo.getInt("roomcount");
	    			for(int j = 0; j < roomCount; j++)
	    			{
	    				String sql = "SELECT COUNT(date) FROM AVAILABILITY WHERE hId="+i+" AND rId="+j+
	    						" AND avail = 1 AND "+dates.getFirstDate()+"<=date AND date<="+dates.getLastDate()+";";		
	    				ResultSet datesFound = stmt.executeQuery(sql);
	    				int dayCount = datesFound.getInt(1);
	    				
	    				if(dates.size() == dayCount){ possIds[counter++] = i;  break; }
	    			}
	    		}
	    		
	    		Hotel[] hotels = new Hotel[counter];
	    		for(int i = 0; i < counter; i++)
	    		{
	    			ResultSet h = stmt.executeQuery("SELECT * FROM HOTELS WHERE id="+possIds[i]+";");
	    			hotels[i] = new Hotel(h.getInt("id"), h.getString("name"), h.getString("location"),
	    					h.getInt("roomcount"), h.getString("address"));
	    		}
	    		
	    		//stmt.close();
	    		//c.close();
	    		return hotels;
	    	} catch ( Exception e ) {
	    			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    			return new Hotel[0];
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
		
		public Hotel[] findHotels(DateRange dates, String name) {
	    	Connection c = null;
	    	Statement stmt = null;
	    	ResultSet hotelCount = null;
	    	try {
	    		Class.forName("org.sqlite.JDBC");
	    		c = DriverManager.getConnection("jdbc:sqlite:..\\HotelDB.db");
	    		stmt = c.createStatement();
	    		
	    		hotelCount = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS");
	    		int count = hotelCount.getInt(1);
	    		int[] nameMatches = new int[count];
	    		int counter = 0;
	    		
	    		for(int i = 1; i <= count; i++)
	    		{
	    			ResultSet matchingNum = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS WHERE id="+i+" AND name LIKE '%"+name+"%';");
	    			int numFound = matchingNum.getInt(1);
	    			if(numFound > 0)
	    				nameMatches[counter++] = i;
	    		}
	    		
	    		int[] possIds = new int[counter];
	    		int counterCopy = counter;
	    		counter = 0;
	    		
	    		for(int i = 0; i < counterCopy; i++)
	    		{
	    			int hotelId = nameMatches[i];
	    			if(dates.size() == 0){ possIds[counter++] = hotelId; continue;  }
	    			
	    			ResultSet roomCountInfo = stmt.executeQuery("SELECT roomcount FROM HOTELS WHERE id="+hotelId+";");
	    			int roomCount = roomCountInfo.getInt("roomcount");
	    			
	    			for(int j = 0; j < roomCount; j++)
	    			{
	    				String sql = "SELECT COUNT(date) FROM AVAILABILITY WHERE hId="+hotelId+" AND rId="+j+
	    						" AND avail = 1 AND "+dates.getFirstDate()+"<=date AND date<="+dates.getLastDate()+";";		
	    				ResultSet datesFound = stmt.executeQuery(sql);
	    				int dayCount = datesFound.getInt(1);
	    				
	    				if(dates.size() == dayCount){ possIds[counter++] = hotelId;	break; }
	    			}
	    		}
	    		
	    		Hotel[] hotels = new Hotel[counter];
	    		for(int i = 0; i < counter; i++)
	    		{
	    			ResultSet h = stmt.executeQuery("SELECT * FROM HOTELS WHERE id="+possIds[i]+";");
	    			hotels[i] = new Hotel(h.getInt("id"), h.getString("name"), h.getString("location"),
	    					h.getInt("roomcount"), h.getString("address"));
	    		}
	    		
	    		//stmt.close();
	    		//c.close();
	    		return hotels;
    		} catch ( Exception e ) {
    			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    			return new Hotel[0];
    		}finally {
				try {
	                if (c != null) {
			        	if(stmt != null)stmt.close();
						if(hotelCount != null)hotelCount.close();
						c.close();
	                } 
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
    		}
		}
		
		public Hotel[] findHotels(DateRange dates, HotelCriteria cri){
	    	Connection c = null;
	    	Statement stmt = null;
	    	ResultSet hotelCount = null;
	    	try {
	    		Class.forName("org.sqlite.JDBC");
	    		c = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
	    		stmt = c.createStatement();
	    		
	    		hotelCount = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS;");
	    		int count = hotelCount.getInt(1);
	    		int[] criMatches = new int[count];
	    		int counter = 0;
	    		
	    		for(int i = 1; i <= count; i++)
	    		{
	    			ResultSet cr = stmt.executeQuery("SELECT * FROM HOTELCRITERIA WHERE id="+i+";");
	    			if(cri.matchesCriteria(cr.getInt("breakfast"), cr.getInt("dinner"), cr.getInt("wifi"),
	    					cr.getInt("handicap"), cr.getInt("stars")))
	    				criMatches[counter++] = i;
	    		}
	    		
	    		int[] possIds = new int[counter];
	    		int counterCopy = counter;
	    		counter = 0;
	    		
	    		for(int i = 0; i < counterCopy; i++)
	    		{
	    			int hotelId = criMatches[i];
	    			if(dates.size() == 0){ possIds[counter++] = hotelId; continue;  }
	    			
	    			ResultSet roomCountInfo = stmt.executeQuery("SELECT roomcount FROM HOTELS WHERE id="+hotelId+";");
	    			int roomCount = roomCountInfo.getInt("roomcount");
	    			
	    			for(int j = 0; j < roomCount; j++)
	    			{
	    				String sql = "SELECT COUNT(date) FROM AVAILABILITY WHERE hId="+hotelId+" AND rId="+j+
	    						" AND avail = 1 AND "+dates.getFirstDate()+"<=date AND date<="+dates.getLastDate()+";";		
	    				ResultSet datesFound = stmt.executeQuery(sql);
	    				int dayCount = datesFound.getInt(1);
	    				
	    				if(dates.size() == dayCount){ possIds[counter++] = hotelId;	break; }
	    			}
	    		}
	    		
	    		Hotel[] hotels = new Hotel[counter];
	    		for(int i = 0; i < counter; i++)
	    		{
	    			ResultSet h = stmt.executeQuery("SELECT * FROM HOTELS WHERE id="+possIds[i]+";");
	    			hotels[i] = new Hotel(h.getInt("id"), h.getString("name"), h.getString("location"),
	    					h.getInt("roomcount"), h.getString("address"));
	    		}
	    		
	    		//stmt.close(); 
	    		//c.close();
	    		return hotels;
    		} catch ( Exception e ) {
    			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    			return new Hotel[0];
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
		
		public Hotel[] findHotels(DateRange dates, int budget, int persons, String loc){
	    	Connection c = null;
	    	Statement stmt = null;
	    	ResultSet hotelCount = null;
	    	try {
	    		Class.forName("org.sqlite.JDBC");
	    		c = DriverManager.getConnection("jdbc:sqlite:..\\HotelDB.db");
	    		stmt = c.createStatement();
	    		hotelCount = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS;");
	    		
	    		int count = hotelCount.getInt(1);
	    		int[] locMatches = new int[count];
	    		int counter = 0;
	    		
	    		for(int i = 1; i <= count; i++)
	    		{
	    			ResultSet matchingNum = stmt.executeQuery("SELECT COUNT(*) FROM HOTELS WHERE id="+i+" AND location LIKE '%"+loc+"%';");
	    			int matchCount = matchingNum.getInt(1);
	    			if(matchCount > 0)
	    				locMatches[counter++] = i;
	    		}
	    		
	    		int[] possIds = new int[counter];
	    		int counterCopy = counter;
	    		counter = 0;
	    		
	    		for(int i = 0; i < counterCopy; i++)
	    		{
	    			int hotelId = locMatches[i];
	    			if(dates.size() == 0){ possIds[counter++] = hotelId; continue;  }
	    			
	    			ResultSet roomCountInfo = stmt.executeQuery("SELECT roomcount FROM HOTELS WHERE id="+hotelId+";");
	    			int roomCount = roomCountInfo.getInt("roomcount");
	    			
	    			for(int j = 0; j < roomCount; j++)
	    			{
	    				String sql = "SELECT COUNT(date) FROM Availability,Rooms WHERE Rooms.hId="+hotelId+" AND Availability.hId="+hotelId+
	    						" AND Rooms.rId ="+j+" AND Availability.rId="+j+" AND Avail = 1 AND price<="+budget+" AND beds="+persons+
	    						" AND "+dates.getFirstDate()+"<=date AND date<="+dates.getLastDate()+";";
	    				ResultSet datesFound = stmt.executeQuery(sql);
	    				int dayCount = datesFound.getInt(1);
	    				
	    				if(dates.size() == dayCount){ possIds[counter++] = hotelId;  break; }
	    			}
	    		}
	    		
	    		Hotel[] hotels = new Hotel[counter];
	    		for(int i = 0; i < counter; i++)
	    		{
	    			ResultSet h = stmt.executeQuery("SELECT * FROM HOTELS WHERE id="+possIds[i]+";");
	    			hotels[i] = new Hotel(h.getInt("id"), h.getString("name"), h.getString("location"),
	    					h.getInt("roomcount"), h.getString("address"));
	    		}
	    		//stmt.close(); 
	    		//c.close();
	    		return hotels;
    		} catch ( Exception e ) {
    			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    			return new Hotel[0];
    		}finally {
    			try {
	                if (c != null) {
						c.close();
	                } 
	                if(hotelCount != null) 
						hotelCount.close();
					if(stmt != null)
			        	stmt.close();
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            }
    		}
		}
		
		/*public static void main(String[] args)
		{
			HotelManager h = new HotelManager();
			DateRange dates = new DateRange();
			
			Hotel[] hs = h.findHotels(dates, "hotel ranga");
			System.out.println(hs.length+"");
			Hotel ranga = hs[0];
			
			ranga.deleteComment(2);
			ranga.submitComment("New and improved comment", 2);
			
			
			for(int j = 0; j < hs.length;j++)
			{
				System.out.println(hs[j].getName());
			}
		}*/
	}
