import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.Image;


import javax.imageio.ImageIO;

/**
 * The Map class represents a game map with various attributes.
 * Each instance of Map can have its own map data and tile size.
 * It also contains different types of blocks that can be used in the map.
 */
public class Map {
    private int[][] mapData; // 2D array to store the map data
    private int tileSize;   // Size of each tile in pixels   
    private Image basicImage;
    private Image iceImage;
    private Image fireImage;
    private Image antiImage;
    private Image resetImage;
    private Image finalImage;


    /**
     * Constructor for the Map class.
     * It initializes a Map object with specific map data and tile size.
     *
     * @param mapData The 2D array representing the map data.
     * @param tileSize The size of each tile in pixels.
     */
    public Map(int[][] mapData, int tileSize) {
        this.mapData = mapData;
        this.tileSize = tileSize;  
        
        try {
        	basicImage = ImageIO.read(new File("levelImages/basicFloor.png"));
        	iceImage = ImageIO.read(new File("levelImages/iceFloor.png"));
        	fireImage = ImageIO.read(new File("levelImages/fireFloor.png"));
        	antiImage = ImageIO.read(new File("levelImages/antiFloor.png"));
        	resetImage = ImageIO.read(new File("levelImages/resetFloor.png"));
        	finalImage = ImageIO.read(new File("levelImages/finalFloor.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Getter methods for the height, width, and tile size of the map
    public int getHeight() {
    	return  mapData.length * tileSize;
    }
    
    public int getWidth() {
    	return mapData[0].length * tileSize;
    }
    
    public int getTileSize(){
    	return tileSize;
    }
    
    /**
     * This method checks if a specific tile is solid or not.
     *
     * @param row The row index of the tile.
     * @param col The column index of the tile.
     * @return true if the tile is solid, false otherwise.
     */
    public boolean isSolidTile(int row, int col){
    	if(mapData[row][col] != 0) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * This method returns a specific type of block based on the map data.
     *
     * @param row The row index of the block.
     * @param col The column index of the block.
     * @param player The player object to get player effects from.
     * @return A Block object representing a specific type of block.
     */
    public Block blockType(int row, int col, Player player){
        switch(mapData[row][col]) {
    		case 0:
    			return null;
    		case 1:
    			Block basic = new Block();
    			return basic;
    		case 2:
    			Block ice = new Block(player.getSize(), 1, player.getJumpSpeed(), player.getGravity(), 20, player.getVelocitySlowdown());
    			return ice;
    		case 3:
    			Block fire = new Block(player.getSize(), 3, player.getJumpSpeed(), player.getGravity(), 60, player.getVelocitySlowdown());
    			return fire;
    		case 4:
    			Block antiGravity = new Block(player.getSize(), player.getBlockSpeed(), -40, -3, player.getMaxVelocity(), player.getVelocitySlowdown());
    			return antiGravity;
    		case 5:
    			Block reset = new Block(50, 2, 40, 3, 40, 1);
    			return reset;
    		case 6:
    			Block win = new Block();
    			win.setDefaultBlock(false);
    			win.setWin(true);
    			return win;
    		default:
    			return null;
    	}
    }
    
    /**
     * This method draws the map on a Graphics object.
     *
     * @param g The Graphics object on which to draw the map.
     * @param xOffset The x offset to apply when drawing the map.
     * @param yOffset The y offset to apply when drawing the map.
     */
    public void draw(Graphics graphics, int xOffset, int yOffset) {
        
        Graphics2D g2d = (Graphics2D) graphics.create();

        int screenWidth = g2d.getClipBounds().width;
        int screenHeight = g2d.getClipBounds().height;

        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                int x = col * tileSize;
                int y = row * tileSize;
                
                if (x > xOffset + screenWidth/2 || x < xOffset - screenWidth/2 - 50 || y > yOffset + screenHeight/2 || y < yOffset - screenHeight/2 - 50) {
                    continue; // Skip drawing if outside of screen bounds
                }

                switch(mapData[row][col]){
                    case 1:
                        g2d.drawImage(basicImage, x, y, tileSize, tileSize, null);
                        break;
                    case 2:
                        g2d.drawImage(iceImage, x, y, tileSize, tileSize, null);
                        break;
                    case 3:
                        g2d.drawImage(fireImage, x, y, tileSize, tileSize, null);
                        break;
                    case 4:
                        g2d.drawImage(antiImage, x, y, tileSize, tileSize, null);
                        break;
                    case 5:
                        g2d.drawImage(resetImage, x, y, tileSize, tileSize, null);
                        break;
                    case 6:
                        g2d.drawImage(finalImage, x, y, tileSize, tileSize, null);
                        break;
                }
            }
        }
        g2d.dispose();
    }
}
