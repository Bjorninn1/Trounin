package view;
import model.*;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;


//þessi klasi býr til Panel með 3 tabs úr klösunum DestinationTripPanel, StopoverTripPanel og OneClickTripPanel

public class TripPlanning extends JPanel {
    
    final JTabbedPane tabbedPane;
    public static TripPlanning tripPlanning = new TripPlanning(); 
    
    private static final long serialVersionUID = 1L;
    boolean resultsShown = false;

    public TripPlanning() {
        
        super(new GridLayout(1, 1));
         
        tabbedPane = new JTabbedPane();
        
        JPanel tab1;
        JPanel tab2;
        JPanel tab3;
        
        tab1 = new DestinationTripPanel();
        tab2 = new StopoverTripPanel();
        tab3 = new OneClickTripPanel();
        //tab4 = new ResultView();
        
        tabbedPane.addTab("Destination trip", null, tab1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        tabbedPane.addTab("Stopover trip", null, tab2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        tabbedPane.addTab("One-click flight+hotel", null, tab3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
    }//constructor endar hér
    public void showResultsView(Hotel[] hotels, Flight[][] flights) {
        if(this.resultsShown)
            tabbedPane.remove(3);
        else
            this.resultsShown = !this.resultsShown;
        //tabbedPane.setEnabledAt(3, true);
        tabbedPane.insertTab("Results", null, new ResultView(hotels, flights), "Search Results",3);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        tabbedPane.setSelectedIndex(3);
    }
     
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Trip Planning");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Add content to the window.
        frame.add(tripPlanning, BorderLayout.CENTER);
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
     
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }
}



