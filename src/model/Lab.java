package model;

import java.time.LocalTime;

public class Lab extends Session {
	private String labTeachingAssistant;

	public Lab(int ID, int day, LocalTime startTime, LocalTime endTime, String location, String teacher,
			String professor, String labTeachingAssistant) {
		super(ID, day, startTime, endTime, location, teacher);

		this.labTeachingAssistant = labTeachingAssistant;
	}

	public void setLabTeachingAssistant(String labTeachingAssistant) {
		this.labTeachingAssistant = labTeachingAssistant;
	}

	public String getLabTeachingAssistant() {
		return this.labTeachingAssistant;
	}

}
