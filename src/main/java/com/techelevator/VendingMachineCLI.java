package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI extends PurchaseCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private int itemsPurchasedCounter = 0;
	private Map<String, String> vendingOptionCodeNamePriceStock = new HashMap<>();
	private String choice = "";
	private File vendingOptions = new File("main.csv");

	private Scanner userInput = new Scanner(System.in);

	public int getItemsPurchasedCounter() {
		return itemsPurchasedCounter;
	}

	public Scanner getUserInput() {
		return userInput;
	}

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {
		List<Item> vendingOptionCodeNamePrice = new ArrayList<>();


		try (Scanner reader = new Scanner(vendingOptions)) {
			while (reader.hasNextLine()) {
				String vendingOptionsLine = reader.nextLine();
				String[] vendingOptionsLineSplit = vendingOptionsLine.split(",");
				if (Objects.equals(vendingOptionsLineSplit[3], "Candy")) {
					vendingOptionCodeNamePrice.add(new Candy(BigDecimal.valueOf(Double.parseDouble(vendingOptionsLineSplit[2])), vendingOptionsLineSplit[0], vendingOptionsLineSplit[1], 5, "Yummy Yummy, So Sweet!"));
				} else if (Objects.equals(vendingOptionsLineSplit[3], "Gum")) {
					vendingOptionCodeNamePrice.add(new Gum(BigDecimal.valueOf(Double.parseDouble(vendingOptionsLineSplit[2])), vendingOptionsLineSplit[0], vendingOptionsLineSplit[1], 5, "Chew Chew, Yum!"));
				} else if (Objects.equals(vendingOptionsLineSplit[3], "Munchy")) {
					vendingOptionCodeNamePrice.add(new Munchy(BigDecimal.valueOf(Double.parseDouble(vendingOptionsLineSplit[2])), vendingOptionsLineSplit[0], vendingOptionsLineSplit[1], 5, "Crunch Crunch, Yum!"));
				} else if (Objects.equals(vendingOptionsLineSplit[3], "Drink")) {
					vendingOptionCodeNamePrice.add(new Drink(BigDecimal.valueOf(Double.parseDouble(vendingOptionsLineSplit[2])), vendingOptionsLineSplit[0], vendingOptionsLineSplit[1], 5, "Glug Glug, Yum!"));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error! Please try again or contact a technician.");
		}
		/*
		Loop through the file
		      split
		      if the line contains a Candy, make an object of type Candy, put that Candy object into your Map or List
		      else if the line contains a Drink, make an object of type Drink, put the Drink into the list
				vendingOptionCodeNamePriceStock.put(lineArr[0], new Candy(lineArr[1]....) )
		*/
		while (true) {
			/*
			 * TODO: Use a method to initialize this value
			 */


			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			choice = getUserInput().nextLine();


			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS) || choice.equals("1")) {
				// display vending machine items
				try (Scanner reader = new Scanner(vendingOptions)) {
					while (reader.hasNextLine()) {
						System.out.println(reader.nextLine());
					}
				} catch (FileNotFoundException e) {
					System.out.println("Error! Please try again or contact a technician.");
				}
				System.out.println();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE) || choice.equals("2")) {
				while (true) {
					System.out.println("Current Money Provided: $" + getCurrentMoneyProvided());
					System.out.println("(1) Feed Money");
					System.out.println("(2) Select Product");
					System.out.println("(3) Finish Transaction");
					String choice2 = getUserInput().nextLine();
					if (choice2.equals(PURCHASE_MENU_FEED_MONEY) || choice2.equals("1")) {
						System.out.println("Amount of money to add");
						setMoneyFed(BigDecimal.valueOf(Double.parseDouble(getUserInput().nextLine())).setScale(2, RoundingMode.HALF_UP)); // this might not work!
						addToCurrentMoney(getCurrentMoneyProvided(), getMoneyFed());//TODO incorporate writer.ln LocalDateTime FEED MONEY getMoneyFed & getCurrentMoneyProvided
					} else if (choice2.equals(PURCHASE_MENU_SELECT_PRODUCT) || choice2.equals("2")) {
						for (Item item : vendingOptionCodeNamePrice) {
							System.out.println(item.getSlotLocation() + " - " + item.getProductName() + " - $" + item.getPrice());
						}
						System.out.println();
						System.out.println("Please select an item code (i.e. A1):");
						String selection = userInput.nextLine();
						boolean validity = false;
						boolean oos = false;
						for (Item item : vendingOptionCodeNamePrice) {
							if (item.getSlotLocation().equals(selection) && item.getStock() <= 0) {
								oos = true;
								System.out.println("The item is now out of stock");
								System.out.println();
								break;
							}
							if (item.getSlotLocation().equals(selection) && item.getStock() > 0) {
								validity = true;
								if (getCurrentMoneyProvided().compareTo((item.getPrice())) < 0) {
									System.out.println("Insufficient funds.");
								} else if (getCurrentMoneyProvided().compareTo((item.getPrice())) >= 0 && itemsPurchasedCounter % 2 == 0) {
									subtractFromCurrentMoney(getCurrentMoneyProvided(), item.getPrice());
									System.out.println(item.dispense());
									itemsPurchasedCounter++;
									item.takeOutOfStock();
									//TODO  writerprintln LocalDateTime item.getproductname item.getitemcode item.get price currentmoneyprovided
								}
								else {item.setPrice(item.getPrice().subtract(BigDecimal.valueOf(1)));
									subtractFromCurrentMoney(getCurrentMoneyProvided(), item.getPrice());
									System.out.println(item.dispense());
									itemsPurchasedCounter++;
									item.setPrice(item.getPrice().add(BigDecimal.valueOf(1)));
									item.takeOutOfStock();
									//TODO writerprintln LocalDateTime item.getproductname item.getitemcode item.get price-1 currentmoneyprovided
								}
							}
						}
						if (!validity && !oos) {
							System.out.println("The itemcode was not found!");
							System.out.println();
						}
					} else if (choice2.equals(FINISH_TRANSACTION) || choice2.equals("3")) {
						System.out.println();
						makeChange(getCurrentMoneyProvided());
						System.out.println();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_EXIT) || choice.equals("3")) {
				System.exit(1); //TODO make change & finish transaction
//					PrintWriter writer = new PrintWriter
//							writer.println(LocalDateTime );
			}
		}
	}
	//	choice = getUserInput().nextLine();

}
// do purchase

		// Remove the break when you've figured out how to setup the input loo



