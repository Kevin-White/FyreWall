import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class Menu extends JFrame{
	private boolean isGrid; // Sets if the layout of the menu is a Grid Or Not.
	private int newMenuItems; // Sets how many buttons will be made
	private String[] Labels; // An Array of Labels used for the setMenuItems Method
	private JFrame menu; 
	private JButton buttons; // The buttons being added to the menu itself
	
	
	public Menu()
	{
	    //menu.setDefaultLookAndFeelDecorated(true);
		menu = new JFrame("menu");
		int width;
	    int height;
		menu.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();        
		menu.setSize(width, height);
		menu.setLocationRelativeTo(null); 
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
        menu.setTitle("FyreWall");   
        menu.getContentPane().removeAll();      
	    menu.revalidate(); // Refresh the GUI
	    menu.repaint();
	    //
     }

//----------------------------	
    public int getMenuItems(){
        return newMenuItems;
    }
  	
    public void setMenuItems(int newMenuItems){ // this sets how many menu items exist that can be clicked on as well as updates the UI with new buttons and labels
        this.newMenuItems = newMenuItems;
        menu.getContentPane().removeAll();
        JButton[] buttons = new JButton[newMenuItems];
        if(isGrid){
            //menu.setLayout(new BoxLayout(menu.getContentPane(), BoxLayout.Y_AXIS));
        }
        else{
        	menu.setLayout(new FlowLayout(FlowLayout.CENTER));
        }
        
        for (int i = 0; i < newMenuItems; i++) {
            buttons[i] = new JButton(Labels[i]);
            buttons[i].addActionListener(new ButtonClickListener());
            menu.add(buttons[i]);
            menu.revalidate(); // Refresh the GUI
            menu.repaint();
        }
    }
//----------------------------
    public void setIsGrid(boolean setIsGrid) {
        this.isGrid = setIsGrid;
    }
    
    public boolean getIsGrid(){
    	return isGrid;
    }
//----------------------------
	public void setButtonLabels(String[] Labels){
		this.Labels = Labels;
	}
	
	public void readLabelsArray() //Test function remove on FINAL submission
	{
		System.out.println("Label Function");
		for(int i = 0; i < Labels.length; i++)
		{
			System.out.println(Labels[i]);
		}
	}
//----------------------------
private class ButtonClickListener implements ActionListener{
	 public void actionPerformed(ActionEvent e) {
         JButton clickedButton = (JButton) e.getSource();
         String buttonText = clickedButton.getText();
         JOptionPane.showMessageDialog(menu, "Button Clicked: " + buttonText);
     }
}

//----------------------------


}