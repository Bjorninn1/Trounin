/*package flightsearch;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class Flight_interface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_fromAirport;
	private JTextField txt_toAirport;
	private JTextField txt_dateDeparture;
	private JTextField txt_nr_passengers;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Flight_interface frame = new Flight_interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Flight_interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		FindFlights findFlights = new FindFlights();
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbl_DepartureAirport = new JLabel("From :");
		lbl_DepartureAirport.setBounds(36, 118, 43, 14);
		panel.add(lbl_DepartureAirport);
		
		JLabel lbl_ArrivalAirport = new JLabel("To : ");
		lbl_ArrivalAirport.setBounds(130, 118, 31, 14);
		panel.add(lbl_ArrivalAirport);
		
		txt_fromAirport = new JTextField();
		txt_fromAirport.setBounds(77, 115, 43, 20);
		panel.add(txt_fromAirport);
		txt_fromAirport.setColumns(10);
		
		txt_toAirport = new JTextField();
		txt_toAirport.setBounds(158, 115, 43, 20);
		panel.add(txt_toAirport);
		txt_toAirport.setColumns(10);
		
		JLabel lbl_DepartureDate = new JLabel("Date :");
		lbl_DepartureDate.setBounds(211, 118, 43, 14);
		panel.add(lbl_DepartureDate);
		
		txt_dateDeparture = new JTextField();
		txt_dateDeparture.setHorizontalAlignment(SwingConstants.TRAILING);
		txt_dateDeparture.setBounds(245, 115, 86, 20);
		panel.add(txt_dateDeparture);
		txt_dateDeparture.setColumns(10);
	
		JLabel lblNrPassengers = new JLabel("");
		lblNrPassengers.setBounds(144, 43, 57, 14);
		panel.add(lblNrPassengers);
		
		txt_nr_passengers = new JTextField();
		txt_nr_passengers.setBounds(132, 150, 44, 20);
		panel.add(txt_nr_passengers);
		txt_nr_passengers.setColumns(10);
		
		JLabel lbl_nr_of_passengers = new JLabel("Nr. of passengers");
		lbl_nr_of_passengers.setBounds(36, 153, 102, 14);
		panel.add(lbl_nr_of_passengers);
		
		JButton button_FindFlights = new JButton("Find Flights");
		button_FindFlights.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fromAirport = txt_fromAirport.getText();
				String toAirport = txt_toAirport.getText();
				String dateDeparture = txt_dateDeparture.getText();
				String nrPassengersString= txt_nr_passengers.getText();
				

				int nrPassengers = Integer.parseInt(nrPassengersString);
				
				
				boolean result = findFlights.bookFlight("2015-04-29","RA04", 5, "RVK");
				System.out.println(result);
				ArrayList<Flight> rightFlights = findFlights.dbflights(fromAirport, toAirport, dateDeparture, nrPassengers);
				
				if(rightFlights == null){
					System.out.println("No flight found");
				}else {
					int listSize = rightFlights.size();
					for(int i = 0; i < listSize; i++){
						
						System.out.println("From airport: " + rightFlights.get(i).getFromAirport());
						System.out.println("To airport: " + rightFlights.get(i).getToAirport());
						System.out.println("Date and time departure : " + rightFlights.get(i).getDateDeparture());
						System.out.println("Arrival time : " + rightFlights.get(i).getDateArrival());
						System.out.println("Price for each passenger :" + rightFlights.get(i).getPrice());
					}	
				}
			}
		});
		button_FindFlights.setBounds(186, 149, 145, 23);
		panel.add(button_FindFlights);
	}
}
*/