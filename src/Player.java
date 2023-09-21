import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
    private int size = 50;
    private int blockSpeed = 2;
    private int jumpSpeed = 50;
    private int gravity = 3;
    private boolean canJump = true;
    private boolean[] keys;
    private int maxVelocity = 50;
    private int velocityX = 0;
    private int velocityY = 0;
    private int velocitySlowdown = 1;
    private Map map;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        keys = new boolean[256];
    }
    
    public void setMap(Map map) {
    	this.map = map;
    }
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
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
        if (keys[KeyEvent.VK_S]) velocityY += blockSpeed;
        if (keys[KeyEvent.VK_A]) velocityX -= blockSpeed;
        if (keys[KeyEvent.VK_D]) velocityX += blockSpeed;

        velocityCalc(velocityX);
        velocityCalc(velocityY);

        // Check and resolve collisions in the x direction
        x += velocityX;
        if (collidesWithMap()) {
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
        	if(velocityY > 0) {
        		canJump = true;
        		y -= 1;
        		while(collidesWithMap()) {
        			y -= 1;
        		}
        	}else {
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


    public boolean collidesWithMap() {
        if (map == null) {
            return false; // No map to collide with
        }

        int playerLeft = getX();
        int playerRight = getX() + size - 1;
        int playerTop = getY();
        int playerBottom = getY() + size - 1;

        int tileSize = map.getTileSize();

        // Calculate the tiles that the player's bounding box overlaps
        int leftTile = playerLeft / tileSize;
        int rightTile = playerRight / tileSize;
        int topTile = playerTop / tileSize;
        int bottomTile = playerBottom / tileSize;

        // Check for collisions with nearby map tiles
        for (int row = topTile; row <= bottomTile; row++) {
            for (int col = leftTile; col <= rightTile; col++) {
                if (map.isSolidTile(row, col)) {
                    // Collision with a solid tile
                    return true;
                }
            }
        }

        return false; // No collision detected
    }

    private void velocityCalc(int velocity) {
        if (velocity > maxVelocity) {
            velocity = maxVelocity;
        }
        if (velocity < -maxVelocity) {
            velocity = -maxVelocity;
        }
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

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, size, size);
    }
}
