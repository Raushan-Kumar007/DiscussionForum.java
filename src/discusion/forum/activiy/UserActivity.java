package discusion.forum.activiy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.forum.main.UserInterface;
import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.User;
import com.forum.units.UserRole;

import discussion.forum.units.service.QuestionService;
import discussion.forum.units.service.ReplyService;
import discussion.forum.units.service.UpvoteService;
import discussion.forum.units.service.UserService;

public class UserActivity {
	
	public void loginActivity() throws IOException {
		System.out.println("Welcome to discussion forum login");
		System.out.println("Enter your username : ");
		String username = UserInterface.br.readLine();
		System.out.println("Enter your password : ");
		String password = UserInterface.br.readLine();
		User user = UserService.getUser(username, password);
		if (user != null) {
			UserInterface.user = user;
		} else {
			System.out.println("You do not have the account. Request admin to get account in discussion forum");
		}
	}
	
	public void createNewUser() throws IOException {
		System.out.println("Enter username : ");
		String username = UserInterface.br.readLine();
		System.out.println("Enter password : ");
		String password = UserInterface.br.readLine();
		System.out.println("Enter email : ");
		String email = UserInterface.br.readLine();
		System.out.println("What role : ");
		UserRole role = UserInterface.roleFromMenu();
		UserService.createUser(username, password, email, role);
	}
	
	public void postNewQuestion() throws IOException {
		System.out.println("Enter question title : ");
		String title = UserInterface.br.readLine();
		System.out.println("Enter question : ");
		String message = UserInterface.br.readLine();
		QuestionService.createQuestion(title, message, UserInterface.user);
	}
	
	public void seeAllQuestions() throws NumberFormatException, IOException {
		ArrayList<Question> questions = QuestionService.questions;
		if (questions.size() == 0) {
			System.out.println("No question posted yet");
		} else {
			sort(questions);
			for (Question question : questions) {
				System.out.println(question.getId() + ". Question Title - " + question.getTitle());
				System.out.println("Question - " + question.getMessage());
				System.out.println("Upvote - " + question.getUpVoteCount());
			}
			UserInterface.questionMenu();
		}
	}
	
	public void sort(ArrayList<Question> questions) {
		Collections.sort(questions, new Comparator<Question>() {
			public int compare(Question q1, Question q2) {
				if (q1.getUpVoteCount() == q2.getUpVoteCount())
					return 0;
				
				return q1.getUpVoteCount() < q1.getUpVoteCount() ? 1 : -1;
			}
		});
	}
	
	public void upvoteQuestion() throws NumberFormatException, IOException {
		System.out.println("Enter question number you want to upvote : ");
		UpvoteService.addUpvote(getQuestion(), UserInterface.user);
	}
	
	public void replyToQuestion() throws IOException {
		System.out.println("Enter question number you want to reply to : ");
		Question question = getQuestion();
		System.out.println("Post your reply");
		ReplyService.addReply(UserInterface.br.readLine(), question, UserInterface.user);
	}
	
	public void deleteQuestion() throws NumberFormatException, IOException {
		System.out.println("Enter question number you want to delete : ");
		Question question = getQuestion();
		if (UserInterface.user.getUserRole() == UserRole.ADMIN) {
			QuestionService.deleteQuestion(question);
		} else if (UserInterface.user.getUserRole() == UserRole.MODERATOR) {
			if (question.getUser().getUserRole() == UserRole.USER) {
				QuestionService.deleteQuestion(question);
			} else if (question.getUser() == UserInterface.user) {
				QuestionService.deleteQuestion(question);
			} else {
				System.out.println("You are not authorised to delete this question");
			}
		} else {
			if (question.getUser() == UserInterface.user) {
				QuestionService.deleteQuestion(question);
			} else {
				System.out.println("You are not authorised to delete this question");
			}
		}
	}
	
	private Question getQuestion() throws NumberFormatException, IOException {
		Question question;
		while (true) {
			question = QuestionService.getQuestion(Long.parseLong(UserInterface.br.readLine()));
			if (question != null)
				break;
			System.out.println("Enter correct question from displayed questions");
		}
		return question;
	}
	
	public void seeAllReplies() throws NumberFormatException, IOException {
		System.out.println("For which question number you want to see replies : ");
		Question question = getQuestion();
		ArrayList<Reply> replies = ReplyService.getReplies(question);
		if (replies.size() == 0) {
			System.out.println("No reply posted yet");
		} else {
			for (Reply reply : replies) {
				System.out.println(reply.getId() + ". Comment - " + reply.getMessage());
				System.out.println("Upvote - " + UpvoteService.upvoteCount(reply));
			}
			UserInterface.replyMenu();
		}
	}
	
	public void upvoteReply() throws NumberFormatException, IOException {
		System.out.println("Enter reply number you want to upvote : ");
		UpvoteService.addUpvote(getReply(), UserInterface.user);
	}
	
	public void deleteReply() throws NumberFormatException, IOException {
		System.out.println("Enter reply number you want to delete : ");
		Reply reply = getReply();
		if (UserInterface.user.getUserRole() == UserRole.ADMIN) {
			ReplyService.deleteReply(reply);
		} else if (UserInterface.user.getUserRole() == UserRole.MODERATOR) {
			if (reply.getUser().getUserRole() == UserRole.USER) {
				ReplyService.deleteReply(reply);
			} else if (reply.getUser() == UserInterface.user) {
				ReplyService.deleteReply(reply);
			} else {
				System.out.println("You are not authorised to delete this reply");
			}
		} else {
			if (reply.getUser() == UserInterface.user) {
				ReplyService.deleteReply(reply);
			} else {
				System.out.println("You are not authorised to delete this reply");
			}
		}
	}
	
	private Reply getReply() throws NumberFormatException, IOException {
		Reply reply;
		while (true) {
			reply = ReplyService.getReply(Long.parseLong(UserInterface.br.readLine()));
			if (reply != null)
				break;
			System.out.println("Enter correct reply from displayed replies");
		}
		return reply;
	}
}
