package view;

import java.util.Scanner;

import controller.CourseManager;
import controller.IndexManager;
import controller.StudentManager;
import model.Index;

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
			System.out.println("6. Check available slot for an index number");
			System.out.println("7. Print student list by index number");
			System.out.println("8. Print student list by course");
			System.out.println("9. Log out");
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
				break;
			case 4:
				// Update Course
				break;
			case 5:
				// Delete Course
				break;
			case 6:
				// Check available slot for an index number
				checkIndexSlot();
				break;
			case 7:
				// Print student list by index number
				printStudentListIndex();
				break;
			case 8:
				// Print student list by course
				printStudentListCourse();
				break;
			case 9:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 7);
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
		
	}
	
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
