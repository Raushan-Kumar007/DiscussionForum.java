package com.forum.units;

public class Upvote extends TimeStamp {
	private Question question;
	private Reply reply;
	private User user;
	private long id;
	private static Long lastEntry = 0L;
	
	public Long getId() {
		return id;
	}
	
	public void setId() {
		lastEntry = lastEntry + 1L;
		this.id = lastEntry;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public Reply getReply() {
		return reply;
	}
	
	public void setReply(Reply reply) {
		this.reply = reply;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
