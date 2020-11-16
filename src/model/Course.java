package model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Course {

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String ID;
	private String Name;
	private String faculty;
	private int au;
	private List<Index> indexList;
	private List<Session> lectures;
	
	public Course(String ID, String Name, String faculty, int au) {
		this.ID = ID;
		this.Name = Name;
		this.faculty = faculty;
		this.au = au;
		this.indexList = null;
		this.lectures = null;
	}
	
	public Course(String ID, String Name, String faculty, int au, List<Index> indexList, List<Session> lectures) {
		this.ID = ID;
		this.Name = Name;
		this.faculty = faculty;
		this.au = au;

		this.indexList = indexList;
		this.lectures = lectures;
	}

	public void printIndexList() {
		if (this.indexList == null) {
			System.out.println("There are currently no index found in this course.");
		} else {
			for (Index index : indexList) {
				index.printIndexDetails();
			}
		}
	}
	

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public void setAu(int au) {
		this.au = au;
	}

	public int getAu() {
		return this.au;
	}
	
	public void addIndex(int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {  // To-do figure out addIndex addLab addTutorial interaction logic
		Scanner sc = new Scanner();
		int ID = this.indexList.size();
		Session tutorial = new Session(ID, day, startTime, endTime, location, teacher);
		if (this.indexList == null) {
			this.indexList = Arrays.asList(tutorial);
		} else {
			this.indexList.add(tutorial);
		}
	}
	
	public void deleteIndex(int index) {
		if (index < this.tutorials.size() && index >= 0) {
			this.tutorials.remove(index);
		} else {
			System.out.println("Tutorial index does not exist");
		}
	}
	
	public void addLecture(int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		int ID = this.lectures.size();
		Session lecture = new Session(ID, day, startTime, endTime, location, teacher);
		if (this.lectures == null) {
			this.lectures = Arrays.asList(lecture);
		} else {
			this.lectures.add(lecture);
		}
	}
	
	public void deleteLecture(int index) {
		if (index < this.lectures.size() && index >= 0) {
			this.lectures.remove(index);
		} else {
			System.out.println("Lecture index does not exist");
		}
	}

	public void addLecture(Session lecture) {
		this.lectures.add(lecture);
	}

	public void deleteLecture() {
	}
	// TODO: Implement other functions
}
