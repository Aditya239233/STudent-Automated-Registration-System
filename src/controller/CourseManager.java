package controller;

import model.Course;
import model.Index;
import model.Session;

import java.util.List;

public class CourseManager {
	private static List<Course> CourseList;
	
	public static void addCourse(String Name, String ID, String faculty) {
		
		Course newCourse = new Course(Name, ID, faculty);
		CourseList.add(newCourse);
		System.out.println("Succesfully Course Added");
	}
	
	public static void deleteCourse(String CourseID) {
		if (checkIfCourseExists(CourseID)) {
			for (Course course : CourseList) {
				if (CourseID.equals(course.getID())) {
					CourseList.remove(course);
					break;
				}
			}
			System.out.println("Course Has been deleted");
		}
		else {
			System.out.println("Course Does not Exist");
		}
	}
	
	public static boolean checkIfCourseExists(String CourseID) {
		return CourseList.stream().anyMatch(Course -> CourseID.equals(Course.getID()));
	}
}
