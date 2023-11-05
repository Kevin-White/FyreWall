import javax.swing.JFrame;

<<<<<<< Updated upstream
public class FyreWall extends JFrame implements Menu.ButtonPressListener {
=======
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> Stashed changes

    private Menu menu = new Menu(this);
    private LevelMenu Levels = new LevelMenu();
    // Menu item declarations
    
    String[] Labels = {"Play" ,"Shop", "Exit"};
    String[] levelSelect = {"Tutorial" ,"1", "2", "3", "4", "5", "6", "7", "Back"};
    // Menu label arrays
    
    private static final long serialVersionUID = 1L;
    public FyreWall() {
<<<<<<< Updated upstream
=======
        JPanel menuPanel = new JPanel(new GridBagLayout()); // Set layout to GridBagLayout    
        GridBagConstraints gbc = new GridBagConstraints(); // Create a GridBagConstraints object


        JButton levelSelectButton = new JButton("Level Select");
        JButton pointShopButton = new JButton("Point Shop");
        JButton exitButton = new JButton("Exit");
        
        ImageIcon background =  new ImageIcon("menuImages/background.png");
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenSize)); // Set to your preferred size
        layeredPane.setLayout(null);
    	
        
        Image img = background.getImage() ;  
        Image newimg = img.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH ) ; 
        background = new ImageIcon(newimg);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        
>>>>>>> Stashed changes
        // Initialize menu
        menu.setButtonLabels(Labels);
        menu.setIsGrid(false);
        menu.setMenuItems(2); 
        // Initialize game panel
        // initGamePanel();
        menu.setMenuItems(3); 
    }

<<<<<<< Updated upstream
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
   
       
=======
            }
        });
        
        pointShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointShop = new PointShop();
                dispose(); // Dispose the frame
                

            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        gbc.gridx = 0; // Set gridx to 0 for all buttons
        gbc.gridy = GridBagConstraints.RELATIVE; // Each component's gridy is one more than previous
        gbc.anchor = GridBagConstraints.CENTER; // Center component in the cell
        gbc.insets = new Insets(10, 0, 10, 0); // Add some space between the buttons

        menuPanel.add(levelSelectButton, gbc); // Add gbc as a parameter
        menuPanel.add(pointShopButton, gbc); // Add gbc as a parameter
        menuPanel.add(exitButton, gbc); // Add gbc as a parameter
        
        menuPanel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size

        menuPanel.setOpaque(false); // Make menuPanel transparent

     // Add the backgroundLabel to the bottom layer of the layeredPane
     layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

     // Add the menuPanel to the top layer of the layeredPane
     layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
>>>>>>> Stashed changes
    }

    public static void main(String[] args) {
        new FyreWall();
    }
}

