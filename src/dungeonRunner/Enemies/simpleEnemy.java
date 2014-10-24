package dungeonRunner.Enemies;




import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;

import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunner.util.dungeonRunnerPlayerLookState;
import dungeonRunner.util.dungeonRunnerStates;
import dungeonRunner.util.dungeonRunnerTransmission;



public class simpleEnemy {
	
	public String name;

	public int maxHealth;
	public int health;
	public int attackDamage;
	public Map currentMap;
	public Shape hitbox;
	public int SIZE = 32;
	public dungeonRunnerPlayerLookState ausrichtung;
	
	public int roundCounterEnemy; //battle
	
	public int givesExperience;
	public boolean alive;
	
	public ArrayList<simpleEnemy> groupList;
	public int groupNumber;
	
	public Image enemyImage;
	public Sound onDie;
	public Sound onAttack;
	public spellBook spellBook;

	
	public simpleEnemy(float x, float y, String pName , float moveSpeedX, float moveSpeedY) throws SlickException{
		
		name = pName;
		alive = true;
		currentMap = dungeonRunnerEngine.currentMap;
		ausrichtung = dungeonRunnerPlayerLookState.South;
		spellBook = new spellBook();	

		groupNumber = 0;
		
	}
	
		
	
	public void die(){
		if(health <= 0){
			if(onDie != null){
				onDie.play();
			}
			alive = false;
		}
		
	}
	

	
	public void checkForBattle(Graphics g){
		if(hitbox.intersects(Player.hitbox)){
			new dungeonRunnerTransmission(g);
			dungeonRunnerEngine.currentBattle = new Battle(groupList);			
	}
	
	}
	
	
	
	public void update(){	
		

		die();
		checkForBattle(dungeonRunnerEngine.g);
		
		currentMap = dungeonRunnerEngine.currentMap;
			
		
	}
	
	public void battleBehavior(){
		
		
		if(onAttack != null)
			onAttack.play();
			Player.currentHealth -= attackDamage;
	}
	
	
	
	


	
	}

