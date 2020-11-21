package controller;

import model.Student;

import java.util.Calendar;
import java.util.List;

public class StudentManager extends UserManager {
	private static List<Student> StudentList;

	public static boolean addStudent(String Name, String Password, String Email, Calendar dob, String MatricNo, String SchoolID,
			String Degree) {
		if (checkIfStudentExists(MatricNo)) {
			System.out.println("Matriculation Number already exists");
			return false;
		}
		Student newStudent = new Student(Name, Password, Email, dob, MatricNo, SchoolID, Degree);
		StudentList.add(newStudent);
		System.out.println("Succesfully Added Student");
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
			System.out.println("Student Has been deleted");
		} else {
			System.out.println("Student Does not Exist");
		}
	}

	public static boolean checkIfStudentExists(String MatricNo) {
		return StudentList.stream().anyMatch(Student -> MatricNo.equals(Student.getMatricNo()));
	}
}
