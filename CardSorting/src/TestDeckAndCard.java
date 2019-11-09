public class TestDeckAndCard {


// Helper method to make copies of decks
	
   public static Deck copyDeck(Deck other) {
   //		Deck d = new Deck(other.getDeck());
      Deck d = new Deck(other);
      return d;
   }
   
   static Deck d, dUnSorted, dSorted;
   
   
   public static void createDecks() {
   
      d = new Deck();
      dUnSorted = new Deck(false);
      dSorted = new Deck(true);
      
   }
   public static void printDecks() {
   
      
   //		Deck dUnSorted = new Deck(dSorted);
   //      dUnSorted.shuffle();
      
      System.out.println("Default Deck\n");
      System.out.println(d);
   
      System.out.println("\nUnsorted Deck\n");
      System.out.println(dUnSorted);
      
      System.out.println("\nSorted Deck");
      System.out.println(dSorted);
      
      dUnSorted.shuffle();
      System.out.println("\nUnsorted Deck after one shuffle\n"); 
      System.out.println(dUnSorted); 
   }
   
   public static void shuffleAndEquals() {
       //Test Deck Equals with the sorted and unsorted decks ... before removing anything
      
      Deck origUnsorted = new Deck(dUnSorted);
      System.out.println("The decks are equal: " + dSorted.equals(dUnSorted));
   	
      for (int x = 0; x < 5; x++)
         dUnSorted.shuffle();
   	
      System.out.println("\n\n: Unsorted Deck after shuffling 5 times");
      System.out.println(dUnSorted);
      System.out.println("\n");
   
   
      //Test Deck Equals after shuffling 5 times ... before removing anything
      
      System.out.println("\nThe decks are equal: " + origUnsorted.equals(dUnSorted)+ "\n");
   }
     
   public static void pick(Deck pDeck, int n) throws IndexOutOfBoundsException {
   
      System.out.println("\nPicking " + n + " cards from this deck: \n");
      System.out.println(pDeck);
      int x = 0;
   	try {
      for (; x < n; x++)
         System.out.println(pDeck.pick());
         } catch (NullPointerException e) {
         System.out.println("***** NullPointerException while Picking Cards");
          } catch (NegativeArraySizeException e) {
         System.out.println("***** NegativeArraySizeException while Picking Cards");

   	}
      System.out.println("\n\n: After Picking " + x + " cards");
      System.out.println(pDeck);
      System.out.println("\n");
   }  

   public static void testUnEqualDecks()  {
      //Test Deck Equals with the sorted and unsorted decks ... after removing anything
     // Deck dCopy = copyDeck(dUnSorted);
     Deck dCopy;
     try {
     dUnSorted = new Deck(false);
      dCopy = new Deck(dUnSorted);
         dCopy.pick();
         System.out.println("The decks are unequal (so should be false): " + dCopy.equals(dUnSorted));
      } catch (NullPointerException e) {
        System.out.println("***** NullPointerException in Test Unequal Decks ");
      } catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("***** ArrayIndexOutOfBoundsException in testUnEqualDecks while calling pick()");
      }  
   }  
 
   public static void printHands(Deck[] allHands) throws NullPointerException {
      int i = 1;
      for (Deck h : allHands) {
         System.out.println("Hand: " + i++);
         System.out.println(h);
      }
   } 
 
   public static void testDeals() throws NullPointerException {
   
      System.out.println("\nTesting a small deal of 3 hands and 2 cards per hand\n");
      dSorted = new Deck();
      System.out.println(dSorted);
      Deck[] hands = dSorted.deal(3,2);
      printHands(hands);
      System.out.println("Deck after dealing");
      System.out.println(dSorted);
      
      System.out.println("\nTesting a larger deal of 5 hands and 10 cards per hand\n");
      System.out.println(d);
      hands = d.deal(5,10);
      printHands(hands);  
      System.out.println("Deck after dealing");
      System.out.println(d);
   }  
   
   public static void testSelectionSort()throws IndexOutOfBoundsException, NullPointerException {
   
      System.out.println("\nFull Deck Selection Sort\n");
      dUnSorted = new Deck(false);
      dUnSorted.shuffle();
      
      System.out.println("\nDeck before Selection Sort");
      System.out.println(dUnSorted);
      dUnSorted.selectionSort();
   	
      System.out.println("\nDeck after Selection Sort");
      System.out.println(dUnSorted);
      System.out.println("\n");
      
      for (int i = 0; i < 42; i++) dUnSorted.pick();
      dUnSorted.shuffle();
 //     dUnSorted.deal(10,3, true);  
      dUnSorted.shuffle(); 
      System.out.println("\nPartial Deck Selection Sort\n");
      
      System.out.println("\nDeck before Selection Sort");
      System.out.println(dUnSorted);
      dUnSorted.selectionSort();
   	
      System.out.println("\nDeck after Selection Sort");
      System.out.println(dUnSorted);
      System.out.println("\n");  
   }
  
   public static void testMergeSort() {
   
      System.out.println("\nFull Deck Merge Sort\n");
      
      System.out.println("\nDeck before Merge Sort");
      dUnSorted = new Deck(false);
      dUnSorted.shuffle();
      System.out.println(dUnSorted);
      
     // dUnSorted.mergeSort(dUnSorted.getDeck());
   	 dUnSorted.mergeSort();
   	
      System.out.println("\nDeck after Merge Sort");
      System.out.println(dUnSorted);
      System.out.println("\n");
     
      for (int i = 0; i < 42; i++) dUnSorted.pick();
   //      dUnSorted.deal(10,3);  
   
      dUnSorted.shuffle();
      dUnSorted.shuffle();
       
      System.out.println("\nPartial Deck Merge Sort\n");
      
      System.out.println("\nDeck before Merge Sort");
      System.out.println(dUnSorted);
      dUnSorted.mergeSort();
   	
      System.out.println("\nDeck after Merge Sort");
      System.out.println(dUnSorted);
      System.out.println("\n"); 
   }
      
   public static void deckTests() {
   
   System.out.println("\nDeck Tests\n");
   	
      createDecks();
   
      printDecks();
      shuffleAndEquals();
    
      try {
         pick(dUnSorted, (int) (Math.random()*40));
         System.out.printf("Top Card: %d, should be 51\n", dUnSorted.getTopCard());
         pick(dSorted, (int) (Math.random()*40));
         pick(dSorted, dSorted.getTopCard()+1);
         pick(dSorted, 52);
      } catch (IndexOutOfBoundsException e) {
         System.out.println("IndexOutOfBoundsException while picking");
      }
   
      testUnEqualDecks();
   
      try {
         testDeals();
      } catch (NullPointerException e) {
         System.out.println("***** Null Pointer Exception during Deal");
      }
  
      try {
         testSelectionSort(); } 
      catch (IndexOutOfBoundsException e) {
         System.out.println("***** IndexOutOfBoundsException during Selection Sort");
      }
      catch (NullPointerException e) {
         System.out.println("***** NullPointerException during Selection Sort");
      }
  
      try {
         testMergeSort(); } 
      catch (IndexOutOfBoundsException e) {
         System.out.println("***** IndexOutOfBoundsException during Merge Sort");
      }
          catch (NullPointerException e) {
         System.out.println("***** NullPointerException during Merge Sort");
      }
   
    
   }
	
   public static void cardTests() {
      int score = 0;
      final int regularScore = 5;
      final int biggerScore = regularScore*2;
      int total = 0;
      String failed = "Failed:";
     Card c=null, c1=null, c2=null, c3=null, c4=null;
     try {
       c = new Card();
      } catch (IllegalArgumentException e) {
        System.out.println("Illegal Arguement Exception while creating a default card\n");
      }
      
      c1  = new Card(3,5); // Suit as int, rank as int
      c2 = new Card("Hearts", "Five");  // Suit as String, Rank as String
      c3 = new Card("Spades", 12);  // Suit as String, Rank as Int
      c4 = new Card (1, "Nine");  // Suit as Int, Rank as String
   	
      System.out.println("Card Tests, with cards: ");
     if (! (c == null) )   {System.out.print("\t" + c.toString() + "\n"); } else {System.out.println("null card");}
     if (! (c1 == null) )  {System.out.print("\t" + c1.toString() + "\n");} else {System.out.println("null card");}
     if (! (c2 == null) )  {System.out.print("\t" + c2.toString() + "\n");} else {System.out.println("null card");}
     if (! (c3 == null) )  {System.out.print("\t" + c3.toString() + "\n");} else {System.out.println("null card");}
     if (! (c4 == null) )  {System.out.print("\t" + c4.toString() + "\n");} else {System.out.println("null card");}
  
      String card = c1.toString();  
      if(card.equalsIgnoreCase("Five of Spades")) 
         score += regularScore;
      else
         failed += "\n\tConstructor(int, int) Should be Five of Spades, was " + card;
      total += regularScore;
   
      card = c2.toString();  
      if(card.equalsIgnoreCase("Five of Hearts")) 
         score += regularScore;
      else
         failed += "\n\tConstructor(String, String) Should be Five of Hearts, was " + card;
      total += regularScore;
   
      card = c3.toString();  
      if(card.equalsIgnoreCase("Queen of Spades")) 
         score += regularScore;
      else
         failed += "\n\tConstructor(String, int) Should be Queen of Spades, was " + card;
      total += regularScore;
   
      card = c4.toString();  
      if(card.equalsIgnoreCase("Nine of Diamonds")) 
         score += regularScore;
      else
         failed += "\n\tConstructor(int, String) Should be Nine of Diamonds, was " + card;
      total += regularScore;
     	
      String suit = c2.getSuitStr();  
      if(suit.equalsIgnoreCase("Hearts")) 
         score += regularScore;
      else
         failed += "\n\tgetSuit: Should be Hearts, was " + suit;
      total += regularScore;
   	
      int rank = c4.getRankInt();	
      if (rank == 9) 
         score += regularScore;
      else
         failed += "\n\tgetRank: Should be 9, was " + rank;
      total += regularScore;
   	
      String cardC1 = c1.toString();   
      if (cardC1.equalsIgnoreCase("Five of Spades")) 
         score += regularScore;
      else
         failed += "\n\ttoString(): Should be Five of Spades was " + cardC1;
      total += regularScore;
   	
   //		suit = c3.getRankStr();		
      suit = c3.getRankStr();		
      if (suit.equalsIgnoreCase("Queen")) 
         score += regularScore;
      else
         failed += "\n\tgetRankStr(): Should be Queen was " + suit;
      total += regularScore;
   	
      rank = c1.getSuitInt();		
      if (rank == 3) 
         score += regularScore;
      else
         failed += "\n\tgetSuitInt(): Should be 3 was " + rank;
      total += regularScore;
   	
      int compare = c3.compareTo(c2); 
      if (compare > 0) 
         score += regularScore;
      else
         failed += "\n\tcompareTo: Should be positive, was " + compare;
      total += regularScore;
   	
      compare = c2.compareTo(c3); 
      if (compare < 0) 
         score += regularScore;
      else
         failed += "\n\tcompareTo: Should be negative, was " + compare;
      total += regularScore;
   			
      compare = c3.compareTo(c3); 
      if (compare == 0) 
         score += regularScore;
      else
         failed += "\n\tcompareTo: Should be 0, was " + compare;
      total += regularScore;
   	
      boolean equal = c1.equals(c4); 
      if (! equal) 
         score += regularScore;
      else
         failed += "\n\tNot Equals";
      total += regularScore;
   	
      boolean equal2 = c1.equals(c1); 
      if (equal2) 
         score += regularScore;
      else
         failed += "\n\tEquals";
      total += regularScore;
   	
      System.out.printf("%s\n\n%d / %d = %.2f\n",failed,score, total, 100.0 * score / total);
   }
	
   public static void main(String[] args) {
      cardTests();
      deckTests();
   }

}
