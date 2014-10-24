package dungeonRunner.Inventory;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunner.core.dungeonRunnerGUI;

public class Inventory {
	
	public void renderInventory(Graphics g){
		
		int iSizeX = 200;
		int iSizeY = 400;
		
		int iPosX = 200;
		int iPosY = 70;
		
		g.drawRect(iPosX + iSizeX -10, iPosY, 10, 10);
		g.drawRect(iPosX, iPosY, iSizeX, iSizeY);
		g.setColor(Color.black);
	}
	
	
	public void checkMouseButtonIntersectionsForExit(){
		if(dungeonRunnerEngine.posXMouse >= 390 && dungeonRunnerEngine.posXMouse <= 400 && dungeonRunnerEngine.posYMouse >= 180 && dungeonRunnerEngine.posYMouse >= 190){
			if(dungeonRunnerEngine.inputHandler.isMousePressed(0)){
				dungeonRunnerGUI.bInventory = false;
			}
		}
	}
	

}
