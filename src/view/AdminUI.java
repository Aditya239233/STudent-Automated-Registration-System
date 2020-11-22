package view;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import controller.CourseManager;
import controller.IndexManager;
import controller.StudentManager;
import controller.StudentCourseManager;
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
			System.out.println("6. Add a index");
			System.out.println("7. Update a index");
			System.out.println("8. Delete a index");
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
				addStudent();
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
				checkVacancyInIndexSlot();
				break;
			case 10:
				// Print student list by index number
				printStudentListByIndex();
				break;
			case 11:
				// Print student list by course
				printStudentListByCourse();
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
		for (int i = 0; i < numLectures; i++) {
			System.out.println("Adding lecture " + i + "...");
			Session lecture = addSession(i);
			lectures.add(lecture);
		}

		CourseManager.addCourse(courseID, courseName, faculty, au, null, lectures);
		System.out.println("Succesfully added Course!");
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
		for (int i = 0; i < numLectures; i++) {
			System.out.println("Adding lecture " + i + "...");
			Session lecture = addSession(i);
			lectures.add(lecture);
		}

		CourseManager.updateCourse(courseID, courseName, faculty, au, null, lectures);
		System.out.println("Succesfully updated Course!");
	}

	public void deleteCourse() {
		System.out.println("Deleting course... ");
		System.out.println("What is the course id? e.g. CZ2002");
		String courseID = sc.next();

		CourseManager.deleteCourse(courseID);
		System.out.println("Succesfully deleted Course!");
	}

	public void addIndex() {
		System.out.println("Adding a new index... See below for the list of Course IDs ");
		CourseManager.printCourseIDs();
		Course course = null;
		do {
			System.out.println("What is the ID of the course that the index belongs? e.g. CZ2002");
			String courseID = sc.next();
			if (CourseManager.checkIfCourseExists(courseID)) {
				course = CourseManager.findCourse(courseID);
			} else {
				System.out.println("The course ID you entered does not exist");
			}
		} while (course != null);
		System.out.println("What is the index's ID? e.g. BCG10");
		String indexID = sc.next();
		System.out.println("How many total vacancies does the index have? eg. 50");
		int totalVacancies = sc.nextInt();

		List<Session> tutorials = null, labs = null;
		if (course.getHasTutorial()) {
			System.out.println("How many tutorial sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			tutorials = new ArrayList<Session>();
			for (int i = 0; i < numLectures; i++) {
				System.out.println("Adding tutorial " + i + "...");
				Session tutorial = addSession(i);
				tutorials.add(tutorial);
			}
		}
		if (course.getHasLab()) {
			System.out.println("How many lab sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			labs = new ArrayList<Session>();
			for (int i = 0; i < numLectures; i++) {
				System.out.println("Adding lab " + i + "...");
				Session lab = addSession(i);
				labs.add(lab);
			}
		}

		Index newIndex = IndexManager.addIndex(indexID, course, totalVacancies, tutorials, labs);
		CourseManager.addIndexToCourse(course.getID(), newIndex);
		System.out.println("Succesfully added Index!");

		IndexManager.addIndex(indexID, course, totalVacancies, tutorials, labs);
	}

	public void updateIndex() {
		System.out.println("Updating an index... See below for the list of Index IDs ");
		IndexManager.printIndexIDs();
		System.out.println("What is the index's ID? e.g. BCG10");
		String indexID = sc.next();
		Index index = null;
		do {
			if (IndexManager.checkIfIndexExists(indexID)) {
				index = IndexManager.findIndex(indexID);
			} else {
				System.out.println("The index ID you entered does not exist");
			}
		} while (index != null);
		CourseManager.printCourseIDs();
		Course course = null;
		do {
			System.out.println("What is the ID of the course that the index belongs? e.g. CZ2002");
			String courseID = sc.next();
			if (CourseManager.checkIfCourseExists(courseID)) {
				course = CourseManager.findCourse(courseID);
			} else {
				System.out.println("The course ID you entered does not exist");
			}
		} while (course != null);

		System.out.println("How many total vacancies does the index have? eg. 50");
		int totalVacancies = sc.nextInt();

		List<Session> tutorials = null, labs = null;
		if (course.getHasTutorial()) {
			System.out.println("How many tutorial sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			tutorials = new ArrayList<Session>();
			for (int i = 0; i < numLectures; i++) {
				System.out.println("Adding tutorial " + i + "...");
				Session tutorial = addSession(i);
				tutorials.add(tutorial);
			}
		}
		if (course.getHasLab()) {
			System.out.println("How many lab sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			labs = new ArrayList<Session>();
			for (int i = 0; i < numLectures; i++) {
				System.out.println("Adding lab " + i + "...");
				Session lab = addSession(i);
				labs.add(lab);
			}
		}

		Index newIndex = IndexManager.updateIndex(indexID, course, totalVacancies, tutorials, labs);
		CourseManager.deleteIndexFromCourse(course.getID(), newIndex);
		CourseManager.addIndexToCourse(course.getID(), newIndex);
		System.out.println("Succesfully updated Index!");

		IndexManager.updateIndex(indexID, course, totalVacancies, tutorials, labs);
	}

	public void deleteIndex() {
		System.out.println("Deleting index... ");
		System.out.println("What is the index id? e.g. BCG10");
		String indexID = sc.next();

		IndexManager.deleteIndex(indexID);
		System.out.println("Succesfully deleted Index!");
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

	public void checkVacancyInIndexSlot() {
		System.out.println("Enter the Index: ");
		String indexID = sc.next();
		while(!IndexManager.checkIfIndexExists(indexID)) {
			System.out.println("Invalid Index, please enter a valid Index: ");
			indexID = sc.next();
		}
		for (int i = 0; i < IndexManager.IndexList.size(); i++) {
			if (indexID == IndexManager.IndexList.get(i).getID()) {
				System.out.println(indexID + "has " + Integer.toString(IndexManager.IndexList.get(i).getTotalVacancies()-IndexManager.IndexList.get(i).getNumStudentsEnrolled())+ " vacancies");
				break;
			}
		}
	}
	
	
	public void printStudentListByIndex() {
		System.out.println("Enter the Index: ");
		String IndexCode = sc.next();
		char loop = 'y';
		while (loop == 'y') {
			if (IndexManager.checkIfIndexExists(IndexCode)) {
				List<ArrayList<String>> studentsEnrolled = StudentCourseManager.getStudentsInIndex(IndexCode);
				if (studentsEnrolled.size() == 0)
					System.out.println("There are no students enrolled in the index " + IndexCode);
				else {
					for (ArrayList<String> student : studentsEnrolled) {
						System.out.println("Student Name: " + student.get(1) + " ,Matric Number: " + student.get(0));
					}
				}
			} else {
				System.out.println("Course does not exist");
				while (true) {
					System.out.println("Do you want to try again? (y/n)");
					loop = sc.next().charAt(0);
					loop = Character.toLowerCase(loop);
					if (loop == 'y' || loop == 'n')
						break;
				}
			}
		}
	}

	public void printStudentListByCourse() {
		System.out.println("Enter the Couse: ");
		String CourseCode = sc.next();
		char loop = 'y';
		while (loop == 'y') {
			if (CourseManager.checkIfCourseExists(CourseCode)) {
				List<ArrayList<String>> studentsEnrolled = StudentCourseManager.getStudentsInCourse(CourseCode);
				if (studentsEnrolled.size() == 0)
					System.out.println("There are no students enrolled in the Course " + CourseCode);
				else {
					for (ArrayList<String> student : studentsEnrolled) {
						System.out.println("Student Name: " + student.get(1) + " ,Matric Number: " + student.get(0));
					}
				}
			} else {
				System.out.println("Course does not exist");
				while (true) {
					System.out.println("Do you want to try again? (y/n)");
					loop = sc.next().charAt(0);
					loop = Character.toLowerCase(loop);
					if (loop == 'y' || loop == 'n')
						break;
				}
			}
		}
	}

	public void addStudent() {
		System.out.println("Enter the following details to add a new student to the system:");
		System.out.println("Full name of student: ");
		String name = sc.nextLine();
		System.out.println("Student account password: ");
		String password = sc.nextLine();
		System.out.println("Student account email: ");
		String email = sc.nextLine();
		while (!email.contains("@e.ntu.edu.sg")) {
			System.out.println("Invalid email address. The email address should be of the form emailId@e.ntu.edu.sg");
			System.out.println("Please enter the email ID again: ");
			email = sc.nextLine();
		}
		System.out.println("Student's date of birth (DD-MM-YYYY format):");
		String dob = sc.nextLine();
		String[] arrOfStr = dob.split("-", 3);
		while (dob.length() != 10 || arrOfStr.length != 3) {
			System.out
					.println("Invalid date of birth. Please enter again in DD-MM-YYYY format and include the hyphens.");
			System.out.println("Student's date of birth (DD-MM-YYYY format):");
			dob = sc.nextLine();
			arrOfStr = dob.split("-", 3);
		}
		int dd = Integer.parseInt(arrOfStr[0]);
		int mm = Integer.parseInt(arrOfStr[1]);
		int yy = Integer.parseInt(arrOfStr[2]);
		Calendar c = Calendar.getInstance();
		c.set(yy, mm, dd);
		System.out.println("Enter the student's school ID: ");
		String school = sc.nextLine();
		System.out.println("Enter the student's degree code: ");
		String degree = sc.nextLine();
		System.out.println("Student's Matric Number: ");
		String mat_num = sc.nextLine();
		while (!StudentManager.addStudent(name, password, email, c, mat_num, school, degree)) {
			System.out.println("Cannot add students with duplicate matric numbers.");
			System.out.println("Please enter another matric number: ");
			mat_num = sc.nextLine();
		}
		System.out.println("Student added successfully!");
		return;
	}
}
