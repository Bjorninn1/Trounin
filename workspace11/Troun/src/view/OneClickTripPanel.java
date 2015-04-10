package view;
import model.*;
import controller.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class OneClickTripPanel extends BasicPanel{
		
	private static final long serialVersionUID = 1L;
	    
    String[] itemsTo3 = new String[]{"Choose!", "Keflavik", "Akureyri"};
    
    JComboBox<String> comboBudget;
    JComboBox<String> comboHotels;
    
	void init(GridBagConstraints c) {
		//Label From: 
	    this.addLabel(c, "   From: ",0,2,0,0);
	
	    //Label To: 
	    this.addLabel(c, "   To: ",0,2,2,0);
	    
	    //DropDown menu for home location
        this.setComboFrom(c, this.getItemsFrom());
	    
	    //DropDown menu for arrival location
		this.setComboTo(c, this.getItemsToIceland());
	   
	  	//DropDown menu for arrival location
	    
        this.comboBudget = this.addComboBox(c,this.getItemsBudget(),0,4,0,6);

        //this.getComboHotels() = this.addComboBox(c,this.hotels, 0, 4, 0, 7);
        this.setComboHotels(c);
	    
	  	//button for departure date, date picker 
        this.textDeparture = this.addUneditableTextField(c,20,this.getDate(),0,2,0,4);
        this.departureDate = this.addButton(c,"Departure Date",0,2,0,3);

	    
	    
	    //button for return date, date picker 
	    this.returnDate = this.addButton(c,"Return Date",0,2,2,3);
        this.textReturn = this.addUneditableTextField(c,20,this.getDate(),0,2,2,4);
	    
	    this.addLabel(c,"Nr. People",0,1,0,5);
        this.setTFnumPeople(c, 10);

	    //search button
        this.searchButton = this.addButton(c,"Search",0,2,2,8);
	    
	    this.inputActions();
	}


	public void parseInfo(String dateDeparture, String dateReturn) {
		//String dateDeparture = this.getDepartureDate();
        //String dateReturn = this.getReturnDate();
        String fromAirport = (String) this.getComboFrom().getSelectedItem();
        String toAirport = (String) this.getComboTo().getSelectedItem();
        String hotel = (String) this.getComboHotels().getSelectedItem();
        int numberPeople = Integer.parseInt(this.getNumberPeople());
        String result = "";
        if(!validateDates(dateDeparture,dateReturn))
            result += "Fix Dates \n";
        if(fromAirport.equals(this.defaultString) || toAirport.equals(this.defaultString))
            result += "Fix departure/return locations/location in Iceland";
        if(!result.equals("")) {
            this.infoBox(result,"Error");
            return;
        }
        Flight[][] flights;
        Hotel[] hotels;
        if(toAirport.equals(this.mainAirport)) {
            Flight[] flights1 = this.searchFlight(dateDeparture, fromAirport, toAirport, numberPeople, 300);
            hotels = this.getHotel(dateDeparture, toAirport, hotel, numberPeople, 300);
            Flight[] flights2 = this.searchFlight(dateReturn, toAirport, fromAirport, numberPeople, 300);
            flights = new Flight[][] {flights1, flights2};
        }else{
            Flight[] flights1 = this.searchFlight(dateDeparture, fromAirport, this.mainAirport, numberPeople, 300);
            Flight[] flights2 = this.searchFlight(dateDeparture, this.mainAirport, toAirport, numberPeople, 300);
            hotels = this.getHotel(dateDeparture, toAirport, hotel, numberPeople, 300);
            Flight[] flights3 = this.searchFlight(dateReturn, toAirport, this.mainAirport, numberPeople, 300); 
            Flight[] flights4 = this.searchFlight(dateReturn, this.mainAirport, fromAirport, numberPeople, 300);   
            flights = new Flight[][] {flights1, flights2, flights3, flights4};
        }
        TripPlanning.tripPlanning.showResultsView(hotels, flights);
        //eftir að búið er að leita, er hægt að athuga hvort nógu mikið fannst, 
        //og kalla aftur á sama fall með öðrum dagsetningum til að reyna að finna fleiri
	}	
}
