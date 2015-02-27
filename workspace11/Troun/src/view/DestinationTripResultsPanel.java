package view;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DestinationTripResultsPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public DestinationTripResultsPanel() {
		// TODO Auto-generated constructor stub	
		//public static void main(String[] args) {
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JFrame frame = new JFrame();
	    frame.setTitle("My First Swing Application");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JLabel label = new JLabel("Welcome");
	    frame.add(label);
	    frame.pack();
	    frame.setVisible(true);
  }
}