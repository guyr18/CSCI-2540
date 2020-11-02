/**
 * @author: Robert Guy
 * RosterInterface is an interface
 * that represents the functionality
 * and behavior of class Roster.
 */

package assg3_guyr18;

import java.util.ArrayList;

public interface RosterInterface
{
	
	/**
	 * Loads data from the source file whose path is
	 * indicated by parameter 'filename'.
	 * @param filename: The filename of the source file.
	 */
	
	public void loadData(String filename);
	
	/**
	 * Displays the complete information of all
	 * students found on this roster.
	 */
	
	public void displayRoster();
	
	/**
	 * Searches for a student on this roster
	 * with a matching id of parameter 'id'.
	 * @param id: The id that is being compared.
	 * @return: Returns the Student object if one
	 * 			is found. Otherwise, returns null.
	 */
	
	public Student searchForStudent(String id);
	
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
	
	public boolean addStudent(String id, String name, String standing, String major);
	
	/**
	 * Removes a student from the roster if the
	 * provided parameter 'id' can be matched to
	 * a student.
	 * @param id: The provided student id.
	 * @return: Returns a boolean value of true if
	 * 			the student is successfully removed.
	 * 			And false, otherwise.
	 */
	
	public boolean removeStudent(String id);
	
	/**
	 * Returns a list (ArrayList) of students
	 * that can be matched to the provided parameter 'major'.
	 * @param major: The provided student major.
	 * @return: Returns an ArrayList of students. This list
	 * 			may be equivalent to an empty set if no students
	 * 			are matched to the given student major.
	 */
	
	public ArrayList<Student> getStudentsByMajor(String major);
	
	/**
	 * Sorts all students by their id in nondecreasing order.
	 */
	
	public void Sort();
	
	/**
	 * Writes the student roster back to the source file if
	 * new data exists.
	 */
	
	public void Save();
	
}