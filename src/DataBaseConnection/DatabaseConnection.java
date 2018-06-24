package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.PizzaConfig;


public class DatabaseConnection {

	private Connection myConnection;
	private PreparedStatement statement;

	/**
	 * DatabaseConnection is the default constructor that connects to the
	 * database
	 */
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String db = "jdbc:mysql://localhost:3306/pizzeria";
			//myConnection = DriverManager.getConnection(db, "dniwemug", "tartans");
			myConnection = DriverManager.getConnection(db, "root", "");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}


	}


	// the following function will check the login credentials of the user

	/*
	 *inserting the values of the configurations, no duplicates
	 *
	 *
	 **/
	public void configurePizzeria(PizzaConfig config) {

		config.getSizeOptions();
		String pisseriaName= config.getPizzeria();
		String [] opsetNames;
		String [] opNames;
		String [] opPrices;
		String qry;
		// avoiding duplicates
		qry ="SELECT * FROM `pizzeria`.`pizzera` WHERE (`pizzeriaName`) = (?);";
		try {
			statement = myConnection.prepareStatement(qry);
			statement.setString(1,pisseriaName);

			if(statement.executeQuery().next())
				return;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		// inserting the base price

		String query ="INSERT INTO `pizzeria`.`pizzera` (`pizzeriaName`,`basePrice`) VALUES (?,?);";
		try {
			statement = myConnection.prepareStatement(query);
			statement.setString(1,pisseriaName);
			statement.setString(2,""+config.getBasePrice());
			statement.executeUpdate();
			//optioSet



			// inserting optiosets and options
			int j=0;
			int size=0;

			//while(config.getOptionSets().get(i)!=null){


			String opSets= config.printOptioSets();
			opsetNames= opSets.split(",",config.getOptionSets().size());

			// over the optionsets
			for(String opsetName:opsetNames){
				qry=("INSERT INTO `pizzeria`.`optionset` (`name`,`pizzeriaId`) VALUES (?,?);");
				statement = myConnection.prepareStatement(qry);
				statement.setString(1,opsetName);
				statement.setString(2,pisseriaName);
				statement.executeUpdate();
				size=Integer.parseInt(config.findOptions(opsetName)[0]);
				opNames=config.findOptions(opsetName)[1].split(",",size);
				opPrices=config.findOptions(opsetName)[2].split(",",size);
				j=0;
				for(String opName: opNames){
					//codes for adding the names of the price into the the data base
					qry= "INSERT INTO `pizzeria`.`options` (`name`,`price`,`opSetId`,`pizzeriaName`) VALUES (?,?,?,?);";
					statement = myConnection.prepareStatement(qry);
					statement.setString(1,opName);
					statement.setString(2,opPrices[j]);
					statement.setString(3,opsetName);
					statement.setString(4,pisseriaName);



					statement.executeUpdate();
					j++;
				};

			}


			// adding size

			{{{
				String [] size_ =config.getSizeOptions();

				qry=("INSERT INTO `pizzeria`.`size` (`pizzeriaName`,`options`,`prices`) VALUES (?,?,?);");
				statement = myConnection.prepareStatement(qry);
				statement.setString(3,size_[1]);

				statement.setString(2,size_[0]);
				statement.setString(1,pisseriaName);
				statement.executeUpdate();

			}}}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}








		try{

			myConnection.close();
		}catch(SQLException e){

			e.getMessage();
		}

	}




	public PizzaConfig getConfig(String pizzeriaName) {
		PizzaConfig config = null;

		String query;

		String basePrice = null;
		ResultSet mySet;



		query = "SELECT * FROM `pizzeria`.`pizzera` WHERE `pizzeriaName`=?;";
		try {
			statement=myConnection.prepareStatement(query);
			statement.setString(1, pizzeriaName);
			mySet = statement.executeQuery();

			while(mySet.next()){

				basePrice = mySet.getString("basePrice");

			}

			if(basePrice==null){


				System.out.println("basePrice null");
				return null;
			}
			config= new PizzaConfig(pizzeriaName, Double.parseDouble(basePrice));

			/*
			 * reading size options
			 * 
			 * */

			String qry=	"SELECT * FROM `pizzeria`.`size` WHERE pizzeriaName=?";			

			statement = myConnection.prepareStatement(qry);


			statement.setString(1, pizzeriaName);
			mySet = statement.executeQuery();
			String opNames =null;
			String opPrices =null;
			String [] options,prices;
			double prices_[]= new double [3];
			if(mySet.next()){

				opNames= mySet.getString("options");
				opPrices= mySet.getString("prices");


			}

			options= opNames.split(",",3);
			prices= opPrices.split(",",3);

			int  i=0;
			for (String op: prices)
				prices_[i++]= Double.parseDouble(op);
			// set the size
			config.setSize(options, prices_);



			/**
			 * Reading the optionSet and options
			 * 
			 * 
			 * */
			String opSetName = null;
			String [] optionNames = null;
			double [] optionPrices=null;
			ArrayList <String> opNmes=new ArrayList<String>();
			ArrayList <String> opPric=new ArrayList<String>();

			qry= " SELECT * fROM `pizzeria`.`optionset`  WHERE pizzeriaId=?";
			statement = myConnection.prepareStatement(qry);
			statement.setString(1, pizzeriaName);
			mySet= statement.executeQuery();
			ResultSet mySet2;
			// for each OptionSet  create anew optionsets with options
			while(mySet.next()){

				//reseting the store of options
				opNmes.clear();
				opPric.clear();
				opSetName=mySet.getString("name");
				qry= " SELECT * FROM `pizzeria`.`options`  WHERE (`pizzeriaNAme`,`opSetId`)= (?,?)";
				statement = myConnection.prepareStatement(qry);
				statement.setString(1, pizzeriaName);
				statement.setString(2, opSetName);

				mySet2= statement.executeQuery();
				while(mySet2.next()){

					opNmes.add(mySet2.getString("name"));
					opPric.add(mySet2.getString("price"));

				}
				// parse arrayList to array
				optionNames = new String[opNmes.size()];
				optionPrices = new double[opNmes.size()];
				int k=0;
				for(String op:opNmes){
					optionNames[k]=op;
					optionPrices[k]=Double.parseDouble(opPric.get(k));
					k++;
				}
				config.addOptionSet(opSetName, optionNames, optionPrices);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 



		return config;
	}



	public String printPizzerias() {
		String query;
		String pizzeria="";
		ResultSet mySet;
		String delimiter ="";
		new ArrayList<String>();


		// selecting all pizzeriaName and basePrices
		query = "SELECT PizzeriaName , basePrice FROM `pizzeria`.`pizzera`";
		try {
			statement= myConnection.prepareStatement(query);
			mySet = statement.executeQuery();
			int i=0;
			while(mySet.next()){
				if(i++>0)
					delimiter="\n";
				pizzeria+=delimiter +mySet.getString("pizzeriaName") +" "+ 
						mySet.getString("basePrice");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		return pizzeria;
	}


	/*
	 * the functio to add an option to the existing configuration
	 * 
	 * 
	 * */
	public void addOption(String pizzeria, String opSet, String option, double price) {


		String qry= "INSERT INTO `pizzeria`.`options` (`name`,`price`,`opSetId`,`pizzeriaName`) VALUES (?,?,?,?);";


		try {
			statement= myConnection.prepareStatement(qry);
			statement.setString(1, option);
			statement.setString(2, ""+price);
			statement.setString(3, opSet);
			statement.setString(4, pizzeria);
			statement.executeUpdate();
			// closing the connection
			myConnection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}





	}



	public boolean updateBasePrice(String pizzerianame, double newPrice) {

		String query ="UPDATE `pizzeria`.`pizzera`  SET basePrice =? WHERE pizzeriaName =? ";

		try {
			statement = myConnection.prepareStatement(query);
			statement.setString(1, ""+newPrice);
			statement.setString(2, pizzerianame);

			statement.executeUpdate();;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return true;
		// TODO Auto-generated method stub

	}



	public void remove(String pizzeria) {

		String qry;

		qry ="DELETE FROM `pizzeria`.`pizzera` Where pizzeriaName =?";

		try {
			// deleting the pizzrria name and BasePrice
			statement = myConnection.prepareStatement(qry);
			statement.setString(1, pizzeria);
			statement.executeUpdate();

			//eleting the option sets
			qry ="DELETE FROM `pizzeria`.`optionset` Where pizzeriaId =?";
			statement = myConnection.prepareStatement(qry);
			statement.setString(1, pizzeria);
			statement.executeUpdate();


			// deleting options
			qry ="DELETE FROM `pizzeria`.`options` Where pizzeriaName =?";
			statement = myConnection.prepareStatement(qry);
			statement.setString(1, pizzeria);
			statement.executeUpdate();

			// deleting the size
			qry ="DELETE FROM `pizzeria`.`size` Where pizzeriaName =?";
			statement = myConnection.prepareStatement(qry);
			statement.setString(1, pizzeria);
			statement.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
