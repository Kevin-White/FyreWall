import java.awt.Graphics;


public class Wall {
    private int x, y;
    private int speed = 1;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        x += speed;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 5000, 5000); // Change the size of the wall as needed
    }
}
