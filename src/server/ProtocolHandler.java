package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import model.PizzaConfig;
import util.PizzeriaProtocol;

public class ProtocolHandler extends Handler{
	
	/*
	 * This class serves as an interpreter of the protocol
	 * The server uses itr to decode the message of from the client embeded in the protocol
	 * the client does not need it because it knows what it is expecting from the server. 
	 * 
	 *  
	 * **/
	
	// this array is used to read the incoming flags carried inside the protocol 
	boolean clientFlags[] = new boolean [10];
	// this arrayList will contain all String message parts from the client
	ArrayList<String>clientVal_ = new ArrayList<String>(10);
	// this object will contain the propertes object
	Properties prop;
	
	// this is an instance of the protocol to be decoded
	private PizzeriaProtocol obj;
	
	// streams of net communication
	ObjectOutputStream serialOut;
	ObjectInputStream serialIn;
	
	// instance of pizzaconfig carried to/fro the client-server
	PizzaConfig config; 
	
	// conanstrutor
	public ProtocolHandler(ServerOperations serverOperations, PizzeriaProtocol obj, ObjectOutputStream serialOut, ObjectInputStream serialIn) {
	this.serialOut=	serialOut;
		
	this.serialIn=	serialIn;
		
		
		int i=0;
		this.obj=obj;
		
		// retrieving all message parts encoded inside the the protocol
		String clientVal[]= obj.getClientVal();
		
		// retrieving the flags which ease the task of decoding
		for (boolean flag: obj.getClientFlags()){
			if (flag){
				clientVal_.add(clientVal[i]);
				
				
			}
			i++;
		}
		
		// invoking the method of interpreting the request
		handle(serverOperations,clientVal[0],clientVal_);

	}
	
	
	// this function interpretes the requests based on the name od the operation and the received variables
	
	public void handle(ServerOperations newThread,String op, ArrayList <String> param){
		
		
		
		if (op.equalsIgnoreCase("delete")){
           // calling the appropriate function to handle the request
			newThread.delete(param.get(1));
			
		}
		
		
		// to print the pizzeria we need the access to the socket stream
		else if(op.equals("printpizzeria"))
			newThread.printPizzerias(serialOut,serialIn);
		else if(op.equals("print"))
			newThread.printPizzeria(param.get(1),serialOut,serialIn);
		
		// to add an option

		else if(op.equals("addoption"))
			newThread.addOption(param.get(1),param.get(2),param.get(3),param.get(4),serialOut,serialIn);
		else if(op.equals("configurePizzeria")){
			
			newThread.createPizzeria(obj.getProp());
		}
		
		// to update base price 
		else if(op.equals("update")){
			newThread.updatePrice(param.get(1), param.get(2),serialOut, serialIn);		
			
		}
		// printing option sets
		else if(op.equals("printopset")){
			newThread.printOpSets(param.get(1),serialOut, serialIn);		
			
		}
			
		
		
	}
	

}
