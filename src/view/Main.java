package view;

import java.util.Scanner;
import controller.StudentManager;
import controller.AdminManager;

public class Main {

	private String username;
	private String password;
	private int user;
	private StudentManager student;
	private AdminManager admin;

	Scanner sc = new Scanner(System.in);

	public void main() {
		login();
		while (true) {
			if (user == 1) {
				student.checkLogin(username, password);
				break;
			} else if (user == 2) {
				admin.checkLogin(username, password);
			} else
				System.out.println("Incorrect Username or Password! Try Again ");
		}
	}

	private void login() {
		System.out.println("1. Student");
		System.out.println("2. Admin");
		user = sc.nextInt();

		System.out.print("Enter your Username: ");
		username = sc.next();

		// Mask Password
		System.out.println("Enter your password");
		password = sc.next();

	}
}
