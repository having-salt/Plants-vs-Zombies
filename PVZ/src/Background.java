

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Background {
	
	
	private int x;
	private int y;
	private int width;
	private int height;
	//背景基本属性
	
	public Background(int width,int height,int x,int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}//构造函数
	
	
	public static BufferedImage loadImage(String fileName) {
		try {
			BufferedImage img = ImageIO.read(Background.class.getResource(fileName));
			return img;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}//读取图片
	
	private static BufferedImage[] image;
	private static BufferedImage[] plantImage;
	static {
		image = new BufferedImage[3];
		image[0] = loadImage("bg0.jpg");
		image[1] = loadImage("bg1.jpg");
		image[2] = loadImage("bg2.jpg");
		plantImage =new BufferedImage[1];
		plantImage[0] = loadImage("SnowPea5.png");
	}//背景照片只加载一次
	
	
	public BufferedImage getImage() {
		if(Game.state==Game.START) {
			return image[0];//开始阶段
		}else if(Game.state==Game.RUNNING) {
			return image[1];//运行阶段
		}else if(Game.state==Game.GAME_OVER) {
			return image[2];//死亡阶段
		}
		return null;
	}
	

	public void paintObject(Graphics g) {
		g.drawImage(getImage(),x,y,null); 
		g.drawImage(plantImage[0],70,220,null);
	}

}
