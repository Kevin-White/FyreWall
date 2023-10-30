import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Points {
	private String levelName;
	private int highScore = 0;
	private int currentPoints = 0;
	private String totalPointsFile = "Total_points";
	
	Points(){
		this.levelName = null;
		findHighScore();
	}

	
	Points(String levelName){
		this.levelName = levelName;
		findHighScore();
	}
	
	private void findHighScore(){
		String filename = "pointsData/" + levelName + "_points.txt";
        File file = new File(filename);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String lastLine = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
                highScore = Integer.parseInt(lastLine);
                reader.close();
            } catch(IOException e) {
                System.err.println("An error occurred while reading high score.");
                e.printStackTrace();
            }
        }
	}
	
	private void addToTotal(int pointsNow, int pointsNew) {
		int newPoints = pointsNew - pointsNow;
		int total = 0;
		String filename = "pointsData/" + totalPointsFile + ".txt";
		File file = new File(filename);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String lastLine = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
                total = Integer.parseInt(lastLine);
                reader.close();
            } catch(IOException e) {
                System.err.println("An error occurred while reading high score.");
                e.printStackTrace();
            }
        }
        

        total += newPoints;

        try {
            FileWriter writer = new FileWriter(filename, false); // Open in overwrite mode
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(Integer.toString(total));
            buffer.newLine();
            buffer.close();
        } catch(IOException e) {
            System.err.println("An error occurred while saving points.");
            e.printStackTrace();
        }
	}
	
	public void calculateCurrentPoints(Wall wall, Map map) {
		currentPoints = 1000 - (((wall.getX() + wall.getWidth()) * 1000) / map.getWidth());
	}

	public int getHighScore() {
		return highScore;
	}

	public int getCurrentPoints() {
		return currentPoints;
	}
	
	public void setHighScore() {
		if(currentPoints > highScore) {
			try {
				String filename = "pointsData/" + levelName + "_points.txt";
	            FileWriter writer = new FileWriter(filename, false); // Open in overwrite mode
	            BufferedWriter buffer = new BufferedWriter(writer);
	            buffer.write(Integer.toString(currentPoints));
	            buffer.newLine();
	            buffer.close();
	        } catch(IOException e) {
	            System.err.println("An error occurred while saving points.");
	            e.printStackTrace();
	        }
			
			addToTotal(highScore, currentPoints);
			
			highScore = currentPoints;
		}
		
	}
	
	public int getTotal() {
		int total = 0;
		String filename = "pointsData/" + totalPointsFile + ".txt";
		File file = new File(filename);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String lastLine = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    lastLine = line;
                }
                total = Integer.parseInt(lastLine);
                reader.close();
            } catch(IOException e) {
                System.err.println("An error occurred while reading high score.");
                e.printStackTrace();
            }
        }
        
        return total;
	}
	
	public void spendTotal(int purchase) {
		String filename = "pointsData/" + totalPointsFile + ".txt";
		int total = getTotal();
		
		total =  total - purchase;
		
		 try {
	            FileWriter writer = new FileWriter(filename, false); // Open in overwrite mode
	            BufferedWriter buffer = new BufferedWriter(writer);
	            buffer.write(Integer.toString(total));
	            buffer.newLine();
	            buffer.close();
	        } catch(IOException e) {
	            System.err.println("An error occurred while saving points.");
	            e.printStackTrace();
	        }
	}
}
