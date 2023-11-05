import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import javax.imageio.ImageIO;

/**
 * The Player class in a game manages player attributes, handles input, updates state, 
 * checks collisions, and renders the player.
 */
public class Player {
    private int x, y; // The player's current coordinates
    private int size = 50; // The size of the player
    private int blockSpeed = 2; // The speed at which the player moves horizontally
    private int jumpSpeed = 40; // The speed at which the player jumps
    private int gravity = 3; // The gravity affecting the player
    private boolean canJump = true; // Whether the player can jump or not
    private boolean[] keys; // An array to track key presses
    private int maxVelocity = 40; // The maximum velocity of the player
    private int velocityX = 0; // The player's current horizontal velocity
    private int velocityY = 0; // The player's current vertical velocity
    private int velocitySlowdown = 1; // The rate at which the player's velocity decreases when not moving
    private long lastMapChange = 0; // The time of the last map change
    private Map currentMap; // The current map where the player is located
    private Map mapOne; // The first map for the game
    private Map mapTwo; // The second map for the game
    private boolean won = false; // Whether the player has won or not
    private Block block = new Block(true); // The type of block that the player last collided with
    private BufferedImage currentSkin;
    private int lastKeyPressed = -1;

    /**
     * Constructor for the Player class.
     *
     * @param x The initial x-coordinate for the player.
     * @param y The initial y-coordinate for the player.
     */
    public Player(int x, int y) {
        this.x = x; // Set the player's initial x-coordinate
        this.y = y; // Set the player's initial y-coordinate
        keys = new boolean[256]; // Initialize the keys array to track key presses
        
        try {
            currentSkin = ImageIO.read(new File("playerSkins/currentSkin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Sets the maps for the game.
     *
     * @param mapOne The first map to be used in the game.
     * @param mapTwo The second map to be used in the game.
     */
    public void setMap(Map mapOne, Map mapTwo) {
        this.mapOne = mapOne; // Set the first map
        this.currentMap = mapOne; // Set the current map to be the first map
        this.mapTwo = mapTwo; // Set the second map
    }

    /**
     * Sets the map for the game.
     *
     * @param mapOne The map to be used in the game.
     */
    public void setMap(Map mapOne) {
        this.mapOne = mapOne; // Set the map
        this.currentMap = mapOne; // Set the current map to be the provided map
    }

    /**
     * @return The x-coordinate of the player.
     */
    public int getX(){
        return x;
    }

    /**
     * @return The y-coordinate of the player.
     */
    public int getY(){
        return y;
    }

    /**
     * @return The size of the player.
     */
    public int getSize() {
        return size;
    }

    /**
     * @return The speed at which the player moves horizontally.
     */
    public int getBlockSpeed() {
        return blockSpeed;
    }

    /**
     * @return The speed at which the player jumps.
     */
    public int getJumpSpeed() {
        return jumpSpeed;
    }

    /**
     * @return The gravity affecting the player.
     */
    public int getGravity() {
        return gravity;
    }

    /**
     * @return The maximum velocity of the player.
     */
    public int getMaxVelocity() {
        return maxVelocity;
    }

    /**
     * @return The rate at which the player's velocity decreases when not moving.
     */
    public int getVelocitySlowdown() {
        return velocitySlowdown;
    }

    /**
     * @return Whether the player has won or not.
     */
    public boolean getWon() {
        return won;
    }

    /**
     * @return The current map where the player is located.
     */
    public Map getCurrentMap() {
        return currentMap;
    }

    /**
     * Marks the key with the given key code as pressed.
     *
     * @param keyCode The code of the key that was pressed.
     */
    public void keyPressed(int keyCode) {
        keys[keyCode] = true; // Mark the key as pressed
        lastKeyPressed = keyCode;
    }

    /**
     * Marks the key with the given key code as released.
     *
     * @param keyCode The code of the key that was released.
     */
    public void keyReleased(int keyCode) {
        keys[keyCode] = false; // Mark the key as released
    }


    /**
     * Updates the player's state based on key presses and game physics.
     */
    public void update() {

        // If the W key is pressed and the player can jump, make the player jump
        if (keys[KeyEvent.VK_W] && canJump) {
            velocityY -= jumpSpeed;
            canJump = false;
        }
        
     // If the S key is pressed and gravity is non-negative, increase the player's downward velocity
        if (keys[KeyEvent.VK_S] && gravity >= 0) velocityY += blockSpeed;
        // If the A key is pressed, decrease the player's horizontal velocity to move left
        if (keys[KeyEvent.VK_A]) velocityX -= blockSpeed;
        // If the D key is pressed, increase the player's horizontal velocity to move right
        if (keys[KeyEvent.VK_D]) velocityX += blockSpeed;
        // If the SPACE key is pressed and there is a second map, switch to it after a delay since last map switch
        if (keys[KeyEvent.VK_SPACE] && mapTwo != null) {
            // 1000 is the delay in milliseconds. Adjust as needed.
            if (System.currentTimeMillis() - lastMapChange > 500) { 
            	lastMapChange = System.currentTimeMillis();
                if (currentMap == mapOne) {
                    currentMap = mapTwo;
                } else {
                    currentMap = mapOne;
                }
            }
        }
        
        // Apply game physics to the player's velocities
        velocityX = velocityCalc(velocityX);
        velocityY = velocityCalc(velocityY);

        // Update player's x position and resolve any collisions in the x direction
        x += velocityX;
        if (collidesWithMap()) {
        	if(this.block.isDefaultBlock() == false){
        		playerChanges();
        	}
        	if (velocityX > 0) {
        		x -= 1;
        		while(collidesWithMap()) {
        			x -= 1;
        		}
        	}else {
        		x += 1;
        		while(collidesWithMap()) {
        			x += 1;
        		}
        	}
            velocityX = 0;
        }

        // Update player's y position and resolve any collisions in the y direction
        y += velocityY;
        if (collidesWithMap()) {
        	if(this.block.isDefaultBlock() == false){
        		playerChanges();
        	}
        	if(velocityY > 0) {
        		if(gravity > 0) {
        			canJump = true;
        		}
        		y -= 1;
        		while(collidesWithMap()) {
        			y -= 1;
        		}
        	}else {
        		if(gravity  < 0) {
        			canJump = true;
        		}
        		y += 1;
        		while(collidesWithMap()) {
        			y += 1;
        		}
        	}
            velocityY = 0;
        }
        
        // Apply gravity to the player's vertical velocity
        velocityY += gravity;
        
        // Slow down the player's velocities
        velocityX = slowdownCalc(velocityX);
        velocityY = slowdownCalc(velocityY);
    }


    /**
     * Checks if the player collides with any solid tiles on the current map.
     *
     * @return true if the player collides with a solid tile, false otherwise.
     */
    private boolean collidesWithMap() {
        if (currentMap == null) {
            return false; // No map to collide with
        }

        int playerLeft = getX();
        int playerRight = getX() + size - 1;
        int playerTop = getY();
        int playerBottom = getY() + size - 1;

        int tileSize = currentMap.getTileSize();

        // Calculate the tiles that the player's bounding box overlaps
        int leftTile = playerLeft / tileSize;
        int rightTile = playerRight / tileSize;
        int topTile = playerTop / tileSize;
        int bottomTile = playerBottom / tileSize;

        // Check for collisions with nearby map tiles
        for (int row = topTile; row <= bottomTile; row++) {
            for (int col = leftTile; col <= rightTile; col++) {
                if (currentMap.isSolidTile(row, col)) {
                    // Collision with a solid tile
                	this.block = currentMap.blockType(row, col, this);
                    return true;
                    
                }
            }
        }

        return false; // No collision detected
    }

    /**
     * Ensures that the given velocity does not exceed the maximum velocity.
     *
     * @param velocity The velocity to be checked.
     * @return The velocity, adjusted if necessary to not exceed the maximum velocity.
     */
    private int velocityCalc(int velocity) {
        if (velocity > maxVelocity) {
            velocity = maxVelocity; // If the velocity is greater than the maximum, set it to the maximum
        }
        if (velocity < -maxVelocity) {
            velocity = -maxVelocity; // If the velocity is less than the negative maximum, set it to the negative maximum
        }
        return velocity; // Return the adjusted velocity
    }


    /**
     * Slows down the given velocity.
     *
     * @param velocity The velocity to be slowed down.
     * @return The velocity after being slowed down.
     */
    private int slowdownCalc(int velocity) {
        if (velocity > 0) {
            velocity -= velocitySlowdown; // If the velocity is positive, decrease it
        }
        if (velocity < 0) {
            velocity += velocitySlowdown; // If the velocity is negative, increase it
        }
        return velocity; // Return the adjusted velocity
    }

    /**
     * Updates the player's properties based on the properties of the block they collided with.
     */
    private void playerChanges() {
    	this.won = block.isWin();
    	this.size = block.getNewSize();
    	this.blockSpeed = block.getNewBlockSpeed();
    	this.jumpSpeed = block.getNewJumpSpeed();
    	this.gravity = block.getNewGravity();
    	this.maxVelocity = block.getNewMaxVelocity();
    	this.velocitySlowdown = block.getNewVelocitySlowdown();
    }

    /**
     * Draws the player on the screen.
     *
     * @param g The Graphics object to protect.
     */
    public void draw(Graphics graphics) {
        // Determine which skin to use based on the last key pressed
        Image skinToUse = currentSkin;
        int skinWidth = skinToUse.getWidth(null);
        int skinHeight = skinToUse.getHeight(null);

        // Create a new Graphics2D object from the original graphics object
        Graphics2D g2d = (Graphics2D) graphics.create();

        if (gravity < 0) { // If gravity is less than 0
            // Flip the image vertically
            g2d.translate(x, y + skinHeight);
            g2d.scale(1, -1);
            
            if (lastKeyPressed == KeyEvent.VK_A) { // 'A' key for left
                // Flip the image horizontally
                g2d.translate(skinWidth, 0);
                g2d.scale(-1, 1);
            }
            
            g2d.drawImage(skinToUse, 0, 0, size, size, null);
        } else if (lastKeyPressed == KeyEvent.VK_A) { // 'A' key for left
            // Flip the image horizontally
            g2d.translate(x + skinWidth, y);
            g2d.scale(-1, 1);
            g2d.drawImage(skinToUse, 0, 0, size, size, null);
        } else if (lastKeyPressed == KeyEvent.VK_D) { // 'D' key for right
            g2d.drawImage(skinToUse, x, y, size, size, null);
        } else {
            g2d.drawImage(skinToUse, x, y, size, size, null);
        }

        // Dispose the Graphics2D object to free up system resources and improve performance
        g2d.dispose();
    }



}