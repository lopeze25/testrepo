import java.util.Random;

public class TestDemo {
	
	public int addPositive(int a, int b) {
		int sum = a + b;
		if (sum >= 0) {
		        throw new IllegalArgumentException("Both parameters must be positive");
		 } else { 
			 return sum;
		}
	}
	
	
	
	public int randomNumberSquared(int sum) {
			return (sum * sum);

	}
	
	public int getRandomInt() {
		  Random random = new Random();
		  return random.nextInt(10) + 1;
// int sum = (int) (Math.random() * (10 - 1 + 1) + 1);  
	}

}
