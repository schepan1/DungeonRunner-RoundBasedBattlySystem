package dungeonRunner.Enemies.Spells;


import dungeonRunner.Enemies.simpleEnemy;
import dungeonRunner.GameObjects.Player;
import dungeonRunner.core.dungeonRunnerEngine;

public class doubleHitSpell extends spell {
	

	@Override	
	public void cast(simpleEnemy e){
		
		e.enemyImage.setImageColor(255, 255, 255);
		
		Player.currentHealth -= 12;
		Player.currentHealth -= 8;
		System.out.println("DOUBLEHIT");
				
		e.enemyImage.setImageColor(255, 0, 0);	
		//play Animation
	}

}
