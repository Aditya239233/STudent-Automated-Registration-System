package controller;

import model.Admin;
import model.Course;
import model.Student;
import java.io.*
;
public class FileManager {
    public void writeStudentObject(Student student) throws Exception{
        String filepath = "src/Data/Students/"+student.getName()+".txt"; 
        File f = new File(filepath);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(student);
        System.out.println("Student records saved");
        oos.close();
    }

    public void writeAdminObject(Admin admin) throws Exception{
        String filepath = "src/Data/Admins/"+admin.getName()+".txt";
        File f = new File(filepath);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(admin);
        System.out.println("Admin records saved");
        oos.close();
    }

    public void writeCourseObject(Course course) throws Exception{
        String filepath = "src/Data/Courses/"+course.getName()+".txt";
        File f = new File(filepath);
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(course);
        System.out.println("Course records saved");
        oos.close();
    }

    public Student readStudentObject(String name) throws Exception{

        String filepath = "src/Data/Students/"+name+".txt";
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Student s = (Student)ois.readObject(); 
        ois.close();
        return s;
    }

    public Admin readAdminObject(String name) throws Exception{

        String filepath = "src/Data/Admins/"+name+".txt";
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Admin a = (Admin)ois.readObject(); 
        ois.close();
        return a;
    }

    public Course readCourseObject(String name) throws Exception{

        String filepath = "src/Data/Courses/"+name+".txt";
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Course obj = (Course)ois.readObject(); 
        ois.close();
        return obj;
    }
}
