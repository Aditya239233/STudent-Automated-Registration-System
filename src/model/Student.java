package model;

import java.io.Serializable;
import java.util.*;

import controller.CourseManager;
import controller.FileManager;
import controller.SendEmail;
import controller.StudentCourseManager;

public class Student extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String MatricNo;
	private String SchoolID;
	private String Degree;
	private List<Index> indexes = new ArrayList<Index>();
	private NotificationMode nm;
	private int AU;
	private final int maxAU = 22;

	/**
	 * Parametrized Constructor for Student
	 * 
	 * @param Name     - Determines the Name of the Student
	 * @param Password - Determines the hashed password of the Student
	 * @param Email    - Determines the Email ID of the Student
	 * @param dob      - Determines the Date of Birth of the Student
	 * @param MatricNo - Determines the Matriculation Number of Student
	 * @param SchoolID - Determines the SchoolID of the Student
	 * @param Degree   - Determines the Degree of the Student
	 */
	public Student(String Name, String Password, String Email, Calendar dob, String MatricNo, String SchoolID,
			String Degree) {
		super(Name, Password, Email, dob);
		this.MatricNo = MatricNo;
		this.SchoolID = SchoolID;
		this.Degree = Degree;
		this.nm = NotificationMode.EMAIL;
		this.AU = 0;

	}

	/**
	 * This function is by the student to register to a course
	 * 
	 * @param index - indicates the <Index> the student wants to be enrolled in
	 * @return
	 */
	public int addCourse(Index index) {
		if (index.getTotalVacancies() - index.getNumStudentsEnrolled() < 1) {
			for (String matric : index.getWaitList()) {
				if (matric.equals(this.getMatricNo()))
					return -1;
			}
			index.addToWaitList(this.MatricNo);
			return -1;
		}
		if (!checkTimeTableClash(index)) {
			Boolean isThere = false;
			for (Index i : getIndexes())
				if (i.getCourse().getID().equals(index.getCourse().getID()))
					isThere = true;
			if (isThere)
				return -4;
			if (getAU() + index.getCourse().getAu() > maxAU)
				return -5;
			indexes.add(index);
			index.setNumStudentEnrolled(index.getNumStudentsEnrolled() + 1);
			setAU(getAU() + index.getCourse().getAu());
			return 1;
		}

		else {
			return 0;
		}
	}

	/**
	 * This function is by the student to de-register to a course
	 * 
	 * @param ID - indicates the Course Code
	 * @return
	 */
	public boolean removeCourse(String ID) {
		Course course;
		for (Index index : indexes) {
			course = index.getCourse();
			if (course.getID().equals(ID)) {
				indexes.remove(index);
				setAU(getAU() - index.getCourse().getAu());
				index.setNumStudentEnrolled(index.getNumStudentsEnrolled() - 1);
				if (handleWaitList(index) == 1)
					index.setNumStudentEnrolled(index.getNumStudentsEnrolled() + 1);
				return true;
			}
		}
		return false;
	}

	/**
	 * This function is used to handle the waitlist
	 * 
	 * @param index - indicates the <Index> index
	 * @return
	 */
	private int handleWaitList(Index index) {
		int result = 0;
		if (index.getWaitList().size() == 0)
			return result;
		String matricNumber = index.viewLastWaitList();
		List<Object> objects = FileManager.readObjectFromFile("student.dat");
		List<Student> students = new ArrayList<Student>();
		for (Object o : objects)
			students.add((Student) o);
		for (int i = 0; i < students.size(); i++) {
			Student s = students.get(i);
			if (s.getMatricNo().equals(matricNumber))
				if (s.addCourse(index) == 1) {
					SendEmail.sendMessage(s, index);
					index.removeFromWaitList(matricNumber);
					students.set(i, s);
					result = 1;
				}
		}
		List<Object> object = new ArrayList<Object>();
		for (Student s : students)
			object.add((Object) s);
		FileManager.writeObjectToFile("student.dat", object);
		return result;
	}

	/**
	 * This function is used to check for a timetable clash
	 * 
	 * @param index - is the index that is going to be added in the timetable
	 * @return
	 */
	public Boolean checkTimeTableClash(Index index) {
		Boolean isClash = false;
		Course newCourse = index.getCourse();
		for (Index i : indexes) {
			if (isClash)
				break;
			if (index.getID().equals(i.getID()))
				continue;
			Course c = i.getCourse();

			// Lecture Clash
			for (Session lecture : c.getLecture()) {
				for (Session newLecture : newCourse.getLecture()) {
					if (!c.getID().equals(newCourse.getID())) {
						isClash = checkClash(lecture, newLecture);
						if (isClash)
							return isClash;
					}
				}
				if (index.getCourse().getHasTutorial()) {
					for (Session newTutorial : index.getTutorial()) {
						isClash = checkClash(newTutorial, lecture);
						if (isClash)
							return isClash;
					}
				}
				if (index.getCourse().getHasLab()) {
					for (Session newLab : index.getLab()) {
						isClash = checkClash(newLab, lecture);
						if (isClash)
							return isClash;
					}
				}
			}
			// Tutorial Clash
			if (i.getCourse().getHasTutorial()) {
				for (Session tutorial : i.getTutorial()) {
					for (Session newLecture : newCourse.getLecture()) {
						isClash = checkClash(newLecture, tutorial);
						if (isClash)
							return isClash;
					}
					if (index.getCourse().getHasTutorial()) {
						for (Session newTutorial : index.getTutorial()) {
							isClash = checkClash(newTutorial, tutorial);
							if (isClash)
								return isClash;
						}
					}
					if (index.getCourse().getHasLab()) {
						for (Session newLab : index.getLab()) {
							isClash = checkClash(newLab, tutorial);
							if (isClash)
								return isClash;
						}
					}
				}
			}
			// Lab Clash
			if (i.getCourse().getHasLab()) {
				for (Session lab : i.getLab()) {
					for (Session newLecture : newCourse.getLecture()) {
						isClash = checkClash(newLecture, lab);
						if (isClash)
							return isClash;
					}
					if (index.getCourse().getHasTutorial()) {
						for (Session newTutorial : index.getTutorial()) {
							isClash = checkClash(newTutorial, lab);
							if (isClash)
								return isClash;
						}
					}
					if (index.getCourse().getHasLab()) {
						for (Session newLab : index.getLab()) {
							isClash = checkClash(newLab, lab);
							if (isClash)
								return isClash;
						}
					}
				}
			}
		}
		return isClash;
	}

	/**
	 * This function is to check a time clash between 2 <Session> sessions
	 * 
	 * @param s1 - First Session
	 * @param s2 - Second Session
	 * @return
	 */
	public Boolean checkClash(Session s1, Session s2) {
		Boolean isClash = false;
		int val1;
		int val2;
		if (s1.getDay() == s2.getDay()) {
			val1 = s2.getStartTime().compareTo(s1.getStartTime());
			val2 = s2.getEndTime().compareTo(s1.getStartTime());
			if (val1 <= 0 && val2 > 0)
				isClash = true;
			val2 = s1.getStartTime().compareTo(s2.getEndTime());
			if (val1 <= 0 && val2 <= 0)
				isClash = true;
		}
		return isClash;
	}

	/**
	 * This function is used to print all the Courses Registered by the Student
	 */
	public void printCoursesRegistered() {
		if (indexes.size() == 0) {
			System.out.println("No Courses Registered");
			return;
		}
		System.out.println("\n#########################################################################");
		for (Index i : indexes) {
			Course c = i.getCourse();
			System.out.println("Course Code: " + c.getID());
			System.out.println("Course Name: " + c.getName());
			System.out.println("AUs: " + c.getAu());
			System.out.println("Index: " + i.getID());
			System.out.println("\n#########################################################################");
		}
	}

	/**
	 * This function is used to check whether a course is already registered by a
	 * student
	 * 
	 * @param index - referes the to Index Code
	 * @return
	 */
	public boolean checkIfCourseRegistered(String index) {
		for (Index i : this.indexes) {
			if (i.getID() == index) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This function is used to swap Index with a Peer
	 * 
	 * @param s1_index - This is the current Student's Index Code
	 * @param s2       - Refers to the Peer <Student>
	 * @param s2_index - This is the Peer's Index Code
	 * @return
	 */
	public boolean swapIndexWithPeer(String s1_index, Student s2, String s2_index) {
		String course = " ";
		List<Index> s1_indexes = this.getIndexes();
		List<Index> s2_indexes = s2.getIndexes();
		String i1 = " ", i2 = " ";
		boolean isValid1 = false;
		boolean isValid2 = false;

		for (Index i : s2_indexes) {
			if (i.getID() == s2_index) {
				if (this.checkTimeTableClash(i)) {
					System.out.println("Cannot swap index due to Timetable clash. Please select another index.");
					isValid1 = false;
				} else {
					i1 = i.getID();
					course = i.getCourse().getID();
					isValid1 = true;
				}
			}
		}
		for (Index i : s1_indexes) {
			if (i.getID() == s1_index) {
				if (s2.checkTimeTableClash(i)) {
					System.out.println("Cannot swap index due to Timetable clash. Please select another index.");
					isValid2 = false;
				} else {
					i2 = i.getID();
					course = i.getCourse().getID();
					isValid2 = true;
				}
			}
		}

		if (isValid1 && isValid2) {
			this.removeCourse(course);
			s2.removeCourse(course);
			Course c = CourseManager.findCourse(course);
			List<Index> iList = c.getIndexList();
			for (Index i : iList) {
				if (i.getID() == i1) {
					this.addCourse(i);
				} else if (i.getID() == i2) {
					s2.addCourse(i);
				}
			}
			StudentCourseManager.writeStudentToFile(this);
			StudentCourseManager.writeStudentToFile(s2);
			return true;
		} else
			return false;

	}

	/**
	 * Getter and Setter functions
	 */
	public void setNotificationMode(NotificationMode nm) {
		this.nm = nm;
	}

	public NotificationMode getNotificationMode() {
		return this.nm;
	}

	public void setDegree(String Degree) {
		this.Degree = Degree;
	}

	public List<Index> getIndexes() {
		return this.indexes;
	}

	public String getDegree() {
		return this.Degree;
	}

	public void setSchoolID(String SchoolID) {
		this.SchoolID = SchoolID;
	}

	public String getSchoolID() {
		return this.SchoolID;
	}

	public String getMatricNo() {
		return this.MatricNo;
	}

	public int getAU() {
		return AU;
	}

	public void setAU(int aU) {
		AU = aU;
	}

}
