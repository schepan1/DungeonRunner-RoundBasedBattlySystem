package dungeonRunner.GameObjects;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;

import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;

public class animalBat extends NPC {
	
	
	public SpriteSheet batSheetRight;
	public Animation batAnimationRight;
	
	public SpriteSheet batSheetLeft;
	public Animation batAnimationLeft;

	public Animation batAnimationDown;
	public SpriteSheet batSheetDown;
	
	public Animation batAnimationUp;
	public SpriteSheet batSheetUp;
	
	public boolean dropped;
	
	
	
	public Integer attackDamage;
	
	public animalBat(float x, float y, String pName, float moveSpeedX, float moveSpeedY, Map map) throws SlickException{
		super(x,y, pName, moveSpeedX, moveSpeedY, map);	
		
		attackDamage = 11;
		health = 100;
		maxHealth = 100;
		
		givesExperience = 5;
		enemyImage = new Image("res/sprites/enemies/bat/batImage.png");
		
		batSheetRight = new SpriteSheet("res/sprites/enemies/bat/right.png", 32, 32);
		batAnimationRight = new Animation(batSheetRight, 300);

		batSheetLeft = new SpriteSheet("res/sprites/enemies/bat/left.png", 32, 32);
		batAnimationLeft = new Animation(batSheetLeft, 300);
		
		batSheetDown = new SpriteSheet("res/sprites/enemies/bat/down.png", 32, 32);
		batAnimationDown = new Animation(batSheetDown, 300);
		
		batSheetUp = new SpriteSheet("res/sprites/enemies/bat/up.png", 32, 32);
		batAnimationUp = new Animation(batSheetUp, 300);
		
		hitbox = new org.newdawn.slick.geom.Rectangle(x, y, 28, 22);
		currentMap = dungeonRunnerEngine.currentMap;

	}
	
	@Override
		public void update(){	
		
			currentMap = dungeonRunnerEngine.currentMap;
			hitbox.setLocation(x, y);

			
			// Sorgt dafür dass die Gegner nicht durch Wände laufen können und dass sich die Gegner auf den Spieler zu bewegen.
			if((Math.round(x * 100) /100.0f) < (Math.round(Player.x * 100 ) / 100.0f)){
				x+= 0.07;
				ausrichtung = dungeonRunnerPlayerLookState.East;
			}
			if((Math.round(x * 100) /100.0f) > (Math.round(Player.x * 100 ) / 100.0f) ){
				x-= 0.07;
				ausrichtung = dungeonRunnerPlayerLookState.West;
			}
			
			if((Math.round(y * 100) /100.0f) < (Math.round(Player.y * 100 ) / 100.0f) && (Math.round(y * 100) /100.0f) != (Math.round(Player.y * 100 ))){
				y+= 0.07;
				ausrichtung = dungeonRunnerPlayerLookState.South;
			}
			if((Math.round(y * 100) /100.0f) > (Math.round(Player.y * 100 ) / 100.0f) && (Math.round(y * 100) /100.0f) != (Math.round(Player.y * 100 ))){
				y-= 0.07;
				ausrichtung = dungeonRunnerPlayerLookState.North;
			}

			this.hitbox.setLocation(x, y);
			
		}

			
	
	
	@Override
	public void renderEnemy(Graphics g){
		
		drawNPCName(g, Color.white);
		
		if(ausrichtung == dungeonRunnerPlayerLookState.East){
			batAnimationRight.draw(x, y);
		}
		else if (ausrichtung == dungeonRunnerPlayerLookState.West){
			batAnimationLeft.draw(x, y);
		}
		else if(ausrichtung == dungeonRunnerPlayerLookState.North){
			batAnimationUp.draw(x, y);
		}
		else if(ausrichtung == dungeonRunnerPlayerLookState.South){
			batAnimationDown.draw(x, y);
		}
	}
	
		
	



	}
	
	



