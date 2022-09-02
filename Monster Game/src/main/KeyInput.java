package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key) {
		
			case KeyEvent.VK_ESCAPE:
				if(Game.gameState == STATE.Menu || Game.gameState == STATE.Options)
					Game.gameState = STATE.Game;
				else
					Game.gameState = STATE.Menu;
					
		}
		
	}
	
}
