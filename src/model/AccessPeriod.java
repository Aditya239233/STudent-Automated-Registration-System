package model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class AccessPeriod implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Date> accessPeriod = new ArrayList<Date>(2);
	
	/**
     * This function is used to set the Access Period for the Students to Login to the System
     * @param start - to store the Start Date of the Access Period
     * @param end - to store the End Date of the Access Period
     */
	public void setAccessPeriod(Date start, Date end) {
		if (accessPeriod.size() == 0) {
			accessPeriod.add(start);
			accessPeriod.add(end);
			return;
		}
		accessPeriod.set(0, start);
		accessPeriod.set(1, end);

	}
	
	/**
     * This function is used to get the Access Period for the Students to Login to the System
     * @return accessPeriod - The accessPeriod of Student's Login
     */
	public List<Date> getAccessPeriod() {
		return accessPeriod;
	}
}
