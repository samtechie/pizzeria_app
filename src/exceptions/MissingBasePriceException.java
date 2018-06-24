package exceptions;

import java.util.Random;
import java.util.logging.Level;

public class MissingBasePriceException extends Exception implements MyCustomException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2820939695785082602L;

	
	
	
	@Override
	public void logging() {

		MyCustomException.log.log(Level.INFO," Log: Base price was missing");
		
	}
	@Override
	public String fix() {
		
		
		return ""+(2000+new Random().nextInt(2000));
		
		
	}
	@Override
	public String getMessage() {
		
		return "The BasePrice was missing a random value was used\n";
	}
	@Override
	public void setString(String void_) {
		// TODO Auto-generated method stub
		
	}
	
	

}
