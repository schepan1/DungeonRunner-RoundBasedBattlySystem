package dungeonRunner.core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.FontUtils;

import dungeonRunner.Enemies.batEnemy;
import dungeonRunner.Enemies.simpleEnemy;
import dungeonRunner.GameObjects.Player;
import dungeonRunner.util.dungeonRunnerFonts;
import dungeonRunner.util.dungeonRunnerStates;

public class Battle {

	public Player player;
	public ArrayList<simpleEnemy> enemyList;
	public String[] consoleHistory;
	
	public String[] battleCommands = { "Attack", "Defend", "Skill", "Item", "Escape"};
	public int battleCommandsPointer;
	public battleMenuStates state;
	public simpleEnemy target;
	
	public int targetPointer;
	public Sound menuPoint;
	public Sound menuConfirm;
		
	public boolean bSimpleAttackFinished;
	public boolean soundPlayed;
	
	public PostBattleScreen postBattleScreen;
	
	public enum battleMenuStates {
		basicChoice, targetChoice, simpleAttack, enemyTurn, postBattle
	}
	
	
	
	public boolean bSimpleAttack;

	public Battle(ArrayList<simpleEnemy> enemy) {

		dungeonRunnerEngine.currentState = dungeonRunnerStates.Battle;
		state = battleMenuStates.basicChoice;

		this.player = dungeonRunnerEngine.player;
		
		enemyList = enemy;
		
		targetPointer = 0;
		target = enemyList.get(targetPointer);

		battleCommandsPointer = 0;
		
		try {
			menuPoint = new Sound("res/sounds/battle/button1.ogg");
			menuConfirm = new Sound("res/sounds/battle/on.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		consoleHistory = new String[100];
		postBattleScreen = new PostBattleScreen();
	}
	
	public void renderBattle(Graphics g) {		
		
		if(state == battleMenuStates.postBattle){
			postBattleScreen.renderPostBattle(g);
		}
		
		if(state != battleMenuStates.postBattle){
		g.drawImage(dungeonRunnerEngine.currentMap.background, 0, 0); // Hintergrund
		drawBattleGUI(g); // GUI
		drawConsole(g); // CONSOLE
		drawEnemyNames(g); // NAMES
		
		for (simpleEnemy e : enemyList) {

			if (e.enemyImage != null && e.alive) {
				if (e.groupNumber == 0) {
					g.drawImage(e.enemyImage, 200, 80);
				}
				if (e.groupNumber == 1) {
					g.drawImage(e.enemyImage, 400, 80);
				}
				if (e.groupNumber == 2) {
					g.drawImage(e.enemyImage, 600, 80);
				}
			}

		}
		

		
		if(state == battleMenuStates.targetChoice){
		g.setColor(Color.red);
		if (target.groupNumber == 0 && target.alive)
			g.drawRect(200, 80, target.enemyImage.getWidth(),
					target.enemyImage.getHeight());

		if (target.groupNumber == 1 && target.alive)
			g.drawRect(400, 80, target.enemyImage.getWidth(),
					target.enemyImage.getHeight());

		if (target.groupNumber == 2 && target.alive)
			g.drawRect(600, 80, target.enemyImage.getWidth(),
					target.enemyImage.getHeight());
		g.setColor(Color.black);
		}
		
		
		if(state == battleMenuStates.simpleAttack){
			
//			if(bSimpleAttackFinished){
//				player.playerHit.restart();
//				player.playerHit.setCurrentFrame(0);
//			}
			
			player.playerHit.stopAt(2);
			player.playerHit.draw(400 , 200);
			bSimpleAttackFinished = player.playerHit.isStopped();
				
		}
		}
				
		
	}

	public void updateBattle(GameContainer gc, int delta) {
		
		
		// Enemy Stuff
		for (simpleEnemy e : enemyList) {
			e.die(); // Checkt hp des Gegners.
		}
		
		if(!enemyList.isEmpty()){
		if (target.alive != true) {
			enemyList.remove(target);
		}
		}

		if (enemyList.isEmpty()) {
			state = battleMenuStates.postBattle;
		}
		
		
		//Console
		updateConsole();
		
		
		// Controlling
		if (state == battleMenuStates.basicChoice) {
			if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_S)) {
				if (battleCommandsPointer == battleCommands.length - 1) {
					
					battleCommandsPointer = 0; // Änderung des Menüpunktes
					menuPoint.play();
					System.out.println("Current Menu Point "+ battleCommandsPointer);
				} else {
					battleCommandsPointer += 1; // Änderung des Menüpunktes
					menuPoint.play();
					System.out.println("Current Menu Point "+ battleCommandsPointer);
				}
			}

			if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_W)) {
				if (battleCommandsPointer == 0) {
					battleCommandsPointer = battleCommands.length - 1; 
					menuPoint.play();									// Änderung
																		// des
																		// Menüpunktes
					System.out.println("Current Menu Point "
							+ battleCommandsPointer);
				} else {
					battleCommandsPointer -= 1; // Änderung des Menüpunktes
					menuPoint.play();
					System.out.println("Current Menu Point "+ battleCommandsPointer);
				}
			}

			if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_SPACE) ) {
				if (battleCommandsPointer == 0) {
					menuConfirm.play();
					state = battleMenuStates.targetChoice;
					bSimpleAttack = true;
				}
				if (battleCommandsPointer == 4) {
					menuConfirm.play();
					System.out.println("You escaped");
					
					
					for(simpleEnemy e : enemyList){
						e.alive = true;
						e.health = e.maxHealth;
					}
									
						dungeonRunnerEngine.currentState = dungeonRunnerStates.Play;
					
					
				}
				
			}

		}

		if (state == battleMenuStates.targetChoice && bSimpleAttack == true) {

			if (enemyList.size() == 1) {
				state = battleMenuStates.simpleAttack;
			}

			if (targetPointer < enemyList.size() - 1
					&& dungeonRunnerEngine.inputHandler
							.isKeyPressed(Input.KEY_D)) {

				if (enemyList.get(targetPointer + 1) != null) {
					targetPointer++;
					target = enemyList.get(targetPointer);
					System.out.println("target: " + targetPointer);
				}
			}

			if (targetPointer > 0 && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_A)) {

				if (enemyList.get(targetPointer - 1) != null) {
					targetPointer--;
					target = enemyList.get(targetPointer);
					System.out.println("target: " + targetPointer);
				}

			}

			if (dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER) || dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_SPACE) ) {
				if(enemyList.size() > 1){
				menuConfirm.play();
				}
				state = battleMenuStates.simpleAttack;
			}

		}
		
		if (state == battleMenuStates.targetChoice && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ESCAPE)){
			state = battleMenuStates.basicChoice;
		}

		if (state == battleMenuStates.simpleAttack) {
			if(!soundPlayed){
			player.basicHitSound.play();
			System.out.println(player.basicHitSound.playing());
			System.out.println("ddd");
			soundPlayed = true;
			}
			if(bSimpleAttackFinished){
			player.attack(target);
			System.out.println(player.basicHitSound.playing());
			for(int i = 0; i < consoleHistory.length; i++){
				if(consoleHistory[i] == null){
					consoleHistory[i] = (player.name + " hit " + target.name + " with "+ player.hitAttack +".");
					break;
				}
			}
			
			System.out.println(player.name + " hit " + target.name + " with "+ player.hitAttack);
			bSimpleAttack = false;
			soundPlayed = false;
			state = battleMenuStates.enemyTurn;
			}
			
		}
		
		if(target != null){
		if(!target.alive){					// Autotargeting
		simpleEnemy temp = null;
		for(simpleEnemy e : enemyList){
			temp = e;
		}
		if(!target.alive){
			target = temp;
		}
		}
		}
		
		if (state == battleMenuStates.enemyTurn && enemyList.size() == 1) {
			enemyList.get(0).battleBehavior();
				
			for(int i = 0; i < consoleHistory.length; i++){
				if(consoleHistory[i] == null){
					consoleHistory[i] = (enemyList.get(0).name + " hit " + player.name+ " with " + enemyList.get(0).attackDamage +".");
					break;
				}
			}
			
			System.out.println(enemyList.get(0).name + " hit " + player.name+ " with " + enemyList.get(0).attackDamage +".");
			enemyList.get(0).roundCounterEnemy++;
			state = battleMenuStates.basicChoice;
		}
		
		
		if (state == battleMenuStates.enemyTurn && enemyList.size() > 1) {
			if(enemyList.get(0).alive){
			enemyList.get(0).battleBehavior();
			for(int i = 0; i < consoleHistory.length; i++){
				if(consoleHistory[i] == null){
					consoleHistory[i] = (enemyList.get(0).name + " hit " + player.name+ " with " + enemyList.get(0).attackDamage +".");
					break;
				}
			}
			System.out.println(enemyList.get(0).name + " hit " + player.name+ " with " + enemyList.get(0).attackDamage);
			enemyList.get(0).roundCounterEnemy++;
			}
			
			
			if(enemyList.get(1).alive){
			enemyList.get(1).battleBehavior();
			for(int i = 0; i < consoleHistory.length; i++){
				if(consoleHistory[i] == null){
					consoleHistory[i] = (enemyList.get(1).name + " hit " + player.name+ " with " + enemyList.get(1).attackDamage +".");
					break;
				}
			}
			
			System.out.println(enemyList.get(1).name + " hit " + player.name+ " with " + enemyList.get(1).attackDamage);
			enemyList.get(1).roundCounterEnemy++;
			}
			
			
			if(enemyList.size() > 2){
			if(enemyList.get(2).alive){
			enemyList.get(2).battleBehavior();
			for(int i = 0; i < consoleHistory.length; i++){
				if(consoleHistory[i] == null){
					consoleHistory[i] = (enemyList.get(2).name + " hit " + player.name+ " with " + enemyList.get(2).attackDamage +".");
					break;
				}
			}
			
			System.out.println(enemyList.get(2).name + " hit " + player.name+ " with " + enemyList.get(2).attackDamage);
			enemyList.get(2).roundCounterEnemy++;
			}
			}
			state = battleMenuStates.basicChoice;
		}
		
		if(state == battleMenuStates.postBattle){
			postBattleScreen.control();
		}

	}

	public void drawBattleGUI(Graphics g) {

		g.fillRect(0, 450, 820, 250);
		g.setColor(Color.white);

		if (state == battleMenuStates.basicChoice || state == battleMenuStates.targetChoice) {

			g.drawString(battleCommands[0], 25, 470);
			g.drawString(battleCommands[1], 25, 490);
			g.drawString(battleCommands[2], 25, 510);
			g.drawString(battleCommands[3], 25, 530);
			g.drawString(battleCommands[4], 25, 550);

			if (battleCommandsPointer == 0) {
				g.drawOval(15, 477, 5, 5);
			}

			if (battleCommandsPointer == 1) {
				g.drawOval(15, 497, 5, 5);
			}

			if (battleCommandsPointer == 2) {
				g.drawOval(15, 517, 5, 5);
			}

			if (battleCommandsPointer == 3) {
				g.drawOval(15, 537, 5, 5);
			}

			if (battleCommandsPointer == 4) {
				g.drawOval(15, 557, 5, 5);
			}
			g.setColor(Color.black);
		}
	}
	
	public void drawEnemyNames(Graphics g){
		g.setColor(Color.white);
		for (simpleEnemy e : enemyList){
			if (e.enemyImage != null && e.alive) {
				if (e.groupNumber == 0) {
					g.setColor(Color.white);
					FontUtils.drawCenter(dungeonRunnerFonts.enemyFont, e.name, 200, 80 + e.enemyImage.getHeight(), e.enemyImage.getWidth());
					g.setColor(org.newdawn.slick.Color.darkGray);
					g.drawRect(200, 100 + e.enemyImage.getHeight(), e.maxHealth, 7);
					g.setColor(org.newdawn.slick.Color.green);
					g.fillRect(200, 100 + e.enemyImage.getHeight(), e.health, 7);
					
				}
				if (e.groupNumber == 1) {
					g.setColor(Color.white);;
					FontUtils.drawCenter(dungeonRunnerFonts.enemyFont, e.name, 400, 80 + e.enemyImage.getHeight(), e.enemyImage.getWidth());
					g.setColor(org.newdawn.slick.Color.darkGray);
					g.drawRect(400, 100 + e.enemyImage.getHeight(), e.maxHealth, 7);
					g.setColor(org.newdawn.slick.Color.green);
					g.fillRect(400, 100 + e.enemyImage.getHeight(), e.health, 7);
					
				}
				if (e.groupNumber == 2) {
					g.setColor(Color.white);
					FontUtils.drawCenter(dungeonRunnerFonts.enemyFont, e.name, 600, 80 + e.enemyImage.getHeight(), e.enemyImage.getWidth());
					g.setColor(org.newdawn.slick.Color.darkGray);
					g.drawRect(600, 100 + e.enemyImage.getHeight(), e.maxHealth, 7);
					g.setColor(org.newdawn.slick.Color.green);
					g.fillRect(600, 100 + e.enemyImage.getHeight(), e.health, 7);
					
				}
			}
		}
		g.setColor(Color.black);
	}
	
	public void drawConsole(Graphics g){
		g.setColor(Color.gray.darker());
		g.fillRect(430,  460 , 360, 130);
		g.setColor(Color.white);
		g.drawRect(431, 460, 361, 131);
		g.setFont(dungeonRunner.util.dungeonRunnerFonts.enemyFont);
		
		if(consoleHistory[0] != null){	
		g.drawString(consoleHistory[0], 440, 465);
		}
		
		if(consoleHistory[1] != null){
		g.drawString(consoleHistory[1], 440, 485);
		}
		
		if(consoleHistory[2] != null){
		g.drawString(consoleHistory[2], 440, 505);
		}
		
		if(consoleHistory[3] != null){
		g.drawString(consoleHistory[3], 440, 525);
		}
		
		if(consoleHistory[4] != null){
		g.drawString(consoleHistory[4], 440, 545);
		}
		
		if(consoleHistory[5] != null){
		g.drawString(consoleHistory[5], 440, 565);
		}

		}
	
	public void updateConsole(){
		if(consoleHistory[0] != null && consoleHistory[1] != null && consoleHistory[2] != null && consoleHistory[3] != null && consoleHistory[4] != null && consoleHistory[5] != null){
//			for(int i = 0; i < consoleHistory.length; i++){
//				consoleHistory[i] = null;
//				System.out.println("Console Clear");
//			}
		}
	}


}
