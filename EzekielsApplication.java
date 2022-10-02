import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class EzekielsApplication {

	static Scanner scanner = new Scanner(System.in);	
	static List<Integer> listOne = new ArrayList<Integer>();	
	static List<String> listTwo = new ArrayList<String>();
	static List<Integer> customInteger = new ArrayList<Integer>();
	static List<Double> customDouble = new ArrayList<Double>();
	static List<Double> customDouble2 = new ArrayList<Double>();
	static boolean isHotOutside = true; 
	static double moneyinPocket = 0;
	static int sum0 = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int choice  = 0;	
		listOne.addAll(Arrays.asList(3, 9, 23, 64, 2, 8, 28, 93));
		listTwo.addAll(Arrays.asList("Sam", "Tommy", "Tim", "Sally", "Buck", "Bob"));
		while (choice !=13) {
			printMenu();
			choice = getUserChoice();
				if (choice == 1) {
						System.out.print("The ages in array one are: ");
						for(int i=0; i<listOne.size(); i++){
							System.out.print(listOne.get(i) + " ");}
							stepOneMenu();
							choice = getUserChoice();
						if (choice == 1) {
							System.out.println("You have " + (listOne.size()) + " ages in your array.");
							System.out.println("The last value in this array minus the first value is: " + (listOne.get(listOne.size() - 1) - listOne.get(0)));
						} else if (choice == 2) {
							addAge();
						} else if (choice == 3) {
							calculateAgeAverage();
						}
				} else if (choice == 2) {
						System.out.print("The names in array two are: ");
						for(int i=0; i<listTwo.size(); i++){
							System.out.print(listTwo.get(i) + " ");}
							stepTwoMenu();
							choice = getUserChoice();
						if (choice == 1) {
							calculateLetterAverage();
						} else if (choice == 2) {
							System.out.print("Concatenation: ");
							for(int i=0; i<listTwo.size(); i++){
								System.out.print(listTwo.get(i) + " ");}
						} else if (choice == 3) {
							System.out.print("Error");
						}
				} else if (choice == 3) {
					System.out.println("Find the last element of any array (if array is valid).");
					System.out.println("Select which array to pick listOne (1) or listTwo (2)");
					System.out.println("[!]The customInteger (3), first customDouble list (4) or second customDouble list (5) MUST be filled before selecting them to avoid crash.");
					choice = getUserChoice();
					if (choice == 1) {
						System.out.print("The last element of this array is: " + listOne.get(listOne.size() - 1));
					} else if (choice == 2) {
						System.out.print("The last element of this array is: " + listTwo.get(listTwo.size() - 1));
					} else if (choice == 3) {
						System.out.print("The last element of this array is: " + customInteger.get(customInteger.size() - 1));
					} else if (choice == 4) {
						System.out.print("The last element of this array is: " +customDouble.get(customDouble.size() - 1 ));
					} else if (choice == 5) {
						System.out.print("The last element of this array is: " +customDouble2.get(customDouble2.size() - 1));
					} else {
						System.out.print("Error");
					}
				}  else if (choice == 4) {
					System.out.println("Find the first element of any array (if array is valid).");
					System.out.println("Select which array to pick listOne (1) or listTwo (2)");
					System.out.println("[!]The customInteger (3), first customDouble list (4) or second customDouble list (5) MUST be filled before selecting them to avoid crash.");
					choice = getUserChoice();
					if (choice == 1) {
						System.out.print("The first element of this array is: " + listOne.get(0));
					} else if (choice == 2) {
						System.out.print("The first element of this array is: " + listTwo.get(0));
					} else if (choice == 3) {
						System.out.print("The first element of this array is: " + customInteger.get(0));
					} else if (choice == 4) {
						System.out.print("The first element of this array is: " + customDouble.get(0));
					} else if (choice == 5) {
						System.out.print("The first element of this array is: " + customDouble2.get(0));
					} else {
						System.out.print("Error");
					}
				} else if (choice == 5) {
					int [] namesLength = new int[listTwo.size()];
					System.out.println("The length of each name in the NameArray is: ");
				    for(int i =0; i < namesLength.length ; i++) {
				    	namesLength[i] = listTwo.get(i).length();
				        System.out.print(namesLength[i] + " ");}
				} else if (choice == 6) {
					double sum = 0;
					int [] namesLength = new int[listTwo.size()];
					System.out.println("The sum of the namesLength array is: ");
				    for(int i =0; i < namesLength.length ; i++) {
				    	namesLength[i] = listTwo.get(i).length();
						sum += listTwo.get(i).length(); } 
				    System.out.print(sum);
				} else if (choice == 7) {
					System.out.println("Input word to concatenate");
					String word = getUserWord();
					System.out.println("Input number of times to concatenate to itself (without space)");
					int input = getUserChoice();
					String concatenate = stringBuilder(word, input);
					 System.out.print(concatenate);
				} else if (choice == 8) {
					System.out.println("Please enter a first name.");
					String firstName = getUserWord();
					StringBuilder fullname = new StringBuilder(firstName);
					System.out.println("Please enter a last name.");
					String lastName = getUserWord();
					fullname.append(" " + lastName);
					System.out.println("The returned full name is: " + fullname.toString());
				} else if (choice == 9) {
					System.out.println("You must first add integers to run program. (Option 3)");
					System.out.println("(1) See added integers, (2) Perform Program  (3) Add Integers to array");					
					choice = getUserChoice();					
					if (choice == 1)  {
						System.out.print("The integers currently in the array are: ");
						for(int i=0; i<customInteger.size(); i++){
							System.out.print(customInteger.get(i) + " ");}
					} else if (choice == 2) {
						sum0method();
						
					
							
					} else if (choice == 3) {
						while (choice != 2) {
						System.out.println("Press 1 to add a integer or 2 to exit");
						choice = getUserChoice();	
							if 	(choice ==1) {								
								addCustomInt();
								System.out.println("Added int");}
						else { 
							System.out.print("Exiting");
						}
					}}
				} else if (choice == 10) {
					System.out.println("You must first add doubles to run program. (Option 3)");
					System.out.println("(1) See added doubles, (2) Perform Program  (3) Add doubles to array");		
					choice = getUserChoice();					
					if (choice == 1)  {
						System.out.print("The doubles currently in the array are: ");
						for(int i=0; i<customDouble.size(); i++){
							System.out.print(customDouble.get(i) + " ");}
					} else if (choice == 2) {
						double sum = 0;
						for(int i=0; i<customDouble.size(); i++){
							sum += customDouble.get(i); }
						System.out.println("The average of the doubles is " + (sum / customDouble.size()));
					} else if (choice == 3) {
						while (choice != 2) {
						System.out.println("Press 1 to add a double or 2 to exit");
						choice = getUserChoice();	
							if 	(choice ==1) {
								addCustomDouble();
								System.out.println("Added double");}
						else { 
							System.out.print("Exiting");
						}
					}
					}
				} else if (choice == 11) {
					System.out.println("[!]This program uses the first customDouble list in Step 10. It must be filled.");
					System.out.println("You must also first add doubles to the second array to run program. (Option 3)");
					System.out.println("(1) See both arrays, (2) Perform Program  (3) Add doubles to second array");	
					choice = getUserChoice();
					if (choice == 1)  {
						System.out.println("The doubles currently in the first custom array are: ");
						for(int i=0; i<customDouble.size(); i++){
							System.out.print(customDouble.get(i) + " ");}
						System.out.println("The doubles currently in the second custom array are: ");
						for(int i=0; i<customDouble2.size(); i++){
							System.out.print(customDouble2.get(i) + " ");}
					} else if (choice == 2) {
						double sum = 0;
						for(int i=0; i<customDouble.size(); i++){
							sum += customDouble.get(i); }
						System.out.println("The average age for the first custom array is: " + (sum / customDouble.size()));
						double sum2 = 0;
						for(int i=0; i<customDouble2.size(); i++){
							sum2 += customDouble2.get(i); }
						System.out.println("The average age for the second custom array is: " + (sum2 / customDouble2.size()));	
						if (sum > sum2) {
							System.out.println("true: The average age of the first array is greater than the second");
						} else {
							System.out.println("false: The average age of the first array is greater than the second");
							System.out.println("It is less than.");
						}
						
					} else if (choice == 3) {
						while (choice != 2) {
						System.out.println("Press 1 to add a double or 2 to exit");
						choice = getUserChoice();	
							if 	(choice ==1) {
								addCustomDouble2();
								System.out.println("Added double");}
						}} else {
							System.out.print("Exiting");
						}
				} else if (choice == 12) {
					System.out.println("Before running program you must set configs for boolean isHotOutside, and double moneyInPocketChoice.");
					System.out.println("(1) Set configs (2) Run program willBuyDrink");
					choice = getUserChoice();
					if (choice == 1) {
						setConfig();
					} else if (choice == 2){
						if(isHotOutside == true ) {
							System.out.println("It is hot outside");
						} else { 
							System.out.println("It is cool outside");
						}
						System.out.println("You have " + moneyinPocket + " dollars in pocket."); 
						if (isHotOutside == true && moneyinPocket > 10.50) {
							System.out.println("true: I will buy you a drink.");
						} else { 
							System.out.println("false: I will buy you a drink."); 
							System.out.println("It is not hot enough or I dont have enough money."); 
						}
					} else {
						System.out.print("Error");
					}
				} else 
					System.out.println("Error. Please choose a valid option.");
				}
		}



	public static void printMenu() {
		System.out.println("");
		System.out.println("");
		System.out.println("-----Ezekiel's Java Application-----");
		System.out.println("Menu: Choose options between 1-12. Each option represents assignment step.");
		System.out.println("1) Run ageArray program");
		System.out.println("2) Run namesArray program");
		System.out.println("3) Access the last element of any array");
		System.out.println("4) Access the first element of any array");
		System.out.println("5) Run program to find the length of each name in the namesArray  ");
		System.out.println("6) Run program to find the sum of all names in the namesArray");
		System.out.println("7) Run concatenation program");
		System.out.println("8) Run fullName program to return a full name"); 
		System.out.println("9) Run program to take an intArray and find if sum is greater than 100"); 
		System.out.println("10) Run program to take a doubleArray and return average of elements");
		System.out.println("11) Run program to take two doubleArrays and find if the firstArray is greater");
		System.out.println("12) Run willBuyDrink program");
		System.out.println("------------------------------------");
		System.out.println("");
}
	public static int getUserChoice() {
		return scanner.nextInt();
	}
	
	public static String getUserWord() {
		return scanner.next();
	}
	public static void stepOneMenu() {
		System.out.println("");
		System.out.println("Please select option 1, 2 or 3. ");
		System.out.println("1) a. Subtract the last value of the array by the first value ");
		System.out.println("2) b. Add a new age to Array");
		System.out.println("3) c. Calculate the average age");
	}
	
	public static void addAge() {
		System.out.println("Enter new age you would like to add: ");
		int number = scanner.nextInt();
		listOne.add(number);
	}

	public static void calculateAgeAverage() {
		double sum = 0;
		for(int i=0; i<listOne.size(); i++){
			sum += listOne.get(i); }
		System.out.println("The average age is: " + (sum / listOne.size()));
	}
	
	public static void stepTwoMenu() {
		System.out.println("");
		System.out.println("Please select  option 1 or 2");
		System.out.println("1) a. Find the average amount of letters per name. ");
		System.out.println("2) b. Concatenate all the names together, separated by spaces");
	}
	
	public static void calculateLetterAverage() {
		double sum = 0;
		for(int i=0; i<listTwo.size(); i++){
			sum += listTwo.get(i).length(); }
		System.out.println("The average amount of letter per name is: " + (sum / listTwo.size()));
	}
	
	public static String stringBuilder(String str, int num)  {
		String result = "";
		for (int i = 0; i < num; i++)  {
			result += str;
		} return result;
 }
	
	public static void namesLength() {
		System.out.print("Length of each name is ");
		for(int i=0; i<listTwo.size(); i++) {
			System.out.println(listTwo.get(i).length()); }
			
	}
	
	public static void addCustomInt() {
		System.out.println("Enter new integer you would like to add: ");
		int number = scanner.nextInt();
		customInteger.add(number);
	}
	
	public static void addCustomDouble() {
		System.out.println("Enter new double you would like to add: ");
		double number = scanner.nextInt();
		customDouble.add(number);
	}
	
	public static void addCustomDouble2() {
		System.out.println("Enter new double you would like to add: ");
		double number = scanner.nextInt();
		customDouble2.add(number);
	}
	
	public static void setConfig() {
		System.out.println("Set whether it is hot outside (1) or cool outside (2)");
	double choice = scanner.nextInt();
		if (choice == 1) {
			isHotOutside = true;
		} else if (choice == 2) {
			isHotOutside = false;
		} else {
		System.out.println("Error");
		}
		System.out.println("Set value for how much money in pocket.");
		choice = getUserChoice();
		moneyinPocket = choice;
		
	}
	
	public static void sum0method() {
	for (int i=0; i<customInteger.size(); i++){
		sum0 += customInteger.get(i); }
	if (sum0 > 100) {
		System.out.println("The sum is " + sum0);
		System.out.println("true: Array is greater than 100");
	} else {
		System.out.println("The sum is " + sum0);
		System.out.println("false: Array is greater than 100");
		System.out.println("It is less than 100.");
	}
	}
}


	