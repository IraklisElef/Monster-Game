package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import main.Game;

public class GameGraphics {

	public LinkedList<Clouds> clouds = new LinkedList<Clouds>();
	public LinkedList<Grass> grass = new LinkedList<Grass>();
	public LinkedList<Trees> trees = new LinkedList<Trees>();
	
	private int holdGrassTime = 25, holdCloudTime = 3;
	
	private boolean grassAnimation = false;
	
	public GameGraphics() {
		
		initClouds();
		initGrass();
		initTrees();
		
	}
	
	private void initClouds() {
		
		clouds.add(new Clouds(60, 50, 0.5f, 1, Game.clouds));
		clouds.add(new Clouds(170, 125, 1f, 2, Game.clouds));
		clouds.add(new Clouds(400, 40, 1.3f, 1, Game.clouds));
		clouds.add(new Clouds(450, 200, 0.8f, 1, Game.clouds));
		clouds.add(new Clouds(700, 125, 0.8f, 2, Game.clouds));
		clouds.add(new Clouds(820, 70, 0.5f, 1, Game.clouds));
		
	}
	private void initGrass() {
		
		grass.add(new Grass(50, 570, 1f, 2, Game.grass));
		grass.add(new Grass(200, 500, 1f, 1, Game.grass));
		grass.add(new Grass(250, 600, 1f, 3, Game.grass));
		grass.add(new Grass(400, 480, 1f, 2, Game.grass));
		grass.add(new Grass(480, 610, 1f, 2, Game.grass));
		grass.add(new Grass(550, 480, 1f, 3, Game.grass));
		grass.add(new Grass(640, 530, 1f, 1, Game.grass));
		grass.add(new Grass(800, 620, 1f, 2, Game.grass));
		grass.add(new Grass(850, 480, 1f, 1, Game.grass));
		
	}
	private void initTrees() {
		
		trees.add(new Trees(75, 250, 1f, 0, Game.tree));
		trees.add(new Trees(750, 190, 1.5f, 0, Game.tree));
		
	}

	public void tick() {
		
		animateGrass();
		animateCloud();
        
	}
	
	public void render(Graphics g) {
	
		drawBackground(g);
		drawBackgroundGrass(g);
		drawGrass(g);
		drawClouds(g);
		drawTrees(g);
		
	}
	
	private void drawBackground(Graphics g) {
		
		g.setColor(new Color(0, 198, 255));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
	}
	
	private void drawBackgroundGrass(Graphics g) {
		
		g.setColor(new Color(37, 214, 66));
		g.fillRect(0, 450, Game.WIDTH, Game.HEIGHT);
		
	}
	
	private void drawGrass(Graphics g) {
		
		for(int i = 0; i < grass.size(); i++)
			grass.get(i).drawObject(g);
		
	}
	
	private void drawClouds(Graphics g) {
		
		for(int i = 0; i < clouds.size(); i++)
			clouds.get(i).drawObject(g);			

	}
	
	private void drawTrees(Graphics g) {
		
		for(int i = 0; i < trees.size(); i++)
			trees.get(i).drawObject(g);	
		
	}
	
	private void animateGrass() {
	
		holdGrassTime--;
		
		if(holdGrassTime == 0 && !grassAnimation) {
			grassAnimation = true;
			
			for(int i = 0; i < grass.size(); i++)
				grass.get(i).setX(grass.get(i).getX() - 3);
		
			holdGrassTime = 25;
		}
	
		if(holdGrassTime == 0 && grassAnimation) {
			grassAnimation = false;
			
			for(int i = 0; i < grass.size(); i++)
				grass.get(i).setX(grass.get(i).getX() + 3);
			
			holdGrassTime = 25;
			
		}
		
	}
	
	private void animateCloud() {
		
		holdCloudTime--;
		
		if(holdCloudTime == 0) {
			
			for(int i = 0; i < clouds.size(); i++) {
				
				Clouds tempObject = clouds.get(i);
				
				tempObject.setX(tempObject.getX() - 1);
				
				if(tempObject.getX() == 0)
					clouds.add(new Clouds(Game.WIDTH, tempObject.getY(), tempObject.getZ(), tempObject.getType(), Game.clouds));
				
				if(tempObject.getX() + tempObject.getWidth() <= 0)
					clouds.remove(i);
				
			}
				
			holdCloudTime = 3;
			
		}
		
	}
	
}
