package view;

import java.util.Scanner;
import java.util.List;
import java.io.Console;

import controller.CourseManager;
import model.Index;
import model.NotificationMode;
import model.Student;
import controller.FileManager;
import controller.StudentManager;
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
				break;
			case 8:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 9);
	}

	public void addCourse() {
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
		else
			System.out.println("Succesfully Added " + CourseCode);
	}

	public void dropCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		Boolean result = student.removeCourse(CourseCode);
		if (result)
			System.out.println("Succesfully Dropped the Course " + CourseCode);
		else
			System.out.println("You're not enrolled in the Course " + CourseCode);
	}

	public void swopIndex() {
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String CourseCode = sc.nextLine();
		while (!CourseManager.checkIfCourseExists(CourseCode)) {
			System.out.println("Invalid course code, please enter a valid course code: ");
			CourseCode = sc.next();
		}
		System.out.println("Enter the index code you would like to swap: ");
		String IndexCode = sc.nextLine();
		// Test if valid index
		int result = scm.swopIndex(student, CourseCode, IndexCode);

		if (result == -1)
			System.out.println("You're not registered to the Course");
		else if (result == 0)
			System.out.println("Could not swop index! There's a timetable clash");
		else
			System.out.println("Succesfully Swopped the index of " + CourseCode + " to " + IndexCode);
	}

	public void swopIndexWithPeer() {
		StudentManager sm = new StudentManager();
		FileManager fm = new FileManager();
		String s1_index = " ";
		String s2_index = " ";
		Student s2 = new Student();
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String course = sc.nextLine();
		while (!CourseManager.checkIfCourseExists(course)) {
			System.out.println("Invalid course code, please enter a valid course code: ");
			course = sc.next();
		}
		List<Index> indexes = student.getIndexes();
		boolean is_registered = false;
		while (!is_registered) {
			for (Index i : indexes) {
				if (i.getCourse().getID() == course) {
					is_registered = true;
					s1_index = i.getID();
					break;
				}

			}
			if (is_registered) {
				break;
			} else {
				System.out.println(
						"You are not registered for this course. Index can only be swapped for registered courses");
				System.out.println("Enter the course code whose index number you would like to swap: ");
				course = sc.nextLine();
				continue;
			}
		}
		boolean is_valid = false;
		while (!is_valid) { // This loop ensures that the user enters a valid combo of student+index, i.e.
							// student must be registered for the index
			System.out.println("Enter the matric number of the student you would like to swap with: ");
			String s2_mat = sc.nextLine();
			while (!sm.checkIfStudentExists(s2_mat)) {
				System.out.println("Invalid student matric number, please enter a valid number: ");
				course = sc.next();
			}
			List<Object> students = fm.readObjectFromFile("Students.ser");
			Student s;
			for (Object o : students) {
				s = (Student) o;
				if (s.getMatricNo() == s2_mat) {
					is_valid = true;
					s2 = s;
				}
			}

			System.out.println("Enter the index number you want to transfer to: ");
			s2_index = sc.nextLine();
			if (s2.checkIfCourseRegistered(s2_index) && is_valid)
				break;
			else {
				System.out.println(
						"The given student and index number combination is invalid. Student must be registered for the given index");
				System.out.println("Please enter details again");
				is_valid = false;
			}
		}

		if (student.swapIndex(s1_index, s2, s2_index)) {
			System.out.println("Index swap successful!");
		} else
			System.out.println("Index swap unsuccessful. Please try again with a valid index.");
	}

	public void changeNotificationMode() {
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

		}
	}

	public void checkVacancyAvailable() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		int result = cm.getCourseVacancy(CourseCode);
		if (result == -1)
			System.out.println("Course does not exist");
		else
			System.out.println("Vacancy for the Course " + CourseCode + " is " + result);
	}
}
