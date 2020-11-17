package model;

import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String MatricNo;
	private String SchoolID;
	private String Degree;
	private List<Course> courses;

	public Student(String Name, String Password, String Email, Calendar dob, String MatricNo, String SchoolID,
			String Degree) {
		super(Name, Password, Email, dob);
		this.MatricNo = MatricNo;
		this.SchoolID = SchoolID;
		this.Degree = Degree;
	}

	public void setDegree(String Degree) {
		this.Degree = Degree;
	}

	public String getDegree() {
		return this.Degree;
	}

	public void setSchoolID(String SchoolID) {
		this.SchoolID = SchoolID;
	}

	public String getSchoolID() {
		return this.SchoolID;
	}

	public String getMatricNo() {
		return this.MatricNo;
	}

	public boolean addCourse(Course newCourse, Index index) {
		// Check and Update the max Limit
		if (!checkClash(newCourse)){
			courses.add(newCourse);
			return true;
		}
			
		else{
			System.out.println("There is a timetable clash. Please resolve it.");
			return false;
		}
			
	}

	public boolean removeCourse(String ID) {
		// Update the max Limit
		for (Course course : courses) {
			if (course.getID().equals(ID)) {
				courses.remove(course);
				System.out.println("Course Succesfully Removed");
				return true;
			}
		}
		System.out.println("You have not registered for this Course. Cannot Drop a Course which hasn't been added");
		return false;
	}

	public Boolean checkClash(Course newCourse, Index index) {
		// Need to work on this -- Shoudln't the parameter for this be an Index? -Devansh
		return true;
	}

	public void printCoursesRegistered() {
		for (Course course : courses) {
			System.out.println("Course ID: " + course.getID());
			System.out.println("Course Name: " + course.getName());
			System.out.println("-------------------");
		}
	}

	public boolean swapIndex(String courseID, String newIndex) {
		for (Course course : courses) {
			if (course.getID().equals(courseID)) {
				// swap index if vacancy
				List<Index> indexList = course.getIndexList();
				for (Index index : indexList){
					if(checkClash(course, index)){
						System.out.println("Cannot swap index, there is a clash");
						return false;
					}
					else{
						if(removeCourse(courseID)){
							if(addCourse(course, index)) 
								return true;
						}
						else{
							System.out.println("Error: Course has not been addded. You can only swap index for added courses");
							return false;
						}
					}
				}
				break;
			}
		}
		System.out.println("Error: Course has not been addded. You can only swap index for added courses");
		return false;
	}

	// Swap index with student should be in Student Manager or higher Level Class

}
