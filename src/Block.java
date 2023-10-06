
/**
 * The Block class represents a block object with various attributes.
 * Each instance of Block can have an effect on the players size, speed, jump speed, gravity,
 * maximum velocity, velocity slowdown rate, and win status.
 * It also indicates whether the block is a default block or not.
 */
public class Block {
    private boolean isDefaultBlock; // Indicates whether the block is a default block or not
    private int newSize; // The size of the block
    private int newBlockSpeed; // The speed of the block
    private int newJumpSpeed; // The speed at which the block jumps
    private int newGravity; // The gravity affecting the block
    private int newMaxVelocity; // The maximum velocity of the block
    private int newVelocitySlowdown; // The rate at which the block's velocity slows down
    private boolean win = false; // Indicates whether it's a win or not
    
    /**
     * This is a constructor for the player class.
     * 
     * @param newSize The size of the player.
     * @param newBlockSpeed The speed of the player.
     * @param newJumpSpeed The speed at which the player jumps.
     * @param newGravity The gravity affecting the player.
     * @param newMaxVelocity The maximum velocity of the player.
     * @param newVelocitySlowdown The rate at which the player's velocity slows down.
     */
    public Block(int newSize, int newBlockSpeed, int newJumpSpeed, int newGravity, int newMaxVelocity, int newVelocitySlowdown){
    	this.newSize = newSize;
    	this.newBlockSpeed = newBlockSpeed;
    	this.newJumpSpeed = newJumpSpeed;
    	this.newGravity = newGravity;
    	this.newMaxVelocity = newMaxVelocity;
    	this.newVelocitySlowdown = newVelocitySlowdown;
    	
    	setDefaultBlock(false);
    }
    
    /**
     * This is another constructor for the block class that takes a boolean parameter.
     * 
     * @param isDefaultBlock A boolean indicating whether the block is a default block or not.
     */
    public Block(boolean isDefaultBlock) {
        // Setting the default block status
        this.setDefaultBlock(isDefaultBlock);
        
        // If the block is a default block, initialize with specific values
        if(isDefaultBlock == true) {
            this.newSize = 50;
            this.newBlockSpeed = 2;
            this.newJumpSpeed = 40;
            this.newGravity = 3;
            this.newMaxVelocity = 40;
            this.newVelocitySlowdown = 1;
        } else {
            // If the block is not a default block, initialize all values to zero
            this.newSize = 0;
            this.newBlockSpeed = 0;
            this.newJumpSpeed = 0;
            this.newGravity = 0;
            this.newMaxVelocity = 0;
            this.newVelocitySlowdown = 0;
        }   
    }

    
    /**
     * This is a default constructor for the block class.
     * It initializes a Block object as a default block with specific values.
     */
    public Block() {
        // Setting the player as a default block
        this.setDefaultBlock(true);
        
        // Initializing the player attributes with specific values
        this.newSize = 50;
        this.newBlockSpeed = 2;
        this.newJumpSpeed = 40;
        this.newGravity = 3;
        this.newMaxVelocity = 40;
        this.newVelocitySlowdown = 1;
    }

    
 // Getter and Setter methods for the player class

 // Getter and Setter for newSize
 public int getNewSize() {
     return newSize; // Returns the size of the player
 }

 public void setNewSize(int newSize) {
     this.newSize = newSize; // Sets the size of the player
 }

 // Getter and Setter for newBlockSpeed
 public int getNewBlockSpeed() {
     return newBlockSpeed; // Returns the speed of the player
 }

 public void setNewBlockSpeed(int newBlockSpeed) {
     this.newBlockSpeed = newBlockSpeed; // Sets the speed of the player
 }

 // Getter and Setter for newJumpSpeed
 public int getNewJumpSpeed() {
     return newJumpSpeed; // Returns the jump speed of the player
 }

 public void setNewJumpSpeed(int newJumpSpeed) {
     this.newJumpSpeed = newJumpSpeed; // Sets the jump speed of the player
 }

 // Getter and Setter for newGravity
 public int getNewGravity() {
     return newGravity; // Returns the gravity affecting the player
 }

 public void setNewGravity(int newGravity) {
     this.newGravity = newGravity; // Sets the gravity affecting the player
 }

 // Getter and Setter for newMaxVelocity
 public int getNewMaxVelocity() {
     return newMaxVelocity; // Returns the maximum velocity of the player
 }

 public void setNewMaxVelocity(int newMaxVelocity) {
     this.newMaxVelocity = newMaxVelocity; // Sets the maximum velocity of the player
 }

 // Getter and Setter for newVelocitySlowdown
 public int getNewVelocitySlowdown() {
     return newVelocitySlowdown; // Returns the rate at which the player's velocity slows down
 }

 public void setNewVelocitySlowdown(int newVelocitySlowdown) {
     this.newVelocitySlowdown = newVelocitySlowdown; // Sets the rate at which the player's velocity slows down
 }

 // Getter and Setter for isDefaultBlock
 public boolean isDefaultBlock() {
     return isDefaultBlock; // Returns whether the player is a default block or not
 }

 public void setDefaultBlock(boolean isDefaultBlock) {
     this.isDefaultBlock = isDefaultBlock; // Sets whether the player is a default block or not
 }

 // Getter and Setter for win
 public boolean isWin() {
     return win; // Returns whether it's a win or not
 }

 public void setWin(boolean win) {
     this.win = win; // Sets whether it's a win or not
 }

}
