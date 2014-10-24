package dungeonRunner.Enemies;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import dungeonRunner.Enemies.Spells.doubleHitSpell;
import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerPlayerLookState;

public class batEnemy extends simpleEnemy {
	
	
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
	
	public batEnemy(float x, float y, String pName, float moveSpeedX, float moveSpeedY) throws SlickException{
		super(x,y, pName, moveSpeedX, moveSpeedY);	
		
		attackDamage = 11;
		health = 100;
		maxHealth = 100;
		
		givesExperience = 5;
		enemyImage = new Image("res/sprites/enemies/bat/batImage.png");

		onDie = new Sound("res/sounds/ingame/enemies/bat/bat_die.ogg");
		onAttack = new Sound("res/sounds/ingame/enemies/bat/qubodupSqueakyRatAttack.ogg");
		
		spellBook.add(new doubleHitSpell());
		
	}
	
	@Override
	public void battleBehavior(){
		Random rnd = new Random(); 
		
		if(roundCounterEnemy >= 3){
			spellBook.get(rnd.nextInt((spellBook.size()) - 0)+ 0).cast(this);
			roundCounterEnemy = 0;
		}
		else{
		if(onAttack != null)
			onAttack.play();
			Player.currentHealth -= attackDamage;	
		}
	}
	
	
}
