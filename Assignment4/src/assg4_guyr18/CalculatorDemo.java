/**
 * @author: Robert Guy
 * CalculatorDemo is the core class of this
 * application. It processes input and
 * instantiates the Calculator class at the
 * appropriate times. 
 */

package assg4_guyr18;

import java.io.*;

public class CalculatorDemo 
{       
	
	/** This function accepts a string array of command-line arguments
	 *  and is the initialization point of this application.
	 * @param args: A string array of command-line arguments.
	 */
	
	public static void main(String[] args)
	{
		
		boolean running = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String yesNo = "";
		System.out.println("This application supports syntax validation.");
		
		while(running)
		{
			
			dispatch(reader);
			System.out.println("Would you like to attempt another conversion (Y/N)? ");
			
			try
			{
			
				yesNo = reader.readLine();
				       
				if(yesNo.toUpperCase().equals("N"))
				{
				
					running = false;
					reader.close();
					System.out.println("Exiting app.");
					System.exit(0);

				}
				else if(yesNo.toUpperCase().equals("Y"))
				{
				
					continue;
				
				}
				else
				{
				
					running = false;
					reader.close();
					System.out.println("Unrecognizable menu code. Exiting app.");
					System.exit(0);
				
				}
				
			}
			catch(IOException e)
			{
				
				System.out.println(e.getMessage());
				
			}
		}
	}
	
	/**
	 * This method accepts a scanner object as input
	 * and processes the input of this application.
	 * Furthermore, it outputs the post-fix expression
	 * and displays its evaluation.
	 * @param keyboard: The user input model or scanner object.
	 */
	
	private static void dispatch(BufferedReader reader)
	{
		
		System.out.println("Enter an in-fix expression to convert to post-fix format: ");
                
        try
        {
                  
            Calculator c = new Calculator(reader.readLine());
            boolean success = !c.getPostfix().equals("");
            c.evaluate();
            
            if(success)
            {
            	
            	System.out.println("Post-fix expression: " + c.getPostfix());
    			System.out.println("Evaluation: " + c.evaluate());
    			
            }            
        }
        catch(IllegalStateException e)
        {
        	
        	System.out.println(e.getMessage());
        	
        }
        catch(IOException e)
        {
        	
            System.out.println(e.getMessage());
            
        }
	}
}