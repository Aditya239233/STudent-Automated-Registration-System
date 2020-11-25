package controller;

import java.util.List;
import java.util.ArrayList;
import model.Course;
import model.Index;
import model.Session;

public class IndexManager {
	public static List<Index> IndexList = new ArrayList<Index>();
	
	/**
	 * This function is used to initialize the IndexList
	 * @param CourseList - list of all courses 
	 */
	public static void init(List<Course> CourseList) {
		List<Index> currentList;
		for (Course course : CourseList) {
			currentList = course.getIndexList();
			IndexList.addAll(currentList);
		}
	}
	
	/**
	 * This function is used to print all the Index IDs
	 */
	public static void printIndexIDs() {
		int length = IndexList.size();
		for (int i = 0; i < length; i++) {
			System.out.println(i + ") " + IndexList.get(i).getID());
		}
	}
	
	/**
	 * This function is used to add a new index to a course
	 * @param ID - refers to the Index ID
	 * @param course - <Course> course for which the index is added
	 * @param totalVacancies - total vacancies in Index
	 * @param tutorials - <Session> of tuttorial
	 * @param labs - <Session> of lab
	 * @return
	 */
	public static Index addIndex(String ID, Course course, int totalVacancies, List<Session> tutorials,
			List<Session> labs) { // check hasTutorial and hasLab in AdminUI => pass null is has__ = false
		if (IndexList.size() == 0) {
		} else if (checkIfIndexExists(ID)) {
			System.out.println("Index already exists");
			return null;
		}
		Index newIndex;
		if (tutorials != null && labs != null) {
			newIndex = new Index(ID, course, totalVacancies, tutorials, labs);
		} else if (tutorials != null) {
			newIndex = new Index(ID, course, totalVacancies, tutorials);
		} else {
			newIndex = new Index(ID, course, totalVacancies);
		}
		IndexList.add(newIndex);
		System.out.println("Succesfully added Index");
		return newIndex;
	}
	
	/**
	 * This function is used to add a new index to a course
	 * @param indexID - refers to the Index ID
	 * @param course - <Course> course for which the index is added
	 * @param totalVacancies - total vacancies in Index
	 * @param tutorials - <Session> of tuttorial
	 * @param labs - <Session> of lab
	 * @return
	 */
	public static Index updateIndex(String indexID, Course course, int totalVacancies, List<Session> tutorials,
			List<Session> labs) {
		if (checkIfIndexExists(indexID)) {
			Index index = findIndex(indexID);
			int i = IndexList.indexOf(index);
			Index newIndex;
			if (tutorials != null && labs != null) {
				newIndex = new Index(indexID, course, totalVacancies, tutorials, labs);
			} else if (tutorials != null) {
				newIndex = new Index(indexID, course, totalVacancies, tutorials);
			} else {
				newIndex = new Index(indexID, course, totalVacancies);
			}
			IndexList.set(i, newIndex);
			System.out.println("Succesfully updated Index");
			return newIndex;
		} else {
			System.out.println("Index does not exist");
			return null;
		}

	}

	public static void deleteIndex(String indexID) {
		if (checkIfIndexExists(indexID)) {
			Index index = findIndex(indexID);
			IndexList.remove(index);
			System.out.println("Index Has been deleted");
		} else {
			System.out.println("Index Does not Exist");
		}
	}
	
	/**
	 * This function is used to find and return a <Index> index
	 * @param indexID - refers to the Index Code
	 * @return
	 */
	public static Index findIndex(String indexID) {
		// finds Index with specific ID in IndexList
		Index reqIndex = null;
		for (Index index : IndexList) {
			if (index.getID().equals(indexID)) {
				reqIndex = index;
				break;
			}
		}
		return reqIndex;
	}
	
	/**
	 * This function is used to check if an Index exists
	 * @param indexID - refers to the Index Code
	 * @return
	 */
	public static boolean checkIfIndexExists(String indexID) {
		return IndexList.stream().anyMatch(index -> indexID.equals(index.getID()));
	}
	
	/**
	 * Getter function for IndexList
	 * @return
	 */
	public static List<Index> getIndexList() {
		return IndexList;
	}
}