package view;

import controller.FileManager;
import java.util.ArrayList;
import model.Admin;
import model.Course;
import model.Index;
import model.Session;
import model.Student;

import java.util.Calendar;

public class TestFiles {
	public static void main(String args[]) throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(1999 + 1900, 10, 22);
		Student devansh = new Student("DevanshK", "password123", "devanshk22@gmail.com", c, "U1823660K", "EEE", "EEE");
		Admin chaiyu = new Admin("Chai Yu", "password456", "cy@ntu", c);
		Course oodp = new Course("OODP", "CZ2002", "SCSE", 3, new ArrayList<Index>(), new ArrayList<Session>());
		FileManager fm = new FileManager();
		fm.writeStudentObject(devansh);
		fm.writeAdminObject(chaiyu);
		fm.writeCourseObject(oodp);
		Admin a = fm.readAdminObject(chaiyu.getName());
		Course course = fm.readCourseObject(oodp.getName());
		Student d = fm.readStudentObject(devansh.getName());
		System.out.println(course.getFaculty());
		System.out.println(a.getEmail());
		System.out.println(d.getDegree());

	}

}
