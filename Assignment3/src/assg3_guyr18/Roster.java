/**
 * @author: Robert Guy
 * This class represents a student roster
 * with several roster management functions.
 * These functions handle general management
 * functions such as filtering, loading, 
 * and saving.
 */

package assg3_guyr18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Roster implements RosterInterface
{
	
	private ArrayList<Student> students;
	private boolean newDataExists = false;
	private String sourceFile;
	
	/** Default constructor
	 */
	
	public Roster()
	{
		
		students = new ArrayList<Student>();
		
	}
	
	/**
	 * Loads data from the source file whose path is
	 * indicated by parameter 'filename'.
	 * @param filename: The filename of the source file.
	 */
	
	public void loadData(String filename)
	{
		
		try
		{
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			StringTokenizer st;
			
			while((line = reader.readLine()) != null)
			{
				
				st = new StringTokenizer(line);
				String id = st.nextToken(",");
				String name = st.nextToken(",");
				String standing = st.nextToken(",");
				String major = st.nextToken(",");
				addStudent(id, name, standing, major);

			}
			
			reader.close();
			
		} catch(IOException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		sourceFile = filename;
		
	}
	
	/**
	 * Displays the complete information of all
	 * students found on this roster.
	 */
	
	public void displayRoster()
	{
		
		Iterator<Student> it = students.iterator();
		
		while(it.hasNext())
		{
			
			Student s = it.next();
			System.out.println(s.toString());
			
		}
	}
	
	/**
	 * Searches for a student on this roster
	 * with a matching id of parameter 'id'.
	 * @param id: The id that is being compared.
	 * @return: Returns the Student object if one
	 * 			is found. Otherwise, returns null.
	 */
	
	public Student searchForStudent(String id)
	{
		
		Iterator<Student> it = students.iterator();
		
		while(it.hasNext())
		{
			
			Student s = it.next();
			
			if(s.getID().equals(id))
			{
				
				return s;
				
			}
		}
		
		return null;
		
	}
	
	/**
	 * Adds a new student to this roster. The provided
	 * parameter(s) are set as properties of the student.
	 * @param id: The provided student id.
	 * @param name: The provided student name.
	 * @param standing: The academic standing of this student.
	 * @param major: The selected major for this student.
	 * @return: Returns a boolean value indicating the success
	 * 			of this operation. Returns true if the user
	 * 			does not exist, and otherwise false. 
	 */
	
	public boolean addStudent(String id, String name, String standing, String major)
	{
						
		Student s = new Student(id, name, standing, major);
		newDataExists = true;
		return students.add(s);
		
	}
	
	/**
	 * Removes a student from the roster if the
	 * provided parameter 'id' can be matched to
	 * a student.
	 * @param id: The provided student id.
	 * @return: Returns a boolean value of true if
	 * 			the student is successfully removed.
	 * 			And false, otherwise.
	 */
	
	public boolean removeStudent(String id)
	{
		
		Student s = searchForStudent(id);
		
		if(s != null)
		{
			
			newDataExists = true;
			students.remove(s);
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * Returns a list (ArrayList) of students
	 * that can be matched to the provided parameter 'major'.
	 * @param major: The provided student major.
	 * @return: Returns an ArrayList of students. This list
	 * 			may be equivalent to an empty set if no students
	 * 			are matched to the given student major.
	 */
	
	public ArrayList<Student> getStudentsByMajor(String major)
	{
		
		ArrayList<Student> results = new ArrayList<Student>();
		Iterator<Student> it = students.iterator();
		
		while(it.hasNext())
		{
			
			Student s = it.next();
			String sUpper = s.getMajor().toUpperCase();
			String tUpper = major.toUpperCase();
			
			if(sUpper.equals(tUpper))
			{
				
				results.add(s);
				
			}
		}
		
		return results;
		
	}
	
	/**
	 * Sorts all students by their id in nondecreasing order.
	 */
	
	public void Sort()
	{
		
		Collections.sort(students);
		
	}
	
	/**
	 * Writes the student roster back to the source file if
	 * new data exists.
	 */
	
	public void Save()
	{
		
		try
		{
			
			if(newDataExists)
			{
			
				PrintWriter writer = new PrintWriter(new FileOutputStream(sourceFile, false));
				Iterator<Student> it = students.iterator();
				
				while(it.hasNext())
				{
					
					Student s = it.next();
					writer.println(s.toString());
					
				}
				
				newDataExists = false;
				writer.close();
				
			}
			
		} catch(FileNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		}
	}
}