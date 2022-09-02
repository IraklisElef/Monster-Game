package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import entities.Player;
import main.Game;
import main.STATE;

public class HUD {

	public void tick() {
		
		
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		if(Game.gameState == STATE.Menu)
			renderMenu(g);
		else if(Game.gameState == STATE.End)
			renderEnd(g);
		else if(Game.gameState == STATE.Options)
			renderOptions(g);
		
	}
	
	private void renderMenu(Graphics g) {
		
		int textWidth;
		
		Font font = new Font("Arial", 5, 45);
		
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 200, 200, 100);
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 50, 200, 100);
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 100, 200, 100);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		textWidth = g.getFontMetrics(font).stringWidth("Play");
		g.drawString("Play", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 135);
		
		textWidth = g.getFontMetrics(font).stringWidth("Options");
		g.drawString("Options", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 15);
		
		textWidth = g.getFontMetrics(font).stringWidth("Exit");
		g.drawString("Exit", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 165);
		
	}
	
	private void renderEnd(Graphics g) {
		
		int textWidth;
		
		Font font = new Font("Arial", 5, 45);
		Font bigFont = new Font("Arial", 5, 100);
		
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 150, 200, 100);
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2, 200, 100);
		
		g.setFont(bigFont);
		g.setColor(Color.WHITE);
		
		if(Player.wonGame()) {
			
			textWidth = g.getFontMetrics(bigFont).stringWidth("You won!");
			g.drawString("You won!", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 235);
			
		}
		else {
			
			textWidth = g.getFontMetrics(bigFont).stringWidth("You lost.");
			g.drawString("You lost.", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 235);
			
		}
		
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		textWidth = g.getFontMetrics(font).stringWidth("Restart");
		g.drawString("Restart", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 85);
		
		textWidth = g.getFontMetrics(font).stringWidth("Exit");
		g.drawString("Exit", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 65);
		
	}
	
	private void renderOptions(Graphics g) {
		
		int textWidth;
		
		Font font = new Font("Arial", 5, 45);
		Font smallFont = new Font("Arial", 2, 35);
		
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 300, 200, 100);
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 - 150, 200, 100);
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2, 200, 100);
		g.fillRect(Game.WIDTH / 2 - 100, Game.HEIGHT / 2 + 150, 200, 100);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		textWidth = g.getFontMetrics(font).stringWidth("Shadows");
		g.drawString("Shadows", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 255);
		
		if(Game.player.getShadows()) {
			
			g.setFont(smallFont);
			textWidth = g.getFontMetrics(smallFont).stringWidth("On");
			g.drawString("On", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 215);
			
		}
		else {
			
			g.setFont(smallFont);
			textWidth = g.getFontMetrics(smallFont).stringWidth("Off");
			g.drawString("Off", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 215);
			
		}
		
		g.setFont(font);
		
		textWidth = g.getFontMetrics(font).stringWidth("Music");
		g.drawString("Music", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 105);
		
		if(Game.musicOn) {
			
			g.setFont(smallFont);
			textWidth = g.getFontMetrics(smallFont).stringWidth("On");
			g.drawString("On", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 65);
			
		}
		else {
			
			g.setFont(smallFont);
			textWidth = g.getFontMetrics(smallFont).stringWidth("Off");
			g.drawString("Off", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 - 65);
			
		}
		
		g.setFont(font);
		
		textWidth = g.getFontMetrics(font).stringWidth("Sounds");
		g.drawString("Sounds", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 45);
		
		if(Game.soundsOn) {
			
			g.setFont(smallFont);
			textWidth = g.getFontMetrics(smallFont).stringWidth("On");
			g.drawString("On", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 85);
			
		}
		else {
			
			g.setFont(smallFont);
			textWidth = g.getFontMetrics(smallFont).stringWidth("Off");
			g.drawString("Off", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 85);
			
		}
		
		g.setFont(font);
		
		textWidth = g.getFontMetrics(font).stringWidth("Back");
		g.drawString("Back", Game.WIDTH / 2 - textWidth / 2, Game.HEIGHT / 2 + 212);
		
	}
	
}
