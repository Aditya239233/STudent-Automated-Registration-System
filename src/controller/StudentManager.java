package controller;

import model.Student;

import java.util.Date;
import java.util.List;

public class StudentManager {
	private static List<Student> StudentList;
	
	public void addStudent(String Name, String Password, String Email, Date dob, String MatricNo, String SchoolID, String Degree) {
		if (checkIfStudentExists(MatricNo)) {
			System.out.println("Matriculation Number already exists");
			return;
		}
		Student newStudent = new Student(Name, Password, Email, dob, MatricNo, SchoolID, Degree);
		StudentList.add(newStudent);
		System.out.println("Succesfully Added Student");
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
		}
		else {
			System.out.println("Student Does not Exist");
		}
	}
	
	public boolean checkIfStudentExists(String MatricNo) {
		return StudentList.stream().anyMatch(Student -> MatricNo.equals(Student.getMatricNo()));
	}
}
