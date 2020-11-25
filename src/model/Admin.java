package model;

import java.util.*;
import java.io.Serializable;

public class Admin extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Parametrized Constructor for Admin
	 * 
	 * @param Name     - Determines the Name of the Admin
	 * @param Password - Determines the hashed password of the Admin
	 * @param Email    - Determines the Email ID of the Admin
	 * @param dob      - Determines the Date of Birth of the Admin
	 */
	public Admin(String Name, String Password, String Email, Calendar dob) {
		super(Name, Password, Email, dob);
	}

}
