package client;

import model.PizzaConfig;

public interface PizzeriaClient {
	
	
	void createPizzeria(String fileName);
	//will request the server to send a serialized objects of all pizzerias by name
	String printPizzerias();
	
	PizzaConfig printPizzeria(String pizzeria);
	
	// will request a server to delete a give pizzerias
	void delete(String Pizzeria);
	// will update the pizzeria with the new price
	String updatePrice(String pizzeria, double newPrice);
	// will add the option in the specified OptionSet of the pizzeria
	void addOption(String pizzeria, String opSet, String Option, double price);
	//
	String printOptionsets(String Pizzeria);

}
