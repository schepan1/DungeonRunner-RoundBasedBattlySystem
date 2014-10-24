package dungeonRunner.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.FontUtils;

import dungeonRunner.core.dungeonRunnerEngine;

public class dungeonRunnerTextField {
	
	public int x;
	public int y;
	public int height;
	public int width;
	public int tbWidth;
    public int index = 0;
    
	public int lineX;
	public int line1;
	public int line2;
	public int line3;
	public int line4;
	
	public Color backGroundColor;
	public Color borderColor;
	
	
	public dungeonRunnerTextField(int x, int y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width  = width;
		
		tbWidth = width - 30;
		
		borderColor = new Color(Color.green);
		backGroundColor = new Color(Color.lightGray.brighter());
		
		lineX = x+10;
		line1 = 500;
		line2 = 520;
		line3 = 540;
		line4 = 560;
	}
	
	public void render(Graphics g){
		
		g.setColor(backGroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
	}
	
		public void setText(String text){
		
			StringBuilder sb = new StringBuilder();
		    String numberOfWords[] = text.split(" ");
		    String lineOne = "";
		    String lineTwo = "";
		    String lineThree = "";
		    String lineFour = "";
		     
		    if(dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineFour) <= tbWidth && !(dungeonRunnerEngine.inputHandler.isKeyPressed(Input.KEY_ENTER))){
		    for(int i = 0; i < numberOfWords.length; i++){
		    	if(dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineOne) <= tbWidth){
		    		lineOne = lineOne + numberOfWords[i] + " ";
		    	}
		    	if( dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineTwo) <= tbWidth && dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineOne) >= tbWidth ){
		    		lineTwo = lineTwo + numberOfWords[i] + " ";
		    	}
		    	if(dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineOne) >= tbWidth && dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineTwo) >= tbWidth && dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineThree) <= tbWidth){
		    		lineThree = lineThree + numberOfWords[i] + " ";
		    	}
		    	if(dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineOne) >= tbWidth && dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineTwo) >= tbWidth && dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineThree) >= tbWidth && dungeonRunner.util.dungeonRunnerFonts.enemyFont.getWidth(lineFour) <= tbWidth){
		    		lineFour = lineFour + numberOfWords[i] + " ";
		    	}
		    	index = i;
		    } 
		    }else{
		    	String newText = "";
		    	for(int b = index; b < numberOfWords.length; b++){
		    		newText = newText + numberOfWords[b];
		    	}
		    	
//		    	int x = dungeonRunnerEngine.tb.x;
//		    	int y = dungeonRunnerEngine.tb.y;
//		    	int height = dungeonRunnerEngine.tb.height;
//		    	int width = dungeonRunnerEngine.tb.width;
//		    	
//		    	dungeonRunnerEngine.tb = null;
//		    	dungeonRunnerEngine.tb = new dungeonRunnerTextField(x, y, height, width);
//		    	dungeonRunnerEngine.tb.setText(newText);
		    }
		    
			
		    
		 
		  
	    dungeonRunner.util.dungeonRunnerFonts.enemyFont.drawString(lineX, line1, lineOne, Color.red);     
	    dungeonRunner.util.dungeonRunnerFonts.enemyFont.drawString(lineX, line2, lineTwo, Color.red);
	    dungeonRunner.util.dungeonRunnerFonts.enemyFont.drawString(lineX, line3, lineThree, Color.red);
	    dungeonRunner.util.dungeonRunnerFonts.enemyFont.drawString(lineX, line4, lineFour, Color.red);     


		
	}
		
		public void dialogueController(){
			 
		 }
	


}
