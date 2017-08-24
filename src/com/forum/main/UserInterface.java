package com.forum.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.forum.units.User;
import com.forum.units.UserRole;

import discusion.forum.activiy.UserActivity;
import discussion.forum.units.service.UserService;

public class UserInterface {
	public static User user;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static UserActivity userActivity;
	
	public static void main(String args[]) throws IOException {
		UserService.createUser("admin", "admin", "admin", UserRole.ADMIN);
		userActivity = new UserActivity();
		while (true) {
			userActivity.loginActivity();
			if (user == null)
				continue;
			System.out.println("Welcome " + user.getUsername());
			menu();
		}
	}
	
	public static void menu() throws NumberFormatException, IOException {
		while (true) {
			int menuIndex = 0;
			if (user.getUserRole() == UserRole.ADMIN) {
				System.out.println(++menuIndex + " Create new user");
			}
			System.out.println(++menuIndex + " Ask a question");
			System.out.println(++menuIndex + " See all questions");
			System.out.println(++menuIndex + " Log Out");
			System.out.println("\n Enter your choice");
			if (!classifyMenuChoice(Integer.parseInt(br.readLine())))
				break;
			
		}
	}
	
	public static boolean classifyMenuChoice(int choice) throws IOException {
		if (UserRole.ADMIN != UserInterface.user.getUserRole()) {
			choice++;
		}
		while (true) {
			switch (choice) {
				case 1:
					userActivity.createNewUser();
					return true;
				case 2:
					userActivity.postNewQuestion();
					return true;
				case 3:
					userActivity.seeAllQuestions();
					return true;
				case 4:
					return false;
				default:
					System.out.println("Wrong choice. Try again");
			}
		}
	}
	
	public static UserRole roleFromMenu() throws NumberFormatException, IOException {
		int menuIndex = 0;
		System.out.println(++menuIndex + UserRole.ADMIN.toString());
		System.out.println(++menuIndex + UserRole.MODERATOR.toString());
		System.out.println(++menuIndex + UserRole.USER.toString());
		while (true) {
			System.out.println("\n Enter your choice");
			int choice = Integer.parseInt(br.readLine());
			switch (choice) {
				case 1:
					return UserRole.ADMIN;
				case 2:
					return UserRole.MODERATOR;
				case 3:
					return UserRole.USER;
				default:
					System.out.println("Wrong choice. Try again");
			}
		}
	}
	
	public static void questionMenu() throws NumberFormatException, IOException {
		while (true) {
			int menuIndex = 0;
			System.out.println(++menuIndex + " Upvote a question");
			System.out.println(++menuIndex + " Reply to a question");
			System.out.println(++menuIndex + " See replies for a question");
			System.out.println(++menuIndex + " Delete a question");
			System.out.println(++menuIndex + " Return to main menu");
			System.out.println("\n Enter your choice");
			if (!processQuestionChoice(Integer.parseInt(br.readLine()))) {
				break;
			}
		}
	}
	
	public static boolean processQuestionChoice(int choice) throws NumberFormatException, IOException {
		while (true) {
			switch (choice) {
				case 1:
					userActivity.upvoteQuestion();
					return true;
				case 2:
					userActivity.replyToQuestion();
					return true;
				case 3:
					userActivity.seeAllReplies();
					return true;
				case 4:
					userActivity.deleteQuestion();
					return true;
				case 5:
					return false;
				default:
					System.out.println("Wrong choice. Try again");
			}
		}
	}
	
	public static void replyMenu() throws NumberFormatException, IOException {
		while (true) {
			int menuIndex = 0;
			System.out.println(++menuIndex + " Upvote a reply");
			System.out.println(++menuIndex + " Delete a reply");
			System.out.println(++menuIndex + " Return to question menu");
			System.out.println("\n Enter your choice");
			if (!processReplyChoice(Integer.parseInt(br.readLine()))) {
				break;
			}
		}
	}
	
	public static boolean processReplyChoice(int choice) throws NumberFormatException, IOException {
		while (true) {
			switch (choice) {
				case 1:
					userActivity.upvoteReply();
					return true;
				case 2:
					userActivity.deleteReply();
					return true;
				case 3:
					return false;
				default:
					System.out.println("Wrong choice. Try again");
			}
		}
	}
}
