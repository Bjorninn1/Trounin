package flightsearch;
import java.util.Date;
public class Flight {

	private String fromAirport;
	private String toAirport;
	private Date dateDeparture;
	private Date dateArrival;
	private int availableSeats;
	private int price;
	private String flightNumber;
	
		public Flight (	String fromAirport, 
						String toAirport, 
						Date dateDeparture,
						Date dateArrival, 
						int availableSeats, 
						int price, 
						String flightNumber) {
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.dateDeparture = dateDeparture;
		this.dateArrival = dateArrival;
		this.availableSeats = availableSeats;
		this.price = price;
		this.flightNumber = flightNumber;
	}

	public String getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(String fromAirport) {
		this.fromAirport = fromAirport;
	}

	public String getToAirport() {
		return toAirport;
	}

	public void setToAirport(String toAirport) {
		this.toAirport = toAirport;
	}

	public Date getDateDeparture() {
		return dateDeparture;
	}

	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}

	public Date getDateArrival() {
		return dateArrival;
	}

	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}


	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
}
