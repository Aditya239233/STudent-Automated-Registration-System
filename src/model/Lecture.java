package model;

import java.time.LocalTime;

public class Lecture extends Session {
	private String professor;

	public Lecture(int ID, int day, LocalTime startTime, LocalTime endTime, String location, String teacher,
			String professor) {
		super(ID, day, startTime, endTime, location, teacher);

		this.professor = professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getProfessor() {
		return this.professor;
	}
}