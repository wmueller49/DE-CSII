/**
 * @author William Mueller
 * DE CSII
 * TicTacToeHashCode
 * 3/16/2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class TicTacToeMyHashMap  {

	private HashMap winners = new HashMap();
	private String currentString = null;

	/**
	 * Constructor that instantiates winners with an initial capacity of 50 and load factor of .75
	 */
   TicTacToeMyHashMap() {
	   int initCapacity = 50;
	   float loadFactor = 0.75f;
	   winners = new HashMap(initCapacity, loadFactor);
   }

   /**
    * finds the capacity of winners
    * @return the capacity of winners
    * @throws NoSuchFieldException
    * @throws IllegalAccessException
    */
   private int capacity() throws NoSuchFieldException, IllegalAccessException {
      Field tableField = HashMap.class.getDeclaredField("table");
      tableField.setAccessible(true);
      Object[] table = (Object[]) tableField.get(winners);
      return table == null ? 0 : table.length;   
   }
   
   /**
    * finds the total number of entries in winners
    * @return the number of entries in winners
    * @throws NoSuchFieldException
    * @throws IllegalAccessException
    */
   private int numEntries() throws NoSuchFieldException, IllegalAccessException {
	   Field tableField = HashMap.class.getDeclaredField("table");
	   tableField.setAccessible(true);
	   Object[] table = (Object[]) tableField.get(winners);
	   int numEntries = 0;
	   int cap = capacity();
	   
	   for(int i = 0; i < cap; i++) {
		   if(table[i] != null) {
			   numEntries++;
			   Class cls = table[i].getClass();
			   Field tField = cls.getDeclaredField("next");
			   tField.setAccessible(true);
			   Object t = tField.get(table[i]);
			   
			   while(t != null) {
				   numEntries++;
				   Class cls2 = t.getClass();
				   Field tField2 = cls2.getDeclaredField("next");
				   tField2.setAccessible(true);
				   t = tField2.get(t);
			   }
		   }
	   }
	   
	   return numEntries;
   }

   /**
    * finds the number of entries in a portion of winners
    * @param start the starting index
    * @param end the ending index
    * @return the total number in the portion between start and end
    * @throws NoSuchFieldException
    * @throws IllegalAccessException
    */
	private int numInPortion(double start, double end) throws NoSuchFieldException, IllegalAccessException {
		   int num = 0;
		   int s = (int) (start * capacity());
		   int e = (int) (end * capacity());
		   
		   Field tableField = HashMap.class.getDeclaredField("table");
		   tableField.setAccessible(true);
		   Object[] table = (Object[]) tableField.get(winners);
		   
		   int cap = capacity();
		   
		   for(int i = s; i < e; i++) {
			   if(table[i] != null) {
				   num++;
				   Class cls = table[i].getClass();
				   Field tField = cls.getDeclaredField("next");
				   Field hsh = cls.getDeclaredField("hash");
				   Field ky = cls.getDeclaredField("key");
				   
				   ky.setAccessible(true);
				   Object key = ky.get(table[i]);
				   
				   hsh.setAccessible(true);
				   hsh.set(table[i], 1);
				   
				   tField.setAccessible(true);
				   Object t = tField.get(table[i]);
				   
				   while(t != null) {
					   num++;
					   Class cls2 = t.getClass();
					   Field tField2 = cls2.getDeclaredField("next");
					   tField2.setAccessible(true);
					   t = tField2.get(t);
				   }
			   }
		   }
		   
		   return num;
	   }
   
	   /**
	    * finds the total number of collisions in a portion of winners
	    * @param start the starting index
	    * @param end the ending index
	    * @return the total number of collisions between start and end
	    * @throws NoSuchFieldException
	    * @throws IllegalAccessException
	    */
   private int numCollisionsInPortion(double start, double end) throws NoSuchFieldException, IllegalAccessException {
	   int numCollisions = 0;
	   int s = (int) (start * capacity());
	   int e = (int) (end * capacity());
	   
	   Field tableField = HashMap.class.getDeclaredField("table");
	   tableField.setAccessible(true);
	   Object[] table = (Object[]) tableField.get(winners);
	   
	   int cap = capacity();
	   
	   for(int i = s; i < e; i++) {
		   if(table[i] != null) {
			   Class cls = table[i].getClass();
			   Field tField = cls.getDeclaredField("next");
			   tField.setAccessible(true);
			   Object t = tField.get(table[i]);
			   
			   while(t != null) {
				   numCollisions++;
				   Class cls2 = t.getClass();
				   Field tField2 = cls2.getDeclaredField("next");
				   tField2.setAccessible(true);
				   t = tField2.get(t);
			   }
		   }
	   }
	   
	   return numCollisions;
   }
   
   /**
    * finds all of the chains in winners
    * @return an arraylist of all the chains in winners
    * @throws NoSuchFieldException
    * @throws IllegalAccessException
    */
   private ArrayList<Integer> chains() throws NoSuchFieldException, IllegalAccessException {
	   ArrayList<Integer> chains = new ArrayList<Integer>();
	   Field tableField = HashMap.class.getDeclaredField("table");
	   tableField.setAccessible(true);
	   Object[] table = (Object[]) tableField.get(winners);
	   
	   int cap = capacity();
	   
	   for(int i = 0; i < capacity(); i++) {
		   int chainLength = 0;
		   
		   if(table[i] != null) {
			   chainLength++;
			   Class cls = table[i].getClass();
			   Field tField = cls.getDeclaredField("next");
			   tField.setAccessible(true);
			   Object t = tField.get(table[i]);
			   
			   while(t != null) {
				   chainLength++;
				   Class cls2 = t.getClass();
				   Field tField2 = cls2.getDeclaredField("next");
				   tField2.setAccessible(true);
				   t = tField2.get(t);
			   }
			   if(chainLength > 1) {
				   chains.add(chainLength);
			   }
		   }
	   }
	   
	   return chains;
   }
   
   public static void main(String[] args) throws java.io.FileNotFoundException,
                                              NoSuchFieldException, 
                                              IllegalAccessException {

      TicTacToeMyHashMap m = new TicTacToeMyHashMap();

	   String input = "TicTacToeWinners.txt";
		File inputFile = new File(input);
		Scanner inputScanner = null;
		
		try {
			inputScanner = new Scanner(inputFile);
		}
		catch(FileNotFoundException ex) {
			
		}
		
		while(inputScanner.hasNextLine()) {
			m.currentString = inputScanner.nextLine();
			NewString s = new NewString(m.currentString);
			
			m.winners.put(s, true);
		}
		
		inputScanner.close();

		System.out.println("Capacity: " + m.capacity());
		System.out.println("Entries: " + m.numEntries());
		
		System.out.println("Num entries in Quarter 1: " + m.numInPortion(0, .25));
		System.out.println("Num entries in Quarter 2: " + m.numInPortion(.25, .5));
		System.out.println("Num entries in Quarter 3: " + m.numInPortion(.5, .75));
		System.out.println("Num entries in Quarter 4: " + m.numInPortion(.75, 1));
		
		System.out.println("Num collisions in Tenth 1: " + m.numCollisionsInPortion(0, .1));
		System.out.println("Num collisions in Tenth 2: " + m.numCollisionsInPortion(.1, .2));
		System.out.println("Num collisions in Tenth 3: " + m.numCollisionsInPortion(.2, .3));
		System.out.println("Num collisions in Tenth 4: " + m.numCollisionsInPortion(.3, .4));
		System.out.println("Num collisions in Tenth 5: " + m.numCollisionsInPortion(.4, .5));
		System.out.println("Num collisions in Tenth 6: " + m.numCollisionsInPortion(.5, .6));
		System.out.println("Num collisions in Tenth 7: " + m.numCollisionsInPortion(.6, .7));
		System.out.println("Num collisions in Tenth 8: " + m.numCollisionsInPortion(.7, .8));
		System.out.println("Num collisions in Tenth 9: " + m.numCollisionsInPortion(.8, .9));
		System.out.println("Num collisions in Tenth 10: " + m.numCollisionsInPortion(.9, 1));
		
		ArrayList<Integer> chains = m.chains();
		int maxChain = 0;
		int avgChain = 0;
		for(int i : chains) {
			avgChain += i;
			if(i > maxChain) {
				maxChain = i;
			}
		}
		avgChain /= chains.size();
		
		System.out.println("Avg chain: " + avgChain);
		System.out.println("Max chain: " + maxChain);
   }

}