/**
 * William Mueller
 * DE CSII
 * Card
 */

import java.util.*;
import java.lang.Comparable;

public class Card implements Comparable {
	
	/*
	 * Fields
	 */

	private String suit;
	private String rank;
	
	/**
	 * Constructors
	 */
	
	/*
	 * Default Constructor creates an Ace of Spades
	 */
	public Card() {
		suit = "Spades";
		rank = "Ace";
	}
	
	/*
	 * Constructor which copies a card
	 */
	public Card(Card c) {
		suit = c.getSuitStr();
		rank = c.getRankStr();
	}
	
	/*
	 * Constructor takes in two integer values to create a card
	 */
	public Card(int s, int r) {
		setSuitInt(s);
		setRankInt(r);
	}
	
	/*
	 * Constructor takes in two String values to create a card
	 */
	public Card(String s, String r) {
		if(isSuit(s)) {
			suit = s;
		}
		else {
			suit = "Spades";
		}
		if(isRank(r)) {
			rank = r;
		}
		else {
			rank = "Ace";
		}
	}
	
	/*
	 * Constructor takes in a String and int value to create a card
	 */
	public Card(String s, int r) {
		if(isSuit(s)) {
			suit = s;
		}
		else {
			suit = "Spades";
		}
		setRankInt(r);
	}
	
	/*
	 * Constructor takes in a String and int value to create a card
	 */
	public Card(int s, String r) {
		setSuitInt(s);
		if(isRank(r)) {
			rank = r;
		}
		else {
			rank = "Ace";
		}
	}
	/*
	 * Methods
	 */
	
	/*
	 * Sets the suit using an integer
	 */
	public void setSuitInt(int s){
			switch (s) {
			case(0):
				suit = "Clubs";
				break;
			case(1):
				suit = "Diamonds";
				break;
			case(2):
				suit = "Hearts";
				break;
			case(3):
				suit = "Spades";
				break;
		}
	}
	
	/*
	 * sets the rank using an integer
	 */
	public void setRankInt(int r) {
		switch (r) {
			case(1):
				rank = "Ace";
				break;
			case(2):
				rank = "Two";
				break;
			case(3):
				rank = "Three";
				break;
			case(4):
				rank = "Four";
				break;
			case(5):
				rank = "Five";
				break;
			case(6):
				rank = "Six";
				break;
			case(7):
				rank = "Seven";
				break;
			case(8):
				rank = "Eight";
				break;
			case(9):
				rank = "Nine";
				break;
			case(10):
				rank = "Ten";
				break;
			case(11):
				rank = "Jack";
				break;
			case(12):
				rank = "Queen";
				break;
			case(13):
				rank = "King";
				break;
		}
	}
	
	/*
	 * returns the suit of the card as a string
	 */
	public String getSuitStr() {
		return suit;
	}
	
	/*
	 * returns the rank of the card as a string
	 */
	public String getRankStr() {
		return rank;
	}
	
	/*
	 * returns the suit of the card as an integer
	 */
	public int getSuitInt() {
		switch(suit.toLowerCase()) {
		 case("clubs"):
			 return 0;
		 case("diamonds"):
			 return 1;
		 case("hearts"):
			 return 2;
		 case("spades"):
			 return 3; 	
		}
		return -1;
	}
	
	/*
	 * returns the rank of the card as an integer
	 */
	public int getRankInt() {
		switch(rank.toLowerCase()) {
			case("ace"):
				return 1;
			case("two"):
				return 2;
			case("three"):
				return 3;
			case("four"):
				return 4;
			case("five"):
				return 5;
			case("six"):
				return 6;
			case("seven"):
				return 7;
			case("eight"):
				return 8;
			case("nine"):
				return 9;
			case("ten"):
				return 10;
			case("jack"):
				return 11;
			case("queen"):
				return 12;
			case("king"):
				return 13;	
		}
		return -1;
	}
	
	/*
	 * returns whether a string is a valid rank
	 */
	public boolean isRank(String r) {
		switch(r.toLowerCase()) {
			case("ace"):
				return true;
			case("two"):
				return true;
			case("three"):
				return true;
			case("four"):
				return true;
			case("five"):
				return true;
			case("six"):
				return true;
			case("seven"):
				return true;
			case("eight"):
				return true;
			case("nine"):
				return true;
			case("ten"):
				return true;
			case("jack"):
				return true;
			case("queen"):
				return true;
			case("king"):
				return true;	
		}
		return false;
	}

	/*
	 * returns whether a string is a valid suit
	 */
	public boolean isSuit(String s) {
		switch(s.toLowerCase()) {
		 case("clubs"):
			 return true;
		 case("diamonds"):
			 return true;
		 case("hearts"):
			 return true;
		 case("spades"):
			 return true; 	
		}
		return false;
	}
	
	/*
	 * returns a string of "Rank" of "Suit"
	 */
	public String toString() {
		return getRankStr() + " of " + getSuitStr();
	}
	
	/*
	 * Returns whether two cards are equivalent
	 */
	public boolean equals(Card c) {
		return this.rank.equals(c.rank) && this.suit.equals(c.rank);
	}
	
	/*
	 * Returns an integer to compare two cards
	 */
	public int compareTo(Object c) {
		if(c instanceof Card) {
			Card compareCard = new Card((Card)c);
			return this.getRankInt() - compareCard.getRankInt();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		Card card1 = new Card();
		System.out.println(card1.toString());
		
		Card card2 = new Card(2, 2);
		System.out.println(card2.toString());
		
		Card card3 = new Card("Spades", 3);
		System.out.println(card3.toString());
		
		Card card4 = new Card(1, "Nine");
		System.out.println(card4.toString());
		
		Card card5 = new Card("Clubs", "Lion");
		System.out.println(card5.toString());
		
	}

}
