import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class Menu extends JFrame{
	private boolean isGrid;
	private JFrame menu; 
	public Menu()
	{
		isGrid = false;
		menu = new JFrame("Menu");
		int width;
	    int height;
		menu.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
		menu.setSize(width, height);
		menu.setLocationRelativeTo(null); 
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);
        menu.setTitle("FyreWall");
	}

	
	
	
	
}