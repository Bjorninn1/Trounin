package view;
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
import controller.DestinationTripController;



public class DestinationTripPanel extends JPanel {
	
		private static final long serialVersionUID = 1L;
		//instance variables for panel1: 
		JTextField textDeparture;
		JTextField textReturn;
		JComboBox<String> comboFrom;
		JComboBox<String> comboTo;
		JComboBox<String> comboBudget;
		JButton departureDate;
		JButton returnDate;
		JButton searchButton;
		
		
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

        comboFrom = new JComboBox<>(ViewUtils.getDropDownFrom());
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        this.add(comboFrom, c);

        //DropDown menu for arrival location
        
        comboTo = new JComboBox<>(ViewUtils.getDropDownTo());
        c.gridx = 1;
        c.gridy = 1;
        this.add(comboTo, c);
       
      	//DropDown menu for arrival location
        
        comboBudget = new JComboBox<>(ViewUtils.getDropDownBudget());
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
        	//ath. sj?? returnDateAction(ae) fyrir ne??an constructor (s?? a??fer?? tekur vi?? eventinum) 
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
		
		String dateDeparture = textDeparture.getText();
		String dateReturn = textReturn.getText();
		String fromAirport = (String) comboFrom.getSelectedItem();
		String toAirport = (String) comboTo.getSelectedItem();
		String budgetString = (String) comboBudget.getSelectedItem();
		int budget = Integer.parseInt(budgetString);
		
		DestinationTripController controller = new DestinationTripController();
		controller.setTrip1(dateDeparture, fromAirport, toAirport);
		controller.setTrip2(dateReturn, fromAirport, toAirport);
		controller.setBudget(budget);
		controller.search();
	}

}
