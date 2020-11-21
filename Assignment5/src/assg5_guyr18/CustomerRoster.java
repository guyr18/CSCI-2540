/**
 * @author: Robert Guy
 * CustomerRoster is a class that handles
 * various operations on company customer
 * data. These operations include, but are 
 * not limited to: data storage, data retrieval,
 * data insertion, data deletion, and data
 * modification.
 * 
 * The functionality of these operations are
 * all represented by a binary search tree
 * data structure.
 */

package assg5_guyr18;

import java.util.*;
import java.io.*;

public class CustomerRoster
{
	
	private BinarySearchTree<Customer, String> bst;
	private String filepath;
	private BufferedReader reader;
	private TreeIterator<Customer> treeIterator;
	private boolean newDataExists = false;
	private ArrayList<Customer> traversalList;
	
	
	/**
	 * A one-parameter constructor that accepts a file path for
	 * a corresponding input file. This method also initializes
	 * a binary search tree data structure.
	 * @param filepath: The source file path.
	 */
	
	public CustomerRoster(String filepath)
	{
		
		this.bst = new BinarySearchTree<Customer, String>();
		this.filepath = filepath;
		this.traversalList = new ArrayList<Customer>();
		
	}
	
	/** LoadData() inserts data into a binary search tree.
	 *  The information is loaded from an external file that
	 *  can be found at the file path string that is accepted
	 *  when constructing a CustomerRoster.
	 * @return: Returns true if the file is successfully loaded.
	 * 	And otherwise, would return false if an IOException occurs.
	 */
	
	public boolean loadData()
	{
		
		try
		{
			
			reader = new BufferedReader(new FileReader(filepath));
			String line = "";
			
			while((line = reader.readLine()) != null)
			{
				
				StringTokenizer st = new StringTokenizer(line);
				String id = st.nextToken();
				String name = st.nextToken();
				String phoneNo = st.nextToken();
				Customer c = new Customer(id, name, phoneNo);
				bst.insert(c);
				traversalList.add(c);
				
			}
			
			treeIterator = new TreeIterator<Customer>(bst);
			reader.close();
			
		} 
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			return false;
			
		}
		
		return true;
		
	}
	
	/** DisplayRoster() displays the current customer roster.
	 */
	
	public void displayRoster()
	{
				
		while(treeIterator.hasNext())
		{
			
			Customer c = treeIterator.next();
			System.out.println(c.toString());
		}
	}
	
	/**
	 * SearchCustomerById(id) returns a customer object that
	 * has the equivalent identifying string of 'id'.
	 * @param id: The customer lookup id.
	 * @return: Returns a customer object. The object will be null if
	 * 			no customer exists with the specified id. Otherwise, it
	 * 			will be defined.
	 */
	
	public Customer searchCustomerById(String id)
	{
		
		Customer c = (Customer)bst.retrieve(id);
		return c;
		
	}
	
	/**
	 * DeleteCustomerById(id) deletes a customer with the
	 * provided id.
	 * @param id: A given customer lookup id.
	 * @return: Returns a defined customer object that is a
	 * 			temporary reference of the deleted customer.
	 * 			If no such customer exists, it will return null.
	 */
	
	public Customer deleteCustomerById(String id)
	{
		
		Customer c = (Customer)bst.retrieve(id);
		
		if(c != null)
		{
			
			bst.delete(id);
			traversalList.remove(c);
			newDataExists = true;
			
		}
		
		return c;
		
	}
	
	/** 
	 * AddCustomer(id, name, no) inserts a customer into the binary
	 * search tree.
	 * @param id: The new customer id.
	 * @param name: The new customer name.
	 * @param no: The new customer phone number.
	 * @return: Returns a boolean indicating the success of the operation.
	 * 			If the provided customer id is taken, it will return false.
	 * 			Otherwise, a boolean value of true will be returned.
	 */
	
	public boolean addCustomer(String id, String name, String no)
	{
		
		boolean available = (Customer)bst.retrieve(id) == null;
		
		if(available)
		{
			
			no = no.replace("-", "");
			Customer c = new Customer(id, name, no);
			bst.insert(c);
			traversalList.add(c);
			newDataExists = true;
			
		}
		
		return available;
		
	}
	
	/** Sort() sorts customer data within the binary search tree.
	 */
	
	public void sort()
	{		
		
		bst = new BinarySearchTree<>();
		
		for(Customer c : traversalList)
		{
			
			bst.insert(c);
			
		}
		
		treeIterator = new TreeIterator<Customer>(bst);
		treeIterator.setInorder();
		
	}
	
	/** Save() saves the current customer data in the binary search tree
	 * 	by overwriting the input file.
	 * @return: Returns true if no exception occurs (such as IOException), 
	 * 			and false if such an error does occur.
	 */
	
	public boolean save()
	{
		
		try
		{
			
			if(newDataExists)
			{
				
				PrintWriter writer = new PrintWriter(new FileOutputStream(filepath, false));
				treeIterator.setInorder();
				
				while(treeIterator.hasNext())
				{
				
					Customer c = treeIterator.next();
					String s = c.getId() + "\t" + c.getName() + "\t" + c.getPhoneNo();
					writer.println(s);
				
				}
			
				writer.close();
				newDataExists = false;
			
			}
			else
			{
				
				return false;
				
			}
			
		}
		catch(IOException e)
		{
			
			System.out.println(e.getMessage());
			return false;
			
		}
		
		return true;
		
	}
}