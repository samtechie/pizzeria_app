package model;

import java.util.ArrayList;

public class CustomOrder {
	
	public String doPricing(ArrayList<String> pizzeria, String option , String size){
	
		
		double price_=0;
		StringBuffer order= new StringBuffer("Pizzeria : ");
		order.append(pizzeria.get(0));
		order.append("<br/>BasePrice: ");
		order.append(pizzeria.get(1));
		
		order.append("<br/>Pizza Type : ");
		order.append(option.split(":",2)[0]);
	    order.append("<br/>Size : ");
	    order.append(size.split(":",2)[0]);
	    
		
	     price_ = Double.parseDouble(pizzeria.get(1)) + Double.parseDouble(option.split(":", 2)[1])+
	    		 Double.parseDouble(size.split(":", 2)[1]);
	    
	    order.append("<br/>Final Price :");
	    order.append(price_);
	    order.append("<br/>Currency: RWF");
	    
		return String.valueOf(order);
			
		
	}	
	

}
