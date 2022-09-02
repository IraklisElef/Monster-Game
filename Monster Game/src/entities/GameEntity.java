package entities;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.Random;

import graphics.Potion;
import imageLoader.SpriteSheet;
import main.Game;

public abstract class GameEntity extends MouseAdapter {
	
	protected static Random rand = new Random();
	
	private SpriteSheet idle_sprite;
	private SpriteSheet idle_no_shadows_sprite;
	
	private SpriteSheet attack_sprite;
	private SpriteSheet attack_no_shadows_sprite;
	
	private SpriteSheet damaged_sprite;
	private SpriteSheet damaged_no_shadows_sprite;
	
	private SpriteSheet death_sprite;
	private SpriteSheet death_no_shadows_sprite;
	
	protected int x, y;
	protected int originalX, originalY;
	protected int healthX, healthY, healthWidth, healthHeight;
	protected int health;
	protected int originalHealth;
	protected int healthPotions;
	protected int originalHealthPotions;
	protected int damage;
	protected int criticalChance;
	protected int entityWidthIdle, entityHeightIdle;
	
	protected int delayIdle;
	protected int originalDelayIdle;
	
	protected int delayAttack;
	protected int originalDelayAttack;
	
	protected int delayDamaged;
	protected int originalDelayDamaged;
	
	protected int delayDeath;
	protected int originalDelayDeath;
	
	protected int greenValue = 255;
	
	protected boolean isIdle = true;
	protected boolean isDamaged = false;
	protected boolean hasShadows = true;
	
	protected float z;
	
	protected String name;
	
	protected BufferedImage[] idle;
	protected BufferedImage[] idle_no_shadows;
	
	protected BufferedImage[] attack;
	protected BufferedImage[] attack_no_shadows;

	protected BufferedImage[] damaged;
	protected BufferedImage[] damaged_no_shadows;
	
	protected BufferedImage[] death;
	protected BufferedImage[] death_no_shadows;
	
	//Object constructor
	public GameEntity(int x, int y, float z, String name, int health, int healthPotions, int damage, int criticalChance) {
		
		this.x = x;
		this.originalX = x;
		this.y = y;
		this.originalY = y;
		this.z = z;
		this.name = name;
		this.health = health;
		this.originalHealth = health;
		this.healthPotions = healthPotions;
		this.originalHealthPotions = healthPotions;
		this.damage = damage;
		this.criticalChance = criticalChance;
		
	}
	
	//Initialize idle sprite sheet
	public void initIdleSheet(BufferedImage idle_sprite, BufferedImage idle_no_shadows_sprite, int frames, int entityWidthIdle, int entityHeightIdle, int delayIdle) {
		
		this.idle_sprite = new SpriteSheet(idle_sprite);
		this.idle_no_shadows_sprite = new SpriteSheet(idle_no_shadows_sprite);
		this.delayIdle = delayIdle;
		this.originalDelayIdle = delayIdle;
		
		initIdleLength(frames);
		
		initIdleFrames(entityWidthIdle, entityHeightIdle);
		
	}
	
	//Initialize attack sprite sheet
	public void initAttackSheet(BufferedImage attack_sprite, BufferedImage attack_no_shadows_sprite, int frames, int entityWidthAttack, int entityHeightAttack, int delayAttack) {
		
		this.attack_sprite = new SpriteSheet(attack_sprite);
		this.attack_no_shadows_sprite = new SpriteSheet(attack_no_shadows_sprite);
		this.delayAttack = delayAttack;
		this.originalDelayAttack = delayAttack;
		
		initAttackLength(frames);
		
		initAttackFrames(entityWidthAttack, entityHeightAttack);
		
	}
	
	//Initialize damaged sprite sheet
	public void initDamagedSheet(BufferedImage damaged_sprite, BufferedImage damaged_no_shadows_sprite, int frames, int entityWidthDamaged, int entityHeightDamaged, int delayDamaged) {
		
		this.damaged_sprite = new SpriteSheet(damaged_sprite);
		this.damaged_no_shadows_sprite = new SpriteSheet(damaged_no_shadows_sprite);
		this.delayDamaged = delayDamaged;
		this.originalDelayDamaged = delayDamaged;
		
		initDamagedLength(frames);
		
		initDamagedFrames(entityWidthDamaged, entityHeightDamaged);
		
	}
	
	//Initialize death sprite sheet
	public void initDeathSheet(BufferedImage death_sprite, BufferedImage death_no_shadows_sprite, int frames, int entityWidthDeath, int entityHeightDeath, int delayDeath) {
		
		this.death_sprite = new SpriteSheet(death_sprite);
		this.death_no_shadows_sprite = new SpriteSheet(death_no_shadows_sprite);
		this.delayDeath = delayDeath;
		this.originalDelayDeath = delayDeath;
		
		initDeathLength(frames);
		
		initDeathFrames(entityWidthDeath, entityHeightDeath);
		
	}
	
	//Initialize idle buffered image length
	private void initIdleLength(int frames) {
		
		this.idle = new BufferedImage[frames];
		this.idle_no_shadows = new BufferedImage[frames];
		
	}

	//Initialize attack buffered image length
	private void initAttackLength(int frames) {
		
		this.attack = new BufferedImage[frames];
		this.attack_no_shadows = new BufferedImage[frames];
		
	}
	
	//Initialize damaged buffered image length
	private void initDamagedLength(int frames) {
		
		this.damaged = new BufferedImage[frames];
		this.damaged_no_shadows = new BufferedImage[frames];
		
	}
	
	//Initialize death buffered image length
	private void initDeathLength(int frames) {
		
		this.death = new BufferedImage[frames];
		this.death_no_shadows = new BufferedImage[frames];
		
	}
	
	private void initIdleFrames(int entityWidth, int entityHeight) {
		
		//With shadows
		int x = 0;
		for(int i = 0; i < this.idle.length; i++) {
				
			this.idle[i] = this.idle_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.idle[i] = resize(this.idle[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
		//Without shadows
		x = 0;
		for(int i = 0; i < this.idle_no_shadows.length; i++) {
				
			this.idle_no_shadows[i] = this.idle_no_shadows_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.idle_no_shadows[i] = resize(this.idle_no_shadows[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
	}
	
	private void initAttackFrames(int entityWidth, int entityHeight) {
		
		//With shadows
		int x = 0;
		for(int i = 0; i < this.attack.length; i++) {
				
			this.attack[i] = this.attack_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.attack[i] = resize(this.attack[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
		//Without shadows
		x = 0;
		for(int i = 0; i < this.attack_no_shadows.length; i++) {
				
			this.attack_no_shadows[i] = this.attack_no_shadows_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.attack_no_shadows[i] = resize(this.attack_no_shadows[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
	}
	
	private void initDamagedFrames(int entityWidth, int entityHeight) {
		
		//With shadows
		int x = 0;
		for(int i = 0; i < this.damaged.length; i++) {
				
			this.damaged[i] = this.damaged_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.damaged[i] = resize(this.damaged[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
		//Without shadows
		x = 0;
		for(int i = 0; i < this.damaged_no_shadows.length; i++) {
				
			this.damaged_no_shadows[i] = this.damaged_no_shadows_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.damaged_no_shadows[i] = resize(this.damaged_no_shadows[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
	}	
	
	private void initDeathFrames(int entityWidth, int entityHeight) {
		
		//With shadows
		int x = 0;
		for(int i = 0; i < this.death.length; i++) {
				
			this.death[i] = this.death_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.death[i] = resize(this.death[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
		//Without shadows
		x = 0;
		for(int i = 0; i < this.death_no_shadows.length; i++) {
				
			this.death_no_shadows[i] = this.death_no_shadows_sprite.grabImage(x, 0, entityWidth, entityHeight);
			this.death_no_shadows[i] = resize(this.death_no_shadows[i], (int) (entityWidth * z), (int) (entityHeight * z));
			x += entityWidth;
			
		}
		
	}
	
	//Update object's health
	public void updateHealth() {
		
		health = clamp(health, 0, 100);
		
		greenValue = health * 2;
		greenValue = clamp(greenValue, 0, 255);
		
	}
	
	//Render object's health
	public void renderHealth(int x1, int y1, Graphics g) {
		
		Potion potion = new Potion(x + x1 + originalHealth / 2 - 20, y - y1 - 55, 1.5f, 0, Game.potion);
		
		Font font = new Font("Arial", 1, 15);
		
		int nameWidth = g.getFontMetrics(font).stringWidth(name);
		
		g.setColor(Color.GRAY);
		g.fillRect(x + x1, y - y1, originalHealth, 10);
		g.setColor(new Color(75, (int)greenValue, 0));
		g.fillRect(x + x1, y - y1, health, 10);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", 1, 20));
		
		int tempX = x + x1 + (originalHealth - nameWidth) / 4;
		g.drawString(name, tempX, y - y1 - 5);
		
		if(healthPotions >= 1) {
			
			potion.drawObject(g);
			g.drawString("x" + Integer.toString(healthPotions), x + x1 + originalHealth / 2 + 5, y - y1 - 22);
			
		}
		
		healthX = x + x1;
		healthY = y - y1;
		healthWidth = originalHealth;
		healthHeight = 10;
		
	}
	
	//Resize buffered image
	public BufferedImage resize(BufferedImage image, int newW, int newH) { 
		
		int type = 0;
        type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(newW, newH, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newW, newH, null);
        g.dispose();
        
        return resizedImage;
	    
	}
	
	//Resize all frames of object
	public void resizeEntity(BufferedImage[] entity, int entityWidth, int entityHeight) {
		
		for(int i = 0; i < entity.length; i++) 
			entity[i] = resize(entity[i], (int) (entityWidth * z), (int) (entityHeight * z));
		
	}
	
	//Clamp an object in certain coordinates
	public static int clamp(int var, int min, int max) {
		
		if(var <= min)
			return var = min;
		else if(var >= max)
			return var = max;
		else
			return var;
		
	}
	
	//Critical damage chance
	public boolean isCritical() {
		
		if((rand.nextInt(100) - 1) >= criticalChance)
			return true;
			
		return false;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void renderObjectIdle(Graphics g);
	public abstract void renderObjectAttack(Graphics g);
	public abstract void renderObjectDamaged(Graphics g);
	public abstract void renderObjectDeath(Graphics g);	
	
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

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getCriticalChance() {
		return criticalChance;
	}
	
	public void setCriticalChance(int criticalChance) {
		this.criticalChance = criticalChance;
	}

	public boolean isDead() {
		
		if(health <= 0)
			return true;
		return false;
		
	}

	public boolean getShadows() {
		return hasShadows;
	}

	public void setShadows(boolean hasShadows) {
		this.hasShadows = hasShadows;
	}

	public int getHealthX() {
		return healthX;
	}

	public void setHealthX(int healthX) {
		this.healthX = healthX;
	}

	public int getHealthY() {
		return healthY;
	}

	public void setHealthY(int healthY) {
		this.healthY = healthY;
	}

	public int getHealthWidth() {
		return healthWidth;
	}

	public void setHealthWidth(int healthWidth) {
		this.healthWidth = healthWidth;
	}

	public int getHealthHeight() {
		return healthHeight;
	}

	public void setHealthHeight(int healthHeight) {
		this.healthHeight = healthHeight;
	}

	public int getHealthPotions() {
		return healthPotions;
	}

	public void setHealthPotions(int healhPotions) {
		this.healthPotions = healhPotions;
	}
	
}
