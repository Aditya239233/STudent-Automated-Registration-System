package model;

import java.time.LocalTime;
import java.time.DayOfWeek;

public class Session {
	
	private int ID;
	private DayOfWeek day;
	private LocalTime startTime;
	private LocalTime endTime;
	private String location;
	private String teacher;
	
	public Session(int ID, int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		this.ID = ID;
		this.day = DayOfWeek.of(day);
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.teacher = teacher;
	}
	
	public void printSessionDetails() {
		System.out.println("		Every " + getDay() + ", " + getStartTime() + " to " + getEndTime()
		+ " at " + getLocation() + " by " + getTeacher());
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID() {
		this.ID = ID;
	}
	
	public void setDay(int day) {
		this.day = DayOfWeek.of(day);
	}
	
	public DayOfWeek getDay() {
		return this.day;
	}
	
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalTime getStartTime() {
		return this.startTime;
	}
	
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	public LocalTime getEndTime() {
		return this.endTime;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	
	public String getTeacher() {
		return this.teacher;
	}
}
