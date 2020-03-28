/**
 * @author William Mueller
 * Priority Message Queue
 * 
 * Message
 * 3/29/2020
 */
public class Message {

	private int priority;
	private int arrival;
	private int processTime;

	public Message(int a) {
		arrival = a;
		priority = (int) Math.floor(Math.random() * 5);
	}
	
	public Message(int a, int p) {
		arrival = a;
		priority = p;
	}
	
	public int getArrival() {
		return arrival;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void process() {
		processTime++;
	}

	public int getProcessTime() {
		return processTime;
	}
}
