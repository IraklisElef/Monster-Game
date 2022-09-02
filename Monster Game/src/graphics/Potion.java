package graphics;

import java.awt.image.BufferedImage;

public class Potion extends GraphicsObject {

	public Potion(int x, int y, float z, int type, BufferedImage sprite_var) {
		super(x, y, z, type, sprite_var);
		
		sprite = ss.grabImage(0, 0, 16, 23);
		sprite = resize(sprite, (int)(16 * z), (int) (23 * z));
		
		this.width = sprite.getWidth();
		
	}

}
