import javax.swing.JFrame;

public class FyreWall extends JFrame implements Menu.ButtonPressListener {

    private Menu menu = new Menu(this);
    private LevelMenu Levels = new LevelMenu();
    // Menu item declarations
    
    String[] Labels = {"Play" ,"Shop", "Exit"};
    String[] levelSelect = {"Tutorial" ,"1", "2", "3", "4", "5", "6", "7", "Back"};
    // Menu label arrays
    
    private static final long serialVersionUID = 1L;
    public FyreWall() {
        // Initialize menu
        menu.setButtonLabels(Labels);
        menu.setIsGrid(false);
        menu.setMenuItems(2); 
        // Initialize game panel
        // initGamePanel();
        menu.setMenuItems(3); 
    }

    @Override
    public void onButtonPressed(String buttonLabel) {
        if (buttonLabel.equals("Play")) {
            Menu levels = new Menu(this);
            levels.setButtonLabels(levelSelect);
            levels.setIsGrid(false);
            levels.setMenuItems(8); 
            setUndecorated(true);
        	setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else if (buttonLabel.equals("Shop")) {
            // Handle shop functionality
        } else if (buttonLabel.equals("Exit")) {
            // Handle exit functionality
            System.exit(0);
        } else if (buttonLabel.equals("Back")) {
            // Return to the main menu
        	// WORK IN PROGRESS
        }
        for (String s : levelSelect) {
        	if (buttonLabel.equals(s)) {
        		Levels.setButtonPressed(s);
        		// Send what level was chosen to the Level Menu.java to launch that level
        	}
        }
   
       
    }

    public static void main(String[] args) {
        new FyreWall();
    }
}

