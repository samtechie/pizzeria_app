package wrapper;

import java.util.LinkedHashMap;
import java.util.Properties;

import DataBaseConnection.DatabaseConnection;
import io.ReadFile;
import model.PizzaConfig;

public abstract class ProxyPizzerias  {

	// a linked hash configs to store the configs
	public static  LinkedHashMap<String, PizzaConfig> configs=new LinkedHashMap<String, PizzaConfig>();


	// this function create  the pizzeria
	public synchronized void createPizzeria(String pizzeriaName,PizzaConfig config ){

		if(pizzeriaName!=null)

			configs.put(pizzeriaName,config);



	}


	// this function update the option set name
	public synchronized void updateOptionSetName(String PizzeriaName,String oldName, String newName) {


		PizzaConfig  conf = configs.get(PizzeriaName);

		conf.updateOptionset(oldName, newName);


	}

	// this fuction update the BasePrice 
	public synchronized   String   updateBasePrice(String pizzerianame,double newPrice,boolean useDB) {
		PizzaConfig  conf=null;
		boolean success;

		if(!useDB){
			conf = configs.get(pizzerianame);
			if(null!=conf){
				conf.setBasePrice(newPrice);
				return "succesful";
			}else {
				return "No such Pizzera";
			}
		}
		else
		{
			DatabaseConnection db = new DatabaseConnection();
			success= db.updateBasePrice( pizzerianame, newPrice);
			return ""+success;
		}



	}

	// this function updates the price od=f an option	
	public synchronized  void  updateOptionPrice(String pzeriaName,String optiosetName,String OptionName,double price) {

		PizzaConfig  conf = configs.get(pzeriaName);
		// updating the price of the option via OptionSet
		if(conf!=null)
			conf.updateOptionPricr(optiosetName, OptionName, price);
		else System.out.println(("No such Pizzera"));

	}

	// adding a configuration 
	public  void configurePizzeria(Properties prop,boolean useDB) {



		PizzaConfig config = ReadFile.buildPizzaConfig_(prop);
		if (config!=null){
			if(!useDB){

				//to avoid duplicates
				if (configs.get(config.getPizzeria())==null)
					configs.put(config.getPizzeria(),config);
				else{ // add a dash to the pizzeria name

					config.setPizzeria(config.getPizzeria()+"_");
					configs.put(config.getPizzeria(),config);

				}
				return;
			}
			DatabaseConnection db = new DatabaseConnection();
			db.configurePizzeria(config);
		}

		// put here the code for updating the data base


	}

	// this function prints pizzeria
	public PizzaConfig printPizzeria(String pizzeriaName,boolean useDB) {
		
		
		PizzaConfig config;
		if(!useDB){
			System.out.println("kkkk"+configs.size());
			for (String key: configs.keySet()){
				System.out.println("here we are"+key);

				}
			
			
			if(pizzeriaName.charAt(pizzeriaName.length()-1)==':')
				pizzeriaName=pizzeriaName.substring(0, pizzeriaName.length()-1);
			config = configs.get(pizzeriaName.trim());
		    System.out.println(pizzeriaName+"mmm");
		System.out.println("fffff"+config);
		}
		   
		else{
			System.out.println("fffffhh");

			DatabaseConnection db = new DatabaseConnection();
			config= db.getConfig(pizzeriaName);
		}

		if (config!=null)
			return config;

		return null;
	}




	public synchronized void  deletePizzeria(String pizzeria,boolean useDB) {
		if (!useDB)
			configs.remove(pizzeria);
		else{

			DatabaseConnection db = new DatabaseConnection();
			db.remove(pizzeria);

		} 


	}
	// a function to print all Pizzeria by name
	public  String  printPizzerias(boolean useDB){

		String listOfPizzeria= "";
		int k=0;
		String delimiter = "";
		boolean found = false;
		if(!useDB)
			for (String key: configs.keySet()){
				if (null!=key){
					if(k++>0)
						delimiter="\n";
					listOfPizzeria+=delimiter+configs.get(key).getPizzeria()+": "+configs.get(key).getBasePrice();

					found|=true;
				}
			}

		else{
			DatabaseConnection db = new DatabaseConnection();
			listOfPizzeria= db.printPizzerias();
			found = (listOfPizzeria!=null);
		}

		if (!found)
			listOfPizzeria="no configured pizzeria";

		return listOfPizzeria;

	}

	public synchronized void deleteOption(String pzeriaName,String OptiosetName,String OptionName){
		PizzaConfig config = configs.get(pzeriaName);
		if (config !=null)
			config.deleteOption("veg", "cabage");



	};

	// printint the option sets of a pizzeria

	public String printOptionSets(String pizzeria,boolean useDB){

		//printing optioSets
		PizzaConfig config ;
		if(!useDB){
			config = configs.get(pizzeria);
			if (null!= config)
				return config.printOptioSets();	
		}else {
			DatabaseConnection db = new DatabaseConnection();
			return db.getConfig(pizzeria).printOptioSets();	
		}


	return "no such Pizzeria";
}
public void addOption(String pizzeria, String opSet, String Option, double price,boolean useDB) {
	PizzaConfig config = null;
	if(!useDB){
		config = configs.get(pizzeria);

		//addin the option set
		if (null!= config){
			config.addOption(opSet, Option, price);	

		}
	}
	else{
		DatabaseConnection db = new DatabaseConnection();
		config = db.getConfig(pizzeria);
		db.addOption( pizzeria,  opSet,  Option,  price);

	}
}




}
