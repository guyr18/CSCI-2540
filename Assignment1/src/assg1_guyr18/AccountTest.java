/**
 * @author: Robert Guy
 * The AccountTest class performs a set
 * of tests related to the class Account
 * and its subclasses.
 */
package assg1_guyr18;

import assg2_guyr18.Account;
import assg2_guyr18.CheckingAccount;
import assg2_guyr18.SavingsAccount;

public class AccountTest
{
	/**
	 * Tests relative to class AccountTest are performed here
	 * @param args: A set of command line parameters.
	 */
	public static void main(String[] args)
	{
		
		// Create some Account objects.
		Account acc1 = new Account("1");
		Account acc2 = new Account("2", 40.00);
		Account acc3 = new Account("1");
		
		// Perform some operations
		acc3.setBalance(75.00);
		acc1.setBalance(30.00);
		acc1.deposit(5.00); 
		acc1.deposit(-5.00); 
		acc1.withdraw(40.00); 
		acc1.withdraw(-50.00); 
		acc1.withdraw(15.00); 
		acc1.displayInfo();
		acc1.transfer(acc3, 14.00);
		acc1.transfer(acc3, -50.00);
		acc1.transfer(acc3, 1500.00);
		acc1.displayInfo();
		acc3.displayInfo();
		System.out.println("Does acc1 equal acc3? " + getBooleanEqualsString(acc1, acc3));
		System.out.println("Does acc1 equal acc2? " + getBooleanEqualsString(acc1, acc2));
		System.out.println("Does acc2 equal acc3? " + getBooleanEqualsString(acc2, acc3));
		System.out.println(acc1.toString());
		System.out.println(acc2.toString());
		System.out.println(acc3.toString());
		
		// Create some CheckingAccount objects
		CheckingAccount cAcc1 = new CheckingAccount("3");
		CheckingAccount cAcc2 = new CheckingAccount("4", 50.00, -200.00);
		CheckingAccount cAcc3 = new CheckingAccount("4");
		cAcc1.setFee(20.00);
		cAcc1.setBalance(30.00);
		cAcc1.setOverdraft(-100.00);
		cAcc3.setBalance(45.00);
		cAcc3.setOverdraft(-500.00);
		
		// Perform some operations
		cAcc1.deposit(20.00); 
		cAcc1.deposit(-20.00);
		cAcc1.displayInfo();
		cAcc1.withdraw(-100.00);
		cAcc1.withdraw(40.00);
		cAcc1.withdraw(500.00);
		cAcc1.withdraw(75.00);
		cAcc1.displayInfo();
		cAcc1.deposit(500.00);
		cAcc1.transfer(cAcc2, -50.00);
		cAcc1.transfer(cAcc2, 45.00);
		cAcc1.transfer(cAcc2, 2000.00);
		cAcc1.displayInfo();
		cAcc2.displayInfo();
		System.out.println("Does cAcc1 equal cAcc2? " + getBooleanEqualsString(cAcc1, cAcc2));
		System.out.println("Does cAcc1 equal cAcc3? " + getBooleanEqualsString(cAcc1, cAcc3));
		System.out.println("Does cAcc2 equal cAcc3? " + getBooleanEqualsString(cAcc2, cAcc3));
		System.out.println(cAcc1.toString());
		System.out.println(cAcc2.toString());
		System.out.println(cAcc3.toString());
		
		// Create some SavingsAccount objects
		SavingsAccount sAcc1 = new SavingsAccount("22");
		SavingsAccount sAcc2 = new SavingsAccount("22", 15.00, 0.04);
		SavingsAccount sAcc3 = new SavingsAccount("26", 55.00, 0.09);
		sAcc1.setBalance(125.00);
		sAcc1.setInterestRate(0.05);
		
		// Perform some operations
		
		System.out.println("The interest rate for sAcc1 is " + sAcc1.getInterestRate());
		sAcc1.deposit(-500.00);
		sAcc1.deposit(7.00);
		sAcc1.AddInterest();
		sAcc1.displayInfo();
		sAcc1.withdraw(1500.00);
		sAcc1.withdraw(-500.00);
		sAcc1.withdraw(45.00);
		sAcc1.AddInterest();
		sAcc1.displayInfo();
		sAcc1.transfer(sAcc2,  5000.00);
		sAcc1.transfer(sAcc2, -17.00);
		sAcc1.transfer(sAcc2, 5.00);
		sAcc1.displayInfo();
		sAcc2.displayInfo();
		System.out.println("Does sAcc1 equal sAcc2? " + getBooleanEqualsString(sAcc1, sAcc2));
		System.out.println("Does sAcc1 equal sAcc3? " + getBooleanEqualsString(sAcc1, sAcc3));
		System.out.println("Does sAcc2 equal sAcc3? " + getBooleanEqualsString(sAcc2, sAcc3));
		System.out.println(sAcc1.toString());
		System.out.println(sAcc2.toString());
		System.out.println(sAcc3.toString());
		
		// Reset balance of a list of previously created instances; reuse them.
		System.out.println("\nReset balance of a selected list of previous instance(s)");
		Account[] list = {acc3, cAcc3, sAcc3};
		accountListReset(list, 100.00);
		
		/* Use previous instances to verify that they
		 * all are compatible with the .transfer() method.
		 */
		
		 System.out.println("\nAccount -> CheckingAccount");
		 verifyTransferCompatibility(acc3, cAcc3);
		 
		 System.out.println("\nCheckingAccount -> Account");
		 verifyTransferCompatibility(cAcc3, acc3);
		 
		 System.out.println("\nCheckingAccount -> SavingsAccount");
		 verifyTransferCompatibility(cAcc3, sAcc3);
		 
		 System.out.println("\nSavingsAccount -> CheckingAccount");
		 verifyTransferCompatibility(sAcc3, cAcc3);
		 
		 System.out.println("\nAccount -> SavingsAccount");
		 verifyTransferCompatibility(acc3, sAcc3);
		 
		 System.out.println("\nSavingsAccount -> Account");
		 verifyTransferCompatibility(sAcc3, acc3);
		 
	}
	
	/**
	 * This method is used as a debug function to verify the 
	 * success of the .transfer() method between two given accounts.
	 * @param a: A given account
	 * @param b: A second account that money is transferred to.
	 */
	private static void verifyTransferCompatibility(Account a, Account b)
	{
		
		double randomNeg = Math.round(Math.random() * -100.00);
		double randomPos = Math.round(Math.random() * 20.00);
		SavingsAccount savings = null;
		a.transfer(b,  randomNeg); // Consider a negative case
		a.transfer(b, 10000.00); // Consider a value that couldn't possibly be reached
		a.transfer(b, randomPos); // Consider a reasonable value and transfer it.
		
		if(a instanceof SavingsAccount)
		{
			
			savings = (SavingsAccount)a;
			savings.AddInterest();
			
		}
		else if(b instanceof SavingsAccount)
		{
			
			savings = (SavingsAccount)b;
			savings.AddInterest();
			
		}
				
		a.displayInfo();
		System.out.println("\n");
		b.displayInfo();
		
	}
	
	/**
	 * Returns a string that represents the 
	 * equality of Account(s) a and b.
	 * @param a: A provided account.
	 * @param b: A second account that is being compared.
	 * @return: A String representing a boolean value.
	 */
	private static String getBooleanEqualsString(Account a, Account b)
	{
		
		boolean bEquals = a.equals(b);
		return Boolean.toString(bEquals);
		
	}
	
	/**
	 * Sets the balance of a list of accounts to some
	 * selected value 'x'.
	 * @param list: The provided list of accounts.
	 * @param x: The reset value.
	 */
	private static void accountListReset(Account[] list, double x)
	{
		
		for(Account a : list)
		{
			
			a.setBalance(x);
			
		}
	}
}