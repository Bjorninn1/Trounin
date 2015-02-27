package controller;
import view.MainResultsView;
import model.Hotels;
import model.Trips;

public class DestinationTripController {
	
	String dateDeparture1;
	String dateDeparture2;
	String fromAirport1;
	String fromAirport2;
	String toAirport1;
	String toAirport2;
	int budget;
	int numberOfPassengers;
	String hotel;
	
	/*
	public DestinationTripController() {
		
	}
	*/
	
	public void setTrip1(String dateDeparture, String fromAirport, String toAirport)
	{
		this.dateDeparture1 = dateDeparture;
		this.fromAirport1 = fromAirport;
		this.toAirport1 = toAirport;
		
	}
	
	public void setTrip2(String dateDeparture, String fromAirport, String toAirport)
	{
		this.dateDeparture2 = dateDeparture;
		this.fromAirport2 = fromAirport;
		this.toAirport2 = toAirport;
	}
	
	//this method is only used by one-click-trips
	public void setHotel(String hotel)
	{
		this.hotel = hotel;
	}
	
	public void setNumberOfPassengers(int number)
	{
		this.numberOfPassengers = number;
	}

	public void setBudget(int b)
	{
		this.budget = b;
	}


	public void search() {
		//departure flight
		Trips[] trip = Trips.findTrips(budget, dateDeparture1, fromAirport1, toAirport1, numberOfPassengers);
		
		//return flight
		Trips[] trip2 = Trips.findTrips(budget, dateDeparture2, fromAirport2, toAirport2, numberOfPassengers);
		
		//hotels
		Hotels[] hotel = Hotels.findHotels(budget, dateDeparture1, dateDeparture2, toAirport1, numberOfPassengers);

		MainResultsView mainResultsView = new MainResultsView(trip, trip2, hotel);
		mainResultsView.setVisible(true);
	}
	
	
	
}
