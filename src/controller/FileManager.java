package controller;

import model.Admin;
import model.Course;
import model.Student;
import java.io.*;

import java.util.*;

public class FileManager {

	/**
	 * This function is used to get the FilePath based on Object type
	 * 
	 * @param object - is the Object that is to be stored
	 * @return
	 */
	public static String getFilePath(Object object) {
		if (object instanceof Student)
			return "student.dat";
		else if (object instanceof Course)
			return "course.dat";
		else if (object instanceof Admin)
			return "admin.dat";
		else if (object instanceof Calendar)
			return "accessPeriod.dat";
		return "Error";
	}

	/**
	 * This function is used to read the object from a file
	 * 
	 * @param filename - Name of the file that is to be read
	 * @return
	 */
	public static List<Object> readObjectFromFile(String filename) {
		List<Object> objects = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/" + filename);
			in = new ObjectInputStream(fis);
			objects = (ArrayList) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return objects;
	}

	/**
	 * This function is used to write an Object to file
	 * 
	 * @param filename - Name of the file that is to be written
	 * @param object   - <Object> that is to be stored
	 */
	public static void writeObjectToFile(String filename, List<Object> object) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("data/" + filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(object);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This function is used to append an Object to a file
	 * 
	 * @param object - <Object> that is to be added
	 */
	public static void addObjectToFile(Object object) {
		try {
			String filename = getFilePath(object);
			if (filename == "Error") {
				System.out.println("File for this Object type does not exist.");
				return;
			}
			List<Object> objects = new ArrayList<>();
			objects = readObjectFromFile(filename);
			objects.add(object);
			writeObjectToFile(filename, objects);
			System.out.println("Record Succesfully added!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is used to write an Course Object to file
	 * 
	 * @param filename - Name of the file that is to be written
	 * @param object   - <Course> that is to be stored
	 */
	public static void writeCourseToFile(String filename, List<Course> object) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("data/" + filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(object);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This function is used to read the Course Object from a file
	 * 
	 * @param filename - Name of the file that is to be read
	 * @return
	 */
	public static List<Course> readCourseFromFile(String filename) {
		List<Course> objects = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("data/" + filename);
			in = new ObjectInputStream(fis);
			objects = (ArrayList) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return objects;
	}
}