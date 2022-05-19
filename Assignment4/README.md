# CSCI-2540

# Demo Video



https://user-images.githubusercontent.com/46636441/169264432-fcc3ce63-41c7-41e3-8300-b00c696758d5.mp4


# Assignment 4
Assignment 4 required students to implement an application resembling a calculator. 
This calculator was required to take an in-fix expression from the designated input
stream and convert it to a post-fix expression.

Following this conversion, the post-fix expression is then evaluated. The conversion
expression and evaluated expression are printed as output. Lastly, this application
gave students experience with stacks.

Conclusively, the instructor of this course gave students an opportunity to gain
bonus points for this assignment. This aspect of the assignment required the
application to validate the in-fix expression during the conversion algorithm.

Syntax checking has been implemented through a hashmap resulting in a lookup
time of O(1). I have included a list below that details each of these error
stages and when they will occur.

Error codes have a zero-based position and are listed as such. An '_'
is included in the value as it is replaced with the appropriate character
in my algorithm.

0 => "Syntax error occurred at _. Consecutive operands are not allowed."  
   Example: 5 5 +   
   Reason: Operands can not be consecutive. In an in-fix expression,   
          binary operators should occur between a given set of operands.   
          
1 => "Syntax error occurred at _. Alphabetic characters are not allowed."   
  Example: a + 4   
  Reason: This application does not support variables. We have no way   
          of determining the true value of any variable a.   
          
2 => "Syntax error occurred at _. Consecutive operators are not allowed."   
  Example: 5 + + 4, 4 / (5 + + 2)   
  Reason: Consecutive binary operations are not permitted in an in-fix expression.   
        
3 => "Syntax error. Improperly nested parentheses."   
  Example: )2 + 3(   
  Reason: This would occur if an unsolvable expression is entered.   
          This expression is unsolvable, because a closing paranthesis.   
          occurs before an opening paranthesis. Intuitively, we cannot   
          close something that was never opened.   
          
4 => "Syntax error. Mismatched parentheses."   
  Example: (5 + 3)), 1 + (5 * 2)))    
  Reason: An unbalanced number of parantheses has occurred   
          in these expression(s). The number of opening parantheses   
          must be equal to the number of closing ones.   
          
5 => "Syntax error. Empty parantheses."   
  Example: (), (/), (5)    
  Reason: No well-defined expression exists within the parantheses. Do not compute it.   
          While "(5)" may be valid, it does not accomplish anything. As a result, there   
          must be at least two digits within any set p (where set p is a set consisting   
          of balanced parantheses).   
          
6 => "Syntax error occurred at _. Expected valid binary operator (+, -, *, or /)."   
  Example: 5 $ 1, 5 # 2   
  Reason: These are not valid binary operators. Do not accept them.   
          As a side mark, there are other acceptable binary operations   
          however, this application does not accept them. Some examples  
          of such operators could be the modulus (%), bitwise operators   
          and various others.   
          
# Compiling This Application
To compile this application, you will need to clone the repository. Then you can navigate to the **src** folder like-wise:

```
cd Assignment4/src
```
Subsequently, you may compile the **CalculatorDemo.java** file.

```
javac assg4_guyr18/CalculatorDemo.java
```

Lastly, you will be able to now run the application:

```
java assg4_guyr18/CalculatorDemo
```

# Points of Emphasis
I. This application does not support floating point numbers as input.   
II. This application only supports positive integers; thus, negative   
    integers do not function as expected.   
III. Unary operators are not valid.   
