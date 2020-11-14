package controller;

import model.Course;
import model.Index;
import model.Session;

import java.util.List;
import java.util.Scanner;

public class CourseManager {
	private static List<Course> CourseList;
	
	public static void printCourseIDs() {
		int length = CourseList.size();
		for (int i=0; i<length; i++) {
			System.out.println(i + ") " + CourseList.get(i).getID());
		}
	}
	
	public static void addCourse(String Name, String ID, String faculty, int au,  List<Index> indexList, List<Session> lectures) {
		if (checkIfCourseExists(ID)) {
			System.out.println("Course already exists");
			return;
		}
		
		Course newCourse = new Course(ID, Name, faculty, au);
		CourseList.add(newCourse);
		System.out.println("Succesfully Course Added");
	}
	
	public static void deleteCourse(String CourseID) {
		if (checkIfCourseExists(CourseID)) {
			Course course = findCourse(CourseID);
			CourseList.remove(course);
			System.out.println("Course Has been deleted");
		} else {
			System.out.println("Course Does not Exist");
		}
	}
	
	public static void updateCourse(String CourseID) {
		if (checkIfCourseExists(CourseID)) {
			Course course = findCourse(CourseID);
			
			System.out.println("Course Has been updated");
		} else {
			System.out.println("Course Does not Exist");
		}
	}
	
	public static Course findCourse(String CourseID) {
		// finds Course with specific ID in CourseList
		return	CourseList.stream()
				.filter(Course -> Course.getID() == CourseID)
				.findFirst()
				.orElse(null);
	}
	
	private static boolean checkIfCourseExists(String CourseID) {
		return CourseList.stream().anyMatch(Course -> CourseID.equals(Course.getID()));
	}
}
