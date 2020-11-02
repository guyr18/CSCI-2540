/**
 * @author: Robert Guy
 * The CheckingAccount class represents a 
 * checking account and is a subclass of
 * the Account class.
 */
package assg1_guyr18;

public class CheckingAccount extends Account
{
	
	private double overdraft;
	private static double fee;
	
	/**
	 * A constructor with a given account number.
	 * @param accountNum
	 */
	public CheckingAccount(String accountNum)
	{
		
		super(accountNum);
		overdraft = 0.0;
		
	}
	
	/**
	 * A three-parameter constructors that takes a given
	 * account number, initial balance, and an overdraft amount;
	 * @param accountNum: The given account number.
	 * @param bal: The initial balance of the account.
	 * @param overdraftFee: The provided overdraft amount.
	 */
	public CheckingAccount(String accountNum, double bal, double overdraftAmt)
	{
		
		super(accountNum, bal);
		overdraft = overdraftAmt;
		
	}
	
	/**
	 * Obtains the overdraft amount associated with this account.
	 * @return The overdraft amount.
	 */
	public double getOverdraft()
	{
		
		return overdraft;
		
	}
	
	/**
	 * Updates the current overdraft amount of this account.
	 * @param od
	 */
	public void setOverdraft(double od)
	{
		
		overdraft = od;
		
	}
	
	/**
	 * Obtains the overdraft fee associated with this account.
	 * @return The overdraft fee.
	 */
	public static double getFee()
	{
		
		return fee;
		
	}
	
	/**
	 * Updates the fee that is charged for an overdraft.
	 * @param f: The updated fee amount.
	 */
	public void setFee(double f)
	{
		
		fee = f;
		
	}
	
	/**
	 * Returns a string with the account number, current account balance,
	 * and the overdraft amount associated with this account.
	 */
	@Override
	public String toString()
	{
		
		return "Account number: " + this.getAcctNo() + "\nCurrent balance: " + this.getBalance() + "\nOverdraft amount: " + overdraft;
		
	}
	
	/**
	 * Withdraws a specified amount from the account.
	 * @param amount: The amount to withdraw.
	 */
	@Override
	public void withdraw(double amount)
	{
		
		double bal = this.getBalance();
		double feeComputation = bal - fee - amount;
		boolean bLimit = (amount - bal >= overdraft) && feeComputation >= overdraft;
		
		if(amount <= -1.0)
		{
			System.out.println("CheckingAccount.withdraw() method cannot withdraw a negative amount.");
			
		}
		else if((amount > bal) && bLimit)
		{
						
			setBalance(feeComputation);
			System.out.println("CheckingAccount.withdraw() method encountered overdraft.\nOverdraft fee applied to account.");
			
		} 
		else if((amount > bal) && !bLimit)
		{
				
			System.out.println("CheckingAccount.withdraw() is not within overdraft limit. Transaction canceled.");
				
		}
		else
		{
			
			setBalance(bal - amount);
			System.out.println("CheckingAccount.withdraw() successfully withdrew " + amount + " dollars.");
			
		}
	}
	
	/**
	 * Transfers a specified amount to the provided account.
	 * @param account: The target transfer account.
	 * @param transferAmount: The amount to transfer to parameter 'account'.
	 */
	@Override
	public void transfer(Account account, double transferAmount)
	{
		
		double bal = this.getBalance();
		double feeComputation = bal - fee - transferAmount;
		boolean bLimit = (transferAmount - bal >= overdraft) && feeComputation >= overdraft;
		
		if(transferAmount <= -1.0)
		{
			
			System.out.println("CheckingAccount.transfer() method cannot transfer a negative amount.");
			
		}
		else if(transferAmount > bal && bLimit)
		{
			
			setBalance(feeComputation);
			account.setBalance(account.getBalance() + transferAmount);
			System.out.println("CheckingAccount.transfer() method encountered overdraft.\nOverdraft fee applied to account.");
			
		} 
		else if(transferAmount > bal && !bLimit)
		{
			
			System.out.println("CheckingAccount.transfer() is not within overdraft limit. Transaction canceled.");

		}
		else
		{
			
			setBalance(bal - transferAmount);
			account.setBalance(account.getBalance() + transferAmount);
			System.out.println("CheckingAccount.transfer() method complete. Transferred " + transferAmount + " dollars");
			
		}
	}
	
	/**
	 * Displays the account number, account balance, and overdraft amount of this account.
	 */
	@Override
	public void displayInfo()
	{
		
		super.displayInfo();
		System.out.println("Overdraft amount: " + overdraft);
		
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
	public static int checkingAccountSearch(CheckingAccount[] arr, int n, String accountNum) throws AccountNotFoundException
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