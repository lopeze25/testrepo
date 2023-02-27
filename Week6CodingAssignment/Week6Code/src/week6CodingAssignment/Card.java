package week6CodingAssignment;

public class Card {
	private int value;
	private String name;
	
	

	
	public String describeCard() { 
		String result = "";
		if (value >= 2 && value < 11 ) { 
		result = (String.valueOf(value) + " of " + name); 
		} 
		else if (value == 11) {   
		result = ("Jack of " + name); }
		else if (value == 12) {   
		result = ("Queen of " + name); }
		else if (value == 13) {   
		result = ("King of " + name); }	
		else if (value == 14) {   
		result =("Ace of " + name); }
		return result;
		}

	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
