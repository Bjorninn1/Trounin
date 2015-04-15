package controller;
//import model.FlightSearch;
//import model.Flight;
import flightsearch.*;
import Archive.*;
import java.util.ArrayList;
public class Controller {
    HotelManager hotelManager = new HotelManager();
    FindFlights flightManager = new FindFlights();
    //Use: contoller.searchFlight(date, fromAirport, toAirport, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: returns an array of flights that match the criteria
    public Flight[] searchFlight(String date, String fromAirport, String toAirport, int numberPeople, int budget) {
        System.out.println("date: " + date + " fromAirport: " + fromAirport + " toAirport: " + toAirport);
        ArrayList<Flight> flightList = flightManager.dbflights(fromAirport, toAirport, date, numberPeople);
        Flight[] flights = flightList.toArray(new Flight[flightList.size()]);
        if(flights.length == 0) flights = this.reiterateSearchFlight(date, fromAirport, toAirport, numberPeople, budget);
        return flights;
    }
    //String fromAirport, String toAirport, String dateDeparture, int nrPassengers

    //Use: contoller.searchHotel(date, location, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: retuns an array of Hotels matching the given criteria
    //DateRange dates, int budget, int persons, String loc
    public Hotel[] searchHotel(String dateFrom, String dateTo, String location, int numberPeople, int budget) {
        if(location == "Akureyri") location = "Nordurland";
        DateRange dateR = new DateRange(Integer.parseInt(dateFrom.substring(8)),Integer.parseInt(dateFrom.substring(5,7)),Integer.parseInt(dateFrom.substring(0,4)),Integer.parseInt(dateTo.substring(8)),Integer.parseInt(dateTo.substring(5,7)),Integer.parseInt(dateTo.substring(0,4)));
        Hotel[] hotels = hotelManager.findHotels(dateR, budget, numberPeople, location);
        if(hotels.length == 0) hotels = this.reiterateSearchHotel(dateFrom, dateTo, location, numberPeople, budget);
        return hotels;
    } 

    //Use: controller.getHotel(date, location, hotelName, numberPeople, budget)
    //Pre: controller is an initialised object of this class, inputs have to make sense
    //Post: returns an array of availability for a given hotel
    //DateRange dates, String name
    public Hotel[] getHotel(String dateFrom, String dateTo, String location, String hotelName, int numberPeople, int budget) {
        if(location == "Akureyri") location = "Nordurland";
        DateRange dateR = new DateRange(Integer.parseInt(dateFrom.substring(8)),Integer.parseInt(dateFrom.substring(5,7)),Integer.parseInt(dateFrom.substring(0,4)),Integer.parseInt(dateTo.substring(8)),Integer.parseInt(dateTo.substring(5,7)),Integer.parseInt(dateTo.substring(0,4)));
        Hotel[] hotels = hotelManager.findHotels(dateR, hotelName);
        if(hotels.length == 0) hotels = this.reiterateGetHotel(dateFrom, dateTo, location, hotelName, numberPeople, budget);
        return hotels;
    }
    private Hotel[] reiterateGetHotel(String dateFrom, String dateTo, String location, String hotelName, int numberPeople, int budget) {
        Hotel[] hotels = new Hotel[0];
        DateRange dateR = new DateRange(Integer.parseInt(dateFrom.substring(8)),Integer.parseInt(dateFrom.substring(5,7)),Integer.parseInt(dateFrom.substring(0,4)),Integer.parseInt(dateTo.substring(8)),Integer.parseInt(dateTo.substring(5,7)),Integer.parseInt(dateTo.substring(0,4)));
        for(int i = 0; i < 5 && hotels.length == 0; i++) {
            dateFrom = this.alterDate(dateFrom);
            dateTo = this.alterDate(dateTo);
            budget = this.raiseBudget(budget);
            hotels = hotelManager.findHotels(dateR, hotelName);
        }
        return hotels;
    }
    private Hotel[] reiterateSearchHotel(String dateFrom, String dateTo, String location, int numberPeople, int budget) {
        Hotel[] hotels = new Hotel[0];
        DateRange dateR = new DateRange(Integer.parseInt(dateFrom.substring(8)),Integer.parseInt(dateFrom.substring(5,7)),Integer.parseInt(dateFrom.substring(0,4)),Integer.parseInt(dateTo.substring(8)),Integer.parseInt(dateTo.substring(5,7)),Integer.parseInt(dateTo.substring(0,4)));
        for(int i = 0; i < 5 && hotels.length == 0; i++) {
            dateFrom = this.alterDate(dateFrom);
            dateTo = this.alterDate(dateTo);
            budget = this.raiseBudget(budget);
            hotels = hotelManager.findHotels(dateR, budget, numberPeople, location);
        }
        return hotels;
    }
    private Flight[] reiterateSearchFlight(String date, String fromAirport, String toAirport, int numberPeople, int budget) {
        ArrayList<Flight> flightList = new ArrayList<Flight>();
        Flight[] flights = new Flight[0];
        for(int i = 0; i < 5 && flights.length == 0; i++) {
            date = this.alterDate(date);
            date = this.alterDate(date);
            budget = this.raiseBudget(budget);
            flightList = flightManager.dbflights(date, fromAirport, toAirport, numberPeople);
            flights = flightList.toArray(new Flight[flightList.size()]);
        }
        return flights;
    }
    // Pre: the input should be the date that you want to update
    // Post: output is a new string with a the day before
    public String getDateBefore(String date) {
        //does not account for reducing a month
        return date.substring(0,8) + (Integer.parseInt(date.substring(8))-1);
    }    
    // Pre: the input should be the date that you want to update
    // Post: output is a new string with a the day after
    public String getDateAfter(String date) {
        //does not account for reducing a month
        return date.substring(0,8) + (Integer.parseInt(date.substring(8))+1);
    }
    private String alterDate(String date) {
        getDateBefore(date);
        return date;
    }
    private int raiseBudget(int budget) {
        return (int)(budget*1.1);
    }
}