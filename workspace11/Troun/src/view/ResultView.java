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
import javax.swing.JList;
import javax.swing.JTextField;


public class ResultView extends basicPanel {
	JList[] lists;
	public ResultView(Hotel[] hotels, Flight[][] flights) {
		this.setPreferredSize(new Dimension(1000, 500));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
        this.init(c, hotels, flights);
	}
	void init(GridBagConstraints c) {

	}
	private void init(GridBagConstraints c, Hotel[] hotels, Flight[][] flights) {
		String[][] options = new String[1 + flights.length][10];
		String[] labels = new String[] {"Hotel:  ", "Arrival Flight:  ", "Intermediary Flight A: ", "Intermediary Flight D: ", "Departure Flight :"};
		lists = new JList[options.length];
		options[0] = parseHotelInfo(hotels);
		for(int i = 0; i < flights.length; i++) {
			options[i+1] = parseFlightInfo(flights[i]);
		}
		for(int i = 0; i < options.length; i++) {
			this.addLabel(c, options.length == 5 ? labels[i] : (i < 2 ? labels[i] : labels[labels.length-1]), 0, 1, i, 0);
			this.lists[i] = this.addJList(c, options[i], 1, options[i].length, i, 1);
		}
		this.searchButton = this.addButton(c,"Book",0,1,options.length-1,11);
		this.addActionSearchButton();
	}
	public String[] parseHotelInfo(Hotel[] hotels) {
		String[] results = new String[hotels.length];
		for(int i = 0; i < hotels.length; i++) {
			results[i] = hotels[i].getName();
			results[i] += " " + hotels[i].getLocation();
			results[i] += " from: " + hotels[i].getMinPrice();
			results[i] += " to: " + hotels[i].getMaxPrice();
		}
		return results;
	}
	public String[] parseFlightInfo(Flight[] flights) {
		String[] results = new String[flights.length];
		for(int i = 0; i < flights.length; i++) {
			results[i] = "" + flights[i].getFlightNumber();
			results[i] += " " + flights[i].getDateTimeDeparture();
			results[i] += " from: " + flights[i].getFromAirport();
			results[i] += " to: " + flights[i].getToAirport();	
			results[i] += " for: " + flights[i].getPrice();
		}
		return results;
	}
	public String getReturnDate() {
		return "";
	}
	public String getDepartureDate() {
		return "";
	}
	public void parseInfo(String dateDeparture, String dateReturn) {
		String[] selected = new String[this.lists.length];
		Boolean valid = true;
		for(int i = 0; i < lists.length; i++) {
			if(!lists[i].isSelectionEmpty())
				selected[i] = lists[i].getSelectedValue().toString();
			else {
				valid = false;
				break;
			}
			System.out.println("Booking: " + selected[i]);
		}
		if(valid)
			System.out.println("booked successfully");
		else
			System.out.println("Please Fill out all Options");
	}
}