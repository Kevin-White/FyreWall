import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Level extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Player player;
    private Map currentMap; // a Map object to store and draw the current game map
    private Timer animationTimer;
    private float alpha = 0f;
    private Wall wall;



    public Level(int x, int y, Map mapOne, Map mapTwo) {
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
        
        // Initialize the map with a map data
        this.currentMap = mapOne;
        player.setMap(mapOne, mapTwo);
        wall = new Wall(-5000, -2500);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the map with the offsets
        g.translate( - player.getX() + getWidth()/2  , - player.getY() + getHeight()/2);
        
        currentMap.draw(g, 0, 0);
       
        // Draw the player at its actual position
        player.draw(g);
       
        wall.draw(g);
 
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(new Color(0, 0, 0, alpha));
        g2d.fillRect(player.getX() - getWidth()/2, player.getY() - getHeight()/2 , getWidth(), getHeight());
        g2d.dispose();
    }


}
