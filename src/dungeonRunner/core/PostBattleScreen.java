package dungeonRunner.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dungeonRunner.util.dungeonRunnerStates;

public class PostBattleScreen {
	
	
	public void renderPostBattle(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.white);
		g.drawString("YOOOOOOOOOU WOOOOOOOOOOOOOOOOOON", 300, 300);
		g.drawString("Press Enter", 300, 320);
	}
	
	public void control(){
		if(dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER)){
				dungeonRunnerEngine.currentBattle = null;
				dungeonRunnerEngine.currentState = dungeonRunnerStates.Play;
		}
	}

}
