import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class Tests {
	TaskSet ts;
	@Before 
	public void init() {
		ts = new TaskSet();
	}
	
	@Test
	public void add() {
		ts.addTask(8); //adds task 0 with time 8
		ts.addTask(3); //adds task 1 with time 3
		ts.addTask(5); //adds task 2 with time 5
		assertEquals("Mulptile source test success", 8, ts.minCompletionTime());
		//ts.minCompletionTime(); //should return 8, since task 0 takes time 8 to complete.
		/* Note it is not the min completion time of any task, but the earliest the entire set can complete. */
		/*
		ts.addConstraint(0, 2); //task 2 must precede task 0
		ts.minCompletionTime(); //should return 13 (task 0 cannot start until time 5)
		ts.addConstraint(0, 1); //task 1 must precede task 0
		ts.minCompletionTime(); //should return 13
		ts.getStartTime(0); //should return 5
		ts.getStartTime(1); //should return 0
		ts.getStartTime(2); //should return 0
		ts.addConstraint(1, 2); //task 2 must precede task 1
		ts.minCompletionTime(); //should return 16
		ts.getStartTime(0); //should return 8
		ts.getStartTime(1); //should return 5
		ts.getStartTime(2); //should return 0
		ts.addConstraint(1, 0); //task 0 must precede task 1 (creates loop)
		ts.minCompletionTime(); //should return -1
		ts.getStartTime(0); //should return -1
		ts.getStartTime(1); //should return -1
		ts.getStartTime(2); //should return 0 (no loops in prerequisites)
		*/
	}
	
	@Test public void addConstraint()
	{
		ts.addTask(8); //adds task 0 with time 8
		ts.addTask(3); //adds task 1 with time 3
		ts.addTask(5); //adds task 2 with time 5
		//assertEquals("Mulptile source test success", 8, ts.minCompletionTime());
		//ts.minCompletionTime(); //should return 8, since task 0 takes time 8 to complete.
		/* Note it is not the min completion time of any task, but the earliest the entire set can complete. */
		ts.addConstraint(0, 1); //task 1 must precede task 0
		assertEquals(11, ts.minCompletionTime());
		assertEquals(3, ts.getStartTime(0));
		assertEquals(0, ts.getStartTime(1));
		assertEquals(0, ts.getStartTime(2));
		//ts.minCompletionTime(); //should return 13 (task 0 cannot start until time 5)
		ts.addConstraint(0, 2); //task 1 must precede task 0
		assertEquals(13, ts.minCompletionTime());
		assertEquals(5, ts.getStartTime(0));
		assertEquals(0, ts.getStartTime(1));
		assertEquals(0, ts.getStartTime(2));
	}

	@Test public void cycles()
	{
		ts.addTask(8); //adds task 0 with time 8
		ts.addTask(3); //adds task 1 with time 3
		ts.addTask(5); //adds task 2 with time 5
		ts.addTask(7); //adds task 2 with time 5
		ts.addTask(6); //adds task 2 with time 5
		//assertEquals("Mulptile source test success", 8, ts.minCompletionTime());
		//ts.minCompletionTime(); //should return 8, since task 0 takes time 8 to complete.
		/* Note it is not the min completion time of any task, but the earliest the entire set can complete. */
		ts.addConstraint(1, 0); //task 0 must precede task 1
		ts.addConstraint(2, 1);
		ts.addConstraint(3, 1);
		ts.addConstraint(3, 2);
		ts.addConstraint(4, 2);
		assertEquals(23, ts.minCompletionTime());
		assertEquals(0, ts.getStartTime(0));
		assertEquals(8, ts.getStartTime(1));
		assertEquals(11, ts.getStartTime(2));
		assertEquals(16, ts.getStartTime(3));
		assertEquals(16, ts.getStartTime(4));
		//ts.minCompletionTime(); //should return 13 (task 0 cannot start until time 5)
		ts.addConstraint(1, 4); //task 4 must precede task 1
		assertEquals(-1, ts.minCompletionTime());
		assertEquals(-1, ts.getStartTime(0));
		assertEquals(-1, ts.getStartTime(1));
		assertEquals(-1, ts.getStartTime(2));
		assertEquals(-1, ts.getStartTime(3));
		assertEquals(-1, ts.getStartTime(4));
	}
}
