import java.awt.Color;
import java.awt.Graphics;

public class Map {
    private int[][] mapData; // 2D array to store map data
    private int tileSize;   // Size of each tile in pixels
    
    private Block basic;
    private Block ice;
    private Block fire;
    private Block antiGravity;
    private Block reset;

    public Map(int[][] mapData, int tileSize) {
        this.mapData = mapData;
        this.tileSize = tileSize;    
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
    
    //All the different block types a user can uuse
    public Block blockType(int row, int col, Player player){
        //All the block types you can pick for a map;
    	//player effects(playerSize, player speed, jump height, gravity, max velocity, slowdown velocity )
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
                	case 4:
                		g.setColor(Color.LIGHT_GRAY);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;
                	case 5:
                		g.setColor(Color.DARK_GRAY);
	                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
	                    break;

                }
            }
        }
    }
}
