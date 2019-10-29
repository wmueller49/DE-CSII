/*
 * William Mueller
 * Test class for Card and Deck
 */
public class CardTest {

	public static void main(String[] args) {
		//Real Cards
		Card card1 = new Card();
		Card card2 = new Card(2, 3);
		Card card3 = new Card(0, "Two");
		Card card4 = new Card("Diamonds", 7);
		Card card5 = new Card("Spades", "King");
		Card card6 = new Card(card5);
		
		System.out.println("--- REAL CARDS ---");
		System.out.println("Card1: " + card1.toString());
		System.out.println("Card1 suit string: " + card1.getSuitStr());
		System.out.println("Card2: " + card2.toString());
		System.out.println("Card2 suit int: " + card2.getSuitInt());
		System.out.println("Card3: " + card3.toString());
		System.out.println("Card3 rank string: " + card3.getRankStr());
		System.out.println("Card4: " + card4.toString());
		System.out.println("Card4 rank int: " + card4.getRankInt());
		System.out.println("Card5: " + card5.toString());
		System.out.println("Card6: " + card6.toString());
		System.out.println();
		
		System.out.println("Card1 equals Card2: " + card1.equals(card2));
		System.out.println("Card3 equals Card3: " + card3.equals(card3));
		System.out.println("Card5 equals Card6: " + card5.equals(card6));
		System.out.println("Card2 compareTo Card4: " + card2.compareTo(card4));
		System.out.println("Card4 compareTo Card2: " + card4.compareTo(card2));
		System.out.println("Card2 compareTo Card2: " + card2.compareTo(card2));
		CompareByRank compRank = new CompareByRank();
		System.out.println("Card2 compareByRank Card4: " + compRank.compare(card2, card4));
		System.out.println("Card4 compareByRank Card2: " + compRank.compare(card4, card2));
		System.out.println("Card4 compareByRank Card4: " + compRank.compare(card4, card4));
		CompareBySuit compSuit = new CompareBySuit();
		System.out.println("Card2 compareBySuit Card4: " + compSuit.compare(card2, card4));
		System.out.println("Card4 compareBySuit Card2: " + compSuit.compare(card4, card2));
		System.out.println("Card4 compareBySuit Card4: " + compSuit.compare(card4, card4));
		
		
		System.out.println();
		System.out.println();
		
		//Fake Cards
		Card fakeCard1 = new Card(100, 100);
		Card fakeCard2 = new Card(100, "Lion");
		Card fakeCard3 = new Card("Stars", 100);
		Card fakeCard4 = new Card("Stars", "Lion");
		
		System.out.println("--- FAKE CARDS ---");
		System.out.println("FakeCard1: " + fakeCard1.toString());
		System.out.println("FakeCard2: " + fakeCard2.toString());
		System.out.println("FakeCard3: " + fakeCard3.toString());
		System.out.println("FakeCard4: " + fakeCard4.toString());
		System.out.println();
		System.out.println();
		
		Deck deck1 = new Deck();
		Deck deck2 = new Deck(false);
		
		System.out.println("DECK1 (Unshuffled)");
		System.out.println(deck1.toString());
		System.out.println("DECK1 (Shuffled)");
		deck1.shuffle();
		System.out.println(deck1.toString());
		System.out.println();
		System.out.println("DECK2 (Shuffled)");
		System.out.println(deck2.toString());
		System.out.println();
		System.out.println("Deck1 equals Deck2: " + deck1.equals(deck2));
		System.out.println("Deck1 equals Deck1: " + deck1.equals(deck1));
		System.out.println();
		
		Deck[] hands = deck2.deal(2, 2);
		System.out.println("Hand 1:\n" + hands[0]);
		System.out.println("Hand 2:\n" + hands[1]);
		
		Deck[] fakeHands = deck1.deal(20, 20);
		System.out.println("Fake hand:\n" + fakeHands);
		System.out.println();
		
		Deck deck3 = new Deck();
		System.out.println("Deck3: " + deck3.toString());
		Card pickedCard = deck3.pick();
		System.out.println(pickedCard);
		System.out.println("Deck3 w/o picked card: " + deck3.toString());	
		System.out.println();
		
		Deck selectionDeck = new Deck(false);
		System.out.println("Selection Deck (Unsorted):\n" + selectionDeck.toString());
		selectionDeck.selectionSort();
		System.out.println("Selection Deck (Sorted w/ SelectionSort):\n" + selectionDeck.toString());
		System.out.println();
		
		Deck mergeDeck = new Deck(false);
		System.out.println("Merge Deck (Unsorted):\n" + mergeDeck.toString());
		mergeDeck.mergeSort();
		System.out.println("Merge Deck (Sorted w/ MergeSort):\n" + mergeDeck.toString());
		System.out.println();
		
		Deck insertionDeck = new Deck(false);
		System.out.println("Insertion Deck (Unsorted):\n" + insertionDeck.toString());
		insertionDeck.insertionSort();
		System.out.println("Insertion Deck (Sorted w/ InsertionSort):\n" + insertionDeck.toString());
		System.out.println();
		
	}

}
