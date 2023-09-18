import java.awt.Color;
import java.awt.Graphics;

public class Map {
    private int[][] mapData; // 2D array to store map data
    private int tileSize;   // Size of each tile in pixels

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
    	if (mapData[row][col] != 0) {
    		return true;
    	}
    	return false;
    }

    public void draw(Graphics g, int xOffset, int yOffset) {
        for (int row = 0; row < mapData.length; row++) {
            for (int col = 0; col < mapData[row].length; col++) {
                if (mapData[row][col] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(col * tileSize + xOffset, row * tileSize + yOffset, tileSize, tileSize);
                }
            }
        }
    }
}
