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
	private List<Index> indexList;
	private List<Session> lecture;
	
	public Course(String Name, String ID, String faculty) {
		this.Name = Name;
		this.ID = ID;
		this.faculty = faculty;
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getName() {
		return this.Name;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	
	public String getFaculty() {
		return this.faculty;
	}
	
	// TODO: Implement other functions 
}
