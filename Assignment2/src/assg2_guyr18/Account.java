/**
 * @author Robert Guy
 * The Account class represents a bank account.
 */
package assg2_guyr18;

public class Account
{
	private String acctNo;
	private double balance;
	
	/**
	 * A constructor with a given account number.
	 * @param accountNum: The given account number.
	 */
	public Account(String accountNum)
	{
		
		acctNo = accountNum;
		balance = 0.0;
		
	}
	
	/**
	 * A two parameter constructor that accepts a given 
	 * account number and a default balance.
	 * @param accountNum: The given account number.
	 * @param bal: The default balance.
	 */
	public Account(String accountNum, double bal)
	{
		acctNo = accountNum;
		
		if(bal > -1)
		{
			
			balance = bal;
			
		}
	}
	
	/**
	 * Obtains the account number.
	 * @return The account number.
	 */
	public String getAcctNo()
	{
		
		return acctNo;
		
	}
	
	/**
	 * Returns the current balance of this account.
	 * @return The current balance.
	 */
	public double getBalance()
	{
		
		return balance;
		
	}
	
	/**
	 * Updates the balance of this account.
	 * @param bal: The new balance.
	 */
	public void setBalance(double bal)
	{
		
		balance = bal;
		
	}
	
	/**
	 * Deposits a specified amount into the balance of the account.
	 * @param amount: The given deposit amount.
	 */
	public void deposit(double amount)
	{
		
		if(amount > -1.0)
		{
			
			setBalance(balance + amount);
			System.out.println("Account.deposit() successfully deposited " + amount + " dollars.");
			
		} 
		else
		{
			
			System.out.println("Account.deposit() method cannot deposit a negative amount.");
			
		}
	}
	
	/**
	 * Withdraws a specified amount from the account.
	 * @param amount: The amount to withdraw.
	 */
	public void withdraw(double amount)
	{
		
		if(amount <= -1.0)
		{
			System.out.println("Account.withdraw() method cannot withdraw a negative amount.");
			
		}
		else if(amount > balance)
		{
			
			System.out.println("Account.withdraw() method has detected insuffucient funds.");
			
		}
		else
		{
			
			setBalance(balance - amount);
			System.out.println("Account.withdraw() successfully withdrew " + amount + " dollars.");
			
		}
	}
	
	/**
	 * Transfers a specified amount to the provided account.
	 * @param account: The target transfer account.
	 * @param transferAmount: The amount to transfer to parameter 'account'.
	 */
	public void transfer(Account account, double transferAmount)
	{
		
		if(transferAmount <= -1)
		{
			
			System.out.println("Account.transfer() method cannot transfer a negative amount.");
			
		}
		else if(transferAmount > balance)
		{
			
			System.out.println("Account.transfer() method has detected insufficent funds.");
			
		}
		else
		{
			
			setBalance(balance - transferAmount);
			account.setBalance(account.getBalance() + transferAmount);
			System.out.println("Account.transfer() method complete. Transferred " + transferAmount + " dollars");
			
		}
	}
	
	/**
	 * Displays the account number and balance of this account.
	 */
	public void displayInfo()
	{
		
		System.out.println("Account number: " + acctNo);
		System.out.println("Current balance: " + balance);
		
	}
	
	/**
	 * Returns a string with the account number and the current balance.
	 */
	@Override
	public String toString()
	{
		
		return "Account number: " + acctNo + "\nCurrent balance: " + balance;
		
	}
	
	/**
	 * Compares this Account instance to a provided object.
	 * @param obj: The object to compare to.
	 * @return Return true if this account has the same account number
	 * as the specified object. Otherwise, return false.
	 */
	@Override
	public boolean equals(Object obj)
	{
		
		boolean res = false;
		if(!(obj instanceof Account) ||  obj == null)
		{
			
			res = false;
			
		}
		else
		{
			
			Account acc = (Account)obj;
			res = acctNo.equals(acc.getAcctNo());
			
		}
		
		return res;
		
	}
}