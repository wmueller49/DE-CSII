/**
 * William Mueller
 * DE CSII
 * CompareBySuit
 */

import java.util.Comparator;

public class CompareBySuit implements Comparator<Card>{

	public int compare(Card a, Card b) {
		return a.getSuitInt() - b.getSuitInt();
	}

}
