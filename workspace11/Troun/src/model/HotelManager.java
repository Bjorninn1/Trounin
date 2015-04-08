package model;
import java.sql.*;
import java.util.ArrayList;
public class HotelManager {
    
    public HotelManager() {
        
    }

    //input is a lot of parameters that should match the hotel
    //output should be a list of hotels that match the given criteria
    //Use: hotelManager.findHotels(budget,date,numOfPeople,location)
    //Pre: budget is the maximum price of hotels you want to find, date is the date of bookin,
    //     numOfPeople is the number of travelers, and location the city you want to find an hotel in
    //Post: returns an array of hotels that match the criteria, empty if none matched
    public Hotel[] findHotels(int budget, String date, int numOfPeople, String location) {
        Hotel[] hotels = new Hotel[5];
        for(int i = 0; i < hotels.length; i++) {
            hotels[i] = new Hotel(location, "Hilton", date, (int)(Math.random()*budget), numOfPeople);
        }
        return hotels;
    }
    //eða
    /*public Hotel[] findHotels(int budget, String date, int numOfPeople, String location) {
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
    }*/
    
    //Use: hotelManager.getHotel(location, hotelName, numOfpeople, date, budget)
    //Pre: location is the city of the hotel your looking for, hotelName is it's name,
    //       numOfPeople the number of travelers, date the date of bookin, and budget the max price
    //Post: Should return an array of availability for requested hotel, empty if none found
    public Hotel[] getHotel(String location, String hotelName, int numOfpeople, String date, int budget) {
        Hotel[] hotels = new Hotel[5];
        for(int i = 0; i < hotels.length; i++) {
            hotels[i] = new Hotel(location, hotelName, date, (int)(Math.random()*budget), numOfpeople);
        }
        return hotels;
    }

    //Use: hotelManager.bookHotelRoom(hotel, numOfPeople)
    //Pre: hotel is the hotel you want to book a room at, and numOfPeople the number of travelers
    //Post: returns true if there was availability at the hotel, false otherwise
    public boolean bookHotelRoom(Hotel hotel, int numOfPeople) {
        if(hotel.getCapacity() >= numOfPeople){
            hotel.reduceCapacity(numOfPeople);
            return true;
        }else
            return false;
    }
}