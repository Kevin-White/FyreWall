/**
 * The TextPrompt class represents a text prompt with various attributes.
 * Each instance of TextPrompt can have its own text, x and y coordinates.
 */
public class TextPrompt {
    private String text; // The text of the prompt
    private int x; // The x coordinate of the prompt
    private int y; // The y coordinate of the prompt
    
    /**
     * Constructor for the TextPrompt class.
     * It initializes a TextPrompt object with specific text and x and y coordinates.
     *
     * @param text The text of the prompt.
     * @param x The x coordinate of the prompt.
     * @param y The y coordinate of the prompt.
     */
    public TextPrompt(String text, int x, int y) {
        this.setText(text);
        this.setX(x);
        this.setY(y);
    }
    
    // Getter methods for the text, x and y coordinates of the prompt
    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    // Setter methods for the text, x and y coordinates of the prompt
    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}