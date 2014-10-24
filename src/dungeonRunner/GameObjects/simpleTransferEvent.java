package dungeonRunner.GameObjects;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunnerExceptions.mapInitException;

public class simpleTransferEvent extends simpleEvent {
	
	float x;
	float y;
	
	
	String text;
	Shape rect;
	Map currentMap;
	
	int targetMapID;
	
	public simpleTransferEvent (float xP , float yP, String text,  int targetMID, Map map  ){
		super(xP,yP, text, map);
	

		targetMapID = targetMID;
		
		rect = new Rectangle(x, y, 32, 32);
		currentMap = dungeonRunnerEngine.currentMap;
		currentMap.interActionHitBoxList.add(this.rect);
		
		currentMap.eventList.add(this);
		
	}
	
	
	public void action(){
		System.out.println(super.text);
		Player.lastEventX = super.x;
		Player.lastEventY = super.y;
		Player.lastID = super.currentMap.ID;
		
		System.out.println(Player.lastEventX);
		
		dungeonRunnerEngine.mapSet.get(currentMap.ID).blocked = null;
		currentMap.transfer.play(0.5f, 0.4f);
		currentMap = dungeonRunnerEngine.mapSet.get(targetMapID);
		try {
			currentMap.init();
		} catch (mapInitException e) {
			e.printStackTrace();
		}
		dungeonRunnerEngine.currentMap = this.currentMap;
		Player.x = currentMap.spawnX*32;
		Player.y = currentMap.spawnY*32;
		
	}

}
