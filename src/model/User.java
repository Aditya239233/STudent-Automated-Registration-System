package model;

import java.util.*;
import controller.PasswordManager;
import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Password;
	private String Email;
	private Calendar dob;
	PasswordManager pm = new PasswordManager();

	public User() {
		this.Name = "undefined";
		this.Password = "undefined";
		this.Email = "undefined";
		this.dob = Calendar.getInstance();
	}

	public User(String Name, String Password, String Email, Calendar dob) {
		this.Name = Name;
		this.Password = pm.hashPassword(Password);
		this.Email = Email;
		this.dob = dob;
	}

	public void setPassword(String Password) {
		this.Password = pm.hashPassword(Password);
	}

	public String getPassword() {
		return this.Password;
	}

	public Calendar getDob() {
		return this.dob;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getName() {
		return this.Name;
	}

	public String getEmail() {
		return this.Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}
}
