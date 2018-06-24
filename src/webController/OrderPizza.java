package webController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import model.PizzaConfig;

/**
 * Servlet implementation class OrederPizza
 */
@WebServlet("/OrderPizza")
public class OrderPizza extends HttpServlet {
	static int count =0;
	private static final long serialVersionUID = 1L;
	static Client client = new Client();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderPizza() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// retriving the pizzeria name
		String pizzeria = request.getParameter("pizzerias").split(" ")[0];
		// retrieving the base price
		String basePrice = request.getParameter("pizzerias").split(" ")[1];
		// getting the optionsets available in the chosen pizzeria 
		String opSets=client.printOptionsets(pizzeria);		
		String opSets_ = "",options_="",optPrices="", size_="";


		// a printable configuration
		//System.out.println(pizzeria);
		PizzaConfig conf=client.printPizzeria(pizzeria);
        //System.out.println(conf);
		String sizes[]= conf.getSizeOptions();// sizes 
		String optionSets = conf.printOptioSets();// optionsets
		String optsets[] = optionSets.split(",",20);

		String resp[];//a var to get options of an option set
		String []ops = null;
		String []prices=null;
		String [] size = sizes[0].split(",",12);
		String [] sizeP=sizes[1].split(",",12);
		// retrieving sizes and options
		ArrayList <String[]> collect = new ArrayList<String[]>();
		ArrayList <String[]> collectPrice = new ArrayList<String[]>();


		// retrieving options and pricess
		for (String choice: optsets)
		{

			resp= conf.findOptions(choice);
			ops= resp[1].split(",",(Integer.parseInt(resp[0])));
			collect.add(ops);
			prices= resp[2].split(",",(Integer.parseInt(resp[0])));
			collectPrice.add(prices);
			System.out.println("this"+Arrays.toString(resp));
			System.out.println("this"+Arrays.toString(ops));
		}


		// options values for optionSets
		int k=0,count=0;


		options_+="<option value= \"selected\" disabled>Choose Pizza Type</option>";

		// iterating through optionSets
		int opsetCout=0;
		for (String ops_[]: collect){ // for each optionSet
			k=0;

			options_+="<option value= '' disabled>"+optsets[count++]+"</option>";

			for (String options: ops_){ // for each option
				System.out.println(options + " "+ k);
				options_+="<option value='"+options.trim()+":"+collectPrice.get(opsetCout)[k]+"'>"+options+" of "+collectPrice.get(opsetCout)[k++]+"</option>";

			}
			// for the next option set
			opsetCout++;
		}

		int i=0; // size counter
		size_+="<option value= \"selected\" disabled>Choose size</option>";
		for (String options: size){
			size_+="<option value='"+options.trim()+":"+sizeP[i].trim()+"'>"+options+" of "+sizeP[i++].trim()+"</option>";

		}


		response.getWriter().append("<html>"  // prepare the front end 
				+ "<body> "
				+ "<form action =\"CustomerOrder.jsp\" method = \"POST\"> "

				+"<select name = options>"
				+ options_ 
				+ "</select >"
				+"<select name = sizes class = size_color>"
				+ size_
				+ "</select >"
				+"<button type=\"submit\" value=\"Submit\">Submit</button>"

				+ "</form> "

				+ "</body>"

				+"<style> .size_color { color: blue; font-size: 14px;} "
				+ ".option_color { color: blue; font-size: 14px;}"
				+ ".op_color { color: blue; font-size: 14px;}"
				+ "</style>"
				+ "</html>");

		// creating a session ro carry the pizzeria information(name and base price)
		HttpSession session = request.getSession();
		ArrayList <String> pizz = new ArrayList<String>();
		pizz.add(pizzeria);
		pizz.add(basePrice);

		//adding the base price and the base price on the session
		session.setAttribute("pizz", pizz);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);

	}

}
