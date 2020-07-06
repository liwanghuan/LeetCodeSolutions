// Leetcode 279. Perfect Squares
/* 
* Problem Description: Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

* Example:

* Example 1:
Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.

*Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/

// Solution 1: Dynamic Programming. Space Complexity: O(n), Time Complexity: O(n^2)

class Solution {
    int[] track;
    public int numSquares(int n) {
        track = new int[n+1];
        for (int k = 1; k < n+1; k++) {
            track[k] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j*j <=i; j++) {
                track[i] = Math.min(track[i], track[i-j*j] + 1);
            }
        }
        return track[n];
    }
}

// Solution 2: Static Dynamic Programming. Lazy Load from front. Space Complexity: O(k), Time Complexity: Min: O(logn) Max: O(n)

class Solution {
	List<Integer> track;

	public int numSquares(int n) {
		track = new ArrayList<>();
		track.add(0);
		while (track.size() <= n) {
			int m = track.size();
			int result = Integer.MAX_VALUE;
			for(int i = 1; i*i <= m; i++) {
				result = Math.min(result, track.get(m-i*i) + 1);
			}
			track.add(result);
		}
		return track.get(n);
	}
}

// Solution 3: Breadth First Search. Space Complexity: O(n), Time Complexity: O(nlogn)

class Solution {

	public int numSquares(int n) {
		Queue<Integer> pq = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		pq.offer(0);
		visited.add(0);
		int depth = 0; // depth will be the result 
		while (!pq.isEmpty()) {
            int size = pq.size();
			depth ++;
			for (int i = 0; i < size; i++){
				int top = pq.poll();
				for (int j = 1; j*j + top <= n; j++) {
					int next = top+j*j;
					if (next == n) return depth;
					if (!visited.contains(next)) {
						pq.offer(next);
						visited.add(next);
					}
				}
			}
		}
		return depth;
	}
}

// Solution 4 (not working, but good to try): Depth First Search. Time Complexity: O(n^2) Spoace Complexity: O(1) Got TLE not work in this prob. 
class Solution {
	int result;
	public int numSquares(int n) {
		if (n < 0) return 0;
		result = n;
		for (int i = (int) Math.sqrt(n); i>=1; i--) {
			if (i*i == n) return 1;
			else dfs(1, n-i*i);
		}
		return result;
	}

	private void dfs(int currentdepth, int currentVal) {
		for (int i = (int) Math.sqrt(currentVal); i>=1; i--) {
		  if (i*i == currentVal) {
		    result = Math.min(currentdepth + 1, result);
		  } else {
		    dfs(currentdepth+1, currentVal - i*i);
		  }
		}
	}
}
