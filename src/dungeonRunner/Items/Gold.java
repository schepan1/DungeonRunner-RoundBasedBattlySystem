package dungeonRunner.Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dungeonRunner.Enemies.simpleEnemy;

public class Gold extends Item {
	
	public int value;

	public Gold(String pName, simpleEnemy pEnemy, int value) {
		super(pName, pEnemy);
		
		dropChance = 9;
		realizeDropChance();
		
		this.value = value;
		try {
			item = new Image("res/sprites/items/gold.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	

}
