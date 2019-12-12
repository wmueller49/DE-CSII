/**
 * William Mueller
 * DE CSII
 * ProductionLine
 */

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class ProductionLine {
	
	Queue<Disk> inputQueue;
	Queue<Tower> outputQueue;
	Tower arm;

	/**
	 * 
	 */
	public ProductionLine() {
		inputQueue = new LinkedList<Disk>();
		outputQueue = new LinkedList<Tower>();
		arm = new Tower();
	}
	
	public void addDisk(Disk d){
		inputQueue.add(d);
	}
	
	public void unloadRobot() {
		outputQueue.add(arm);
		arm = new Tower();
	}
	
	public void process() {
		//while inputQueue is not empty
		//check radius, if bigger then add to the arm, else call unload
		while(!(inputQueue.isEmpty())) {
			Disk d = inputQueue.remove();
			if(arm.getTower().isEmpty() || d.getRadius() > ((Disk)arm.getTower().peek()).getRadius()) {
				arm.getTower().add(d);
			}
			else {
				this.unloadRobot();
				arm.getTower().add(d);
			}
		}
		this.unloadRobot();
	}
	
	public Tower removeTower() {
		return outputQueue.poll();
	}

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		ProductionLine pLine = new ProductionLine();
		
		if(args.length > 0) {
			for(int i = 0; i < args.length; i++) {
				pLine.addDisk(new Disk(Integer.parseInt(args[i])));
			}
		}
		
		else {
			System.out.println("How many disks would you like to add?");
			int answer = kb.nextInt();
			for(int i = 0; i < answer; i++) {
				System.out.print("Please enter a disk radius: ");
				pLine.addDisk(new Disk(kb.nextInt()));
			}
		}
		
		pLine.process();
			
		System.out.println(pLine.outputQueue);
	}
}
