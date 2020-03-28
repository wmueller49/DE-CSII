/**
 * @author William Mueller
 * Priority Message Queue
 * 
 * MessagePriorityQueue
 * 3/29/30
 */
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MessagePriorityQueue {

	private ArrayList<ArrayList<Message>> messagePriorityQueue;
	private ArrayList<ArrayList<Integer>> waitTimes;
	private int currentTime;
	
	/**
	 * 
	 */
	public MessagePriorityQueue() {
		messagePriorityQueue  = new ArrayList<ArrayList<Message>>(5);
		waitTimes = new ArrayList<ArrayList<Integer>>(5);
		
		for(int i = 0; i < 5; i++) {
			messagePriorityQueue.add(new ArrayList<Message>());
			waitTimes.add(new ArrayList<Integer>());
		}

		currentTime = 0;
	}
	
	public void addMessage(Message m) {
		int priorityIndex = m.getPriority();
		messagePriorityQueue.get(priorityIndex).add(m);
	}
	
	public void addWaitTime(Message m) {
		int priorityIndex = m.getPriority();
		waitTimes.get(priorityIndex).add(currentTime - m.getArrival());
	}
	
	public Message getHighestPriority() {
		for(int i = 0; i < messagePriorityQueue.size(); i++) {
			if(!(messagePriorityQueue.get(i).isEmpty())) {
				return messagePriorityQueue.get(i).get(0);
			}
		}
		return null;
	}
	
	public Message removeHighestPriority() {
		for(int i = 0; i < messagePriorityQueue.size(); i++) {
			if(!(messagePriorityQueue.get(i).isEmpty())) {
				return messagePriorityQueue.get(i).remove(0);
			}
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessagePriorityQueue messageQueue = new MessagePriorityQueue();
		
		for(int i = 0; i < 400; i++) {
			Message msg = new Message(messageQueue.currentTime);
			messageQueue.addMessage(msg);
			
			messageQueue.getHighestPriority().process();
			
			if(messageQueue.getHighestPriority().getProcessTime() == 4) {
				messageQueue.addWaitTime(messageQueue.removeHighestPriority());
			}
			
			messageQueue.currentTime++;
		}
		
		while(messageQueue.getHighestPriority() != null) {
			messageQueue.getHighestPriority().process();
			
			if(messageQueue.getHighestPriority().getProcessTime() == 4) {
				messageQueue.addWaitTime(messageQueue.removeHighestPriority());
			}
			
			messageQueue.currentTime++;
		}
		
		int avgWaitTime = 0;
		
		
		for(int j = 0; j < messageQueue.waitTimes.size(); j++) {
			for(int k = 0; k < messageQueue.waitTimes.get(j).size(); k++) {
				avgWaitTime += messageQueue.waitTimes.get(j).get(k);
			}
			System.out.println("Average wait time of priority " + j + ": " + avgWaitTime/messageQueue.waitTimes.get(j).size());
			
			avgWaitTime = 0;
		}

	}

}
