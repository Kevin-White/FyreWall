import javax.swing.JFrame;

public class LevelMenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ButtonPressed;
   
	void setButtonPressed(String pressed)
	{
		this.ButtonPressed = pressed;
		int level;
		if(ButtonPressed == "Tutorial"){
			level = 0;
		}
		else {
		level = Integer.parseInt(ButtonPressed);
		}
		// Gets level pressed from Main, if tutorial sets level to 0.
		playLevel(level);
	
	}
	
	void playLevel(int level) {
		System.out.println(level + " was pressed ");
		// Debugging Print
		switch(level) {
		case 0:
		    System.out.println("Tutorial");
		    // Does stuff to play the tutorial level
			break;
		case 1:
		    System.out.println("Level 1");
		    // Does stuff to play Level 1
			break;
		}
		// You get the idea, will fill out once I can get these two to work. 
		
	}
	


    
}
