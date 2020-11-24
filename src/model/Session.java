package model;

import java.time.LocalTime;
import java.time.DayOfWeek;
import java.io.Serializable;

public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ID;
	private DayOfWeek day;
	private LocalTime startTime;
	private LocalTime endTime;
	private String location;
	private String teacher;
	
	/**
	 * Parametrized Constructor for Session
	 * @param ID - refers to the ID of the Session
	 * @param day - refers to the day of week
	 * @param startTime - refers to the starting time of session
	 * @param endTime - refers to the ending time of session
	 * @param location - refers to the location of session
	 * @param teacher - refers to the professor teching the session
	 */
	public Session(int ID, int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		this.ID = ID;
		this.day = DayOfWeek.of(day);
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.teacher = teacher;
	}

	public void printSessionDetails() {
		System.out.println("		Every " + getDay() + ", " + getStartTime() + " to " + getEndTime() + " at "
				+ getLocation() + " by " + getTeacher());
	}
	
	/**
	 * Getter and Setter functions
	 */
	public int getID() { return this.ID; }

	public void setID(int ID) { this.ID = ID; }

	public void setDay(int day) { this.day = DayOfWeek.of(day); }

	public DayOfWeek getDay() { return this.day; }

	public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

	public LocalTime getStartTime() { return this.startTime; }

	public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

	public LocalTime getEndTime() { return this.endTime; }

	public void setLocation(String location) { this.location = location; }

	public String getLocation() { return this.location; }

	public void setTeacher(String teacher) { this.teacher = teacher; }

	public String getTeacher() { return this.teacher; }
}
