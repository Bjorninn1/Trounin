import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
 

//date picker imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





public class TabbedPaneDemo extends JPanel {
  
	
	private static final long serialVersionUID = 1L;

	public TabbedPaneDemo() {
		
		super(new GridLayout(1, 1));
         
        final JTabbedPane tabbedPane = new JTabbedPane();
     //   ImageIcon icon = createImageIcon("images/middle.gif");
         
        JComponent panel1 = makeTextPanel("");
        panel1.setPreferredSize(new Dimension(500, 500));
        tabbedPane.addTab("Destination trip", null, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JComponent panel2 = makeTextPanel("");
        panel2.setPreferredSize(new Dimension(500, 500));
        tabbedPane.addTab("Stopover trip", null, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
         
        JComponent panel3 = makeTextPanel("");
        panel2.setPreferredSize(new Dimension(500, 500));
        tabbedPane.addTab("One-click flight+hotel", null, panel3,
                "Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
         
        /*
        JComponent panel4 = makeTextPanel(
                "Panel #4 (has a preferred size of 410 x 50).");
        panel4.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Tab 4", null, panel4,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
         */
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
         
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        //
        //
        //panel1 
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        //Label From: 
        JLabel labelFrom = new JLabel("   From: ");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel1.add(labelFrom, c);

        //Label To: 
        JLabel labelTo = new JLabel("   To: ");
        c.gridx = 1;
        c.gridy = 0;
        panel1.add(labelTo, c);
        
        //DropDown menu for home location
        String[] itemsFrom = new String[]{"Choose!", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
        JComboBox<String> comboFrom = new JComboBox<>(itemsFrom);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        panel1.add(comboFrom, c);

        //DropDown menu for arrival location
        String[] itemsTo = new String[]{"Choose!", "Reykjavik", "Akureyri"};
        JComboBox<String> comboTo = new JComboBox<>(itemsTo);
        c.gridx = 1;
        c.gridy = 1;
        panel1.add(comboTo, c);
       
      	//DropDown menu for arrival location
        String[] itemsBudget = new String[]{"choose budget", "€1000", "€2000"};
        JComboBox<String> comboBudget = new JComboBox<>(itemsBudget);
        //c.ipady = 40; 
        c.gridwidth = 3;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 5;
        panel1.add(comboBudget, c);
        
      //button for departure date, date picker 
        JButton departureDate = new JButton();
        departureDate.setText("Departure Date");
        final JTextField textDeparture = new JTextField(20);
        departureDate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	textDeparture.setText(new DatePicker(tabbedPane).setPickedDate());
            }
        });
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        panel1.add(textDeparture, c);
        c.gridx = 0;
        c.gridy = 3;
        panel1.add(departureDate, c);
        
        //button for return date, date picker 
        JButton returnDate = new JButton();
        returnDate.setText("Return Date");
        final JTextField textReturn = new JTextField(20);
        returnDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	textReturn.setText(new DatePicker(tabbedPane).setPickedDate());
            }
        });
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 4;
        panel1.add(textReturn, c);
        
        c.gridx = 1;
        c.gridy = 3;
        panel1.add(returnDate, c);
        
        //search button
        JButton searchButton = new JButton("Search");
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 1;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 6;       //third row
        panel1.add(searchButton, c);
        
        
        
        
        //panel2 stopover trip
        panel2.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.HORIZONTAL;
        
    /*    
        JButton button = new JButton("Long-Named Button 4");
       // c.ipady = 40;      //make this component tall
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        panel2.add(button, c2);
*/
       

        //Label From: 
        JLabel labelFrom2 = new JLabel("   From: ");
        c2.weightx = 0.5;
        c2.gridx = 0;
        c2.gridy = 0;
        panel2.add(labelFrom2, c2);

        //Label To: 
        JLabel labelTo2 = new JLabel("   To: ");
        c2.gridx = 1;
        c2.gridy = 0;
        panel2.add(labelTo2, c2);
        
        //DropDown menu for home location
        String[] itemsFrom2 = new String[]{"Choose!", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
        JComboBox<String> comboFrom2 = new JComboBox<>(itemsFrom2);
        c2.weightx = 0.5;
        c2.gridx = 0;
        c2.gridy = 1;
        panel2.add(comboFrom2, c2);

        //DropDown menu for arrival location
        String[] itemsTo2 = new String[]{"Choose!", "New York", "Boston"};
        JComboBox<String> comboTo2 = new JComboBox<>(itemsTo2);
        c2.gridx = 1;
        c2.gridy = 1;
        panel2.add(comboTo2, c2);
       
      	
        
      //button for departure date, date picker 
        JButton departureDate2 = new JButton();
        departureDate2.setText("Departure Date");
        final JTextField textDeparture2 = new JTextField(20);
        departureDate2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	textDeparture2.setText(new DatePicker(tabbedPane).setPickedDate());
            }
        });
        c2.gridwidth = 1;
        c2.weightx = 0.5;
        c2.gridx = 0;
        c2.gridy = 4;
        panel2.add(textDeparture2, c2);
        c2.gridx = 0;
        c2.gridy = 3;
        panel2.add(departureDate2, c2);
        
        //button for return date, date picker 
        JButton returnDate2 = new JButton();
        returnDate2.setText("Departure Date From Iceland");
        final JTextField textReturn2 = new JTextField(20);
        returnDate2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	textReturn2.setText(new DatePicker(tabbedPane).setPickedDate());
            }
        });
        c2.gridwidth = 1;
        c2.gridx = 1;
        c2.gridy = 4;
        panel2.add(textReturn2, c2);
        
        c2.gridx = 1;
        c2.gridy = 3;
        panel2.add(returnDate2, c2);
        
        
        //DropDown menu to specify area in Iceland
        String[] itemsIceland = new String[]{"specify area in Iceland", "Reykjavik", "Akureyri"};
        JComboBox<String> comboIceland = new JComboBox<>(itemsIceland);
        //c.ipady = 40; 
        c2.gridwidth = 3;
        c2.weightx = 0.0;
        c2.gridx = 0;
        c2.gridy = 5;
        panel2.add(comboIceland, c2);
        
        
        //DropDown menu for arrival location
        String[] itemsBudget2 = new String[]{"choose budget", "€1000", "€2000"};
        JComboBox<String> comboBudget2 = new JComboBox<>(itemsBudget2);
        //c.ipady = 40; 
        c2.gridwidth = 3;
        c2.weightx = 0.0;
        c2.gridx = 0;
        c2.gridy = 6;
        panel2.add(comboBudget2, c2);

        
        //search button
        JButton searchButton2 = new JButton("Search");
        c2.ipady = 0;       //reset to default
        c2.weighty = 1.0;   //request any extra vertical space
        c2.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c2.insets = new Insets(10,0,0,0);  //top padding
        c2.gridx = 1;       //aligned with button 2
        c2.gridwidth = 2;   //2 columns wide
        c2.gridy = 7;       //third row
        panel2.add(searchButton2, c2);
        
        
        
        //
        //
        //
        //panel3 one-click trip
        panel3.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.HORIZONTAL;
        
    /*    
        JButton button = new JButton("Long-Named Button 4");
       // c.ipady = 40;      //make this component tall
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        panel2.add(button, c2);
*/
       

        //Label From: 
        JLabel labelFrom3 = new JLabel("   From: ");
        c3.weightx = 0.5;
        c3.gridx = 0;
        c3.gridy = 0;
        panel3.add(labelFrom3, c3);

        //Label To: 
        JLabel labelTo3 = new JLabel("   To: ");
        c3.gridx = 1;
        c3.gridy = 0;
        panel3.add(labelTo3, c3);
        
        //DropDown menu for home location
        String[] itemsFrom3 = new String[]{"Choose!", "London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
        JComboBox<String> comboFrom3 = new JComboBox<>(itemsFrom3);
        c3.weightx = 0.5;
        c3.gridx = 0;
        c3.gridy = 1;
        panel3.add(comboFrom3, c3);

        //DropDown menu for arrival location
        String[] itemsTo3 = new String[]{"Choose!", "Reykjavik", "Akureyri"};
        JComboBox<String> comboTo3 = new JComboBox<>(itemsTo3);
        c3.gridx = 1;
        c3.gridy = 1;
        panel3.add(comboTo3, c3);
       
      	//DropDown menu for arrival location
        String[] itemsBudget3 = new String[]{"choose hotel", "Hotel Cabin", "Hotel 101", "Hotel Saga",};
        JComboBox<String> comboBudget3 = new JComboBox<>(itemsBudget3);
        //c.ipady = 40; 
        c3.gridwidth = 3;
        c3.weightx = 0.0;
        c3.gridx = 0;
        c3.gridy = 5;
        panel3.add(comboBudget3, c3);
        
      //button for departure date, date picker 
        JButton departureDate3 = new JButton();
        departureDate3.setText("Departure Date");
        final JTextField textDeparture3 = new JTextField(20);
        departureDate3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
            	textDeparture3.setText(new DatePicker(tabbedPane).setPickedDate());
            }
        });
        c3.gridwidth = 1;
        c3.weightx = 0.5;
        c3.gridx = 0;
        c3.gridy = 4;
        panel3.add(textDeparture3, c3);
        c3.gridx = 0;
        c3.gridy = 3;
        panel3.add(departureDate3, c3);
        
        //button for return date, date picker 
        JButton returnDate3 = new JButton();
        returnDate3.setText("Return Date");
        final JTextField textReturn3 = new JTextField(20);
        returnDate3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	textReturn3.setText(new DatePicker(tabbedPane).setPickedDate());
            }
        });
        c3.gridwidth = 1;
        c3.gridx = 1;
        c3.gridy = 4;
        panel3.add(textReturn3, c3);
        
        c3.gridx = 1;
        c3.gridy = 3;
        panel3.add(returnDate3, c3);
        
        //search button
        JButton searchButton3 = new JButton("Search");
        c3.ipady = 0;       //reset to default
        c3.weighty = 1.0;   //request any extra vertical space
        c3.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c3.insets = new Insets(10,0,0,0);  //top padding
        c3.gridx = 1;       //aligned with button 2
        c3.gridwidth = 2;   //2 columns wide
        c3.gridy = 6;       //third row
        panel3.add(searchButton3, c3);
        
        
        
        
        
        
	}
     
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
     
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Add content to the window.
        frame.add(new TabbedPaneDemo(), BorderLayout.CENTER);
         
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




 
class DatePicker {
  int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
  int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
  JLabel l = new JLabel("", JLabel.CENTER);
  String day = "";
  JDialog d;
  JButton[] button = new JButton[49];
 
  public DatePicker(JTabbedPane tabbedPane) {
    d = new JDialog();
    d.setModal(true);
    String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
    JPanel p1 = new JPanel(new GridLayout(7, 7));
    p1.setPreferredSize(new Dimension(430, 120));
 
    for (int x = 0; x < button.length; x++) {
      final int selection = x;
      button[x] = new JButton();
      button[x].setFocusPainted(false);
      button[x].setBackground(Color.white);
      if (x > 6) {
        button[x].addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            day = button[selection].getActionCommand();
            d.dispose();
          }
        });
      }
      if (x < 7) {
        button[x].setText(header[x]);
        button[x].setForeground(Color.red);
      }
      p1.add(button[x]);
    }
    JPanel p2 = new JPanel(new GridLayout(1, 3));
     
    // Previous month button
    JButton previous = new JButton("<< Previous");
    previous.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month--;
        displayDate();
      }
    });
    p2.add(previous);
     
    // Current month label between the previous and next buttons
    p2.add(l);
     
    // Next month button
    JButton next = new JButton("Next >>");
    next.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        month++;
        displayDate();
      }
    });
    p2.add(next);
     
    d.add(p1, BorderLayout.CENTER);
    d.add(p2, BorderLayout.SOUTH);
    d.pack();
    d.setLocationRelativeTo(tabbedPane);
    displayDate();
    d.setVisible(true);
  }
 
  public void displayDate() {
    for (int x = 7; x < button.length; x++) {
      button[x].setText("");
    }
     
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM yyyy");
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, 1);
    int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
    int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
 
    for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
      button[x].setText("" + day);
    }
   
    l.setText(sdf.format(cal.getTime()));
    d.setTitle("Date Picker");
  }
 
  public String setPickedDate() {
    if (day.equals("")) {
      return day;
    }
   
    // Set the return date format
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
         
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.set(year, month, Integer.parseInt(day));
    return sdf.format(cal.getTime());
  }
}

