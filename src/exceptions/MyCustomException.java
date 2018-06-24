package exceptions;

import java.util.logging.*;

public interface  MyCustomException  {

	public static final Logger log= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	boolean logger = false;
	public String getMessage() ;
	public String fix();
	public void setString(String void_);
	
	public void logging();
	

}
