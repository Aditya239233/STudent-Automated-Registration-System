package model;

import java.util.Date;

public class User {
	
	private String Name;
	private String Password;
	private String Email;
	private Date dob;
	
	public User(String Name, String Password, String Email, Date dob) {
		this.Name = Name;
		this.Password = Password;
		this.Email = Email;
		this.dob = dob;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	public String getPassword() {
		return this.Password;
	}
	
	public Date getDob() {
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
}
