package dungeonRunner.Items;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

import dungeonRunner.Enemies.simpleEnemy;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;

public class Item {
	
	public String name;
	public Map currentMap;
	public float x;
	public float y;
	public Shape hitbox;
	public Image item;
	public Image icon;
	public boolean taken;
	public simpleEnemy enemy;
	public int dropChance;
	public int value;
	
	
	// dropChance = 0 , kann nicht droppen.
	// dropChance = 10, droppt immer.
	
	public Item(String pName, simpleEnemy pEnemy){
		name = pName;
		enemy = pEnemy;
		
				
		currentMap = dungeonRunnerEngine.currentMap;
		
					
	}
	
	public void realizeDropChance(){
		Random rnd = new Random();
		int wahrscheinlichtkeit = rnd.nextInt(10);
			
		if(wahrscheinlichtkeit <= dropChance  ){
//		currentMap.itemList.add(this); 
		}
	}
		

	
	
	public void renderItem(Graphics g){
		g.drawImage(item, x, y);
	
	}
	
	public Image getIcon(){
		return icon;
	}


}
