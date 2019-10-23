import java.util.*;

public class IndexEntry {


	private TreeSet<Integer> lineNums = new TreeSet<Integer>();
	private String word;
	
	public IndexEntry(String s) {
		word = s;
	}

	public void add(int n) {
		lineNums.add(n);
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		Iterator it = lineNums.iterator();
		String result = word.toUpperCase() + " ";
		
		while(it.hasNext()) {
			result += it.next() + ", ";
			if (it.hasNext()) {
				result += it.next();
			}
		}
		
		return result;
	}
	
}
