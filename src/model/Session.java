package model;

import java.time.LocalTime;

public class Session {
	
	private Day day;
	private LocalTime startTime;
	private LocalTime endTime;
	private String location;
	
	public Session(Day day, LocalTime startTime, LocalTime endTime, String location) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
	}
	
	public Session(Day day, LocalTime startTime, LocalTime endTime) {
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}
	
	public Day getDay() {
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
}
