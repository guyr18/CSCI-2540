/**
 * @author: Robert Guy
 * The class AccountSearchApplication
 * is the core class for this application
 * and contains the main method. 
 * 
 * This application reads a list of accounts
 * from an input file and allows users to search
 * for one or more accounts.
 */
package assg2_guyr18;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class AccountSearchApplication
{
	
	public static final String SOURCE_FILE = "assg2_input.txt";
	public static final String CHECKING = "C";
	public static final String SAVING = "S";
	public static final String QUERY_Y = "Y";
	public static final String QUERY_N = "N";
	public static final int ARR_SIZE = 100;
	private static CheckingAccount[] arrChecking;
	private static SavingsAccount[] arrSavings;
	private static int numChecking = 0;
	private static int numSaving = 0;
	private static int runCount = 0;
	private static boolean running = true;
	
	/**
	 * The main method of this application. Responsible for a variety
	 * of tasks such as parsing data from the source file, accepting user
	 * input, calling the search loop, and printing parsed data at
	 * the appropriate times. 
	 * @param args: A String array consisting of command-line parameters.
	 */
	public static void main(String[] args)
	{
		
		arrChecking = new CheckingAccount[AccountSearchApplication.ARR_SIZE];
		arrSavings = new SavingsAccount[AccountSearchApplication.ARR_SIZE];
		File inputFile = new File(AccountSearchApplication.SOURCE_FILE);
		
		try
		{
			
			Scanner scanner = new Scanner(inputFile);
			
			while(scanner.hasNext())
			{
				
				String accountType = scanner.next();
				String accountNum = scanner.next();
				double accountBal = scanner.nextDouble();
				double finalCol = scanner.nextDouble();
				
				if(isCheckingAccount(accountType))
				{
					
					arrChecking[numChecking] = new CheckingAccount(accountNum, accountBal, finalCol);
					numChecking++;
					
				}
				else
				{
					
					arrSavings[numSaving] = new SavingsAccount(accountNum, accountBal, finalCol);
					numSaving++;
					
				}
			}
			
			scanner.close();
			
		} catch(IOException e) {
			
			System.out.println(e.getMessage());
			running = false;
			System.exit(1);
			
		} 
		
		Scanner keyboard = new Scanner(System.in);
		String strYesNo = AccountSearchApplication.QUERY_Y;
		printAverageInfo(arrChecking, arrSavings);
		System.out.println("\n");
		printAccountData(arrChecking, true);
		System.out.println("\n");
		printAccountData(arrSavings, false);
		
		while(running)
		{
			
			if(runCount > 0)
			{
				
				System.out.print("\nDo you want to search for another Account? (Y/N)? ");
				strYesNo = keyboard.next();
				
			}
			
			dispatchSearchResponse(strYesNo, keyboard, arrChecking, arrSavings);
			runCount++;
			
		}
	}
	
	/**
	 * This method acts a event handler that dispatches an event based
	 * on the user input represented by parameter 's'.
	 * @param s: The user input.
	 * @param keyboard: A scanner object.
	 * @param a: A given array of checking accounts. 
	 * @param b: A given array of savings accounts.
	 */
	private static void dispatchSearchResponse(String s, Scanner keyboard, CheckingAccount[] a, SavingsAccount[] b)
	{
		
		if(s.toUpperCase().equals(AccountSearchApplication.QUERY_Y))
		{
			
			searchLoop(keyboard, a, b);
			
		} 
		else if(s.toUpperCase().equals(AccountSearchApplication.QUERY_N))
		{
					
			System.out.println("Ending application.");
			running = false;
			keyboard.close();
			System.exit(1);
					
		}
		else
		{
					
			System.out.println("Invalid input. Terminating.");
			running = false;
			keyboard.close();
			System.exit(1);
	
		}
	}
	
	/**
	 * Starts the search loop to accept user input and 
	 * search for the entered account number. This method
	 * accepts a search type and account number through
	 * user input.
	 * @param keyboard: The Scanner object that is accepted (input is read from this).
	 * @param a: The array of CheckingAccounts.
	 * @param b: The array of SavingsAccounts.
	 */
	private static void searchLoop(Scanner keyboard, CheckingAccount[] a, SavingsAccount[] b)
	{
		
		String searchType, accountNum;
		int selectedIndex = -1;
		boolean validSearchType = false;
		
		try
		{
			
			if(keyboard != null)
			{
				
				System.out.println("\nStart searching ....\n");
				System.out.println("Enter account type (C/S - C for Checking and S for Savings) to search: ");
				searchType = keyboard.next();
				validSearchType = isValidSearchType(searchType);

				if(!validSearchType)
				{
					
					System.out.println("Invalid account type.");
					return;
					
				}
					
				System.out.println("Enter account number: ");
				accountNum = keyboard.next();
				
				if(isCheckingAccount(searchType))
				{
				
					selectedIndex = CheckingAccount.checkingAccountSearch(a, numChecking, accountNum);
					a[selectedIndex].displayInfo();
				
				}
				else
				{
				
					selectedIndex = SavingsAccount.savingsAccountSearch(b, numSaving, accountNum);
					b[selectedIndex].displayInfo();
						
				}
			}
			
		} catch(AccountNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		} catch(NullPointerException e) {
			
			System.out.println(e.getMessage());
			
		}
	}
		
	/**
	 * Determines if the account type found in user input
	 * is an acceptable value.
	 * @param type: The account type entered.
	 * @return: A boolean value. True, if the account type
	 * 			is valid, and false otherwise.
	 */
	private static boolean isValidSearchType(String type)
	{
		
		String upperCaseType = type.toUpperCase();
		return upperCaseType.equals(AccountSearchApplication.CHECKING) || upperCaseType.equals(AccountSearchApplication.SAVING);
		
	}
	
	/**
	 * Prints data relative to a given array of account objects.
	 * @param a: The provided array:
	 * @param isChecking: A boolean value indicating if the 
	 * 					  provided array is an instance of CheckingAccount objects.
	 */
	private static void printAccountData(Account[] a, boolean isChecking)
	{
		
		String header = isChecking ? "All the checking accounts: " : "All the savings accounts: ";
		System.out.println(header);
		
		for(Account obj : a)
		{
			
			if(obj != null)
			{
				
				String accountNum = obj.getAcctNo();
				double accountBal = obj.getBalance();
				System.out.println(accountNum + ", " + accountBal);
			
			}
		}
	}
	
	/**
	 * Prints info related to the length of two givens arrays
	 * (a and b) along with the average balance of said arrays.
	 * @param a: A given array of checking account objects.
	 * @param b: A given array of savings account objects.
	 */
	private static void printAverageInfo(CheckingAccount[] a, SavingsAccount[] b)
	{
		
		double avgBalA = getAverageBalance(a, numChecking);
		double avgBalB = getAverageBalance(b, numSaving);
		System.out.println("Number of checking accounts: " + numChecking);
		System.out.println("Average balance of all checking accounts: $" + avgBalA);
		System.out.println("Number of savings accounts: " + numSaving);
		System.out.println("Average balance of all savings accounts: $" + avgBalB);
		
	}
	
	/** 
	 * Computes the average balance of accounts
	 * within a given array, 'arr'.
	 * @param arr: The given array
	 * @return An integer representing the average balance.
	 */
	private static double getAverageBalance(Account[] arr, double num)
	{
		
		double sum = 0.0;
		
		for(Account a : arr)
		{
			
			if(a != null)
			{
				
				sum += a.getBalance();
			
			}
		}
		
		return sum / num;
		
	}
	
	/**
	 * Compares a given string to determine if it
	 * is checking account or not.
	 * @param s: The string to check (account type)
	 * @return A boolean value. True, if s is equal
	 * to the checking account type constant, and false
	 * otherwise.
	 */
	private static boolean isCheckingAccount(String s)
	{
		
		String upperCaseS = s.toUpperCase();
		return upperCaseS.equals(AccountSearchApplication.CHECKING);
		
	}
}