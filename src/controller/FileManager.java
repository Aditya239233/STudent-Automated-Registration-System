package controller;

import model.Admin;
import model.Course;
import model.Student;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class FileManager {
	
	public String checkObjectType(Object object) {
		if (object instanceof Student)
			return "data/student.dat";
		else if (object instanceof Course)
			return "data/course.dat";
		else if (object instanceof Admin)
				return "data/admin.dat";
		return "Error";
	}
	
	public List<Object> readObjectFromFile(String filename) {
		List<Object> objects = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
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
	
	public void writeObjectToFile(String filename, List<Object> object) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(object);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void addObjectToFile(Object object) {
		try {
			String filename = checkObjectType(object);
			List<Object> objects = new ArrayList<>();
			objects = readObjectFromFile(filename);
			objects.add(object);
			writeObjectToFile(filename, objects);
			System.out.println("Record Succesfully added!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}