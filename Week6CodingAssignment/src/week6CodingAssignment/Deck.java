package week6CodingAssignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<String> cards = new ArrayList<String>();
	
	public void shuffle() {
		Collections.shuffle(cards);}
	

	public int length() {
		return cards.size();
	}

	public List<String> getFirstHalf() {
		List<String> topHalf = new ArrayList<String>();
		for (int i=0; i<=25; i++) {
			topHalf.add(cards.get(i));
			
		}
		return topHalf;
		}
		
	public List<String> getSecondHalf() {
		List<String> bottomHalf = new ArrayList<String>();
			for (int i=26; i<=51; i++) {
				bottomHalf.add(cards.get(i));
					
			}
		return bottomHalf;
		}
	
	public void addCard(String card) {
		cards.add(card);
	}
	
	public List<String> getCards() {
		return cards;
	}
 
				
			
	}



