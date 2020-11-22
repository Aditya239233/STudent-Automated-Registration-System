package model;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private String Name;
	private String ID;
	private String faculty;
	private boolean hasTutorial;
	private boolean hasLab;
	private int au;
	private List<Index> indexList;
	private List<Session> lectures;
	
	public Course(String ID, String Name, String faculty, int au, List<Session> lectures) {
		this.ID = ID;
		this.Name = Name;
		this.faculty = faculty;
		this.au = au;

		this.indexList = null;
		this.lectures = lectures;
	}
	
	public Course(String ID, String Name, String faculty, int au, List<Index> indexList, List<Session> lectures) {
		this.ID = ID;
		this.Name = Name;
		this.faculty = faculty;
		this.au = au;

		this.indexList = indexList;
		this.lectures = lectures;
	}

	public List<Index> getIndexList(){
		return this.indexList;
	}

	public void printIndexList() {
		if (this.indexList == null) {
			System.out.println("There are currently no index found in this course.");
		} else {
			for (Index index : indexList) {
				index.printIndexDetails();
			}
		}
	}
	
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
	
	public void addLecture(int day, LocalTime startTime, LocalTime endTime, String location, String teacher) {
		int ID = this.lectures.size();
		Session lecture = new Session(ID, day, startTime, endTime, location, teacher);
		if (this.lectures == null) {
			this.lectures = Arrays.asList(lecture);
		} else {
			this.lectures.add(lecture);
		}
	}
	
	public void deleteLecture(int index) {
		if (index < this.lectures.size() && index >= 0) {
			this.lectures.remove(index);
		} else {
			System.out.println("Lecture index does not exist");
		}
	}
	
	public void addIndex(Index index) {
		if (this.indexList == null) {
			this.indexList = Arrays.asList(index);
		} else {
			this.indexList.add(index);
		}
	}
	
	public void deleteIndex(String indexID) {
		Index currentIndex;
		for(int i=0;i<indexList.size();i++) {
			currentIndex = indexList.get(i);
			if (currentIndex.getID() == indexID) {
				this.indexList.remove(i);
				System.out.println("Index " + indexID + " has been removed");
			}
		}
	}
}
