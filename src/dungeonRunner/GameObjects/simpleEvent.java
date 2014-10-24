package dungeonRunner.GameObjects;

import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.maps.Map;

public class simpleEvent {
	
	float x;
	float y;
	String text;
	Shape rect;
	Map currentMap;
	
	public simpleEvent (float xP , float yP, String text, Map map){
		x = xP *32;
		y = yP *32;
		this.text = text;
				
		rect = new Rectangle(x, y, 32, 32);
		currentMap = map;
		currentMap.interActionHitBoxList.add(this.rect);
		
		currentMap.eventList.add(this);
		
	}
	
	public void update(){
					
			if(rect.intersects(Player.hitbox) && dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER)){
				this.action();
			}
	}
	
	public void action(){
		System.out.println(text);
	}

}
