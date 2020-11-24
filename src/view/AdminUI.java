package view;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import controller.CourseManager;
import controller.FileManager;
import controller.IndexManager;
import controller.StudentManager;
import controller.StudentCourseManager;
import model.Course;
import model.Index;
import model.Session;
import model.Student;

public class AdminUI implements UserUI {

	private int choice;
	Scanner sc = new Scanner(System.in);
	
	/**
     * This function is used to perform all Admin actions
     */
	public void display() {
		do {
			System.out.println("\n#########################################################################");
			System.out.println("\nPlease Select One of the Options Below");
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
			System.out.println("12. Log out\n");
			System.out.println("#########################################################################\n");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please enter valid option:");
			}
			choice = sc.nextInt();
			System.out.println("\n");
			switch (choice) {
			case 1:
				this.editStudentAccessPeriod();
				break;
			case 2:
				this.addStudent();
				break;
			case 3:
				this.addCourse();
				break;
			case 4:
				this.updateCourse();
				break;
			case 5:
				this.deleteCourse();
				break;
			case 6:
				this.addIndex();
				break;
			case 7:
				this.updateIndex();
				break;
			case 8:
				this.deleteIndex();
				break;
			case 9:
				checkVacancyInIndexSlot();
				break;
			case 10:
				printStudentListByIndex();
				break;
			case 11:
				printStudentListByCourse();
				break;
			case 12:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 12);
	}
	
	/**
     * This function is used by the admin to edit the student's Access Period
     */
	public void editStudentAccessPeriod() {
		List<Object> objects = FileManager.readObjectFromFile("accessPeriod.dat");
		List<Calendar> accessPeriod = new ArrayList<Calendar>();
		for (Object o : objects)
			accessPeriod.add((Calendar) o);
		System.out.println("Start time: " + accessPeriod.get(0).getTime());
		System.out.println("End time: " + accessPeriod.get(1).getTime());
		System.out.println("Do you want to edit the access period? (y/n)");
		char result = sc.next().toLowerCase().charAt(0);
		if (result == 'y') {
			accessPeriod.clear();
			System.out.println("Enter Start Date: (DD-MM-YYYY format):");
			String date = sc.next();
			String[] arrOfStr = date.split("-", 3);
			while (date.length() != 10 || arrOfStr.length != 3) {
				System.out.println("Invalid date. Please enter again in DD-MM-YYYY format and include the hyphens.");
				date = sc.next();
				arrOfStr = date.split("-", 3);
			}
			int dd = Integer.parseInt(arrOfStr[0]);
			int mm = Integer.parseInt(arrOfStr[1]);
			int yy = Integer.parseInt(arrOfStr[2]);
			Calendar c = Calendar.getInstance();
			c.set(yy, mm, dd);
			accessPeriod.add(c);

			System.out.println("Enter End Date: (DD-MM-YYYY format):");
			date = sc.next();
			arrOfStr = date.split("-", 3);
			while (date.length() != 10 || arrOfStr.length != 3) {
				System.out.println("Invalid date. Please enter again in DD-MM-YYYY format and include the hyphens.");
				date = sc.next();
				arrOfStr = date.split("-", 3);
			}
			dd = Integer.parseInt(arrOfStr[0]);
			mm = Integer.parseInt(arrOfStr[1]);
			yy = Integer.parseInt(arrOfStr[2]);
			Calendar c1 = Calendar.getInstance();
			c1.set(yy, mm, dd);
			accessPeriod.add(c1);
			System.out.println();
			List<Object> object = new ArrayList<Object>();
			for (Calendar cal : accessPeriod)
				object.add(cal);
			FileManager.writeObjectToFile("accessPeriod.dat", object);
			System.out.println("Access Period has been Updated");
		} else {
			System.out.println("Access Period remains the same");
		}
	}
	
	/**
     * This function is used by the admin to add a new Student
     */
	public void addStudent() {
		System.out.println("Enter the following details to add a new student to the system:");
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Full name of student: ");
			String name = sc.next();
			System.out.println("Student account password: ");
			String password = sc.next();
			System.out.println("Student account email: ");
			String email = sc.next();
			while (!email.contains("@")) {
				System.out
						.println("Invalid email address. The email address should be of the form emailId@e.ntu.edu.sg");
				System.out.println("Please enter the email ID again: ");
				email = sc.next();
			}
			System.out.println("Student's date of birth (DD-MM-YYYY format):");
			String date = sc.next();
			String[] arrOfStr = date.split("-", 3);
			while (date.length() != 10 || arrOfStr.length != 3) {
				System.out.println(
						"Invalid date of birth. Please enter again in DD-MM-YYYY format and include the hyphens.");
				System.out.println("Student's date of birth (DD-MM-YYYY format):");
				date = sc.next();
				arrOfStr = date.split("-", 3);
			}
			int dd = Integer.parseInt(arrOfStr[0]);
			int mm = Integer.parseInt(arrOfStr[1]);
			int yy = Integer.parseInt(arrOfStr[2]);
			Calendar c = Calendar.getInstance();
			c.set(yy, mm, dd);
			System.out.println("Enter the student's school ID: ");
			String school = sc.next();
			System.out.println("Enter the student's degree code: ");
			String degree = sc.next();
			System.out.println("Student's Matric Number: ");
			String mat_num = sc.next();
			System.out.println();
			if (StudentManager.addStudent(name, password, email, c, mat_num, school, degree)) {
				System.out.println("Student added successfully!\n");
				StudentManager.printStudentInfo();
				return;
			} else {
				System.out.println("Cannot add students with duplicate matric numbers.");
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
	
	/**
     * This function is used by the admin to add a new Course
     */
	public void addCourse() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Adding a new course... ");
			System.out.println("What is the course id? e.g. CZ2002");
			String courseID = sc.next();
			
			if (CourseManager.checkIfCourseExists(courseID)) {
				System.out.println("Course Already Exists!");
				while (true) {
					System.out.println("Do you want to try again? (y/n)");
					loop = sc.next().charAt(0);
					loop = Character.toLowerCase(loop);
					if (loop == 'y' || loop == 'n')
						break;
				}
			}
			if (loop == 'n')
				break;
			System.out.println("What is the course name? e.g. Object Oriented Programming and Design");
			String courseName = sc.next();
			System.out.println("What is the course faculty? e.g. SCSE");
			String faculty = sc.next();
			System.out.println("How many Academic Units (AU) does the course have? eg. 3");
			int au = sc.nextInt();
			System.out.println("Does the course have tutorials? e.g. True/False");
			boolean hasTutorial = false;
			String tut = sc.next().toLowerCase();
			if (tut.equals("true"))
				hasTutorial = true;
			System.out.println("Does the course have lab sessions? e.g. True/False");
			boolean hasLab = false;
			String lab = sc.next().toLowerCase();
			if (lab.equals("true"))
				hasLab = true;

			System.out.println("How many lecture sessions does the course have per week? eg. 1");
			int numLectures = sc.nextInt();
			List<Session> lectures = new ArrayList<Session>();
			for (int i = 0; i < numLectures; i++) {
				System.out.println("Adding lecture " + i + "...");
				Session lecture = addSession(i);
				lectures.add(lecture);
			}
			
			System.out.println();
			CourseManager.addCourse(courseID, courseName, faculty, au, null, lectures, hasTutorial, hasLab);
			CourseManager.printCourseInfo();
			System.out.println("Succesfully added Course!");
			loop = 'n';
		}
	}
	
	/**
     * This function is used by the admin to update an existing Course
     */
	public void updateCourse() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Updating a new course... ");
			System.out.println("What is the course id? e.g. CZ2002");
			String courseID = sc.next();
			if (!CourseManager.checkIfCourseExists(courseID)) {
				System.out.println("Course Does not Exist!");
				while (true) {
					System.out.println("Do you want to try again? (y/n)");
					loop = sc.next().charAt(0);
					loop = Character.toLowerCase(loop);
					if (loop == 'y' || loop == 'n')
						break;
				}
			}
			if (loop == 'n')
				break;
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
			
			System.out.println();
			CourseManager.updateCourse(courseID, courseName, faculty, au, null, lectures, hasTutorial, hasLab);
			System.out.println("Succesfully updated Course!");
			loop = 'n';
		}
	}
	
	/**
     * This function is used by the admin to delete an existing Course
     */
	public void deleteCourse() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Deleting course... ");
			System.out.println("What is the course id? e.g. CZ2002");
			String courseID = sc.next();
			if (CourseManager.checkIfCourseExists(courseID)) {
				CourseManager.deleteCourse(courseID);
				System.out.println();
				System.out.println("Succesfully deleted Course!");
				break;
			} else {
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
	
	/**
     * This function is used by the admin to add a new Index to a Course
     */
	public void addIndex() {
		CourseManager.printCourseIDs();
		Course course = null;
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("\nAdding a new index... See below for the list of Course IDs ");
			System.out.println("What is the ID of the course that the index belongs? e.g. CZ2002");
			String courseID = sc.next();
			if (CourseManager.checkIfCourseExists(courseID)) {
				System.out.println("What is the index's ID? e.g. BCG10");
				course = CourseManager.findCourse(courseID);
				String indexID = sc.next();
				if (!IndexManager.checkIfIndexExists(indexID)) {
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
					System.out.println();
					Index newIndex = IndexManager.addIndex(indexID, course, totalVacancies, tutorials, labs);
					if (newIndex == null) {
						System.out.println("Index already exists");
					} else {
						CourseManager.addIndexToCourse(course.getID(), newIndex);
						System.out.println("Succesfully added Index!");

						IndexManager.addIndex(indexID, course, totalVacancies, tutorials, labs);
						loop = 'n';
					}
				}
			}
			if (loop == 'n')
				break;
			System.out.println("Invalid Credentials");
			while (true) {
				System.out.println("Do you want to try again? (y/n)");
				loop = sc.next().charAt(0);
				loop = Character.toLowerCase(loop);
				if (loop == 'y' || loop == 'n')
					break;
			}
		}
	}
	
	/**
     * This function is used by the admin to update an existing Index in a Course
     */
	public void updateIndex() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Updating an index... See below for the list of Index IDs ");
			IndexManager.printIndexIDs();
			System.out.println("What is the index's ID? e.g. BCG10");
			String indexID = sc.next();
			if (IndexManager.checkIfIndexExists(indexID)) {
				CourseManager.printCourseIDs();
				Course course = null;

				System.out.println("What is the ID of the course that the index belongs? e.g. CZ2002");
				String courseID = sc.next();
				if (CourseManager.checkIfCourseExists(courseID)) {
					course = CourseManager.findCourse(courseID);
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
					System.out.println();
					Index newIndex = IndexManager.updateIndex(indexID, course, totalVacancies, tutorials, labs);
					CourseManager.deleteIndexFromCourse(course.getID(), newIndex);
					CourseManager.addIndexToCourse(course.getID(), newIndex);
					System.out.println("Succesfully updated Index!");

					IndexManager.updateIndex(indexID, course, totalVacancies, tutorials, labs);
					loop = 'n';
				} else {
					System.out.println("The course ID you entered does not exist");
				}
			} else {
				System.out.println("Index DOes not Exist");
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
	
	/**
     * This function is used by the admin to delete an Index of a Course
     */
	public void deleteIndex() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Deleting index... ");
			IndexManager.printIndexIDs();
			System.out.println("What is the index id? e.g. BCG10");
			String indexID = sc.next();
			System.out.println();
			if (IndexManager.checkIfIndexExists(indexID)) {

				IndexManager.deleteIndex(indexID);
				System.out.println("Succesfully deleted Index!");
				loop = 'n';
			} else {
				System.out.println("Index Does not Exist");
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
	
	/**
     * This function is used by the admin to check the vacancy in a Index
     */
	public void checkVacancyInIndexSlot() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Enter the Index: ");
			String indexID = sc.next();
			System.out.println();
			if (IndexManager.checkIfIndexExists(indexID)) {
				Index index = IndexManager.findIndex(indexID);
				System.out.println(indexID + " has "
						+ Integer.toString(index.getTotalVacancies() - index.getNumStudentsEnrolled()) + " vacancies");
				loop = 'n';
			} else {
				System.out.println("Index does not exist");
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
	
	/**
     * This function is used by the admin to print all the Students enrolled in an Index
     */
	public void printStudentListByIndex() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Enter the Index: ");
			String IndexCode = sc.next();
			if (IndexManager.checkIfIndexExists(IndexCode)) {
				List<Student> studentsEnrolled = StudentCourseManager.getStudentsInIndex(IndexCode);
				if (studentsEnrolled.size() == 0)
					System.out.println("There are 0 students enrolled in the index " + IndexCode);
				else {
					if (studentsEnrolled.size() > 0) {
					for (Student student : studentsEnrolled) {
						System.out.println("Student Name: " + student.getName() + " ,Matric Number: " + student.getMatricNo());
					}
					}
				}
				loop = 'n';
			} else {
				System.out.println("Index does not exist");
				while (true) {
					System.out.println("Do you want to try again? (y/n)");
					loop = sc.next().charAt(0);
					loop = Character.toLowerCase(loop);
					if (loop == 'y' || loop == 'n')
						break;
				}
			}
		}
		System.out.println();
	}
	
	/**
     * This function is used by the admin to print all the Students enrolled in a Course
     */
	public void printStudentListByCourse() {
		char loop = 'y';
		while (loop == 'y') {
			System.out.println("Enter the Course: ");
			String CourseCode = sc.next();
			System.out.println();
			if (CourseManager.checkIfCourseExists(CourseCode)) {
				List<Student> studentsEnrolled = StudentCourseManager.getStudentsInCourse(CourseCode);
				if (studentsEnrolled.size() == 0)
					System.out.println("There are no students enrolled in the Course " + CourseCode);
				else {
					for (Student student : studentsEnrolled) {
						System.out.println("\nStudent Name: " + student.getName() + " ,Matric Number: " + student.getMatricNo());
					}
				}
				loop = 'n';
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
}
