package webController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import client.Client;

/**
 * Servlet implementation class ShowList
 */
@WebServlet("/ShowList")
public class ShowList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList <String> aList = new ArrayList<String>();
		// client object
		Client client = new Client();
		
		// the printable list of pizzerias
		String list=client.printPizzerias();
		PrintWriter out = response.getWriter();
        
        String list_[]= list.split("\n",100);
        
        int i=0;
        for(String pizz:list_)// an arraList of pizzeria names
        	aList.add(i,pizz);
        
        String options=""; 
        String field =null;

        for(int k=0;k<aList.size();k++) { // prepare a list of pizzeria names

            field=aList.get(k);
            options += "<option value='"+field+"'>"+field+"</option>";
        }
        out.print("<html>"   // front end 
        		+"<body>"
        		+ "<form  action = \"OrderPizza\" method = \"POST\">"
        		+ "<select name= pizzerias>"
        		+ options
        		+ "</select>"
        		+"<button type=\"submit\" value=\"Submit\">Submit</button>"
        		+ "</from>"
        		+ "</body>"
        		+ "</html>");
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
