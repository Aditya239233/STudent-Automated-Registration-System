package model;

import java.io.Serializable;
import java.util.*;

import controller.CourseManager;

public class Student extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String MatricNo;
	private String SchoolID;
	private String Degree;
	private List<Course> courses;
	private List<Index> indexes;
	private NotificationMode nm;

	public Student(){
		super();
		this.MatricNo = "undefined";
		this.SchoolID = "undefined";
		this.Degree = "undefined";
		nm = NotificationMode.EMAIL;

	}

	public Student(String Name, String Password, String Email, Calendar dob, String MatricNo, String SchoolID,
			String Degree) {
		super(Name, Password, Email, dob);
		this.MatricNo = MatricNo;
		this.SchoolID = SchoolID;
		this.Degree = Degree;
		nm = NotificationMode.EMAIL;
	}

	public void setNotificationMode(NotificationMode nm){
		this.nm = nm;
	}

	public NotificationMode getNotificationMode(){
		return this.nm;
	}

	public void setDegree(String Degree) {
		this.Degree = Degree;
	}

	public List<Index> getIndexes(){
		return this.indexes;
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

	public boolean addCourse(Index index) {
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

	public boolean checkIfCourseRegistered(String index){
		for(Index i : this.indexes){
			if(i.getID() == index){
				return true;
			}
		}
		return false;
	}

	public boolean swapIndex(String s1_index, Student s2, String s2_index) {
		String course = " ";
		List<Index> s1_indexes = this.getIndexes();
		List<Index> s2_indexes = s2.getIndexes();
		String i1 = " ", i2 = " ";
		boolean isValid1 = false;
		boolean isValid2 = false;

		for(Index i:s2_indexes){
			if(i.getID() == s2_index){
				if(this.checkTimeTableClash(i)){
					System.out.println("Cannot swap index due to Timetable clash. Please select another index.");
					isValid1 = false;
				}
				else{
					i1 = i.getID();
					course = i.getCourse().getID();
					isValid1 = true;
				}
			}
		}
		for(Index i:s1_indexes){
			if(i.getID() == s1_index){
				if(s2.checkTimeTableClash(i)){
					System.out.println("Cannot swap index due to Timetable clash. Please select another index.");
					isValid2 = false;
				}
				else{
					i2 = i.getID();
					course = i.getCourse().getID();
					isValid2 = true;
				}
			}
		}

		if(isValid1 && isValid2){
			this.removeCourse(course);
			s2.removeCourse(course);
			Course c = CourseManager.findCourse(course);
			List<Index> iList = c.getIndexList();
			for(Index i : iList){
				if(i.getID() == i1){
					this.addCourse(i);
				}
				else if(i.getID() == i2){
					s2.addCourse(i);
				}
			}			
			return true;
		}
		else
			return false;
		
		
	}

	// Swap index with student should be in Student Manager or higher Level Class

}
