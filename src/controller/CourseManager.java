package controller;

import model.Course;
import model.Index;
import model.Session;

import java.util.List;
import java.util.ArrayList;

public class CourseManager {

	private static List<Course> CourseList = new ArrayList<Course>();

	public static void init() {
		try {

			CourseList = FileManager.readCourseFromFile("course.dat");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void setCourseList(ArrayList<Course> cl) {
		CourseList = cl;
	}

	public static List<Course> getCourseList() {
		return CourseList;
	}

	public static void printCourseIDs() {
		int length = CourseList.size();
		for (int i = 0; i < length; i++) {
			System.out.println(i + ") " + CourseList.get(i).getID());
		}
	}

	public static Course addCourse(String courseID, String Name, String faculty, int au, List<Index> indexList,
			List<Session> lectures, Boolean hasTutorial, Boolean hasLab) {
		if (CourseList.size() == 0) {
			Course newCourse;
			if (indexList != null) {
				newCourse = new Course(courseID, Name, faculty, au, indexList, lectures, hasTutorial, hasLab);
			} else {
				newCourse = new Course(courseID, Name, faculty, au, lectures, hasTutorial, hasLab);
			}
			CourseList.add(newCourse);
			List<Object> courses = new ArrayList<Object>();
			courses.add(newCourse);
			FileManager.writeCourseToFile("course.dat", CourseList);
			return newCourse;
		} else if (checkIfCourseExists(courseID)) {
			return null;
		}

		Course newCourse;
		if (indexList != null) {
			newCourse = new Course(courseID, Name, faculty, au, indexList, lectures, hasTutorial, hasLab);
		} else {
			newCourse = new Course(courseID, Name, faculty, au, lectures, hasTutorial, hasLab);
		}
		CourseList.add(newCourse);
		FileManager.addObjectToFile(newCourse);
		return newCourse;
	}

	public static void updateCourse(String courseID, String Name, String faculty, int au, List<Index> indexList,
			List<Session> lectures, Boolean hasTutorial, Boolean hasLab) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			int i = CourseList.indexOf(course);
			Course newCourse;
			if (indexList != null) {
				newCourse = new Course(courseID, Name, faculty, au, indexList, lectures, hasTutorial, hasLab);
			} else {
				newCourse = new Course(courseID, Name, faculty, au, lectures, hasTutorial, hasLab);
			}
			CourseList.set(i, newCourse);
			List<Object> courses = new ArrayList<Object>();
			for (Course c : CourseList)
				courses.add((Object) c);
			FileManager.writeCourseToFile("course.dat", CourseList);
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
			FileManager.writeCourseToFile("course.dat", CourseList);
			System.out.println("Course Has been deleted");
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static void addIndexToCourse(String courseID, Index index) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			course.addIndex(index);
			for (int i = 0; i < CourseList.size(); i++) {
				if (CourseList.get(i).getID().equals(course.getID())) {
					CourseList.set(i, course);
					break;
				}
			}
			List<Object> objects = new ArrayList<Object>();
			for (Course c : CourseList)
				objects.add(c);
			FileManager.writeCourseToFile("course.dat", CourseList);
			System.out.println("Index " + index.getID() + " has been added to course " + courseID);
		} else {
			System.out.println("Course Does not Exist");
		}
	}

	public static void deleteIndexFromCourse(String courseID, Index index) {
		if (checkIfCourseExists(courseID)) {
			Course course = findCourse(courseID);
			course.deleteIndex(index.getID());
			for (int i = 0; i < CourseList.size(); i++) {
				if (CourseList.get(i).getID().equals(course.getID())) {
					CourseList.set(i, course);
					break;
				}
			}
			List<Object> objects = new ArrayList<Object>();
			for (Course c : CourseList)
				objects.add(c);
			FileManager.writeCourseToFile("course.dat", CourseList);
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
		if (CourseList.size() == 0)
			return false;
		return CourseList.stream().anyMatch(Course -> CourseID.equals(Course.getID()));
	}

	public static int getCourseVacancy(String CourseCode) {
		int result = -1;
		List<Course> courseList = new ArrayList<Course>();
		List<Object> objectList = FileManager.readObjectFromFile("course.dat");
		for (Object o : objectList) {
			courseList.add((Course) o);
		}
		for (Course course : courseList)
			if (course.getID().equals(CourseCode)) {
				result = 0;
				for (Index i : course.getIndexList()) {
					result += (i.getTotalVacancies() - i.getNumStudentsEnrolled());
				}
				break;
			}
		return result;
	}
	
	public static int getTotalCourseVacancy(String CourseCode) {
		int result = -1;
		List<Course> courseList = new ArrayList<Course>();
		List<Object> objectList = FileManager.readObjectFromFile("course.dat");
		for (Object o : objectList) {
			courseList.add((Course) o);
		}
		for (Course course : courseList)
			if (course.getID().equals(CourseCode)) {
				result = 0;
				for (Index i : course.getIndexList()) {
					result += i.getTotalVacancies();
				}
				break;
			}
		return result;
	}
	
	public static void printCourseInfo() {
		for (Course course: CourseList)
			System.out.println("Name: "+course.getName()+" Course Code: "+course.getID() + " Number of AUs"+course.getAu()+" School: "+course.getFaculty());
	}

}