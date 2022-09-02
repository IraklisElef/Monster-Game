package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import entities.Assassin;
import entities.Player;
import entities.Warrior;
import entities.Wizard;
import graphics.GameGraphics;
import graphics.HUD;
import imageLoader.BufferedImageLoader;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 950, HEIGHT = WIDTH / 12 * 9;
	
	private boolean running = false;
	public static boolean musicOn = true, soundsOn = true;
	
	private Thread thread;
	private GameGraphics graphics;
	private HUD hud;
	
	public static Player player;

	public static Wizard wizard;
	public static Warrior warrior;
	public static Assassin assassin;

	public static BufferedImage clouds;
	public static BufferedImage grass;
	public static BufferedImage tree;
	public static BufferedImage potion;
	
	public static BufferedImage player_sprite_idle;
	public static BufferedImage player_sprite_idle_no_shadows;
	public static BufferedImage player_sprite_attack;
	public static BufferedImage player_sprite_attack_no_shadows;
	public static BufferedImage player_sprite_damaged;
	public static BufferedImage player_sprite_damaged_no_shadows;
	public static BufferedImage player_sprite_death;
	public static BufferedImage player_sprite_death_no_shadows;
	
	public static BufferedImage wizard_sprite_idle;
	public static BufferedImage wizard_sprite_idle_no_shadows;
	public static BufferedImage wizard_sprite_attack;
	public static BufferedImage wizard_sprite_attack_no_shadows;
	public static BufferedImage wizard_sprite_damaged;
	public static BufferedImage wizard_sprite_damaged_no_shadows;
	public static BufferedImage wizard_sprite_death;
	public static BufferedImage wizard_sprite_death_no_shadows;

	public static BufferedImage warrior_sprite_idle;
	public static BufferedImage warrior_sprite_idle_no_shadows;
	public static BufferedImage warrior_sprite_attack;
	public static BufferedImage warrior_sprite_attack_no_shadows;
	public static BufferedImage warrior_sprite_damaged;
	public static BufferedImage warrior_sprite_damaged_no_shadows;
	public static BufferedImage warrior_sprite_death;
	public static BufferedImage warrior_sprite_death_no_shadows;

	public static BufferedImage assassin_sprite_idle;
	public static BufferedImage assassin_sprite_idle_no_shadows;
	public static BufferedImage assassin_sprite_attack;
	public static BufferedImage assassin_sprite_attack_no_shadows;
	public static BufferedImage assassin_sprite_damaged;
	public static BufferedImage assassin_sprite_damaged_no_shadows;
	public static BufferedImage assassin_sprite_death;
	public static BufferedImage assassin_sprite_death_no_shadows;
	
	public static STATE gameState = STATE.Menu;
	
	//Game constructor
	public Game() {
		
		loadSprites();
		initObjects();
		
		addMouseListener(player);
		addKeyListener(new KeyInput());
		
		this.start();
	
	}
	
	//Start game
	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	//Stop game
	public synchronized void stop() {
		
		try{
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//Game loop
	public void run() {
		
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now; 
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running)
				render();

			if(System.currentTimeMillis() - timer > 1000)
				timer += 1000;
			
		}
		
		stop();
		
	}
	
	//Tick game
	private void tick() {
		
		graphics.tick();
		
		if(gameState == STATE.Game) {
			
			player.tick();
			wizard.tick();
			warrior.tick();
			assassin.tick();
			
		}
		else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Options)
			hud.tick();
			
		
	}
	
	//Render game
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		graphics.render(g);
		
		if(gameState == STATE.Game) {
			
			wizard.render(g);
			warrior.render(g);
			assassin.render(g);
			player.render(g);
			
		}
		else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Options)
			hud.render(g);
			
		
		g.dispose();
		bs.show();
		
	}
	
	//Load all game sprite
	private void loadSprites() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		clouds = loader.loadImage("/graphics/clouds.png");
		grass = loader.loadImage("/graphics/grass.png");
		tree = loader.loadImage("/graphics/tree.png");
		potion = loader.loadImage("/graphics/potion.png");
		
		player_sprite_idle = loader.loadImage("/entities/player/idle/player_idle.png");
		player_sprite_idle_no_shadows = loader.loadImage("/entities/player/idle/player_idle_no_shadows.png");
		player_sprite_attack = loader.loadImage("/entities/player/attack/player_attack.png");
		player_sprite_attack_no_shadows = loader.loadImage("/entities/player/attack/player_attack_no_shadows.png");
		player_sprite_damaged = loader.loadImage("/entities/player/damaged/player_damaged.png");
		player_sprite_damaged_no_shadows = loader.loadImage("/entities/player/damaged/player_damaged_no_shadows.png");
		player_sprite_death = loader.loadImage("/entities/player/death/player_death.png");
		player_sprite_death_no_shadows = loader.loadImage("/entities/player/death/player_death_no_shadows.png");
		
		wizard_sprite_idle = loader.loadImage("/entities/wizard/idle/wizard_idle.png");
		wizard_sprite_idle_no_shadows = loader.loadImage("/entities/wizard/idle/wizard_idle_no_shadows.png");
		wizard_sprite_attack = loader.loadImage("/entities/wizard/attack/wizard_attack.png");
		wizard_sprite_attack_no_shadows = loader.loadImage("/entities/wizard/attack/wizard_attack_no_shadows.png");
		wizard_sprite_damaged = loader.loadImage("/entities/wizard/damaged/wizard_damaged.png");
		wizard_sprite_damaged_no_shadows = loader.loadImage("/entities/wizard/damaged/wizard_damaged_no_shadows.png");
		wizard_sprite_death = loader.loadImage("/entities/wizard/death/wizard_death.png");
		wizard_sprite_death_no_shadows = loader.loadImage("/entities/wizard/death/wizard_death_no_shadows.png");
		
		warrior_sprite_idle = loader.loadImage("/entities/warrior/idle/warrior_idle.png");
		warrior_sprite_idle_no_shadows = loader.loadImage("/entities/warrior/idle/warrior_idle_no_shadows.png");
		warrior_sprite_attack = loader.loadImage("/entities/warrior/attack/warrior_attack.png");
		warrior_sprite_attack_no_shadows = loader.loadImage("/entities/warrior/attack/warrior_attack_no_shadows.png");
		warrior_sprite_damaged = loader.loadImage("/entities/warrior/damaged/warrior_damaged.png");
		warrior_sprite_damaged_no_shadows = loader.loadImage("/entities/warrior/damaged/warrior_damaged_no_shadows.png");
		warrior_sprite_death = loader.loadImage("/entities/warrior/death/warrior_death.png");
		warrior_sprite_death_no_shadows = loader.loadImage("/entities/warrior/death/warrior_death_no_shadows.png");
		
		assassin_sprite_idle = loader.loadImage("/entities/assassin/idle/assassin_idle.png");
		assassin_sprite_idle_no_shadows = loader.loadImage("/entities/assassin/idle/assassin_idle_no_shadows.png");
		assassin_sprite_attack = loader.loadImage("/entities/assassin/attack/assassin_attack.png");
		assassin_sprite_attack_no_shadows = loader.loadImage("/entities/assassin/attack/assassin_attack_no_shadows.png");
		assassin_sprite_damaged = loader.loadImage("/entities/assassin/damaged/assassin_damaged.png");
		assassin_sprite_damaged_no_shadows = loader.loadImage("/entities/assassin/damaged/assassin_damaged_no_shadows.png");
		assassin_sprite_death =  loader.loadImage("/entities/assassin/death/assassin_death.png");
		assassin_sprite_death_no_shadows = loader.loadImage("/entities/assassin/death/assassin_death_no_shadows.png");
		
	}
	
	//Initialize all game objects
	private void initObjects() {
		
		graphics = new GameGraphics();
		hud = new HUD();
		
		player = new Player(0, 375, 1f, "Hercules", 100, 10, 10, 50);
		player.initIdleSheet(player_sprite_idle, player_sprite_idle_no_shadows, 6, 260, 315, 30);
		player.initAttackSheet(player_sprite_attack, player_sprite_attack_no_shadows, 3, 291, 312, 50);
		player.initDamagedSheet(player_sprite_damaged, player_sprite_damaged_no_shadows, 1, 227, 325, 40);
		player.initDeathSheet(player_sprite_death, player_sprite_death_no_shadows, 7, 254, 331, 70);
		
		wizard = new Wizard(400, 250, 1f, "Wizard", 100, 0, 15, 50);
		wizard.initIdleSheet(wizard_sprite_idle, wizard_sprite_idle_no_shadows, 2, 295, 335, 100);
		wizard.initAttackSheet(wizard_sprite_attack, wizard_sprite_attack_no_shadows, 4, 291, 330, 125);
		wizard.initDamagedSheet(wizard_sprite_damaged, wizard_sprite_damaged_no_shadows, 1, 263, 324, 40);
		wizard.initDeathSheet(wizard_sprite_death, wizard_sprite_death_no_shadows, 7, 346, 442, 70);
		
		warrior = new Warrior(500, 350, 1f, "Warior", 100, 0, 10, 55);
		warrior.initIdleSheet(warrior_sprite_idle, warrior_sprite_idle_no_shadows, 2, 308, 310, 100);
		warrior.initAttackSheet(warrior_sprite_attack, warrior_sprite_attack_no_shadows, 3, 286, 304, 100);
		warrior.initDamagedSheet(warrior_sprite_damaged, warrior_sprite_damaged_no_shadows, 1, 282, 310, 40);
		warrior.initDeathSheet(warrior_sprite_death, warrior_sprite_death_no_shadows, 7, 307, 310, 70);
		
		assassin = new Assassin(720, 450, 0.75f, "Assassin", 80, 0, 15, 55);
		assassin.initIdleSheet(assassin_sprite_idle, assassin_sprite_idle_no_shadows, 2, 261, 339, 100);
		assassin.initAttackSheet(assassin_sprite_attack, assassin_sprite_attack_no_shadows, 3, 262, 339, 100);
		assassin.initDamagedSheet(assassin_sprite_damaged, assassin_sprite_damaged_no_shadows, 1, 315, 336, 40);
		assassin.initDeathSheet(assassin_sprite_death, assassin_sprite_death_no_shadows, 7, 338, 345, 70);
		
		new Window(WIDTH, HEIGHT, "Monster Game", this);
		
	}

	public static void main(String[] args) {
		
		new Game();

	}
	
}
