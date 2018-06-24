package exceptions;

import java.io.File;
import java.util.logging.Level;

public class FileMissingException extends Exception implements MyCustomException{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7443594953870376192L;
	private String filename;
	
	public String getFilename() {
		
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	@Override
	public String fix() {
		
return fix(filename);

	}
	
	
	public String fix(String filename){
		
		String userHomeDirectory = System.getProperty("user.home");
		 String user_operatingSystem = System.getProperty("os.name").toLowerCase();
			String filepath = "";
			String setBack = "../";

		              
		
		             if (user_operatingSystem.contains("windows")) {
		
		                  
		 
		         		filepath = setBack+filename;
		             
		 
		             }
		
		
		
		
		
		
		return filepath;
	}
	 public String getMesage(){
		 
		 return "The file wan not found in the specified folder";
		 
		 
		 
	 }


	@Override
	public void setString(String newFilewName) {
		this.filename = newFilewName;
	}


	@Override
	public void logging() {

		MyCustomException.log.log(Level.INFO," file missed in the specified folder");
		
	}
	 
	 
	 
	 

}
