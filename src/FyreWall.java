import javax.swing.JFrame;


public class FyreWall extends JFrame {
    /**
	 * Driver function for the game
	 */
	
	private static final long serialVersionUID = 1L;

	public FyreWall() {
        setTitle("FyreWall");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(true);
        
        // Add the game panel
        Level gamePanel = new Level();
        add(gamePanel);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new FyreWall();
    }
}

