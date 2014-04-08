import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


public class TaskSet {
	ArrayList<Node> dag;
	int noNodes;
	int nodeVisited;
	ArrayList<Integer> topSorted;
	public TaskSet()
	{
		dag = new ArrayList<Node>();
		noNodes = 0;	
		topSorted = new ArrayList<Integer>();
	}
	
	public void addTask(int t)
	{
		
		Node n = new Node(t);
		dag.add(n);
		topSorted.add(noNodes);
		noNodes++;
	}
	/**
	 * add an edge from j to i (j must proceed i)
	 * @param i
	 * @param j
	 */
	public void addConstraint(int end, int start)
	{
		if (pathExist(end, start))
		{
			dag.get(start).setStartTime(-1);
			dag.get(end).setStartTime(-1);
		}
		else
		{
			dag.get(start).addNode(end); 
			if (start > end)
			{
				int temp = start;
				for (int i = start; i >= end; i--)
				{
					int tempValue = topSorted.get(i-1);
					topSorted.set(i, tempValue);
				}
				topSorted.set(end, temp);
			} 
			else
			{
				
			}
		}
	}
	/**
	 * give two vertex number and check if they form a cycle
	 * @param i vertex1
	 * @param j vertex 2
	 * @return true for no cycles
	 */
	
	public int getStartTime(int v)
	{
		/*
		if (topologicalSort())
		{
			int vertexIndex;
			do
			{
				vertexIndex = sorted.pop();
				for (int j : dag.get(vertexIndex).getSucList())
				{
					int startTime = (dag.get(vertexIndex).getTaskTime() + dag.get(vertexIndex).getStartTime());
					//if (dag.get(j).getPreList().size() != 0){
					if (dag.get(j).getStartTime() < startTime)
						dag.get(j).setStartTime(startTime);
					//}
				}
			}
			while(vertexIndex != v);
			return dag.get(v).getStartTime();
		}
		else
			return -1;
			*/
	}
	
	public int minCompletionTime(){
		//topologicalSort();
		Stack<Integer> sinkVertices = new Stack<Integer>();
		
		for (int i = 0; i<dag.size(); i++)
		{
			if (dag.get(i).getSucList().size() == 0)
				sinkVertices.push(i);
		}

		int maxCompletionTime = -1;
		
		for (int j : sinkVertices)
		{
			int startTime = getStartTime(j);
			if (startTime == -1)
			{
				return -1;
			}
			
			else if ((startTime + dag.get(j).getTaskTime()) > maxCompletionTime)
				 maxCompletionTime = startTime + dag.get(j).getTaskTime();
		}
		
		return maxCompletionTime;
	}
	/**
	 * perform topological sort starting from start to end
	 * @param start
	 * @return
	 */
	private void topologicalSort(int start, int end)
	{
		//Stack<Integer> sorted = new Stack<Integer>();
		
		//ArrayList<Integer> inDegree = new ArrayList<Integer>();
		//ArrayList<Boolean> visited = new ArrayList<Boolean>();
		if (start > end)
		{
			int temp = start;
			for (int i = start; i >= end; i--)
			{
				int tempValue = topSorted.get(i-1);
				topSorted.set(i, tempValue);
			}
			topSorted.set(end, temp);
		}
		
	}
	/*
		for (int k = start; k<end; k++)
		{
			if ()
		}
		
		while(nodeVisited < outDegree.size())
		{
			boolean nextFound = false;
			for (int i = 0; i < outDegree.size(); i++)
			{
				if (outDegree.get(i) == 0 && !dag.get(i).isVisited())
				{
					sorted.push(i);
					nodeVisited++;
					dag.get(i).markVisited();
					for (int v : dag.get(i).getPreList())
					{
						int temp = outDegree.get(v) - 1;
						outDegree.set(v, temp);
					}
					nextFound = true;
				}
			}
			if (nextFound == false)
			{
				System.out.println("Cycle Found at node " + sorted.peek());
			}
		}
		*/
		//pushPredecessor(dag.get(sink).getPreList());
	
	/**
	 * if there is a path from i to j return true, else false
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean pathExist(int i, int j)
	{
		Stack<Node> nodesToVisit = new Stack<Node>();
		nodesToVisit.push(dag.get(i));
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		visited.put(i, i);
		while(!nodesToVisit.isEmpty())
		{
			Node current = nodesToVisit.pop();
			ArrayList<Integer> children = current.getNodeList();
			
			if (children.size() != 0)
			{
				
				int k;
				for(k = 0; k < children.size(); k++)
				{
					int index = children.get(k);
					if (index == j)
						return true;
					else if (!visited.containsKey(index))
					{
						visited.put(index, index);
						nodesToVisit.push(dag.get(index));
					}
				}
			}
		}
		return false;
	}
	
}
