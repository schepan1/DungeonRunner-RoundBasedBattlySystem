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
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;

public class skeletonGuardEnemy extends simpleEnemy {

			
		public SpriteSheet batSheetRight;
		public Animation batAnimationRight;
		
		public SpriteSheet batSheetLeft;
		public Animation batAnimationLeft;

		public Animation batAnimationDown;
		public SpriteSheet batSheetDown;
		
		public Animation batAnimationUp;
		public SpriteSheet batSheetUp;
		

		
		public boolean dropped;
		
		
		public skeletonGuardEnemy(float x, float y, String pName , float moveSpeedX, float moveSpeedY) throws SlickException{
			super(x,y, pName, moveSpeedX, moveSpeedY);	
				
			onDie = new Sound("res/sounds/ingame/enemies/skeletonGuard/pain.wav");
			
			health = 250;
			maxHealth = 250;
			attackDamage = 20;
			
			enemyImage = new Image("res/sprites/enemies/skeletonGuard/skeletonGuard.png");
			
		}
		
		
		
	

		
		
	}



