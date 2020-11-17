package model;

import java.util.List;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;

public class Index {

	private String ID;
	private Course course;
	private int totalVacancies;
	private int numStudentsEnrolled;
	private LinkedList<String> waitList; // first is head, last is tail
	private List<Session> tutorials;
	private List<Session> labs;
	
	public Index(String ID, Course course, int totalVacancies) { // constructor for index without tutorial and lab sessions
		this.ID = ID;
		this.course = course;
		this.totalVacancies = totalVacancies;
		this.numStudentsEnrolled = 0;
		this.waitList = new LinkedList<String>();

		this.tutorials = null;
		this.labs = null;
	}
	
	public Index(String ID, Course course, int totalVacancies, List<Session> tutorials) { // constructor for index without lab sessions
		this.ID = ID;
		this.course = course;
		this.totalVacancies = totalVacancies;
		this.numStudentsEnrolled = 0;
		this.waitList = new LinkedList<String>();

		this.tutorials = tutorials;
		this.labs = null;
	}

	public Index(String ID, Course course, int totalVacancies, List<Session> tutorials, List<Session> labs) {
		this.ID = ID;
		this.course = course;
		this.totalVacancies = totalVacancies;
		this.numStudentsEnrolled = 0;
		this.waitList = new LinkedList<String>();

		this.tutorials = tutorials;
		this.labs = labs;
	}

	public void printIndexDetails() {
		System.out.println("Index " + ID + " :");
		
		if (tutorials == null) {
			System.out.println("	Tutorial Sessions: None");
		} else {
			System.out.println("	Tutorial Sessions: ");
			for (int i=0; i<tutorials.size(); i++) {
				System.out.print("Tutorial ID:" + i);
				tutorials.get(i).printSessionDetails();
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

	public String getID() {
		return this.ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setTotalVacancies(int totalVacancies) {
		this.totalVacancies = totalVacancies;
	}
	
	public int getTotalVacancies() {
		return this.totalVacancies;
	}

	public void setNumStudentEnrolled(int numStudentsEnrolled) {
		if (numStudentsEnrolled > getTotalVacancies()) {
			this.numStudentsEnrolled = getTotalVacancies();
		} else {
			this.numStudentsEnrolled = numStudentsEnrolled;
		}
	}

	public int getNumStudentsEnrolled() {
		return this.numStudentsEnrolled;
	}
	
	public LinkedList<String> getWaitList() {
		return this.waitList;
	}

	public void addToWaitList(String studentMatricNo) {
		if (this.numStudentsEnrolled < this.totalVacancies) {
			this.setNumStudentEnrolled(getNumStudentsEnrolled() + 1);
			this.waitList.addFirst(studentMatricNo);
		}
		else {
			System.out.println("Error, Index " + getID() + " is full!");
		}
	}

	public String removeFromWaitList(String studentMatricNo) {
		if (this.numStudentsEnrolled > 0) {
			this.setNumStudentEnrolled(getNumStudentsEnrolled() - 1);
		} else {
			System.out.println("Error, Index " + getID() + " is empty!");
		}
		return this.waitList.removeLast();
	}

	public String viewLastWaitList() {
		return this.waitList.peekLast();
	}
	
	public void addTutorial(int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		int ID = this.tutorials.size();
		Session tutorial = new Session(ID, day, startTime, endTime, location, teacher);
		if (this.tutorials == null) {
			this.tutorials = Arrays.asList(tutorial);
		} else {
			this.tutorials.add(tutorial);
		}
	}
	
	public void updateTutorial(int tutorialID, int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		Session tutorial = new Session(tutorialID, day, startTime, endTime, location, teacher);
		tutorials.set(tutorialID, tutorial);
	}
	
	public void deleteTutorial(int tutorialID) {
		if (tutorialID < this.tutorials.size() && tutorialID >= 0) {
			this.tutorials.remove(tutorialID);
		} else {
			System.out.println("Tutorial index does not exist");
		}
	}
	
	public void addLab(int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		int ID = this.labs.size();
		Session lab = new Session(ID, day, startTime, endTime, location, teacher);
		if (this.labs == null) {
			this.labs = Arrays.asList(lab);
		} else {
			this.labs.add(lab);
		}
	}
	
	public void updateLab(int labID, int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		Session lab = new Session(labID, day, startTime, endTime, location, teacher);
		tutorials.set(labID, lab);
	}
	
	public void deleteLab(int labID) {
		if (labID < this.labs.size() && labID >= 0) {
			this.labs.remove(labID);
		} else {
			System.out.println("Lab index does not exist");
		}
	}
}
