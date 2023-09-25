import java.awt.Color;
import java.awt.Graphics;

public class Map {
    private int[][] mapData; // 2D array to store map data
    private int tileSize;   // Size of each tile in pixels
    
    private Block basic;
    private Block ice;
    private Block fire;

    public Map(int[][] mapData, int tileSize) {
        this.mapData = mapData;
        this.tileSize = tileSize;
        
        //All the block types you can pick for a map;
        //player effects(playerSize, player speed, jump height, gravity, max velocity, slowdown velocity )
        basic = new Block(50, 2, 40, 3, 40, 1);
        basic.setDefaultBlock(true);
        ice = new Block(50, 1, 20, 3, 20, 1);
        fire = new Block(50, 2, 40, 3, 40, 1);
    }
    
    public int getHeight() {
    	return  mapData.length * tileSize;
    }
    
    public int getWidth() {
    	return mapData[0].length * tileSize;
    }
    
    public int getTileSize(){
    	return tileSize;
    }
    
    public boolean isSolidTile(int row, int col){
    	if(mapData[row][col] != 0) {
    		return true;
    	}
    	return false;
    }
    
    public Block blockType(int row, int col){
    	switch(mapData[row][col]) {
    		case 0:
    			return null;
    		case 1:
    			return basic;
    		case 2:
    			return ice;
    		case 3:
    			return fire;
    		default:
    			return null;
    	}
    }
    
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
                }
            }
        }
    }
}
