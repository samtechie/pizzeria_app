package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;

import model.PizzaConfig;
import server.PizzeriaInterface;
import util.PizzeriaProtocol;
import wrapper.*;
import wrapper.PizzeriaConfigAPI;
/**
 * 
 * this class inplements thresd activity
 * every time a connection sokcket is created, it passed to a new thread 
 * which does activities as described in this class
 * 
 * */
public class ServerOperations extends PizzeriaServer implements Runnable{
	/* the connection socket: this will be set 
	   by the one comming from the server
	 */
	Socket connectionSocket;
	/* API: this is a single instance obtained by singleton pattern*/
	CreatePizzeria api2 = PizzeriaConfigAPI.api;
	UpdatePizzeria api = PizzeriaConfigAPI.api;
	//
	boolean useDB;

	// a constructor to set the socket 
	public ServerOperations( Socket connectionSocket,boolean useDB){
		this.connectionSocket = connectionSocket;
        this.useDB= useDB;
	}


	@Override// operation
	public void run(){

		try {
			//  declaring streams
			ObjectOutputStream serialOut; 
			ObjectInputStream serialIn; 


			serialOut=new ObjectOutputStream(connectionSocket.getOutputStream());
			serialIn = new ObjectInputStream(connectionSocket.getInputStream());


			PizzeriaProtocol obj;
			//reading from the socket
			if( (obj = (PizzeriaProtocol) serialIn.readObject())!=null){

				//instatiating an object which  interpretes the protocol using a factory of handlers
				ProtocolHandlerFactory aFactory = new ProtocolHandlerFactory(obj, this, serialOut, serialIn);

				// this is a parent handler handler but reffering to the chosen concrete handler
				@SuppressWarnings("unused")/* the object calls the handling  methode once created*/

				Handler myHandler = aFactory.getAhandler();// done interpreting

			}
			// closing the absolete connection socket of the absolete thread 

			connectionSocket.close();


		} catch (Exception e) {
			// TODO Auto-generated catch block

		}


	}
	//this function create a pizzeria by delegating to the API  
	public void createPizzeria(Properties prop){
		//will request the server to send a serialized objects of all pizzerias by name and Base price


		api2.configurePizzeria(prop,useDB);
	};

	public String printPizzerias(ObjectOutputStream serialOut, ObjectInputStream serialIn){
		String list= api2.printPizzerias(useDB);
		try {

			PizzeriaProtocol obj= new PizzeriaProtocol();
			obj.setFeedBack(list);

			serialOut.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	};

	// will request a server to delete a give pizzerias: done
	public void delete(String pizzeria){
		api.deletePizzeria(pizzeria,useDB);
	};



	// will update the pizzeria with the new price, feed back to the user
	public void updatePrice(String pizzeria, String basePrice,ObjectOutputStream serialOut, ObjectInputStream serialIn){

		double price = Double.parseDouble(basePrice);
		String feedpack =api.updateBasePrice(pizzeria, price,useDB);
		PizzeriaProtocol obj = new PizzeriaProtocol();
		PizzaConfig config = api2.printPizzeria(pizzeria,useDB);
		obj.setFeedBack(feedpack);
		try {
			System.out.println("jjjjj"+obj);
			serialOut.writeObject(obj);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	};
	// will add the option in the specified OptionSet of the pizzeria
	public void addOption(String pizzeria, String opsetName, String opName, String price, ObjectOutputStream serialOut, ObjectInputStream serialIn){

		api.addOption(pizzeria, opsetName, opName, Double.parseDouble(price),useDB);

		return;
	};
	//
	//void printOptionsets(String Pizzeria){};

	/*
	 * 
	 * 
	 * */
	@Override // thi is done
	public void printPizzeria(String pizzeria, ObjectOutputStream serialOut, 
			ObjectInputStream serialIn){
		PizzeriaProtocol obj = new PizzeriaProtocol();
		PizzaConfig config = api2.printPizzeria(pizzeria,useDB);
		obj.setConfig(config);
		try {
			serialOut.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return;


	}

	@Override
	public void printOpSets(String pizzeria,ObjectOutputStream serialOut, ObjectInputStream serialIn) {

		String feedback=api.printOptionSets(pizzeria,useDB);

		PizzeriaProtocol obj = new PizzeriaProtocol();
		obj.setFeedBack(feedback);
		try {
			serialOut.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return;
	}



}
