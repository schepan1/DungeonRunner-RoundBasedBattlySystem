package dungeonRunner.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class dungeonRunnerTransmission {
	
	public dungeonRunnerTransmission(Graphics g){
		fade(g);
	}
	
	public void fade(Graphics g){
		g.setColor(new Color(1f,1f,1f,0.5f));
		g.fillRect(0, 0, 1000, 1000);
	}

}
