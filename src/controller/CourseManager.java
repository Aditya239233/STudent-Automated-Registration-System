package controller;

import model.Tutorial;
import model.Lab;

import model.Course;
import model.Index;
import model.Session;

import java.util.List;
import java.util.Objects;

public class CourseManager {
	private static List<Course> CourseList;

	public static void addCourse(String Name, String ID, String faculty, int au, List<Index> indexList,
			List<Session> lectures) {
		if (checkIfCourseExists(ID)) {
			System.out.println("Course already exists");
			return;
		}
		Course newCourse = new Course(Name, ID, faculty, au, indexList, lectures);
		CourseList.add(newCourse);
		System.out.println("Succesfully Course Added");
	}

	public static void deleteCourse(List<Course> CourseList, String CourseID) {
		if (checkIfCourseExists(CourseID)) {
			for (Course course : CourseList) {
				if (CourseID.equals(course.getID())) {
					CourseList.remove(course);
					break;
				}
			}
			System.out.println("Course Has been deleted");
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static void updateCourse(List<Course> CourseList, String CourseID) {
		// Updates a Course in the CourseList
		if (checkIfCourseExists(CourseID)) {
			for (Course course : CourseList) {
				if (CourseID.equals(course.getID())) {
					// implement update course logic: name, index (tutorials, labs), lectures
					// add appropriate input parameters (Follow updated implementation of addCourse
					break;
				}
			}
			System.out.println("Course Has been updated");
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static Course findCourse(String CourseID) {
		// finds Course with specific ID in CourseList
		return CourseList.stream().filter(Course -> Course.getID() == CourseID).findFirst().orElse(null);
	}

	private static boolean checkIfCourseExists(String CourseID) {
		return CourseList.stream().anyMatch(Course -> CourseID.equals(Course.getID()));
	}
}
