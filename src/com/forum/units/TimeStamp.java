package com.forum.units;

import java.util.Date;

import com.forum.util.Utility;

public abstract class TimeStamp {
	
	private Date created;
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated() {
		this.created = Utility.getCurrentDate();
	}
}
