/**
 * @author: Robert Guy
 * This is a class representing
 * a university student; namely, Student.
 */

package assg3_guyr18;

public class Student implements Comparable<Student>
{
	
	private String id;
	private String name;
	private String standing;
	private String major;
	
	/**
	 * This is a 4-parameter constructor that is used to
	 * instantiate a Student.
	 * @param id: The provided student id.
	 * @param name: The student name.
	 * @param standing: The academic standing of this student.
	 * @param major: The students selected major.
	 */
	
	public Student(String id, String name, String standing, String major)
	{
		
		this.id = id;
		this.name = name;
		this.standing = standing;
		this.major = major;
		
	}
	
	/**
	 * Returns the id of this student.
	 * @return: A string representing the id of this student.
	 */
	
	public String getID()
	{
		
		return id;
		
	}
	
	/**
	 * Returns the name of this student.
	 * @return: A string representing the name of this student.
	 */
	
	public String getName()
	{
		
		return name;
		
	}
	
	/**
	 * Returns the academic standing of this student.
	 * @return: A string representing the academic standing of this student.
	 */
	
	public String getStanding()
	{
		
		return standing;
		
	}
	
	/**
	 * Returns the selected major of this student.
	 * @return: A string representing the selected major of this student.
	 */
	
	public String getMajor()
	{
		
		return major;
		
	}
	
	/**
	 * Updates the major for this student.
	 * @param major: The new major.
	 */
	
	public void setMajor(String major)
	{
		
		this.major = major;
		
	}
	
	/**
	 * Returns a string representing the properties
	 * of this student. Prints the id, name, academic standing,
	 * and major of this student.
	 */
	
	@Override 
	public String toString()
	{
		
		return  this.id + "," + this.name + "," + this.standing + "," + this.major;
		
	}
	
	/**
	 * Checks whether or not this student
	 * is equivalent to a given object 'obj'.
	 * @return: If the two students (or objects)
	 * 			have the same id this function returns true.
	 * 			Otherwise, this method returns false.
	 */
	
	@Override
	public boolean equals(Object obj)
	{
				
		if(obj != null && (obj instanceof Student))
		{
			
			Student s = (Student)obj;
			return s.getID().equals(this.id);
			
		}
		
		return false;
		
	}
	
	/**
	 * Compares the id of the given student s to
	 * the id of this student.
	 * @return: The before-mentioned id(s) are the same
	 * then 0 is returned. If one is less in length than the other,
	 * then -1 is returned. And otherwise (one is greater in length), 
	 * 1 is returned.
	 */
	
	public int compareTo(Student s)
	{
		
		if(s.getID().equals(this.id))
		{
			
			return 0;
			
		}
		else if(Integer.parseInt(s.getID()) < Integer.parseInt(this.id))
		{
			
			return 1;
			
		}
		else
		{
			
			return -1;
			
		}
	}
}