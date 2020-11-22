package controller;

import model.Course;
import model.Index;
import model.Session;

import java.util.List;
import java.util.ArrayList;

public class CourseManager {

	public static List<Course> CourseList = new ArrayList<Course>();

	public static void init() {
		try {
			List<Object> objects = FileManager.readObjectFromFile("course.dat");
			for (Object o : objects)
				CourseList.add((Course) o);
		} catch (Exception e) {
			System.out.println();
		}
	}

	public static void printCourseIDs() {
		int length = CourseList.size();
		for (int i = 0; i < length; i++) {
			System.out.println(i + ") " + CourseList.get(i).getID());
		}
	}

	public static List<Course> getCourseList() {
		return CourseList;
	}

	public static Course addCourse(String courseID, String Name, String faculty, int au, List<Index> indexList,
			List<Session> lectures) {
		if (CourseList.size() == 0) {
			Course newCourse;
			if (indexList != null) {
				newCourse = new Course(courseID, Name, faculty, au, indexList, lectures);
			} else {
				newCourse = new Course(courseID, Name, faculty, au, lectures);
			}
			CourseList.add(newCourse);
			List<Object> courses = new ArrayList<Object>();
			courses.add(newCourse);
			System.out.println("Succesfully added Course");
			FileManager.writeObjectToFile("course.dat", courses);
			return newCourse;
		}
		if (checkIfCourseExists(courseID)) {
			System.out.println("Course already exists");
			return null;
		}

		Course newCourse;
		if (indexList != null) {
			newCourse = new Course(courseID, Name, faculty, au, indexList, lectures);
		} else {
			newCourse = new Course(courseID, Name, faculty, au, lectures);
		}
		CourseList.add(newCourse);
		System.out.println("Succesfully added Course");
		FileManager.addObjectToFile(newCourse);
		return newCourse;
	}

	public static void updateCourse(String courseID, String Name, String faculty, int au, List<Index> indexList,
			List<Session> lectures) {
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
			List<Object> courses = new ArrayList<Object>();
			for (Course c : CourseList)
				courses.add((Object) c);
			FileManager.writeObjectToFile("course.dat", courses);
			System.out.println("Succesfully updated Course");
		} else {

			System.out.println("Course does not exist");
		}
	}

	public static void deleteCourse(String courseID) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			CourseList.remove(course);
			List<Object> courses = new ArrayList<Object>();
			for (Course c : CourseList)
				courses.add((Object) c);
			FileManager.writeObjectToFile("course.dat", courses);
			System.out.println("Course Has been deleted");
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static void addIndexToCourse(String courseID, Index index) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			course.addIndex(index);
			System.out.println("Index " + index.getID() + " has been added to course " + courseID);
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static void deleteIndexFromCourse(String courseID, Index index) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			course.deleteIndex(index.getID());
			System.out.println("Index " + index.getID() + " has been deleted from the course " + courseID);
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static Course findCourse(String CourseID) {
		// finds Course with specific ID in CourseList
		Course reqCourse = null;
		for (Course course : CourseList) {
			if (course.getID().equals(CourseID)) {
				reqCourse = course;
				break;
			}
		}
		return reqCourse;
	}

	public static boolean checkIfCourseExists(String CourseID) {
		return CourseList.stream().anyMatch(Course -> CourseID.equals(Course.getID()));
	}

	public int getCourseVacancy(String CourseCode) {
		int result = -1;
		for (Course course : getCourseList())
			if (course.getID().equals(CourseCode)) {
				result = 0;
				for (Index i : course.getIndexList())
					result += i.getTotalVacancies() - i.getNumStudentsEnrolled();
				break;
			}
		return result;
	}

}