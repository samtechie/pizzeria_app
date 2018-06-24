package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Scanner;

import client.Client;
import client.PizzeriaClient;
import model.PizzaConfig;


public class userInterface {
	/**
	 * 
	 * This is a command line interface of the program
	 * I prompts the user until when they types exit
	 * when the response when the server fails, the client is not affected
	 * when the file is not found the working directory, it serches in upper dirs
	 *
	 * 
	 * */
	@SuppressWarnings("resource")
	public static void main(String [] argv){

		// creating an instance of the client by polymorphism
		PizzeriaClient client_ = new Client();

		// an index that specifies which action to take
		double operation=0;
		BufferedReader   input = new BufferedReader (new InputStreamReader(System.in));

		String fileName;

		// prompt hte user until they are choose to exit
		while(true){
			System.out.println("choose the number of opration:\n"
					+ "1. upload a file to create a pizzeria\n"
					+ "2. print pizzerias\n"
					+ "3. delete a Pizzeria\n"
					+ "4. update base price\n"
					+ "5. add an option\n"
					+ "6. print a pizzeria"
					+ "7. exit");

			// read the operation chosen
			try {// handling invalid input
				operation= Integer.valueOf(input.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("invalid Input");
				operation =0;
			}



			if(operation ==1){

				Scanner input_= new Scanner(System.in);
				System.out.println("enter the name of the file");

				fileName=input_.nextLine();

				// ask the client to create a pizzeria using the provided filename
				client_.createPizzeria(fileName);
			}

			// printing a pizzerias
			else if(operation==2){
				//System.out.println(client.printPizzerias());

				System.out.println(client_.printPizzerias());


			}

			// deleting a pizzeria
			else if(operation==3){
				System.out.println("deleting a pizzeria....");
				String pizzria;
				Scanner input_ = new Scanner (System.in);

				System.out.println("choose by name your Pizzeria to delete");
				System.out.println(client_.printPizzerias());
				pizzria = input_.nextLine();
				client_.delete(pizzria);

			}
			//updating base price
			else if(operation==4){
				System.out.println("updating the base price");
				String pizzria;
				String printable;
				final double contsP=0.12345678;
				double price = contsP;
				Scanner input_ = new Scanner (System.in);

				System.out.println("choose by name your Pizzeria to update");
				// printing the available pisserias
				printable=client_.printPizzerias();
				System.out.println(printable);

				if(!client_.printPizzerias().equals("no configured pizzeria")){
					pizzria = input_.nextLine();
					if(printable.contains(pizzria)){
						System.out.println("enter the new price");
						try{
							price = input_.nextDouble();

						} catch(Exception e){
							System.out.println("the price was not modified due to your typing error");
						}
					}

					// delegating the rest to the client
					if(price!=contsP)
						System.out.println("FeedBack: "+client_.updatePrice(pizzria, price));
				}
			}
			//adding an option
			else if(operation==5){
				System.out.println("adding an option...");
				String pizzria;
				String optionSetName;
				String optioNname;
				String printable;
				double price;
				Scanner input_ = new Scanner (System.in);

				System.out.println("choose by name your Pizzeria to update");
				printable = client_.printPizzerias();
				System.out.println(printable);
				pizzria = input_.nextLine();
				if(printable.contains(pizzria)){
				System.out.println("choose the optionSet to update");
				System.out.println(client_.printOptionsets(pizzria));
				optionSetName = input_.nextLine();
				System.out.println("enter your option name");
				optioNname = input_.nextLine();
				System.out.println("enter the option price");
				price = input_.nextDouble();
				//adding
				System.out.println("adding the option...");
				client_.addOption(pizzria, optionSetName, optioNname, price);
				}
			}	



			else if(operation==6){
				System.out.println("Printing a pizzeria....");
				String pizzria;
				PizzaConfig  printable;
				Scanner input_ = new Scanner (System.in);

				System.out.println("choose by name your Pizzeria to Print");
				System.out.println(client_.printPizzerias());
				pizzria = input_.nextLine();
				if((printable=client_.printPizzeria(pizzria))!=null)
					System.out.println(printable);

			}
			else if(operation == 7){
				System.out.println("exiting...\n exited");
				break;
			}

			else if(operation>0)
				System.out.println("invalid input");

		}
	}

}
