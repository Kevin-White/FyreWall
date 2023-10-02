import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int size = 50;
    private int blockSpeed = 2;
    private int jumpSpeed = 40;
    private int gravity = 3;
    private boolean canJump = true;
    private boolean[] keys;
    private int maxVelocity = 40;
    private int velocityX = 0;
    private int velocityY = 0;
    private int velocitySlowdown = 1;
    private long lastMapChange = 0;
    private Map currentMap;
    private Map mapOne;
    private Map mapTwo;

    private Block block = new Block(true);

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        keys = new boolean[256];
    }
    
    public void setMap(Map mapOne, Map mapTwo) {
    	this.mapOne = mapOne;
    	this.currentMap = mapOne;
    	this.mapTwo = mapTwo;
    }
    
    public int getX(){
    	return x;
    }
    public int getY(){
    	return y;
    }
    public int getSize() {
    	return size;
    }
    public int getBlockSpeed() {
    	return blockSpeed;
    }
    public int getJumpSpeed() {
    	return jumpSpeed;
    }
    public int getGravity() {
    	return gravity;
    }
    public int getMaxVelocity() {
    	return maxVelocity;
    }
    public int getVelocitySlowdown() {
    	return velocitySlowdown;
    }
    public Map getCurrentMap() {
    	return currentMap;
    }

    public void keyPressed(int keyCode) {
        keys[keyCode] = true;
    }

    public void keyReleased(int keyCode) {
        keys[keyCode] = false;
    }

    public void update() {


        if (keys[KeyEvent.VK_W] && canJump) {
            velocityY -= jumpSpeed;
            canJump = false;
        }
        if (keys[KeyEvent.VK_S] && gravity >= 0) velocityY += blockSpeed;
        if (keys[KeyEvent.VK_A]) velocityX -= blockSpeed;
        if (keys[KeyEvent.VK_D]) velocityX += blockSpeed;
        if (keys[KeyEvent.VK_SPACE]) {
            // 1000 is the delay in milliseconds. Adjust as needed.
            if (System.currentTimeMillis() - lastMapChange > 2000) { 
            	lastMapChange = System.currentTimeMillis();
                if (currentMap == mapOne) {
                    currentMap = mapTwo;
                } else {
                    currentMap = mapOne;
                }
            }
        }

        velocityX = velocityCalc(velocityX);
        velocityY = velocityCalc(velocityY);

        // Check and resolve collisions in the x direction
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

        // Check and resolve collisions in the y direction
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
//        	}else if(velocityY < 0 && gravity < 0){
//        		canJump = true;
//        		y -= 1;
//        		while(collidesWithMap()) {
//        			y -= 1;
//        		}
        	}else {
        		if(gravity  < 0) {
        			canJump = true;
        		}
        		y += 1;
        		while(collidesWithMap()) {
        			y += 1;
        		}
        	}
        	//y -= velocityY;
            velocityY = 0;
        }
        
        velocityY += gravity;
        velocityX = slowdownCalc(velocityX);
        velocityY = slowdownCalc(velocityY);
    }


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

    private int velocityCalc(int velocity) {
        if (velocity > maxVelocity) {
            velocity = maxVelocity;
        }
        if (velocity < -maxVelocity) {
            velocity = -maxVelocity;
        }
        return velocity;
    }

    private int slowdownCalc(int velocity) {
        if (velocity > 0) {
            velocity -= velocitySlowdown;
        }
        if (velocity < 0) {
            velocity += velocitySlowdown;
        }
        return velocity;
    }
    
    private void playerChanges() {
    	this.size = block.getNewSize();
    	this.blockSpeed = block.getNewBlockSpeed();
    	this.jumpSpeed = block.getNewJumpSpeed();
    	this.gravity = block.getNewGravity();
    	this.maxVelocity = block.getNewMaxVelocity();
    	this.velocitySlowdown = block.getNewVelocitySlowdown();
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, size, size);
    }
}
