/**
 * @author: Robert Guy
 * The SavingsAccount class represents a
 * savings account and is a subclass of
 * Account.
 */
package assg2_guyr18;

public class SavingsAccount extends Account
{
	
	private double interestRate;
	
	/**
	 * A SavingsAccount constructor that accepts a single 
	 * account number.
	 * @param accountNum: The given account number.
	 */
	public SavingsAccount(String accountNum)
	{
		
		super(accountNum);
		interestRate = 0.0;
		
	}
	
	/**
	 * A three-parameter constructors for SavingsAccount that
	 * accepts a account number, a default balance, and an interest rate.
	 * @param accountNum: The given account number.
	 * @param bal: The default balance.
	 * @param rate: The associated interest rate.
	 */
	public SavingsAccount(String accountNum, double bal, double rate)
	{
		
		super(accountNum, bal);
		interestRate = rate;
		
	}
	
	/** 
	 * Obtains the interest rate associated with
	 * this account.
	 * @return The interest rate.
	 */
	public double getInterestRate()
	{
		
		return interestRate;
		
	}
	
	/**
	 * Modifies the interest rate associated
	 * with this account.
	 * @param rate: The modified interest rate value.
	 */
	public void setInterestRate(double rate)
	{
		
		interestRate = rate;
		
	}
	
	/**
	 * Computes the new balance of this account
	 * based on the interest rate.
	 */
	public void AddInterest()
	{
		
		this.setBalance((1.0 + interestRate) * this.getBalance());
		
	}
	
	/**
	 * Returns a string with the account number, current account balance,
	 * and the associated interest rate.
	 */
	@Override
	public String toString()
	{
		
		return super.toString() + "\nInterest rate: " + interestRate;
	
	}
	
	/**
	 * Displays the account number, account balance, and the 
	 * associated interest rate.
	 */
	@Override
	public void displayInfo()
	{
		
		super.displayInfo();
		System.out.println("Interest rate: " + interestRate);
		
	}
	
	/**
	 * Returns the index of the specified account number if found.
	 * Otherwise, this method throws an AccountNotFoundException.
	 * @param arr: The array to search for the account in.
	 * @param n: The size of the given array, 'arr'.
	 * @param accountNum: The account number to search for.
	 * @return: Returns an integer representing the index
	 * 			of the found account.
	 * @throws AccountNotFoundException
	 */
	public static int savingsAccountSearch(SavingsAccount[] arr, int n, String accountNum) throws AccountNotFoundException
	{
		
		for(int i = 0; i < n; i++)
		{
			
			if(arr[i] != null)
			{
				
				if(arr[i].getAcctNo().equals(accountNum))
				{
				
					return i;
					
				}
			}
		}
		
		throw new AccountNotFoundException("Account number not found.");
		
	}
}