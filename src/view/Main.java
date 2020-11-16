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
		displayWelcome();
		while (true) {
			System.out.println("1. Student");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			this.user = sc.nextInt();
			if (this.user == 3) {
				System.exit(0);
			} else if (this.user != 1 || this.user != 2) {
				System.out.println("Invalid Choice! Try Again");
				continue;
			}
			login();
			if (this.user == 1) {
				StudentUI newUI = new StudentUI();
				newUI.display();
				// Perform Student Login
				break;
			} else {
				AdminUI newUI = new AdminUI();
				newUI.display();
				// Perform Admin Login
				break;
			}

		}
	}

	private void displayWelcome() {
		System.out.println("#########################################################################");
		System.out.println("#\t\t___  ___      _____ _____ ___  ______  _____ \t\t#");
		System.out.println("#\t\t|  \\/  |     /  ___|_   _/ _ \\ | ___ \\/  ___|\t\t#");
		System.out.println("#\t\t| .  . |_   _\\ `--.  | |/ /_\\ \\| |_/ /\\ `--. \t\t#");
		System.out.println("#\t\t| |\\/| | | | |`--. \\ | ||  _  ||    /  `--. \\\t\t#");
		System.out.println("#\t\t| |  | | |_| /\\__/ / | || | | || |\\ \\ /\\__/ /\t\t#");
		System.out.println("#\t\t\\_|  |_/\\__, \\____/  \\_/\\_| |_/\\_| \\_|\\____/ \t\t#");
		System.out.println("#\t\t         __/ |                               \t\t#");
		System.out.println("#\t\t        |___/                                \t\t#");
		System.out.println("#\t    Welcome to My Student Automated Registration System    \t#");
		System.out.println("#########################################################################");
	}

	private void login() {
		System.out.print("Enter your Username: ");
		this.setUsername(sc.next());

		// Mask Password
		System.out.println("Enter your password: ");
		this.setPassword(sc.next());

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
