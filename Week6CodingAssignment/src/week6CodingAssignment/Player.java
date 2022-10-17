package week6CodingAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	private List<String> hand = new ArrayList<String>();
	private String name;
	private int score;


	public int length() {
		return hand.size();
	}
	
	public void shuffle() {
		Collections.shuffle(hand);}

	public String getValue(int i) {
		return hand.get(i);
	}

	public void flip() {
		String CardEnd = hand.get(hand.size()-1);
	    for(int i = hand.size()-1; i > 0; i--)
	    {
	        hand.set(i,hand.get(i-1)); 
	    }
	    hand.set(0, CardEnd);   
	}

	
	public void addCard(String card) {
		hand.add(card);
	}

	public List<String> getCards() {
		return hand;
	}

	public void removelast() {
		hand.remove(hand.size() - 1);
	}

	public void describe(int playerNumber) {
		System.out.println("Player " + playerNumber + "'s name is: " + name);
		System.out.println(name + " score is now " + score);
		
	}
	
	public void incrementScore() {
		this.score = score + 1;
	}


	public int getScore() {
		return score;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getHand() {
		return hand;
	}

	public void setHand(List<String> hand) {
		this.hand = hand;
	}
	
	
	public void setScore(int score) {
		this.score = score;
	}
	

}
