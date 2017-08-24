package com.forum.units;

public class Reply extends TimeStamp {
	private String message;
	private User user;
	private Question question;
	private long id;
	private static Long lastEntry = 0L;
	
	public Long getId() {
		return id;
	}
	
	public void setId() {
		lastEntry = lastEntry + 1L;
		this.id = lastEntry;
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
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
}
