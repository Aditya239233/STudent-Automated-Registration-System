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
	
	/**
     * Parametrized Constructor for User
     * @param Name - Determines the Name of the Admin
     * @param Password - Determines the hashed password of the Admin
     * @param Email - Determines the Email ID of the Admin
     * @param dob - Determines the Date of Birth of the Admin
     */
	public User(String Name, String Password, String Email, Calendar dob) {
		this.Name = Name;
		this.Password = PasswordManager.hashPassword(Password);
		this.Email = Email;
		this.dob = dob;
	}
	
	/**
	 * Getter and Setter functions
	 */
	public void setPassword(String Password) { this.Password = PasswordManager.hashPassword(Password); }

	public String getPassword() { return this.Password; }

	public Calendar getDob() { return this.dob; }

	public void setName(String Name) { this.Name = Name; }

	public String getName() { return this.Name; }

	public String getEmail() { return this.Email; }

	public void setEmail(String email) { this.Email = email; }
}
