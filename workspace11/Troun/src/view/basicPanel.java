import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;


public abstract class basicPanel extends JPanel {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	JTextField textDeparture;
	JTextField textReturn;
	private JTextField inputPeople;
	JButton departureDate;
	JButton returnDate;
	JButton searchButton;
	private JComboBox<String> comboFrom;
	private JComboBox<String> comboTo;
	JComboBox<String> comboBudget;
	String today = sdf.format(new Date()).toString();
	String[] itemsToIceland = new String[]{"Specify area in Iceland", "Keflavik", "Akureyri"};
	String defaultString = itemsToIceland[0];
	String[] itemsFrom = new String[]{"select departure airport", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
	String[] itemsBudget = new String[]{"choose budget", "€1000", "€2000"};
	Controller controller = new Controller();

	String mainAirport = "Keflavik";
	public JButton addButton(GridBagConstraints c, String text, double weightX, double gridwidth, int gridX, int gridY) {
		JButton button = new JButton(text);
		c.gridwidth = (int) gridwidth;
        c.weightx = weightX;
        c.gridx = (int)gridX;
        c.gridy = (int)gridY;
        this.add(button, c);
        return button;
	}
	public void addLabel(GridBagConstraints c, String text, double weightX, double gridwidth, double gridX, double gridY) {
		JLabel label = new JLabel(text);
		c.gridwidth = (int)gridwidth;
        c.weightx = weightX;
        c.gridx = (int)gridX;
        c.gridy = (int)gridY;
        this.add(label, c);
	}
	public JComboBox addComboBox(GridBagConstraints c, String[] text, double weightX, double gridWidth, double gridX, double gridY) {
		JComboBox comboBox = new JComboBox(text);
        c.weightx = weightX;
        c.gridwidth = (int)gridWidth;
        c.gridx = (int)gridX;
        c.gridy = (int)gridY;
        this.add(comboBox, c);
        return comboBox;
	}
	public JTextField addTextField(GridBagConstraints c, int size, String defaultValue, double weightX, int gridwidth, int gridX, int gridY) {
		JTextField tf = new JTextField(size);
		c.gridwidth = gridwidth;
		tf.setText(defaultValue);
        c.weightx = weightX;
        c.gridx = (int)gridX;
        c.gridy = (int)gridY;
        this.add(tf, c);
        return tf;
	}
	public JList addJList(GridBagConstraints c, String[] data, int gridWidth, int gridHeight, int gridX, int gridY) {
		JList list = new JList(data);
		//list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		c.insets = new Insets(5,5,5,5);
		c.gridwidth = gridWidth;
		c.gridheight = gridHeight;
		c.gridx = gridX;
		c.gridy = gridY;
        this.add(list, c);
        return list;
	}
	public JComboBox getComboFrom() {
		return this.comboFrom;
	}
	public void setComboFrom(GridBagConstraints c, String[] fromLoc) {
		this.comboFrom = this.addComboBox(c,fromLoc,0,2,0,1);	
	}
	public JComboBox getComboTo() {
		return this.comboTo;
	}
	public void setComboTo(GridBagConstraints c, String[] toLoc) {
		this.comboTo = this.addComboBox(c,toLoc,0,2,2,1);	
	}
	public JComboBox getComboBudget() {
		return this.comboBudget;
	}
	public String getNumberPeople() {
		return this.getTFnumPeople().getText();
	}
	public JTextField getTFnumPeople() {
		return this.inputPeople;
	}
	public void setTFnumPeople(GridBagConstraints c, int size) {
		this.inputPeople = this.addTextField(c,size,"1",0,3,1,5);
	}

	public String[] getItemsFrom() {
		return this.itemsFrom;
	}
	public String getDepartureDate() {
		return this.textDeparture.getText();
	}
	public String getReturnDate() {
		return this.textReturn.getText();
	}
	public String[] getItemsToIceland() {
		return this.itemsToIceland;
	}
	public String[] getItemsBudget() {
		return this.itemsBudget;
	}
	public String getDate() {
		return this.today;
	}
	public JButton getDepartureDateButton() {
		return this.departureDate;
	}
	public JButton getReturnDateButton() {
		return this.returnDate;
	}
	public JButton getSearchButton() {
		return this.searchButton;
	}
	public void departureDateAction(ActionEvent ae) {
		// TODO Auto-generated method stub
		this.textDeparture.setText(new DatePicker(this).setPickedDate());
	}
	
	public void returnDateAction(ActionEvent ae) {
		// TODO Auto-generated method stub
		this.textReturn.setText(new DatePicker(this).setPickedDate());
	}
	public void infoBox(String infoMessage, String titleBar){
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    public int convertDateToInt(String a) {
    	a = a.substring(0,4) + a.substring(5,7) + a.substring(8,10);
    	return Integer.parseInt(a);
    }
    public boolean validateDates(String a, String b) {
    	if(a.equals("") || b.equals(""))
    		return false;
        if(a.matches("^((0[1-9])|([1-2][0-9])|(3[0-1])).((0[1-9])|1[0-2]).20([0-9][0-9])$"))
            a = a.substring(6,10) + "/" + a.substring(3,5) + "/" + a.substring(0,2);
        if(b.matches("^((0[1-9])|([1-2][0-9])|(3[0-1])).((0[1-9])|1[0-2]).20([0-9][0-9])$"))
            b = b.substring(6,10) + "/" + b.substring(3,5) + "/" + b.substring(0,2);
    	if(!a.matches("^20([0-9][0-9]).((0[1-9])|1[0-2]).((0[1-9])|([1-2][0-9])|(3[0-1]))$"))
            return false;
        if(!b.matches("^20([0-9][0-9]).((0[1-9])|1[0-2]).((0[1-9])|([1-2][0-9])|(3[0-1]))$"))
            return false;
    	int datea = convertDateToInt(a);
    	int dateb = convertDateToInt(b);
    	return datea < dateb && datea >= convertDateToInt(today);
    }
    public void validateInfo() {

    }
    public void inputActions() {
		this.getDepartureDateButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	departureDateAction(ae);
            }
			
        });
		this.getReturnDateButton().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	returnDateAction(ae);
            }
        });
		this.getSearchButton().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	searchButtonAction(ae);
	            }
	    });
	}
	public void addActionSearchButton() {
		this.getSearchButton().addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	searchButtonAction(ae);
	            }
	    });
	}
    public Flight[] searchFlight(String date, String fromAirport, String toAirport, int numberPeople, int budget) {
    	//DestinationTripController controller = new DestinationTripController();
		//return controller.findTrips(date, fromAirport, toAirport, numberPeople);
		return controller.searchFlight(date, fromAirport, toAirport, numberPeople, budget); 
    }
    public Hotel[] searchHotel(String date, String location, int numberPeople, int budget) {
    	//DestinationTripController controller = new DestinationTripController();
		//return controller.findHotels(date, location, numberPeople);
		return controller.searchHotel(date, location, numberPeople, budget);
    }
    public Hotel[] getHotel(String date, String location, String hotelName, int numberPeople, int budget) {
    	return controller.getHotel( date, location, hotelName, numberPeople, budget);
    }
    public void searchButtonAction(ActionEvent ae) {
    	this.parseInfo(this.getDepartureDate().trim(), this.getReturnDate().trim());
    }
    public abstract void parseInfo(String dateDeparture, String dateReturn);
}