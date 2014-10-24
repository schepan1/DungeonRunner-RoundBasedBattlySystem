package dungeonRunner.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import dungeonRunner.util.dungeonRunnerStates;

public class dungeonRunnerSplashScreen {
	
	Image image;
	
	int elapsedTime;
	Sound ssSound;
	final int  delay = 530;
	boolean soundStarted;
	
	public dungeonRunnerSplashScreen() throws SlickException {
		image = new Image("res/splashscreen/images/ss.png");
		ssSound = new Sound("res/splashscreen/sounds/sssound.wav");
	}
	
	public void renderSplash(Graphics g){
		elapsedTime += (int)dungeonRunnerEngine.fdelta;
		
		g.drawImage(image, 0, 0);
		g.setFont(dungeonRunner.util.dungeonRunnerFonts.heroFont);
		g.drawString("{raLaSystems}", 350, 510);
		
		if(!soundStarted){
		ssSound.play(0.9f, 0.3f);
		soundStarted = true;
		}
		
	//	System.out.println(elapsedTime/50);
		

		
		if(elapsedTime >= delay){
			dungeonRunnerEngine.currentState = dungeonRunnerStates.Menu;
		}
		
		
	}
		
	

}
