package graphics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import imageLoader.SpriteSheet;

public abstract class GraphicsObject {

	public int x, y, type, width;
	public float z;
	
	public BufferedImage sprite;
	public SpriteSheet ss;

	public GraphicsObject(int x, int y, float z, int type, BufferedImage sprite_var) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		this.ss = new SpriteSheet(sprite_var);
		
	}
	
	public static BufferedImage resize(BufferedImage image, int newW, int newH) { 
		
		int type = 0;
        type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(newW, newH, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newW, newH, null);
        g.dispose();
        
        return resizedImage;
	    
	}
	
	public void drawObject(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
}
