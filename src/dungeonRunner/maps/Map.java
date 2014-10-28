package dungeonRunner.maps;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;

import dungeonRunner.Enemies.batEnemy;
import dungeonRunner.Enemies.casterOfSandsEnemy;
import dungeonRunner.Enemies.simpleEnemy;
import dungeonRunner.Enemies.skeletonGuardEnemy;
import dungeonRunner.GameObjects.DezoyNPC;
import dungeonRunner.GameObjects.NPC;
import dungeonRunner.GameObjects.NPCbrian;
import dungeonRunner.GameObjects.NPCcain;
import dungeonRunner.GameObjects.NPCcorpse;
import dungeonRunner.GameObjects.Player;
import dungeonRunner.GameObjects.RolanNPC;
import dungeonRunner.GameObjects.animalBat;
import dungeonRunner.GameObjects.simpleBackEvent;
import dungeonRunner.GameObjects.simpleEvent;
import dungeonRunner.GameObjects.simpleTransferEvent;
import dungeonRunner.Items.Item;
import dungeonRunner.core.Battle;
import dungeonRunner.core.dungeonRunnerEngine;
import dungeonRunnerExceptions.mapInitException;

public class Map {

	public TiledMap map;
	public boolean blocked[][];
	public boolean isDoor[][];
	public boolean isBack[][];
	public int ID;

	public ArrayList<ArrayList<simpleEnemy>> battleList;
	public ArrayList<NPC> npcList;
	public ArrayList<Shape> hitBoxList;
	public ArrayList<Shape> interActionHitBoxList;
	public ArrayList<Animation> animationList;
	public ArrayList<simpleEvent> eventList;
	public int spawnXBack;
	public int spawnYBack;
	public int spawnX;
	public int spawnY;
	public int enemyFreq;

	Random rnd;

	public Image background;

	public static int SIZE = 32;
	public Image shaders;

	public Sound transfer;
	
	public SpriteSheet bonfireImage;
	
	//MISC BONFIRE
	
	public Animation bonfire;
	public float bonX1;
	public float bonY1;
	
	
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

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public Map(int pID, int spawnX, int spawnY, int spawnXBack, int spawnYBack,TiledMap map) throws SlickException {
		ID = pID;

		this.map = map;
		shaders = new Image("res/maps/shaders/shaders.png");
		background = new Image("res/maps/battlebackgrounds/simpleCavern.png");
		bonfireImage = new SpriteSheet("res/maps/misc/bonfire.png", 32 ,32);
		bonfire = new Animation(bonfireImage, 300);

		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.spawnXBack = spawnXBack;
		this.spawnYBack = spawnYBack;

		rnd = new Random();

		npcList = new ArrayList<>();
		battleList = new ArrayList<>();
		hitBoxList = new ArrayList<>();
		interActionHitBoxList = new ArrayList<>();
		animationList = new ArrayList<>();
		eventList = new ArrayList<>();

		try {
			transfer = new Sound("res/sounds/ingame/transfer/simpleTransfer.wav");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	//-----------------------------------------------------------------------------------NPC ADD METHODE-------------------------------------------------------------------\\
	public void addBatNPCAnimalBat() throws SlickException{
		npcList.add(new animalBat(0, 10, "Bat", 12, 12, this));
	}
	
	public void addBatNPCRolan() throws SlickException{
		npcList.add(new RolanNPC(20, 6, "Rolan the Adventurer", 12, 12, this));
	}
	
	public void addBatNPCDezoy() throws SlickException{
		npcList.add(new DezoyNPC(14, 14, "Dezoy", 12, 12, this));
	}
	
	public void addBatNPCCain(int x, int y) throws SlickException{
		npcList.add(new NPCcain(x, y, "Cain", 12, 12, this));
	}
	
	public void addBatNPCBrian(int x, int y) throws SlickException{
		npcList.add(new NPCbrian(x, y, "Brian", 12, 12, this));
	}
	
	public void addBatNPCCorpse(float x, float y) throws SlickException{
		npcList.add(new NPCcorpse(x, y, "", 12, 12, this));
	}
	
	
	
	//-----------------------------------------------------------------------------------NPC ADD METHODEN <ENDE>--------------------------------------------------------------------\\
	
	
	//-----------------------------------------------------------------------------------MISC- ADD METHODE-------------------------------------------------------------------\\
	public void add32Event (float x, float y, String text){	// TEXT ONLY
		new simpleEvent(x, y, text, this);
	}
	
	public void addSimpleTransferEvent (float x, float y, String text, int ID){	// TEXT ONLY
		new simpleTransferEvent(x, y, text, ID, this);
	}
	
	public void addBonfire(float x, float y){
		bonX1 = x*32;
		bonY1 = y*32;
		animationList.add(bonfire);
	}
	
	
	//-----------------------------------------------------------------------------------GEGNER ADD METHODEN--------------------------------------------------------------------\\
	public void addBatList1() throws SlickException {	
		
		ArrayList<simpleEnemy> list = new ArrayList<>();

		simpleEnemy temp = new batEnemy(0, 0, "Bat", 0, 0);
		list.add(temp);

		battleList.add(list);
	}

	public void addBatList2() throws SlickException {

		ArrayList<simpleEnemy> list = new ArrayList<>();

		simpleEnemy temp = new batEnemy(0, 0, "Bat", 0, 0);
		
		list.add(temp);
		list.add(new batEnemy(0, 0, "Bat", 0, 0));

		for (int i = 0; i < list.size(); i++) {
			list.get(i).groupNumber = i;
		}

		battleList.add(list);

	}

	public void addBatList3() throws SlickException {

		ArrayList<simpleEnemy> list = new ArrayList<>();

		simpleEnemy temp = new batEnemy(0, 0, "Bat", 0, 0);


		list.add(temp);
		list.add(new batEnemy(0, 0, "Bat", 0, 0));
		list.add(new batEnemy(0, 0, "Bat", 0, 0));

		for (int i = 0; i < list.size(); i++) {
			list.get(i).groupNumber = i;
		}

		battleList.add(list);
	}

	public void addskeletonGuard1withBat1() throws SlickException {

		ArrayList<simpleEnemy> list = new ArrayList<>();

		simpleEnemy temp = new skeletonGuardEnemy(0, 0, "Skeletonguard", 0, 0);

		list.add(temp);
		list.add(new batEnemy(0, 0, "Bat", 0, 0));

		for (int i = 0; i < list.size(); i++) {
			list.get(i).groupNumber = i;
		}

		battleList.add(list);
	}

	public void addskeletonGuard2() throws SlickException {

		ArrayList<simpleEnemy> list = new ArrayList<>();

		simpleEnemy temp = new skeletonGuardEnemy(0, 0, "Skeletonguard", 0, 0);
		list.add(temp);
		list.add(new skeletonGuardEnemy(0, 0, "Skeletonguard", 0, 0));

		for (int i = 0; i < list.size(); i++) {
			list.get(i).groupNumber = i;
		}

		battleList.add(list);
	}
	
	public void addCasterOfSand2() throws SlickException {

		ArrayList<simpleEnemy> list = new ArrayList<>();

		simpleEnemy temp = new casterOfSandsEnemy(0, 0, "Caster of Sands", 0, 0);
		list.add(temp);
		list.add(new casterOfSandsEnemy(0, 0, "Skeletonguard", 0, 0));

		for (int i = 0; i < list.size(); i++) {
			list.get(i).groupNumber = i;
		}

		battleList.add(list);
	}
	//-----------------------------------------------------------------------------------GEGNER ADD METHODEN <ENDE>-------------------------------------------------------------------\\
	@SuppressWarnings("unchecked")
	public void calculateBattle() {

		if(!battleList.isEmpty() && dungeonRunnerEngine.currentMap != null){

		if (Player.schritte >= this.enemyFreq) {

			ArrayList<ArrayList<simpleEnemy>> test;

			test = new ArrayList<ArrayList<simpleEnemy>>(battleList.size()); // COPYSHIIIIT
			for (ArrayList<simpleEnemy> e : battleList) { // COPYSHIIIIT
				test.add((ArrayList<simpleEnemy>) e.clone()); // COPYSHIIIIT
			}

			int battleRnd = rnd.nextInt(test.size());
			dungeonRunnerEngine.currentBattle = new Battle(test.get(battleRnd));

			for (ArrayList<simpleEnemy> s : battleList) { // belebt gegner
															// wieder.
				for (simpleEnemy x : s) {
					x.alive = true;
					x.health = x.maxHealth;
				}
			}

			Player.schritte = 0;
		}
		}
		else{
//			Log.warn(" battleList might be empty or currentMap is null");
		}
	}

	public boolean[][] getBlocked() {
		return blocked;
	}

	public Image getShaders() {
		return shaders;
	}

	public TiledMap getMap() {
		return map;
	}

	public void init() throws mapInitException {

		isDoor = new boolean[map.getWidth()][map.getHeight()];
		blocked = new boolean[map.getWidth()][map.getHeight()];
		isBack = new boolean[map.getWidth()][map.getHeight()];

		for (int xAxis = 0; xAxis < map.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < map.getHeight(); yAxis++) {
				int tileID = map.getTileId(xAxis, yAxis, 0);

				int objectLayer = map.getLayerIndex("Kachelebene 2");
				int tileID2 = map.getTileId(xAxis, yAxis, objectLayer);

				String value = map.getTileProperty(tileID, "blocked", "false");

				String value1 = map.getTileProperty(tileID, "isDoor", "false");

				String valueBack = map.getTileProperty(tileID, "isBack",
						"false");

				String value2 = map
						.getTileProperty(tileID2, "blocked", "false");
				
				String value3 = map
						.getTileProperty(tileID, "back", "false");

				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}

				if ("true".equals(value2)) {
					blocked[xAxis][yAxis] = true;
				}

				if ("true".equals(value1)) {
					isDoor[xAxis][yAxis] = true;
				}

				if ("true".equals(valueBack)) {
					isBack[xAxis][yAxis] = true;
				}
				
				if ("true".equals(value3)) {
					eventList.add(new simpleBackEvent(xAxis, yAxis, "", this));
				}

			}

		}

		System.out.println("Map " + ID + " has been initialized.");

	}

	public boolean isBlocked(float x, float y) {
		int xBlock = (int) x / SIZE;
		int yBlock = (int) y / SIZE;
		if (blocked == null) {
			Log.error("One important tile is probably not set blocked true or map initiation process is bugged.");
			return false;
		}
		return blocked[xBlock][yBlock];
	}

	public boolean isDoor(float x, float y) {
		int xBlock = (int) x / SIZE;
		int yBlock = (int) y / SIZE;

		return isDoor[xBlock][yBlock];
	}

	public boolean isBack(float x, float y) {
		int xBlock = (int) x / SIZE;
		int yBlock = (int) y / SIZE;

		return isBack[xBlock][yBlock];
	}
	
	public void drawMap(){
			
		this.getMap().render(0,0);
		
		for(Animation e : animationList){
			e.draw(bonX1, bonY1);
		}
		
		this.getShaders().draw(0, 0);
		
	}
	
	public void updateEvents(){
		try{
		for(simpleEvent e : eventList){
			e.update();
		}
		}
		catch(ConcurrentModificationException e){
			System.out.println("Nothing to worry about: "+e);
		}
	}
	
	public void setShader(String c) throws SlickException{
		if(c.equals("none")){
			return;
		}
		shaders = new Image(c);
	}

}
