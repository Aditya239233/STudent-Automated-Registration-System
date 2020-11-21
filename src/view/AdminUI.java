package view;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.CourseManager;
import controller.IndexManager;
import controller.StudentManager;
import model.Course;
import model.Index;
import model.Session;

public class AdminUI {

	private int choice;
	private IndexManager im = new IndexManager();
	Scanner sc = new Scanner(System.in);

	public void display() {
		do {
			System.out.println("Please Select One of the Options Below");
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a Student");
			System.out.println("3. Add a course");
			System.out.println("4. Update a course");
			System.out.println("5. Delete a course");
			System.out.println("6. Add a course");
			System.out.println("7. Update a course");
			System.out.println("8. Delete a course");
			System.out.println("9. Check available slot for an index number");
			System.out.println("10. Print student list by index number");
			System.out.println("11. Print student list by course");
			System.out.println("12. Log out");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please enter valid option:");
			}
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// Edit Student Access Period
				break;
			case 2:
				// Add a new Student
				break;
			case 3:
				// Add Course
				addCourse();
				break;
			case 4:
				// Update Course
				updateCourse();
				break;
			case 5:
				// Delete Course
				deleteCourse();
				break;
			case 6:
				// Add Index
				addIndex();
				break;
			case 7:
				// Update Index
				updateIndex();
				break;
			case 8:
				// Delete Index
				deleteIndex();
				break;
			case 9:
				// Check available slot for an index number
				checkIndexSlot();
				break;
			case 10:
				// Print student list by index number
				printStudentListIndex();
				break;
			case 11:
				// Print student list by course
				printStudentListCourse();
				break;
			case 12:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 7);
	}
	
	public void addCourse() {
		System.out.println("Adding a new course... ");
		System.out.println("What is the course id? e.g. CZ2002");
		String courseID = sc.next();
		System.out.println("What is the course name? e.g. Object Oriented Programming and Design");
		String courseName = sc.next();
		System.out.println("What is the course faculty? e.g. Professor Tan");
		String faculty = sc.next();
		System.out.println("How many Academic Units (AU) does the course have? eg. 3");
		int au = sc.nextInt();
		System.out.println("Does the course have tutorials? e.g. True/False");
		boolean hasTutorial = sc.nextBoolean();
		System.out.println("Does the course have lab sessions? e.g. True/False");
		boolean hasLab = sc.nextBoolean();
		
		System.out.println("How many lecture sessions does the course have per week? eg. 1");
		int numLectures = sc.nextInt();
		List<Session> lectures = new ArrayList<Session>();
		for(int i=0;i<numLectures;i++) {
			System.out.println("Adding lecture " + i + "...");
			Session lecture = addSession(i);
			lectures.add(lecture);
		}
		
		CourseManager.addCourse(courseID, courseName, faculty, au, null, lectures);
	}
	
	public void updateCourse() {
		System.out.println("Updating a new course... ");
		System.out.println("What is the course id? e.g. CZ2002");
		String courseID = sc.next();
		System.out.println("What is the course name? e.g. Object Oriented Programming and Design");
		String courseName = sc.next();
		System.out.println("What is the course faculty? e.g. Professor Tan");
		String faculty = sc.next();
		System.out.println("How many Academic Units (AU) does the course have? eg. 3");
		int au = sc.nextInt();
		System.out.println("Does the course have tutorials? e.g. True/False");
		boolean hasTutorial = sc.nextBoolean();
		System.out.println("Does the course have lab sessions? e.g. True/False");
		boolean hasLab = sc.nextBoolean();
		
		System.out.println("How many lecture sessions does the course have per week? eg. 1");
		int numLectures = sc.nextInt();
		List<Session> lectures = new ArrayList<Session>();
		for(int i=0;i<numLectures;i++) {
			System.out.println("Adding lecture " + i + "...");
			Session lecture = addSession(i);
			lectures.add(lecture);
		}
		
		CourseManager.updateCourse(courseID, courseName, faculty, au, null, lectures);
	}
	
	public void deleteCourse() {
		System.out.println("Deleting course... ");
		System.out.println("What is the course id? e.g. CZ2002");
		String courseID = sc.next();
		
		CourseManager.deleteCourse(courseID);
	}
	
	public void addIndex() {
		System.out.println("Adding a new index... See below for the list of Course IDs "); 
		CourseManager.printCourseIDs();
		Course course;
		do {
			System.out.println("What is the ID of the course that the index belongs? e.g. CZ2002");
			String courseID = sc.next();
			if (CourseManager.checkIfCourseExists(courseID)) {
				course = CourseManager.findCourse(courseID);
			} else {
				System.out.println("The course ID you entered does not exist");
			}
		} while(course != null);
		System.out.println("What is the index's ID? e.g. BCG10");
		String indexID = sc.next();
		System.out.println("How many total vacancies does the index have? eg. 50");
		int totalVacancies = sc.nextInt();
		
		List<Session> tutorials=null, labs=null;
		if (course.getHasTutorial()) {
			System.out.println("How many tutorial sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			tutorials = new ArrayList<Session>();
			for(int i=0;i<numLectures;i++) {
				System.out.println("Adding tutorial " + i + "...");
				Session tutorial = addSession(i);
				tutorials.add(tutorial);
			}
		}
		if (course.getHasLab()) {
			System.out.println("How many lab sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			labs = new ArrayList<Session>();
			for(int i=0;i<numLectures;i++) {
				System.out.println("Adding lab " + i + "...");
				Session lab = addSession(i);
				labs.add(lab);
			}
		}
		
		IndexManager.addIndex(indexID, course, totalVacancies, tutorials, labs);
	}
	
	public void updateIndex() {
		System.out.println("Updating an index... See below for the list of Index IDs "); 
		IndexManager.printIndexIDs();
		System.out.println("What is the index's ID? e.g. BCG10");
		String indexID = sc.next();
		Index index=null;
		do {
			if (IndexManager.checkIfIndexExists(indexID)) {
				index = IndexManager.findIndex(indexID);
			} else {
				System.out.println("The course ID you entered does not exist");
			}
		} while(index != null);
		CourseManager.printCourseIDs();
		Course course;
		do {
			System.out.println("What is the ID of the course that the index belongs? e.g. CZ2002");
			String courseID = sc.next();
			if (CourseManager.checkIfCourseExists(courseID)) {
				course = CourseManager.findCourse(courseID);
			} else {
				System.out.println("The course ID you entered does not exist");
			}
		} while(course != null);
		
		System.out.println("How many total vacancies does the index have? eg. 50");
		int totalVacancies = sc.nextInt();
		
		List<Session> tutorials=null, labs=null;
		if (course.getHasTutorial()) {
			System.out.println("How many tutorial sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			tutorials = new ArrayList<Session>();
			for(int i=0;i<numLectures;i++) {
				System.out.println("Adding tutorial " + i + "...");
				Session tutorial = addSession(i);
				tutorials.add(tutorial);
			}
		}
		if (course.getHasLab()) {
			System.out.println("How many lab sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			labs = new ArrayList<Session>();
			for(int i=0;i<numLectures;i++) {
				System.out.println("Adding lab " + i + "...");
				Session lab = addSession(i);
				labs.add(lab);
			}
		}
		
		IndexManager.updateIndex(indexID, course, totalVacancies, tutorials, labs);
	}
	
	public void deleteIndex() {
		System.out.println("Deleting index... ");
		System.out.println("What is the index id? e.g. BCG10");
		String indexID = sc.next();
		
		IndexManager.deleteIndex(indexID);
	}
	
	private Session addSession(int ID) {
		System.out.println("What day of the week is the session held? e.g. 4 = Thursday");
		int DayOfWeek = sc.nextInt();
		System.out.println("Which hour is the session held? e.g. 13 = 1pm");
		int hour = sc.nextInt();
		System.out.println("What minute is the session held? e.g. 30 = 1:30pm");
		int minute = sc.nextInt();
		System.out.println("Duration of session in minutes? e.g 60");
		int duration = sc.nextInt();
		System.out.println("Venue of session? e.g Lecture Hall 5");
		String location = sc.next();
		System.out.println("Who is teaching the course? e.g Professor Zhang");
		String teacher = sc.next();
		
		LocalTime startTime = LocalTime.of(hour, minute, 0, 0);
		LocalTime endTime = startTime.plusMinutes(duration);
		Session session = new Session(ID, DayOfWeek, startTime, endTime, location, teacher);
		return session;
	}
	
	public void checkIndexSlot() {
		System.out.println("Enter the Index: ");
		String indexID = sc.next();
		while(!IndexManager.checkIfIndexExists(indexID)) {
			System.out.println("Invalid Index, please enter a valid Index: ");
			indexID = sc.next();
		}
		for (int i = 0; i < IndexManager.IndexList.size(); i++) {
			if (indexID == IndexManager.IndexList.get(i)) {
				System.out.println(indexID + "has" + IndexManager.IndexList.get(i).getTotalVacancies() + "vacancies");
			}
		}
		
	}in
	
	public void printStudentListIndex() {
		System.out.println("Enter the Index: ");
		String indexID = sc.next();
		while(!IndexManager.checkIfIndexExists(indexID)) {
			System.out.println("Invalid Index, please enter a valid Index: ");
			indexID = sc.next();
		}
		for (int i = 0; i < StudentManager.StudentList.size(); i++) {
			if (indexID == StudentManager.StudentList.get(i).getIndexes()) {
				System.out.println(StudentManager.StudentList.get(i));
			}
		}
	}
	
	public void printStudentListCourse() {
		System.out.println("Enter the Couse: ");
		String course = sc.next();
		while(!CourseManager.checkIfCourseExists(course)){
			System.out.println("Invalid course code, please enter a valid course code: ");
			course = sc.next();
		}
		for (int i = 0; i < StudentManager.StudentList.size(); i++) {
			if (course == StudentManager.StudentList.get(i).getID()) {
				System.out.println(StudentManager.StudentList.get(i));
			}
		}
	}
}
