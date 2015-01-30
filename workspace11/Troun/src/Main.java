import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
 
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
 
public class Main {
  public static void main(String[] args) {
	//prófa	
	final JFrame f = new JFrame();
	   
	JPanel p = new JPanel();
//	p.setLayout(null);
	
	
	//Create the radio buttons.
    JRadioButton birdButton = new JRadioButton("destination trip");
    birdButton.setMnemonic(KeyEvent.VK_B);
   // birdButton.setSize(150, 50);
   // birdButton.setLocation(100, 100);
   // birdButton.setActionCommand(birdString);
    birdButton.setSelected(true);

    JRadioButton catButton = new JRadioButton("stopover trip");
    catButton.setMnemonic(KeyEvent.VK_C);
  //  catButton.setActionCommand(catString);

    JRadioButton dogButton = new JRadioButton("one-click hotel + flight");
    dogButton.setMnemonic(KeyEvent.VK_D);
 //   dogButton.setActionCommand(dogString);

    //Group the radio buttons.
    ButtonGroup group = new ButtonGroup();
    group.add(birdButton);
    group.add(catButton);
    group.add(dogButton);
    
    p.add(birdButton);
    p.add(catButton);
    p.add(dogButton);
    
    //Register a listener for the radio buttons.
   
	
	
	
	
	//JLabel label3 = new JLabel("Type of triphhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
	//p.add(label3);
	   
	JLabel label = new JLabel("Departure Date:");
    final JTextField text = new JTextField(20);
    JButton b = new JButton("popup");
    
    
    
    label.setBounds(new Rectangle(new Point(200, 300)));
    
//    label.setBounds(0, 0, 200, 200);
    label.setLocation(200,200);
   
    p.setBackground(Color.cyan);
    p.add(label);
 
    
    
    p.add(label);
    p.add(text);
    p.add(b);
    //  f.setSize( 1024, 768 );
  //    System.out.print(f.getSize());
//      f.getContentPane().add(p);
      
    
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        text.setText(new DatePicker(f).setPickedDate());
      }
    });
    
    
    JLabel label2 = new JLabel("Return Date:");
    label2.setBounds(100,100,200,200);
    
    final JTextField text2 = new JTextField(20);
    JButton b2 = new JButton("popup");
    JPanel p2 = new JPanel();
    p.add(label2);
    p.add(text2);
    p.add(b2);
   // final JFrame f2 = new JFrame();
    f.getContentPane().add(p2);
  //  f.pack();
 //   f.setVisible(true);
    b2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        text2.setText(new DatePicker(f).setPickedDate());
      }
    });
    
    
 //   f.setLocationByPlatform(true);
 //   label2.setLocation(200, 150);
    
   
    
    f.getContentPane().add(p);
 //   f.getContentPane().setSize(800,1000);
    
    f.setPreferredSize(new Dimension(950, 1000));
    
    f.pack();
    f.setVisible(true);
    
    
  }
}