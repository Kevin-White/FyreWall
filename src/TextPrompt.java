
public class TextPrompt {
	private String text;
	private int x;
	private int y;
	
	public TextPrompt(String text, int x, int y) {
		this.setText(text);
		this.setX(x);
		this.setY(y);
	}

	public String getText() {
		return text;
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

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
