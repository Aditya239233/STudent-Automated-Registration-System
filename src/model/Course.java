package model;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String ID;
	private String faculty;
	private boolean hasTutorial = false;
	private boolean hasLab = false;
	private int au;
	private List<Index> indexList;
	private List<Session> lectures;

	/**
	 * Parametrized Constructor for Course
	 * 
	 * @param ID          - Determines the Course Code
	 * @param Name        - Determines the Course Name
	 * @param faculty     - Determines the Course Faculty
	 * @param au          - Determines the number of registered AU's
	 * @param lectures    - Determines the List<Session> of lectures
	 * @param hasTutorial - determines whether the course has a tutorial
	 * @param hasLab      - determines whether the course has a lab
	 */
	public Course(String ID, String Name, String faculty, int au, List<Session> lectures, Boolean hasTutorial,
			Boolean hasLab) {
		this.ID = ID;
		this.Name = Name;
		this.faculty = faculty;
		this.au = au;

		this.indexList = new ArrayList<Index>();

		this.lectures = lectures;
		this.hasTutorial = hasTutorial;
		this.hasLab = hasLab;
	}

	/**
	 * Parametrized Constructor for Course
	 * 
	 * @param ID          - Determines the Course Code
	 * @param Name        - Determines the Course Name
	 * @param faculty     - Determines the Course Faculty
	 * @param au          - Determines the number of registered AU's
	 * @param indexList   - Determines the List<Index> of all registered Courses
	 * @param lectures    - Determines the List<Session> of lectures
	 * @param hasTutorial - Determines whether the course has a tutorial
	 * @param hasLab      - Determines whether the course has a lab
	 */
	public Course(String ID, String Name, String faculty, int au, List<Index> indexList, List<Session> lectures,
			Boolean hasTutorial, Boolean hasLab) {
		this.ID = ID;
		this.Name = Name;
		this.faculty = faculty;
		this.au = au;

		this.indexList = indexList;
		this.lectures = lectures;
		this.hasTutorial = hasTutorial;
		this.hasLab = hasLab;
	}

	/**
	 * This function prints the Indexes of a Course
	 */
	public void printIndexList() {
		if (this.indexList == null) {
			System.out.println("There are currently no index found in this course.");
		} else {
			for (Index index : indexList) {
				index.printIndexDetails();
			}
		}
	}

	/**
	 * This function adds a Lecture to a Course
	 * 
	 * @param day       - indicates the day of the lecture
	 * @param startTime - indicates the starting time of lecture
	 * @param endTime   - indicates the ending time of lecture
	 * @param location  - indicates where the lecture is held
	 * @param teacher   - indicates the Name of the Professor
	 */
	public void addLecture(int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		int ID = this.lectures.size();
		Session lecture = new Session(ID, day, startTime, endTime, location, teacher);
		this.lectures.add(lecture);
	}

	/**
	 * This function is used to delete a lecture from a course
	 * 
	 * @param index - indicates the lecture ID
	 */
	public void deleteLecture(int index) {
		if (index < this.lectures.size() && index >= 0) {
			this.lectures.remove(index);
		} else {
			System.out.println("Lecture index does not exist");
		}
	}

	/**
	 * This function is Used to delete an Index from a Course
	 * 
	 * @param indexID - used to remove an Index from a Course
	 */
	public void deleteIndex(String indexID) {
		Index currentIndex;
		for (int i = 0; i < indexList.size(); i++) {
			currentIndex = indexList.get(i);
			if (currentIndex.getID() == indexID) {
				this.indexList.remove(i);
				System.out.println("Index " + indexID + " has been removed");
			}
		}

	}

	/**
	 * Getter, Setter and Adder functions
	 */
	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getFaculty() {
		return this.faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public void setHasTutorial(boolean hasTutorial) {
		this.hasTutorial = hasTutorial;
	}

	public boolean getHasTutorial() {
		return this.hasTutorial;
	}

	public void setHasLab(boolean hasLab) {
		this.hasLab = hasLab;
	}

	public boolean getHasLab() {
		return this.hasLab;
	}

	public void setAu(int au) {
		this.au = au;
	}

	public int getAu() {
		return this.au;
	}

	public List<Session> getLecture() {
		return this.lectures;
	}

	public List<Index> getIndexList() {
		return this.indexList;
	}

	public void addIndex(Index index) {
		this.indexList.add(index);
	}
}
