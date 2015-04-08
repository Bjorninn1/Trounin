package controller;
import model.*;
public class Controller {
    HotelManager hotelManager = new HotelManager();
    FlightSearch flightManager = new FlightSearch();
    //Use: contoller.searchFlight(date, fromAirport, toAirport, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: returns an array of flights that match the criteria
    public Flight[] searchFlight(String date, String fromAirport, String toAirport, int numberPeople, int budget) {
        return flightManager.findRightFlight(date, fromAirport, toAirport, budget, numberPeople);
    }

    //Use: contoller.searchHotel(date, location, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: retuns an array of Hotels matching the given criteria
    public Hotel[] searchHotel(String date, String location, int numberPeople, int budget) {
        return hotelManager.findHotels( budget, date, numberPeople, location);
    } 

    //Use: controller.getHotel(date, location, hotelName, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: returns an array of availability for a given hotel
    public Hotel[] getHotel(String date, String location, String hotelName, int numberPeople, int budget) {
        return hotelManager.getHotel(location, hotelName, numberPeople, date, budget);
    }
}