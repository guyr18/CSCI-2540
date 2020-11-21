/**
 * @author: Robert Guy
 * CustomerMIS is the core class for this application
 * and is responsible for handling all user input.
 */

package assg5_guyr18;

import java.io.*;
import java.util.*;

public class CustomerMIS
{
	
	// The source path of the input file for this application.
	public static final String FILE_PATH = "assg5_CustomerRoster.txt";
	private static CustomerRoster roster;
	private static BufferedReader reader;
	private static boolean running = true;
	
	/**
	 * Main(args) is the entry point method for this application
	 * and accepts a string array of command-line arguments.
	 * @param args: A string array of command-line arguments.
	 */
	
	public static void main(String[] args)
	{
		
		roster = new CustomerRoster(CustomerMIS.FILE_PATH);
		roster.loadData();
		
		try
		{
			
			reader = new BufferedReader(new InputStreamReader(System.in));
			String yesNo = "";
			
			while(running)
			{
				
				showMenu();
				handleUserInput(reader.readLine());
				System.out.println("Would you like to enter another command (Y/N)?");
				yesNo = reader.readLine();
				
				if(yesNo.toUpperCase().equals("Y"))
				{
					
					continue;
					
				}
				else if(yesNo.toUpperCase().equals("N"))
				{
					
					running = false;
					System.out.println("Exiting application.");
					System.exit(0);
					
				}
				else
				{
					
					running = false;
					System.out.println("Unrecognized command to (Y/N).\nExiting application.");
					System.exit(0);
					
				}
			}
		} 
		catch(IOException e)
		{
			
			running = false;
			System.out.println(e.getMessage());
			System.exit(0);
			
		}	
	}
	
	/** Displays the console menu for this application.
	 */
	
	private static void showMenu()
	{
		
		System.out.println("1. Display customer roster (sorted)");
		System.out.println("2. Add a customer by Id");
		System.out.println("3. Delete a customer by Id");
		System.out.println("4. Search for customer by Id");
		System.out.println("5. Update customer name by Id");
		System.out.println("6. Update customer phone number by Id");
		System.out.println("7. Save and exit");
		
	}
	
	/** HandleUserInput(cmd) processes user input based on
	 * 	the read command 'cmd'.
	 * @param cmd: The input command.
	 */
	
	private static void handleUserInput(String cmd)
	{
			
		if(cmd.equals("1"))
		{
			
			roster.sort();
			roster.displayRoster();
			
		}
		else if(cmd.equals("2"))
		{
			
			tryAddCustomer();
			
		}
		else if(cmd.equals("3"))
		{
			
			tryDeleteCustomer();
			
		}
		else if(cmd.equals("4"))
		{
			
			tryCustomerSearch();
			
		}
		else if(cmd.equals("5"))
		{
			
			tryUpdateCustomerName();
			
		}
		else if(cmd.equals("6"))
		{
			
			tryUpdateCustomerPhoneNo();
			
		}
		else if(cmd.equals("7"))
		{
			
			trySaveExit();
			
		}
		else
		{
			
			System.out.println("Unrecognized menu code. Exiting app.");
			running = false;
			System.exit(0);
			
		}
	}
	
	/**
	 * TryAddCustomer() signals relative command prompts and
	 * processes user input necessary to add a new customer.
	 */
	
	private static void tryAddCustomer()
	{
		
		try
		{
			
			System.out.println("Please enter a customer id, name and phone number such as (id, name, phone number):");
			String input = reader.readLine();
			StringTokenizer st = new StringTokenizer(input);
			String id = st.nextToken(",");
			String name = st.nextToken(",");
			String no = st.nextToken();
			id = id.replace(" ", "");
			name = name.replace(" ", "");
			no = no.replace(" ", "");
			boolean success = roster.addCustomer(id, name, no);
			
			if(!success)
			{
			
				System.out.println("The specified id already exists.");
			
			}
			else
			{
				
				System.out.println("Customer successfully added.");
			
			}
			
		} 
		catch(NoSuchElementException e)
		{
			
			System.out.println("The provided answer(s) were formatted incorrectly.");
			
		} 
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * TryDeleteCustomer() signals relative command prompts and
	 * processes user input necessary to delete a customer.
	 */
	
	private static void tryDeleteCustomer()
	{
		
		try
		{
			
			String input = "";
			System.out.println("Enter a customer id to delete: ");
			input = reader.readLine();
			Customer c = roster.deleteCustomerById(input);
		
			if(c == null)
			{
			
				System.out.println("No customer exists with this id.");
			
			}
			else
			{
			
				System.out.println("Customer successfully deleted.");
			
			}
			
		} 
		catch(NoSuchElementException e)
		{
			
			System.out.println("The provided answer(s) were formatted incorrectly.");
			
		} 
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * TryCustomerSearch() signals relative command prompts and
	 * processes user input necessary to search for a customer.
	 */
	
	private static void tryCustomerSearch()
	{
		
		try
		{
			
			String input = "";
			System.out.println("Enter a customer id to look for: ");
			input = reader.readLine();
			Customer c = roster.searchCustomerById(input);
		
			if(c == null)
			{
			
				System.out.println("This customer does not exist.");
			
			}
			else
			{
			
				System.out.println("Customer Name: " + c.getName());
				System.out.println("Customer Phone Number: " + c.getPhoneNo());
			
			}
			
		} 
		catch(NoSuchElementException e)
		{
			
			System.out.println("The provided answer(s) were formatted incorrectly.");
			
		} 
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * TryUpdateCustomerName() signals relative command prompts and
	 * processes user input necessary to update a customer's name.
	 */
	
	private static void tryUpdateCustomerName()
	{
		
		try
		{
			
			String input = "";
			System.out.println("Enter the id of the customer whose name you would like to update: ");
			input = reader.readLine();
			Customer c = roster.searchCustomerById(input);
		
			if(c == null)
			{
			
				System.out.println("No customer exists with that id. Update failed.");
			
			}
			else
			{
			
				System.out.println("Enter the new name for this customer: ");
				input = reader.readLine();
				c.setName(input);
				System.out.println("Customer name updated.");
			
			}
			
		} 
		catch(NoSuchElementException e)
		{
			
			System.out.println("The provided answer(s) were formatted incorrectly.");
			
		} 
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * TryUpdateCustomerPhoneNo() signals relative command prompts and
	 * processes user input necessary to update a customer's phone number.
	 */
	
	private static void tryUpdateCustomerPhoneNo()
	{
		
		try
		{
			
			String input = "";
			System.out.println("Enter the id of the customer whose phone number you would like to update: ");
			input = reader.readLine();
			Customer c = roster.searchCustomerById(input);
		
			if(c == null)
			{
			
				System.out.println("No customer exists with that id. Update failed.");
			
			}
			else
			{
			
				System.out.println("Enter the new phone number for this customer: ");
				input = reader.readLine();
				input = input.replace("-", "");
				c.setPhoneNo(input);
				System.out.println("Customer phone number updated.");
			
			}
			
		}
		catch(NoSuchElementException e)
		{
			
			System.out.println("The provided answer(s) were formatted incorrectly.");
			
		} 
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * TrySaveExit() saves the current customer roster to the
	 * input file and exits the application.
	 */
	
	private static void trySaveExit()
	{
		
		System.out.println("Saving roster to input file: " + CustomerMIS.FILE_PATH);
		boolean success = roster.save();
		
		if(success)
		{
			
			System.out.println("Input file [" + CustomerMIS.FILE_PATH + "] successfully updated.");

		}
		else
		{
			
			System.out.println("No new data exists or the source file could not be loaded.");
			
		}
		
		running = false;
		System.out.println("Exiting application.");
		System.exit(0);
		
	}
}