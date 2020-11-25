package controller;

import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.Index;
import model.Student;
import java.io.Serializable;

public class StudentCourseManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * This function is used to register a student to a course
	 * @param student - <Student> student the Course is added to
	 * @param CourseCode - refers to the COurse Code
	 * @param IndexCode - refers to the Index Code
	 * @return
	 */
	public static int registerCourse(Student student, String CourseCode, String IndexCode) {
		int result = -4;
		List<Course> courseList = new ArrayList<Course>();
		List<Object> objectList = FileManager.readObjectFromFile("course.dat");
		for (Object o : objectList) {
			courseList.add((Course) o);
		}
		for (int i = 0; i < courseList.size(); i++) {
			Course c = courseList.get(i);
			if (c.getID().equals(CourseCode)) {
				result = -2;
				for (int j = 0; j < c.getIndexList().size(); j++) {
					Index index = c.getIndexList().get(j);
					if (index.getID().equals(IndexCode)) {
						result = student.addCourse(index);
						for (int idx = 0; idx < c.getIndexList().size(); idx++) {
							Index id = c.getIndexList().get(idx);
							if (id.getID().equals(index.getID())) {
								c.getIndexList().set(idx, index);
								courseList.set(i, c);
								break;
							}
						}
						FileManager.writeCourseToFile("course.dat", courseList);
						break;
					}
				}
			}
			if (result != -4)
				break;
		}

		return result;
	}
	
	/**
	 * This function is used to swop a student's index in a course
	 * @param student - <Student> student who swaps his/her index
	 * @param CourseCode - refers to the COurse Code
	 * @param IndexCode - refers to the Index Code
	 * @return
	 */
	public static int swopIndex(Student student, String CourseCode, String IndexCode) {
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
		if (course == null)
			return -1;
		for (Index i : course.getIndexList()) {
			if (i.getID().equals(IndexCode)) {
				if (i.getNumStudentsEnrolled() == i.getTotalVacancies())
					return -3;
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
	
	/**
	 * This function is used by a student to swap index of a course with his/her peer
	 * @param student - <Student> student who's index is to be swapped with his/her peer
	 * @param CourseCode - refers to the COurse Code
	 * @param MatricNumber - refers to the Matriculation Number of peer
	 * @return
	 */
	public static int swopIndexWithPeer(Student student, String CourseCode, String MatricNumber) {
		List<Index> indexes = student.getIndexes();

		int result = -1;
		String s1_index = "";
		for (Index i : indexes) {
			if (i.getCourse().getID().equals(CourseCode)) {
				result = 1;
				s1_index = i.getID();
				break;
			}
		}
		if (result == -1)
			return result;
		result = -2;
		Student s2 = null;
		List<Object> students = FileManager.readObjectFromFile("student.dat");
		Student s;
		for (Object o : students) {
			s = (Student) o;
			if (s.getMatricNo().equals(MatricNumber)) {
				result = 1;
				s2 = s;
				break;
			}
		}
		if (result == -2)
			return result;
		result = -3;
		for (Index i : s2.getIndexes())
			if (i.getCourse().getID().equals(CourseCode)) {
				result = 0;
				if (student.swapIndexWithPeer(s1_index, s2, i.getID()))
					result = 1;
				break;
			}
		return result;
	}
	
	/**
	 * This function is used to write student object to the database
	 * @param student - <Student> student who's details are written to file
	 */
	public static void writeStudentToFile(Student student) {
		List<Object> objectList = FileManager.readObjectFromFile("student.dat");
		List<Object> students = new ArrayList<Object>();
		for (int i = 0; i < objectList.size(); i++) {
			Student s = (Student) objectList.get(i);
			if (s.getMatricNo().equals(student.getMatricNo()))
				s = student;
			students.add(s);
		}
		FileManager.writeObjectToFile("student.dat", students);

	}
	
	/**
	 * This function is used to retrieve all students registered in a course
	 * @param CourseCode - refers to the CourseCode
	 * @return
	 */
	public static List<Student> getStudentsInCourse(String CourseCode) {
		List<Student> studentsEnrolled = new ArrayList<Student>();

		List<Object> objectList = FileManager.readObjectFromFile("student.dat");
		List<Student> students = new ArrayList<Student>();
		for (Object o : objectList)
			students.add((Student) o);
		for (Student student : students) {
			if (student.getIndexes() == null)
				continue;
			for (Index index : student.getIndexes()) {
				if (index.getCourse().getID().equals(CourseCode)) {
					studentsEnrolled.add(student);
				}
			}
		}

		return studentsEnrolled;
	}
	
	/**
	 * This function is used to get all students registered in an Index
	 * @param IndexCode - refers to the Index Code
	 * @return
	 */
	public static List<Student> getStudentsInIndex(String IndexCode) {
		List<Student> studentsEnrolled = new ArrayList<Student>();

		List<Object> objectList = FileManager.readObjectFromFile("student.dat");
		List<Student> students = new ArrayList<Student>();
		for (Object o : objectList)
			students.add((Student) o);
		for (Student student : students) {
			if (student.getIndexes() == null)
				continue;
			for (Index index : student.getIndexes()) {
				if (index.getID().equals(IndexCode)) {
					studentsEnrolled.add(student);
				}
			}
		}

		return studentsEnrolled;
	}

}
