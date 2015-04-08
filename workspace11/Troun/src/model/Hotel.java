package model;

public class Hotel {
    private String date;
    private String hotelName;
    private String location;
    private int capacity;
    private int price;
     
    //Use: Hotel hotel = new Hotel(location, hotelName, date, price, capacity)
    //Pre: inputs have to make sense given naming
    //Post: will return a new hotel object containing the info
    public Hotel(String location, String hotelName, String date, int price, int capacity) {
        this.location = location;
        this.hotelName = hotelName;
        this.price = price;
        this.date = date;
        this.capacity = capacity;
        //here we might want to have an array of rooms, and move capacity and price to that
    }
    
    //Use: hotel.getHotelName();
    //Pre: hotel is an initialized Hotel object
    //Post: returns the hotels name
    public String getName() {
        return this.hotelName;
    }
    //Use: hotel.getLocation();
    //Pre: hotel is an initialized Hotel object
    //Post: returns the hotels location(city)
    public String getLocation() {
        return this.location;
    }
    //Use:
    //Pre: hotel is an initialized Hotel object
    //Post: return's the hotel room's price
    public int getMinPrice() {
        return this.price/2;
    }
    //Use:
    //Pre: hotel is an initialized Hotel object
    //Post: return's the hotel room's price
    public int getMaxPrice() {
        return this.price;
    }
    //Use: hotel.getDate()
    //Pre: hotel is an initialized Hotel object
    //Post: returns the hotel's(room's) date of availability 
    public String getDate() {
        return this.date;
    }
    //Use: hotel.getCapacity();
    //Pre: hotel is an initialized Hotel object
    //Post: returns the hotel's capacity
    public int getCapacity() {
        return this.capacity;
    }
    //Use: hotel.reduceCapacity(numOfPeople);
    //Pre: hotel is an initialized Hotel object
    //Post: hotel's capacity has been reduced by numOfPeople
    public void reduceCapacity(int numOfPeople) {
        this.capacity -= numOfPeople;
    }
}
