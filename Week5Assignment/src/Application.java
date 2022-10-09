import java.util.Scanner;

public class Application {
	static Scanner scanner = new Scanner(System.in);	
	public static void main(String[] args) {
		int choice  = 0;
		Logger logger = new AsteriskLogger();
		Logger logger2 = new SpacedLogger();
		System.out.println("Welcome to Ezekiel's Asterisk and Spaced Application:");
		while (true) { 
			System.out.print("Please choose 1 for the AsteriskLogger or 2 for the SpaceLogger: ");
			choice = getUserChoice();
			if (choice == 1) {
				System.out.print("AsteriskLogger: Please choose 1 Log method or 2 Error method: ");
				choice = getUserChoice();
				if (choice == 1) {
					System.out.print("Please input word: ");
					logger.log(scanner.next());
				}
	
				else if (choice == 2) {
					System.out.print("Please input word: ");
					logger.error(scanner.next());
				}
				
				else {
					System.out.println("Invalid choice. Try again");
				}
				
			} else if (choice == 2) {
				System.out.print("SpacedLogger: Please choose 1 Log method or 2 Error method: ");
				choice = getUserChoice();
				if (choice == 1) {
					System.out.print("Please input word: ");
					logger2.log(scanner.next());
				}
	
				else if (choice == 2) {
					System.out.print("Please input word: ");
					logger2.error(scanner.next());
				}
				else {
					System.out.println("Invalid choice. Try again");
				}		
			}
		}
	}

	public static int getUserChoice() {
		return scanner.nextInt();
	}
	
	public static String getUserWord() {
		return scanner.next();
	}
}
