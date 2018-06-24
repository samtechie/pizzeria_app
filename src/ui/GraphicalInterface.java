 package ui;

import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import client.Client;
import client.PizzeriaClient;

@SuppressWarnings("unused")
public class GraphicalInterface {

	private JFrame frame;
	private JTextArea textArea;
	private JTextArea pizzeriaName;
	private JTextArea basePrice;
	private JTextArea updateResult;
	private String [] listOfPizzeria = new String[10];
	private PizzeriaClient client = new Client();
	/*
	 * Launch the program.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicalInterface window = new GraphicalInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/*
	 * Create the application.
	 */
	public GraphicalInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 540, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		// creating a buttomg for creating the pizzria for properties files
		JButton btnNewButton = new JButton("Create Configurations");
		// creating and adding a listener
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// file dialog to propmt the use to chosse the diretory of the file
				FileDialog fd = new FileDialog(new JFrame(), "Choose a file for the Vehicle", FileDialog.LOAD);
				fd.setVisible(true);
				String filename = fd.getDirectory() + "/" + fd.getFile();
				client.createPizzeria(filename);
				frame.repaint();

			}
		});
		btnNewButton.setBounds(10, 28, 129, 23);
		// adding the button to the pannel
		frame.getContentPane().add(btnNewButton);


		/*
		 * the same methodology as one above is used for the remeining of the program
		 * 
		 * */

		JButton btnPrintPizzerias = new JButton("Print Pizzerias");
		btnPrintPizzerias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pizeerias = client.printPizzerias();
				textArea.setText(pizeerias);

			}
		});
		btnPrintPizzerias.setBounds(10, 75, 129, 23);
		frame.getContentPane().add(btnPrintPizzerias);

		JButton btnUpdateBaseprice = new JButton("Update BasePrice");
		btnUpdateBaseprice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				double price =0;
				String pizzeria = "";
				try {
					if(!pizzeriaName.getText().equals("") && !basePrice.getText().equals("") ){
						pizzeria = pizzeriaName.getText();

						price = Double.parseDouble(basePrice.getText());
						updateResult.setText(client.updatePrice(pizzeria, price));

					}

					else 
						updateResult.setText("fields are empty");

				}catch (Exception e2){
					updateResult.setText("NaN");

				}
			}

		});
		btnUpdateBaseprice.setBounds(10, 121, 117, 23);
		frame.getContentPane().add(btnUpdateBaseprice);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(336, 11, 153, 89);
		frame.getContentPane().add(textArea);




		basePrice = new JTextArea();
		basePrice.setBounds(242, 150, 84, 23);
		frame.getContentPane().add(basePrice);

		JLabel lblEnterBasePrice = new JLabel("Enter Base Price");
		lblEnterBasePrice.setEnabled(false);
		lblEnterBasePrice.setBounds(241, 125, 106, 14);
		frame.getContentPane().add(lblEnterBasePrice);

		JLabel lblEnterThePizzeria = new JLabel("Enter the Pizzeria");
		lblEnterThePizzeria.setEnabled(false);
		lblEnterThePizzeria.setBounds(137, 125, 106, 14);
		frame.getContentPane().add(lblEnterThePizzeria);

		pizzeriaName = new JTextArea();
		pizzeriaName.setBounds(137, 150, 84, 23);
		frame.getContentPane().add(pizzeriaName);

		JLabel lblResult = new JLabel("Result");
		lblResult.setEnabled(false);
		lblResult.setBounds(346, 125, 88, 14);
		frame.getContentPane().add(lblResult);

		updateResult = new JTextArea();
		updateResult.setBounds(336, 150, 123, 23);
		frame.getContentPane().add(updateResult);




	}
}
