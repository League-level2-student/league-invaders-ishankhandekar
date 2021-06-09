import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	
	Rocketship rocket;
	ArrayList <Projectile> projectiles;
	ArrayList <Alien> aliens ;
	Random random;
	public ObjectManager(Rocketship rocket) {
		this.rocket = rocket;
		random = new Random();
		aliens = new ArrayList <Alien>();
		projectiles = new ArrayList <Projectile>();
	}
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	public void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	public void update() {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
			
			if (projectiles.get(i).y <= 0) {
				projectiles.get(i).isActive = false;
			}
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).y >= LeagueInvaders.HEIGHT) {
			aliens.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
		
	}
	public void draw(Graphics g) {
		rocket.draw(g);
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
	}
	public void purgeObjects() {
		
		for (int i = aliens.size()-1; i >= 0; i--) {
			if (projectiles.get(i).isActive == false) {
				projectiles.remove(i);
			}
		}
		
		for (int i = aliens.size()-1; i >= 0; i--) {
			if (aliens.get(i).isActive == false) {
			aliens.remove(i);
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAlien();
	}
	public void checkCollision() {
		
		for (int i = 0; i < aliens.size(); i++) {
			if (rocket.collisionBox.intersects(aliens.get(i)
					.collisionBox)) {
				aliens.get(i).isActive = false;
				rocket.isActive = false;
			}
		}
		
		for (int i = 0; i < aliens.size(); i++) {
			for (int j = 0; j < projectiles.size(); j++) {
				if (aliens.get(i).collisionBox.intersects(projectiles.get(j)
						.collisionBox)) {
					aliens.get(i).isActive = false;
					projectiles.get(j).isActive = false;
				}
			}
			
		}
		
		
	}
	
}
	

