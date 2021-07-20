import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

 
public class Game extends JPanel{
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 600;
	public static final int START = 0;
	public static final int RUNNING =1;
	public static final int GAME_OVER =2;
	public static int state = RUNNING;
	boolean plantCheck = false;

	
	private Background start = new Background(800,533,300,50);
	private Background running = new Background(WIDTH,HEIGHT,0,0);
	private Background gameOver = new Background(WIDTH,HEIGHT,0,0);

	
	private List<Zombie> zombies = new ArrayList<Zombie>();
	private List<Plant> plants = new ArrayList<Plant>();
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Glass> glasses = new ArrayList<Glass>();
	public int glassX = 260;
	public int glassY = 80;
	public void glassEnterAction() {
		for(int i=0;i<9;i++) {
			int x = glassX + i*Glass.WIDTH;
			for(int j=0;j<5;j++) {
				int y = glassY + j*Glass.HEIGHT;
				glasses.add(new Glass(x,y));
			}
		}
	}
	int zombieEnterTime = 0;
	public void zombieEnterAction() {
		zombieEnterTime++;
		if(zombieEnterTime%30==0) {
			zombies.add(new Zombie1());
		}
	}
	int zombieStepTime = 0;
	public void zombieStepAction() {
		if(zombieStepTime++%3==0) {
			for(Zombie z:zombies) {
				if(z.isLife()) {
					z.step();
				}
			}
		}
	}
	public void paint(Graphics g) {
		if(state==START) {
			start.paintObject(g);
		}else if(state==RUNNING) {
			running.paintObject(g);
		}else if(state==GAME_OVER) {
			gameOver.paintObject(g);
		}
		for(Plant p:plants) {
			p.paintObject(g);
		}
		for(Zombie z:zombies) {
			z.paintObject(g);
		}
	}
	public void play() {
		MouseAdapter l = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int Mx = e.getX();
				int My = e.getY();
				//鼠标坐标
				System.out.println(Mx+","+My);
				if(state == RUNNING) {
					if(Mx>78&&Mx<164&&My>250&&My<333&&!plantCheck) {
						SnowPea sp =new SnowPea (71,71);						
						sp.goMove();						
						plants.add(sp);
						plantCheck = true;
					}else {
						f:for(Plant p:plants) {
							if(p.isMove()) {
								for(Glass g:glasses) {
									int x1 = g.getX();
									int x2 = g.getX()+g.getWidth();
									int y1 = g.getY();
									int y2 = g.getY()+g.getHeight();	
									if(Mx>x1&&Mx<x2&&My>y1&&My<y2&&g.isEmpty()) {
										p.setX(x1);
										p.setY(y1);
										g.goHold();
										p.goLife();
										plantCheck = false;
										break f;
									}
								}			
							}
						}
					}
					
					
				}
				
			}
			public void mouseMoved(MouseEvent e) {
				if(state==RUNNING) {
					for(Plant p:plants) {
						if(p.isMove()) {
							int x = e.getX();
							int y = e.getY();
							p.moveTo(x, y);
							break;
						}
					}
					
				}
			}
	};
	this.addMouseListener(l);
	this.addMouseMotionListener(l);
	
	new Timer().schedule(new TimerTask() {
        @Override
        public void run() {
            try {
            	zombieEnterAction();
            	zombieStepAction();
                repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    },0,100);

	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		Game game= new Game();
		frame.add(game);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 
		
		game.glassEnterAction();
		game.play();
		
		
}
}
