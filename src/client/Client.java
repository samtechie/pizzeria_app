package client;

import java.io.*;
import java.net.*;
import java.util.*;

import exceptions.ExceptionFactory;
import exceptions.MyCustomException;
import model.PizzaConfig;
import util.PizzeriaProtocol;

public class Client implements PizzeriaClient {

	private static int recursion =0;

	private ObjectOutputStream out;
	private  ObjectInputStream in;
	PizzeriaProtocol obj;

	public PizzeriaProtocol getObj() {
		return obj;
	}



	public  void doSend(Object obj,ObjectOutputStream out){

		try {
			out.writeObject(obj);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	public  PizzeriaProtocol doGet( ){
		PizzeriaProtocol obj= null;

		try {
			obj=(PizzeriaProtocol) in.readObject();
		} catch (ClassNotFoundException | IOException e) {

		}

		return   obj;

	}

	//a function to request the server to print the all pizzerias
	public String printPizzerias(){


		// setting the object to send to the server
		obj= new PizzeriaProtocol();
		obj.setOperation("printpizzeria");

		// the connector returns an object from the server
		obj=connectToserver(obj);
		// if the returned object is not null
		if(obj!=null){
			//return the the feedback to the client
			System.out.println("I have got it");
			return obj.getFeedBack();
		}

		// it the socket failed inform the user
		return "socket issue";

	}

   // this function parses the flat file into propertis
	Properties fixFile(String file){
		Exception e5= new ExceptionFactory().getException("wrongExtension");

		try {
			throw e5;
		} catch (Exception e4) {

			MyCustomException e6= (MyCustomException) e4;
			if (MyCustomException.logger)
				e6.logging();
			e6.setString(file);
			file = e6.fix();
		}

		Properties prop = new Properties();
		InputStream input = null;


		try {
			input = new FileInputStream(file);
			prop.load(input);

		} catch (FileNotFoundException e) {

			Exception e2 = new ExceptionFactory().getException("missingFile");

			try {
				throw e2;
			} catch (Exception e1) {
				MyCustomException e3 = (MyCustomException) e1;

				if (MyCustomException.logger)
					e3.logging();


				e3.setString(file);				
			    file=e3.fix();
				if(recursion++ >100)
					System.exit(1);
				prop=fixFile(file);
			}

		} catch(IOException e){
			// handle this exception
		}
		return prop;
		
		
	}
	
	// this fumction sends the properties file to the server via protocol
	public void createPizzeria(String file){

		Properties prop = fixFile(file);
		obj= new PizzeriaProtocol();
		obj.setOperation("configurePizzeria");
		obj.setProp(prop);


		connectToserver(obj);

	}


	// deleting a pizzeria
	public void delete(String pizzeria) {
		obj= new PizzeriaProtocol();
		obj.setOperation("delete");
		obj.setPizzeria(pizzeria);
		connectToserver(obj);
	}


	/**
	 * @param objToSend
	 * @return
	 */
	public PizzeriaProtocol connectToserver(PizzeriaProtocol objToSend){

		PizzeriaProtocol obj_;
		Socket socketClient = null;

		try{


			socketClient= new Socket("localhost",5555);

			System.out.println("Client: "+"Connection Established");

			in = new ObjectInputStream(socketClient.getInputStream());

			out=new ObjectOutputStream(socketClient.getOutputStream());


		}catch(Exception e){e.printStackTrace();

		}

		// Sending the object 
		doSend(objToSend,out);

//
//		try {
//
//			//Thread.sleep(500);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		//System.out.println("here");


		while((obj_=doGet())!=null){

			try {
				socketClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return obj_;			

			// make sure it breaks

		}
		return null;


	}



	@Override
	public PizzaConfig printPizzeria(String pizzeria) {
       
		obj= new PizzeriaProtocol();
		obj.setOperation("print");
		obj.setPrice(pizzeria);
		obj=connectToserver(obj);

		System.out.println("fffllllll"+obj);

		return obj.getConfig();

	}


    /*
     * this function when invoked, updates the base price of the specified pizzeria
     * 
     * */
	@Override
	public String updatePrice(String pizzeria, double newPrice) {

		//setting the request through the protocol
		PizzeriaProtocol obj = new PizzeriaProtocol();
		obj.setPizzeria(pizzeria);
		obj.setPrice(""+newPrice);
		obj.setOperation("update");
		
		// communicating to the server
	    obj=connectToserver(obj);
	    System.out.println(obj==null);
	    // if the returned repsonse is not void
		if(obj!=null)
			return obj.getFeedBack();
		
		
		// if the the action dint pass through
		return new String("not succesfull");
	}



	@Override
	public void addOption(String pizzeria, String opSet, String optionName, double price) {
      PizzeriaProtocol obj = new PizzeriaProtocol();
		obj.setPizzeria(pizzeria);
		obj.setOptionSetName(opSet);
		obj.setOptionName(optionName);
		obj.setPrice(""+price);
		obj.setOperation("addoption");
		
		 connectToserver(obj);
		 
		   
	}



    /*
     * this function when invoked returns the list of option sets of a pizzeria
     * 
     * */
	@Override
	public String printOptionsets(String pizzeria) {

		
		PizzeriaProtocol obj = new PizzeriaProtocol();
		obj.setPizzeria(pizzeria);
		obj.setOperation("printopset");
		
		 obj=connectToserver(obj);
		 
		   if(obj!= null){
			   		   
			   return obj.getFeedBack();
		   }
		
		return "action not succesful";
	}







}
