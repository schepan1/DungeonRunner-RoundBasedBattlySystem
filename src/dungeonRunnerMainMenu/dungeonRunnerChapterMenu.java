package dungeonRunnerMainMenu;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.util.dungeonRunnerStates;

public class dungeonRunnerChapterMenu {
	
	
	private String[] menuPoints = {"Chapter 01: Cave of Sands"};
	private int menuPointer = 0;
	
	private int menuPointerGraphic = 78;
	public Music menuMusic;
	private Sound upDown;
	private Sound confirm;
	
	public static boolean menuMusicBool;

	
	public dungeonRunnerChapterMenu(){
		
		
		
		try {
			upDown = new Sound("res/sounds/menu/menuSound+-.wav");
			confirm = new Sound("res/sounds/menu/menuConfirm.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Zeichnet das Graphic Menü
	 */
	
	public void initMenuGraphic(Graphics g){

		g.setBackground(org.newdawn.slick.Color.white);
		g.setColor(org.newdawn.slick.Color.black);
				
		g.drawString(menuPoints[0], 20, 70);
		g.setColor(Color.green.darker());
		g.drawString("Easy", 320, 70);
		g.setColor(Color.black);
		g.setLineWidth(2);
		g.drawLine(400, 15,  400, 430);
		g.drawRect(10, 435, 780, 155);
				
		
		/**
		 * Bei neuem Menüpunkt -------HIER EINTRAGEN---------
		 */
		if (menuPointer == 0) {
			g.drawOval(9, menuPointerGraphic, 3, 3);
		}
		
		if (menuPointer == 1) {
			g.drawOval(9, menuPointerGraphic, 3, 3);
		}
	}
	
	
	/**
	 * 
	 * Logik des Menüs
	 * 
	 * 
	 * 
	 * @param gc
	 * @param delta
	 */
	public void menuControl(GameContainer gc, int delta){
		
		if(menuMusicBool == true){
		playMenuMusic();
		}
		
		/**
		 * Änderung des MenüPunkts für Key_DOWN
		 */
		if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_DOWN)) {
			if(menuPointer == menuPoints.length-1){
				menuPointer = 0; 					// Änderung des Menüpunktes
				menuPointerGraphic = 78;				//Änderung des Pointers(Graphic)
				upDown.play(1,0.5f);					// Sound der Abgespielt wird.
				System.out.println("Current Menu Point " + menuPointer);
			}
			else{
			menuPointer += 1;		// Änderung des Menüpunktes
			menuPointerGraphic += 20;		//Änderung des Pointers(Graphic)
			upDown.play(1,0.5f);			// Sound der Abgespielt wird.
			System.out.println("Current Menu Point " + menuPointer);
			}
		} 
		
		/**
		 * Änderung des MenüPunkts für Key_UP
		 */
		if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_UP)) {
			if(menuPointer == 0){
				menuPointer = menuPoints.length-1;			// Änderung des Menüpunktes
				menuPointerGraphic = 78;					//Änderung des Pointers(Graphic)
				upDown.play(1,0.5f);							// Sound der Abgespielt wird.
				System.out.println("Current Menu Point " + menuPointer);
			}
			else{
			menuPointer -= 1;					// Änderung des Menüpunktes
			menuPointerGraphic -= 20;					//Änderung des Pointers(Graphic)
			upDown.play(1,0.5f);						// Sound der Abgespielt wird.
			System.out.println("Current Menu Point " + menuPointer);
			}
		}
		
		/**
		 * Menüpunkteingabe Bestätigung mit Enter
		 */
			if(menuPoints[menuPointer].equals("Chapter 01: Cave of Sands") &&  dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) ){
				confirm.play(1,0.7f);				// Sound der Abgespielt wird.
				dungeonRunnerEngine.chapter01 = true;
				dungeonRunnerEngine.currentState = dungeonRunnerStates.Play; 
				dungeonRunnerEngine.gameHasBeenStarted = true;
				
			}
			
	}
	
	public void playMenuMusic(){
		menuMusic.loop(0.9f, 0.6f);
	}


}