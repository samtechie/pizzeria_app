package wrapper;
import java.util.Properties;

import model.PizzaConfig;

public interface CreatePizzeria {
	void configurePizzeria(Properties prop,boolean useDB);
	void createPizzeria(String pzzeriaName,PizzaConfig config);
	PizzaConfig printPizzeria(String PizzeriaName,boolean useDB);
	String printPizzerias(boolean useDB);

}
