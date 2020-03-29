/**
 * @author William Mueller
 * Priority Message Queue
 * 
 * Message
 * 3/29/2020
 */
public class Message implements Comparable<Message>{

	/**
	 * Field an int the message's priority
	 */
	private int priority;
	
	/**
	 * Field an int when the message arrived
	 */
	private int arrival;
	
	/**
	 * Field an int how many minutes the message has been processed
	 */
	private int processTime;

	/**
	 * Constructor that creates a message with the arrival time a and a random priority from 0 to 4
	 * @param a the arrival time of the message
	 */
	public Message(int a) {
		arrival = a;
		priority = (int) Math.floor(Math.random() * 5);
	}
	
	/**
	 * Constructor that creates a message with the arrival time a and priority p
	 * @param a the arrival time of the message
	 * @param p the priority of the message
	 */
	public Message(int a, int p) {
		arrival = a;
		priority = p;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Message m) {
		return ((Integer) this.getPriority()).compareTo((Integer)m.getPriority());
	}
	
	/**
	 * returns when the message arrived
	 * @return the field arrival
	 */
	public int getArrival() {
		return arrival;
	}
	
	/**
	 * returns the message's priority
	 * @return the field priority
	 */
	public int getPriority() {
		return priority;
	}
	
	/**
	 * adds one to process
	 */
	public void process() {
		processTime++;
	}

	/**
	 * returns the process time
	 * @return the field processTime
	 */
	public int getProcessTime() {
		return processTime;
	}
}
