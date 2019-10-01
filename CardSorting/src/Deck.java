/**
 * William Mueller
 * DE CSII
 * Deck
 */

public class Deck {
	
	private Card deck[];
	private Card topCard;
	private boolean isShuffled;
	
	public Deck() {
		deck = new Card[52];
		int i = 0;
		for(int s = 0; s < 4; s++) {
			for(int r = 1; r < 14; r++) {
				deck[i] = new Card(s, r);
				i++;
			}
		}
	}
	
	public Deck(boolean b) {
		if(b) {
			
		}
		else {
			
		}
		
	}
	
	public void shuffle() {
		int randNum = 0;
		Card tempCard;
		for(int i = 0; i < this.deck.length; i++) {
			tempCard = deck[i];
			deck[i] = deck[randNum];
			deck[randNum] = tempCard;
		}
	}
	
	public static void main(String [] args) {
		
	}
	
	
	
}
