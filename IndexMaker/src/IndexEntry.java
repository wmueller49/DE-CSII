/**
 * William Mueller
 * DE CSII
 * IndexEntry
 * 11/9/19
 */

import java.util.Iterator;
import java.util.TreeSet;

public class IndexEntry {

	/*
	 * Treeset of all the lineNumbers the word appears on
	 * String of the word itself
	 */
	private TreeSet<Integer> lineNums = new TreeSet<Integer>();
	private String word;
	
	/*
	 * Creates an IndexEntry with a specific word
	 */
	public IndexEntry(String s) {
		word = s;
	}

	/*
	 * adds the line number n to the TreeSet of line numbers
	 */
	public void add(int n) {
		lineNums.add(n);
	}
	
	/*
	 * returns the word
	 */
	public String getWord() {
		return word;
	}
	
	/*
	 * returns the IndexEntry as a String with the format of the word in all caps
	 * followed by all the line numbers it appears on
	 */
	public String toString() {
		Iterator it = lineNums.iterator();
		String result = word.toUpperCase() + " ";
		
		while(it.hasNext()) {
				result += it.next() + ", ";
		}
		result = result.substring(0, result.length() - 2);
		
		return result;
	}
	
}
