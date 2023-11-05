import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FyreWall extends JFrame {
    private static final long serialVersionUID = 1L;
    private LevelSelect levelSelect;
    private PointShop pointShop;

    public FyreWall() {
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
        
        // Initialize menu
        levelSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                levelSelect = new LevelSelect();
                dispose(); // Dispose the frame
                

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
    }
    public static void main(String[] args) {
        new FyreWall();
    }
}
