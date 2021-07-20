public class Glass {
	private int x;
	private int y;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 100;
	
	public Glass(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}

	
	private static final int EMPTY = 0;
	private static final int HOLD = 1;
	private int state = EMPTY;

	
	public boolean isEmpty() {
		return state==EMPTY;
	}
	public boolean isHold() {
		return state==HOLD;
	}
	
	public void goEmpty() {
		state = EMPTY;
	}
	public void goHold() {
		state = HOLD;
	}
	
}
