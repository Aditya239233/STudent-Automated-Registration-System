package model;

import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String MatricNo;
	private String SchoolID;
	private String Degree;
	private List<Course> courses;
	private List<Index> indexes;

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
		if (!checkTimeTableClash(index)){
			indexes.add(index);
			System.out.println("Course Succesfully added to Timetable");
			return true;
		}
			
		else{
			System.out.println("There is a timetable clash. Please resolve it.");
			return false;
		}
	}

	public boolean removeCourse(String ID) {
		// Update the max Limit
		Course course;
		for (Index index : indexes) {
			course = index.getCourse();
			if (course.getID().equals(ID)) {
				indexes.remove(index);
				System.out.println("Course Succesfully Removed");
				return true;
			}
		}
		System.out.println("You have not registered for this Course. Cannot Drop a Course which hasn't been added");
		return false;
	}

	public Boolean checkTimeTableClash(Index index) {
		Boolean isClash = false;
		Course newCourse = index.getCourse();
		for (Index i: indexes) {
			if (isClash)
				break;
			Course c = i.getCourse();
			// Lecture Clash
			for (Session lecture: c.getLecture()) {
				for (Session newLecture: c.getLecture()) {
					isClash = checkClash(lecture, newLecture);
					if (isClash)
						return isClash;
				}
				for (Session newTutorial: index.getTutorial()) {
					isClash = checkClash(newTutorial, lecture);
					if (isClash)
						return isClash;
				}
				for (Session newLab: index.getLab()) {
					isClash = checkClash(newLab, lecture);
					if (isClash)
						return isClash;
				}
			}
			
			// Tutorial Clash
			for (Session tutorial: i.getTutorial()) {
				for (Session newLecture: c.getLecture()) {
					isClash = checkClash(newLecture, tutorial);
					if (isClash)
						return isClash;
				}
				for (Session newTutorial: index.getTutorial()) {
					isClash = checkClash(newTutorial, tutorial);
					if (isClash)
						return isClash;
				}
				for (Session newLab: index.getLab()) {
					isClash = checkClash(newLab, tutorial);
					if (isClash)
						return isClash;
				}
			}
			
			// Lab Clash
			for (Session lab: i.getLab()) {
				for (Session newLecture: c.getLecture()) {
					isClash = checkClash(newLecture, lab);
					if (isClash)
						return
								isClash;
				}
				for (Session newTutorial: index.getTutorial()) {
					isClash = checkClash(newTutorial, lab);
					if (isClash)
						return isClash;
				}
				for (Session newLab: index.getLab()) {
					isClash = checkClash(newLab, lab);
					if (isClash)
						return isClash;
				}
			}
		}
		return isClash;
	}
	
	public Boolean checkClash(Session s1, Session s2) {
		Boolean isClash = false;
		int val1;
		int val2;
		if (s1.getDay() == s2.getDay()) {
			val1 = s2.getStartTime().compareTo(s1.getStartTime());
			val2 = s2.getEndTime().compareTo(s1.getStartTime());
			if (val1<=0 && val2>0)
				isClash = true;
			val2 = s1.getStartTime().compareTo(s2.getEndTime());
			if (val1 <= 0 && val2 <= 0)
				isClash = true;
		}
		return isClash;
	}

	public void printCoursesRegistered() {
		if (indexes.size() == 0) {
			System.out.println("No Courses Registered");
			return;
		}
		for (Index i: indexes) {
			Course c = i.getCourse();
			System.out.println("Course Code: "+c.getID());
			System.out.println("Course Name: "+c.getName());
			System.out.println("AUs: "+c.getAu());
			System.out.println("Index: "+i.getID());
			System.out.println("-----------------------------------------------------------------");
		}
	}

	public boolean swapIndex(String courseID, String newIndex) {
		for (Course course : courses) {
			if (course.getID().equals(courseID)) {
				// swap index if vacancy
				List<Index> indexList = course.getIndexList();
				for (Index index : indexList){
					if(index.getID() == newIndex){
						if(checkTimeTableClash(index)){
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
				}
				break;
			}
		}
		System.out.println("Error: Course has not been addded. You can only swap index for added courses");
		return false;
	}

	// Swap index with student should be in Student Manager or higher Level Class

}
