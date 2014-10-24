package dungeonRunner.GameObjects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.FontUtils;

import dungeonRunner.Enemies.simpleEnemy;
import dungeonRunner.Inventory.Inventory;
import dungeonRunner.Items.Gold;
import dungeonRunner.Items.Item;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.*;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;
import dungeonRunner.util.dungeonRunnerPlayerStates;
import dungeonRunner.util.dungeonRunnerStates;
import dungeonRunnerExceptions.mapInitException;
import dungeonRunnerExceptions.transferException;

public class Player {
	
	public Map currentMap = dungeonRunnerEngine.currentMap;
	public String name;
	
	public static float x = 0;
	public static float y = 0;
	public static Shape hitbox;
	public static dungeonRunnerPlayerLookState ausrichtung;
	public static dungeonRunnerPlayerStates acting;
	public int SIZE = 32;
	public static float schritte;
	
	public static float lastEventX;
	public static float lastEventY;
	public static int lastID;
	
	public Image playerImage;
	public Image playerHead;
	
	public SpriteSheet playerStand;
	public SpriteSheet playerStandLeft;
	public SpriteSheet playerStandRight;
	public SpriteSheet playerStandNorth;
	
	public boolean isMoving;
	
	public SpriteSheet playerSheetRight;
	public Animation playerAnimationRight;
	
	public SpriteSheet playerSheetLeft;
	public Animation playerAnimationLeft;

	public Animation playerAnimationDown;
	public SpriteSheet playerSheetDown;
	
	public Animation playerAnimationUp;
	public SpriteSheet playerSheetUp;
	
	public Animation playerHit;
	public SpriteSheet playerHitSheet;
	
	public int hitAttack;
	public static Integer maxHealth;
	public static Integer currentHealth;
	public int level;
	
	public static Inventory inventory;
	public Sound takeItem;
	public Sound takeGold;
	public Sound basicHitSound;
	
	/**
	  * Kamera
	  *
	  */
	public  Integer offsetMaxX = 0;
	public  Integer offsetMaxY = 0;
	public  Integer offsetMinX = 0;
	public  Integer offsetMinY = 0;
	 
	 public static float camX = 0;
	 public static float camY = 0;
	
	public Player(float x, float y, String pName) throws SlickException{
		
		name = pName;
		playerImage = new Image("res/sprites/enemies/bat/batImage.png");
		hitAttack = 20;
		maxHealth = 100;
		currentHealth   = 100;
		level = 0;
		
		inventory = new Inventory();
		takeItem = new Sound("res/sounds/ingame/actions/itemTaken.wav");
		takeGold = new Sound("res/sounds/ingame/actions/goldTaken.wav");
		basicHitSound = new Sound("res/sounds/battle/player/sword sound.wav");
		
		playerSheetRight = new SpriteSheet("res/sprites/player/redirect.png", 32, 31);
		playerAnimationRight = new Animation(playerSheetRight, 300);

		playerSheetLeft = new SpriteSheet("res/sprites/player/redirectLeft.png", 32, 31);
		playerAnimationLeft = new Animation(playerSheetLeft, 300);
		
		playerSheetDown = new SpriteSheet("res/sprites/player/redirectDown.png", 32, 32);
		playerAnimationDown = new Animation(playerSheetDown, 300);
		
		playerSheetUp = new SpriteSheet("res/sprites/player/redirectUp.png", 32, 32);
		playerAnimationUp = new Animation(playerSheetUp, 300);
		
		playerStand = new SpriteSheet("res/sprites/player/redirectStand.png", 32, 32);
		playerStandLeft = new SpriteSheet("res/sprites/player/redirectStandLeft.png", 32, 32);
		playerStandNorth = new SpriteSheet("res/sprites/player/redirectStandUp.png", 32, 32);
		playerStandRight = new SpriteSheet("res/sprites/player/redirectStandRight.png", 32, 32); 
		
		playerHitSheet = new SpriteSheet("res/sprites/player/playerHit.png", 32, 32);
		playerHit = new Animation(playerHitSheet, 300);


		
		hitbox = new org.newdawn.slick.geom.Rectangle(x, y, 32, 32);
		this.x = x * 32;
		this.y = y * 32;
	}
	
	/**
	 * Logik des Spielers im Bezug auf Transfer, Hitboxen, Bewegung.
	 * @throws SlickException 
	 */
	public void update(int delta) throws transferException{
		
		hitbox.setLocation(x, y);
		currentMap = dungeonRunnerEngine.currentMap; // Immer die Akutelle Map.
		
		
		// TransferProzess
		try{
		if (currentMap.isDoor(Player.x + 32, Player.y + 32 + dungeonRunnerEngine.fdelta)) {
			dungeonRunnerEngine.mapSet.get(currentMap.ID).blocked = null; // Alte Map berechnung wird zurückgesetzt.
			currentMap.transfer.play(0.5f, 0.4f);						  // Transfer Sound
			schritte = 0;
			currentMap = dungeonRunnerEngine.mapSet.get(currentMap.ID+1); //Hier wird nächste Map initalisiert.
			currentMap.init();
			dungeonRunnerEngine.currentMap = this.currentMap;
			Player.x = currentMap.spawnX*32;
			Player.y = currentMap.spawnY*32;
		}
		
		if (currentMap.isBack(Player.x + 32, Player.y + 32 + dungeonRunnerEngine.fdelta)) {
			dungeonRunnerEngine.mapSet.get(currentMap.ID).blocked = null;	// Alte Map berechnung wird zurückgesetzt.
			currentMap.transfer.play(0.5f, 0.4f);							 // Transfer Sound
			schritte = 0;
			currentMap = dungeonRunnerEngine.mapSet.get(currentMap.ID-1);  //Hier wird vorherige Map initalisiert.
			currentMap.init();
			dungeonRunnerEngine.currentMap = this.currentMap;
			Player.x = currentMap.spawnXBack*32;
			Player.y = currentMap.spawnYBack*32;
		}
		}
		catch(mapInitException e){
			e.printStackTrace();
		}
		
		checkIntersection();
		}
	

	
	/**
	 * Rectangle an der Stelle des Player Objektes
	 */
	public void drawPlayer(Graphics g){

		
		drawPlayerName(g);
		
		if(!(dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_A)) && !(dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_D)) && !(dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_W)) && !(dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_S))){
			if(Player.ausrichtung == dungeonRunnerPlayerLookState.South){
				playerStand.draw(Player.x , Player.y);
			}
			if(Player.ausrichtung == dungeonRunnerPlayerLookState.West){
				playerStandLeft.draw(Player.x , Player.y);
			}
			if(Player.ausrichtung == dungeonRunnerPlayerLookState.East){
				playerStandRight.draw(Player.x , Player.y);
			}
			if(Player.ausrichtung == dungeonRunnerPlayerLookState.North){
				playerStandNorth.draw(Player.x , Player.y);
			}
		}
		
		if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_D)){
		playerAnimationRight.draw(Player.x, Player.y);
		}
		if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_A)){
		playerAnimationLeft.draw(Player.x, Player.y);
		}
		if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_S)){
		playerAnimationDown.draw(Player.x, Player.y);
		}
		if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_W)){
		playerAnimationUp.draw(Player.x, Player.y);
		}
		
	
		
	
	}
	
	public void controlPlayer(Graphics g){
		
		
	
		if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_W)) {

			if ( !checkIntersection() && !(currentMap.isBlocked(x, y - dungeonRunnerEngine.fdelta) || currentMap.isBlocked(x + SIZE - 1, y - dungeonRunnerEngine.fdelta))  ) {
				y -= dungeonRunnerEngine.fdelta +3;
				Player.ausrichtung = dungeonRunnerPlayerLookState.North;
				schritte += 0.05f;
			    isMoving = true;
			    dungeonRunnerEngine.inputHandler.clearKeyPressedRecord();
			}
		} else if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_S)) {

			if (!checkIntersection() && !(currentMap.isBlocked(x, y + SIZE + dungeonRunnerEngine.fdelta) || currentMap.isBlocked(x + SIZE - 1, y + SIZE+ dungeonRunnerEngine.fdelta)) ) {

				y += dungeonRunnerEngine.fdelta +3;
				Player.ausrichtung = dungeonRunnerPlayerLookState.South;
				schritte += 0.05f;
				isMoving = true;
				dungeonRunnerEngine.inputHandler.clearKeyPressedRecord();
			}
		} else if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_A)) {

			if (!checkIntersection() && !checkIntersection() && !(currentMap.isBlocked(x - dungeonRunnerEngine.fdelta, y) || currentMap.isBlocked(x - dungeonRunnerEngine.fdelta, y + SIZE - 1)) ) {

				x -= dungeonRunnerEngine.fdelta +3;
				Player.ausrichtung = dungeonRunnerPlayerLookState.West ;
				schritte += 0.05f;
				isMoving = true;
				dungeonRunnerEngine.inputHandler.clearKeyPressedRecord();
			}
		} else if (dungeonRunnerEngine.inputHandler.isKeyDown(Input.KEY_D)) {

			if (!checkIntersection() &&!(currentMap.isBlocked(x + SIZE + dungeonRunnerEngine.fdelta, y) || currentMap.isBlocked(x + SIZE + dungeonRunnerEngine.fdelta, y + SIZE- 1)) ) {

				x += dungeonRunnerEngine.fdelta +3;
				Player.ausrichtung = dungeonRunnerPlayerLookState.East;
				schritte += 0.05f;
				isMoving = true;
				dungeonRunnerEngine.inputHandler.clearKeyPressedRecord();
			}
		}
		
		if(dungeonRunnerEngine.developerMode){
			if(dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_NUMPAD7)){
				if(dungeonRunnerEngine.showHitboxes){
				   dungeonRunnerEngine.showHitboxes = false;
				}
				else{
					dungeonRunnerEngine.showHitboxes = true;
				}
			}
			if(dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_NUMPAD8)){
				if(dungeonRunnerEngine.showMapGrid){
					   dungeonRunnerEngine.showMapGrid = false;
					}
					else{
						dungeonRunnerEngine.showMapGrid = true;
					}
			}
			if(dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_NUMPAD9)){
				if(dungeonRunnerEngine.showInteractionHitBoxes){
					   dungeonRunnerEngine.showInteractionHitBoxes = false;
					}
					else{
						dungeonRunnerEngine.showInteractionHitBoxes = true;
					}
	
			}
		}
			
				
			
	}

	
	public boolean checkIntersection() {
		for (Shape e : currentMap.hitBoxList) {
			if (hitbox.intersects(e)) {
				if (ausrichtung == dungeonRunnerPlayerLookState.North) {
					y += 0.5;
				}
				if (ausrichtung == dungeonRunnerPlayerLookState.East) {
					x -= 0.5;
				}
				if (ausrichtung == dungeonRunnerPlayerLookState.South) {
					y -= 0.5;
				}
				if (ausrichtung == dungeonRunnerPlayerLookState.West) {
					x += 0.5;
				}

				return true;
			}

		}
		return false;

	}
	
	
	

	
	
	public void drawPlayerName(Graphics g){
		FontUtils.drawCenter(dungeonRunnerFonts.heroFont, name, (int)x-2, (int)y-18, 32, Color.blue.brighter().brighter() );
		g.setColor(Color.black);
	}
	
	public void attack(simpleEnemy target){
		target.health -= hitAttack;
//		if(basicHitSound != null){
//			basicHitSound.play();
//		}
	}
	
	
		
	}
