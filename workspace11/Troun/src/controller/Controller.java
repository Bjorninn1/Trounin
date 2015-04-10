package controller;
import model.*;
public class Controller {
    HotelManager hotelManager = new HotelManager();
    FlightSearch flightManager = new FlightSearch();
    //Use: contoller.searchFlight(date, fromAirport, toAirport, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: returns an array of flights that match the criteria
    public Flight[] searchFlight(String date, String fromAirport, String toAirport, int numberPeople, int budget) {
        Flight[] flights = flightManager.findRightFlight(date, fromAirport, toAirport, budget, numberPeople);
        if(flights.length == 0) flights = this.reiterateSearchFlight(date, fromAirport, toAirport, budget, numberPeople);
        return flights;
    }

    //Use: contoller.searchHotel(date, location, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: retuns an array of Hotels matching the given criteria
    public Hotel[] searchHotel(String date, String location, int numberPeople, int budget) {
        Hotel[] hotels = hotelManager.findHotels( budget, date, numberPeople, location);
        if(hotels.length == 0) hotels = this.reiterateSearchHotel(date, location, numberPeople, budget);
        return hotels;
    } 

    //Use: controller.getHotel(date, location, hotelName, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: returns an array of availability for a given hotel
    public Hotel[] getHotel(String date, String location, String hotelName, int numberPeople, int budget) {
        Hotel[] hotels = hotelManager.getHotel(location, hotelName, numberPeople, date, budget);
        if(hotels.length == 0) hotels = this.reiterateGetHotel(date, location, hotelName, numberPeople, budget);
        return hotels;
    }
    private Hotel[] reiterateGetHotel(String date, String location, String hotelName, int numberPeople, int budget) {
        Hotel[] hotels = new Hotel[0];
        for(int i = 0; i < 5 && hotels.length == 0; i++) {
            date = this.alterDate(date);
            budget = this.raiseBudget(budget);
            hotels = hotelManager.getHotel(location, hotelName, numberPeople, date, budget);
        }
        return hotels;
    }
    private Hotel[] reiterateSearchHotel(String date, String location, int numberPeople, int budget) {
        Hotel[] hotels = new Hotel[0];
        for(int i = 0; i < 5 && hotels.length == 0; i++) {
            date = this.alterDate(date);
            budget = this.raiseBudget(budget);
            hotels = hotelManager.findHotels( budget, date, numberPeople, location);
        }
        return hotels;
    }
    private Flight[] reiterateSearchFlight(String date, String fromAirport, String toAirport, int numberPeople, int budget) {
        Flight[] flights = new Flight[0];
        for(int i = 0; i < 5 && flights.length == 0; i++) {
            date = this.alterDate(date);
            budget = this.raiseBudget(budget);
            flights = flightManager.findRightFlight(date, fromAirport, toAirport, budget, numberPeople);
        }
        return flights;
    }
    private String alterDate(String date) {
        return date;
    }
    private int raiseBudget(int budget) {
        return (int)(budget*1.1);
    }
}