package wrapper;

public interface UpdatePizzeria {
	
void updateOptionSetName(String pizzeriaName,String oldName, String newName);
String updateBasePrice(String pizzerianame,double newPrice,boolean useDB);
void updateOptionPrice(String pzeriaName,String OptiosetName,String OptionName,double price);
void deletePizzeria(String pizzeria,boolean useDB);
void deleteOption(String pzeriaName,String OptiosetName,String OptionName);

// this function will return a string of the option setds of a pizzeria
String printOptionSets(String pizzeria,boolean useDB);

void addOption(String pizzeria, String opSet, String Option, double price,boolean useDB);
}
