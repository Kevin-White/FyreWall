import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FyreWall extends JFrame {
    private static final long serialVersionUID = 1L;
    private LevelSelect levelSelect;
    private PointShop pointShop;

    public FyreWall() {
        JPanel menuPanel = new JPanel();
        JButton levelSelectButton = new JButton("Level Select");
        JButton pointShopButton = new JButton("Point Shop");
        JButton exitButton = new JButton("Exit");
    	
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

        menuPanel.add(levelSelectButton);
        menuPanel.add(pointShopButton);
        menuPanel.add(exitButton);
        add(menuPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new FyreWall();
    }
}

