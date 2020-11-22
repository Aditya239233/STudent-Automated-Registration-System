package controller;

import model.Student;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import controller.FileManager;

public class StudentManager {
	private static List<Student> StudentList = new ArrayList<Student>();

	public static void init() {
		List<Object> objects = FileManager.readObjectFromFile("student.dat");
		for (Object o : objects)
			StudentList.add((Student) o);
	}

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

	public static boolean checkIfStudentExists(String MatricNo) {
		return StudentList.stream().anyMatch(Student -> MatricNo.equals(Student.getMatricNo()));
	}
}
