/*
11. Container With Most Water: 

Problem Description:
 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
Note: You may not slant the container and n is at least 2.

Example: Input: [1,8,6,2,5,4,8,3,7] Output: 49 Explanation: choose a[1] and a[8], the lower is a[8] * (8-1) = 49

Tag: Two Pointers
*/
class Solution {
    public int maxArea(int[] height) {
        int result = 0;
        int n = height.length;
        int l = 0;
        int r = n-1;
        while (l < r) {
            result = Math.max(result, Math.min(height[l], height[r]) * (r-l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return result;
    }
}