package view;

import controller.FileManager;
import model.Admin;

import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class TestFiles {
	public static void main(String args[]) throws Exception {
		Calendar c = Calendar.getInstance();
		c.set(1999 , 10, 22);
		Admin SuperUser = new Admin("SuperUser", "Qwerty1!", "SuperUser@gmail.com", c);
		Admin admin = new Admin("Admin", "P@ssword", "Admin@gmail.com", c);
		List<Object> admins = new ArrayList<Object>();
		admins.add(SuperUser);
		admins.add(admin);
		FileManager.writeObjectToFile("admin.dat", admins);
		admins.clear();
		admins = FileManager.readObjectFromFile("admin.dat");
		for (Object o: admins) {
			Admin a = (Admin)o;
			System.out.println(a.getName());
		}
	}

}
