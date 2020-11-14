package model;

import java.time.LocalTime;

public class Lecture extends Session {
	private String professor;
	
	public Lecture(String professor, int day, LocalTime startTime, LocalTime endTime, String location) {
		super(day, startTime, endTime, location);
		
		this.professor = professor;
	}
	
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
	public String getProfessor() {
		return this.professor;
	}
}