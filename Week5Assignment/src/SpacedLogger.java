

public class SpacedLogger implements Logger {

	public void log(String word) {
		 int size = word.length();
		 System.out.println("");
			System.out.print("*** ");
		 for (int i = 0; i < size; i++)  {
			 System.out.print((word.charAt(i) + " "));
			} 
		 System.out.println("***");
		 System.out.println("");
	}

	public void error(String word) {
		 int size = word.length();
		 System.out.println("");
		 	System.out.print("****************\n***ERROR: ");
		 for (int i = 0; i < size; i++)  {
			 System.out.print((word.charAt(i) + " "));
			} 
		 System.out.println("***\n****************");
		 System.out.println("");
	}
}