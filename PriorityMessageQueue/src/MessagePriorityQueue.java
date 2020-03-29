/**
 * @author William Mueller
 * Priority Message Queue
 * 
 * MessagePriorityQueue
 * 3/29/30
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MessagePriorityQueue {

	/**
	 * Field an ArrayList of ArrayLists which holds messages
	 */
	private ArrayList<ArrayList<Message>> messagePriorityQueue;
	
	/**
	 * Field an ArrayList of ArrayLists which holds the wait times for messagePriorityQueue
	 */
	private ArrayList<ArrayList<Integer>> waitTimes;
	
	/**
	 * Field a PriorityQueue to compare to the ArrayList of ArrayLists
	 */
	private PriorityQueue<Message> heapQueue;
	
	/**
	 * Field an ArrayList of ArrayLists which holds the wait times for heapQueue
	 */
	private ArrayList<ArrayList<Integer>> heapWaitTimes;
	
	/**
	 * Field which is the currentTime for keeping track of when messages were created
	 */
	private int currentTime;
	
	private static String outputPath = "output.txt";
	private static File outputFile = new File(outputPath);
	private static PrintWriter output = null;
	
	/**
	 * Constructor instantiates messagePriorityQueue, waitTimes and heapWaitTimes as ArrayLists with 5 ArrayLists, one for each priority
	 * instantiates heapQueue and sets currentTime to 0
	 */
	public MessagePriorityQueue() {
		messagePriorityQueue  = new ArrayList<ArrayList<Message>>(5);
		waitTimes = new ArrayList<ArrayList<Integer>>(5);
		heapWaitTimes = new ArrayList<ArrayList<Integer>>(5);
		heapQueue = new PriorityQueue<Message>();
		
		for(int i = 0; i < 5; i++) {
			messagePriorityQueue.add(new ArrayList<Message>());
			waitTimes.add(new ArrayList<Integer>());
			heapWaitTimes.add(new ArrayList<Integer>());
		}

		currentTime = 0;
	}
	
	/**
	 * adds a message to messagePriorityQueue in the ArrayList associated with its priority
	 * @param m the message to be added to messagePriorityQueue
	 */
	public void addMessage(Message m) {
		int priorityIndex = m.getPriority();
		messagePriorityQueue.get(priorityIndex).add(m);
	}
	
	/**
	 * adds a message's wait time to waitTimes
	 * @param m the message whose wait time is being added
	 */
	public void addWaitTime(Message m) {
		int priorityIndex = m.getPriority();
		waitTimes.get(priorityIndex).add(currentTime - m.getArrival());
	}
	
	/**
	 * adds a message's wait time to heapWaitTimes
	 * @param m the message whose wait time is being added
	 */
	public void addHeapWaitTime(Message m) {
		int priorityIndex = m.getPriority();
		heapWaitTimes.get(priorityIndex).add(currentTime - m.getArrival());
	}
	
	/**
	 * returns the message with the highest priority in messagePriorityQueue without removing it
	 * @return the message with the highest priority
	 */
	public Message getHighestPriority() {
		for(int i = 0; i < messagePriorityQueue.size(); i++) {
			if(!(messagePriorityQueue.get(i).isEmpty())) {
				return messagePriorityQueue.get(i).get(0);
			}
		}
		return null;
	}
	
	/**
	 * returns and removes the message with the highest priority in messagePriorityQueue
	 * @return the message with the highest priority
	 */
	public Message removeHighestPriority() {
		for(int i = 0; i < messagePriorityQueue.size(); i++) {
			if(!(messagePriorityQueue.get(i).isEmpty())) {
				return messagePriorityQueue.get(i).remove(0);
			}
		}
		return null;
	}

	/**
	 * main method instantiates a MessagePriorityQueue, fills it with 400 messages, and then compares the wait times for each priority
	 * compares the wait times of the messages in messagePriorityQueue to heapQueue
	 * @param args an array for arguments
	 */
	public static void main(String[] args) {
		MessagePriorityQueue messageQueue = new MessagePriorityQueue();
		
		for(int i = 0; i < 400; i++) {
			Message msg = new Message(messageQueue.currentTime);
			Message msg2 = new Message(messageQueue.currentTime);
			
			messageQueue.addMessage(msg);
			messageQueue.heapQueue.add(msg2);
			
			messageQueue.getHighestPriority().process();
			messageQueue.heapQueue.peek().process();
			
			if(messageQueue.getHighestPriority().getProcessTime() == 4) {
				messageQueue.addWaitTime(messageQueue.removeHighestPriority());
			}
			
			if(messageQueue.heapQueue.peek().getProcessTime() == 4) {
				messageQueue.addHeapWaitTime(messageQueue.heapQueue.poll());
			}
			
			messageQueue.currentTime++;
		}
		
		
		while(messageQueue.getHighestPriority() != null && messageQueue.heapQueue.peek() != null) {
			messageQueue.getHighestPriority().process();
			messageQueue.heapQueue.peek().process();
			
			if(messageQueue.getHighestPriority().getProcessTime() == 4) {
				messageQueue.addWaitTime(messageQueue.removeHighestPriority());
			}
			
			if(messageQueue.heapQueue.peek().getProcessTime() == 4) {
				messageQueue.addHeapWaitTime(messageQueue.heapQueue.poll());
			}
			
			messageQueue.currentTime++;
		}
		
		try {
			output = new PrintWriter(outputFile);
		}
		catch(FileNotFoundException ex) {
			
		}
		
		output.println("My implementation:");
		
		int avgWaitTime = 0;
		
		
		for(int j = 0; j < messageQueue.waitTimes.size(); j++) {
			for(int k = 0; k < messageQueue.waitTimes.get(j).size(); k++) {
				avgWaitTime += messageQueue.waitTimes.get(j).get(k);
			}
			output.println("Average wait time of priority " + j + ": " + avgWaitTime/messageQueue.waitTimes.get(j).size());
			
			avgWaitTime = 0;
		}
		
		output.println();
		output.println("PriorityQueue's Heap Implementation:");
		
		int avgHeapTime = 0;
		
		for(int j = 0; j < messageQueue.heapWaitTimes.size(); j++) {
			for(int k = 0; k < messageQueue.heapWaitTimes.get(j).size(); k++) {
				avgHeapTime += messageQueue.heapWaitTimes.get(j).get(k);
			}
			output.println("Average wait time of heap priority " + j + ": " + avgHeapTime/messageQueue.heapWaitTimes.get(j).size());
			avgHeapTime = 0;
		}

		output.close();	
	}

}
