import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame j;
	static GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;

	public static void main(String[] args) {
		LeagueInvaders LI = new LeagueInvaders();
		LI.setup();
		LI.j.add(panel);
	}

	public LeagueInvaders() {
		this.j = new JFrame();
		panel = new GamePanel();
		j.addKeyListener(panel);
		
	}
	
	public void setup() {
		j.setSize(WIDTH, HEIGHT);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
