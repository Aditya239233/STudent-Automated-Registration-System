package view;

import java.io.Console;
import java.util.*;

//import java.io.Console;
import model.Student;
import model.Admin;
import model.Course;
import controller.CourseManager;
import controller.FileManager;
import controller.IndexManager;
import controller.PasswordManager;
import controller.StudentManager;

public class Main {

	private static int user;
	private static Student student;
	private static PasswordManager pm = new PasswordManager();
	// private static Console console = System.console();
	static Scanner sc = new Scanner(System.in);
	
	/**
     * Main function. Program execution begins from here
     * @param args
     */
	public static void main(String[] args) {

		if (CourseManager.getCourseList() == null) {
			CourseManager.setCourseList(new ArrayList<Course>());
		}
		displayWelcome();
		while (true) {
			System.out.println("1. Student");
			System.out.println("2. Admin");
			System.out.println("3. Exit");
			user = sc.nextInt();
			if (user == 3) {
				System.out.println("\nHope to see you soon!");
				System.exit(0);
			} else if (!(user == 1 || user == 2)) {
				System.out.println("Invalid Choice! Try Again");
				continue;
			}
			if (user == 1) {
				Boolean isLogged = studentLogin();
				if (isLogged) {
					System.out.println("\n### Login Successful! ###");
					UserUI ui = new StudentUI(student);
					ui.display();
				} else {
					continue;
				}

			} else {
				Boolean isLogged = adminLogin();
				if (isLogged) {
					System.out.println("\n### Login Successful! ###");
					AdminUI ui = new AdminUI();
					ui.display();
				} else {
					continue;
				}
			}

		}
	}
	/**
     * This function is used to display the Welcome Message
     */
	private static void displayWelcome() {
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
	
	/**
     * This function is used by the Student to Login 
     */
	public static Boolean studentLogin() {
		if (checkAccessPeriod()) {
			System.out.print("Enter your Matric Number: ");
			String matricNumber = sc.next();

			// Mask Password
			System.out.println("Enter your password: ");
			String password = sc.next();
			password = pm.hashPassword(password);
			List<Object> records = FileManager.readObjectFromFile("student.dat");
			List<Student> students = new ArrayList<Student>();
			for (Object o : records)
				students.add((Student) o);
			for (Student s : students)
				if (s.getMatricNo().equals(matricNumber)) {
					if (s.getPassword().equals(password)) {
						student = s;
						return true;
					}
				}
			System.out.println("Incorrect Login Credentials");
			return false;
		} else {
			System.out.println("Cannot Login now. STARS Planner is not Active");
			return false;
		}

	}
	
	/**
     * This function is used by the Admin to Login 
     */
	public static Boolean adminLogin() {
		Console cnsl = System.console();
		StringBuilder sb = new StringBuilder();
		System.out.print("Enter your Email ");
		String email = sc.next();

		// Mask Password
		System.out.println("Enter your password: ");
		char[] pwd = cnsl.readPassword("Password: ");
		for (Character ch : pwd) { 
            sb.append(ch); 
		}
		String password = sb.toString();
		String hashedPassword = PasswordManager.hashPassword(password);
		List<Object> records = FileManager.readObjectFromFile("admin.dat");
		List<Admin> admins = new ArrayList<Admin>();
		for (Object o : records)
			admins.add((Admin) o);
		for (Admin a : admins)
			if (a.getEmail().equals(email)) {
				if (a.getPassword().equals(hashedPassword)) {
					try {
						CourseManager.init();
						IndexManager.init(CourseManager.getCourseList());
						StudentManager.init();
					} catch (Exception e) {
						System.out.println(e);
					}
					return true;
				}
			}
		return false;
	}
	
	/**
     * This function is used to check whether the Date and Time is within the Access Period for Student 
     */
	public static Boolean checkAccessPeriod() {
		Boolean canLogin = false;
		List<Object> objects = FileManager.readObjectFromFile("accessPeriod.dat");
		List<Calendar> accessPeriod = new ArrayList<Calendar>();
		for (Object o : objects)
			accessPeriod.add((Calendar) o);
		Calendar currDate = Calendar.getInstance();
		if (currDate.before(accessPeriod.get(1)) && currDate.after(accessPeriod.get(0)))
			canLogin = true;
		return canLogin;
	}
}
