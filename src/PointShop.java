import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;

class PointShop extends JFrame {
    private static final long serialVersionUID = 1L;
    private FyreWall fyreWall;

    public PointShop() {
        setLayout(new BorderLayout());
        JPanel pointPanel = new JPanel(new GridBagLayout());
        JPanel pointHeader = new JPanel();
        JPanel zombieSkin = new JPanel();
        JPanel ghostSkin = new JPanel();
        JPanel defaultSkin = new JPanel();
        JPanel pineappleSkin = new JPanel();
        JButton backButton = new JButton("Back");
        JTextField  pointsLabel = new JTextField ("Points");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fyreWall = new FyreWall();
                dispose();
            }
        });

        
        
        pointsLabel.setEditable(false);
        pointHeader.add(backButton);
        pointHeader.add(pointsLabel);

        // Add the image and the buy button to the new panel
        defaultSkin = itemPanel("Default", "Default.png");
        zombieSkin = itemPanel("Zombie", "Zombie.png");
        ghostSkin = itemPanel("Ghost", "Ghost.png");
        pineappleSkin = itemPanel("Pineapple", "Pineapple.png");

        
        // Add the new panel to the main panel
        pointPanel.add(defaultSkin);
        pointPanel.add(zombieSkin);
        pointPanel.add(ghostSkin);
        pointPanel.add(pineappleSkin);

        
        add(pointHeader, BorderLayout.NORTH);
        add(pointPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
    }
    
    private JPanel itemPanel(String itemName, String imageName) {
        JPanel itemPanel = new JPanel();
        JButton buyButton = new JButton("Buy");

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("You bought: " + itemName);
            }
        });
    	
    	JLabel imageLabel = new JLabel();
         try {
             ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("playerSkins/" + imageName)));
             imageLabel.setIcon(imageIcon);
         } catch (IOException e) {
             e.printStackTrace();
         }

         // Create a new JPanel to hold the image and the buy button
         itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.PAGE_AXIS));
         
         // Add padding to the left and right of the image and button
         itemPanel.setBorder(BorderFactory.createCompoundBorder(
             BorderFactory.createLineBorder(Color.BLACK), 
             BorderFactory.createEmptyBorder(0, 10, 0, 10) // top, left, bottom, right padding
         ));

         // Add the image and the buy button to the new panel
         itemPanel.add(imageLabel);
         itemPanel.add(buyButton);

         return itemPanel;
    }
    

}

