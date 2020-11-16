package view;

import java.util.Scanner;

public class StudentUI {

	private int choice;
	Scanner sc = new Scanner(System.in);

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
			System.out.println("8. Change Number or Email");
			System.out.println("9. Log out");
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please enter valid option:");
			}
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				// Register Course
				break;
			case 2:
				// Drop Course
				break;
			case 3:
				// Check/Print Courses Registered
				break;
			case 4:
				// Check vacancies available
				break;
			case 5:
				// Change index number of Course
				break;
			case 6:
				// Swop index with another student
				break;
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
}
