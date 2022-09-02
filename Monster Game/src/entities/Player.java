package entities;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;
import main.STATE;

public class Player extends GameEntity {
	
	boolean playerTurn = true;
	public static int timer = 100;
	
	public Player(int x, int y, float z, String name, int health, int healthPotions, int damage, int criticalChance) {
		super(x, y, z, name, health, healthPotions, damage, criticalChance);
	}

	public void tick() {
		
		if(!isDead())
			updateHealth();
		
		if(isIdle) {
			
			if(isDamaged)
				delayDamaged--;
			else
				delayIdle--;
			
		}
		else
			delayAttack--;
		
		if(isDead()) {
			
			if(delayDeath != 1)
				delayDeath--;
			
		}
		
		if(wonGame() || health <= 0)
			timer--;
		
		if(timer == 0)
			Game.gameState = STATE.End;
		
	}

	public void render(Graphics g) {
		
		if(!isDead()) {
			
			renderHealth(125, 30, g);
			
			if(isIdle) {
				
				if(isDamaged)
					renderObjectDamaged(g);
				else
					renderObjectIdle(g);
			
			}	
			else
				renderObjectAttack(g);
			
		}
		else
			renderObjectDeath(g);

	}
	
	public void renderObjectIdle(Graphics g) {
		
		if(hasShadows) {
			
			if(delayIdle > 25)
				g.drawImage(idle[0], x, y, null);
			else if(delayIdle > 20)
				g.drawImage(idle[1], x, y, null);
			else if(delayIdle > 15)
				g.drawImage(idle[2], x, y, null);	
			else if(delayIdle > 10)
				g.drawImage(idle[3], x, y, null);
			else if(delayIdle > 5)
				g.drawImage(idle[4], x, y, null);
			else if(delayIdle > 0)
				g.drawImage(idle[5], x, y, null);
	
		}
		else {
			
			if(delayIdle > 25)
				g.drawImage(idle_no_shadows[0], x, y, null);
			else if(delayIdle > 20)
				g.drawImage(idle_no_shadows[1], x, y, null);
			else if(delayIdle > 15)
				g.drawImage(idle_no_shadows[2], x, y, null);	
			else if(delayIdle > 10)
				g.drawImage(idle_no_shadows[3], x, y, null);
			else if(delayIdle > 5)
				g.drawImage(idle_no_shadows[4], x, y, null);
			else if(delayIdle > 0)
				g.drawImage(idle_no_shadows[5], x, y, null);
			
		}
		
		if(delayIdle <= 1)
			delayIdle = originalDelayIdle;
		
	}

	public void renderObjectAttack(Graphics g) {

		if(hasShadows) {
			
			if(delayAttack > 25)
				g.drawImage(attack[0], x, y, null);
			else if(delayAttack > 20)
				g.drawImage(attack[1], x, y, null);
			else if(delayAttack > 0)
				g.drawImage(attack[2], x, y, null);	

		}
		else {
			
			if(delayAttack > 25)
				g.drawImage(attack_no_shadows[0], x, y, null);
			else if(delayAttack > 20)
				g.drawImage(attack_no_shadows[1], x, y, null);
			else if(delayAttack > 0)
				g.drawImage(attack_no_shadows[2], x, y, null);	

		}
		
		if(delayAttack <= 1) {
			
			if(!wonGame()) {
				
				int nextTurn;
				boolean isDead;
				
				do{
					
					isDead = false;
					nextTurn = rand.nextInt(3) + 1;
					
					if(nextTurn == 1 && Game.wizard.isDead())
						isDead = true;
					if(nextTurn == 2 && Game.warrior.isDead())
						isDead = true;
					if(nextTurn == 3 && Game.assassin.isDead())
						isDead = true;
					
				}while(isDead);
				
				switch(nextTurn) {
				
					case 1:
						Game.wizard.isIdle = false;
						break;
					case 2:
						Game.warrior.isIdle = false;
						break;
					case 3:
						Game.assassin.isIdle = false;
						break;
				
				}
				
			}
			
			delayAttack = originalDelayAttack;
			isIdle = true;
			
			x = originalX;
			y = originalY;

		}
		
	}
	
	public void renderObjectDamaged(Graphics g) {
		
		if(hasShadows) {
			
			if(delayDamaged > 0)
				g.drawImage(damaged[0], x, y, null);
			
		}
		else {
			
			if(delayDamaged > 0)
				g.drawImage(damaged_no_shadows[0], x, y, null);
			
		}
		
		if(delayDamaged <= 1)
			delayDamaged = originalDelayDamaged;
		
	}

	public void renderObjectDeath(Graphics g) {
		
		if(hasShadows) {
			
			if(delayDeath > 60)
				g.drawImage(death[0], x, y, null);
			else if(delayDeath > 50)
				g.drawImage(death[1], x, y, null);
			else if(delayDeath > 40)
				g.drawImage(death[2], x, y, null);	
			else if(delayDeath > 30)
				g.drawImage(death[3], x, y, null);
			else if(delayDeath > 20)
				g.drawImage(death[4], x, y, null);
			else if(delayDeath > 10)
				g.drawImage(death[5], x, y, null);
			else if(delayDeath > 0)
				g.drawImage(death[6], x, y, null);
	
		}
		else {
			
			if(delayDeath > 60)
				g.drawImage(death_no_shadows[0], x, y, null);
			else if(delayDeath > 50)
				g.drawImage(death_no_shadows[1], x, y, null);
			else if(delayDeath > 40)
				g.drawImage(death_no_shadows[2], x, y, null);	
			else if(delayDeath > 30)
				g.drawImage(death_no_shadows[3], x, y, null);
			else if(delayDeath > 20)
				g.drawImage(death_no_shadows[4], x, y, null);
			else if(delayDeath > 10)
				g.drawImage(death_no_shadows[5], x, y, null);
			else if(delayDeath > 0)
				g.drawImage(death_no_shadows[6], x, y, null);
			
		}

	}
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		
		if(mx > x && mx < x + width) {
			 if(my > y && my < y + height)
				 return true;
			 
			 return false;
		}
		
		return false;
		
	}
	
	private void toggleShadows() {
		
		hasShadows = !hasShadows;
		Game.wizard.setShadows(!Game.wizard.getShadows());
		Game.warrior.setShadows(!Game.warrior.getShadows());
		Game.assassin.setShadows(!Game.assassin.getShadows());
		
	}
	
	private void restart() {
		
		setHealth(originalHealth);
		Game.wizard.setHealth(Game.wizard.originalHealth);
		Game.warrior.setHealth(Game.warrior.originalHealth);
		Game.assassin.setHealth(Game.assassin.originalHealth);
		
		setHealthPotions(originalHealthPotions);
		Game.wizard.setHealthPotions(Game.wizard.originalHealthPotions);
		Game.warrior.setHealthPotions(Game.warrior.originalHealthPotions);
		Game.assassin.setHealthPotions(Game.assassin.originalHealthPotions);
		
		delayDeath = 60;
		Game.wizard.delayDeath = 60;
		Game.warrior.delayDeath = 60;
		Game.assassin.delayDeath = 60;
		
		timer = 100;
		playerTurn = true;
		
		Game.gameState = STATE.Game;
		
	}
	
	public static boolean wonGame() {
		
		int i = 0;
		
		if(Game.wizard.isDead())
			i++;
		if(Game.warrior.isDead())
			i++;
		if(Game.assassin.isDead())
			i++;
		
		if(i == 3)
			return true;
		
		return false;
		
	}
	
	private void attackWizard(int mx, int my) {
		
		if((mouseOver(mx, my, Game.wizard.getHealthX(), Game.wizard.getHealthY(), Game.wizard.getHealthWidth(), Game.wizard.getHealthHeight())) && Game.wizard.getHealth() != 0 && !Game.wizard.isDead() && playerTurn && !isDead()) {
			
			int currentDamage = isCritical() ? damage * 2 : damage;
			
			x = Game.wizard.getX() - 100;
			y = Game.wizard.getY() + 50;
		
			Game.wizard.setHealth(Game.wizard.getHealth() - currentDamage);
			
			if(Game.wizard.isDead()) {
				
				int getPotion = rand.nextInt(2);
				
				if(getPotion == 1)
					healthPotions++;
				
			}
			
			isIdle = false;
			playerTurn = false;
			
			Game.wizard.isDamaged = true;
			
		}
		
	}
	
	private void attackWarrior(int mx, int my) {
		
		if((mouseOver(mx, my, Game.warrior.getHealthX(), Game.warrior.getHealthY(), Game.warrior.getHealthWidth(), Game.warrior.getHealthHeight())) && Game.warrior.getHealth() != 0 && !Game.warrior.isDead() && playerTurn && !isDead()) {
			
			int currentDamage = isCritical() ? damage * 2 : damage;
			
			x = Game.warrior.getX() - 50;
			y = Game.warrior.getY() + 50;
			
			Game.warrior.setHealth(Game.warrior.getHealth() - currentDamage);

			if(Game.warrior.isDead()) {
				
				int getPotion = rand.nextInt(2);
				
				if(getPotion == 1)
					healthPotions++;
				
			}
			
			isIdle = false;
			playerTurn = false;
			
			Game.warrior.isDamaged = true;
			
		}
		
	}
	
	private void attackAssassin(int mx, int my) {
		
		if((mouseOver(mx, my, Game.assassin.getHealthX(), Game.assassin.getHealthY(), Game.assassin.getHealthWidth(), Game.assassin.getHealthHeight())) && Game.assassin.getHealth() != 0 && !Game.assassin.isDead() && playerTurn && !isDead()) {
			
			int currentDamage = isCritical() ? damage * 2 : damage;
			
			x = Game.assassin.getX() - 150;
			y = Game.assassin.getY();
			
			Game.assassin.setHealth(Game.assassin.getHealth() - currentDamage);

			if(Game.assassin.isDead()) {
				
				int getPotion = rand.nextInt(2);
				
				if(getPotion == 1)
					healthPotions++;
				
			}
			
			isIdle = false;
			playerTurn = false;
			
			Game.assassin.isDamaged = true;
			
		}
		
	}
	
	private void drinkPotion(int mx, int my) {
		
		if(mouseOver(mx, my, getHealthX() + originalHealth / 2 - 20, getHealthY() - 55, 24, 35)) {
			
			if(health != 100 && healthPotions >= 1 && playerTurn && !isDead()) {
				
				health += 50;
				healthPotions--;
				
			}
			
		}
		
	}
	
	private void menuButtons(int mx, int my) {
		
		if(Game.gameState == STATE.Menu) {
				
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 200, 200, 100))
				Game.gameState = STATE.Game;
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 50, 200, 100))
				Game.gameState = STATE.Options;

			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 100, 200, 100))
				System.exit(1);
			
		}
			
	}
	
	private void optionButtons(int mx, int my) {
		
		if(Game.gameState == STATE.Options) {
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 300, 200, 100))
				toggleShadows();
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 150, 200, 100))
				Game.musicOn = !Game.musicOn;
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2, 200, 100))
				Game.soundsOn = !Game.soundsOn;
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 150, 200, 100))
				Game.gameState = STATE.Menu;
			
		}
		
	}
	
	private void lostButtons(int mx, int my) {
		
		if(Game.gameState == STATE.End) {
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 150, 200, 100))
				restart();
			
			if(mouseOver(mx, my, Game.WIDTH / 2 - 100, Game.HEIGHT / 2, 200, 100))
				System.exit(1);
			
		}
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		attackWizard(mx, my);
		attackWarrior(mx, my);
		attackAssassin(mx, my);
		drinkPotion(mx, my);
		
		menuButtons(mx, my);
		optionButtons(mx, my);
		lostButtons(mx, my);
		
	}

}
