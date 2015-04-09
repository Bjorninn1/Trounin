package model;
public class Flight {
    private String date;
    private String departureAirport;
    private String arrivalAirport;
    private int capacity;
    private int price;
    private int flightId;
    //Use: Flight flight = new Flight(date,departureAirport,arrivalAirport,price,capacity)
    //Pre: inputs have to make sense
    //Post: A flight has been created with the following info
    public Flight(int flightId, String date, String departureAirport, String arrivalAirport, int price, int capacity) {
        this.flightId = flightId;
        this.date = date;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.price = price;
        this.capacity = capacity;
    }
    //Use:  flight.getFlightNumber();
    //Pre:  flight has to be of type flight, and has been initialized
    //Post: returns an integer that's the flight's id
    public int getFlightNumber() {
        return this.flightId;
    }
    //Use:  flight.getPrice()
    //Pre:  flight has to be of type flight, and has been initialized
    //Post: returns the price of the flight
    public int getPrice() {
        return this.price;
    }
    //Use: flight.getFromAirport();
    //Pre: flight has to be of type flight, and has been initialized
    //Post: returns the flights departure airport
    public String getFromAirport() {
        return this.departureAirport;
    }
    //Use: flight.geToAirport();
    //Pre: flight has to be of type flight, and has been initialized
    //Post: return the flights arrival airport
    public String getToAirport() {
        return this.arrivalAirport;
    }
    //Use: flight.getDate();
    //Pre: flight has to be of type flight, and has been initialized
    //Post: returns the flights date of departure
    public String getDateTimeDeparture() {
        return this.date;
    }
    //Use: flight.getAvailableSeats()
    //Pre: flight has to be of type flight, and has been initialized
    //Post: returns the flights remaining capacity
    public int getAvailableSeats() {
        return this.capacity;
    }
    //Use: flight.reduceCapacity(numberOfPassengers)
    //Pre: flight has to be of type flight, and has been initialized
    //Post: the flights capacity has been reduced by numberOfPassengers
    public void reduceCapacity(int numberOfPassengers) {
        this.capacity -= numberOfPassengers;
        if(this.capacity < 0) this.capacity = 0;
    }
}