/**
 * William Mueller
 * DE CSII
 * DocumentIndex
 * 11/9/19
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DocumentIndex {
	
	/**
	 * treemap of IndexEntries based on the string that the IndexEntry holds
	 */
	private TreeMap<String, IndexEntry> index;
	
	/**
	 * default constructor
	 */
	public DocumentIndex() {
		index = new TreeMap<String, IndexEntry>();	
	}
	
	
	/**
	 * @param s word to be added
	 * @param n line number of the word
	 */
	public void addWord(String s, int n) {
      if(s.equals(""))
         return;
      s = s.toUpperCase();
		if(index.get(s) != null) {
			index.get(s).add(n);
		}
		else {
			IndexEntry entry = new IndexEntry(s);
			entry.add(n);
			index.put(s, entry);
		}
	}
	
	/**
	 * @param s String made of multiple strings to be broken up into words
	 * @param n Line number
	 * 
	 * Takes in the entire line as a string and splits it into single words
	 * Calls addWord for each word
	 */
	public void addAllWords(String s, int n) {
		String[] words = s.split("\\s+");
		
		for (int i = 0; i < words.length; i++) {
		    words[i] = words[i].replaceAll("[^\\w]", "");
		}
		
		for(String word : words) {
			addWord(word, n);
		}
		
	}
	
	/**
	 * returns an ArrayList of all the IndexEntries in the DocumentIndex
	 */
	public ArrayList<IndexEntry> getIndex() {
      ArrayList<IndexEntry> entries = new ArrayList<IndexEntry>();
      Set set = index.entrySet();
      Iterator i = set.iterator();
      
      while(i.hasNext()){
         Map.Entry m = (Map.Entry)i.next();
         entries.add((IndexEntry)m.getValue());
      }
   
      return entries;
	}
	
}
