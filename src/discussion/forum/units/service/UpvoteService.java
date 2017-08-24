package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.Upvote;
import com.forum.units.User;

public class UpvoteService {
	public static ArrayList<Upvote> upvotes = new ArrayList<>();
	
	public static void addUpvote(Question question, User user) {
		if (question != null && user != null) {
			if (!containsUpvote(user, question, null)) {
				addUpvote(user, question, null);
				question.increaseUpVoteCount();
			} else {
				System.out.println("You have already upvoted");
			}
		} else {
			System.out.println("Any specified field can't be empty");
		}
	}
	
	public static long upvoteCount(Reply reply) {
		int count = 0;
		for (Upvote upvote : upvotes) {
			if (upvote != null && (upvote.getReply() == reply)) {
				count++;
			}
		}
		return count;
	}
	
	public static void addUpvote(Reply reply, User user) {
		if (reply != null && user != null) {
			if (!containsUpvote(user, null, reply)) {
				addUpvote(user, null, reply);
			} else {
				System.out.println("You have already upvoted");
			}
		} else {
			System.out.println("Any specified field can't be empty");
		}
	}
	
	private static boolean containsUpvote(User user, Question question, Reply reply) {
		for (Upvote upvote : upvotes) {
			if ((upvote != null) && (upvote.getUser() == user) && ((upvote.getQuestion() == question) || (upvote.getReply() == reply))) {
				return true;
			}
		}
		return false;
	}
	
	private static void addUpvote(User user, Question question, Reply reply) {
		Upvote upvote = new Upvote();
		upvote.setQuestion(question);
		upvote.setReply(reply);
		upvote.setUser(user);
		upvote.setId();
		upvote.setCreated();
		upvotes.add(upvote);
	}
}
