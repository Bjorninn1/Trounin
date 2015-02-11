package View;
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

import Controller.DestinationTripController;


public class StopoverTripPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
		//instance variables for panel2:
		final JTextField textDeparture2;	
		final JTextField textReturn2;
		
		JComboBox<String> comboFrom2;
		JComboBox<String> comboTo2;
		JComboBox<String> comboBudget2;
		JComboBox<String> comboIceland;
		
		JButton departureDate2;
		JButton returnDate2;
		JButton searchButton2;
		
		String[] itemsFrom2 = new String[]{"Choose!", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
		String[] itemsTo2 = new String[]{"Choose!", "New York", "Boston"};
		String[] itemsBudget2 = new String[]{"choose budget", "€1000", "€2000"};
		String[] itemsIceland = new String[]{"specify area in Iceland", "Reykjavik", "Akureyri"};
	
		public StopoverTripPanel(){
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c2 = new GridBagConstraints();
	    c2.fill = GridBagConstraints.HORIZONTAL;
	       
	    //Label From: 
	    JLabel labelFrom2 = new JLabel("   From: ");
	    c2.weightx = 0.5;
	    c2.gridx = 0;
	    c2.gridy = 0;
	    this.add(labelFrom2, c2);
	
	    //Label To: 
	    JLabel labelTo2 = new JLabel("   To: ");
	    c2.gridx = 1;
	    c2.gridy = 0;
	    this.add(labelTo2, c2);
	    
	    //DropDown menu for home location
	    
	    comboFrom2 = new JComboBox<>(itemsFrom2);
	    c2.weightx = 0.5;
	    c2.gridx = 0;
	    c2.gridy = 1;
	    this.add(comboFrom2, c2);
	
	    
	    //DropDown menu for arrival location
	    
	    comboTo2 = new JComboBox<>(itemsTo2);
	    c2.gridx = 1;
	    c2.gridy = 1;
	    this.add(comboTo2, c2);
	        
	  //button for departure date, date picker 
	    departureDate2 = new JButton();
	    departureDate2.setText("Departure Date");
	    textDeparture2 = new JTextField(20);
	    departureDate2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	departureDateAction(ae);
            }

			
        });
	    c2.gridwidth = 1;
	    c2.weightx = 0.5;
	    c2.gridx = 0;
	    c2.gridy = 4;
	    this.add(textDeparture2, c2);
	    c2.gridx = 0;
	    c2.gridy = 3;
	    this.add(departureDate2, c2);
	    
	    //button for return date, date picker 
	    returnDate2 = new JButton();
	    returnDate2.setText("Departure Date From Iceland");
	    textReturn2 = new JTextField(20);
	    returnDate2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	returnDateAction(ae);
            }

			
        });
	    c2.gridwidth = 1;
	    c2.gridx = 1;
	    c2.gridy = 4;
	    this.add(textReturn2, c2);
	    
	    c2.gridx = 1;
	    c2.gridy = 3;
	    this.add(returnDate2, c2);
	    
	    
	    //DropDown menu to specify area in Iceland
	    
	    comboIceland = new JComboBox<>(itemsIceland);
	    //c.ipady = 40; 
	    c2.gridwidth = 3;
	    c2.weightx = 0.0;
	    c2.gridx = 0;
	    c2.gridy = 5;
	    this.add(comboIceland, c2);
	    
	    
	    //DropDown menu for arrival location
	    
	    comboBudget2 = new JComboBox<>(itemsBudget2);
	    //c.ipady = 40; 
	    c2.gridwidth = 3;
	    c2.weightx = 0.0;
	    c2.gridx = 0;
	    c2.gridy = 6;
	    this.add(comboBudget2, c2);
	
	    
	    //search button
	    searchButton2 = new JButton("Search");
	    c2.ipady = 0;       //reset to default
	    c2.weighty = 1.0;   //request any extra vertical space
	    c2.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    c2.insets = new Insets(10,0,0,0);  //top padding
	    c2.gridx = 1;       //aligned with button 2
	    c2.gridwidth = 2;   //2 columns wide
	    c2.gridy = 7;       //third row
	    this.add(searchButton2, c2);
	    searchButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	searchButtonAction(ae);
            }
        });

	}
		
		
		private void departureDateAction(ActionEvent ae) {
			// TODO Auto-generated method stub
			textDeparture2.setText(new DatePicker(this).setPickedDate());
		}
		
		
		private void returnDateAction(ActionEvent ae) {
			// TODO Auto-generated method stub
			textReturn2.setText(new DatePicker(this).setPickedDate());
		}

		private void searchButtonAction(ActionEvent ae) {
			
			
			
		}
		
	
}
