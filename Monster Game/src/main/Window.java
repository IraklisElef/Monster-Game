package main;

import javax.swing.JFrame;

public class Window {

	public Window(int width, int height, String title, Game game) {
		
		JFrame jf = new JFrame(title);
		
		jf.setSize(width, height);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(game);
	
		jf.setVisible(true);
		
	}
	
}
