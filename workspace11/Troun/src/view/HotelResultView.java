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

public class HotelResultView extends BasicPanel {
	Hotel[] hotels;
	Flight[] flightsToBook;
	String dateDeparture;
	String dateReturn;
	int numberPeople;
	JComboBox<String> hotelCB;
	JComboBox<String> roomsCB;
    GridBagConstraints c = new GridBagConstraints();
	public HotelResultView(Hotel[] hotels, Flight[] flightsToBook, String dateDeparture, String dateReturn, int numberPeople) {
		this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new GridBagLayout());
        this.dateDeparture = dateDeparture;
        this.dateReturn = dateReturn;
        this.flightsToBook = flightsToBook;
        this.numberPeople = numberPeople;
        this.c.fill = GridBagConstraints.HORIZONTAL;
		this.c.insets = new Insets(5,5,5,5);
        this.init(this.c, hotels);
	}
	void init(GridBagConstraints c) {
	}
	void init(GridBagConstraints c, Hotel[] hotels) {
        this.hotels = hotels;
		String[] current;
		this.addLabel(c, "Hotels: ",0,1,0,0);
		current = this.parseHotels();
		this.hotelCB = this.addComboBox(c, current, 0 , 1 , 0, 1);
		this.addActionComboBox(this.hotelCB);
		this.addLabel(c, "Rooms: ",0,1,1,0);
		this.roomsCB = this.addComboBox(c, new String[]{"Pick Hotel First"},0,1,1,1);
		this.searchButton = this.addButton(c,"Book",0,1, 1, 2);
		this.addActionSearchButton();
	}
	String[] parseHotels() {
		String[] hotelNames = new String[this.hotels.length+1];
		hotelNames[0] = "Select Hotel";
		for(int i = 0; i < this.hotels.length; i++) {
			hotelNames[i+1] = this.hotels[i].getName();
		}
		return hotelNames;
	}
	String[] parseRooms(HotelRoom[] rooms) {
		String[] roomInfo = new String[rooms.length+1];
		roomInfo[0] = "Select room";
		for(int i = 0; i < rooms.length; i++) {
			roomInfo[i+1] = "Beds: "+rooms[i].getBedCount();
			roomInfo[i+1] += " Price: $"+rooms[i].getNightPrice();
		}
		return roomInfo;
	}
	public void addActionComboBox(final JComboBox<String> cb) {
		cb.addActionListener(new ActionListener() {//add actionlistner to listen for change
            public void actionPerformed(ActionEvent e) {
                int i = cb.getSelectedIndex()-1;//get the selected item
                String[] rooms;
                if(i < 0) rooms = new String[] {"Select Hotel First"};
                else rooms = HotelResultView.this.parseRooms(HotelResultView.this.hotels[i].getRooms());
                DefaultComboBoxModel model = new DefaultComboBoxModel( rooms );
				HotelResultView.this.roomsCB.setModel( model );
	    	}
	    });
	}
    public void parseInfo(String dateDeparture, String dateReturn) {
    	if(hotelCB.getSelectedIndex() == 0 || roomsCB.getSelectedIndex() == 0) {
    		this.infoBox("Please fill out all options","Error");
    		return;
    	}
    	Hotel hotel = this.hotels[hotelCB.getSelectedIndex()-1];
    	HotelRoom room = hotel.getRooms()[roomsCB.getSelectedIndex()-1];
        DateRange dateR = new DateRange(Integer.parseInt(this.dateDeparture.substring(8)),Integer.parseInt(this.dateDeparture.substring(5,7)),Integer.parseInt(this.dateDeparture.substring(0,4)),Integer.parseInt(this.dateReturn.substring(8)),Integer.parseInt(this.dateReturn.substring(5,7)),Integer.parseInt(this.dateReturn.substring(0,4)));
        int costRoom = room.getNightPrice();
        int days = this.convertDateToInt(this.dateReturn)-this.convertDateToInt(this.dateDeparture);
        int totalCost = costRoom*days;

    	Controller controller = new Controller();
        boolean valid = controlller.book(flightsToBook, hotel, room, this.numberPeople);
        if(!valid) {
        	this.infoBox("Booking unsuccessful, please pick something else", "Error");
        	return;
        }
        for(int i = 0; i < this.flightsToBook.length; i++) {
        	totalCost += this.flightsToBook[i].getPrice()/120;
        }
        this.infoBox("Booking successful, total cost: $"+(totalCost*this.numberPeople)+"\n"+days + " Nights at $"+costRoom+" per night = $"+(days*costRoom)+"\n$"+(totalCost-days*costRoom)+" for flights \n *"+this.numberPeople+" people = $"+(totalCost*this.numberPeople),"Success");
    }
    public String getReturnDate() {
		return "";
	}
	public String getDepartureDate() {
		return "";
	}
}
