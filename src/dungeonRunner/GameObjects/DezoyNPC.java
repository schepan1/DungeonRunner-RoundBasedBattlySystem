package dungeonRunner.GameObjects;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.Log;

import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;
import dungeonRunner.util.dungeonRunnerTextField;

public class DezoyNPC extends NPC {
	
	
	public SpriteSheet batSheetRight;
	public Animation batAnimationRight;
	
	public SpriteSheet batSheetLeft;
	public Animation batAnimationLeft;

	public Animation batAnimationDown;
	public SpriteSheet batSheetDown;
	
	public Animation batAnimationUp;
	public SpriteSheet batSheetUp;
		
	public dungeonRunnerTextField text;
	
	public boolean dropped;
	
	
	
	public Integer attackDamage;
	
	public DezoyNPC(float x, float y, String pName, float moveSpeedX, float moveSpeedY, Map map) throws SlickException{
		super(x,y, pName, moveSpeedX, moveSpeedY, map);	
		
		attackDamage = 11;
		health = 100;
		maxHealth = 100;
		quest = false;
		
		givesExperience = 5;
		enemyImage = new Image("res/sprites/enemies/bat/batImage.png");
		
	
		
		standSouth= new SpriteSheet("res/sprites/enemies/dezoy/standDown.png", 32, 32);
		standNorth = new SpriteSheet("res/sprites/enemies/dezoy/standUp.png", 32, 32);
		standWest = new SpriteSheet("res/sprites/enemies/dezoy/standLeft.png", 32, 32);
		standEast = new SpriteSheet("res/sprites/enemies/dezoy/standRight.png", 32, 32);
		
		hitbox = new org.newdawn.slick.geom.Rectangle(x, y, 32, 32);
		
	}
	
	
	
	
	
	@Override
	public void action(){
		System.out.println("Dezoy");
	}
		
	

	}
	
	



