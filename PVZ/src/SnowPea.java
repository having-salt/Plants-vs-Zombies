import java.awt.image.BufferedImage;

public class SnowPea extends Plant implements Shoot{
	private static BufferedImage[] imgs;
	static {
		imgs = new BufferedImage[6];
		for(int i=0;i<imgs.length;i++) {
			imgs[i] = loadImage("SnowPea"+i+".png");
		}
	}
	SnowPea(int width, int height) {
		super(width, height);
		 live =5 ;
	}
	int index = 1;
	public BufferedImage getImage() {
		if(isWait()||isMove()) {
			return imgs[5];
		}else if(isLife()) {
			return imgs[index++%5];//0-4
		}else {
			return null;
		}
	}

	@Override
	public Bullet[] shoot() {
		return null;
	}

	

}
