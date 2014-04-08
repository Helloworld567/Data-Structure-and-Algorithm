/**
 * Node class that contains the time to finish the task, predecessor and successor as arraylist
 */

import java.util.ArrayList;


public class Node {
	int taskTime;
	int startTime;
	ArrayList<Integer> successor;

	public Node(int t)
	{
		taskTime = t;
		startTime = 0; 
		successor = new ArrayList<Integer>();
	}
	
	public void addNode(int i)
	{
		successor.add(i);
	}
	
	public int getTaskTime()
	{
		return taskTime;
	}
	
	public ArrayList<Integer> getNodeList()
	{
		return successor;
	}
	
	
	public void setStartTime(int t)
	{
		startTime = t;
	}
	
	public int getStartTime()
	{
		return startTime;
	}
}
