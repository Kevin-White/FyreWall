import java.awt.Color;
import java.awt.Graphics;

/**
 * The Wall class represents a wall object with various attributes.
 * Each instance of Wall can have its own x and y coordinates, speed, width, and height.
 */
public class Wall {
    private int x, y; // The x and y coordinates of the wall
    private int speed = 5; // The speed at which the wall moves
    private static int width = 5000; // The width of the wall
    private static int height = 5000; // The height of the wall

    /**
     * This is a constructor for the Wall class.
     * It initializes a Wall object with specific x and y coordinates.
     *
     * @param x The x coordinate of the wall.
     * @param y The y coordinate of the wall.
     */
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method updates the x coordinate of the wall by adding the speed to it.
     */
    public void update() {
        x += speed;
    }

    /**
     * This method draws the wall on a Graphics object.
     *
     * @param g The Graphics object on which to draw the wall.
     */
    public void draw(Graphics graphics) {
    	graphics.setColor(Color.red);
    	graphics.fillRect(x, y, width, height); // Draws a rectangle (the wall) on the Graphics object
    }

    /**
     * This method returns the width of the wall.
     *
     * @return The width of the wall.
     */
	public int getWidth() {
		return width;
	}
	
	/**
     * This method returns the x coordinate of the wall.
     *
     * @return The x coordinate of the wall.
     */
	public int getX() {
		return x;
	}
	
	/**
     * This method returns the y coordinate of the wall.
     *
     * @return The y coordinate of the wall.
     */
	public int getY() {
		return y;
	}
}