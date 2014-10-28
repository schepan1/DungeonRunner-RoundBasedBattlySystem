package dungeonRunner.GameObjects;




import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.FontUtils;
import org.newdawn.slick.util.Log;

import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;
import dungeonRunner.util.dungeonRunnerStates;
import dungeonRunner.util.dungeonRunnerTransmission;



public class NPC {
	
	public String name;
	public float x = 0;
	public float y = 0;
	public float moveSpeedX;
	public float moveSpeedY;
	public int maxHealth;
	public int health;
	public int attackDamage;
	public Map currentMap;
	public Shape hitbox;
	public Shape interActionHitbox1;
	public Shape interActionHitbox2;
	public int SIZE = 32;
	public dungeonRunnerPlayerLookState ausrichtung;
	
	public int givesExperience;
	public boolean alive;

	public int groupNumber;
	
	public Image enemyImage;
	public Sound onDie;
	public boolean quest;
	
	public SpriteSheet standSouth;
	public SpriteSheet standNorth;
	public SpriteSheet standEast;
	public SpriteSheet standWest;

	
	public NPC(float x, float y, String pName , float moveSpeedX, float moveSpeedY, Map map) throws SlickException{
		
		name = pName;
		alive = true;
		quest = false;
		currentMap = map;
		ausrichtung = dungeonRunnerPlayerLookState.South;
			
		hitbox = new org.newdawn.slick.geom.Rectangle(x*32, y*32, 32, 32);
		interActionHitbox1 =  new org.newdawn.slick.geom.Rectangle((x*32)-32, (y*32), 64+32, 32); // X Achse
		interActionHitbox2 =  new org.newdawn.slick.geom.Rectangle((x*32), (y*32)-32, 32, 64+32); // Y Achse	
		
		currentMap.hitBoxList.add(this.hitbox);
		currentMap.interActionHitBoxList.add(this.interActionHitbox1);
		currentMap.interActionHitBoxList.add(this.interActionHitbox2);
				
		this.x = x * 32;
		this.y = y * 32;
		
		this.moveSpeedX = moveSpeedX;
		this.moveSpeedY = moveSpeedY;
		
		
	}
	
		
		

	
	public void renderEnemy(Graphics g){
		
		drawNPCName(g, Color.green.brighter());
		
		
		if(this.ausrichtung == dungeonRunnerPlayerLookState.South){
			standSouth.draw(x,y);
		}
		if(this.ausrichtung == dungeonRunnerPlayerLookState.North){
			standNorth.draw(x,y);
		}
		if(this.ausrichtung == dungeonRunnerPlayerLookState.West){
			standWest.draw(x,y);
		}
		if(this.ausrichtung == dungeonRunnerPlayerLookState.East){
			standEast.draw(x,y);
		}
	}
	

	

	
	
	
	public void update(){	
		

 
 		this.hitbox.setLocation(x, y);
 
		//Sorgt bei Kollision von Gegner und Spieler dafür dass der Spieler nicht durch die Wand geschoben wird	
 		
 		 				
 		if(interActionHitbox1.intersects(Player.hitbox) && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || interActionHitbox2.intersects(Player.hitbox) && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) ){
			this.action();
			
			if(interActionHitbox1.intersects(Player.hitbox) && Player.x < x){
				Player.ausrichtung = dungeonRunnerPlayerLookState.East;
				this.ausrichtung = dungeonRunnerPlayerLookState.West;
				}
			if(interActionHitbox1.intersects(Player.hitbox) && Player.x > x){
				Player.ausrichtung = dungeonRunnerPlayerLookState.West;
				this.ausrichtung = dungeonRunnerPlayerLookState.East;
				}
			if(interActionHitbox2.intersects(Player.hitbox) && Player.y > y){
				Player.ausrichtung = dungeonRunnerPlayerLookState.North;
				this.ausrichtung = dungeonRunnerPlayerLookState.South;
				}
			if(interActionHitbox2.intersects(Player.hitbox) && Player.y < y){
				Player.ausrichtung = dungeonRunnerPlayerLookState.South;
				this.ausrichtung = dungeonRunnerPlayerLookState.North;
						
						
				}
			
		}
			
		if(hitbox.intersects(Player.hitbox)){
			if(x < Player.x){
				Player.x +=0.1;
			}
			if(x > Player.x ){
				Player.x -=0.1;
			}
			
			if(y < Player.y){
				Player.y +=0.1;
			}
			if(y > Player.y){			
			    Player.y -=0.1;
			} 
		}
		
//		float f = 1.987678F;
		
		
//		// Sorgt dafür dass die Gegner nicht durch Wände laufen können und dass sich die Gegner auf den Spieler zu bewegen.
//		if((Math.round(x * 100) /100.0f) <= (Math.round(Player.x * 100 ) / 100.0f) && !(currentMap.isBlocked(x + SIZE + dungeonRunnerEngine.fdelta, y) || currentMap.isBlocked(x + SIZE + dungeonRunnerEngine.fdelta, y + SIZE- 1))){
//			x+= moveSpeedX;
//		
//			ausrichtung = dungeonRunnerPlayerLookState.East;
//		}
//		if((Math.round(x * 100) /100.0f) >= (Math.round(Player.x * 100 ) / 100.0f) && !(currentMap.isBlocked(x - dungeonRunnerEngine.fdelta, y) || currentMap.isBlocked(x - dungeonRunnerEngine.fdelta, y + SIZE - 1))){
//			x-= moveSpeedX;
//			ausrichtung = dungeonRunnerPlayerLookState.West;
//		}
//		
//		if((Math.round(y * 100) /100.0f) <= (Math.round(Player.y * 100 ) / 100.0f) && !(currentMap.isBlocked(x, y + SIZE + dungeonRunnerEngine.fdelta) || currentMap.isBlocked(x + SIZE - 1, y + SIZE+ dungeonRunnerEngine.fdelta))){
//			y+= moveSpeedY;
//			ausrichtung = dungeonRunnerPlayerLookState.South;
//		}
//		if((Math.round(y * 100) /100.0f) >= (Math.round(Player.y * 100 ) / 100.0f) && !(currentMap.isBlocked(x, y - dungeonRunnerEngine.fdelta) || currentMap.isBlocked(x + SIZE - 1, y - dungeonRunnerEngine.fdelta)) && x != Player.x){
//			y-= moveSpeedY;
//			ausrichtung = dungeonRunnerPlayerLookState.North;
//		}

	}
	
	public void drawNPCName(Graphics g, Color c){
		FontUtils.drawCenter(dungeonRunnerFonts.enemyFont, name, (int)x-5, (int)y-18, 32, c);
		if(quest){
			FontUtils.drawCenter(dungeonRunnerFonts.bigFont, "?", (int)x, (int)y-40, 32, Color.orange);	
		}
		g.setColor(Color.black);
		
	}
	
	public void action(){
		Log.error("Abstact NPC - Error");
	}
	

	
	


	
	}

