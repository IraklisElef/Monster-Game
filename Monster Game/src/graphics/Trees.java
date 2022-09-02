package graphics;
import java.awt.image.BufferedImage;

public class Trees extends GraphicsObject {

	public Trees(int x, int y, float z, int type, BufferedImage sprite_var) {
		
		super(x, y, z, type, sprite_var);

		sprite = ss.grabImage(0, 0, 125, 225);
		sprite = resize(sprite, (int)(125 * z), (int) (225 * z));
		
		this.width = sprite.getWidth();
		
	}
	
}
