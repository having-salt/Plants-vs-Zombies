import java.awt.image.BufferedImage;

public class Zombie1 extends Zombie{
	
	public Zombie1() {
		super(166,144);
		live = 15;
		xSpeed = 10;
	}
	private static BufferedImage imgs[];
	static {
		imgs = new BufferedImage[10];
		for(int i=0;i<imgs.length;i++) {
			imgs[i] = loadImage("zombie1"+i+".png");
		}
	}
	@Override
	public void step() {
		this.x-=xSpeed;
	}
	int index = 0;
	@Override
	public BufferedImage getImage() {
		if(isLife()) {
			return imgs[index++%5];//0-4
		}else if(isAttack()) {
			return imgs[index++%5+5];//5-9
		}else{
			return null;
		}
	}
	
}
