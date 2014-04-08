public class Collatz()
{
	HashMap<int, int> result = new HashMap<int, int>();
	public static int cycleLength(int n)
	{
		long int t = n;
		if (t == 1)
			return 1;
		int count = 2;
		while(t != 1)
		{
			if (result.containsKey(t))
				return result.get(t);

			if (t%2 == 0) // if n is even
			{
				t = t/2;
			}
			else
			{
				t = t*3+1
			}
			count++;

			result.put(t, count);	
		}
		return count;
	}

	public static int maximumCycle(int i, int j)
	{
		int maxCount = 0;
		for (int k = i; k <= j; k++)
		{
			if (cycleLength(k) > maxCount)
				maxCount = cycleLength(k);
		}
		return maxCount;
	}
}
