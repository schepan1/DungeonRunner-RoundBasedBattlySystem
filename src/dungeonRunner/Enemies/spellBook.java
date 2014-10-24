package dungeonRunner.Enemies;

import java.util.ArrayList;

import dungeonRunner.Enemies.Spells.spell;

public class spellBook {
	
	ArrayList<spell> spellBook;
	
	public spellBook(){
		spellBook = new ArrayList<>();
	}
	
	
	public void add(spell e){
		spellBook.add(e);
	}
	
	
	public spell get(int a){
		
		return spellBook.get(a);
		
	}
	
	public int size(){
		return spellBook.size();
	}
	

}
