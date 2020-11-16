package model;

import java.util.*;
import java.io.Serializable;

public class Admin extends User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Admin(String Name, String Password, String Email, Calendar dob) {
		super(Name, Password, Email, dob);
	}

}
