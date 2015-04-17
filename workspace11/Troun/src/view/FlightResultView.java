package view;
import flightsearch.*;
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
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class FlightResultView extends BasicPanel {
	Flight[][] flights;
	Hotel[] hotels;
	String dateDeparture;
	String dateReturn;
	int numberPeople;
	JComboBox<String>[] airlines;
	JComboBox<String>[] flightTimes;
	JComboBox<String>[] classes;
	JTextField[] prices;
	boolean isChanging = false;
	public FlightResultView(Hotel[] hotels, Flight[][] flights, String dateDeparture, String dateReturn, int numberPeople) {
		this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridBagLayout());
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.flights = flights;
        this.numberPeople = numberPeople;
        this.airlines = new JComboBox[flights.length];
        this.flightTimes = new JComboBox[flights.length];
        this.classes = new JComboBox[flights.length];
        this.prices = new JTextField[flights.length];
        this.hotels = hotels;
        System.out.println("FRV "+this.hotels.length);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
        this.init(c, hotels);
	}
	void init(GridBagConstraints c) {

	}
	void init(GridBagConstraints c, Hotel[] hotels) {
		String[] current;
		for(int i = 0; i < this.flights.length; i++) {
			this.addLabel(c, this.flights[i][0].getFromAirport()+"-"+this.flights[i][0].getToAirport(),0,1,0,3*i);
			current = this.parseAirlines(this.flights[i]);
			this.airlines[i] = this.addComboBox(c, current, 0 , 1 , 0, 3*i+1);
			this.addLabel(c,"Price: ",0,1,0,3*i+2);
			this.prices[i] = this.addUneditableTextField(c, 20, "0", 0, 1, 1, 3*i+2);
			this.addActionComboBox(this.airlines[i], i, 0);
			current = this.parseTimes(this.flights[i]);
			this.flightTimes[i] = this.addComboBox(c, current, 0 , 1 , 1, 3*i+1);
			this.addActionComboBox(this.flightTimes[i], i, 1);
			this.classes[i] = this.addComboBox(c, this.parseClass(this.flights[i]), 0 , 1, 2, 3*i+1);
			this.addActionComboBox(this.classes[i], i, 2);
		}
		this.searchButton = this.addButton(c,"Select Hotel",0,1, 1, this.flights.length*3);
		this.addActionSearchButton();
	}
	String[] parseClass(Flight[] flights) {
		return new String[] {"Economy Class", "Business Class"}; 
	}
	String[] parseAirlines(Flight[] flights) {
		String result = "Select Airline/";
		for(int i = 0; i < flights.length; i++) {
			if(result.indexOf(flights[i].getAirline()) < 0) result += flights[i].getAirline()+"/";
		}
		if(result.length() > 0)
			result = result.substring(0,result.length()-1);
		return result.split("/");
	}
	String[] parseAirlines2(int indice, String time) {
		String result = "Select Airline/";
		for(int i = 0; i < this.flights[indice].length; i++) {
			if(this.convertDateToString(this.flights[indice][i].getDateDeparture()).equals(time)  && result.indexOf(this.flights[indice][i].getAirline()) < 0) result += this.flights[indice][i].getAirline()+"/";
		}
		if(result.length() > 0)result = result.substring(0,result.length()-1);
		return result.split("/");
	}
	String[] parseAirlineTimes(int indice, String airline) {
		String result = "Select Time/";
		for(int i = 0; i < this.flights[indice].length; i++) {
			if(this.flights[indice][i].getAirline().equals(airline) && result.indexOf(this.convertDateToString(this.flights[indice][i].getDateDeparture())) < 0) result += this.convertDateToString(this.flights[indice][i].getDateDeparture())+"/";
		}
		if(result.length() > 0)
			result = result.substring(0,result.length()-1);
		return result.split("/");
	}
	String[] parseTimes(Flight[] flights) {
		String result = "Select Time/";
		for(int i = 0; i < flights.length; i++) {
			if(result.indexOf(this.convertDateToString(flights[i].getDateDeparture())) < 0) result += this.convertDateToString(flights[i].getDateDeparture())+"/";
		}
		if(result.length() > 0)
			result = result.substring(0,result.length()-1);
		return result.split("/");
	}
	Flight findFlight(int indice, String airline, String date, String className) {
		Flight flight;
		for(int i = 0; i < flights[indice].length; i++) {
			flight = this.flights[indice][i];
			if(flight.getAirline().equals(airline) && this.convertDateToString(flights[indice][i].getDateDeparture()).equals(date) && flight.getFClass().equals(className))
				return flight;
		}
		return null;
	}
	boolean allCBset(int indice) {
		System.out.println(this.airlines[indice].getSelectedIndex() + " :1 2: "+this.flightTimes[indice].getSelectedIndex());
		return this.airlines[indice].getSelectedIndex() != 0 && this.flightTimes[indice].getSelectedIndex() != 0;
	}
	public void addActionComboBox(final JComboBox<String> cb, final int indice, final int box) {
		cb.addActionListener(new ActionListener() {//add actionlistner to listen for change
            public void actionPerformed(ActionEvent e) {
                if(FlightResultView.this.isChanging) return;
            	FlightResultView.this.isChanging = true;
            	if(box != 2) {
	            	if(cb.getSelectedIndex() == 0 && box == 0) {
	            		String[] airlines = FlightResultView.this.parseAirlines(FlightResultView.this.flights[indice]);
	            		DefaultComboBoxModel model = new DefaultComboBoxModel( airlines );
						FlightResultView.this.airlines[indice].setModel( model );
						String[] times = FlightResultView.this.parseTimes(FlightResultView.this.flights[indice]);
	            		DefaultComboBoxModel model2 = new DefaultComboBoxModel( times );
						FlightResultView.this.flightTimes[indice].setModel( model2 );
						FlightResultView.this.isChanging = false;
	            		return;
	            	}
	                String s = (String) cb.getSelectedItem();//get the selected item
	                switch(box) {
	                	case 0:
	                		String[] times = FlightResultView.this.parseAirlineTimes(indice , s);
	                		DefaultComboBoxModel model = new DefaultComboBoxModel( times );
							FlightResultView.this.flightTimes[indice].setModel( model );
							break;
						case 1:
							String tmp = (String)FlightResultView.this.airlines[indice].getSelectedItem();
							String[] airlines = FlightResultView.this.parseAirlines2(indice , s);
							DefaultComboBoxModel model2 = new DefaultComboBoxModel( airlines );
							FlightResultView.this.airlines[indice].setModel( model2 );
							FlightResultView.this.airlines[indice].setSelectedItem(tmp);
							FlightResultView.this.flightTimes[indice].setSelectedIndex(1);
							break;
	                }
            	}
                if(FlightResultView.this.allCBset(indice)) {
	                Flight selected = FlightResultView.this.findFlight(indice, (String)FlightResultView.this.airlines[indice].getSelectedItem(), (String)FlightResultView.this.flightTimes[indice].getSelectedItem(), (String)FlightResultView.this.classes[indice].getSelectedItem());
	                FlightResultView.this.prices[indice].setText("$"+(selected.getPrice()/120));
                }
                FlightResultView.this.isChanging = false;
	    	}
	    });
	}
    public void parseInfo(String dateDeparture, String dateReturn) {
    	boolean valid = true;
    	for(int i = 0; i < this.flights.length; i++) {
    		valid = valid && this.allCBset(i);
    	}
    	if(!valid) {
    		this.infoBox("Please fill out all options","Error");
    		return;
    	}
    	Flight[] flightsToBook = new Flight[this.flights.length];
    	for(int i = 0; i < this.flights.length; i++) {
    		flightsToBook[i] = this.findFlight(i, (String)this.airlines[i].getSelectedItem(), (String)this.flightTimes[i].getSelectedItem(), (String)this.classes[i].getSelectedItem());
    	}
        TripPlanning.tripPlanning.showHotelView(this.hotels, flightsToBook, this.dateDeparture, this.dateReturn, this.numberPeople);
    }
    public String getReturnDate() {
		return "";
	}
	public String getDepartureDate() {
		return "";
	}
}
