import java.util.*;


// this problem is very similar to LeetCode Problem 3: Longest Substring Without Repeating Characters

// strat: use hashset so that we know when there's a duplicate

public class A1_Q3b {

	public static int protocol_3b(int[] arrangement) {
		
		Set<Integer> set = new HashSet<>();
		int left = 0;
		int result = 0;
		for (int right = 0; right < arrangement.length; right++) {
			while (set.contains(arrangement[right])) { // when it is a duplicate
				set.remove(arrangement[left]);			// remove the left-most element
				left++;
			}
			set.add(arrangement[right]);
			result = Math.max(result, right - left + 1);
		}
		return result;
	}

}
