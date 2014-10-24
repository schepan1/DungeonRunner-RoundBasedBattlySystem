package dungeonRunnerExceptions;

import dungeonRunner.core.dungeonRunnerEngine;

public class mapInitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public mapInitException(String s){
		super("Error while initiating the Map "+ dungeonRunnerEngine.currentMap.ID +".");
	}

}
