package dungeonRunnerExceptions;

public class noMapException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public noMapException(String s){
		super("No Current Map.");
	}

}
