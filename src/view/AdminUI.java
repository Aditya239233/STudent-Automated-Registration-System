package view;

import java.util.Scanner;

public class AdminUI {

	private int choice;
	Scanner sc = new Scanner(System.in);

	public void display() {
		do {
			System.out.println("Please Select One of the Options Below");
			System.out.println("1. Edit Student Access Period");
			System.out.println("2. Add a Student");
			System.out.println("3. Add/Update a course");
			System.out.println("4. Check available slot for an index number");
			System.out.println("5. Print student list by index number");
			System.out.println("6. Print student list by course");
			System.out.println("7. Log out");
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
				// Add/Update Course
				break;
			case 4:
				// Check available slot for an index number
				break;
			case 5:
				// Print student list by index number
				break;
			case 6:
				// Print student list by course
				break;
			case 7:
				break;
			default:
				System.out.print("Enter a valid Option");
			}

		} while (choice != 7);
	}
}
