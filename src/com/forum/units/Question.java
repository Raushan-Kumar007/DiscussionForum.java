package com.forum.units;

public class Question extends TimeStamp {
	
	private String title;
	private String message;
	private User user;
	private int upVoteCount = 0;
	private long id;
	private static Long lastEntry = 0L;
	
	public Long getId() {
		return id;
	}
	
	public void setId() {
		lastEntry = lastEntry + 1L;
		this.id = lastEntry;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getUpVoteCount() {
		return upVoteCount;
	}
	
	public void increaseUpVoteCount() {
		this.upVoteCount = this.upVoteCount + 1;
	}
	
}
