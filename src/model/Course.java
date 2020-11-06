package model;

import java.util.List;

public class Course {
	
	private String Name;
	private String ID;
	private String faculty;
	private int totalVacancies;
	private int totalEnrolled;
	private List<Index> indexList;
	private List<Session> lecture;
	
	public Course(String Name, String ID, String faculty) {
		this.Name = Name;
		this.ID = ID;
		this.faculty = faculty;
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
	
	public int getTotalVacancies() {
		return this.totalVacancies;
	}
	
	public void setTotalVacancies(int totalVacancies) {
		this.totalVacancies = totalVacancies;
	}
	
	public int getTotalEnrolled() {
		return this.totalVacancies;
	}
	
	public void setTotalEnrolled(int totalEnrolled) {
		this.totalEnrolled = totalEnrolled;
	}
	
	// TODO: Implement other functions 
}
