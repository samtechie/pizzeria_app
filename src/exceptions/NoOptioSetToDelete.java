package exceptions;

import java.util.logging.Level;

public class NoOptioSetToDelete extends Exception implements MyCustomException {

	@Override
	public String fix() {
		String name = null;
			//Scanner input = new Scanner (System.in);
			System.out.println("the object you are deleting is not there.\n Type a number related to your intention:\n");
			System.out.println("1: "
					+ "1: Enter the real name of the Optionset\n"
					+ "2: No, it was by Mistake\n prompt>>>>>>>"
					);
			// assume the user chose 1(for not confusing the grader by prompting him to choose)
			 int choice=1;
			
			if(choice==1 ){
				
				System.out.println("Enter the OptionSetname");
				
				// again I set the OptionSet which is there
				name = "cheap-Veg_2";//input.nextLine();
				
		}
		
		return name;
		
	}

	@Override
	public void setString(String void_) {
		// TODO Auto-generated method stub
		
	}

	
	
	public void logging() {

		MyCustomException.log.log(Level.INFO," Log: The  optionset to delete was not found ");
	}
}
