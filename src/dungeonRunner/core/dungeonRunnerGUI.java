package dungeonRunner.core;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dungeonRunner.GameObjects.Player;
import dungeonRunner.Inventory.Inventory;
import dungeonRunner.util.dungeonRunnerFonts;

public class dungeonRunnerGUI {
	
	dungeonRunnerGUIHealthBar healthBar;
	Inventory inventory;
	Image southBorder;
	
	/**
	  * Kamera
	  *
	  */
	 Integer offsetMaxX = 0;
	 Integer offsetMaxY = 0;
	 Integer offsetMinX = 0;
	 Integer offsetMinY = 0;
	 
	 float camX = 0;
	 float camY = 0;
	
	public static boolean  bInventory;
	int borderX;
	int borderY;
	
	public dungeonRunnerGUI() throws SlickException{
		healthBar = new dungeonRunnerGUIHealthBar();
		inventory = new Inventory();
		southBorder = new Image("res/gui/southFrame.png");
	}
	
	public void initGUI(GameContainer gc, Graphics g){

		
		offsetMaxX = dungeonRunnerEngine.currentMap.map.getWidth()*32 - 640;
	    offsetMaxY = dungeonRunnerEngine.currentMap.map.getHeight()*32- 480;
				
	   
	    g.drawImage(southBorder, 0, 480);
	 //   g.fillRect(0, 480, 640, 120);
		g.fillRect(640, 0, 160, 480); 

		g.setColor(Color.white);
		g.setFont(dungeonRunnerFonts.goldFont);
		g.drawString("Player X Coord:" + Player.x/32, 655, 375);
		g.drawString("Player Y Coord:" + Player.y/32, 655, 395);
		g.setColor(Color.black);
		

		healthBar.initBars(g);
		if(bInventory){
			inventory.renderInventory(g);
		}
		
		
	}
	

	
	public void initInventoryButton(Graphics g){
		g.drawRect(700, 30, 23, 23);
	    
	}
	
	public void checkMouseButtonIntersections(){
		if(dungeonRunnerEngine.posXMouse >= 700 && dungeonRunnerEngine.posXMouse <= 723 && dungeonRunnerEngine.posYMouse >= 30 && dungeonRunnerEngine.posYMouse >= 53){
			if(dungeonRunnerEngine.inputHandler.isMousePressed(0) && !bInventory){
				bInventory = true;
				return;
			}
			if(dungeonRunnerEngine.inputHandler.isMousePressed(1) && bInventory){
				bInventory = false;
				return;
			}
		}
		
		if(bInventory){
			inventory.checkMouseButtonIntersectionsForExit();
		}
	}

}
