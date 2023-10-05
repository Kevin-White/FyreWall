import java.awt.Graphics;


public class Wall {
    private int x, y;
    private int speed = 2;
    private static int width = 5000;
    private static int hight = 5000;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, hight); // Change the size of the wall as needed
    }

	public int getWidth() {
		return width;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
