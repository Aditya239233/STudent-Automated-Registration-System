package model;

import java.util.List;
import java.util.LinkedList;

public class Index {

	private int ID;
	private String CourseIndex;
	private int maxLimit;
	private int numStudentsEnrolled;
	private LinkedList<String> waitList; // first is head, last is tail
	private List<Session> tutorials;
	private List<Session> labs;

	public Index(String CourseIndex, int maxLimit, List<Session> tutorials) { // constructor for index without lab
																				// sessions
		this.CourseIndex = CourseIndex;
		this.maxLimit = maxLimit;
		this.numStudentsEnrolled = 0;
		this.waitList = new LinkedList<String>();

		this.tutorials = tutorials;
		this.labs = null;
	}

	public Index(int ID, String CourseIndex, int maxLimit, List<Session> tutorials, List<Session> labs) { // constructor
																											// for index
																											// with lab
																											// sessions
		this.ID = ID;
		this.CourseIndex = CourseIndex;
		this.maxLimit = maxLimit;
		this.numStudentsEnrolled = 0;
		this.waitList = new LinkedList<String>();

		this.tutorials = tutorials;
		this.labs = labs;
	}

	public void printIndexDetails() {
		System.out.println("Index " + ID + " for Course " + CourseIndex);

		if (tutorials == null) {
			System.out.println("	Tutorial Sessions: None");
		} else {
			System.out.println("	Tutorial Sessions: ");
			for (Session tutorial : tutorials) {
				tutorial.printSessionDetails();
			}
		}

		if (labs == null) {
			System.out.println("	Lab Sessions: None");
		} else {
			System.out.println("	Lab Sessions: ");
			for (Session lab : labs) {
				lab.printSessionDetails();
			}
		}
	}

	public int getID() {
		return this.ID;
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

	public void setNumStudentEnrolled(int numStudentsEnrolled) {
		if (numStudentsEnrolled > getMaxLimit()) {
			this.numStudentsEnrolled = getMaxLimit();
		} else {
			this.numStudentsEnrolled = numStudentsEnrolled;
		}
	}

	public int getNumStudentsEnrolled() {
		return this.numStudentsEnrolled;
	}

	public void setTutorials(List<Session> tutorials) {
		this.tutorials = tutorials;
	}

	public List<Session> getTutorials() {
		return tutorials;
	}

	public void setLabs(List<Session> labs) {
		this.labs = labs;
	}

	public List<Session> getLabs() {
		return this.labs;
	}

	public LinkedList<String> getWaitList() {
		return this.waitList;
	}

	public void addToWaitList(String studentMatricNo) {
		this.waitList.addFirst(studentMatricNo);
	}

	public String removeFromWaitList(String studentMatricNo) {
		return this.waitList.removeLast();
	}

	public String viewLastWaitList() {
		return this.waitList.peekLast();
	}
	// TODO: Implement other functions
}
