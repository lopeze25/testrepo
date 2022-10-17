package week6CodingAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Application {
	static Scanner scanner = new Scanner(System.in);
	static Player p1 = new Player();
	static Player p2 = new Player();
	static Deck main = new Deck();;
	public static void main(String[] args) {
		int choice = 0;
		int finalScore = 100;
		boolean loop = true;
		p1.setName("Player 1");
		p2.setName("Player 2");
		while (loop) { 
			mainMenu();
			choice = getUserChoice();
			if (choice == 1) { 
				p1.setScore(0);
				p1.setScore(0);
				deckInitialization(); 
				main.shuffle();
				System.out.println(main.getCards());
				System.out.println(main.length() + " successfully shuffled");
				distribute();
				System.out.println("----------");
				System.out.println("TYPE 0 to BEGIN Game and first draw. Any number to Exit."); 
				choice = getUserChoice();
				while (choice == 0) {
					compare();
					int ifa = p1.length();
					int ifb = p2.length();
					if (ifa == 1 || ifb == 1) { 
						System.out.println("Game ended. Opponent out of cards.");	
						if (p1.getScore() > p2.getScore()) { 
							endGame(p1.getName());
						} else if (p2.getScore() > p1.getScore()) { 
							endGame(p2.getName());
						}
						choice = 1;
					} else if (p1.getScore() == finalScore || p2.getScore() == finalScore)  { 
						System.out.println("Game ended. Final score reached.");	
						if (p1.getScore() > p2.getScore()) { 
							endGame(p1.getName());
						} else if (p2.getScore() > p1.getScore()) { 
							endGame(p2.getName());
						}
						choice = 1;
					} else {

					System.out.println("Currently " + p1.getName() + " has " + p1.length() + " cards.");
					System.out.println("Currently " + p2.getName() + " has " + p2.length() + " cards.");
					System.out.println("TYPE 0 to CONTINUE Game and 1 to describe players. Any number to Exit."); 
					// Remove choice to end game
					choice = getUserChoice();
					if (choice ==1) {
						System.out.println("----------");
						p1.describe(1);
						p2.describe(2);
						System.out.println("----------");
						System.out.println("TYPE 0 to RETURN to game. Any number to exit");
						choice = getUserChoice(); 
						}
					}
				System.out.println("Thanks for Playing!");
				}
			} else if (choice == 2) { 
				System.out.println("Rules");
				System.out.println("1. After the deck is shuffled each player gets 26 cards and then flips the first one to see which is higher. Their personal score is incremented.");
				System.out.println("2. In the case of a draw, no player gets points and the cards return too the back of their card. The players flip again to find a winner.");
				System.out.println("3. The war continues under one player wins all the cards or reaches score " + finalScore);
				System.out.println("4. You can change customize player names or the score you play up to in settings.");
			
			} else if (choice == 3) { 
				System.out.println("Change Player Name (1) or Change Final Score (2)");	
				choice = getUserChoice();
				if (choice == 1) {
					System.out.println("Please input name for Player 1:");
					p1.setName(getUserWord());
					System.out.println("Please input name for Player 2:");
					p2.setName(getUserWord());
				}
	
				else if (choice == 2) {
					System.out.println("Please input score: ");
					finalScore = getUserChoice();
				}
			}
	
			else if (choice == 4) { 
				loop = false;
				System.out.println("Exiting Application");
			}
		}	

	}

	private static void endGame(String victor) {
		System.out.println(victor + " WON!");	
		System.out.println(p1.getName() + " ended with " + p1.getScore());
		System.out.println(p2.getName() + " ended with " + p2.getScore());
	}

	public static int getUserChoice() {
		System.out.print("Please select an Option: ");
		return scanner.nextInt();
	}
	
	public static String getUserWord() {
		System.out.print("Input word: ");
		return scanner.next();
	}
	
	public static void mainMenu() {
		System.out.println("----------");
		System.out.println("WELCOME TO WAR");
		System.out.println("Play (1)"); 
		System.out.println("How to Play (2)"); 
		System.out.println("Settings (3)"); 
		System.out.println("Exit (4)"); 
		System.out.println("---------");
	}
	
	public static void deckInitialization()  {
		System.out.println("Creating Deck:");
		Card card = new Card();
		for (int i=2; i<=14; i++) {
			card.setValue(i);
			card.setName("Spades");
			 main.addCard(card.describeCard()); 
				System.out.println(main.getCards());
				System.out.println(main.length() + " cards initialized");}
		for (int i=2; i<=14; i++) {
			card.setValue(i);
			card.setName("Hearts");
			 main.addCard(card.describeCard()); 
			 	System.out.println(main.getCards());
				System.out.println(main.length() + " cards initialized");}
		for (int i=2; i<=14; i++) {
			card.setValue(i);
			card.setName("Diamonds");
			 main.addCard(card.describeCard());  
				System.out.println(main.getCards());
				System.out.println(main.length() + " cards initialized");}
		for (int i=2; i<=14; i++) {
			card.setValue(i);
			card.setName("Clubs");
			 main.addCard(card.describeCard()); 
				System.out.println(main.getCards());
				System.out.println(main.length() + " cards initialized");}
		System.out.println("Deck succesfully created.");
		
	}
	

	public static void distribute() {
			p1.setHand(main.getFirstHalf());
			System.out.println(p1.getHand());
			System.out.println(p1.length() + " cards distributed");
			p1.shuffle();
			p2.setHand(main.getSecondHalf());
			System.out.println(p2.getHand());
			System.out.println(p2.length() + " cards distributed");
			p2.shuffle();		
		}
	
	public static void compare() { 
		System.out.println("----------");
		char p1Value = p1.getValue(p1.length() - 1).charAt(0);
		char p2Value = p2.getValue(p2.length() - 1).charAt(0);
		int a = 0;
		int b = 0;
		System.out.println(p1.getName() + " drew a " + p1.getValue(p1.length() - 1) + "!");
		try { a = Integer.parseInt(String.valueOf(p1Value));
			if (a == 1) {
				a = 10;
			}
		 	System.out.println("This is a " + a); 
		 
		} catch (Exception e) { 
			if (p1Value ==  'A' ) { 
				System.out.println("This is a Ace"); 
				a = 14;
			} else if  (p1Value == 'K') {
				System.out.println("This is a King"); 
				a = 13;
			} else if (p1Value == 'Q') { 
				System.out.println("This is a Queen"); 
				a = 12;	
			} else if (p1Value == 'J') {
				System.out.println("This is a Jack"); 
				a = 11;
			}	
		}
		System.out.println(p2.getName() + " drew a " + p2.getValue(p2.length() - 1) + "!");
		try {  b = Integer.parseInt(String.valueOf(p2Value));
				if (b == 1) {
						b = 10;
					}
				System.out.println("This is a " + b);	
			} catch (Exception z) { 
				if (p2Value ==  'A' ) { 
					System.out.println("This is a Ace"); 
					b = 14;
				} else if  (p2Value == 'K') {
					System.out.println("This is a King"); 
					b = 13;
				} else if (p2Value == 'Q') {
					System.out.println("This is a Queen"); 
					b = 12; 
				} else if (p2Value == 'J') {
					System.out.println("This is a Jack"); 
					b = 11;
				}
			}
		
		if (a > b) { 
			p1.addCard(p2.getValue(p2.length()- 1)); 
			p2.removelast();
			System.out.println(p1.getName() +  " WINS round.");
			p1.flip();
			p1.flip();
			//p1.addCard(cardToAdd);
			p1.incrementScore();
		}
		else if (b > a) { 
			p2.addCard(p1.getValue(p1.length()- 1)); 
			p1.removelast();
			System.out.println(p2.getName() + " WINS round.");
			p2.flip();
			p2.flip();
			//p2.addCard(cardToAdd);
			p2.incrementScore();
		}
		else { 
			System.out.println("Draw round.");
			p1.flip();
			p2.flip();
			compare();
		}
		System.out.println("----------");
	}
}
			

