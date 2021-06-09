import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Rocketship r;
	ObjectManager objManager; 
	Timer alienSpawn;
	Timer frameDraw;
	Font titleFont;
	Font subTitleFont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	GamePanel(){
		if (needImage) {
		    loadImage ("space.png");
		}
		r = new Rocketship(250,700, 50,50);
		objManager = new ObjectManager(r);
		 titleFont = new Font("Arial", Font.PLAIN, 48);
		 subTitleFont = new Font("Arial", Font.PLAIN, 24);
		 frameDraw = new Timer(1000/60,this);
		    frameDraw.start();
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	 void updateMenuState(){}
	 void updateGameState(){
		 objManager.update();
		 if (r.isActive == false) {
			currentState = END;
		}
	 }
	 void updateEndState() {
		 
	 }
	 void drawMenuState(Graphics g) {
		 g.setColor(Color.BLUE);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setColor(Color.YELLOW);
		 g.setFont(titleFont);
		 g.drawString("LEAGUE INVADERS", 25,100);
		 g.setFont(subTitleFont);
		 g.drawString("Press ENTER to start", 150, 400);
		 g.drawString("Press SPACE for instructions", 110, 550);
		 
	 }
	 void drawGameState(Graphics g) {
		
		 
		 if (gotImage) {
				g.drawImage(image, 0, 0 , LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
			} else {
				g.setColor(Color.BLUE);
				g.fillRect(0, 0 , LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
			}

		 objManager.draw(g);
	 }
	 void drawEndState(Graphics g) {
		 g.setColor(Color.RED);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		 g.setColor(Color.WHITE);
		 g.setFont(titleFont);
		 g.drawString("GAME OVER", 100,100);
		 g.setFont(subTitleFont);
		 g.drawString("You killed enemies", 125, 400);
		 g.drawString("Press ENTER to restart", 110, 550);
	 }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    }else if(currentState == MENU) {
		    	currentState++;
		    	startGame();
		    }else if(currentState == GAME) {
		    	alienSpawn.stop();
		    } else {
		        currentState++;
		    }
		
		}
		if (e.getKeyCode()==KeyEvent.VK_UP && currentState == GAME && (r.y < 800 && r.y > 0 )) {
		    r.up();
		}else if (e.getKeyCode()==KeyEvent.VK_DOWN && currentState == GAME && (r.y < 710)) {
			
			r.down();
		}else if (e.getKeyCode()==KeyEvent.VK_RIGHT && currentState == GAME && ( r.x < 435 )) {
			
			r.right();
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT && currentState == GAME && ( r.x > 0 )) {
			
			r.left();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE && currentState == GAME) {
			objManager.addProjectile(r.getProjectile());
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	public void startGame() {
		alienSpawn = new Timer(1000 , objManager);
	    alienSpawn.start();
	}
}
