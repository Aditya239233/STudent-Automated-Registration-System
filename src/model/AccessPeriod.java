package model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

public class AccessPeriod implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Date> accessPeriod = new ArrayList<Date>(2);

	public void setAccessPeriod(Date start, Date end) {
		if (accessPeriod.size() == 0) {
			accessPeriod.add(start);
			accessPeriod.add(end);
			return;
		}
		accessPeriod.set(0, start);
		accessPeriod.set(1, end);

	}

	public List<Date> getAccessPeriod() {
		return accessPeriod;
	}
}
