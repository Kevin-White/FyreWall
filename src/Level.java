import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Level class represents a game level. It extends JPanel for GUI rendering and implements ActionListener for event handling.
 * This class is responsible for setting up the game level, updating game state, and rendering the game view.
 */
public class Level extends JPanel implements ActionListener {
	private int originalPlayerX, originalPlayerY;    // Original position of the player
	private Map mapOne, mapTwo;    // Two Map objects that can be used in the level
    private static final long serialVersionUID = 1L;    // A unique serial version identifier for this class in the context of serialization
    private Player player;    // Player object representing the player in the level
    private Map currentMap;    // A Map object to store and draw the current game map
    private Timer animationTimer;    // Timer object for creating animation
    private float alpha = 0f;    // Alpha value for creating fading effects
    private Wall wall;    // Wall object representing a moving wall in the level
    private TextPrompt prompt[];    // Array of TextPrompt objects representing prompts in the level
    private int promptSize = 0;    // Size of the prompt array
    private LevelSelect levelSelect;	//Holds the menu so you can exit the game
    private Timer timer;
    private String levelName;
    private boolean[] keys; // An array to track key presses

    
    private BufferedImage backgroundImage0;
    private BufferedImage backgroundImage1;


    /**
     * Constructor for the Level class with two maps.
     * 
     * @param x The initial x-coordinate of the player.
     * @param y The initial y-coordinate of the player.
     * @param mapOne The first map to be used in the level.
     * @param mapTwo The second map to be used in the level.
     */
    public Level(String levelName, int x, int y, Map mapOne, Map mapTwo, LevelSelect levelSelect) {
    	this.levelName = levelName;
    	
    	//Assign a copy to the menu to the level
    	this.levelSelect = levelSelect;
    	
        keys = new boolean[256]; // Initialize the keys array to track key presses

    	// Set up the level with initial player coordinates
    	levelSetup(x, y);
    	
        // Store the original player coordinates
    	originalPlayerX = x;
    	originalPlayerY = y;
    	
        // Store the maps
    	this.mapOne = mapOne;
    	this.mapTwo = mapTwo;
    	
    	// Initialize the map with a map data
        this.currentMap = mapOne;
        
        // Set the player's maps
        player.setMap(mapOne, mapTwo);        
    }
    
    /**
     * Constructor for the Level class with one map.
     * 
     * @param x The initial x-coordinate of the player.
     * @param y The initial y-coordinate of the player.
     * @param mapOne The map to be used in the level.
     */
    public Level(String levelName, int x, int y, Map mapOne, LevelSelect levelSelect) {
    	this.levelName = levelName;
    	
    	this.levelSelect = levelSelect;
    	
        keys = new boolean[256]; // Initialize the keys array to track key presses

    	// Set up the level with initial player coordinates
    	levelSetup(x, y);
    	
        // Store the original player coordinates
    	originalPlayerX = x;
    	originalPlayerY = y;
    	
    	// Initialize the map with a map data
        this.currentMap = mapOne;
        player.setMap(mapOne);        
    }
    
    /**
     * Method to add a new TextPrompt to the existing array of prompts.
     * 
     * @param text The text of the prompt to be added.
     * @param x The x-coordinate of the prompt.
     * @param y The y-coordinate of the prompt.
     */
    public void addPrompt(String text, int x, int y) {
    	
    	// Temporary array for storing existing prompts
    	TextPrompt[] temp;

    	// Check if there are existing prompts
    	if (prompt != null) {
    		
    		// Temporary array for storing existing prompts
    		temp = Arrays.copyOf(prompt, prompt.length);
    		
    		// Create a new array with an additional slot for the new prompt
    		prompt = new TextPrompt[promptSize + 1]; 
    		
    		// Copy prompts from temporary array to the new array
        	for(int i = 0; i < promptSize; i++) {
        		prompt[i] = temp[i];
        	}
        	
        	// Add new prompt to the last slot of the new array
        	prompt[promptSize] = new TextPrompt(text, x, y);
    	}else {
    		// If there are no existing prompts, create a new array with one slot for the new prompt
    		prompt = new TextPrompt[promptSize + 1];
    		
    		// Add new prompt to the first slot of the new array
    		prompt[0] = new TextPrompt(text, x, y);
    	}
    	
    	// Increment the size of prompts
    	promptSize++;
    }
    
    
    /**
     * Method to set up the level with initial player coordinates and game settings.
     * 
     * @param x The initial x-coordinate of the player.
     * @param y The initial y-coordinate of the player.
     */
    private void levelSetup(int x, int y) {
        // Create a new player at the given coordinates
    	player = new Player(x, y);

        try {
			backgroundImage0 = ImageIO.read(new File("levelImages/background0.png"));
			backgroundImage1 = ImageIO.read(new File("levelImages/background1.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
        // Add a key listener to handle player's key presses and releases
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Call player's keyPressed method when a key is pressed
                player.keyPressed(e.getKeyCode());
                keys[e.getKeyCode()] = true; // Mark the key as pressed

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Call player's keyReleased method when a key is released
                player.keyReleased(e.getKeyCode());
                keys[e.getKeyCode()] = false; // Mark the key as pressed

            }
        });

        // Set focusable to true to receive key events
        setFocusable(true);
        
        // Enable double buffering for smooth rendering
        setDoubleBuffered(true);
        
        // Create a timer to call actionPerformed method at 60 FPS
        timer = new Timer(1000 / 60, this); // 60 FPS
        timer.start();
        
        // Create an animation timer to gradually decrease alpha value and repaint the component
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
        
        // Create a new wall at the given coordinates
        wall = new Wall(-2500, -2500);
    }

    /**
     * Method called by the timer every frame.
     * 
     * @param e The action event from the timer.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game state
        update();
        
        // Repaint the component
        repaint();
    }


    /**
     * Method to update the game state every frame.
     */
    private void update(){
        // Update the player's state
        player.update();
        
        // Check if the player's current map has changed
        if(player.getCurrentMap() != currentMap) {
            // If so, update the current map and reset the alpha value
        	currentMap = player.getCurrentMap();
        	alpha = 1f;
        	
            // Start the animation timer
        	animationTimer.start();
        }
        
        // Update the wall's state
        wall.update();
        
        // Check if the player has won
        if (player.getWon()){
            // If so, stop the game timer
            animationTimer.stop();
            Points points = new Points(levelName);

            // Calculating points
            points.calculateCurrentPoints(wall, currentMap);

            // Saving points if greater than high score
            if (points.getCurrentPoints() > points.getHighScore()) {
            	 // Show a message dialog to congratulate the player
                JOptionPane.showMessageDialog(null, "Congratulations, you finished the Level and got " + points.getCurrentPoints() + " points!\n"
                		+ "This is better then your old score of " +  points.getHighScore() + "!", "Congratulations" , JOptionPane.INFORMATION_MESSAGE);
               points.setHighScore();
            }else {
            	JOptionPane.showMessageDialog(null, "Congratulations, you finished the Level and got " + points.getCurrentPoints() + " points!\n"
                		+ "However, this is not better then old score of " +  points.getHighScore() + "!", "Congratulations" , JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Exit the game
            levelSelect.dispose();
            levelSelect = new LevelSelect();
            timer.stop();
        }
        
        // Check if the wall has passed the player
        if (wall.getX() + wall.getWidth() > player.getX() || keys[KeyEvent.VK_ESCAPE]) {
            // If so, stop the game timer
            animationTimer.stop();

            // Show a dialog with options to exit or restart
            Object[] options = {"Exit", "Restart"};
            int n;
            if( keys[KeyEvent.VK_ESCAPE]) {
            	n = JOptionPane.showOptionDialog(null,
                        "You have paused the game. Would you like to exit or restart?",
                        "Pause Menu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);
            }else {
            	n = JOptionPane.showOptionDialog(null,
                        "The wall has passed you. Would you like to exit or restart?",
                        "Game Over",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);            	
            }
            
            // If the player chooses to exit, exit the game
            if (n == JOptionPane.YES_OPTION) {
                restartLevel();
                levelSelect.dispose();
                levelSelect = new LevelSelect();
                timer.stop();
            } 
            // If the player chooses to restart, restart the level
            else if (n == JOptionPane.NO_OPTION) {
                restartLevel();
             // Restart the game timer
                animationTimer.start();
            }
        }
    }


    /**
     * Method to Restart the level
     */
    private void restartLevel() {
        // Recreate the player at the original position
        player = new Player(originalPlayerX, originalPlayerY);
        player.setMap(mapOne, mapTwo);
        
        // Recreate the wall at the original position
        wall = new Wall(-2500, -2500);
        
        // Repaint the component
        repaint();

    }

    /**
     * Method to paint the component. This method is called every frame.
     * 
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        // Call the superclass's paintComponent method
        super.paintComponent(graphics);
        
     // Draw the background image
        graphics.drawImage(backgroundImage0, 0, 0, getWidth(), getHeight(), this);
        
     // Draw the background image at the current x position, scaled to the component's size
        graphics.drawImage(backgroundImage1, (0 - (player.getX() / 2) % getWidth()), 0, getWidth(), getHeight(), this);
        graphics.drawImage(backgroundImage1, (0 - (player.getX() / 2) % getWidth()) + getWidth(), 0, getWidth(), getHeight(), this);
        
        // Translate the Graphics object so that the player is always in the center of the screen
        graphics.translate( - player.getX() + getWidth()/2  , - player.getY() + getHeight()/2);
        
        // Draw the current map at the origin
        currentMap.draw(graphics, player.getX(),player.getY());
       
        // Set the font for drawing text prompts
        graphics.setFont(new Font("default", Font.BOLD, 20));
        
        // Draw each text prompt at its position
        for(int i = 0; i < promptSize; i++) {
        	graphics.drawString(prompt[i].getText(), prompt[i].getX(), prompt[i].getY());
        }
        
        // Draw the player at its position
        player.draw(graphics);
       
        // Draw the wall at its position
        wall.draw(graphics);
 
        // Create a new Graphics2D object from the Graphics object
        Graphics2D g2d = (Graphics2D) graphics.create();
        
        // Set the color to black with alpha transparency
        g2d.setColor(new Color(0, 0, 0, alpha));
        
        // Fill a rectangle over the entire screen with this color
        g2d.fillRect(player.getX() - getWidth()/2, player.getY() - getHeight()/2 , getWidth(), getHeight());
        
        // Dispose of this Graphics2D object to free up system resources and to improve performance
        g2d.dispose();
        

    }


}
