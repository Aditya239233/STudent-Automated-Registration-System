package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import controller.CourseManager;
import controller.FileManager;
import model.Course;
import model.Index;
import model.NotificationMode;
import model.Student;
import controller.StudentCourseManager;

public class StudentUI implements UserUI {

	private int choice;
	private Student student; // Student object for the student who is logged in
	Scanner sc = new Scanner(System.in);

	public StudentUI(Student s) {
		this.student = s;
	}

	/**
	 * This function is used to perform all Student actions
	 */
	public void display() {

		do {
			System.out.println("\n#########################################################################");
			System.out.println("Please Select One of the Options Below");
			System.out.println("1. Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check / Print Courses Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swop Index Number with Another Student");
			System.out.println("7. Change notification mode");
			System.out.println("8. Log out");
			System.out.println("\n#########################################################################");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please enter valid option:");
			}
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Input");
				System.out.println("Do you want to Try again? (y/n)");
				char trial = sc.next().toLowerCase().charAt(0);
				if (trial == 'y')
					display();
				return;
			}
			System.out.println("\n");
			switch (choice) {
			case 1:
				addCourse();
				student.printCoursesRegistered();
				break;
			case 2:
				dropCourse();
				student.printCoursesRegistered();
				break;
			case 3:
				student.printCoursesRegistered();
				break;
			case 4:
				checkVacancyAvailable();
				break;
			case 5:
				swopIndex();
				break;
			case 6:
				swopIndexWithPeer();
				break;
			case 7:
				changeNotificationMode();
				StudentCourseManager.writeStudentToFile(student);
				break;
			case 8:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 8);
	}

	/**
	 * This function is used by a Student to add a Course to his Timetable
	 */
	private void addCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.next();
		System.out.println("Enter the Course Index: ");
		String IndexCode = sc.next();
		int result = StudentCourseManager.registerCourse(student, CourseCode, IndexCode);
		System.out.println();
		if (result == -3)
			System.out.println("Course Not Found");
		else if (result == -2)
			System.out.println("Index Not Found");
		else if (result == -1)
			System.out.println("There are no vacancies. You've been added to the waitlist");
		else if (result == 0)
			System.out.println("There is a TimeTable Clash. Cannot Add this Course");
		else if (result == -4)
			System.out.println("You're already registered to this course! Try Registering to Another Course.");
		else if (result == -5)
			System.out.println("You can't exceed 22 AUs. You already have " + student.getAU());
		else {
			StudentCourseManager.writeStudentToFile(student);
			System.out.println("Succesfully Added " + CourseCode);
		}
	}

	/**
	 * This function is used by a Student to remove a Course to his Timetable
	 */
	private void dropCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.next();
		String idx = null;
		System.out.println();
		for (Index i : student.getIndexes()) {
			if (i.getCourse().getID().equals(CourseCode)) {
				idx = i.getID();
				break;
			}
		}
		Boolean result = student.removeCourse(CourseCode);

		if (result) {
			StudentCourseManager.writeStudentToFile(student);
			List<Object> object = FileManager.readObjectFromFile("course.dat");
			List<Course> courses = new ArrayList<Course>();
			for (Object o : object) {
				Course c = (Course) o;
				if (c.getID().equals(CourseCode)) {
					for (int i = 0; i < c.getIndexList().size(); i++) {
						if (c.getIndexList().get(i).getID().equals(idx)) {
							c.getIndexList().get(i)
									.setNumStudentEnrolled(c.getIndexList().get(i).getNumStudentsEnrolled() - 1);
							break;
						}
					}
				}
				courses.add(c);
			}
			FileManager.writeCourseToFile("course.dat", courses);
			System.out.println("Succesfully Dropped the Course " + CourseCode);
		} else
			System.out.println("You're not enrolled in the Course " + CourseCode);
	}

	/**
	 * This function is used by a Student to swop an index of a Course in his/her
	 * timetable
	 */
	private void swopIndex() {
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String CourseCode = sc.next();
		String idx = null;
		for (Index i : student.getIndexes()) {
			if (i.getCourse().getID().equals(CourseCode)) {
				idx = i.getID();
				break;
			}
		}
		System.out.println("Enter the index code you would like to swap: ");
		String IndexCode = sc.next();
		System.out.println();
		int result = StudentCourseManager.swopIndex(student, CourseCode, IndexCode);
		if (result == -1)
			System.out.println("Index " + IndexCode + " does not have any vacancy");
		else if (result == -2)
			System.out.println("Course not found!");
		else if (result == -1)
			System.out.println("You're not registered to the Course");
		else if (result == 0)
			System.out.println("Could not swop index! There's a timetable clash");
		else {
			List<Object> object = FileManager.readObjectFromFile("course.dat");
			List<Course> courses = new ArrayList<Course>();
			for (Object o : object) {
				Course c = (Course) o;
				if (c.getID().equals(CourseCode)) {
					for (int i = 0; i < c.getIndexList().size(); i++) {
						if (c.getIndexList().get(i).getID().equals(idx)) {
							c.getIndexList().get(i)
									.setNumStudentEnrolled(c.getIndexList().get(i).getNumStudentsEnrolled() + 1);
						} else if (c.getIndexList().get(i).getID().equals(IndexCode))
							c.getIndexList().get(i)
									.setNumStudentEnrolled(c.getIndexList().get(i).getNumStudentsEnrolled() - 1);
					}
				}
				courses.add(c);
			}
			StudentCourseManager.writeStudentToFile(student);
			System.out.println("Succesfully Swopped the index of " + CourseCode + " to " + IndexCode);
		}
	}

	/**
	 * This function is used by a Student to swop an index of a Course with a peer
	 * in his/her timetable
	 */
	private void swopIndexWithPeer() {
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String CourseCode = sc.next();
		System.out.println("Enter the matric number of the student you would like to swap with: ");
		String s2_mat = sc.next();
		System.out.println();
		int result = StudentCourseManager.swopIndexWithPeer(student, CourseCode, s2_mat);
		if (result == -1)
			System.out.println("You have not been enrolled in the course " + CourseCode);
		else if (result == -2)
			System.out.println("Matric Number " + s2_mat + " does not exist");
		else if (result == -3)
			System.out.println("Your peer has not been enrolled in the course " + CourseCode);
		else
			System.out.println("Succesfully Added Course");
	}

	/**
	 * This function is used by a Student to swop to change the Notification Mode
	 */
	private void changeNotificationMode() {
		NotificationMode curr_nm = student.getNotificationMode();
		boolean val1 = false, val2 = false;
		System.out.println("Your current notification mode is: " + curr_nm);
		System.out.println("Would you like to change your notification mode? (y/n)");
		char opt = sc.next().charAt(0);
		System.out.println();
		while (!val1 || !val2) {
			switch (opt) {
			case 'y': {
				System.out.println("Choose new notification mode: ");
				System.out.println("1. Email");
				System.out.println("2. SMS");
				System.out.println("3. WhatsApp");
				int new_nm = sc.nextInt();
				System.out.println();
				switch (new_nm) {
				case 1: {
					student.setNotificationMode(NotificationMode.EMAIL);
					System.out.println("Notification mode changed successfully to EMAIL");
					val1 = true;
					break;
				}
				case 2: {
					student.setNotificationMode(NotificationMode.SMS);
					System.out.println("Notification mode changed successfully to SMS");
					val1 = true;
					break;
				}
				case 3: {
					student.setNotificationMode(NotificationMode.WHATSAPP);
					System.out.println("Notification mode changed successfully to WHATSAPP");
					val1 = true;
					break;
				}
				default: {
					System.out.println("Invalid choice");
				}
				}
				val2 = true;
				break;
			}
			case 'n': {
				System.out.println("Notification mode will remain unchanged: " + curr_nm);
				val1 = true;
				val2 = true;
				break;
			}
			default: {
				System.out.println("Invalid choice");
			}
			}
			StudentCourseManager.writeStudentToFile(student);
		}
	}

	/**
	 * This function is used by a Student to check the Vacancy Available in a Course
	 */
	private void checkVacancyAvailable() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.next();
		System.out.println();
		int result = CourseManager.getCourseVacancy(CourseCode);
		int total = CourseManager.getTotalCourseVacancy(CourseCode);
		if (result == -1)
			System.out.println("Course does not exist");
		else
			System.out.println("Vacancy for the Course " + CourseCode + " is " + result + "/" + total);
	}

}
