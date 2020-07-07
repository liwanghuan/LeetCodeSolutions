// 62. Unique Path
/*
* Problem Description: A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

* Example: 
* Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

* Example 2:
Input: m = 7, n = 3
Output: 28
*/
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] path = new int[m+1][n+1];
        path[0][1] = 1;
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                path[i][j] = path[i-1][j] + path[i][j-1];
            }
        }
        return path[m][n];
    }
}