package view;
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


public class OneClickTripPanel extends JPanel{
		
	private static final long serialVersionUID = 1L;
		JTextField textDeparture3;
		JTextField textReturn3;
	    
	    JComboBox<String> comboFrom3;
	    JComboBox<String> comboTo3;
	    JComboBox<String> comboBudget3;
	    
	    JButton departureDate3;
	    JButton returnDate3;
	    JButton searchButton3;
	    
	    
		
	public OneClickTripPanel(){
		
	    this.setLayout(new GridBagLayout());
	    GridBagConstraints c3 = new GridBagConstraints();
	    c3.fill = GridBagConstraints.HORIZONTAL;
	
	    //Label From: 
	    JLabel labelFrom3 = new JLabel("   From: ");
	    c3.weightx = 0.5;
	    c3.gridx = 0;
	    c3.gridy = 0;
	    this.add(labelFrom3, c3);
	
	    //Label To: 
	    JLabel labelTo3 = new JLabel("   To: ");
	    c3.gridx = 1;
	    c3.gridy = 0;
	    this.add(labelTo3, c3);
	    
	    //DropDown menu for home location
	    
	    comboFrom3 = new JComboBox<>(ViewUtils.getDropDownFrom());
	    c3.weightx = 0.5;
	    c3.gridx = 0;
	    c3.gridy = 1;
	    this.add(comboFrom3, c3);
	
	    
	    //DropDown menu for arrival location
	    
	    comboTo3 = new JComboBox<>(ViewUtils.getDropDownTo());
	    c3.gridx = 1;
	    c3.gridy = 1;
	    this.add(comboTo3, c3);
	   
	  	//DropDown menu for arrival location
	    
	    comboBudget3 = new JComboBox<>(ViewUtils.getDropDownHotel());
	    //c.ipady = 40; 
	    c3.gridwidth = 3;
	    c3.weightx = 0.0;
	    c3.gridx = 0;
	    c3.gridy = 5;
	    this.add(comboBudget3, c3);
	    
	  //button for departure date, date picker 
	    departureDate3 = new JButton();
	    departureDate3.setText("Departure Date");
	    textDeparture3 = new JTextField(20);
	    departureDate3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	departureDateAction(ae);
            }
			
        });
	    c3.gridwidth = 1;
	    c3.weightx = 0.5;
	    c3.gridx = 0;
	    c3.gridy = 4;
	    this.add(textDeparture3, c3);
	    c3.gridx = 0;
	    c3.gridy = 3;
	    this.add(departureDate3, c3);
	    
	    //button for return date, date picker 
	    returnDate3 = new JButton();
	    returnDate3.setText("Return Date");
	    textReturn3 = new JTextField(20);
	    returnDate3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	returnDateAction(ae);
            }
        });
	    c3.gridwidth = 1;
	    c3.gridx = 1;
	    c3.gridy = 4;
	    this.add(textReturn3, c3);
	    
	    c3.gridx = 1;
	    c3.gridy = 3;
	    this.add(returnDate3, c3);
	    
	    //search button
	    searchButton3 = new JButton("Search");
	    c3.ipady = 0;       //reset to default
	    c3.weighty = 1.0;   //request any extra vertical space
	    c3.anchor = GridBagConstraints.PAGE_END; //bottom of space
	    c3.insets = new Insets(10,0,0,0);  //top padding
	    c3.gridx = 1;       //aligned with button 2
	    c3.gridwidth = 2;   //2 columns wide
	    c3.gridy = 6;       //third row
	    this.add(searchButton3, c3);
	    searchButton3.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	searchButtonAction(ae);
	            }
	    });

	}

	
	private void departureDateAction(ActionEvent ae) {
		textDeparture3.setText(new DatePicker(this).setPickedDate());
	}
	
	
	private void returnDateAction(ActionEvent ae) {
		textReturn3.setText(new DatePicker(this).setPickedDate());
	}
	
	private void searchButtonAction(ActionEvent ae) {
		
	}
	
	
}
