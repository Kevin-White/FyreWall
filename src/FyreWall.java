import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.*;


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
        
        ImageIcon logo = new ImageIcon("menuImages/logo.png");

        JLabel logoLabel;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(screenSize)); // Set to your preferred size
        layeredPane.setLayout(null);
        
        Image img = background.getImage() ;  
        Image newimg = img.getScaledInstance(screenSize.width, screenSize.height,  java.awt.Image.SCALE_SMOOTH ) ; 
        background = new ImageIcon(newimg);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        
        img = logo.getImage() ;  
        newimg = img.getScaledInstance(800, 300,  java.awt.Image.SCALE_SMOOTH ) ; 
        logo = new ImageIcon(newimg);
        logoLabel = new JLabel(logo);
        logoLabel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        
        // Initialize menu
        levelSelectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	levelSelect = new LevelSelect();
                levelSelect.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        dispose(); // Dispose the current JFrame here
                    }
                });
            }
        });
        
        pointShopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pointShop = new PointShop();
                pointShop.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        dispose(); // Dispose the current JFrame here
                    }
                });                
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        logoLabel.setBounds(screenSize.width/2 - logo.getIconWidth()/2, 100, logo.getIconWidth(), logo.getIconHeight());
        levelSelectButton = mainButtonStyle(levelSelectButton);
        pointShopButton = mainButtonStyle(pointShopButton);
        exitButton = mainButtonStyle(exitButton);
        
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
     
     layeredPane.add(logoLabel, JLayeredPane.PALETTE_LAYER);

     // Add the menuPanel to the top layer of the layeredPane
     layeredPane.add(menuPanel, JLayeredPane.PALETTE_LAYER);


        add(layeredPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
    }
    
    private JButton mainButtonStyle(JButton button){
    	ImageIcon buttonIcon = new ImageIcon("menuImages/mainButton.png"); // Load the image
        Image img = buttonIcon.getImage() ;  
        Image newimg = img.getScaledInstance(200, 72,  java.awt.Image.SCALE_SMOOTH ) ;  
        buttonIcon = new ImageIcon(newimg);
        
        Font pressStart2P;
		try {
			pressStart2P = Font.createFont(Font.TRUETYPE_FONT, new File("menuImages/PressStart2P.ttf"));
	        pressStart2P = pressStart2P.deriveFont(14f);

		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			pressStart2P = new Font("Dialog", Font.PLAIN, 12);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			pressStart2P = new Font("Dialog", Font.PLAIN, 12);
		}
    	
    	button.setIcon(buttonIcon); // Set the button icon
    	button.setHorizontalTextPosition(JButton.CENTER); // Center the text horizontally
    	button.setVerticalTextPosition(JButton.CENTER); // Center the text vertically
    	button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
    	button.setContentAreaFilled(false); // Make the button background transparent
    	button.setBorderPainted(false);
    	button.setFont(pressStart2P);
    	
    	return button;
    }
    
    public static void main(String[] args) {
        new FyreWall();
    }
}
