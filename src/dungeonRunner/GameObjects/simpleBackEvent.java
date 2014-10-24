package dungeonRunner.GameObjects;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;
import dungeonRunnerExceptions.mapInitException;

public class simpleBackEvent extends simpleEvent {
	
	float x;
	float y;
	
	float xTarget;
	float yTarget;
	
	String text;
	Map currentMap;
	
	int targetMapID;
	
	public simpleBackEvent (float xP , float yP, String text,  Map map  ){
		super(xP,yP, text, map);
	
		currentMap = map;
	}
	
	@Override
	public void update(){
	
		if(rect.intersects(Player.hitbox)){
			this.action();
			Player.hitbox.setLocation(-20, -20);	// damit nur ein transfer stattfindet.
		}
}
	
	
	public void action(){
		Player.hitbox.setLocation(Player.x, Player.y);
		System.out.println(super.text);
		
		dungeonRunnerEngine.mapSet.get(currentMap.ID).blocked = null;
		currentMap.transfer.play(0.5f, 0.4f);
		currentMap = dungeonRunnerEngine.mapSet.get(targetMapID);
		try {
			currentMap.init();
		} catch (mapInitException e) {
			e.printStackTrace();
		}
		dungeonRunnerEngine.currentMap = dungeonRunnerEngine.mapSet.get(Player.lastID);
		try {
			dungeonRunnerEngine.currentMap.init();
		} catch (mapInitException e) {
			e.printStackTrace();
		}
		Player.x = Player.lastEventX;
		Player.y = Player.lastEventY;
		return;
	}

}
