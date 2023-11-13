import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.util.List;



class PointShop extends JFrame {
    private static final long serialVersionUID = 1L;
    private FyreWall fyreWall;
    private Points points = new Points();

    public PointShop() {
        setLayout(new BorderLayout());
        JPanel pointPanel = new JPanel(new GridBagLayout());
        JPanel pointHeader = new JPanel();
        JPanel zombieSkin = new JPanel();
        JPanel ghostSkin = new JPanel();
        JPanel defaultSkin = new JPanel();
        JPanel sirPlatanoSkin = new JPanel();
        JPanel mrSpicySkin = new JPanel();
        JButton backButton = new JButton("<-");
        JTextField  pointsLabel = new JTextField (" Points: " + points.getTotal() + " ");
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Create a panel for the back button with a FlowLayout


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

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fyreWall = new FyreWall();
                fyreWall.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        dispose(); // Dispose the current JFrame here
                    }
                });
            }
        });

        backButton = backButtonStyle(backButton);
        
        backButtonPanel.add(backButton); // Add backButton to the backButtonPanel
        backButtonPanel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        backButtonPanel.setOpaque(false); // Make backButtonPanel transparent

        pointsLabel.setEditable(false);
        pointsLabel.setBackground(new Color(255, 255, 255, 50)); // RGBA values, A for alpha (transparency)
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Change the font size to your preference
        pointsLabel.setForeground(new Color(255, 255, 255));
        pointsLabel.setBorder(null); // Remove the border

        headerPanel.add(pointsLabel);
        headerPanel.setBounds(screenSize.width / 2 - 75, 0, 150, 150); // Set height to your preferred value
        headerPanel.setOpaque(false);



        defaultSkin = itemPanel(0 , "Default.png");
        zombieSkin = itemPanel(100 , "Zombie.png");
        ghostSkin = itemPanel(500 , "Ghost.png");
        sirPlatanoSkin = itemPanel(1000, "sirPlatano.png");
        mrSpicySkin = itemPanel(2000, "mrSpicy.png");

        pointPanel.add(defaultSkin);
        pointPanel.add(zombieSkin);
        pointPanel.add(ghostSkin);
        pointPanel.add(sirPlatanoSkin);
        pointPanel.add(mrSpicySkin);

        pointPanel.setBounds(0, 0, screenSize.width, screenSize.height); // Set bounds to match the layeredPane size
        pointPanel.setOpaque(false); // Make menuPanel transparent

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(backButtonPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(pointPanel, JLayeredPane.PALETTE_LAYER);

        add(layeredPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
    }
    
    private JPanel itemPanel(int itemCost, String imageName) {
        JPanel itemPanel = new JPanel();
        JButton buyButton = new JButton("Buy");
        JTextField cost;
        List<String> lockedSkins;
        final int itemCostLocal;

        buyButton = mainButtonStyle(buyButton);
    	
    	JLabel imageLabel = new JLabel();
         try {
             ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("playerSkins/" + imageName)));
             imageLabel.setIcon(imageIcon);
             imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Add this line
         } catch (IOException e) {
             e.printStackTrace();
         }

      // Check if imageName is in "playerSkins/locked.txt"
         try {
             lockedSkins = Files.readAllLines(Paths.get("playerSkins/locked.txt"));
             if (lockedSkins.contains(imageName)) {
            	 itemCostLocal = itemCost;
                 cost = new JTextField("" + itemCostLocal);
                 cost.setEditable(false);
                 if(itemCostLocal <= points.getTotal()) {
                	cost.setBackground(Color.green);
                 }else {
                 	cost.setBackground(Color.red);
                 }
             }else {
            	 cost = new JTextField("Owned");
            	 buyButton.setText("Use");
                 cost.setEditable(false);
                 itemCostLocal = 0;
             }
         } catch (IOException e) {
             e.printStackTrace();
             return null;
         }
         
         buyButton.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        if(itemCostLocal <= 0) {        	        	
        	        	 // Delete the existing "currentSkin.pdf" file
        	            try {
        	                Files.deleteIfExists(Paths.get("playerSkins/currentSkin.png"));
        	            } catch (IOException ex) {
        	                ex.printStackTrace();
        	            }

        	            // Copy the new skin file to "currentSkin.pdf"
        	            try {
        	                Files.copy(Paths.get("playerSkins/" + imageName), Paths.get("playerSkins/currentSkin.png"), StandardCopyOption.REPLACE_EXISTING);
        	            } catch (IOException ex) {
        	                ex.printStackTrace();
        	            }        	        
        	        } else if( 0 < itemCostLocal && itemCostLocal <= points.getTotal()){

        	            points.spendTotal(itemCostLocal);
        	            // Remove the item from the locked skins list
        	            lockedSkins.remove(imageName);
        	            // Write the updated list back to the file
        	            try {
        	                Files.write(Paths.get("playerSkins/locked.txt"), lockedSkins);
        	            } catch (IOException ex) {
        	                ex.printStackTrace();
        	            }
        	            
        	            PointShop pointshop = new PointShop();
        	            pointshop.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                super.windowClosed(e);
                                dispose(); // Dispose the current JFrame here
                            }
                        });
        	            
        	        } else {
        	        	// do nothing
        	        }
        	    }
        	});

         // Create a new JPanel to hold the image and the buy button
         itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.PAGE_AXIS));
         
         // Add padding to the left and right of the image and button
         itemPanel.setBorder(BorderFactory.createCompoundBorder(
             BorderFactory.createLineBorder(Color.BLACK), 
             BorderFactory.createEmptyBorder(10, 10, 10, 10) // top, left, bottom, right padding
         ));
         itemPanel.setBackground(new Color(255, 255, 255, 50));
         // Add the image and the buy button to the new panel
         itemPanel.add(imageLabel);
         itemPanel.add(cost);
         itemPanel.add(buyButton);

         return itemPanel;
    }
    
    private JButton backButtonStyle (JButton button){
    	ImageIcon buttonIcon = new ImageIcon("menuImages/backButton.png"); // Load the image
        Image img = buttonIcon.getImage() ;  
        Image newimg = img.getScaledInstance(72, 72,  java.awt.Image.SCALE_SMOOTH ) ;  
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
    
    private JButton mainButtonStyle(JButton button){
    	ImageIcon buttonIcon = new ImageIcon("menuImages/mainButton.png"); // Load the image
        Image img = buttonIcon.getImage() ;  
        Image newimg = img.getScaledInstance(100, 36,  java.awt.Image.SCALE_SMOOTH ) ;  
        buttonIcon = new ImageIcon(newimg);
        
        Font pressStart2P;
		try {
			pressStart2P = Font.createFont(Font.TRUETYPE_FONT, new File("menuImages/PressStart2P.ttf"));
	        pressStart2P = pressStart2P.deriveFont(10f);

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

}

