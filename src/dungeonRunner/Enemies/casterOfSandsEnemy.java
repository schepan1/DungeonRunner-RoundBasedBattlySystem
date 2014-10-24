package dungeonRunner.Enemies;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import dungeonRunner.Items.Gold;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;

public class casterOfSandsEnemy extends simpleEnemy {


	
	public boolean dropped;
	public SpriteSheet enemySheet;
	public Animation[] enemyAnim = new Animation[4];
	public int currentAnimation;
	
	
	public casterOfSandsEnemy(float x, float y, String pName , float moveSpeedX, float moveSpeedY) throws SlickException{
		super(x,y, pName, moveSpeedX, moveSpeedY);	
			
		onDie = new Sound("res/sounds/ingame/enemies/skeletonGuard/pain.wav");
		
		health = 250;
		maxHealth = 250;
		attackDamage = 8;
		hitbox = new org.newdawn.slick.geom.Rectangle(x, y, 28, 22);
		

		enemyImage = new Image("res/sprites/enemies/casterOfSand/casterOfSandImage.png");
	}
		

	
	

	
	
	


}


