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
	
	
	/**
	 * @return the radius of this disk
	 */
	public int getRadius() {
		return radius;
	}
	
	
	/**
	 * Compares this disk to disk d by comparing their radius
	 * @return the difference between radii
	 */
	public int compareTo(Disk d) {
		return this.getRadius() - d.getRadius();
	}
	
	/**
	 * @return the Disk as a string
	 */
	public String toString() {
		String result = "";
		for(int i = 0; i < radius; i++) {
			result += "*";
		}
		result += "\n";
		return result;
	}
}
