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



public class StopoverTripPanel extends basicPanel {
	
	private static final long serialVersionUID = 1L;
	//instance variables for panel2:

	JComboBox<String> comboBudget;
	JComboBox<String> comboIceland;
	
	
	String[] itemsTo2 = new String[]{"Choose!", "New York", "Boston"};
	String[] itemsIceland = new String[]{"specify area in Iceland", "Reykjavik", "Akureyri"};

	public StopoverTripPanel(){
        this.setPreferredSize(new Dimension(1000, 500));
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c2 = new GridBagConstraints();
	    c2.fill = GridBagConstraints.HORIZONTAL;
	    this.init(c2);
	}

	private void init(GridBagConstraints c) {
		//Label From: 
	    this.addLabel(c,"   From: ",0,2,0,0);	    

	    //Label To: 
	    this.addLabel(c,"   To: ",0,2,2,0);
	    
	    //DropDown menu for home location
	    this.setComboFrom(c, this.getItemsFrom());
	    
	    //DropDown menu for arrival addText
		this.setComboTo(c, this.getItemsToIceland());
	    
	        
	 	//button for departure date, date picker 
	    this.departureDate = this.addButton(c,"Departure Date",0,2,0,3);
	    //departureDate2.setText("Departure Date");
	    this.textDeparture = this.addTextField(c,20,this.getDate(),0,2,0,4);
	    this.textDeparture.setEditable(false);
	    
	    
	    
	    //button for return date, date picker 
	    this.returnDate = this.addButton(c,"Departure Date From Iceland",0,2,2,3);
	    //returnDate2.setText("Departure Date From Iceland");
	    this.textReturn = this.addTextField(c,20,this.getDate(),0,2,2,4);
	    this.textReturn.setEditable(false);
	    

	    this.addLabel(c,"Nr. People",0,1,0,5);
	    this.setTFnumPeople(c, 10);
	    
	    //DropDown menu to specify area in Iceland
	    
	    this.comboIceland = this.addComboBox(c,this.getItemsToIceland(),0,4,0,6);
	    
	    //DropDown menu for arrival location
	    
	    this.comboBudget = this.addComboBox(c,this.getItemsBudget(),0,4,0,7);
	
	    
	    //search button
	    this.searchButton = this.addButton(c,"Search",0,2,2,8);
	    
	    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    c.insets = new Insets(10,0,0,0);  //top padding
	    
	    this.inputActions();

	}

	public void parseInfo(String dateDeparture, String dateReturn) {
		//String dateDeparture = this.getDepartureDate();
        //String dateReturn = this.getReturnDate();
        String fromAirport = (String) this.getComboFrom().getSelectedItem();
        String toAirport = (String) this.getComboTo().getSelectedItem();
        String locationIceland = (String) this.comboIceland.getSelectedItem();
        int numberPeople = Integer.parseInt(this.getNumberPeople());
        String result = "";
        if(!validateDates(dateDeparture,dateReturn))
            result += "Fix Dates \n";
        if(fromAirport.equals(this.getItemsFrom()[0]) || toAirport.equals(this.itemsTo2[0]) || locationIceland.equals(this.getItemsToIceland()[0]))
            result += "Fix departure/return locations/location in Iceland";
        if(!result.equals("")) {
            this.infoBox(result,"Error");
            return;
        }
        Flight[][] flights;
    	Hotel[] hotels;
        if(locationIceland.equals(itemsIceland[1])) {
            Flight[] flights1 = this.searchFlight(dateDeparture, fromAirport, this.mainAirport, numberPeople, 300);
            hotels = this.searchHotel(dateDeparture, locationIceland, numberPeople, 300);
            Flight[] flights2 = this.searchFlight(dateReturn, this.mainAirport, toAirport, numberPeople, 300);
            flights = new Flight[][] {flights1, flights2};
        }else{
            Flight[] flights1 = this.searchFlight(dateDeparture, fromAirport, this.mainAirport, numberPeople, 300);
            Flight[] flights2 = this.searchFlight(dateDeparture, this.mainAirport, locationIceland, numberPeople, 300);
            hotels = this.searchHotel(dateDeparture,locationIceland, numberPeople, 300);
            Flight[] flights3 = this.searchFlight(dateReturn, locationIceland, this.mainAirport, numberPeople, 300); 
            Flight[] flights4 = this.searchFlight(dateReturn, this.mainAirport, toAirport, numberPeople, 300);   
            flights = new Flight[][] {flights1, flights2, flights3, flights4};
        }
        TripPlanning.tripPlanning.showResultsView(hotels, flights);
	}
	

}
