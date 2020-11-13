/**
 * @author: Robert Guy
 * The Calculator class provides several methods
 * for computing the precedence of a given character
 * (namely operator) and for converting and evaluating
 * an in-fix expression to its equivalent post-fix
 * form.
 */

package assg4_guyr18;

import java.util.Stack;
import java.util.HashMap;
import java.lang.IllegalStateException;

public class Calculator
{
	
	private String exp;
	private String postFixExp;
	private HashMap<Integer, String> errorMap;
	
	/** A one-parameter constructor that accepts
	 *  a in-fix expression string, initializes it,
	 *  and defaults the post-fix expression to an
	 *  empty string.
	 * @param exp: The in-fix expression.
	 */
	
	public Calculator(String exp)
	{
		
		this.exp = exp;
		this.errorMap = buildErrorMap();
		this.postFixExp = "";
		this.convertPostfix();
		
	}
	
    /**
     * Returns a fixed hash map with keys and values specific to error
     * messages within this application.
     * @return: A fixed hash map.
     */
     
	private HashMap<Integer, String> buildErrorMap()
	{
	
		String common = "Syntax error occurred at character ";
        HashMap<Integer, String> data = new HashMap<>();
		data.put(0, common + "_. Consecutive operands are not allowed.");
		data.put(1, common + "_. Alphabetic characters are not allowed.");
		data.put(2, common + "_. Consecutive operators are not allowed.");
		data.put(3, "Syntax error. Improperly nested parentheses.");
		data.put(4, "Syntax error. Mismatched parentheses.");
		data.put(5, "Syntax error. Empty parantheses.");
		data.put(6, common + "_. Expected valid binary operator (+, -, *, or /)");
		return data;
	
	}
		
	/** This method converts the initialized in-fix expression
	 *  string to its equivalent post-fix expression.
	 * @return: Returns true if the conversion is successful, and
	 * 			false otherwise.
	 */
	
	private boolean convertPostfix()
	{
		
		Stack<Character> s = new Stack<Character>();
		char errorTarget = ' ';
		int operandCount = 0;
		int operatorCount = 0;
		boolean isMultiDigit = false;
		int errorCode = -1;
		int openParenCount = 0;
		int closeParenCount = 0;
		int totalDigitCount = 0;
                
		for(int i = 0; i < exp.length(); i++)
		{
			
			char c = exp.charAt(i);
            int prevIndex = i == 0 ? 0 : i - 1;
            
			if(Character.isDigit(c))
			{
					
				postFixExp = postFixExp + c;
				totalDigitCount++;
				operandCount++;
				operatorCount = 0;
				
				if(operandCount > 1 && exp.charAt(prevIndex) == ' ')
				{
					
					errorCode = 0;
					operandCount = 0;
					errorTarget = c;
					isMultiDigit = false;
					break;
					
				}
				else if(Character.isDigit(exp.charAt(prevIndex)))		
				{
					
					operandCount++;
					isMultiDigit = true;
					
				}
				
			}
			else if(Character.isAlphabetic(c))
			{
				
				errorCode = 1;
				errorTarget = c;
				break;
				
			}
			else if(Character.isWhitespace(c))
			{
				
				if(operandCount > 1 && !isMultiDigit)
				{
					
					errorCode = 0;
					errorTarget = c;
					break;
					
				}
					
			}
			else if(c == '(')
			{
					
				openParenCount++;
				s.push(c);
				
			}
			else if(c == ')')
			{
					
				postFixExp = postFixExp + " ";
				operatorCount = 0;
				closeParenCount++;
				
                if((closeParenCount > openParenCount && (i < exp.length() - 1)))
                {
                                    
                   errorCode = 3;
                   break;
                                    
                }
                                
				while(!s.isEmpty() && s.peek() != '(')
				{
						
					postFixExp = postFixExp + s.pop();
						
				}
				
				if(!s.isEmpty())
				{
					
					s.pop();
					
				}
					
			}
			else 
			{
					
				postFixExp = postFixExp + " ";
				operandCount = 0;
				
				if(getPrecedence(c) == -1)
				{
					
					errorCode = 6;
					errorTarget = c;
					break;
					
				}
				
				operatorCount++;
				
				if(operatorCount > 1)
				{
					
					errorCode = 2;
					operatorCount = 0;
					errorTarget = c;
					break;
					
				}
				
				while(!s.isEmpty() && s.peek() != '(' && getPrecedence(c) <= getPrecedence(s.peek()))
				{
						
					postFixExp = postFixExp + s.pop() + " ";
						
				}
					
				s.push(c);

			}
		}
		
		while(!s.isEmpty())
		{
			
		    postFixExp = postFixExp + " " + s.pop();
			
		}
                
		if(errorCode == -1 && openParenCount != closeParenCount && totalDigitCount > 0)
		{
			
			errorCode = 4;
			
		} 
		else if(errorCode == -1 && totalDigitCount <= 1)
		{
			
			errorCode = 5;
			
		}
		
		if(errorCode > -1)
		{
			
			String m = errorMap.get(errorCode);
			m = m.replace('_', errorTarget);
			postFixExp = "";
			System.out.println("\n" + m);
			
		}	
		
		return errorCode == -1;
			
	}
	
	/** Returns an integer representing the 
	 *  precedence of a given character. As in mathematics,
	 *  multiplication and division are given a higher precedence
	 *  than other operators; such as addition and subtraction.
	 * @param c: The given character.
	 * @return: An integer representing the precedence of some
	 * 			character c.
	 */
	
	private int getPrecedence(char c)
	{
							
		if(c == '*' || c == '/')
		{
			
			return 2;
			
		}
		else if(c == '+' || c == '-')
		{
			
			return 1;
			
		}
		
		return -1;
		
	}
	
	/**
	 * Returns the converted in-fix expression (post-fix form).
	 * @return: If the in-fix expression has not been converted this method
	 * 			will throw an IllegalStateException. Otherwise, the post-fix
	 * 			expression string will be returned.
	 * 
	 * @throws IllegalStateException
	 */
	
	public String getPostfix() throws IllegalStateException
	{
		
		if(postFixExp.equals(""))
		{
			
			throw new IllegalStateException("[IllegalStateException at getPostfix()]: The in-fix expression has not been converted to a post-fix expression yet.\n");
			
		}
		
		return postFixExp;
		
	}
	
	/**
	 * This method evaluates the post-fix expression that
	 * was obtained through convertPostFix(). If this method
	 * has not been called then an IllegalStateException is thrown.
	 * @return: Returns a integer representing the value of the post-fix expression.
	 * @throws IllegalStateException
	 */
	
	public int evaluate() throws IllegalStateException
	{
		
		if(postFixExp.equals(""))
		{
			
			throw new IllegalStateException("[IllegalStateException at evaluate()]: The in-fix expression has not been converted yet.\n");
			
		}
		else
		{
			
			int leftOp = 0, rightOp = 0;
			String str = "";
			Stack<Integer> S = new Stack<Integer>();
			
			for(int i = 0; i < postFixExp.length(); i++)
			{
			
				char c = postFixExp.charAt(i);
				
				if(Character.isLetterOrDigit(c))
				{
					
					str = str + Character.toString(c);
						
				} 
				else if(Character.isWhitespace(c))
				{
					
					if(!str.equals(""))
					{
						
						S.push(Integer.parseInt(str));
						str = "";
					
					}
					else
					{
						
						continue;
						
					}
				}
				else
				{
					
					rightOp = S.pop();
					leftOp = S.pop();
					S.push(computeValues(c, leftOp, rightOp));
					
				}
			}
			
			return S.get(0);
			
		}
	}
	
	/** This method decides on the correct operation from the value of
	 * 	a given character c that represents a binary operator. It then computes
	 *  and returns the result of that operation on parameters 's0' and 's1'.
	 *  
	 *  It is assumed that character c is a valid binary operator.
	 *  
	 * @param c: The given operator character.
	 * @param s0: An integer operand.
	 * @param s1: Another integer operand.
	 * @return: Returns an integer that is the result of a given
	 * 			binary operation on s0 and s1.
	 */
		
	private int computeValues(char c, int s0, int s1)
	{
			
		if(c == '+')
		{
				
			return s0 + s1;
				
		}
		else if(c == '-')
		{
				
			return s0 - s1;
				
		}
		else if(c == '/')
		{
				
			return s0 / s1;
				
		}
		else
		{
				
			return s0 * s1;
				
		}
	}
	
	/**
	 * ToString() returns the in-fix expression.
	 * @return: The in-fix expression.
	 */
	
	@Override
	public String toString()
	{
		
		return exp;
		
	}
}