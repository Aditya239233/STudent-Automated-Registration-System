package controller;

import model.Student;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class StudentManager {
	private static List<Student> StudentList = new ArrayList<Student>();
	
	/**
	 * This function is used to initialize the StudentList from database
	 */
	public static void init() {
		List<Object> objects = FileManager.readObjectFromFile("student.dat");
		for (Object o : objects)
			StudentList.add((Student) o);
	}
	
	/**
	 * This function is used to add a new Student to the database. The logic flow happens in this function
	 * @param Name - Name of the Student
	 * @param Password - Password of the Student
	 * @param Email - Email ID of the Student
	 * @param dob - Date of birth of the Student
	 * @param MatricNo - Matriculation Number of the Student
	 * @param SchoolID - School the student studies in
	 * @param Degree - refers to his/her Major
	 * @return
	 */
	public static boolean addStudent(String Name, String Password, String Email, Calendar dob, String MatricNo,
			String SchoolID, String Degree) {

		if (StudentList.size() == 0) {
			Student newStudent = new Student(Name, Password, Email, dob, MatricNo, SchoolID, Degree);
			List<Object> student = new ArrayList<Object>();
			student.add((Object) newStudent);
			System.out.println("Succesfully Added Student");
			FileManager.writeObjectToFile("student.dat", student);
			return true;
		}
		if (checkIfStudentExists(MatricNo)) {
			System.out.println("Matriculation Number already exists");
			return false;
		}

		Student newStudent = new Student(Name, Password, Email, dob, MatricNo, SchoolID, Degree);
		StudentList.add(newStudent);
		System.out.println("Succesfully Added Student");
		FileManager.addObjectToFile(newStudent);
		return true;
	}
	
	/**
	 * This function is used to delete a Student from the database. The logic flow happens in this function
	 * @param MatricNo - refers to the matriculation number of a Student
	 */
	public void deleteStudent(String MatricNo) {
		if (checkIfStudentExists(MatricNo)) {
			for (Student student : StudentList) {
				if (MatricNo.equals(student.getMatricNo())) {
					StudentList.remove(student);
					break;
				}
			}
			List<Object> students = new ArrayList<Object>();
			for (Student s : StudentList)
				students.add((Object) s);
			FileManager.writeObjectToFile("student.dat", students);
			System.out.println("Student Has been deleted");
		} else {
			System.out.println("Student Does not Exist");
		}
	}
	
	/**
	 * This function is used to check whether a student exists in the database 
	 * @param MatricNo - refers to the matriculation number of student
	 * @return
	 */
	public static boolean checkIfStudentExists(String MatricNo) {
		return StudentList.stream().anyMatch(Student -> MatricNo.equals(Student.getMatricNo()));
	}
	
	/**
	 * This function is used to print all the Students Information
	 */
	public static void printStudentInfo() {
		for (Student student: StudentList)
			System.out.println("Name: "+student.getName()+" Matric Number: "+student.getMatricNo() + " Degree:"+student.getDegree());
	}
}
