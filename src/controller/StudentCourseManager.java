package controller;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Index;
import model.Student;

public class StudentCourseManager {

	public int registerCourse(Student student, String CourseCode, String IndexCode) {
		int result = -3;
		List<Course> courseList = new ArrayList<Course>();
		List<Object> objectList = FileManager.readObjectFromFile("course.dat");
		for (Object o : objectList) {
			courseList.add((Course) o);
		}
		for (Course c : courseList) {
			if (c.getID().equals(CourseCode)) {
				result = -2;
				for (Index i : c.getIndexList()) {
					if (i.getID().equals(IndexCode)) {
						result = student.addCourse(i);
						break;
					}
				}
			}
			if (result != 3)
				break;
		}
		return result;
	}

	public int swopIndex(Student student, String CourseCode, String IndexCode) {
		int result = -1;
		List<Index> indexes = student.getIndexes();
		Index currIndex = null;
		Course course = null;
		for (Index i : indexes) {
			if (i.getCourse().getID().equals(CourseCode)) {
				course = i.getCourse();
				currIndex = i;
				break;
			}
		}
		for (Index i : course.getIndexList()) {
			if (i.getID().equals(IndexCode)) {
				student.removeCourse(course.getID());
				if (student.addCourse(i) == 1)
					result = 1;
				else {
					student.addCourse(currIndex);
					result = 0;
				}
				break;
			}
		}
		return result;
	}

}
