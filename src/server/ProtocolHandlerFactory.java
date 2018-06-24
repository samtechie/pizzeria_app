package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.PizzeriaProtocol;

public class ProtocolHandlerFactory {
	/**
	 * 
	 * this class serves as a factory of handlers. when a requests is received via a given protocol, 
	 * it uses its AI(artifial inteligence) and knows which handler to call
	 * 
	 * 
	 * */
	
	Object protocol;
	ServerOperations serverOperations;
	ObjectOutputStream serialOut;
	ObjectInputStream serialIn;
	
	public ProtocolHandlerFactory(Object protocol,ServerOperations serverOperations,ObjectOutputStream serialOut, ObjectInputStream serialIn){
		
		
		this.protocol= protocol;
		this.serialIn= serialIn;
		this.serialOut= serialOut;
		this.serverOperations= serverOperations;
	}

    public Handler getAhandler(){
    	
    	// In our context, this method is hardcoded to always return a single handler that I have	
    	Handler myHandler = 	new ProtocolHandler(serverOperations, (PizzeriaProtocol)protocol, serialOut, serialIn);
    	
    	return (ProtocolHandler) myHandler;
    	
    }



}
