package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.User;
import com.forum.util.Utility;

public class QuestionService {
	public static ArrayList<Question> questions = new ArrayList<>();
	
	public static void createQuestion(String title, String message, User user) {
		if (Utility.isNotNullAndEmpty(title) && Utility.isNotNullAndEmpty(message) && user != null) {
			if ((!containsMessage(message))) {
				Question question = new Question();
				question.setId();
				question.setTitle(title);
				question.setMessage(message);
				question.setUser(user);
				question.setCreated();
				questions.add(question);
			} else {
				System.out.println("Asked question already exists with same body");
			}
		} else {
			System.out.println("Any specified field can't be empty");
		}
	}
	
	private static boolean containsMessage(String message) {
		for (Question question : questions) {
			if (question != null && question.getMessage().equals(message)) {
				return true;
			}
		}
		return false;
	}
	
	public static Question getQuestion(long id) {
		for (Question question : questions) {
			if (question != null && (question.getId() == id)) {
				return question;
			}
		}
		return null;
	}
	
	public static void deleteQuestion(Question question) {
		questions.remove(question);
	}
}
