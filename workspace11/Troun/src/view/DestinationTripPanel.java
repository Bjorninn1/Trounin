package view;
import model.FlightSearch;
import model.Flight;
import Archive.*;
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

//import Controller.DestinationTripController;
//import Model.Hotels;
//import Model.Trips;


public class DestinationTripPanel extends BasicPanel{
	
	private static final long serialVersionUID = 1L;
	//instance variables for panel1: 
	JComboBox<String> comboBudget;
	
	String[] itemsTo = new String[]{"Choose!", "Keflavik", "Akureyri"};
	
	void init(GridBagConstraints c) {
        //Label From: 
        this.addLabel(c, "   From: ",0,1,0,0);

        //Label To: 
        this.addLabel(c, "   To: ",0,1,2,0);
        
        //DropDown menu for home location
        this.setComboFrom(c, this.getItemsFrom());

        //DropDown menu for arrival location
        this.setComboTo(c, this.getItemsToIceland());
        
        //button for departure date, date picker 
        this.departureDate = this.addButton(c,"Departure Date", 0,2,0,3);
        this.textDeparture = this.addUneditableTextField(c,20,this.getDate(),0,2,0,4);
        
        //button for return date, date picker 
        this.returnDate = this.addButton(c,"Return Date",0,2,2,3);
        this.textReturn = this.addUneditableTextField(c,20,this.getDate(),0,2,2,4);

        this.addLabel(c,"Nr. People",0,1,0,5);
        this.setTFnumPeople(c, 10);
        
        //DropDown menu for arrival location
        this.comboBudget = this.addComboBox(c,this.getItemsBudget(),0,4,0,6);
        //search button
        c.ipady = 0;       //reset to default
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        this.searchButton = this.addButton(c,"Search",0,1,3,7);

        this.inputActions();
    }
	
    public void parseInfo(String dateDeparture, String dateReturn) {
        //String dateDeparture = this.getDepartureDate();
        //String dateReturn = this.getReturnDate();
        String fromAirport = (String) this.getComboFrom().getSelectedItem();
        String toAirport = (String) this.getComboTo().getSelectedItem();
        int numberPeople = Integer.parseInt(this.getNumberPeople());
        String result = "";
        if(!validateDates(dateDeparture,dateReturn))
            result += "Fix Dates \n";
        if(fromAirport.equals(this.getItemsFrom()[0]) || toAirport.equals(this.getItemsToIceland()[0]))
            result += "Fix departure/return locations";
        //validateInfo();
        if(!result.equals("")) {
            this.infoBox(result,"Error");
            return;
        }
        Flight[][] flights;
        Hotel[] hotels;
        if(toAirport.equals(this.mainAirport)) {
            Flight[] flights1 = this.searchFlight(dateDeparture, fromAirport, toAirport, numberPeople, 300000000);
            hotels = this.searchHotel(dateDeparture, dateReturn, toAirport, numberPeople, 300000000);
            Flight[] flights2 = this.searchFlight(dateReturn, toAirport, fromAirport, numberPeople, 300000000);
            flights = new Flight[][] {flights1, flights2};
        }else{
            Flight[] flights1 = this.searchFlight(dateDeparture, fromAirport, this.mainAirport, numberPeople, 300000000);
            Flight[] flights2 = this.searchFlight(dateDeparture, this.mainAirport, toAirport, numberPeople, 300000000);
            hotels = this.searchHotel(dateDeparture, dateReturn, toAirport, numberPeople, 300000000);
            Flight[] flights3 = this.searchFlight(dateReturn, toAirport, this.mainAirport, numberPeople, 300000000); 
            Flight[] flights4 = this.searchFlight(dateReturn, this.mainAirport, fromAirport, numberPeople, 300000000);   
            flights = new Flight[][] {flights1 , flights2, flights3, flights4};
        }
        TripPlanning.tripPlanning.showResultsView(hotels, flights, dateDeparture, dateReturn);
    }
}
