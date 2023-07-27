package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT = "Exit";
	private Scanner userInput = new Scanner(System.in);

	public Scanner getUserInput() {
		return userInput;
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {

		while (true) {
			/*
			 * TODO: Use a method to initialize this value
			 */
			String choice = "";
			File vendingOptions = new File("main.csv");

			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			choice = getUserInput().nextLine();

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				try (Scanner reader = new Scanner(vendingOptions)){
					while (reader.hasNextLine()) {
						System.out.println(reader.nextLine());
					}
				} catch (FileNotFoundException e) {
					System.out.println("Error! Please try again or contact a technician.");
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					System.out.println("Current Money Provided: " + );
					System.out.println("(1) Feed Money");
					System.out.println("(2) Select Product");
					System.out.println("(3) Finish Transaction");
					choice = getUserInput().nextLine();

				}
				// do purchase

			} else if (choice.equals(EXIT)) {
				System.exit(1);
			}

			// Remove the break when you've figured out how to setup the input loop

			System.out.println();
		}
	}
}


