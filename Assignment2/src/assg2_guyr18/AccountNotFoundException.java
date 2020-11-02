/**
 * @author: Robert Guy
 * AccountNotFoundException is a checked
 * exception that is thrown whenever an invalid
 * account number search occurs.
 */
package assg2_guyr18;

import java.lang.Exception;

public class AccountNotFoundException extends Exception
{
	
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public AccountNotFoundException()
	{
		
		super("Account number not found.");
		
	}
	
	/**
	 * This is a one-parameter constructor
	 * @param msg: The given message.
	 */
	public AccountNotFoundException(String msg)
	{
		
		super(msg);
		
	}
}
