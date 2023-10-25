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
        JPanel zombieSkin = new JPanel();
        JButton backButton = new JButton("Back");
        JTextField  pointsLabel = new JTextField ("Points");
        JButton buyZombieButton = new JButton("Buy");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fyreWall = new FyreWall();
                dispose();
            }
        });

        buyZombieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add your action for the buy button here
            }
        });
        
        pointsLabel.setEditable(false);

        JLabel zombieLabel = new JLabel();
        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("playerSkins/Zombie.png")));
            zombieLabel.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new JPanel to hold the image and the buy button
        zombieSkin.setLayout(new BoxLayout(zombieSkin, BoxLayout.PAGE_AXIS));
        
        // Add padding to the left and right of the image and button
        zombieSkin.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK), 
            BorderFactory.createEmptyBorder(0, 10, 0, 10) // top, left, bottom, right padding
        ));

        // Add the image and the buy button to the new panel
        zombieSkin.add(zombieLabel);
        zombieSkin.add(buyZombieButton);

        // Add the new panel to the main panel
        pointPanel.add(zombieSkin);

        add(backButton, BorderLayout.NORTH);
        add(pointsLabel);
        add(pointPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
    }
}
