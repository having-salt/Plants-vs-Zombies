import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;



public abstract class Plant {
	
	public static final int WAIT = 0;
	public static final int MOVE = 1;
	public static final int DEAD = 2;
	public static final int LIFE = 3;
	
	protected int state = WAIT;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int live;
	public void setState(int state) {
		this.state = state;
	}
	public boolean isWait() {
		return state == WAIT;
	}
	public boolean isMove() {
		return state == MOVE;
	}
	public boolean isLife() {
		return state == LIFE;
	}
	public boolean isDead() {
		return state == DEAD;
	}
	public void goMove() {
		state = MOVE;
	}
	public void goLife() {
		state = LIFE;
	}
	public void goWait() {
		state = WAIT;
	}
	public void goDead() {
		state = DEAD;
	}
	public Plant(int width,int height) {
		this.width = width;
		this.height = height;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void moveTo(int x,int y) {
		this.x = x-this.width/2;
		this.y = y-this.height/2;
	}
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Plant.class.getResource(fileName));
			return img;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public abstract BufferedImage getImage();//各个植物图片不同，继承的时候再写
	public void paintObject(Graphics g) {
		g.drawImage(getImage(),x,y,null);                                   
	}
}
