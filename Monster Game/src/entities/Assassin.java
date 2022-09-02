package entities;

import java.awt.Graphics;

import main.Game;

public class Assassin extends GameEntity {
	
	public Assassin(int x, int y, float z, String name, int health, int healthPotions, int damage, int criticalChance) {
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
		
	}

	public void render(Graphics g) {
		
		if(!isDead()) {
			
			renderHealth(115, 30, g);
			
			if(isIdle) {
				
				if(isDamaged && Game.player.delayAttack <= 20)
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
			else if(delayIdle > 0)
				g.drawImage(idle[1], x, y, null);
				
		}
		else {
			
			if(delayIdle > 25)
				g.drawImage(idle_no_shadows[0], x, y, null);
			else if(delayIdle > 0)
				g.drawImage(idle_no_shadows[1], x, y, null);
			
		}
	
		if(delayIdle <= 1)
			delayIdle = originalDelayIdle;
		
	}

	public void renderObjectAttack(Graphics g) {
		
		if(delayAttack == originalDelayAttack - 25) {
			
			x = Game.player.getX() + 150;
			y = Game.player.getY() + 50;
			
		}
		
		if(hasShadows) {
			
			if(delayAttack > 50 && delayAttack <= 75)
				g.drawImage(attack[0], x, y, null);
			else if(delayAttack > 25 && delayAttack <= 50)
				g.drawImage(attack[1], x, y, null);
			else if(delayAttack > 0 && delayAttack <= 25)
				g.drawImage(attack[2], x, y, null);

		}
		else {
			
			if(delayAttack > 50 && delayAttack <= 75)
				g.drawImage(attack_no_shadows[0], x, y, null);
			else if(delayAttack > 25 && delayAttack <= 50)
				g.drawImage(attack_no_shadows[1], x, y, null);
			else if(delayAttack > 0 && delayAttack <= 25)
				g.drawImage(attack_no_shadows[2], x, y, null);	

		}
		
		if(delayAttack <= 1) {
			
			int currentDamage = isCritical() ? damage * 2 : damage;
			
			Game.player.setHealth(Game.player.getHealth() - currentDamage);
			
			delayAttack = originalDelayAttack;
			isIdle = true;
			
			x = originalX;
			y = originalY;

			Game.player.playerTurn = true;
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
		
		if(delayDamaged <= 1) {
			
			if(health >= 1 && health <= 20 && healthPotions >= 1) {
				
				health += 50;
				healthPotions--;
				
			}
			
			delayDamaged = originalDelayDamaged;
			isDamaged = false;
			
		}
		
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

}
