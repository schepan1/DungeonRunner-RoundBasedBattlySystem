package dungeonRunner.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dungeonRunner.Enemies.simpleEnemy;

public class SandStone extends Item {

	public SandStone(String pName, simpleEnemy pEnemy) {
		super(pName, pEnemy);
		
		dropChance = 5;
		value = 20;
		realizeDropChance();
		
		try {
			item = new Image("res/sprites/items/sandStone.png");
			icon = new Image("res/sprites/items/sandStone.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	

}
