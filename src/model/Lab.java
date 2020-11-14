package model;

import java.time.LocalTime;

public class Lab extends Session{
	private String labTeachingAssistant;
	
	public Lab(String labTeachingAssistant, int day, LocalTime startTime, LocalTime endTime, String location) {
		super(day, startTime, endTime, location);
		
		this.labTeachingAssistant = labTeachingAssistant;
	}
	
	public void setLabTeachingAssistant(String labTeachingAssistant) {
		this.labTeachingAssistant = labTeachingAssistant;
	}
	
	public String getLabTeachingAssistant() {
		return this.labTeachingAssistant;
	}
	
}
