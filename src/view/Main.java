package view;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import model.Student;
import model.Admin;
import controller.FileManager;
import controller.PasswordManager;

public class Main {

	private int user;
	private Student student;
	PasswordManager pm = new PasswordManager();
	FileManager fm = new FileManager();

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
			if (this.user == 1) {
				Boolean isLogged = studentLogin();
				if (isLogged) {
					StudentUI ui = new StudentUI(this.student);
					ui.display();
					break;
				} else {
					System.out.println("Incorrect Login Credentials");
					continue;
				}

			} else {
				Boolean isLogged = adminLogin();
				if (isLogged) {
					AdminUI ui = new AdminUI();
					ui.display();
				} else {
					System.out.println("Incorrect Login Credentials");
					continue;
				}
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
		System.out.println("#\t    Welcome to My STudent Automated Registration System    \t#");
		System.out.println("#########################################################################");
	}

	public Boolean studentLogin() {
		System.out.print("Enter your Matric Number: ");
		String matricNumber = sc.next();

		// Mask Password
		System.out.println("Enter your password: ");
		String password = sc.next();
		password = pm.hashPassword(password);
		List<Object> records = fm.readObjectFromFile("student.dat");
		List<Student> students = new ArrayList<Student>();
		for (Object o : records)
			students.add((Student) o);
		for (Student s : students)
			if (s.getMatricNo().equals(matricNumber) && s.getPassword().equals(password)) {
				this.student = s;
				return true;
			}
		return false;
	}

	public Boolean adminLogin() {
		System.out.print("Enter your Email ");
		String email = sc.next();

		// Mask Password
		System.out.println("Enter your password: ");
		String password = sc.next();
		password = pm.hashPassword(password);
		List<Object> records = fm.readObjectFromFile("admin.dat");
		List<Admin> admins = new ArrayList<Admin>();
		for (Object o : records)
			admins.add((Admin) o);
		for (Admin a : admins)
			if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
				return true;
			}
		return false;
	}
}
