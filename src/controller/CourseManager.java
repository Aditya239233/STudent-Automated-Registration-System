package controller;

import model.Course;
import model.Index;
import model.Session;

import java.util.List;

public class CourseManager {
	private static List<Course> CourseList;
	
	
	public static void printCourseIDs() {
		int length = CourseList.size();
		for (int i=0; i<length; i++) {
			System.out.println(i + ") " + CourseList.get(i).getID());
		}
	}

	public static void addCourse(String courseID, String Name, String faculty, int au, List<Index> indexList, List<Session> lectures) {
		if (checkIfCourseExists(courseID)) {
			System.out.println("Course already exists");
			return;
		}
		
		Course newCourse;
		if (indexList != null) {
			newCourse = new Course(courseID, Name, faculty, au, indexList, lectures);
		} else {
			newCourse = new Course(courseID, Name, faculty, au, lectures);
		}
		CourseList.add(newCourse);
		System.out.println("Succesfully added Course");
	}

	public static void updateCourse(String courseID, String Name, String faculty, int au, List<Index> indexList, List<Session> lectures) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			int i = CourseList.indexOf(course);
			Course newCourse;
			if (indexList != null) {
				newCourse = new Course(courseID, Name, faculty, au, indexList, lectures);
			} else {
				newCourse = new Course(courseID, Name, faculty, au, lectures);
			}
			CourseList.set(i, newCourse);
			System.out.println("Succesfully updated Course");
		} else {

			System.out.println("Course does not exist");
		}
	}
	
	public static void deleteCourse(String courseID) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			CourseList.remove(course);
			System.out.println("Course Has been deleted");
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static Course findCourse(String CourseID) {
		// finds Course with specific ID in CourseList
		return CourseList.stream().filter(Course -> Course.getID() == CourseID).findFirst().orElse(null);
	}

	public static boolean checkIfCourseExists(String CourseID) {
		return CourseList.stream().anyMatch(Course -> CourseID.equals(Course.getID()));
	}

}