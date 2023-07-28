package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingMachineCLI extends PurchaseCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private int itemsPurchasedCounter = 0;
	private String choice = "";
	private File vendingOptions = new File("alternate.csv");

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
		File file = new File("logger.txt");
		Logger logger = new Logger (file);

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
		} catch (ArrayIndexOutOfBoundsException f) {
			System.out.println("Error! File: <" + vendingOptions.getPath() + "> has been corrupted.");
			System.exit(0);
		}
		while (true) {

			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");
			choice = getUserInput().nextLine();

			if (choice.equalsIgnoreCase(MAIN_MENU_OPTION_DISPLAY_ITEMS) || choice.equals("1")) {
				// display vending machine items
				try (Scanner reader = new Scanner(vendingOptions)) {
					while (reader.hasNextLine()) {
						System.out.println(reader.nextLine());
						//TODO **OPTIONAL** print the list instead of readerNL for ~customization~ 3:22 7/27
					}
				} catch (FileNotFoundException e) {
					System.out.println("Error! Please try again or contact a technician.");
				}
				System.out.println();
			} else if (choice.equalsIgnoreCase(MAIN_MENU_OPTION_PURCHASE) || choice.equals("2")) {
				while (true) {
					System.out.println("Current Money Provided: $" + getCurrentMoneyProvided());
					System.out.println("(1) Feed Money");
					System.out.println("(2) Select Product");
					System.out.println("(3) Finish Transaction");
					String choice2 = getUserInput().nextLine();
					if (choice2.equals(PURCHASE_MENU_FEED_MONEY) || choice2.equals("1")) {
						System.out.print("\n" + "Please enter an amount of money to add (i.e. 5):   ");
						try {
							setMoneyFed(BigDecimal.valueOf(Double.parseDouble(getUserInput().nextLine())).setScale(2, RoundingMode.HALF_UP));//TODO confirm taking in doubles as opposed to ints is okay
							addToCurrentMoney(getCurrentMoneyProvided(), getMoneyFed());
							logger.write("FEED MONEY $" + getMoneyFed() + " $" + getCurrentMoneyProvided());
						} catch (NumberFormatException e) {
							System.out.println("\n" + "No number value detected." + "\n");
						}
					} else if (choice2.equals(PURCHASE_MENU_SELECT_PRODUCT) || choice2.equals("2")) {
						for (Item item : vendingOptionCodeNamePrice) {
							System.out.println(item.getSlotLocation() + " - " + item.getProductName() + " - $" + item.getPrice());
						}
						System.out.print("\n" + "Please select an item code (i.e. A1):    ");
						String selection = userInput.nextLine();
						boolean validity = false;
						boolean oos = false;
						for (Item item : vendingOptionCodeNamePrice) {
							if (item.getSlotLocation().equals(selection) && item.getStock() <= 0) {
								oos = true;
								System.out.println("\n" + "The item is now out of stock" + "\n");
								break;
							}
							if (item.getSlotLocation().equals(selection) && item.getStock() > 0) {
								validity = true;
								if (getCurrentMoneyProvided().compareTo((item.getPrice())) < 0) {
									System.out.println("\n" + "Insufficient funds." + "\n");
								} else if (getCurrentMoneyProvided().compareTo((item.getPrice())) >= 0 && itemsPurchasedCounter % 2 == 0) {
									subtractFromCurrentMoney(getCurrentMoneyProvided(), item.getPrice());
									System.out.println(item.dispense());
									itemsPurchasedCounter++;
									item.takeOutOfStock();
									logger.write(item.getProductName() + " " + item.getSlotLocation() + " $" + item.getPrice() + " $" + getCurrentMoneyProvided());
								}
								else {item.setPrice(item.getPrice().subtract(BigDecimal.valueOf(1)));
									subtractFromCurrentMoney(getCurrentMoneyProvided(), item.getPrice());
									System.out.println(item.dispense());
									itemsPurchasedCounter++;
									item.setPrice(item.getPrice().add(BigDecimal.valueOf(1)));
									item.takeOutOfStock();
									logger.write(item.getProductName() + " " + item.getSlotLocation() + " $" + item.getPrice().subtract(BigDecimal.valueOf(1)) + " $" + getCurrentMoneyProvided());
								}
							}
						}
						if (!validity && !oos) {
							System.out.println("\n" + "The itemcode was not found!" + "\n");
						}
					} else if (choice2.equals(FINISH_TRANSACTION) || choice2.equals("3")) {
						System.out.println();
						logger.write("GIVE CHANGE " + " $" + getCurrentMoneyProvided() + " $0.00");
						makeChange(getCurrentMoneyProvided());
						System.out.println();
						break;
					}
				}
			} else if (choice.equalsIgnoreCase(MAIN_MENU_EXIT) || choice.equals("3")) {
				System.exit(1);
			}
		}
	}
}