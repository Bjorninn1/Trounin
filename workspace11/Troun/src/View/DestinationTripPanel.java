package View;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.DestinationTripController;


public class DestinationTripPanel extends JPanel {
	
		private static final long serialVersionUID = 1L;
		//instance variables for panel1: 
		final JTextField textDeparture;
		final JTextField textReturn;
		JComboBox<String> comboFrom;
		JComboBox<String> comboTo;
		JComboBox<String> comboBudget;
		JButton departureDate;
		JButton returnDate;
		JButton searchButton;
		
		String[] itemsFrom = new String[]{"Choose!", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
		String[] itemsTo = new String[]{"Choose!", "Keflavik", "Akureyri"};
		String[] itemsBudget = new String[]{"choose budget", "€1000", "€2000"};
		
		
		
	public DestinationTripPanel() {
        this.setPreferredSize(new Dimension(500, 500));
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
      
        //Label From: 
        JLabel labelFrom = new JLabel("   From: ");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        this.add(labelFrom, c);

        //Label To: 
        JLabel labelTo = new JLabel("   To: ");
        c.gridx = 1;
        c.gridy = 0;
        this.add(labelTo, c);
        
        //DropDown menu for home location

        comboFrom = new JComboBox<>(itemsFrom);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        this.add(comboFrom, c);

        //DropDown menu for arrival location
        
        comboTo = new JComboBox<>(itemsTo);
        c.gridx = 1;
        c.gridy = 1;
        this.add(comboTo, c);
       
      	//DropDown menu for arrival location
        
        comboBudget = new JComboBox<>(itemsBudget);
        //c.ipady = 40; 
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 5;
        this.add(comboBudget, c);
        
        //button for departure date, date picker 
        departureDate = new JButton();
        departureDate.setText("Departure Date");
        textDeparture = new JTextField(20);
        departureDate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	departureDateAction(ae);
            }
        });
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        this.add(textDeparture, c);
        c.gridx = 0;
        c.gridy = 3;
        this.add(departureDate, c);
        
        
        
        //button for return date, date picker 
        returnDate = new JButton();
        returnDate.setText("Return Date");
        textReturn = new JTextField(20);
        returnDate.addActionListener(new ActionListener() {
        	//ath. sjá returnDateAction(ae) fyrir neðan constructor (sú aðferð tekur við eventinum) 
            public void actionPerformed(ActionEvent ae) {
            	returnDateAction(ae);
            }
        });
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 4;
        this.add(textReturn, c);
        
        c.gridx = 1;
        c.gridy = 3;
        this.add(returnDate, c);
        
        //search button
        searchButton = new JButton("Search");
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 6;       //third row
        this.add(searchButton, c);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	searchButtonAction(ae);
            }
        });

        
	}
	
	
	private void returnDateAction(ActionEvent ae){
		textReturn.setText(new DatePicker(this).setPickedDate());
	}
	
	 protected void departureDateAction(ActionEvent ae) {
	    	textDeparture.setText(new DatePicker(this).setPickedDate());
		}
	
	 
	
	private void searchButtonAction(ActionEvent ae) {
		DestinationTripController controller = new DestinationTripController();
		
		
		String dateDeparture = textDeparture.getText();
		String fromAirport = (String) comboFrom.getSelectedItem();
		String toAirport = (String) comboTo.getSelectedItem();
		
		controller.findTrips(dateDeparture, fromAirport, toAirport);
		
		
		
		//virkar
		JFrame frame = new JFrame("Search Results");
		JPanel panel = new JPanel();
        frame.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(500, 500));
		frame.setPreferredSize(new Dimension(500, 500));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
      
        
      //Label From: 
        JLabel labelFromEfst = new JLabel();
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        labelFromEfst.setText("From");
        frame.add(labelFromEfst, c);
        
        //Label To: 
        JLabel labelToEfst = new JLabel();
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        labelToEfst.setText("To");
        frame.add(labelToEfst, c);
        
        //Label Date: 
        JLabel labelDateEfst = new JLabel();
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 0;
        labelDateEfst.setText("Date");
        frame.add(labelDateEfst, c);
        
        //Label Price: 
        JLabel labelPriceEfst = new JLabel();
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 0;
        labelPriceEfst.setText("Price");
        frame.add(labelPriceEfst, c);
        
        //Label Time: 
        JLabel labelTimeEfst = new JLabel();
        c.weightx = 0.5;
        c.gridx = 5;
        c.gridy = 0;
        labelTimeEfst.setText("Time");
        frame.add(labelTimeEfst, c);
        
        //Label Airline: 
        JLabel labelAirlineEfst = new JLabel();
        c.weightx = 0.5;
        c.gridx = 6;
        c.gridy = 0;
        labelAirlineEfst.setText("Airline");
        frame.add(labelAirlineEfst, c);
        
        
        
        
        
        
        
        //Label From: 
        JLabel labelFrom = new JLabel();
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        labelFrom.setText(fromAirport);
        frame.add(labelFrom, c);
        
        //Label To: 
        JLabel labelTo = new JLabel();
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        labelTo.setText(toAirport);
        frame.add(labelTo, c);
        
        //Label Date: 
        JLabel labelDate = new JLabel();
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        labelDate.setText(dateDeparture);
        frame.add(labelDate, c);
        
        //Label Price: 
        JLabel labelPrice = new JLabel();
        c.weightx = 0.5;
        c.gridx = 4;
        c.gridy = 1;
        labelPrice.setText(String.valueOf(controller.prices[0]));
        frame.add(labelPrice, c);
        
        
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
	
		/*
	     JFrame frame = new JFrame("Example of TableLayout");
	        frame.setSize(450, 450);

	        double size[][] = {{10, 75, 75, 75, 75, 75, 10}, // Columns
	            {10, 75, 75, 75, 75, 75, 10}}; // Rows

	        //frame.setLayout(new LayoutManager(new Dimension(500,500)));


	        String label[] = {"(1,1)", "(1,5)", "(1,3)", "(5,3)", "(3,3)"};
	        JButton button[] = new JButton[label.length];

	        for (int i = 0; i < label.length; i++) {
	            button[i] = new JButton(label[i]);
	        }


	        frame.add(button[0], "1, 1");
	        frame.add(button[1], "1, 5");
	        frame.add(button[2], "1, 3");
	        frame.add(button[3], "5, 3");
	        frame.add(button[4], "3, 3");

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	    }
		*/
		
	}

}
