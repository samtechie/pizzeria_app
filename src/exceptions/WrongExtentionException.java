package exceptions;

import java.util.logging.Level;

public class WrongExtentionException extends Exception implements MyCustomException {


	String fileName;


	@Override
	public String getMessage() {
		return null;
	}

	
	
	public void logging() {

		MyCustomException.log.log(Level.INFO," Log: The extension was not valid");
	}
	@Override
	public String fix() {

		String newFile = repairExtension(fileName);
               newFile+=".txt";


		return newFile;
	}

	@Override
	public void setString(String fileName) {
		this.fileName= fileName;		
	} 

	// this handler only expexts txt files
	public String repairExtension(String filename){
char c;
		for (int i = filename.length()-1; i >=0; i--){

		if (( c =filename.charAt(i))=='.'){

				filename = filename.substring(0, i) ;
break;
			}

		}



		return filename;
	}



}
