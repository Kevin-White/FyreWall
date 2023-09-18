import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {
    private int x, y;
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
            velocityY -= blockSpeed;
        }
        if (keys[KeyEvent.VK_S]) velocityY += blockSpeed;
        if (keys[KeyEvent.VK_A]) velocityX -= blockSpeed;
        if (keys[KeyEvent.VK_D]) velocityX += blockSpeed;

        if (collidesWithMap()) {
        	System.out.println("Hit");
        }

        velocityCalc(velocityX);
        velocityCalc(velocityY);

        x += velocityX;
        y += velocityY;

        velocityX = slowdownCalc(velocityX);
        velocityY = slowdownCalc(velocityY);
    }

    public boolean collidesWithMap() {
        if (map == null) {
            return false; // No map to collide with
        }

        int playerLeft = getX();
        int playerRight = getX() + 50;
        int playerTop = getY();
        int playerBottom = getY() + 50;

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
        g.fillRect(x, y, 50, 50);
    }
}
