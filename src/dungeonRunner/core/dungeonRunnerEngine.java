package dungeonRunner.core;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

import de.lessvoid.nifty.Nifty;
import dungeonRunner.GameObjects.NPC;
import dungeonRunner.GameObjects.Player;
import dungeonRunner.maps.*;
import dungeonRunner.util.dungeonRunnerPlayerLookState;
import dungeonRunner.util.dungeonRunnerStates;
import dungeonRunner.util.dungeonRunnerTextField;
import dungeonRunnerExceptions.mapInitException;
import dungeonRunnerExceptions.noMapException;
import dungeonRunnerMainMenu.dungeonRunnerChapterMenu;
import dungeonRunnerMainMenu.dungeonRunnerMenu;



public class dungeonRunnerEngine extends BasicGame {
	
	
	
	/**
	 * ENGINE SETTINGS---------------------------------------------
	 * 
	 * 
	 * 	
	 * 
	 **/
	
	public static boolean developerMode = true;
	public static boolean fullscreen = false;
	public static boolean showHitboxes = false;
	public static boolean showInteractionHitBoxes = false;
	public static boolean showMapGrid = false;

	/**
	 * Initialisierung der Engine Attribute.
	 **/

	public static AppGameContainer appgc;
	public static Input inputHandler;
	public static boolean gameHasBeenStarted;
	public static dungeonRunnerStates currentState;
	public static float fdelta; 		// fdelta geteilt durch 50 um ungefähre Sekunden zu berechnen.
	public static Graphics g;
	public static int posXMouse;
	public static int posYMouse;

	
	/**
	 * Splashscreen
	 * 
	 * @category Intro
	 **/
	private dungeonRunnerSplashScreen ss;
	
	/**
	 * Initialisierung des MainMenu.
	 * 
	 * @category Menu
	 **/
	private dungeonRunnerMenu mainMenu;
	private dungeonRunnerChapterMenu chapterMenu;
	
	// public static dungeonRunnerTextField tb;
	
	/**
	 * Initialisierung der Chapter Attribute
	 * 
	 * @category Chapter
	 **/
	 public static boolean chapter01;
	
	 
	 /**
		 * Initialisierung der Map Attribute
		 * 
		 * @category Maps
		 **/
	 public static Battle currentBattle;
	 
	/**
	 * Initialisierung der Map Attribute
	 * 
	 * @category Maps
	 **/
	public static  Map currentMap;
	static public  Map map001;
	static public  Map map002;
	static public  Map map003;
	static public  Map map004;
	static public  Map map005;
	static public  Map map006;
	static public  Map map007;
	static public  Map map008;
	static public  Map map009;
	static public  Map map009smallTent01;
	static public  Map map009smallTent02;
	static public  Map map009bigTent03;
	public Map bigTest;
	
	public static HashMap<Integer , Map> mapSet;

	/**
	 * Initalisierung der IngameGUI.
	 *
	 */
	 public dungeonRunnerGUI ingameGUI;
	 
	 /**
	  * Initialisierung der Player Attribute
	  *
	  */
	 public static Player player;
	 
	 /**
	  * Kamera
	  *
	  */
	 
	 public Camera camera;
	 
	//TESTAREA
	Nifty nifty;
	float f;
	 
	/**
	 * @param Konstruktor
	 */
	public dungeonRunnerEngine(String title) {
		super(title);

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		//DeveloperSettings;
		
				
		
		mainMenu = new dungeonRunnerMenu();
		chapterMenu = new dungeonRunnerChapterMenu();
		
		//Initialisierung der Hintergrund Musik

		
		//SplashScreen
		ss = new dungeonRunnerSplashScreen();
		
		
		ingameGUI = new dungeonRunnerGUI();
		
		
		
		mapSet  = new HashMap<>();
		
		// ID, SPAWNX , SPAWNY, SPAWNXBACK , SPAWNYBACK

		map001  = new Map(0, 10, 4, 10, 12, new TiledMap("res/maps/Start.tmx"));
		map002  = new Map(1, 10, 2, 1, 12, new TiledMap("res/maps/chapter1map2.tmx"));
		map003  = new Map(2, 17, 12, 4, 2, new TiledMap("res/maps/chapter1map3.tmx"));
		map004  = new Map(3, 4, 11, 16, 1, new TiledMap("res/maps/chapter1map4.tmx"));	
		map005  = new Map(4, 15, 12, 4, 12, new TiledMap("res/maps/chapter1map5.tmx"));
		map006  = new Map(5, 3, 2, 3, 12, new TiledMap("res/maps/chapter1map6.tmx"));
		map007  = new Map(6, 3, 2, 16, 11, new TiledMap("res/maps/chapter1map7.tmx"));
		map008  = new Map(7, 4, 6, 46, 5, new TiledMap("res/maps/chapter1map8.tmx"));
		
		map009  = new Map(8, 2, 6, 21, 21, new TiledMap("res/maps/chapter1map9.tmx"));
		map009smallTent01 = new Map(-2, 10, 11, 0, 0, new TiledMap("res/maps/chapter1map9smallTent01.tmx"));
		map009smallTent02 = new Map(-3, 11, 11, 0, 0, new TiledMap("res/maps/chapter1map9smallTent02.tmx"));
		map009bigTent03 = new Map(-4, 11, 17, 0, 0, new TiledMap("res/maps/chapter1map9bigTent03.tmx"));
		
		bigTest = new Map(-1, 2, 2, 10, 10, new TiledMap("res/maps/bigTest.tmx"));
		
		
		mapSet.put(map001.ID, map001);
		mapSet.put(map002.ID, map002);
		mapSet.put(map003.ID, map003);
		mapSet.put(map004.ID, map004);
		mapSet.put(map005.ID, map005);
		mapSet.put(map006.ID, map006);
		mapSet.put(map007.ID, map007);
		mapSet.put(map008.ID, map008);
		mapSet.put(map009.ID, map009);
		mapSet.put(map009smallTent01.ID, map009smallTent01);
		mapSet.put(map009smallTent02.ID, map009smallTent02);
		mapSet.put(map009bigTent03.ID,map009bigTent03);
		
		map001.enemyFreq = 15;
		map002.enemyFreq = 13;
		map003.enemyFreq = 10;
		
		mapSet.put(bigTest.ID, bigTest);
		
		currentMap = map009; // Don't MOVE!!!
	
		
		// Map Animations.
		map009.addBonfire(10.99f, 12.85f);
		
		// Map Shaders
		
		map008.setShader("res/maps/shaders/shadersBig.png");
		map009.setShader("res/maps/shaders/map009Shaders.png");
		map009smallTent01.setShader("none");
		map009smallTent02.setShader("none");
		map009bigTent03.setShader("none");
		//Battle
		
		currentBattle = null;
		
		// Initialisiert alle Maps in der HashMap
		Set<Integer> maps = mapSet.keySet();
		try{
		for(Integer e : maps){
			mapSet.get(e).init();	
		}
		}
		catch(mapInitException e){
			e.printStackTrace();
		}
		
		
		// Simple Map Events
		map009.add32Event(17, 4.8f, "A Strange looking statue.");
		map009.add32Event(19, 3f, "It's written in a language you can't understand.");
		map009.addSimpleTransferEvent(11, 7, "You entered a new Room",  -2);
		map009.addSimpleTransferEvent(4, 15, "You entered a new Room",  -3);
		map009.addSimpleTransferEvent(18, 20, "You entered a new Room", -4);
		
		// Map mit Gegnerm füllen
		map001.addBatList1();
		map001.addBatList2();
		map001.addBatList3();
		
		map002.addBatList1();
		map002.addskeletonGuard1withBat1();
		
		map003.addCasterOfSand2();
		
		// Map mit NPC füllen
		map001.addBatNPCAnimalBat();
		map009.addBatNPCRolan();
		map009.addBatNPCDezoy();
		map009.addBatNPCCain(13, 9);
		map009bigTent03.addBatNPCBrian(6, 14);
		
		map008.addBatNPCAnimalBat();
			
		// Aktuelle Map und Player erstellung.
		player = new Player(currentMap.spawnX, currentMap.spawnY, "raLa");
		Player.ausrichtung = dungeonRunnerPlayerLookState.South;
				
				
		//Anfangsstate
		currentState = dungeonRunnerStates.Menu;
		
		int f = 30;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		

		
		this.g = g;
		
		
		if (currentState == dungeonRunnerStates.Splashscreen) {
			ss.renderSplash(g);
		}
		
		if (currentState == dungeonRunnerStates.Menu) {
			mainMenu.initMenuGraphic(g);
		}
		if (currentState == dungeonRunnerStates.Chapterchoose) {
			chapterMenu.initMenuGraphic(g);
		}
		if (currentState == dungeonRunnerStates.Play || currentState == dungeonRunnerStates.Dialogue) {
							
			
			
            camera = new Camera(gc, currentMap.getMap());
            camera.centerOn(Player.x, Player.y);
            camera.translateGraphics();
            camera.drawMap(0);
            camera.drawMap(1);

            currentMap.drawMap();    
			
			//----------------------------------------------------------DEVELOPER RENDERING-----------------------------------------------------------------
            
        	if(showMapGrid){
				g.setColor(Color.red);
				for(int i = 0; i < currentMap.map.getWidth()*32; i = i+32){
				g.draw(new Rectangle(i, 0, 32, currentMap.map.getHeight()*32));
				}
				for(int i = 0; i < currentMap.map.getHeight()*32; i = i+32){
				g.draw(new Rectangle(0, i, currentMap.map.getWidth()*32, 32));
				}
				g.setColor(Color.black);
			}
        				
			if(showInteractionHitBoxes)
				for(Shape e : currentMap.interActionHitBoxList){
				g.setColor(Color.cyan);
				g.draw(e);
				g.setColor(Color.black);
			}
			
			if(showHitboxes){
				g.setColor(Color.yellow);
				g.draw(Player.hitbox);
				for(Shape e : currentMap.hitBoxList){
					g.setColor(Color.yellow);
					g.draw(e);
					g.setColor(Color.black);
				}
			}
			
		
			//-------------------------------------------------------------------------------------------------------------------------------------------------
		   
			for(NPC e : currentMap.npcList){
				e.renderEnemy(g);
			}
			
			player.drawPlayer(g);
			
			camera.untranslateGraphics();
			ingameGUI.initGUI(gc, g);
			ingameGUI.initInventoryButton(g);
	//		if(tb != null){
	//			tb.render(g);
	//		}
			
						
			
			
		}
		

		
		 if (currentState == dungeonRunnerStates.Battle) {
				if(currentBattle != null){
					currentBattle.renderBattle(g);
				}
				
				
		 	}
		
	
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		
		
				
		if(currentMap == null){
			try {
				throw new noMapException(" ");
			} catch (noMapException e) {
				e.printStackTrace();
			}
		}
		
		inputHandler = gc.getInput(); // InputActivation
		fdelta = delta * 0.1f;			 // Delta Objekt berechnen
	
	//	f = f + fdelta;
	//	System.out.println(f);
		
		if (currentState == dungeonRunnerStates.Menu) {
			mainMenu.menuControl(gc, delta);	
		}
		
		if (currentState == dungeonRunnerStates.Chapterchoose) {
			chapterMenu.menuControl(gc, delta);
			
		}
		if (currentState == dungeonRunnerStates.Play) {
			
			
			ingameGUI.checkMouseButtonIntersections(); // checkt ob mouse auf button ist;
			currentMap.calculateBattle(); // berechnet battle event
			
			try{
			
		    mouseControl();
			player.update(delta);
			player.controlPlayer(g);
			
			currentMap.updateEvents();
			
			for(NPC e : currentMap.npcList){
				
				if(e != null){
				e.update();
				}
			}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
		}
		 if (currentState == dungeonRunnerStates.Battle) {
			 if(currentBattle != null){
					currentBattle.updateBattle(gc, delta);
				}
			  	
		 	}
		 

	}
	
	
	

	public static void main(String[] args) {
		try {

			appgc = new AppGameContainer(new dungeonRunnerEngine("Dungeon Runner"));
			appgc.setDisplayMode(800, 600, false);
			appgc.setTargetFrameRate(60);
			appgc.setFullscreen(fullscreen);
			appgc.start();

		} catch (SlickException ex) {
			Logger.getLogger(dungeonRunnerEngine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void mouseControl(){
		posXMouse = Mouse.getX();
		posYMouse = Mouse.getY();
	}
	
	public static void wait(int milliSeconds){
		appgc.sleep(milliSeconds);
	}
	
	

}
