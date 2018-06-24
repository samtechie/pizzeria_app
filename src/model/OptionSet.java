


package model;

import java.io.Serializable;
import java.util.ArrayList;
/*
 * This the class of option sets.
 * the class has an array of options or choices made of name and price
 * it has an inner class of options
 *
 * */
public class OptionSet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5526785048305370758L;
	//class attribute
	protected String name;
	protected ArrayList <Option > choices;
	protected int  optionSetLen=0;


	/**
	 * Constructors
	 * 
	 * **/
	//default constructor
	protected OptionSet(){}

	/*Specific constructor to create an option with the name given 
		and the price associated to the name*/
	protected OptionSet(String name){

		setName(name);
	}




	/**
	 * setters and getters
	 **/

	// a getter to retrieve an option at the specified  index of optionset
	protected Option getOption(String name){		

		int index = getOptionIndex(name);
		if(check(index,choices))
			return choices.get(index);

		return null;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}

	// this function initiatea a collection for the options
	protected void setChoices() {
		choices = new ArrayList<Option>();
		return;
	}


	public String toString(){

		StringBuffer out = new StringBuffer("");
		for (Option choice:choices){

			if(choice!=null){
				out.append(choice.getName()+" ");
			}

		}
		return String.valueOf(out);
	}


	protected void addChoice(Option anOption){

		choices.add(optionSetLen++, anOption);
		return;
	}

	// The methode to delete an option given the name
	protected void deleteOption(String name){

		int indexOfOption = getOptionIndex(name);
		if(indexOfOption>-1)
			choices.remove(indexOfOption);

		else
			System.out.println("the option you are deleting is not on the list\n");
		updateOptions();

	}

	// function looping throuh the options an return the index of an option in question
	protected   int getOptionIndex(String name){



		int i=0;

		for (Option aChoice: choices){
			if(aChoice!=null)
				if (aChoice.getName().equalsIgnoreCase(name)){
					System.out.println("Option :"+aChoice.getName()+ " is found.\n");

					return i;
				}
			i++;
		}

		return -1;

	} 

	protected Option getChoices(int index) {
		return choices.get(index);
	}
	protected ArrayList<Option> getChoices(){

		return choices;
	}



	// a function to update the Options when an option is removed
	protected void updateOptions(){

		ArrayList<Option> newChoices = new ArrayList <Option>(choices.size());
		int i=0;
		for(Option aChoices : choices){

			if (aChoices!=null){
				newChoices.add(aChoices);
				i++;
			}


		}

		choices =newChoices;

	}

	protected class Option implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4805991379105534872L;
		protected String name="lohan";
		protected double price=0;


		/**
		 * Constructors
		 * */
		//default constructor
		protected Option (){}

		//a constructor to set the name and aapropriate price
		protected Option(String name,double price){
			setName(name);			
			setPrice(price);
		}



		/**
		 * Setters and getters
		 * 
		 * **/
		protected String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		protected double getPrice() {
			return price;
		}
		protected void setPrice(double price){


			this.price=price;

		}

		/**
		 * Instance Methods
		 * **/

	}
	//This method checks if the the index is within the size of the array given
	protected boolean check(int index, ArrayList <Option> choices){


		if(index<0||index>choices.size())
			return false;
		return true;
	}

}
