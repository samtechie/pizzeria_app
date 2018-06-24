package exceptions;

import java.util.logging.Level;

/**
 * This Handles an exception of missing/unclear size
 * it fixes the issue by setting the exception to small
 * After fixing, it notifies the user of what happened
 * 
 * */

public class MissingSizeException extends Exception implements MyCustomException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2820939695785082602L;

	
	public void logging() {

		MyCustomException.log.log(Level.INFO," Log: The configuration had no size options");
	}
	@Override
	public String fix() {
		
		
		return "S";
		
		
	}
	@Override
	public String getMessage() {
		
		return "The Size was missing a default small was used\n";
	}
	@Override
	public void setString(String void_) {
		// TODO Auto-generated method stub
		
	}
	

}
