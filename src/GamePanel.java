import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	Timer frameDraw;
	Font titleFont;
	Font subTitleFont;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	GamePanel(){
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
	 void updateGameState(){}
	 void updateEndState() {}
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
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
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
		System.out.println("action");
		repaint();
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		
		}
		if (e.getKeyCode()==KeyEvent.VK_UP && currentState == GAME) {
		    System.out.println("UP");
		}else if (e.getKeyCode()==KeyEvent.VK_DOWN && currentState == GAME) {
			System.out.println("DOWN");
		}else if (e.getKeyCode()==KeyEvent.VK_RIGHT && currentState == GAME) {
			System.out.println("RIGHT");
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT && currentState == GAME) {
			System.out.println("LEFT");
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
}
