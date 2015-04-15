package view;
//import model.FlightSearch;
//import model.Flight;
import flightsearch.*;
import Archive.*;
import controller.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import java.awt.GridBagLayout;


public abstract class BasicPanel extends JPanel {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	JTextField textDeparture;
	JTextField textReturn;
	private JTextField inputPeople;
	JButton departureDate;
	JButton returnDate;
	JButton searchButton;
	private JComboBox<String> comboFrom;
	private JComboBox<String> comboTo;
	private JComboBox<String> comboHotels;
	JComboBox<String> comboBudget;
	String today = sdf.format(new Date()).toString();
	String[] itemsToIceland = new String[]{"Specify area in Iceland", "Reykjavik", "Akureyri"};
	String[] inlandAirports = new String[]{null, "KEF", "AK"};
	String defaultString = itemsToIceland[0];
	//String[] itemsFrom = new String[]{"select departure airport", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
	String[] itemsFrom = new String[]{"select departure airport", "London", "Copenhagen", "Boston", "China"};
	String[] foreignAirports = new String[] {null, "LHR", "CPH", "BOS", "PEK"};
	String[] itemsTo = new String[]{"select arrival airport", "London", "Copenhagen", "Boston", "China"};
	//String[] itemsTo = new String[]{"select arrival airport", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
	String[] itemsBudget = new String[]{"choose budget", "€1000", "€2000"};
    String[] hotels = new String[] {"Hotel 1", "Hotel 2", "Hotel 3"};
	Controller controller = new Controller();
	//KEF
	//CPH
	//RVK
	//LHR
	//BOS
	//PEK
	//AK

	String mainAirport = "KEF";
	public BasicPanel() {
		this.setPreferredSize(new Dimension(1000, 500));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5,5,5,5);
        this.setBackground(Color.CYAN);
        this.init(c);
	}
	abstract void init(GridBagConstraints c);

	public JButton addButton(GridBagConstraints c, String text, double weightX, double gridwidth, int gridX, int gridY) {
		JButton button = new JButton(text);
		c.gridwidth = (int) gridwidth;
        c.weightx = weightX;
        c.gridx = gridX;
        c.gridy = gridY;
        this.add(button, c);
        return button;
	}
	public void addLabel(GridBagConstraints c, String text, double weightX, double gridwidth, int gridX, int gridY) {
		JLabel label = new JLabel(text);
		c.gridwidth = (int)gridwidth;
        c.weightx = weightX;
        c.gridx = gridX;
        c.gridy = gridY;
        this.add(label, c);
	}
	public JComboBox<String> addComboBox(GridBagConstraints c, String[] text, double weightX, double gridWidth, int gridX, int gridY) {
		JComboBox<String> comboBox = new JComboBox<String>(text);
        c.weightx = weightX;
        c.gridwidth = (int)gridWidth;
        c.gridx = gridX;
        c.gridy = gridY;
        this.add(comboBox, c);
        return comboBox;
	}
	public JTextField addTextField(GridBagConstraints c, int size, String defaultValue, double weightX, int gridwidth, int gridX, int gridY) {
		JTextField tf = new JTextField(size);
		c.gridwidth = gridwidth;
		tf.setText(defaultValue);
        c.weightx = weightX;
        c.gridx = gridX;
        c.gridy = gridY;
        this.add(tf, c);
        return tf;
	}
	public JTextField addUneditableTextField(GridBagConstraints c, int size, String defaultValue, double weightX, int gridwidth, int gridX, int gridY) {
		JTextField tf = new JTextField(size);
		c.gridwidth = gridwidth;
		tf.setText(defaultValue);
	    c.weightx = weightX;
	    c.gridx = gridX;
	    c.gridy = gridY;
	    this.add(tf, c);
	    tf.setEditable(false);
	    return tf;
	}
	public JList<String> addJList(GridBagConstraints c, String[] data, int gridWidth, int gridHeight, int gridX, int gridY) {
		JList<String> list = new JList<String>(data);
		//list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		c.gridwidth = gridWidth;
		c.gridheight = gridHeight;
		c.gridx = gridX;
		c.gridy = gridY;
        this.add(list, c);
        return list;
	}
	public JComboBox<String> getComboFrom() {
		return this.comboFrom;
	}
	public void setComboFrom(GridBagConstraints c, String[] fromLoc) {
		this.comboFrom = this.addComboBox(c,fromLoc,0,2,0,1);	
	}
	public JComboBox<String> getComboTo() {
		return this.comboTo;
	}
	public void setComboTo(GridBagConstraints c, String[] toLoc) {
		this.comboTo = this.addComboBox(c,toLoc,0,2,2,1);	
	}
	public JComboBox<String> getComboBudget() {
		return this.comboBudget;
	}
	public JComboBox<String> getComboHotels() {
		return this.comboHotels;
	}
	public void setComboHotels(GridBagConstraints c) {
		this.comboHotels = this.addComboBox(c, this.hotels, 0, 4, 0, 7);
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
	public String[] getItemsTo() {
		return this.itemsTo;
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
	    this.textDeparture.setEditable(true);
		this.textDeparture.setText(new DatePicker(this).setPickedDate());
	    this.textDeparture.setEditable(false);
	}
	
	public void returnDateAction(ActionEvent ae) {
		this.textReturn.setEditable(true);
		this.textReturn.setText(new DatePicker(this).setPickedDate());
		this.textReturn.setEditable(false);
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
    public String getSelectedForeignAirport(int index) {
    	return this.foreignAirports[index];
    }
    public String getSelectedInlandAirport(int index) {
    	return this.inlandAirports[index];
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
		return controller.searchFlight(date, fromAirport, toAirport, numberPeople, budget); 
    }
    public Hotel[] searchHotel(String dateFrom, String dateTo, String location, int numberPeople, int budget) {
		return controller.searchHotel(dateFrom, dateTo, location, numberPeople, budget);
    }
    public Hotel[] getHotel(String dateFrom, String dateTo, String location, String hotelName, int numberPeople, int budget) {
    	return controller.getHotel( dateFrom, dateTo, location, hotelName, numberPeople, budget);
    }
    public void searchButtonAction(ActionEvent ae) {
    	this.parseInfo(this.getDepartureDate().trim(), this.getReturnDate().trim());
    }
    public abstract void parseInfo(String dateDeparture, String dateReturn);
}