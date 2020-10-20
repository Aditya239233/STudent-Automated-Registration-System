package model;

import java.util.*;

public class Student extends User {
	
	private String MatricNo;
	private String SchoolID;
	private String Degree;
	
	public Student(String Name, String Password, String Email, Date dob, String MatricNo, String SchoolID, String Degree) {
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
}
