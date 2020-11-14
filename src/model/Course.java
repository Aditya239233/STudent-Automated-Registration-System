package model;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String ID;
	private String faculty;
	private int au;
	private List<Index> indexList;
	private List<Session> lectures;

	public Course(String Name, String ID, String faculty, int au, List<Index> indexList, List<Session> lectures) {
		this.Name = Name;
		this.ID = ID;
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

	public void addLecture(Session lecture) {
		this.lectures.add(lecture);
	}

	public void deleteLecture() {
	}
	// TODO: Implement other functions
}
