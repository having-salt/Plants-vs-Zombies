import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Zombie {
	protected int width;
	protected int height;
	protected int live;
	protected int x;
	protected int y;
	protected int xSpeed;
	
	public static final int LIFE = 0;
	public static final int ATTACK = 1;
	public static final int DEAD = 2;
	protected int state = LIFE;
	Zombie(int width,int height){
		Random rand = new Random();
		this.x = Game.WIDTH-300;
		int j = rand.nextInt(5);
		this.y = 20 + j*Glass.HEIGHT;
		this.width = width;
		this.height = height;
	}
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Zombie.class.getResource(fileName));
			return img;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	public abstract BufferedImage getImage();
	public void paintObject(Graphics g) {
		g.drawImage(getImage(),x,y,null);                                   
	}
	public abstract void step();
	public boolean isLife() {
		return state == LIFE;
	}
	public boolean isAttack() {
		return state == ATTACK;
	}
}
