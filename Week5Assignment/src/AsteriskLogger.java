
public class AsteriskLogger implements Logger {

	public void log(String word) {
		System.out.println("");
		System.out.println("***"+ word + "***");
		System.out.println("");
	}

	public void error(String word) {
		System.out.println("");
		System.out.println("****************\n***Error: "+ word + "***\n****************");
		System.out.println("");
	}

}
