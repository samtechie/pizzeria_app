package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

public interface PizzeriaInterface {
	
	
	
	
	void createPizzeria(Properties prop);
	
	
/*
 * 
 * printin the pizzeria
 * 
 * 
 * */	
	
	String printPizzerias(ObjectOutputStream serialOut, ObjectInputStream serialIn);
	
	
	
	/*
	 * This function will send the list of pizzerias, the user will select one to delete, 
	 * and the server will delete it
	 * 
	 * */
	void delete(String Pizzeria);

	// adding an option
	/*
	 * once this methde invoked will serialize back the pizzaConfig,
	 * When the config is back, it will update it into the linkedhashMap	 * 
	 * 
	 * */
	void addOption(String pizzeria, String string, String string2, String string3, ObjectOutputStream serialOut, ObjectInputStream serialIn);
	
	/*
	 *  this function when invoked, shall send the config to the client	 * 
	 * 
	 * */
	void printPizzeria(String pizzeria, ObjectOutputStream serialOut, 
			ObjectInputStream serialIn);

   /*
    * This function when invoked will print all optionsets availbale in a given pizzeria
    * 
    * 
    * */
	void printOpSets(String pizzeria, ObjectOutputStream serialOut, ObjectInputStream serialIn);	


}
