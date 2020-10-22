package model;

import java.util.List;

public class Index {
	
	private String CourseIndex;
	private int maxLimit;
	private List<String> studentsEnrolled;
	private List<String> studentsWaitlist;
	private List<Session> tutorial;
	private List<Session> lab;
	
	public Index(String CourseIndex, int maxLimit) {
		this.CourseIndex = CourseIndex;
		this.maxLimit = maxLimit;
	}
	
	public void setCourseIndex(String CourseIndex) {
		this.CourseIndex = CourseIndex;
	}
	
	public String getCourseIndex() {
		return this.CourseIndex;
	}
	
	public void setMaxLimit(int maxLimit) {
		this.maxLimit = maxLimit;
	}
	
	public int getMaxLimit() {
		return this.maxLimit;
	}
	
	// TODO: Implement other functions 
}
