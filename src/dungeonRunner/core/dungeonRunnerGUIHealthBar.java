package dungeonRunner.core;


import org.newdawn.slick.Graphics;

import dungeonRunner.GameObjects.Player;

public class dungeonRunnerGUIHealthBar {
	
	
	public void initBars(Graphics g){
	g.setColor(org.newdawn.slick.Color.darkGray);
	g.drawRect(20, 565, Player.maxHealth, 10);
	g.setColor(org.newdawn.slick.Color.green);
	g.setColor(org.newdawn.slick.Color.white);
	g.drawString(Player.currentHealth.toString()+" / "+ Player.maxHealth.toString(),50, 580);
	g.setColor(org.newdawn.slick.Color.green);
	g.fillRect(20, 565, Player.currentHealth, 10);
	}
	
	
	
}
