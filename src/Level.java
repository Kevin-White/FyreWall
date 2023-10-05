import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import java.awt.*;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Level extends JPanel implements ActionListener {
	private int originalPlayerX, originalPlayerY;
	private Map mapOne, mapTwo;
	
    private static final long serialVersionUID = 1L;
    private Player player;
    private Map currentMap; // a Map object to store and draw the current game map
    private Timer animationTimer;
    private float alpha = 0f;
    private Wall wall;
    private TextPrompt prompt[];
    private int promptSize = 0;


    public Level(int x, int y, Map mapOne, Map mapTwo) {
    	levelSetup(x, y);
    	originalPlayerX = x;
    	originalPlayerY = y;
    	
    	this.mapOne = mapOne;
    	this.mapTwo = mapTwo;
    	
    	// Initialize the map with a map data
        this.currentMap = mapOne;
        player.setMap(mapOne, mapTwo);        
    }
    
    public Level(int x, int y, Map mapOne) {
    	levelSetup(x, y);
    	originalPlayerX = x;
    	originalPlayerY = y;
    	
    	// Initialize the map with a map data
        this.currentMap = mapOne;
        player.setMap(mapOne);        
    }
    
    public void addPrompt(String text, int x, int y) {
    	TextPrompt[] temp;
    	if (prompt != null) {
    		temp = Arrays.copyOf(prompt, prompt.length);
    		prompt = new TextPrompt[promptSize + 1]; 
        	for(int i = 0; i < promptSize; i++) {
        		prompt[i] = temp[i];
        	}
        	prompt[promptSize] = new TextPrompt(text, x, y);
    	}else {
    		prompt = new TextPrompt[promptSize + 1];
    		prompt[0] = new TextPrompt(text, x, y);
    	}
    	promptSize++;
    }
    
    private void levelSetup(int x, int y) {
    	player = new Player(x, y);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.keyReleased(e.getKeyCode());
            }
        });

        setFocusable(true);
        setDoubleBuffered(true);
        Timer timer = new Timer(1000 / 60, this); // 60 FPS
        timer.start();
        
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha -= 0.1f;
                if (alpha <= 0) {
                    alpha = 0;
                    ((Timer)e.getSource()).stop();
                }
                repaint();
            }
        });
        
        wall = new Wall(-5500, -2500);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update(){
        player.update();
        if(player.getCurrentMap() != currentMap) {
        	currentMap = player.getCurrentMap();
        	alpha = 1f;
        	animationTimer.start();
        }
        wall.update();
        if (player.getWon()){
            // Stop the game timer
            animationTimer.stop();

            // Show a message dialog
            JOptionPane.showMessageDialog(null, "Congratulations, you finished the Tutorial!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

            // Exit the game
            System.exit(0);
        }
        if (wall.getX() + wall.getWidth() > player.getX()) {
            // Stop the game timer
            animationTimer.stop();

            // Show a dialog with options to exit or restart
            Object[] options = {"Exit", "Restart"};
            int n = JOptionPane.showOptionDialog(null,
                "The wall has passed you. Would you like to exit or restart?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

            if (n == JOptionPane.YES_OPTION) {
                // Exit the game
                System.exit(0);
            } else if (n == JOptionPane.NO_OPTION) {
                // Restart the level
                restartLevel();
            }
        }
    }

    
    private void restartLevel() {
        // Recreate the player at the original position
        player = new Player(originalPlayerX, originalPlayerY);
        player.setMap(mapOne, mapTwo);
        
        // Recreate the wall at the original position
        wall = new Wall(-5500, -2500);

        // Restart the game timer
        animationTimer.start();
        
        repaint();

    }

    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the map with the offsets
        g.translate( - player.getX() + getWidth()/2  , - player.getY() + getHeight()/2);
        
        currentMap.draw(g, 0, 0);
       
        //Draw Text Prompts for the user
        g.setFont(new Font("default", Font.BOLD, 20));
        for(int i = 0; i < promptSize; i++) {
        	g.drawString(prompt[i].getText(), prompt[i].getX(), prompt[i].getY());
        }
        
        // Draw the player at its actual position
        player.draw(g);
       
        wall.draw(g);
 
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, alpha));
        g2d.fillRect(player.getX() - getWidth()/2, player.getY() - getHeight()/2 , getWidth(), getHeight());
        g2d.dispose();
    }


}
