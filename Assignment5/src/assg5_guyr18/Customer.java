/**
 * @author: Robert Guy
 * Customer represents the properties of
 * a customer. The before-mentioned properties are:
 * 
 * 	1. An identification number (string).
 * 	2. A customer name string.
 *  3. A customer phone number string.
 */

package assg5_guyr18;

public class Customer extends KeyedItem<String>
{

	private String id;
	private String name;
	private String phoneNo;
	
	/** A three-parameter constructor that accepts a id,
	 *  name, and phone number.
	 * @param id: A id string.
	 * @param name: A name string.
	 * @param phoneNo: A phone number string.
	 */
	
	public Customer(String id, String name, String phoneNo)
	{
		
		super(id);
		this.id = id;
		this.name = name;
		this.phoneNo = phoneNo;
		
	}
	
	/**
	 * Returns the id associated with this customer.
	 * @return: The id string.
	 */
	
	public String getId()
	{
		
		return this.id;
		
	}
	
	/**
	 * Returns the name associated with this customer.
	 * @return: The customer name string.
	 */
	
	public String getName()
	{
		
		return this.name;
		
	}
	
	/**
	 * SetName(x) sets the name of this customer to
	 * some string x.
	 * @param x: A customer name.
	 */
	
	public void setName(String x)
	{
		
		this.name = x;
		
	}
	
	/**
	 * Returns the phone number associated with this customer.
	 * @return: The phone number string.
	 */
	
	public String getPhoneNo()
	{
		
		return this.phoneNo;
		
	}
	
	/**
	 * SetPhoneNo(x) sets the phone number of this account
	 * to a string x.
	 * @param x: A phone number string.
	 */
	
	public void setPhoneNo(String x)
	{
		
		this.phoneNo = x;
		
	}
	
	/** ToString() returns a line-by-line string representation
	 * 	of the properties of this class; namely, a id, name, and
	 *  phone number.
	 *  @return: The string representation of this class.
	 */
	
	@Override
	public String toString()
	{
		
		return "Customer ID: " + this.id + "\nCustomer Name: " + this.name + "\nCustomer Phone No.: " + this.phoneNo + "\n";
		
	}
}