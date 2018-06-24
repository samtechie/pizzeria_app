package wrapper;

public class PizzeriaConfigAPI extends ProxyPizzerias implements CreatePizzeria, UpdatePizzeria {
	/*
	 * 
	 * this class implemets a sinpletn pattern 
	 * 
	 * */
	// Implementing a singleton pattern
		
	
	public static PizzeriaConfigAPI api = new PizzeriaConfigAPI();// this the only object avalaible outside
	
	
	// a private constructor to avoid instatiating of many objects
	private PizzeriaConfigAPI(){}
	

}
