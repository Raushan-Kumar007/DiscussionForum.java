package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.User;
import com.forum.util.Utility;

public class ReplyService {
	public static ArrayList<Reply> replies = new ArrayList<>();
	
	public static void addReply(String message, Question question, User user) {
		if (Utility.isNotNullAndEmpty(message) && question != null && user != null) {
			if (!containsReply(question, message)) {
				Reply reply = new Reply();
				reply.setMessage(message);
				reply.setQuestion(question);
				reply.setUser(user);
				reply.setId();
				reply.setCreated();
				replies.add(reply);
			} else {
				System.out.println("This reply is already present for this question");
			}
		} else {
			System.out.println("Any specified field can't be empty");
		}
	}
	
	private static boolean containsReply(Question question, String message) {
		for (Reply reply : replies) {
			if (reply != null && (reply.getQuestion() == question) && reply.getMessage().equals(message)) {
				return true;
			}
		}
		return false;
	}
	
	public static Reply getReply(long id) {
		for (Reply reply : replies) {
			if (reply != null && (reply.getId() == id)) {
				return reply;
			}
		}
		return null;
	}
	
	public static ArrayList<Reply> getReplies(Question question) {
		ArrayList<Reply> repliesToQuestion = new ArrayList<>();
		for (Reply reply : replies) {
			if (reply != null && (reply.getQuestion() == question)) {
				repliesToQuestion.add(reply);
			}
		}
		return repliesToQuestion;
	}
	
	public static void deleteReply(Reply reply) {
		replies.remove(reply);
	}
}
