package exceptions;

import java.util.logging.Level;


public class DuplicateOptioSetName extends Exception implements MyCustomException {

	        /**
	 * 
	 */
	private static final long serialVersionUID = 8414142655944597849L;
			
	        private static int charToAppend = 2;
	        
	        
	        
	        
	 
	
	@Override
	public String fix() {
		// TODO Auto-generated method stub
		return "_"+charToAppend;
	}
	
	
	
	@Override
	public String getMessage() {
		
		return "two Optioset of the same name detected, the second one is modified\n";
	}



	@Override
	public void setString(String void_) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void logging() {

		MyCustomException.log.log(Level.INFO," Log: Two duplicates OptioSets were detected ");
		
	}
	
	
	
	

}
