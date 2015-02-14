package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Hotels;
import Model.Trips;

public class MainResultsView extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public MainResultsView(Trips[] trip, Trips[] trip2, Hotels[] hotel){
		
				JPanel panel = new JPanel();
		        this.setLayout(new GridBagLayout());
		        panel.setPreferredSize(new Dimension(1000, 700));
				this.setPreferredSize(new Dimension(1000, 700));
		        GridBagConstraints c = new GridBagConstraints();
		        c.fill = GridBagConstraints.HORIZONTAL;
		      
		        
		        //label titill fyrir departure flights
		        JLabel labelTitle1 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 0;
		        c.gridy = 0;
		        labelTitle1.setText("Departure Flights: ");
		        this.add(labelTitle1, c);
		        
		        //eftirfarandi labels eru titlar fyrir þær leitarniðurstöðurnar eiga að birtast fyrir trip
		        //Label From
		        JLabel labelFromEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 1;
		        c.gridy = 1;
		        labelFromEfst.setText("From");
		        this.add(labelFromEfst, c);
		        
		        //Label To
		        JLabel labelToEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 2;
		        c.gridy = 1;
		        labelToEfst.setText("To");
		        this.add(labelToEfst, c);
		        
		        //Label Date
		        JLabel labelDateEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 3;
		        c.gridy = 1;
		        labelDateEfst.setText("Date");
		        this.add(labelDateEfst, c);
		        
		        //Label Price
		        JLabel labelPriceEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 4;
		        c.gridy = 1;
		        labelPriceEfst.setText("Price");
		        this.add(labelPriceEfst, c);
		        
		        //Label Time
		        JLabel labelTimeEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 5;
		        c.gridy = 1;
		        labelTimeEfst.setText("Time");
		        this.add(labelTimeEfst, c);
		        
		        //Label Airline
		        JLabel labelAirlineEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 6;
		        c.gridy = 1;
		        labelAirlineEfst.setText("Airline");
		        this.add(labelAirlineEfst, c);
		        
		        //Label TimeDeparture
		        JLabel labelTimeDepartureEfst = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 7;
		        c.gridy = 1;
		        labelTimeDepartureEfst.setText("TimeDeparture");
		        this.add(labelTimeDepartureEfst, c);
		        
		        
		        //eftirfarandi labels sýna leitarniðurstöður fyrir trip
		        for(int i=0; i<trip.length; i++){   
		        
			        JLabel labelFrom = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 1;
			        c.gridy = 2+i;
			        labelFrom.setText(trip[i].fromAirport);
			        this.add(labelFrom, c);
			        
			        JLabel labelTo = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 2;
			        c.gridy = 2+i;
			        labelTo.setText(trip[i].toAirport);
			        this.add(labelTo, c);
			        
			        JLabel labelDate = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 3;
			        c.gridy = 2+i;
			        labelDate.setText(trip[i].dateDeparture);
			        this.add(labelDate, c);
			        
			        JLabel labelPrice = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 4;
			        c.gridy = 2+i;
			        labelPrice.setText(String.valueOf(trip[i].price));
			        this.add(labelPrice, c);
			        
			        JLabel labelFlightDuration = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 5;
			        c.gridy = 2+i;
			        labelFlightDuration.setText(String.valueOf(trip[i].flightDuration));
			        this.add(labelFlightDuration, c);
	
			        JLabel labelAirline = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 6;
			        c.gridy = 2+i;
			        labelAirline.setText(trip[i].airlineName);
			        this.add(labelAirline, c);
			        
			        JLabel labelTimeDeparture = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 7;
			        c.gridy = 2+i;
			        labelTimeDeparture.setText(String.valueOf(trip[i].timeDeparture));
			        this.add(labelTimeDeparture, c);
		        }
		        
		        
		        //label titill fyrir return flights
		        JLabel labelTitle2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 0;
		        c.gridy = trip.length+2;
		        labelTitle2.setText("Return Flights: ");
		        this.add(labelTitle2, c);
		        
		        
		        
		        //eftirfarandi labels eru titlar fyrir þær leitarniðurstöðurnar eiga að birtast fyrir trip2
		        //Label From
		        JLabel labelFromEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 1;
		        c.gridy = trip.length+3;
		        labelFromEfst2.setText("From");
		        this.add(labelFromEfst2, c);
		        
		        //Label To
		        JLabel labelToEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 2;
		        c.gridy = trip.length+3;
		        labelToEfst2.setText("To");
		        this.add(labelToEfst2, c);
		        
		        //Label Date
		        JLabel labelDateEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 3;
		        c.gridy = trip.length+3;
		        labelDateEfst2.setText("Date");
		        this.add(labelDateEfst2, c);
		        
		        //Label Price
		        JLabel labelPriceEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 4;
		        c.gridy = trip.length+3;
		        labelPriceEfst2.setText("Price");
		        this.add(labelPriceEfst2, c);
		        
		        //Label Time
		        JLabel labelTimeEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 5;
		        c.gridy = trip.length+3;
		        labelTimeEfst2.setText("Time");
		        this.add(labelTimeEfst2, c);
		        
		        //Label Airline
		        JLabel labelAirlineEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 6;
		        c.gridy = trip.length+3;
		        labelAirlineEfst2.setText("Airline");
		        this.add(labelAirlineEfst2, c);
		        
		        //Label TimeDeparture
		        JLabel labelTimeDepartureEfst2 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 7;
		        c.gridy = trip.length+3;
		        labelTimeDepartureEfst2.setText("TimeDeparture");
		        this.add(labelTimeDepartureEfst2, c);
		        
		        
		        
		        
		        
		        //eftirfarandi labels sýna leitarniðurstöður fyrir trip2
		        for(int i=0; i<trip2.length; i++){   
		        
			        JLabel labelFrom = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 1;
			        c.gridy = 1+trip.length+3+i;
			        labelFrom.setText(trip2[i].toAirport); //from label sýnir toAirport því þetta er return flight
			        this.add(labelFrom, c);
			        
			        JLabel labelTo = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 2;
			        c.gridy = 1+trip.length+3+i;
			        labelTo.setText(trip2[i].fromAirport); //to label sýnir fromAirport því þetta er return flight
			        this.add(labelTo, c);
			        
			        JLabel labelDate = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 3; 
			        c.gridy = 1+trip.length+3+i;
			        labelDate.setText(trip2[i].dateDeparture);
			        this.add(labelDate, c);
			        
			        JLabel labelPrice = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 4;
			        c.gridy = 1+trip.length+3+i;
			        labelPrice.setText(String.valueOf(trip2[i].price));
			        this.add(labelPrice, c);
			        
			        JLabel labelFlightDuration = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 5;
			        c.gridy = 1+trip.length+3+i;
			        labelFlightDuration.setText(String.valueOf(trip2[i].flightDuration));
			        this.add(labelFlightDuration, c);
	
			        JLabel labelAirline = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 6;
			        c.gridy = 1+trip.length+3+i;
			        labelAirline.setText(trip2[i].airlineName);
			        this.add(labelAirline, c);
			        
			        JLabel labelTimeDeparture = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 7;
			        c.gridy = 1+trip.length+3+i;
			        labelTimeDeparture.setText(String.valueOf(trip2[i].timeDeparture));
			        this.add(labelTimeDeparture, c);
		        }
		        
		        
		        //label titill fyrir hotel
		        JLabel labelTitle3 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 0;
		        c.gridy = trip.length+trip2.length+4;
		        labelTitle3.setText("Hotels: ");
		        this.add(labelTitle3, c);
		        
		        
		        //eftirfarandi labels eru titlar fyrir þær leitarniðurstöðurnar eiga að birtast fyrir hotel
		        //Label HotelName
		        JLabel labelHotelNameEfst3 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 1;
		        c.gridy = trip.length+trip2.length+5;
		        labelHotelNameEfst3.setText("Hotel name");
		        this.add(labelHotelNameEfst3, c);
		        
		        //Label Date
		        JLabel labelDateFromEfst3 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 2;
		        c.gridy = trip.length+trip2.length+5;
		        labelDateFromEfst3.setText("Date from");
		        this.add(labelDateFromEfst3, c);
		        
		        //Label Date
		        JLabel labelDateToEfst3 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 3;
		        c.gridy = trip.length+trip2.length+5;
		        labelDateToEfst3.setText("Date to");
		        this.add(labelDateToEfst3, c);
		        
		        //Label Price
		        JLabel labelPriceEfst3 = new JLabel();
		        c.weightx = 0.5;
		        c.gridx = 4;
		        c.gridy = trip.length+trip2.length+5;
		        labelPriceEfst3.setText("Price");
		        this.add(labelPriceEfst3, c);
		        
		        
		        
		        //eftirfarandi labels sýna leitarniðurstöður fyrir hotel
		        for(int i=0; i<hotel.length; i++){   
		        
			        JLabel labelHotelName = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 1;
			        c.gridy = trip.length+trip2.length+6+i;
			        labelHotelName.setText(hotel[i].hotelName); //from label sýnir toAirport því þetta er return flight
			        this.add(labelHotelName, c);
			        
			        JLabel labelDateFrom = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 2;
			        c.gridy = trip.length+trip2.length+6+i;
			        labelDateFrom.setText(hotel[i].dateCheckIn); //to label sýnir fromAirport því þetta er return flight
			        this.add(labelDateFrom, c);
			       
			        /*NEXT TO IMPLEMENT
			        JLabel labelDateTo = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 3; 
			        c.gridy = trip.length+trip2.length+6+i;
			        labelDateTo.setText(hotel[i].price);
			        this.add(labelDateTo, c);
			        */
			        
			        JLabel labelPrice = new JLabel();
			        c.weightx = 0.5;
			        c.gridx = 4; 
			        c.gridy = trip.length+trip2.length+6+i;
			        labelPrice.setText(hotel[i].price);
			        this.add(labelPrice, c);
			   
		        }
		        
		        
		        
		        this.add(panel);
		        this.pack();
	}
	
}



