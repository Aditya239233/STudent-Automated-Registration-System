package view;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import controller.CourseManager;
import model.Course;
import model.Index;
import model.Student;
import controller.FileManager;

public class StudentUI {

	private int choice;
	private Student student; // Student object for the student who is logged in
	private CourseManager cm = new CourseManager();
	private FileManager f = new FileManager();
	Scanner sc = new Scanner(System.in);

	public StudentUI(Student s){
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
			System.out.println("6. Swop Index Number with Another Student"); //Devansh
			System.out.println("7. Change notification mode"); // Devansh
			System.out.println("8. Change Email"); // Devansh
			System.out.println("9. Log out");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please enter valid option:");
			}
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// Register Course
				addCourse();	
				break;
			case 2:
				// Drop Course
				dropCourse();
				break;
			case 3:
				// Check/Print Courses Registered
				student.printCoursesRegistered();
				break;
			case 4:
				// Check vacancies available
				break;
			case 5:
				// Change index number of Course
				break;
			case 6:
				// Swop index with another student
				student.printCoursesRegistered();
				swopIndex();
			case 7:
				// Change Notification mode. But for Notification we need to implement only
				// email.
				break;
			case 8:
				// Change Email or Password
				break;
			case 9:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 9);
	}
	
	public void addCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		List<Course> courseList = new ArrayList<Course>();
		List<Object> objectList = f.readObjectFromFile("dat/course.dat");
        for(Object o: objectList){
            courseList.add((Course)o);
        }
        Boolean courseFound = false;
        for (Course c: courseList) {
        	courseFound = true;
        	if (c.getID().equals(CourseCode)) {
        		System.out.println("Enter the Course Index: ");
				String IndexCode = sc.nextLine();
				for (Index i: c.getIndexList()) {
					if (i.getID().equals(IndexCode)) {
						student.addCourse(c, i);
						break;
					}
				}
        	}
        	if (courseFound)
        		break;
        }
	}
	
	public void dropCourse() {
		System.out.println("Enter the Course Code: ");
		String CourseCode = sc.nextLine();
		student.removeCourse(CourseCode);
	}
	
	public void swopIndex() {
		System.out.println("Enter the course code whose index number you would like to swap: ");
		String course = sc.nextLine();
		while(!CourseManager.checkIfCourseExists(course)){
			System.out.println("Invalid course code, please enter a valid course code: ");
			course = sc.next();
		}
		System.out.println("Enter the index number you want to transfer to: ");
		String new_index = sc.nextLine();
		Course c = CourseManager.findCourse(course);
		if(student.swapIndex(course, new_index))
			System.out.println("Index swap successful!");
		else 
			System.out.println("Index swap unsuccesful. Please try again.");
	}
}
