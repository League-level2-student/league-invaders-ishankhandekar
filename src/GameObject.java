import java.awt.Rectangle;

public class GameObject {
	int x;
	 int y;
	 int width;
	 int height;
	 boolean isActive = true;
	 Rectangle collisionBox;
	 public GameObject(int x, int y, int width, int height) {
		 this.y = y;
		 this.x = x;
		 this.width = width;
		 this.height = height;
		 collisionBox = new Rectangle(x,y,width,height);
		 
	 }
	 void update() {
		 collisionBox.setBounds(x, y, width, height);
	 }
}
