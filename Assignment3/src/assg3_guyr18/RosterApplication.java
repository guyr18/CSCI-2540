/**
 * @author: Robert Guy
 * RosterApplication is the core execution class
 * of which this application is executed from.
 */

package assg3_guyr18;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class RosterApplication 
{
	
	public static final String SOURCE_FILE = "assg3_roster.txt";
	private static Scanner keyboard;
	private static Roster activeRoster;
	private static boolean running = true;
	
	/** The main function of which this program is executed.
	 * @param args: The provided string array of command line arguments.
	 */
	
	public static void main(String[] args)
	{
		
		keyboard = new Scanner(System.in);
		activeRoster = new Roster();
		activeRoster.loadData(RosterApplication.SOURCE_FILE);
		
		while(running)
		{
			
			System.out.println("\n");
			displayMenuOptions();
			String input = keyboard.next();
			
			if(input.equals("1"))
			{
				
				activeRoster.displayRoster();
				
			}
			else if(input.equals("2"))
			{
				
				searchForStudentById();
				
			}
			else if(input.equals("3"))
			{
				
				addNewStudent();
				
			}
			else if(input.equals("4"))
			{
				
				removeSomeStudent();
				
			}
			else if(input.equals("5"))
			{
				
				searchForStudentsWithMajor();
				
			}
			else if(input.equals("6"))
			{
				
				activeRoster.Sort();
				activeRoster.Save();
				System.out.println("The current roster was sorted in nondecreasing order and saved to the source file.");
				
			}
			else if(input.equals("7"))
			{
				
				activeRoster.Save();
				System.out.println("The current roster was successfully saved.");
				
			} 
			else if(input.equals("8"))
			{
				
				System.out.println("Exiting application.");
				running = false;
				System.exit(1);
				
			}
			else
			{
				
				System.out.println("Unrecognizable menu code.");
				
			}
		}
		
		keyboard.close();
		
	}
	
	/**
	 * Displays the menu options for this application.
	 */
	
	private static void displayMenuOptions()
	{
		
		System.out.println("1. Display the roster");
		System.out.println("2. Search for a student by id");
		System.out.println("3. Add a new student");
		System.out.println("4. Remove a student");
	    System.out.println("5. Search for students by major");
	    System.out.println("6. Sort and save to file");
	    System.out.println("7. Save to file");
	    System.out.println("8. Exit");
	    
	}
	
	/** 
	 * Searches for a student within the current student
	 * roster that matches the inputted student id. Prints a 
	 * message to the user that indicates the success of
	 * the operation.
	 */
	
	private static void searchForStudentById()
	{
		
		System.out.println("Please enter a student id to lookup: ");
		String id = keyboard.next();
		Student s = activeRoster.searchForStudent(id);
		
		if(s != null)
		{
			
			System.out.println("Student ID: " + s.getID());
			System.out.println("Student Name: " + s.getName());
			System.out.println("Student Standing: " + s.getStanding());
			System.out.println("Student Major: " + s.getMajor());
			
		}
		else
		{
			
			System.out.println("This student could not be found.");
			
		}
	}
	
	/** 
	 * Adds a new student to the current roster permitting
	 * information can be validated.
	 */
	
	private static void addNewStudent()
	{
		
		System.out.println("Please provide a desired student ID: ");
		String id, name, standing, major;
		id = keyboard.next();
		boolean available = activeRoster.searchForStudent(id) == null;
		
		if(!available)
		{
			
			System.out.println("This student id is not available or the entered id is not numeric.");
			
		}
		else
		{
			
			System.out.println("Please provide your name: ");
			name = keyboard.next();
			System.out.println("Please provide your estimated standing: ");
			standing = keyboard.next();
			System.out.println("Please select a major: ");
			major = keyboard.next();
			activeRoster.addStudent(id, name, standing, major);
			System.out.println("Student successfully added.");
			
		}
	}
	
	/**
	 * Removes the inputted student id x provided that it 
	 * exists. A respective message is printed to the user
	 * either-way.
	 */
	
	private static void removeSomeStudent()
	{
		
		System.out.println("Please enter the student id that you wish to remove: ");
		String id = keyboard.next();
		boolean exists = activeRoster.searchForStudent(id) != null;
		
		if(exists)
		{
			
			activeRoster.removeStudent(id);
			System.out.println("Student successfully removed from roster.");
			
		}
		else
		{
			
			System.out.println("This student does not exist. Student cannot be removed.");
			
		}
	}
	
	/** 
	 * Searches for a student with the inputted major x. If no student has
	 * chosen this major x, a message is displayed. Otherwise, a filtered
	 * list of students with this major x is generated and displayed.
	 */
	
	private static void searchForStudentsWithMajor()
	{
		
		System.out.println("Please enter a major to filter by: ");
		String major = keyboard.next();
		ArrayList<Student> filteredList = activeRoster.getStudentsByMajor(major);
		
		if(filteredList.size() <= 0)
		{
			
			System.out.println("No students exist with this major.");
			
		}
		else
		{
			
			Iterator<Student> it = filteredList.iterator();
			
			while(it.hasNext())
			{
				
				Student s = it.next();
				System.out.println("Student ID: " + s.getID());
				System.out.println("Student Name: " + s.getName());
				
			}
		}
	}
}