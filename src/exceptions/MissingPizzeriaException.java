package exceptions;
/**
 * This Handles an exception of missing Pizzeria name
 * it fixes the issue by setting a unique pizzeria name
 * it uses secure random to make sure it does not create duplicates
 * After fixing, it notifies the user of what happened
 * 
 * */
import java.security.SecureRandom;
import java.util.logging.Level;


public class MissingPizzeriaException extends Exception implements MyCustomException {

  

    /**
	 * 
	 */
	private static final long serialVersionUID = -881006812044035959L;

	public MissingPizzeriaException(){
		
	}
	
	
	
	@Override
	public void logging() {

		MyCustomException.log.log(Level.INFO," Log: The configuration had no pizzeria");
	}
	
	@Override
	public String getMessage() {
		
		return "The pizzeria name was missing a default name was used\n";
	}

	@Override
	public  String fix() {
				
		return  "Pizzeria" + new SecureRandom().nextInt(100)+1;
	}

	@Override
	public void setString(String void_) {
		// TODO Auto-generated method stub
		
	}


}
