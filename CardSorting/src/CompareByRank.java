/**
 * William Mueller
 * DE CSII
 * CompareByRank
 */

import java.util.Comparator;

public class CompareByRank implements Comparator<Card> {
	
	public int compare(Card a, Card b) {
		return a.getRankInt() - b.getRankInt();
	}
	
}
