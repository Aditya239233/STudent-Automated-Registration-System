package view;

import java.util.Calendar;
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
				addStudent();
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

	public void addStudent(){
		System.out.println("Enter the following details to add a new student to the system:");
		System.out.println("Full name of student: ");
		String name = sc.nextLine();
		System.out.println("Student account password: ");
		String password = sc.nextLine();
		System.out.println("Student account email: ");
		String email = sc.nextLine();
		while(!email.contains("@e.ntu.edu.sg")){
			System.out.println("Invalid email address. The email address should be of the form emailId@e.ntu.edu.sg");
			System.out.println("Please enter the email ID again: ");
			email = sc.nextLine();
		}
		System.out.println("Student's date of birth (DD-MM-YYYY format):");
		String dob = sc.nextLine();
		String[] arrOfStr = dob.split("-", 3);
		while(dob.length() != 10 || arrOfStr.length != 3){
			System.out.println("Invalid date of birth. Please enter again in DD-MM-YYYY format and include the hyphens.");
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
		while(!StudentManager.addStudent(name, password, email, c, mat_num, school, degree)){
			System.out.println("Cannot add students with duplicate matric numbers.");
			System.out.println("Please enter another matric number: ");
			mat_num = sc.nextLine();
		}
		System.out.println("Student added successfully!");
		return;
	}
}
