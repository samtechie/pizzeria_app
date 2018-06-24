package util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import model.*;

public class PizzeriaProtocol implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4701843510562102701L;
	/*
	 * This is a class designing a communication protocol between the server and the client 
	 * It carries all of the information from the client,through setters, in the array clientVal
	 * together with the flags: clientFlags which will hwlp decoding which activity to perfoem on which data 
	 * if the client sends a string feedback, it puts it in "feedBack"
	 * If it ia an object it is put in PizzaConfig
	 * */
	
	boolean clientFlags[] = new boolean [10];
	String [] clientVal = new String [10];
	String feedBack;

	Properties prop;
	String pizzeria;
	String optionSetName;
	String optionName;
	String price;
	String Operation;
	String resp;
	PizzaConfig config;
	boolean useDB;
	
	
	
	
	public boolean isUseDB() {
		return useDB;
	}

	public void setUseDB(boolean useDB) {
		this.useDB = useDB;
	}



	// server side	
	boolean serverFlags[] = new boolean[10];
	
	public PizzeriaProtocol(){
		initClientFlags();
	}
	
	public String toString(){
		
		return Operation;
		
		
	}
	
	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	
	
	public void doPost(PizzeriaProtocol obj,ObjectOutputStream out){
		
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	
		
		
		}


	public boolean[] getClientFlags() {
		return clientFlags;
	}



	public void initClientFlags() {
		for (int i = 0; i < clientFlags.length; i++) {
			clientFlags[i]|=false;;
		}
	}



	public Properties getProp() {
		return prop;
	}



	public void setProp(Properties prop) {
		clientFlags[5]=true;
		

		this.prop = prop;

		
		
	}



	public String getPizzeria() {
		return pizzeria;
	}



	public void setPizzeria(String pizzeria) {
		clientFlags[1]=true;
		this.pizzeria = pizzeria;
		clientVal[1]=pizzeria;

	}



	public String getOptionSetName() {
		return optionSetName;
	}



	public void setOptionSetName(String optionSetName) {
		clientFlags[2]=true;

		this.optionSetName = optionSetName;
		clientVal[2]=optionSetName;

	}



	public String getOptionName() {
		return optionName;
	}



	public void setOptionName(String optionName) {
		clientFlags[3]=true;

		this.optionName = optionName;
		clientVal[3]=optionName;

	}



	public String getPrice() {
		return price;
	}


    // used
	public void setPrice(String optPrice) {
		clientFlags[4]=true;

		this.price = optPrice;
		clientVal[4]=optPrice;

	}



	public String getOperation() {
		return Operation;
	}



	public void setOperation(String operation) {
		clientFlags[0]=true;

		Operation = operation;
		clientVal[0]=operation;
		

	}



	public String[] getClientVal() {
		return clientVal;
	}

	public void setClientVal(String[] clientVal) {
		this.clientVal = clientVal;
	}

	public void setClientFlags(boolean[] clientFlags) {
		this.clientFlags = clientFlags;
	}

	public boolean[] getServerFlags() {
		return serverFlags;
	}



	public void setServerFlags(boolean[] serverFlags) {
		this.serverFlags = serverFlags;
	}



	public String getResp() {
		return resp;
	}



	public void setResp(String resp) {
		this.resp = resp;
	}



	public PizzaConfig getConfig() {
		return config;
	}



	public void setConfig(PizzaConfig config) {
		this.config = config;
	}

}
