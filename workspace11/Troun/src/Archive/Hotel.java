package Archive;

import java.sql.*;

public class Hotel {
	private int id;
	private int roomCount;
	private String address;
	private String location;
	private String name;
	private Comment[] comments;
	private HotelRoom[] hotelRooms;
	
	Hotel(int id, String name,String loc, int rooms, String addr )
	{
		this.id = id;
		roomCount = rooms;
		address = addr;
		location = loc;
		this.name = name;
		//this.comments = loadComments(id);
		this.hotelRooms = loadRooms(id);
	}
	
	public String getName()
	{
		return name;
	}
	public String getAddress()
	{
		return address;
	}
	public String getLocation()
	{
		return location;
	}
	public int getRoomCount()
	{
		return roomCount;
	}
	int getHotelID()
	{
		return id;
	}
	public Comment[] getComments()
	{
		return comments;
	}
	public HotelRoom[] getRooms()
	{
		return hotelRooms;
	}
	
	public HotelRoom[] getAvailRooms(DateRange dates, RoomCriteria cri)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			
			int[] criMatches = new int[this.roomCount];
			int counter = 0;
			
			for(int i = 0; i < this.roomCount; i++)
			{
				ResultSet r = stmt.executeQuery("SELECT * FROM Rooms WHERE hId="+this.id+" AND rId="+i+" ;");
				
				if(cri.matchesCriteria(r.getInt("beds"), r.getInt("price"), r.getInt("tv"), r.getInt("minibar"), r.getInt("handicap")))
					criMatches[counter++] = i;
			}

			int[] possIds = new int[counter];
			int counterCopy = counter;
			counter = 0;
			
			for(int i = 0; i < counterCopy; i++)
			{
				int roomId = criMatches[i];
				if(dates.size() == 0){ possIds[counter++] = roomId; continue; } 
					
				String sql = "SELECT COUNT(date) FROM Availability WHERE Avail = 1 AND rId="+roomId+
						" AND hId="+this.id+" AND "+dates.getFirstDate()+"<=date AND date<="+dates.getLastDate()+";";
				ResultSet matchCount = stmt.executeQuery(sql);
				int daysFound = matchCount.getInt(1);
				if(daysFound == dates.size())
					possIds[counter++] = roomId;
			}
			
			HotelRoom[] rooms = new HotelRoom[counter];
			for(int i = 0; i<counter; i++)
			{
				ResultSet r = stmt.executeQuery("SELECT * FROM Rooms WHERE hId="+this.id+" AND rId="+possIds[i]+";");
				boolean tv = (r.getInt("tv") == 1)? true:false ;
				boolean handi = (r.getInt("handicap") == 1)? true:false ;
				boolean mini = (r.getInt("minibar") == 1)? true:false ;
				
				rooms[i] = new HotelRoom(this.id, possIds[i], r.getInt("beds"), tv, handi,r.getInt("price") ,mini);
			}
		
			stmt.close();
			conn.close();
			return rooms;
			
		}catch(Exception e)
		{
			System.out.print(e.getMessage());
			return new HotelRoom[0];
		}
	}
	
	public boolean bookHotelRoom(DateRange dates, HotelRoom room) {
		int roomId = room.getRoomId();
	    Connection c = null;
	    Statement stmt = null;
	    ResultSet count = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
	
	    	stmt = c.createStatement();
	    		
	    	count = stmt.executeQuery("SELECT Count(*) FROM Availability WHERE rId="+roomId+" AND hId="+this.id+" AND "+
	      						dates.getFirstDate()+"<=date AND date<="+dates.getLastDate()+" AND avail=0;");
	    	int countUnavail = count.getInt(1);
	    	if(countUnavail > 0) return false;
	      	
	    	String sql = "UPDATE Availability SET avail = 0 WHERE rId="+roomId+
	    				" AND hId="+this.id+" AND "+dates.getFirstDate()+"<= date AND date<="+dates.getLastDate()+";";
	    	stmt.executeUpdate(sql);
	    		
	    	
	       
	    	return true;
        } catch ( SQLException e ) {
          	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          	 return false;
        }catch(ClassNotFoundException e)
		{
			System.out.print(e.getMessage());
			return false;
		}
        finally{
        	try {
                if (c != null) {
                    //con.close();
		        	stmt.close();
			    	c.close();
			    	count.close();
                }
            } catch (SQLException ignored) {
            	ignored.printStackTrace();
            }
	    }

	}
	
	public boolean submitComment(String comment, int userId)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			
			ResultSet count = stmt.executeQuery("SELECT COUNT(*) FROM Comments WHERE userId="+userId+" AND hotelId="+this.id+";");
			if(count.getInt(1) > 0)
				return false;
			
			String sql = "INSERT INTO Comments VALUES("+userId+","+this.id+ ",'" +comment+ "');" ;
			stmt.executeUpdate(sql);
			
			int length = this.comments.length;
			Comment[] copy = new Comment[length+1];
			copy[length] = new Comment(userId, this.id, comment);
			
			for(int i = 0; i < length; i++) copy[i] = this.comments[i];
		
			this.comments = copy;
			stmt.close();
			conn.close();
			return true;
			
		}catch(SQLException e)
		{
			System.out.print(e.getMessage());
			return false;
		}
		catch(ClassNotFoundException e)
		{
			System.out.print(e.getMessage());
			return false;
		}
	}
	
	public boolean deleteComment(int userId) 
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			
			ResultSet count = stmt.executeQuery("SELECT COUNT(*) FROM Comments WHERE hotelId="+this.id+" AND UserId ="+userId+";");
			if(count.getInt(1) == 0)
				return false;
			count.close();
			
			String sql = "DELETE FROM Comments WHERE HotelId="+this.id +" AND UserId="+userId+";";
			stmt.executeUpdate(sql);
			
			Comment[] copy = new Comment[this.comments.length-1];
			int counter = 0;
			for(int i = 0; i < this.comments.length; i++)
			{
				if(this.comments[i].getUserId() != userId)
					copy[counter++] = this.comments[i];
			}
			this.comments = copy;
			stmt.close();
			conn.close();
			return true;
	
		}catch(Exception e)
		{
			System.out.print(e.getMessage());
			return false;
		}

	}
	
	public Comment[] loadComments(int hotelId)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
		    stmt = conn.createStatement();
			
			ResultSet count = stmt.executeQuery("select COUNT(*) from Comments where HotelId ="+hotelId+";");
			Comment[] comms = new Comment[count.getInt(1)];
			
			ResultSet comments = stmt.executeQuery("select * from Comments where HotelId ="+ hotelId+";");

			int i = 0;
			while(comments.next())
			{
				int hId = comments.getInt("HotelId");
				int userId = comments.getInt("UserId");
				String comment = comments.getString("text");
				
				comms[i] = new Comment(userId, hId, comment);
				i++;
			}
			stmt.close();
			conn.close();
			return comms;	
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return new Comment[0];
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
			return new Comment[0];
		}
	}
	
	public HotelRoom[] loadRooms(int hotelId)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:HotelDB.db");
			stmt = conn.createStatement();
			
			ResultSet count = stmt.executeQuery("select COUNT(*) from Rooms where hId ="+hotelId+";");
			HotelRoom[] rs = new HotelRoom[count.getInt(1)];
			
			ResultSet rooms = stmt.executeQuery("select * from Rooms where hId ="+ hotelId+";");

			int i = 0;
			while(rooms.next())
			{
				int hId = rooms.getInt("hId");
				int rId = rooms.getInt("rId");
				int price = rooms.getInt("price");
				int beds = rooms.getInt("beds");
				boolean tv = (rooms.getInt("tv") == 1)?true :false ;
				boolean handi = (rooms.getInt("handicap") == 1)?true :false ;
				boolean mini = (rooms.getInt("minibar") == 1)?true :false ;
				
				rs[i] = new HotelRoom(hId,rId , beds, tv, handi, price, mini);
				i++;
			}
			
			stmt.close();
			conn.close();
			return rs;	
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return new HotelRoom[0];
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
			return new HotelRoom[0];
		}
		finally {
			try {
				if(conn != null) {
					conn.close();
					stmt.close();
				}
			} catch (SQLException e) {
            	e.printStackTrace();
            }
		}
	}
	
}
