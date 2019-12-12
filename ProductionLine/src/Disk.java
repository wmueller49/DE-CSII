/**
 * William Mueller
 * DE CSII
 * Disk
 */

public class Disk implements Comparable<Disk> {
	
	private int radius;

	/**
	 * Creates a disk with radius r
	 */
	public Disk(int r) {
		radius = r;
	}
	
	
	/*
	 * Returns the radius of this disk
	 */
	public int getRadius() {
		return radius;
	}
	
	
	/*
	 * Compares this disk to disk d by comparing their radius
	 */
	public int compareTo(Disk d) {
		return this.getRadius() - d.getRadius();
	}
	
	public String toString() {
		String result = "";
		for(int i = 0; i < radius; i++) {
			result += "*";
		}
		result += "\n";
		return result;
	}
}
