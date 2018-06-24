package util;

import DataBaseConnection.DatabaseConnection;
import client.Client;

public class DbTestDriver {
	
	public  static void main (String []f){
		
		
		Client client = new Client();
		
		// testing reading from empty DB
		 
		 // testing printint a configutation from DB
		 
		 
		 System.out.println("printing the pizzerias");
		 System.out.println(new DatabaseConnection().printPizzerias());
		
		 System.out.println("printing a specific  pizzeria");
		 System.out.println(new DatabaseConnection().getConfig("Felix"));
		 
		 System.out.println("updatinb the base price of Taly pizzeria");

		 System.out.println(new DatabaseConnection().updateBasePrice("Taly", 1500));
		 
		 System.out.println("adding hen option to the Veg of Felix the pizzeria");

		 new DatabaseConnection().addOption("Felix", "Veg", "hen", 100);
		 
		 System.out.println("removing Taly from the list of pizzerias");

		 new DatabaseConnection().remove("Taly");
		 System.out.println("remaining pizzerias");
		 System.out.println(new DatabaseConnection().printPizzerias());
		 
		
	}
	
	

}
