public class FlightManager {

	public FlightManager() {
		
	}
	//private static maxPrice;
	public Flight[] findRightFlight(String date, String departureAirport, String arrivalAirport, int maxPrice, int numberOfPassengers) {
		Flight[] flights = new Flight[5];
		for(int i = 0; i < flights.length; i++) {
			flights[i] = new Flight( (int) (Math.random()*1000), date, departureAirport, arrivalAirport, (int)(Math.random()*maxPrice), numberOfPassengers);
		}
		return flights;
	}
	public Flight[] findNoFlights(String date, String departureAirport, String arrivalAirport, int maxPrice, int numberOfPassengers) {
		return new Flight[0];
	}
	public boolean bookFlight(Flight flight, int numberOfPassengers) {
		return true;
	} 
	public boolean bookFlightFails(Flight flight, int numberOfPassengers) {
		return false;
	}
}