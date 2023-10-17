import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;




public class Menu extends JFrame{
	
    private static final long serialVersionUID = 1L;
	private boolean isGrid; // Sets if the layout of the menu is a Grid Or Not.
	private int newMenuItems; // Sets how many buttons will be made
	private String[] Labels; // An Array of Labels used for the setMenuItems Method
	private JFrame menu; 
	private boolean isVisible;
    private String lastButtonPressed;
    private ButtonPressListener buttonPressListener;

	
	
    public Menu(ButtonPressListener buttonPressListener) {
        this.buttonPressListener = buttonPressListener;
		menu = new JFrame("menu");
		menu.pack();
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		menu.setLocationRelativeTo(null); 
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
        menu.setTitle("FyreWall");   
	  }
	    //
     
//----------------------------		
	
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
	
//	public void readLabelsArray() //Test function remove on FINAL submission
//	{
//		System.out.println("Label Function");
//		for(int i = 0; i < Labels.length; i++)
//		{
//			System.out.println(Labels[i]);
//		}
//	}
//----------------------------
	public void setIsVisible(boolean isVisible)
	{
		this.isVisible = isVisible;
	}
	
	public boolean getIsVisible()
	{
		return isVisible;
	}
//----------------------------

//----------------------------	
	public void  setButtonPressed(String lastButtonPressed){
		this.lastButtonPressed = lastButtonPressed;
			
	}
	public String getButtonPressed(){
			return lastButtonPressed;
	}
	
	private class ButtonClickListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        JButton clickedButton = (JButton) e.getSource();
	        String buttonText = clickedButton.getText();
	        setButtonPressed(buttonText);

	        // Notify the driver or other listeners
	        if (buttonPressListener != null) {
	            buttonPressListener.onButtonPressed(buttonText);
	        }
	    }
	}
//----------------------------	
	public interface ButtonPressListener {
	    void onButtonPressed(String buttonLabel);
	}
	
	public void onButtonPressed(String buttonLabel) {
        // Default implementation (can be overridden by subclasses)
    }
}

