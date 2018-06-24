package io;
 
import java.util.Properties;

import model.PizzaConfig;

public class ReadFile {

	// a helper function to count the number of options available in 
	// a give n set knowin the delimiter
	public static int getNumOfOptions(String temp, char delimiter){
		int i=0;
		int j=0;
		while(i++<temp.length()){
			if (temp.charAt(i-1)==delimiter){ 
				j++;
			}
		}
		return j+1;
	}


   // a method to parse the properties files into different parameters of the configuration
	public static PizzaConfig buildPizzaConfig_(Properties prop){
		PizzaConfig config = null;
		// catching the wrong extension exception
		double basePrice = 0;

		String pizzeriaName = null;
		String optionSetName=null;
		String [] optionName;
		double[] optionPrices = null;
		String [] size = null;
		double [] sizePrice = null;

		// temp variables

		//options
		String options;
		String optName;
		String prices_Opt;

		// size
		String size_;
		String sizeName;
		String prices_Size;


		//optionsets
		String [] optionSets;

		int numOfElements;


		pizzeriaName=prop.getProperty("Pizzeria");
		String basePrice_ = prop.getProperty("baseprice");
		
		basePrice=Double.parseDouble(basePrice_);

		// creating a configuration with a basePrice and pizzeriaName;
		config = new PizzaConfig(pizzeriaName, basePrice);

		// reading the size of in the configurations

		size_ = prop.getProperty("size");
		sizeName = size_.split(":",2)[0];

		prices_Size = size_.split(":",2)[1];
		numOfElements= getNumOfOptions(sizeName, ',');
		size= sizeName.split(",",numOfElements);

		// memery allocation for the prices of size options
		sizePrice= new double[numOfElements];
		int j=0;
		for (String sizePrc:prices_Size.split(",",numOfElements) ){
			sizePrice[j]= Double.parseDouble(sizePrc);
			j++;
		}

		// retrieving option sets
		numOfElements=getNumOfOptions(prop.getProperty("OptionSets"),',');

		optionSets=  (prop.getProperty("OptionSets")).split(",",numOfElements);


		// associating Options to corresponding optionsets
		int i=0;
		for(String opSet: optionSets){
			optionSetName= prop.getProperty(opSet);
			//System.out.println(optionSetName);
			// retriving options
			options = prop.getProperty("Options"+(i+1));
			//
			optName = options.split(":",2)[0];
			prices_Opt= options.split(":",2)[1];
			numOfElements= getNumOfOptions(optName, ',');
			optionName= optName.split(",",numOfElements);

			optionPrices= new double[numOfElements];
			int k=0;
			for (String price:prices_Opt.split(",",numOfElements)){

				optionPrices[k]= Double.parseDouble(price);

				k++;
			}
			i++;
			// setting the size 
			config.setSize(size, sizePrice);
			// adding an optionSets
			config.addOptionSet(optionSetName, optionName, optionPrices);
		}
		
		return config;
	}


	
}
