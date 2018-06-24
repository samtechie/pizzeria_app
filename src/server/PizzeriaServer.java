
package server;

import java.net.ServerSocket;  
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
public abstract class PizzeriaServer implements PizzeriaInterface
{
	/**
	 * 
	 * this is the server class:
	 * 		It implements the PizzeriaInterface
	 * 		It delegates the implemeted methods to the threads
	 * 		for each new connection socket, it creates a new thread, and the latter handles the request
	 *      the server uses FIFO priority in dequeuing the list of the sockets
	 * 		 
	 * 
	 * **/
	// declaring and allocating memory for a store of sockets created by the server
	static ArrayList <Socket> sockets = new ArrayList<Socket>() ;

	@SuppressWarnings("resource")
	public static void main(String argv[]) throws Exception
	{
		Thread thread; 
		// creating a server socket
		ServerSocket mysocket = new ServerSocket(5555);
		// pointer to the storage of choice
		boolean useDB =false;
		
		System.out.println(" Server Running  " );
		/* choosing the store  */
         System.out.println("chose your preference by index\n"
         		+ "1.Use Database"
         		+ "2.Use Linked Hash Map");
         Scanner input = new Scanner(System.in);
         useDB=(input.nextDouble()==1);
		// the server should run forever
		while(true)
		{
			// wait for the socket connection and add it the store of sockets
			sockets.add (mysocket.accept());

			// if the socket store is not empty pass sockets to threads by first come first served
			if(!sockets.isEmpty()){
				thread = new Thread(new ServerOperations(sockets.remove(0),useDB));
				//start the thread
				thread.start();
			}

		}

	}
}