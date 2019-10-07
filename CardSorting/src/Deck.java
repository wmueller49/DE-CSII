/**
 * William Mueller
 * DE CSII
 * Deck
 */

public class Deck {
	
	/*
	 * Fields
	 */
	private Card[] deck;
	private int topCard;
	private boolean isShuffled;
	private Card[] temp;
	
	
	/*
	 * Constructors
	 */
	
	/*
	 * Default Constructor creates cards in order
	 */
	public Deck() {
		deck = new Card[52];
		int s = 0;
		int r = 1;
		
		for(int i = 0; i < 52; i++) {
			if(r == 14) {
				r = 1;
				s++;
			}
			deck[i] = new Card(s,r);
			r++;
		}
	}
	
	/*
	 * Constructor which creates an empty deck of n length
	 */
	public Deck(int n) {
		deck = new Card[n];
	}
	
	/*
	 * Constructor which uses boolean b to decide whether the deck should be shuffled or not
	 */
	public Deck(boolean b) {
		deck = new Card[52];
		int s = 0;
		int r = 1;
		
		for(int i = 0; i < 52; i++) {
			if(r == 14) {
				r = 1;
				s++;
			}
			deck[i] = new Card(s,r);
			r++;
		}
		
		if(b) {
			
		}
		else {
			this.shuffle();
		}
		
	}
	
	/*
	 * Methods
	 */
	
	/*
	 * shuffles the deck
	 */
	public void shuffle() {
		int randNum = 0;
		Card tempCard;
		for(int i = 0; i < this.deck.length; i++) {
			randNum = (int)Math.floor(Math.random()*52);
			tempCard = deck[i];
			deck[i] = deck[randNum];
			deck[randNum] = tempCard;
		}
	}
	
	
	/*
	 * Converts the deck to a String
	 * In order of appearance by suit in deck if length of 52
	 * In order if not
	 */
	public String toString() {
		String result = "";
		
		if(deck.length == 52) {
			String cResult = "";
			for(int i = 0; i < 52; i++) {
				if(deck[i].getSuitInt() == 0) {
					cResult += deck[i].toString() + "-";
				}
			}
			String[] cArray = cResult.split("-");
			
			String dResult = "";
			for(int i = 0; i < 52; i++) {
				if(deck[i].getSuitInt() == 1) {
					dResult += deck[i].toString() + "-";
				}
			}
			String[] dArray = dResult.split("-");
			
			String hResult = "";
			for(int i = 0; i < 52; i++) {
				if(deck[i].getSuitInt() == 2) {
					hResult += deck[i].toString() + "-";
				}
			}
			String[] hArray = hResult.split("-");
			
			String sResult = "";
			for(int i = 0; i < 52; i++) {
				if(deck[i].getSuitInt() == 3) {
					sResult += deck[i].toString() + "-";
				}
			}
			String[] sArray = sResult.split("-");
			
			for(int i = 0; i < cArray.length; i++) {
				result += cArray[i] + " | " + dArray[i] + " | " + hArray[i] + " | " + sArray[i] + "\n";
			}
			
		}
		
		
		else {
			for(int i = 0; i < deck.length; i++) {
				result = result + deck[i] + "\n";
			}
		}
		return result;
	}
	
	/*
	 * returns whether two decks are equal
	 */
	public boolean equals(Deck d) {
		return this.toString().equals(d.toString());
	}
	
	/*
	 * returns an array of decks with cards drawn from the deck
	 */
	public Deck[] deal(int numHands, int numCards){
		if(numHands * numCards > 52) {
			return null;
		}
		
		Deck[] hands = new Deck[numHands];
		topCard = deck.length-1;
		
		for(int i = 0; i < numHands; i++) {
			hands[i] = new Deck(numCards);
			for(int j = 0; j < numCards; j++) {
				hands[i].add(deck[topCard], j);
				topCard--;
			}
		}
		
		return hands;
	}
	
	/*
	 * returns a random card from the deck
	 */
	public Card pick() {
		int random = (int) Math.floor(Math.random() * 52);
		return deck[random];
	}
	
	/*
	 * adds a card c to the array at position n
	 */
	public void add(Card c, int n) {
		deck[n] = c;
	}
	
	/*
	 * sorts the deck using selection sort
	 */
	public void selectionSort() {
		for(int n = deck.length; n > 1; n--) {
			int iMax = 0;
			for(int i = 1; i < n; i++) {
				if(deck[i].compareTo(deck[iMax]) > 0) {
					iMax = i;
				}
			}
			
			Card aTemp = deck[iMax];
			deck[iMax] = deck[n-1];
			deck[n-1] = aTemp;
		}
	}
	
	/*
	 * sorts the deck using mergeSort
	 */
	public void mergeSort() {
		int n = deck.length;
		temp = new Card[n];
		recursiveSort(deck, 0, n-1);
	}
	
	/*
	 * helper method for mergeSort
	 */
	private void recursiveSort(Card[] c, int from, int to) {
		if(to - from < 2) {
			if(to > from && (c[to].compareTo(c[from]) < 0)) {
				Card aTemp = c[to];
				c[to] = c[from];
				c[from] = aTemp;
			}
		}
		else {
			int middle = (from+to)/2;
			recursiveSort(c, from, middle);
			recursiveSort(c, middle+1, to);
			merge(c, from, middle, to);
		}
	}
	
	/*
	 * helper method for mergeSort
	 */
	private void merge(Card[] c, int from, int middle, int to) {
		int i = from, j = middle + 1, k = from;
		while(i <= middle && j <= to){
			if(c[i].compareTo(c[j]) < 0) {
				temp[k] = c[i];
				i++;
			}
			else {
				temp[k] = c[j];
				j++;
			}
			k++;
		}
		
		while(i <= middle) {
			temp[k] = c[i];
			i++;
			k++;
		}
		while(j <= to) {
			temp[k] = c[j];
			j++;
			k++;
		}
		
		for(k = from; k <= to; k++) {
			c[k] = temp[k];
		}
	}
	
	public static void main(String [] args) {
		Deck deck1 = new Deck(false);
		//System.out.println(deck1.toString());
		//deck1.mergeSort();
		//deck1.selectionSort();
		System.out.println(deck1.toString());
		
	}
	
	
	
}
