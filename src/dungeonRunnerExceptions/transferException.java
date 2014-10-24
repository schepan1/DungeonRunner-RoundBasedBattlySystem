package dungeonRunnerExceptions;

import dungeonRunner.core.dungeonRunnerEngine;

public class transferException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public transferException(String s){
		super("Error while transferring the Player from"+ dungeonRunnerEngine.currentMap.ID + " to " + dungeonRunnerEngine.mapSet.get(dungeonRunnerEngine.currentMap.ID+1) +" or similiar.");
	}

}
