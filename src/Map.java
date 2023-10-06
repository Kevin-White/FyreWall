import java.awt.Color;
import java.awt.Graphics;

/**
 * The Map class represents a game map with various attributes.
 * Each instance of Map can have its own map data and tile size.
 * It also contains different types of blocks that can be used in the map.
 */
public class Map {
    private int[][] mapData; // 2D array to store the map data
    private int tileSize;   // Size of each tile in pixels
    
    // Different types of blocks that can be used in the map
    private Block basic;
    private Block ice;
    private Block fire;
    private Block antiGravity;
    private Block reset;
    private Block win;
    
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
    			basic = new Block();
    			return basic;
    		case 2:
    			ice = new Block(player.getSize(), 1, player.getJumpSpeed(), player.getGravity(), 20, player.getVelocitySlowdown());
    			return ice;
    		case 3:
    			fire = new Block(player.getSize(), 3, player.getJumpSpeed(), player.getGravity(), 60, player.getVelocitySlowdown());
    			return fire;
    		case 4:
    			antiGravity = new Block(player.getSize(), player.getBlockSpeed(), -40, -3, player.getMaxVelocity(), player.getVelocitySlowdown());
    			return antiGravity;
    		case 5:
    			reset = new Block(50, 2, 40, 3, 40, 1);
    			return reset;
    		case 6:
    			win = new Block();
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
    public void draw(Graphics g, int xOffset, int yOffset) {
        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                switch(mapData[row][col]){
                	case 1:
	                	g.setColor(Color.BLACK);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                	case 2:
                		g.setColor(Color.BLUE);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                	case 3:
                		g.setColor(Color.RED);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                	case 4:
                		g.setColor(Color.LIGHT_GRAY);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                	case 5:
                		g.setColor(Color.DARK_GRAY);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                	case 6:
                		g.setColor(Color.GREEN);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                }
            }
        }
    }
}
