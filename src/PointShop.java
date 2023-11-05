import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JButton backButton = new JButton("Back");
        JTextField  pointsLabel = new JTextField ("Points: " + points.getTotal());
        // Create a new JPanel for the header
        JPanel headerPanel = new JPanel();

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
                dispose();
            }
        });

        pointsLabel.setEditable(false);
        pointHeader.add(backButton);
        pointHeader.add(pointsLabel);
        // pointsHeader gets a transparent background
        headerPanel.setOpaque(false);
        headerPanel.add(pointHeader);
     // Set the bounds of the headerPanel to span the width of the screen and a height of your choosing
        headerPanel.setBounds(0, 0, screenSize.width, 100); // Set height to your preferred value

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

        
    	
    	JLabel imageLabel = new JLabel();
         try {
             ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("playerSkins/" + imageName)));
             imageLabel.setIcon(imageIcon);
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
                        dispose();
        	            
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
             BorderFactory.createEmptyBorder(0, 10, 0, 10) // top, left, bottom, right padding
         ));

         // Add the image and the buy button to the new panel
         itemPanel.add(imageLabel);
         itemPanel.add(cost);
         itemPanel.add(buyButton);

         return itemPanel;
    }
    

}

