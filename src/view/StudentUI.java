package view;

import java.util.Scanner;

import controller.CourseManager;
import model.NotificationMode;
import model.Student;
import controller.StudentCourseManager;

public class StudentUI {

	private int choice;
	private Student student; // Student object for the student who is logged in
	private CourseManager cm = new CourseManager();
	private StudentCourseManager scm = new StudentCourseManager();
	Scanner sc = new Scanner(System.in);

	public StudentUI(Student s) {
		this.student = s;
	}

	public void display() {

		do {
			System.out.println("Please Select One of the Options Below");
			System.out.println("1. Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check / Print Courses Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swop Index Number with Another Student");
			System.out.println("7. Change notification mode");
			System.out.println("8. Log out");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please enter valid option:");
			}
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addCourse();
				break;
			case 2:
				dropCourse();
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
			case 7:
				changeNotificationMode();
				scm.writeStudentToFile(student);
				break;
			case 8:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 8);
	}

	private void addCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		System.out.println("Enter the Course Index: ");
		String IndexCode = sc.nextLine();
		int result = scm.registerCourse(student, CourseCode, IndexCode);
		if (result == -3)
			System.out.println("Course Not Found");
		else if (result == -2)
			System.out.println("Index Not Found");
		else if (result == -1)
			System.out.println("There are no vacancies. You've been added to the waitlist");
		else if (result == 0)
			System.out.println("There is a TimeTable Clash. Cannot Add Course");
		else {
			scm.writeStudentToFile(student);
			System.out.println("Succesfully Added " + CourseCode);
		}
	}

	private void dropCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		Boolean result = student.removeCourse(CourseCode);
		if (result) {
			scm.writeStudentToFile(student);
			System.out.println("Succesfully Dropped the Course " + CourseCode);
		} else
			System.out.println("You're not enrolled in the Course " + CourseCode);
	}

	private void swopIndex() {
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String CourseCode = sc.nextLine();
		System.out.println("Invalid course code, please enter a valid course code: ");
		CourseCode = sc.next();
		System.out.println("Enter the index code you would like to swap: ");
		String IndexCode = sc.nextLine();
		// Test if valid index
		int result = scm.swopIndex(student, CourseCode, IndexCode);

		if (result == -1)
			System.out.println("You're not registered to the Course");
		else if (result == 0)
			System.out.println("Could not swop index! There's a timetable clash");
		else {
			scm.writeStudentToFile(student);
			System.out.println("Succesfully Swopped the index of " + CourseCode + " to " + IndexCode);
		}
	}

	private void swopIndexWithPeer() {
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String CourseCode = sc.nextLine();
		System.out.println("Enter the matric number of the student you would like to swap with: ");
		String s2_mat = sc.nextLine();
		int result = scm.swopIndexWithPeer(student, CourseCode, s2_mat);
		if (result == -1)
			System.out.println("You have not been enrolled in the course " + CourseCode);
		else if (result == -2)
			System.out.println("Matric Number " + s2_mat + " does not exist");
		if (result == -3)
			System.out.println("Your peer has not been enrolled in the course " + CourseCode);
		else
			System.out.println("Succesfully Added Course");
	}

	private void changeNotificationMode() {
		NotificationMode curr_nm = student.getNotificationMode();
		boolean val1 = false, val2 = false;
		System.out.println("Your current notification mode is: " + curr_nm);
		System.out.println("Would you like to change your notification mode? (y/n)");
		char opt = sc.next().charAt(0);
		while (!val1 || !val2) {
			switch (opt) {
			case 'y': {
				System.out.println("Choose new notification mode: ");
				System.out.println("1. Email");
				System.out.println("2. SMS");
				System.out.println("3. WhatsApp");
				int new_nm = sc.nextInt();
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
			scm.writeStudentToFile(student);
		}
	}

	private void checkVacancyAvailable() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		int result = cm.getCourseVacancy(CourseCode);
		if (result == -1)
			System.out.println("Course does not exist");
		else
			System.out.println("Vacancy for the Course " + CourseCode + " is " + result);
	}

}
