package exceptions;

public class ExceptionFactory extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587749673400029903L;
	
	
	public ExceptionFactory(){
		
		
	}
		
	public Exception getException(String excepType){
		

		if (excepType.equalsIgnoreCase("missingPizzeria")){
			
			
			return new MissingPizzeriaException();
		}
		else if  (excepType.equalsIgnoreCase("missingBasePrice"))
		
			return  new MissingBasePriceException();
		else if  (excepType.equalsIgnoreCase("missingSize"))
			
			return  new MissingSizeException();
		
        else if  (excepType.contains("dup"))
			
			return  new DuplicateOptioSetName();
       else if  (excepType.contains("voidOptionset"))
			
			return  new NoOptioSetToDelete();
       else if(excepType.equalsIgnoreCase("missingFile"))
    	      return new FileMissingException();
		 else if(excepType.equalsIgnoreCase("wrongExtension"))
    	      return new WrongExtentionException();
		
		
		return null;
	}
		


}
